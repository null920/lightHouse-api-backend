package com.lighthouse.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighthouse.api.common.ErrorCode;
import com.lighthouse.api.exception.BusinessException;
import com.lighthouse.api.exception.ThrowUtils;
import com.lighthouse.api.mapper.InterfaceInfoMapper;
import com.lighthouse.api.model.entity.InterfaceInfo;
import com.lighthouse.api.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author null&&
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2024-05-30 22:38:39
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Long userId = interfaceInfo.getUserId();
        String description = interfaceInfo.getDescription();
        Integer status = interfaceInfo.getStatus();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, url, method, requestHeader, responseHeader, description), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }


}




