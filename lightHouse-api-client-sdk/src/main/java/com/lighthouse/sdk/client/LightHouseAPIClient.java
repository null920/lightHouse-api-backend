package com.lighthouse.sdk.client;

import com.lighthouse.sdk.constant.InterfaceUrl;
import lombok.Data;

/**
 * @author null&&
 * @Date 2024/6/1 22:15
 */
@Data
public class LightHouseAPIClient {
    private String accessKey;
    private String secretKey;

    private static final String GATEWAY_HOST = InterfaceUrl.GATEWAY_HOST;

    public LightHouseAPIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
