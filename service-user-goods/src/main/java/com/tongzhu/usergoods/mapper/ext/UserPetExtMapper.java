package com.tongzhu.usergoods.mapper.ext;

import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.model.UserPet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPetExtMapper {
    List<PetVO> getUsetPetByUserIdAndPetId(@Param("userId") String userId, @Param("intensifyId") String intensifyId);

    PetVO getPetVOByUserIdAndFollow(@Param("userId") String userId, @Param("follow") Integer follow);

    List<UserPet> getUserPetByUserIdAnId(@Param("id") int id, @Param("userId") String userId);

    Integer getPetLevelPet(@Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel);
}