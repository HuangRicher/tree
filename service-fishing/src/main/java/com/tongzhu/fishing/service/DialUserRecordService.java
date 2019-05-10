package com.tongzhu.fishing.service;

import com.tongzhu.fishing.model.DialUserRecord;

/**
 * Created by Administrator on 2019/1/4 0004.
 */
public interface DialUserRecordService {
    DialUserRecord getDialUserRecordByUserIdAndDialId(String userId, Integer id);

    int insertSelective(DialUserRecord dialUserRecord);

    int updateByPrimaryKeySelective(DialUserRecord dialUserRecord);
}
