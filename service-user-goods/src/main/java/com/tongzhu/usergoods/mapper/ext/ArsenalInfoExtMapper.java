package com.tongzhu.usergoods.mapper.ext;

import com.tongzhu.usergoods.mapper.vo.ArsenalInfoVO;
import com.tongzhu.usergoods.model.ArsenalInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArsenalInfoExtMapper {

    List<ArsenalInfoVO> queryWeaponList(@Param("userId") String userId, @Param("settingPosition") Integer settingPosition);

    List<ArsenalInfo> findUserAllNotGetWeaponList(@Param("userId") String userId, @Param("profession") String profession);
}