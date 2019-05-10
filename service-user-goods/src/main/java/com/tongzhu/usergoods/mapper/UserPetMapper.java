package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UserPet;
import com.tongzhu.usergoods.model.UserPetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPetMapper {
    int countByExample(UserPetExample example);

    int deleteByExample(UserPetExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserPet record);

    int insertSelective(UserPet record);

    List<UserPet> selectByExample(UserPetExample example);

    UserPet selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserPet record, @Param("example") UserPetExample example);

    int updateByExample(@Param("record") UserPet record, @Param("example") UserPetExample example);

    int updateByPrimaryKeySelective(UserPet record);

    int updateByPrimaryKey(UserPet record);
}