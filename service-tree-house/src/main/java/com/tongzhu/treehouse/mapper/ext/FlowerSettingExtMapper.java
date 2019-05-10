package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.FlowerNumberSetting;
import com.tongzhu.treehouse.model.FlowerSeedsDrawSetting;
import com.tongzhu.treehouse.model.FlowerSetting;

import java.util.List;

public interface FlowerSettingExtMapper {

    void batchInsertSeeds(List<FlowerSetting> list);

    void batchInsertDraw(List<FlowerSeedsDrawSetting> list);

    void batchInsertNumber(List<FlowerNumberSetting> list);
}
