package com.lighthouse.api.model.vo;

import com.lighthouse.api.model.entity.InterfaceInfo;
import com.lighthouse.api.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图
 */
@Data
public class InterfaceInfoVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 接口状态 0-关闭 1-开启
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer deleted;

    /**
     * 包装类转对象
     *
     * @param interfaceInfoVO
     * @return
     */
    public static Post voToObj(InterfaceInfoVO interfaceInfoVO) {
        if (interfaceInfoVO == null) {
            return null;
        }
        Post post = new Post();
        BeanUtils.copyProperties(interfaceInfoVO, post);
        return post;
    }

    /**
     * 对象转包装类
     *
     * @param interfaceInfo
     * @return
     */
    public static InterfaceInfoVO objToVo(InterfaceInfo interfaceInfo) {
        if (interfaceInfo == null) {
            return null;
        }
        InterfaceInfoVO postVO = new InterfaceInfoVO();
        BeanUtils.copyProperties(interfaceInfo, postVO);
        return postVO;
    }
}
