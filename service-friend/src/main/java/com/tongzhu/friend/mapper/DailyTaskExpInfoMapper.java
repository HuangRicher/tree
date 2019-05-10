package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.DailyTaskExpInfo;
import com.tongzhu.friend.model.DailyTaskExpInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyTaskExpInfoMapper {
    int countByExample(DailyTaskExpInfoExample example);

    int deleteByExample(DailyTaskExpInfoExample example);

    int deleteByPrimaryKey(Integer level);

    int insert(DailyTaskExpInfo record);

    int insertSelective(DailyTaskExpInfo record);

    List<DailyTaskExpInfo> selectByExample(DailyTaskExpInfoExample example);

    DailyTaskExpInfo selectByPrimaryKey(Integer level);

    int updateByExampleSelective(@Param("record") DailyTaskExpInfo record, @Param("example") DailyTaskExpInfoExample example);

    int updateByExample(@Param("record") DailyTaskExpInfo record, @Param("example") DailyTaskExpInfoExample example);

    int updateByPrimaryKeySelective(DailyTaskExpInfo record);

    int updateByPrimaryKey(DailyTaskExpInfo record);
}