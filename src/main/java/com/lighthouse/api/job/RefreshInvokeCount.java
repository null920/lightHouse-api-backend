package com.lighthouse.api.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lighthouse.api.mapper.InterfaceInfoMapper;
import com.lighthouse.api.mapper.UserInterfaceInfoMapper;
import com.lighthouse.api.service.UserInterfaceInfoService;
import com.lighthouse.common.entity.InterfaceInfo;
import com.lighthouse.common.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每天 0 点 0 分刷新测试用户的调用次数
 *
 * @author null&&
 * @Date 2024/6/8 19:49
 */
@Component
@Slf4j
public class RefreshInvokeCount {
    public static final Long TEST_USER_ID = 3L;

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Scheduled(cron = "0 59 23 * * ?")
    public void refreshTestInvokeCount() {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", TEST_USER_ID);
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoService.list(queryWrapper);
        List<Long> userInterfaceInfoIdList = userInterfaceInfoList.stream().map(UserInterfaceInfo::getId).collect(Collectors.toList());
        userInterfaceInfoService.refreshInvokeCount(userInterfaceInfoIdList);
    }

}
