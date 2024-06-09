package com.lighthouse.common.service;

import com.lighthouse.common.entity.User;

/**
 * 用户服务
 */
public interface InnerUserService {
    /**
     * 根据身份标识符查用户
     *
     * @param accessKey 身份标识符
     * @return 用户
     */
    User getInvokeUser(String accessKey);

}
