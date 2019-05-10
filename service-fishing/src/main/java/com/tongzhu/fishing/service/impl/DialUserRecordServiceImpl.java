package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.mapper.DialUserRecordMapper;
import com.tongzhu.fishing.model.DialUserRecord;
import com.tongzhu.fishing.model.DialUserRecordKey;
import com.tongzhu.fishing.service.DialUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/4 0004.
 */

@Service
public class DialUserRecordServiceImpl implements DialUserRecordService {

    @Autowired
    private DialUserRecordMapper dialUserRecordMapper;
    @Override
    public DialUserRecord getDialUserRecordByUserIdAndDialId(String userId, Integer id) {
        DialUserRecordKey dialUserRecordKey = new DialUserRecordKey();
        dialUserRecordKey.setUserId(userId);
        dialUserRecordKey.setDialId(id);
        DialUserRecord dialUserRecord = dialUserRecordMapper.selectByPrimaryKey(dialUserRecordKey);
        return dialUserRecord;
    }

    @Override
    public int insertSelective(DialUserRecord dialUserRecord) {
        return dialUserRecordMapper.insertSelective(dialUserRecord);
    }

    @Override
    public int updateByPrimaryKeySelective(DialUserRecord dialUserRecord) {
        return dialUserRecordMapper.updateByPrimaryKeySelective(dialUserRecord);
    }
}
