package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.MallConsumerLog;
import com.tongzhu.welfare.model.MallConsumerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallConsumerLogMapper {
    int countByExample(MallConsumerLogExample example);

    int deleteByExample(MallConsumerLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallConsumerLog record);

    int insertSelective(MallConsumerLog record);

    List<MallConsumerLog> selectByExample(MallConsumerLogExample example);

    MallConsumerLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallConsumerLog record, @Param("example") MallConsumerLogExample example);

    int updateByExample(@Param("record") MallConsumerLog record, @Param("example") MallConsumerLogExample example);

    int updateByPrimaryKeySelective(MallConsumerLog record);

    int updateByPrimaryKey(MallConsumerLog record);
}