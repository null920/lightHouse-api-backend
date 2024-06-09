package com.lighthouse.api.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author null&&
 * @Date 2024/6/9 10:41
 */
public interface InterfaceAPIService {

    /**
     * 获取摸鱼日历
     */
    String getFishedCalendar();

    /**
     * 获取打工人日历
     */
    String getWorkerCalendar();

    /**
     * 获取每天 60 秒读懂世界
     */
    String getSixtySecondsWorld();


    /**
     * 鉴权
     *
     * @param request 请求
     * @return 是否有权限
     */
    void authentication(HttpServletRequest request);
}
