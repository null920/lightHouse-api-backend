package com.lighthouse.sdk.service.impl;

import com.google.gson.Gson;
import com.lighthouse.common.entity.User;
import com.lighthouse.sdk.service.APIService;
import com.lighthouse.sdk.service.BaseStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author null&&
 * @Date 2024/6/9 09:46
 */
@Slf4j
public class UsernameStrategy implements BaseStrategy {
    @Override
    public String handlerRequest(String restfulUrl, String method, String args, APIService apiService) {
        log.info("url = {} , args = {}", restfulUrl, args);
        Gson gson = new Gson();
        User user;
        try {
            user = gson.fromJson(args, User.class);
        } catch (Exception e) {
            log.error("转换json出现异常,错误为: {},输入参数为: {}", e.getMessage(), args);
            return "{\"code\":\"10001\",\"msg\":\"参数错误\"}";
        }
        if (user == null) {
            user = new User();
        }
        return apiService.definitionRequest(restfulUrl, method, user);
    }
}
