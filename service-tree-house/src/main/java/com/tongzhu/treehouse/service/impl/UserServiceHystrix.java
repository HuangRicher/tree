package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserMessage;
import com.tongzhu.treehouse.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceHystrix implements UserService {
    @Override
    public User findByUserId(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public List<User> selectUserForBuyByRand(Integer minSellingPrice, Integer maxSellingPrice, String userId, Integer maxExchangeCount,Integer count) {
        throw new RuntimeException("error");
    }

    @Override
    public List<User> selectFriendsForBuyByRand(String userId, Integer status, Integer exchangeCount, Integer sellingPrice, Integer count) {
        throw new RuntimeException("error");
    }

    @Override
    public User reduceSellingPrice(String userId, String workerId) {
        throw new RuntimeException("error");
    }

    @Override
    public void updateUserSellingPrice(String userId, Integer sellingPrice) {
        throw new RuntimeException("error");
    }

    @Override
    public int countAllUser() {
        throw new RuntimeException("error");
    }

    @Override
    public int addUserMessage(UserMessage userMessage) {
        throw new RuntimeException("error");
    }
}
