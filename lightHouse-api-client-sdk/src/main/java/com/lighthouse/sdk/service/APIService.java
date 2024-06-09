package com.lighthouse.sdk.service;

/**
 * @author null&&
 * @Date 2024/6/9 09:19
 */
public interface APIService {

    /**
     * 定义请求
     *
     * @param restfulUrl restful风格的请求地址
     * @param args       参数
     * @return 请求结果
     */
    <T> String definitionRequest(String restfulUrl, String method, T args);
}
