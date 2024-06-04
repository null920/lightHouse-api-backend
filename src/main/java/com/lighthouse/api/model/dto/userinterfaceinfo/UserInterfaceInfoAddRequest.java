package com.lighthouse.api.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    private static final long serialVersionUID = 2246575518134403821L;

    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 被调用接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总接口调用次数
     */
    private Long totalInvokeNum;

    /**
     * 剩余接口调用次数
     */
    private Long leftInvokeNum;
}