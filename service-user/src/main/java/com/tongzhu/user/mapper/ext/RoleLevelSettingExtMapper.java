package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.RoleLevelSetting;

import java.util.List;

public interface RoleLevelSettingExtMapper {
    void batchInsert(List<RoleLevelSetting> list);
}
