package com.tongzhu.user.service;

import com.tongzhu.user.model.FightFriendExpSetting;

import java.util.List;

public interface FightFriendExpSettingService {

    void batchInsert(List<FightFriendExpSetting> settingList);

    FightFriendExpSetting getByRoleLevel(Integer roleLevel);
}
