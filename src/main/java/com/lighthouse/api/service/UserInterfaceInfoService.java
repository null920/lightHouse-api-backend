package com.lighthouse.api.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lighthouse.common.entity.UserInterfaceInfo;

import java.util.List;


/**
 * @author Ycri
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service
 * @createDate 2024-06-04 16:44:41
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 验证接口参数
     *
     * @param userInterfaceInfo 参数信息
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


    /**
     * 调整接口调用次数
     *
     * @param interfaceInfoId 接口id
     * @param userId          用户id
     * @return 是否成功
     */
    boolean invokeCount(long interfaceInfoId, long userId);


    void refreshInvokeCount(List<Long> userInterfaceInfoIdList);
}
