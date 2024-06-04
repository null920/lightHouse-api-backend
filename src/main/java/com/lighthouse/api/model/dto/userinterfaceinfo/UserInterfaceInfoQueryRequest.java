package com.lighthouse.api.model.dto.userinterfaceinfo;

import com.lighthouse.api.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 0-正常 1-禁用
     */
    private Integer status;

    private static final long serialVersionUID = -3036342001797387590L;
}