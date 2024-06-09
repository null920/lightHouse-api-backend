package com.lighthouse.sdk.service;

/**
 * 请求策略
 *
 * @author null&&
 * @Date 2024/6/9 09:32
 */
public interface BaseStrategy {
    /**
     * @param restfulUrl restful风格的请求地址
     * @param args       请求参数
     * @param apiService 请求服务
     * @return 返回结果
     */
    String handlerRequest(String restfulUrl, String method, String args, APIService apiService);
}
