package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.ChatHistoryMessage;
import com.tongzhu.friend.model.ChatHistoryMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatHistoryMessageMapper {
    int countByExample(ChatHistoryMessageExample example);

    int deleteByExample(ChatHistoryMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChatHistoryMessage record);

    int insertSelective(ChatHistoryMessage record);

    List<ChatHistoryMessage> selectByExample(ChatHistoryMessageExample example);

    ChatHistoryMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChatHistoryMessage record, @Param("example") ChatHistoryMessageExample example);

    int updateByExample(@Param("record") ChatHistoryMessage record, @Param("example") ChatHistoryMessageExample example);

    int updateByPrimaryKeySelective(ChatHistoryMessage record);

    int updateByPrimaryKey(ChatHistoryMessage record);
}