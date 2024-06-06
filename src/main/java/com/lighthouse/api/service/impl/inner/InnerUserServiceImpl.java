package com.lighthouse.api.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lighthouse.api.common.ErrorCode;
import com.lighthouse.api.exception.BusinessException;
import com.lighthouse.api.mapper.UserMapper;
import com.lighthouse.common.entity.User;
import com.lighthouse.common.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author null&&
 * @Date 2024/6/6 22:13
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_key", accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
