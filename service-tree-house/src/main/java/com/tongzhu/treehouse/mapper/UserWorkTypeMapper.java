package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.UserWorkType;
import com.tongzhu.treehouse.model.UserWorkTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserWorkTypeMapper {
    int countByExample(UserWorkTypeExample example);

    int deleteByExample(UserWorkTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserWorkType record);

    int insertSelective(UserWorkType record);

    List<UserWorkType> selectByExample(UserWorkTypeExample example);

    UserWorkType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserWorkType record, @Param("example") UserWorkTypeExample example);

    int updateByExample(@Param("record") UserWorkType record, @Param("example") UserWorkTypeExample example);

    int updateByPrimaryKeySelective(UserWorkType record);

    int updateByPrimaryKey(UserWorkType record);
}