package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.EquipmentInfo;
import com.tongzhu.usergoods.model.EquipmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentInfoMapper {
    int countByExample(EquipmentInfoExample example);

    int deleteByExample(EquipmentInfoExample example);

    int deleteByPrimaryKey(String intensifyId);

    int insert(EquipmentInfo record);

    int insertSelective(EquipmentInfo record);

    List<EquipmentInfo> selectByExample(EquipmentInfoExample example);

    EquipmentInfo selectByPrimaryKey(String intensifyId);

    int updateByExampleSelective(@Param("record") EquipmentInfo record, @Param("example") EquipmentInfoExample example);

    int updateByExample(@Param("record") EquipmentInfo record, @Param("example") EquipmentInfoExample example);

    int updateByPrimaryKeySelective(EquipmentInfo record);

    int updateByPrimaryKey(EquipmentInfo record);
}