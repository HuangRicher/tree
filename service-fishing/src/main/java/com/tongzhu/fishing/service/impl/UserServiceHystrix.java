package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.domain.User;
import com.tongzhu.fishing.domain.UserMessage;
import com.tongzhu.fishing.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserService {
    @Override
    public User findByUserId(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public int addUserMessage(UserMessage userMessage) {
            throw new RuntimeException("error");
    }
}
