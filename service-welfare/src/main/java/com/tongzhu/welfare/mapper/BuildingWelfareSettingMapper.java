package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingWelfareSetting;
import com.tongzhu.welfare.model.BuildingWelfareSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingWelfareSettingMapper {
    int countByExample(BuildingWelfareSettingExample example);

    int deleteByExample(BuildingWelfareSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingWelfareSetting record);

    int insertSelective(BuildingWelfareSetting record);

    List<BuildingWelfareSetting> selectByExample(BuildingWelfareSettingExample example);

    BuildingWelfareSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingWelfareSetting record, @Param("example") BuildingWelfareSettingExample example);

    int updateByExample(@Param("record") BuildingWelfareSetting record, @Param("example") BuildingWelfareSettingExample example);

    int updateByPrimaryKeySelective(BuildingWelfareSetting record);

    int updateByPrimaryKey(BuildingWelfareSetting record);
}