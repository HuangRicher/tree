package com.tongzhu.user.mapper;

import com.tongzhu.user.model.FightFriendExpSetting;
import com.tongzhu.user.model.FightFriendExpSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FightFriendExpSettingMapper {
    int countByExample(FightFriendExpSettingExample example);

    int deleteByExample(FightFriendExpSettingExample example);

    int deleteByPrimaryKey(Integer roleLevel);

    int insert(FightFriendExpSetting record);

    int insertSelective(FightFriendExpSetting record);

    List<FightFriendExpSetting> selectByExample(FightFriendExpSettingExample example);

    FightFriendExpSetting selectByPrimaryKey(Integer roleLevel);

    int updateByExampleSelective(@Param("record") FightFriendExpSetting record, @Param("example") FightFriendExpSettingExample example);

    int updateByExample(@Param("record") FightFriendExpSetting record, @Param("example") FightFriendExpSettingExample example);

    int updateByPrimaryKeySelective(FightFriendExpSetting record);

    int updateByPrimaryKey(FightFriendExpSetting record);
}