package com.lighthouse.sdk.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.lighthouse.sdk.client.LightHouseAPIClient;
import com.lighthouse.sdk.constant.InterfaceUrl;
import com.lighthouse.sdk.service.APIService;
import com.lighthouse.sdk.utils.SignUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author null&&
 * @Date 2024/6/9 09:22
 */
@Slf4j
@Data
public class APIServiceImpl implements APIService {

    private LightHouseAPIClient lightHouseAPIClient;

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", lightHouseAPIClient.getAccessKey());
        // 随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        // 请求参数
        hashMap.put("body", body);
        // 时间戳
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 签名
        hashMap.put("sign", SignUtils.getSign(body, lightHouseAPIClient.getSecretKey()));
        return hashMap;
    }

    @Override
    public <T> String definitionRequest(String restfulUrl, String method, T args) {
        String json = JSONUtil.toJsonStr(args);
        HttpResponse httpResponse = null;
        if ("POST".equals(method)) {
            httpResponse = HttpRequest.post(InterfaceUrl.GATEWAY_HOST + restfulUrl)
                    .addHeaders(getHeaderMap(json))
                    .body(json)
                    .execute();
        } else if ("GET".equals(method)) {
            httpResponse = HttpRequest.get(InterfaceUrl.GATEWAY_HOST + restfulUrl)
                    .addHeaders(getHeaderMap(json))
                    .body(json)
                    .execute();
        }
        String result = httpResponse.body();
        log.info("SDK 返回状态为: {}", httpResponse.getStatus());
        log.info("SDK 返回结果为: {}", result);
        return result;
    }
}
