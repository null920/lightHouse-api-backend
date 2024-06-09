package com.lighthouse.sdk;

import com.lighthouse.sdk.client.LightHouseAPIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author null&&
 * @Date 2024/6/2 15:42
 */
@Configuration
@ConfigurationProperties(prefix = "lighthouse.api.client")
@Data
@ComponentScan
public class LightHouseAPIClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public LightHouseAPIClient getLightHouseAPIClient() {
        return new LightHouseAPIClient(accessKey, secretKey);
    }
}
