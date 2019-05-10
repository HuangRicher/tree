package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseRoomSetting;

public interface TreeHouseRoomSettingService {

    /**
     * 根据繁荣度查找相关配置
     * @param flourishingRate
     * @return
     */
    TreeHouseRoomSetting getByFlourishingRate(int flourishingRate);

    /**
     * 根据设定值每日消耗木材维持树屋房间上限数
     */
    void dailyConsumeBySetting();

    /**
     * 根据房间数上限查找相关配置
     * @return
     */
    TreeHouseRoomSetting findByWorkerCount(int count);


}
