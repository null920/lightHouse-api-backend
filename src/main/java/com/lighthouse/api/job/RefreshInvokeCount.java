package com.lighthouse.api.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每天 0 点 0 分刷新测试用户的调用次数
 *
 * @author null&&
 * @Date 2024/6/8 19:49
 */
@Component
@Slf4j
public class RefreshInvokeCount {
    public static final String TEST_USER_ID = "test";


    @Scheduled(cron = "0 59 23 * * *")
    public void refreshTestInvokeCount() {
        log.info("刷新用户调用次数开始");
        // 更新用户的所有接口的调用次数

        log.info("刷新用户调用次数结束");
    }


}
