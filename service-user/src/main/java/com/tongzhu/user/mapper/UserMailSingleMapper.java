package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.model.UserMailSingleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMailSingleMapper {
    int countByExample(UserMailSingleExample example);

    int deleteByExample(UserMailSingleExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserMailSingle record);

    int insertSelective(UserMailSingle record);

    List<UserMailSingle> selectByExample(UserMailSingleExample example);

    UserMailSingle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserMailSingle record, @Param("example") UserMailSingleExample example);

    int updateByExample(@Param("record") UserMailSingle record, @Param("example") UserMailSingleExample example);

    int updateByPrimaryKeySelective(UserMailSingle record);

    int updateByPrimaryKey(UserMailSingle record);
}