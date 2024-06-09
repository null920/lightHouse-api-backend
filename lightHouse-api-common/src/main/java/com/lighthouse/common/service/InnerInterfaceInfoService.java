package com.lighthouse.common.service;

import com.lighthouse.common.entity.InterfaceInfo;

/**
 * @author null&&
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2024-05-30 22:38:39
 */
public interface InnerInterfaceInfoService {
    /**
     * 查询接口是否存在
     *
     * @param url    请求路径
     * @param method 请求方法
     * @return 接口信息
     */
    InterfaceInfo getInterfaceInfo(String url, String method);

}
