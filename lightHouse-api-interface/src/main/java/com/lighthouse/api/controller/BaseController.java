package com.lighthouse.api.controller;

import com.lighthouse.api.service.InterfaceAPIService;

import com.lighthouse.common.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 名称API
 *
 * @author null&&
 * @Date 2024/6/1 21:43
 */
@RestController
@RequestMapping("/interface")
public class BaseController {

    @Resource
    private InterfaceAPIService interfaceAPIService;

    @PostMapping("/name")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        if (user == null || StringUtils.isBlank(user.getUserName())) {
            return "POST body 参数错误";
        }
        interfaceAPIService.authentication(request);
        return "POST body 你的名字是" + user.getUserName();
    }


    @GetMapping("/fished")
    public String getFishedCalendar(HttpServletRequest request) {
        interfaceAPIService.authentication(request);
        return interfaceAPIService.getFishedCalendar();
    }

    @GetMapping("/worker")
    public String getWorkerCalendar(HttpServletRequest request) {
        interfaceAPIService.authentication(request);
        return interfaceAPIService.getWorkerCalendar();
    }

    @GetMapping("/world")
    public String getSixtySecondsWorld(HttpServletRequest request) {
        interfaceAPIService.authentication(request);
        return interfaceAPIService.getSixtySecondsWorld();
    }

}
