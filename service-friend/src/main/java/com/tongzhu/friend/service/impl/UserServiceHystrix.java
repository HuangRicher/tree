package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.GameNPC;
import com.tongzhu.friend.domain.User;
import com.tongzhu.friend.domain.UserDetail;
import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    public Map<String, Object> updateRoleExp(String userId, Integer exp) {
        throw new RuntimeException("error");    }

    @Override
    public List<UserDetail> getUserByUserIdOrName(String searchUser) {
        throw new RuntimeException("error");
    }

    @Override
    public User recommendFriendByOppositeSex(String userId) {
        return null;
    }

    @Override
    public List<User> selectRecommendFriends(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public UserRole getUserRoleByUserId(String userId) {
        return null;
    }

}
