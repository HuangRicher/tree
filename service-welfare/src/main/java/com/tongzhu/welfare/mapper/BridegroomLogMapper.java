package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BridegroomLog;
import com.tongzhu.welfare.model.BridegroomLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BridegroomLogMapper {
    int countByExample(BridegroomLogExample example);

    int deleteByExample(BridegroomLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BridegroomLog record);

    int insertSelective(BridegroomLog record);

    List<BridegroomLog> selectByExample(BridegroomLogExample example);

    BridegroomLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BridegroomLog record, @Param("example") BridegroomLogExample example);

    int updateByExample(@Param("record") BridegroomLog record, @Param("example") BridegroomLogExample example);

    int updateByPrimaryKeySelective(BridegroomLog record);

    int updateByPrimaryKey(BridegroomLog record);
}