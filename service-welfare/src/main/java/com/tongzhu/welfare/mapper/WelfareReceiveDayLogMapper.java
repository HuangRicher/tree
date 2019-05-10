package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WelfareReceiveDayLog;
import com.tongzhu.welfare.model.WelfareReceiveDayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WelfareReceiveDayLogMapper {
    int countByExample(WelfareReceiveDayLogExample example);

    int deleteByExample(WelfareReceiveDayLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WelfareReceiveDayLog record);

    int insertSelective(WelfareReceiveDayLog record);

    List<WelfareReceiveDayLog> selectByExample(WelfareReceiveDayLogExample example);

    WelfareReceiveDayLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WelfareReceiveDayLog record, @Param("example") WelfareReceiveDayLogExample example);

    int updateByExample(@Param("record") WelfareReceiveDayLog record, @Param("example") WelfareReceiveDayLogExample example);

    int updateByPrimaryKeySelective(WelfareReceiveDayLog record);

    int updateByPrimaryKey(WelfareReceiveDayLog record);
}