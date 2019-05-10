package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.Skill;
import com.tongzhu.user.model.UserSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSkillExtMapper {

    List<UserSkill> selectByUserIdSkillType(@Param("userId")String userId,@Param("type") Integer type);

    List<UserSkill> selectByUserIdBuffLike(@Param("userId")String userId,@Param("buff")String buff);

    //玩家未获得的角色
    List<Skill> selectUserNoneSkill(@Param("userId")String userId);

}
