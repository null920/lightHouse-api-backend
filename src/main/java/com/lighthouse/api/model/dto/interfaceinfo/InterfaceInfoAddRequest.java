package com.lighthouse.api.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    private static final long serialVersionUID = 2246575518134403821L;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 描述
     */
    private String description;
}