package com.lighthouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lighthouse.api.annotation.AuthCheck;
import com.lighthouse.api.common.BaseResponse;
import com.lighthouse.api.common.ErrorCode;
import com.lighthouse.api.common.ResultUtils;
import com.lighthouse.api.constant.UserConstant;
import com.lighthouse.api.exception.BusinessException;
import com.lighthouse.api.mapper.InterfaceInfoMapper;
import com.lighthouse.api.mapper.UserInterfaceInfoMapper;
import com.lighthouse.api.model.vo.InterfaceInfoVO;
import com.lighthouse.api.model.vo.UserInterfaceInfoVO;
import com.lighthouse.common.entity.InterfaceInfo;
import com.lighthouse.common.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 接口使用分析
 *
 * @author null&&
 * @Date 2024/6/7 19:39
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterface() {
        List<UserInterfaceInfoVO> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterface(3);
        Map<Long, List<UserInterfaceInfoVO>> interfaceInfoIdObjMap = userInterfaceInfoList.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> interfaceInfoList = interfaceInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(interfaceInfoList)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = interfaceInfoList.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            Long totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }

}
