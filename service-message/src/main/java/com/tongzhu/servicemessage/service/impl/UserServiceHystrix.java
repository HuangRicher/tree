package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.domain.User;
import com.tongzhu.servicemessage.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserService {

    @Override
    public void updateForUserOnlineState(String userId, Integer status) {

    }

    @Override
    public User findByUserId(String userId) {
        return null;
    }

    @Override
    public int userMailMessage(String userId) {
        return 0;
    }

    @Override
    public int userMessageRow(String userId) {
        return 0;
    }

    @Override
    public int userMessageRowAndType(String userId, int type) {
        return 0;
    }

    @Override
    public Boolean checkSkillCanUpgrade(String userId) {
        return null;
    }
}
