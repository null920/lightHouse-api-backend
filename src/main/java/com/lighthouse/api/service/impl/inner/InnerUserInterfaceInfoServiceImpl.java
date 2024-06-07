package com.lighthouse.api.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lighthouse.api.mapper.UserInterfaceInfoMapper;
import com.lighthouse.api.service.UserInterfaceInfoService;
import com.lighthouse.common.entity.UserInterfaceInfo;
import com.lighthouse.common.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author null&&
 * @Date 2024/6/6 22:12
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    @Override
    public long getLeftInvokeNum(long interfaceInfoId, long userId) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("left_invoke_num");
        queryWrapper.eq("interface_info_id", interfaceInfoId);
        queryWrapper.eq("user_id", userId);
        return userInterfaceInfoMapper.selectOne(queryWrapper).getLeftInvokeNum();
    }
}
