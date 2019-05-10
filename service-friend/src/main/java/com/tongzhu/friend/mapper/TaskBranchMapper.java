package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.TaskBranch;
import com.tongzhu.friend.model.TaskBranchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskBranchMapper {
    int countByExample(TaskBranchExample example);

    int deleteByExample(TaskBranchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskBranch record);

    int insertSelective(TaskBranch record);

    List<TaskBranch> selectByExample(TaskBranchExample example);

    TaskBranch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskBranch record, @Param("example") TaskBranchExample example);

    int updateByExample(@Param("record") TaskBranch record, @Param("example") TaskBranchExample example);

    int updateByPrimaryKeySelective(TaskBranch record);

    int updateByPrimaryKey(TaskBranch record);
}