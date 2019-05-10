package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.enums.StatusEnum;
import com.tongzhu.fishing.mapper.FishInfoMapper;
import com.tongzhu.fishing.mapper.ext.FishInfoExtMapper;
import com.tongzhu.fishing.model.FishInfo;
import com.tongzhu.fishing.model.FishInfoExample;
import com.tongzhu.fishing.service.FishInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishInfoServiceImpl implements FishInfoService {

    @Autowired
    private FishInfoMapper fishInfoMapper;

    @Autowired
    private FishInfoExtMapper fishInfoExtMapper;

    @Override
    public List<FishInfo> getFishInfoList() {
        FishInfoExample fishInfoExample = new FishInfoExample();
        fishInfoExample.createCriteria().andStatusEqualTo(StatusEnum.NORMAL.getStatusCode());
        return fishInfoMapper.selectByExample(fishInfoExample);
    }

    @Override
    public Integer getSumSeniorProbability() {
        return fishInfoExtMapper.getSumSeniorProbability();
    }

    @Override
    public Integer getSumRrareProbability() {
        return fishInfoExtMapper.getSumRrareProbability();
    }

    @Override
    public Integer getSumCommonProbability() {
        return fishInfoExtMapper.getSumCommonProbability();
    }

    @Override
    public FishInfo getFishInfoById(Integer id) {
        return fishInfoMapper.selectByPrimaryKey(id);
    }
}
