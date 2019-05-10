package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.TaskActivityRewards;
import com.tongzhu.friend.model.TaskActivityRewardsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskActivityRewardsMapper {
    int countByExample(TaskActivityRewardsExample example);

    int deleteByExample(TaskActivityRewardsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskActivityRewards record);

    int insertSelective(TaskActivityRewards record);

    List<TaskActivityRewards> selectByExample(TaskActivityRewardsExample example);

    TaskActivityRewards selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskActivityRewards record, @Param("example") TaskActivityRewardsExample example);

    int updateByExample(@Param("record") TaskActivityRewards record, @Param("example") TaskActivityRewardsExample example);

    int updateByPrimaryKeySelective(TaskActivityRewards record);

    int updateByPrimaryKey(TaskActivityRewards record);
}