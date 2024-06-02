package com.lighthouse.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighthouse.api.model.entity.InterfaceInfo;

/**
 * @author null&&
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2024-05-30 22:38:39
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
