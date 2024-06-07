package com.lighthouse.api.model.vo;

import com.lighthouse.common.entity.UserInterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author null&&
 * @Date 2024/6/7 22:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoVO extends UserInterfaceInfo {
    /**
     * 调用次数
     */
    private Long totalNum;
}
