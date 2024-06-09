package com.lighthouse.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighthouse.common.entity.InterfaceInfo;
import com.lighthouse.common.entity.UserInterfaceInfo;

import java.util.List;


/**
 * @author null&&
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2024-05-30 22:38:39
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 验证接口参数
     *
     * @param interfaceInfo 参数信息
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
