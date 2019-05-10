package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.mapper.TaskActivityRewardsMapper;
import com.tongzhu.friend.model.TaskActivityRewards;
import com.tongzhu.friend.model.TaskActivityRewardsExample;
import com.tongzhu.friend.service.TaskActivityRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
@Service
public class TaskActivityRewardsServiceImpl implements TaskActivityRewardsService {
    @Autowired
    private TaskActivityRewardsMapper taskActivityRewardsMapper;
    @Override
    public List<TaskActivityRewards> getTaskActivityRewardsList(String userId) {
        TaskActivityRewardsExample taskActivityRewardsExample = new TaskActivityRewardsExample();
        return taskActivityRewardsMapper.selectByExample(taskActivityRewardsExample);
    }

    @Override
    public TaskActivityRewards getTaskActivityRewards(Integer id) {
        return taskActivityRewardsMapper.selectByPrimaryKey(id);
    }
}
