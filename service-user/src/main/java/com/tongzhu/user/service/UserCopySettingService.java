package com.tongzhu.user.service;

import com.tongzhu.user.model.UserCopySetting;

import java.util.List;

public interface UserCopySettingService {
    void add(UserCopySetting setting);
    List<UserCopySetting> findByCopyId(Integer copyId);
    void deleteAll();
}
