package com.tongzhu.friend.service;

import com.tongzhu.friend.model.TaskActivityRewards;

import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
public interface TaskActivityRewardsService {
    /**
     * 获取所有活跃度宝箱基础配置
     * @param userId
     * @return
     */
    List<TaskActivityRewards> getTaskActivityRewardsList(String userId);

    TaskActivityRewards getTaskActivityRewards(Integer id);
}
