package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserMailAll;
import com.tongzhu.user.model.UserMailAllExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMailAllMapper {
    int countByExample(UserMailAllExample example);

    int deleteByExample(UserMailAllExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserMailAll record);

    int insertSelective(UserMailAll record);

    List<UserMailAll> selectByExample(UserMailAllExample example);

    UserMailAll selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserMailAll record, @Param("example") UserMailAllExample example);

    int updateByExample(@Param("record") UserMailAll record, @Param("example") UserMailAllExample example);

    int updateByPrimaryKeySelective(UserMailAll record);

    int updateByPrimaryKey(UserMailAll record);
}