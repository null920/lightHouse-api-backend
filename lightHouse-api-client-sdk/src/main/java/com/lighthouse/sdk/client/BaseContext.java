package com.lighthouse.sdk.client;

import com.lighthouse.sdk.constant.InterfaceUrl;
import com.lighthouse.sdk.service.APIService;
import com.lighthouse.sdk.service.BaseStrategy;
import com.lighthouse.sdk.service.impl.BaseStrategyImpl;
import com.lighthouse.sdk.service.impl.UsernameStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略核心上下文
 *
 * @author null&&
 * @Date 2024/6/9 09:40
 */
@Component
public class BaseContext {
    private static final Map<String, BaseStrategy> strategyMap = new ConcurrentHashMap<>();
    private APIService apiService;
    private static final BaseStrategyImpl BASE_STRATEGY = new BaseStrategyImpl();

    static {
        strategyMap.put(InterfaceUrl.USERNAME_URL, new UsernameStrategy());
        strategyMap.put(InterfaceUrl.FISHED_URL, BASE_STRATEGY);
        strategyMap.put(InterfaceUrl.WORKER_URL, BASE_STRATEGY);
        strategyMap.put(InterfaceUrl.WORLD_URL, BASE_STRATEGY);
    }

    public String handler(String restfulUrl, String method, String args) {
        BaseStrategy baseStrategy = strategyMap.get(restfulUrl);
        return baseStrategy.handlerRequest(restfulUrl, method, args, apiService);
    }

    public void setAPIService(APIService apiService) {
        this.apiService = apiService;
    }

}
