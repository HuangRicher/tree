package com.tongzhu.friend.service;

import com.tongzhu.friend.model.ChatHistoryMessage;

import java.util.List;

public interface ChatHistoryMessageService {

    void add(ChatHistoryMessage message);

    List<ChatHistoryMessage> findTop50ByUserId(String userId,String friendUserId);

    int countUnReadMessage(String userId);

    int countUnReadMessage(String userId,String friendUserId);

    void readMessage(String id);
}
