package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.FlowerSeedsDrawSetting;
import com.tongzhu.treehouse.model.FlowerSeedsDrawSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlowerSeedsDrawSettingMapper {
    int countByExample(FlowerSeedsDrawSettingExample example);

    int deleteByExample(FlowerSeedsDrawSettingExample example);

    int deleteByPrimaryKey(String goodsId);

    int insert(FlowerSeedsDrawSetting record);

    int insertSelective(FlowerSeedsDrawSetting record);

    List<FlowerSeedsDrawSetting> selectByExample(FlowerSeedsDrawSettingExample example);

    FlowerSeedsDrawSetting selectByPrimaryKey(String goodsId);

    int updateByExampleSelective(@Param("record") FlowerSeedsDrawSetting record, @Param("example") FlowerSeedsDrawSettingExample example);

    int updateByExample(@Param("record") FlowerSeedsDrawSetting record, @Param("example") FlowerSeedsDrawSettingExample example);

    int updateByPrimaryKeySelective(FlowerSeedsDrawSetting record);

    int updateByPrimaryKey(FlowerSeedsDrawSetting record);
}