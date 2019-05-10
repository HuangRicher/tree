package com.tongzhu.welfare.service.impl;

import com.tongzhu.welfare.domain.NewPlayerGuide;
import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.domain.UserDetail;
import com.tongzhu.welfare.service.UserService;

@Component
public class UserServiceHystrix implements UserService {
    @Override
    public UserDetail getUserDetail(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public User findByUserId(String userId) {
        throw new RuntimeException("error");
    }

	@Override
	public void updateCrunchies(String userId, Integer type, Integer recordValues) {
		throw new RuntimeException("error");
	}

    @Override
    public void sendSkillRedTip(String userId) {

    }

    @Override
    public NewPlayerGuide getNewPlayerGuide(String userId) {
        return null;
    }
}
