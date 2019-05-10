package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.WeddingRingUpgradeInfoMapper;
import com.tongzhu.usergoods.model.WeddingRingUpgradeInfo;
import com.tongzhu.usergoods.service.WeddingRingUpgradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/9 0009.
 */
@Service
public class WeddingRingUpgradeInfoServiceImpl implements WeddingRingUpgradeInfoService {

    @Autowired
    private WeddingRingUpgradeInfoMapper weddingRingUpgradeInfoMapper;

    @Override
    public WeddingRingUpgradeInfo getWeddingRingUpgradeInfoByLevel(int level) {
        return weddingRingUpgradeInfoMapper.selectByPrimaryKey(level);
    }
}
