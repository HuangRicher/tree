package com.tongzhu.user.mapper;

import com.tongzhu.user.model.CopyExpSetting;
import com.tongzhu.user.model.CopyExpSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CopyExpSettingMapper {
    int countByExample(CopyExpSettingExample example);

    int deleteByExample(CopyExpSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(CopyExpSetting record);

    int insertSelective(CopyExpSetting record);

    List<CopyExpSetting> selectByExample(CopyExpSettingExample example);

    CopyExpSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CopyExpSetting record, @Param("example") CopyExpSettingExample example);

    int updateByExample(@Param("record") CopyExpSetting record, @Param("example") CopyExpSettingExample example);

    int updateByPrimaryKeySelective(CopyExpSetting record);

    int updateByPrimaryKey(CopyExpSetting record);
}