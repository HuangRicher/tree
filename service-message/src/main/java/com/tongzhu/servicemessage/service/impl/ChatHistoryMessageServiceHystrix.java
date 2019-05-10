package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.service.ChatHistoryMessageService;
import org.springframework.stereotype.Component;

@Component
public class ChatHistoryMessageServiceHystrix implements ChatHistoryMessageService {

    @Override
    public void addHistoryMessage(String id, String userId, String friendUserId, String message) {

    }

    @Override
    public int countUnReadMessage(String userId) {
        return 0;
    }

    @Override
    public void readMessage(String id) {

    }

}
