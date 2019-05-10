package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.enums.StatusEnum;
import com.tongzhu.fishing.mapper.DialInfoMapper;
import com.tongzhu.fishing.mapper.ext.DialInfoExtMapper;
import com.tongzhu.fishing.model.DialInfo;
import com.tongzhu.fishing.model.DialInfoExample;
import com.tongzhu.fishing.service.DialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/10/18 0018.
 */
@Service
public class DialInfoServiceImpl implements DialInfoService{

    @Autowired
    private DialInfoMapper dialInfoMapper;

    @Autowired
    private DialInfoExtMapper dialInfoExtMapper;

    @Override
    public List<DialInfo> getDialInfoList() {
        DialInfoExample dialInfoExample = new DialInfoExample();
        dialInfoExample.createCriteria().andStatusEqualTo(StatusEnum.NORMAL.getStatusCode());
        return dialInfoMapper.selectByExample(dialInfoExample);
    }

    @Override
    public Integer getForHeavySum(String userId) {
        return dialInfoExtMapper.getForHeavySum(userId);
    }

    @Override
    public List<DialInfo> getDialInfoList(String userId) {
        return dialInfoExtMapper.getDialInfoList(userId);
    }

    @Override
    public DialInfo getDialInfoById(Integer dialId) {
        return dialInfoMapper.selectByPrimaryKey(dialId);
    }
}
