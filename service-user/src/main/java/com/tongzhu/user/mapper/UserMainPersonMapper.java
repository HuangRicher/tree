package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserMainPerson;
import com.tongzhu.user.model.UserMainPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMainPersonMapper {
    int countByExample(UserMainPersonExample example);

    int deleteByExample(UserMainPersonExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserMainPerson record);

    int insertSelective(UserMainPerson record);

    List<UserMainPerson> selectByExample(UserMainPersonExample example);

    UserMainPerson selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserMainPerson record, @Param("example") UserMainPersonExample example);

    int updateByExample(@Param("record") UserMainPerson record, @Param("example") UserMainPersonExample example);

    int updateByPrimaryKeySelective(UserMainPerson record);

    int updateByPrimaryKey(UserMainPerson record);
}