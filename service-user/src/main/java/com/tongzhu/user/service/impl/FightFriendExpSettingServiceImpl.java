package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.FightFriendExpSettingMapper;
import com.tongzhu.user.mapper.ext.FightFriendExpSettingExtMapper;
import com.tongzhu.user.model.FightFriendExpSetting;
import com.tongzhu.user.model.FightFriendExpSettingExample;
import com.tongzhu.user.service.FightFriendExpSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FightFriendExpSettingServiceImpl implements FightFriendExpSettingService {

    @Autowired
    private FightFriendExpSettingMapper fightFriendExpSettingMapper;

    @Autowired
    private FightFriendExpSettingExtMapper fightFriendExpSettingExtMapper;



    @Transactional
    @Override
    public void batchInsert(List<FightFriendExpSetting> settingList) {
        FightFriendExpSettingExample example=new FightFriendExpSettingExample();
        fightFriendExpSettingMapper.deleteByExample(example);
        fightFriendExpSettingExtMapper.batchInsert(settingList);
    }

    @Override
    public FightFriendExpSetting getByRoleLevel(Integer roleLevel) {
        return fightFriendExpSettingMapper.selectByPrimaryKey(roleLevel);
    }
}
