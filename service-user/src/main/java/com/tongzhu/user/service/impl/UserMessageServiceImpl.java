package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.constant.MessageConstant;
import com.tongzhu.user.constant.UserMessageConstant;
import com.tongzhu.user.controller.vo.UserMessageVO;
import com.tongzhu.user.mapper.UserMessageMapper;
import com.tongzhu.user.mapper.ext.UserMessageExtMapper;
import com.tongzhu.user.model.UserMessage;
import com.tongzhu.user.model.UserMessageExample;
import com.tongzhu.user.service.ChatMessageService;
import com.tongzhu.user.service.UserMessageService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private UserMessageExtMapper userMessageExtMapper;

    @Autowired
    private ChatMessageService chatMessageService;


    @Override
    public int add(UserMessage message) {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andReceiverIdEqualTo(message.getReceiverId());
        example.setOrderByClause(" create_date ASC ");
        List<UserMessage> messages = userMessageMapper.selectByExample(example);
        if (messages != null && messages.size() >= UserMessageConstant.MAX_LEAVE_MESSAGE_COUNT) {
            userMessageMapper.deleteByPrimaryKey(messages.get(0).getId());
        }
        message.setCreateDate(new Date());
        message.setStatus(1);
        message.setRead(0);
        if (message.getType() == MessageConstant.TYPE_MARRY_JOIN) {
            Map<String, Object> object = ObjectUtil.getRedTipMap("card");
            chatMessageService.sendMessageToSomeBody(message.getReceiverId(), JSON.toJSONString(object));
        } else if (message.getType() == MessageConstant.TYPE_TREE_HOUSE_INVITE) {
            Map<String, Object> object = ObjectUtil.getRedTipMap("invite");
            chatMessageService.sendMessageToSomeBody(message.getReceiverId(), JSON.toJSONString(object));
        } else {
            Map<String, Object> object = ObjectUtil.getRedTipMap("mail");
            chatMessageService.sendMessageToSomeBody(message.getReceiverId(), JSON.toJSONString(object));
        }
        return userMessageMapper.insertSelective(message);
    }

    @Override
    public int delete(String id) {
        return userMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Pager<UserMessage> findReceivedMessageByPage(String userId, int pageNum, int pageSize) {
        List<Integer> list = Stream.of(MessageConstant.TYPE_MARRY_JOIN, MessageConstant.TYPE_TREE_HOUSE_INVITE).collect(toList());
        Page<UserMessage> page = PageHelper.startPage(pageNum, pageSize);
        userMessageExtMapper.findReceivedMessageByPage(userId, UserMessageConstant.STATUS_NORMAL, list);
        return new Pager(pageNum, pageSize, page.getPages(), page.getTotal(), page.getResult());
    }

    @Override
    public List<UserMessageVO> findMessageByType(String userId, Integer type) {
        return userMessageExtMapper.findReceivedMessageByType(userId, type);
    }


    @Override
    public int updateMessageRead(String userId, Integer type) {
        UserMessage userMessage = new UserMessage();
        userMessage.setReceiverId(userId);
        userMessage.setRead(1);
        UserMessageExample userMessageExample = new UserMessageExample();
        if (type == null) {
            userMessageExample.createCriteria().andReceiverIdEqualTo(userId);
        } else {
            userMessageExample.createCriteria().andReceiverIdEqualTo(userId).andTypeEqualTo(type);
        }
        int i = userMessageMapper.updateByExampleSelective(userMessage, userMessageExample);
        return i;
    }

    @Override
    public int userMessageRow(String userId) {
        UserMessageExample userMessageExample = new UserMessageExample();
        userMessageExample.createCriteria().andReceiverIdEqualTo(userId).andReadEqualTo(0).andTypeNotEqualTo(MessageConstant.TYPE_MARRY_JOIN).andTypeNotEqualTo(MessageConstant.TYPE_TREE_HOUSE_INVITE);
        int i = userMessageMapper.countByExample(userMessageExample);
        return i;
    }


    @Override
    public int userMessageRowAndType(String userId, int type) {
        UserMessageExample userMessageExample = new UserMessageExample();
        userMessageExample.createCriteria().andReceiverIdEqualTo(userId).andReadEqualTo(0).andTypeEqualTo(type).andCreateDateGreaterThanOrEqualTo(DateUtil.getLastDate(new Date())).andSurplusDateGreaterThanOrEqualTo(new Date());
        int i = userMessageMapper.countByExample(userMessageExample);
        return i;
    }
}
