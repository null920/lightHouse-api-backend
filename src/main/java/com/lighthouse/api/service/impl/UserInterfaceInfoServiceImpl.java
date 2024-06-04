package com.lighthouse.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighthouse.api.common.ErrorCode;
import com.lighthouse.api.exception.BusinessException;
import com.lighthouse.api.mapper.UserInterfaceInfoMapper;
import com.lighthouse.api.model.entity.UserInterfaceInfo;
import com.lighthouse.api.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ycri
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service实现
 * @createDate 2024-06-04 16:44:41
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Long totalInvokeNum = userInterfaceInfo.getTotalInvokeNum();
        Long leftInvokeNum = userInterfaceInfo.getLeftInvokeNum();
        // 创建时，参数不能为空
        if (add) {
            if (interfaceInfoId <= 0 || userId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        // 有参数则校验
        if (leftInvokeNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
        }
        //todo 加分布式锁

        UpdateWrapper<UserInterfaceInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("interfaceInfoId", interfaceInfoId);
        wrapper.eq("userId", userId);
        // 剩余次数大于0
        wrapper.gt("leftInvokeNum", 0);
        wrapper.setSql("leftInvokeNum = leftInvokeNum - 1, totalInvokeNum = totalInvokeNum + 1");
        return this.update(wrapper);
    }
}




