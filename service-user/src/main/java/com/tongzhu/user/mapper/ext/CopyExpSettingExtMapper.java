package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.CopyExpSetting;

import java.util.List;

public interface CopyExpSettingExtMapper {
    void batchInsert(List<CopyExpSetting> list);
}
