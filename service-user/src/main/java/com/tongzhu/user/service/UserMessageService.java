package com.tongzhu.user.service;


import com.tongzhu.common.Pager;
import com.tongzhu.user.controller.vo.UserMessageVO;
import com.tongzhu.user.model.UserMessage;

import java.util.List;

public interface UserMessageService {

    int add(UserMessage message);

    int delete(String id);

    /**
     * 分页查找收到的消息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pager<UserMessage> findReceivedMessageByPage(String userId, int pageNum, int pageSize);

    List<UserMessageVO> findMessageByType(String userId, Integer type);

    int updateMessageRead(String userId,Integer type);

    int userMessageRow(String userId);

    int userMessageRowAndType(String userId, int type);
}
