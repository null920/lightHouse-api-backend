package com.lighthouse.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighthouse.api.common.ErrorCode;
import com.lighthouse.api.exception.BusinessException;
import com.lighthouse.api.mapper.UserInterfaceInfoMapper;
import com.lighthouse.api.service.UserInterfaceInfoService;

import com.lighthouse.common.entity.UserInterfaceInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Ycri
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service实现
 * @createDate 2024-06-04 16:44:41
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {
    @Resource
    private RedissonClient redissonClient;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
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
        // 分布式锁 用户同时只能有一个调用接口的行为
        RLock lock = redissonClient.getLock(String.format("lightHouse:invokeInterface:userId:%s", userId));
        int invokeCount = 5;
        try {
            while (true) {
                if (invokeCount <= 0) {
                    throw new BusinessException(ErrorCode.LOCK_TIMEOUT);
                }
                if (lock.tryLock(0, 10000, TimeUnit.MILLISECONDS)) {
                    UpdateWrapper<UserInterfaceInfo> wrapper = new UpdateWrapper<>();
                    wrapper.eq("interface_info_id", interfaceInfoId);
                    wrapper.eq("user_id", userId);
                    // 剩余次数大于0
                    wrapper.gt("left_invoke_num", 0);
                    wrapper.setSql("left_invoke_num = left_invoke_num - 1, total_invoke_num = total_invoke_num + 1");
                    boolean updateResult = this.update(wrapper);
                    if (!updateResult) {
                        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "调用接口失败");
                    }
                    return true;
                } else {
                    invokeCount--;
                }
            }
        } catch (InterruptedException e) {
            log.error("invokeInterface Exception", e);
            return false;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @Override
    public void refreshInvokeCount(List<Long> userInterfaceInfoIdList) {
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", userInterfaceInfoIdList);
        updateWrapper.set("left_invoke_num", 10);
        this.update(updateWrapper);
    }
}




