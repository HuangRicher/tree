package com.tongzhu.user.mapper;

import com.tongzhu.user.model.UserSkill;
import com.tongzhu.user.model.UserSkillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSkillMapper {
    int countByExample(UserSkillExample example);

    int deleteByExample(UserSkillExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserSkill record);

    int insertSelective(UserSkill record);

    List<UserSkill> selectByExample(UserSkillExample example);

    UserSkill selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserSkill record, @Param("example") UserSkillExample example);

    int updateByExample(@Param("record") UserSkill record, @Param("example") UserSkillExample example);

    int updateByPrimaryKeySelective(UserSkill record);

    int updateByPrimaryKey(UserSkill record);
}