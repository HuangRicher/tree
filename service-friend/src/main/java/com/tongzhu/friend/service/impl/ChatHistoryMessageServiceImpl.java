package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.mapper.ChatHistoryMessageMapper;
import com.tongzhu.friend.model.ChatHistoryMessage;
import com.tongzhu.friend.model.ChatHistoryMessageExample;
import com.tongzhu.friend.service.ChatHistoryMessageService;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatHistoryMessageServiceImpl implements ChatHistoryMessageService {

    @Autowired
    private ChatHistoryMessageMapper chatHistoryMessageMapper;


    @Override
    public void add(ChatHistoryMessage message) {
        message.setCreateDate(new Date());
        chatHistoryMessageMapper.insertSelective(message);
    }

    @Override
    public List<ChatHistoryMessage> findTop50ByUserId(String userId,String friendUserId) {
        ChatHistoryMessageExample example=new ChatHistoryMessageExample();
        example.createCriteria().andSenderEqualTo(friendUserId).andReceiverEqualTo(userId).andStatusEqualTo(0);
        example.setOrderByClause(" create_date asc limit 50 ");
        return chatHistoryMessageMapper.selectByExample(example);
    }

    @Override
    public int countUnReadMessage(String userId) {
        ChatHistoryMessageExample example=new ChatHistoryMessageExample();
        example.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(0);
        return chatHistoryMessageMapper.countByExample(example);
    }

    @Override
    public int countUnReadMessage(String userId, String friendUserId) {
        ChatHistoryMessageExample example=new ChatHistoryMessageExample();
        example.createCriteria().andReceiverEqualTo(userId).andSenderEqualTo(friendUserId).andStatusEqualTo(0);
        return chatHistoryMessageMapper.countByExample(example);
    }

    @Override
    public void readMessage(String id) {
        ChatHistoryMessage message=chatHistoryMessageMapper.selectByPrimaryKey(id);
        if(message!=null){
            ChatHistoryMessageExample example=new ChatHistoryMessageExample();
            example.createCriteria().andCreateDateLessThanOrEqualTo(message.getCreateDate());
            ChatHistoryMessage m1=new ChatHistoryMessage();
            m1.setStatus(1);
            chatHistoryMessageMapper.updateByExampleSelective(m1,example);
        }
    }

}
