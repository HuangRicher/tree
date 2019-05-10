package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.UserMessage;
import com.tongzhu.treehouse.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceHystrix implements MessageService {
    @Override
    public int addUserMessage(UserMessage userMessage) {
        throw new RuntimeException("error");
    }

    @Override
    public void inviteFriendToTreeHouse(String userId, String receiverIds) {

    }
}
