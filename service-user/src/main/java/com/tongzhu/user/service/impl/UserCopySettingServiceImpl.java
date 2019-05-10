package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.UserCopySettingMapper;
import com.tongzhu.user.model.UserCopySetting;
import com.tongzhu.user.model.UserCopySettingExample;
import com.tongzhu.user.service.UserCopySettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCopySettingServiceImpl implements UserCopySettingService {

    @Autowired
    private UserCopySettingMapper userCopySettingMapper;


    @Override
    public void add(UserCopySetting setting) {
        userCopySettingMapper.insertSelective(setting);
    }

    @Override
    public List<UserCopySetting> findByCopyId(Integer copyId) {
        UserCopySettingExample example=new UserCopySettingExample();
        example.createCriteria().andCopyIdEqualTo(copyId);
        return userCopySettingMapper.selectByExample(example);
    }

    @Override
    public void deleteAll() {
        UserCopySettingExample example=new UserCopySettingExample();
        userCopySettingMapper.deleteByExample(example);
    }
}
