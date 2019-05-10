package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.mapper.DailyTaskExpInfoMapper;
import com.tongzhu.friend.model.DailyTaskExpInfo;
import com.tongzhu.friend.service.DailyTaskExpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/17 0017.
 */
@Service
public class DailyTaskExpInfoServiceImpl implements DailyTaskExpInfoService {

    @Autowired
    private DailyTaskExpInfoMapper dailyTaskExpInfoMapper;

    @Override
    public DailyTaskExpInfo getDailyTaskExpInfo(Integer roleLevel) {
        return dailyTaskExpInfoMapper.selectByPrimaryKey(roleLevel);
    }
}
