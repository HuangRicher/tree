package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.UserWeddingRingLevelMapper;
import com.tongzhu.usergoods.model.UserWeddingRingLevel;
import com.tongzhu.usergoods.service.UserWeddingRingLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/9 0009.
 */
@Service
public class UserWeddingRingLevelServiceImpl implements UserWeddingRingLevelService {

    @Autowired
    private UserWeddingRingLevelMapper userWeddingRingLevelMapper;

    @Override
    public UserWeddingRingLevel getUserWeddingRingLevelByUserId(String userId) {
        return userWeddingRingLevelMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserWeddingRingLevel userWeddingRingLevel) {
        userWeddingRingLevel.setUpdateDate(new Date());
        return userWeddingRingLevelMapper.updateByPrimaryKeySelective(userWeddingRingLevel);
    }

    @Override
    public int insertSelective(UserWeddingRingLevel userWeddingRingLevel) {
        userWeddingRingLevel.setUpdateDate(new Date());
        return userWeddingRingLevelMapper.insertSelective(userWeddingRingLevel);
    }
}
