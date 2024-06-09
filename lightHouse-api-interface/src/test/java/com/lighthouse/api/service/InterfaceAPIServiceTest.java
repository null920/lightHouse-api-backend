package com.lighthouse.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author null&&
 * @Date 2024/6/9 15:13
 */
@SpringBootTest
public class InterfaceAPIServiceTest {
    @Resource
    InterfaceAPIService interfaceAPIService;

    @Test
    void fishedTest() {
        String fishedCalendar = interfaceAPIService.getFishedCalendar();
        System.out.println(fishedCalendar);
    }

    @Test
    void workerTest() {
        String workerCalendar = interfaceAPIService.getWorkerCalendar();
        System.out.println(workerCalendar);
    }
}
