package com.lighthouse.api.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口上下线请求
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}