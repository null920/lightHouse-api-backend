package com.lighthouse.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lighthouse.api.model.vo.UserInterfaceInfoVO;
import com.lighthouse.common.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @author Ycri
 * @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Mapper
 * @createDate 2024-06-04 16:44:41
 * @Entity generator.domain.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfoVO> listTopInvokeInterface(int limit);
}




