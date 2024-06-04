package com.lighthouse.api.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 总接口调用次数
     */
    private Long totalInvokeNum;

    /**
     * 剩余接口调用次数
     */
    private Long leftInvokeNum;

    /**
     * 0-正常 1-禁用
     */
    private Integer status;

    private static final long serialVersionUID = 4469866669146211797L;
}