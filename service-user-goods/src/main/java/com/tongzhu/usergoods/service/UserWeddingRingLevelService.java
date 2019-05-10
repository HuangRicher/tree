package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.UserWeddingRingLevel;

/**
 * Created by Administrator on 2019/1/9 0009.
 */
public interface UserWeddingRingLevelService {
    UserWeddingRingLevel getUserWeddingRingLevelByUserId(String userId);

    int updateByPrimaryKeySelective(UserWeddingRingLevel userWeddingRingLevel);

    int insertSelective(UserWeddingRingLevel userWeddingRingLevel);
}
