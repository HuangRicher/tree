package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.WorkIncomeSetting;
import com.tongzhu.treehouse.model.WorkIncomeSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkIncomeSettingMapper {
    int countByExample(WorkIncomeSettingExample example);

    int deleteByExample(WorkIncomeSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkIncomeSetting record);

    int insertSelective(WorkIncomeSetting record);

    List<WorkIncomeSetting> selectByExample(WorkIncomeSettingExample example);

    WorkIncomeSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkIncomeSetting record, @Param("example") WorkIncomeSettingExample example);

    int updateByExample(@Param("record") WorkIncomeSetting record, @Param("example") WorkIncomeSettingExample example);

    int updateByPrimaryKeySelective(WorkIncomeSetting record);

    int updateByPrimaryKey(WorkIncomeSetting record);
}