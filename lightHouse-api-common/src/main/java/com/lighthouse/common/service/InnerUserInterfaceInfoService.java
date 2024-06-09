package com.lighthouse.common.service;

/**
 * @author Ycri
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service
 * @createDate 2024-06-04 16:44:41
 */
public interface InnerUserInterfaceInfoService {
    /**
     * 调整接口调用次数
     *
     * @param interfaceInfoId 接口id
     * @param userId          用户id
     * @return 是否成功
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 查询剩余调用次数
     *
     * @param interfaceInfoId 接口id
     * @param userId          用户id
     * @return 剩余调用次数
     */
    long getLeftInvokeNum(long interfaceInfoId, long userId);
}
