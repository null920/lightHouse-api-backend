package com.lighthouse.sdk.service.impl;

import com.lighthouse.sdk.service.APIService;
import com.lighthouse.sdk.service.BaseStrategy;

/**
 * @author null&&
 * @Date 2024/6/9 09:59
 */
public class BaseStrategyImpl implements BaseStrategy {
    @Override
    public String handlerRequest(String restfulUrl, String method, String args, APIService apiService) {
        return apiService.definitionRequest(restfulUrl, method, "");
    }
}
