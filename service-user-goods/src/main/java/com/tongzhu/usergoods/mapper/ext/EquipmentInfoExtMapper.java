package com.tongzhu.usergoods.mapper.ext;

import com.tongzhu.usergoods.mapper.vo.EquipmentInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentInfoExtMapper {

    List<EquipmentInfoVO> queryEquipmentInfoList(@Param("userId") String userId,@Param("settingPosition") Integer settingPosition);

    List<EquipmentInfoVO> queryWarehouseEquipmentInfoList(@Param("userId") String userId);
}