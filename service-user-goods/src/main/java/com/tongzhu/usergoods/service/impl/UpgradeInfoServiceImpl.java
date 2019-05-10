package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.UpgradeInfoMapper;
import com.tongzhu.usergoods.model.UpgradeInfo;
import com.tongzhu.usergoods.model.UpgradeInfoExample;
import com.tongzhu.usergoods.service.UpgradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/12/22 0022.
 */
@Service
public class UpgradeInfoServiceImpl implements UpgradeInfoService {

    @Autowired
    private UpgradeInfoMapper upgradeInfoMapper;


    @Override
    public UpgradeInfo getUpgradeInfoByIdAndQuality(String upgradeId, Integer quality, Integer wearPosition) {
        UpgradeInfoExample upgradeInfoExample = new UpgradeInfoExample();
        upgradeInfoExample.createCriteria().andUpgradeIdEqualTo(upgradeId).andQualityEqualTo(quality).andWearPositionLike("%"+wearPosition+",%");
        List<UpgradeInfo> upgradeInfos = upgradeInfoMapper.selectByExample(upgradeInfoExample);
        if (upgradeInfos.size() > 0) {
            return upgradeInfos.get(0);
        }
        return null;
    }

    @Override
    public UpgradeInfo getUpgradeInfoByQualityAndWearPosition(Integer quality, int wearPosition, Integer type) {
        UpgradeInfoExample upgradeInfoExample = new UpgradeInfoExample();
        upgradeInfoExample.createCriteria().andTypeEqualTo(type).andQualityEqualTo(quality).andWearPositionLike("%"+wearPosition+"%");
        List<UpgradeInfo> upgradeInfos = upgradeInfoMapper.selectByExample(upgradeInfoExample);
        if (upgradeInfos.size() > 0) {
            return upgradeInfos.get(0);
        }
        return null;
    }
}
