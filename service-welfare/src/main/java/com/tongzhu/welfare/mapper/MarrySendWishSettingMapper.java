package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.MarrySendWishSetting;
import com.tongzhu.welfare.model.MarrySendWishSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarrySendWishSettingMapper {
    int countByExample(MarrySendWishSettingExample example);

    int deleteByExample(MarrySendWishSettingExample example);

    int insert(MarrySendWishSetting record);

    int insertSelective(MarrySendWishSetting record);

    List<MarrySendWishSetting> selectByExample(MarrySendWishSettingExample example);

    int updateByExampleSelective(@Param("record") MarrySendWishSetting record, @Param("example") MarrySendWishSettingExample example);

    int updateByExample(@Param("record") MarrySendWishSetting record, @Param("example") MarrySendWishSettingExample example);
}