package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseLevelSetting;

import java.util.List;

public interface TreeHouseLevelSettingService {

    TreeHouseLevelSetting findByTreeLevel(int level);

    List<TreeHouseLevelSetting> findAll();

    void update(TreeHouseLevelSetting setting);
}
