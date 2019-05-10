package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.WorkPositionSetting;

public interface WorkPositionSettingService {
    WorkPositionSetting findByWorkTypeIdAndOrder(int workTypeId, int order);
}
