package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.UpgradeInfo;

/**
 * Created by Administrator on 2018/12/22 0022.
 */
public interface UpgradeInfoService {



    UpgradeInfo getUpgradeInfoByIdAndQuality(String upgradeId, Integer quality, Integer wearPosition);

    UpgradeInfo getUpgradeInfoByQualityAndWearPosition(Integer quality, int wearPosition, Integer type);
}
