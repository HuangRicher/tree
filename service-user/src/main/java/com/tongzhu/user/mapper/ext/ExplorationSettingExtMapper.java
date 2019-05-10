package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.ExplorationSetting;

import java.util.List;

public interface ExplorationSettingExtMapper {

    void batchInsert(List<ExplorationSetting> list);
}
