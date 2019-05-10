package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.SendFlowers;
import com.tongzhu.welfare.model.SendFlowersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendFlowersMapper {
    int countByExample(SendFlowersExample example);

    int deleteByExample(SendFlowersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendFlowers record);

    int insertSelective(SendFlowers record);

    List<SendFlowers> selectByExample(SendFlowersExample example);

    SendFlowers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendFlowers record, @Param("example") SendFlowersExample example);

    int updateByExample(@Param("record") SendFlowers record, @Param("example") SendFlowersExample example);

    int updateByPrimaryKeySelective(SendFlowers record);

    int updateByPrimaryKey(SendFlowers record);
}