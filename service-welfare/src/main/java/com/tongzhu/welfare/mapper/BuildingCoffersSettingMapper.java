package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingCoffersSetting;
import com.tongzhu.welfare.model.BuildingCoffersSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingCoffersSettingMapper {
    int countByExample(BuildingCoffersSettingExample example);

    int deleteByExample(BuildingCoffersSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingCoffersSetting record);

    int insertSelective(BuildingCoffersSetting record);

    List<BuildingCoffersSetting> selectByExample(BuildingCoffersSettingExample example);

    BuildingCoffersSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingCoffersSetting record, @Param("example") BuildingCoffersSettingExample example);

    int updateByExample(@Param("record") BuildingCoffersSetting record, @Param("example") BuildingCoffersSettingExample example);

    int updateByPrimaryKeySelective(BuildingCoffersSetting record);

    int updateByPrimaryKey(BuildingCoffersSetting record);
}