package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WelfareReceiveMonthLog;
import com.tongzhu.welfare.model.WelfareReceiveMonthLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WelfareReceiveMonthLogMapper {
    int countByExample(WelfareReceiveMonthLogExample example);

    int deleteByExample(WelfareReceiveMonthLogExample example);

    int insert(WelfareReceiveMonthLog record);

    int insertSelective(WelfareReceiveMonthLog record);

    List<WelfareReceiveMonthLog> selectByExample(WelfareReceiveMonthLogExample example);

    int updateByExampleSelective(@Param("record") WelfareReceiveMonthLog record, @Param("example") WelfareReceiveMonthLogExample example);

    int updateByExample(@Param("record") WelfareReceiveMonthLog record, @Param("example") WelfareReceiveMonthLogExample example);
}