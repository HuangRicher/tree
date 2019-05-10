package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.UserWorkPosition;
import com.tongzhu.treehouse.model.UserWorkPositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserWorkPositionMapper {
    int countByExample(UserWorkPositionExample example);

    int deleteByExample(UserWorkPositionExample example);

    int deleteByPrimaryKey(String workPositionId);

    int insert(UserWorkPosition record);

    int insertSelective(UserWorkPosition record);

    List<UserWorkPosition> selectByExample(UserWorkPositionExample example);

    UserWorkPosition selectByPrimaryKey(String workPositionId);

    int updateByExampleSelective(@Param("record") UserWorkPosition record, @Param("example") UserWorkPositionExample example);

    int updateByExample(@Param("record") UserWorkPosition record, @Param("example") UserWorkPositionExample example);

    int updateByPrimaryKeySelective(UserWorkPosition record);

    int updateByPrimaryKey(UserWorkPosition record);
}