package com.tongzhu.user.mapper;

import com.tongzhu.user.model.RankingExpSetting;
import com.tongzhu.user.model.RankingExpSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RankingExpSettingMapper {
    int countByExample(RankingExpSettingExample example);

    int deleteByExample(RankingExpSettingExample example);

    int deleteByPrimaryKey(Integer roleLevel);

    int insert(RankingExpSetting record);

    int insertSelective(RankingExpSetting record);

    List<RankingExpSetting> selectByExample(RankingExpSettingExample example);

    RankingExpSetting selectByPrimaryKey(Integer roleLevel);

    int updateByExampleSelective(@Param("record") RankingExpSetting record, @Param("example") RankingExpSettingExample example);

    int updateByExample(@Param("record") RankingExpSetting record, @Param("example") RankingExpSettingExample example);

    int updateByPrimaryKeySelective(RankingExpSetting record);

    int updateByPrimaryKey(RankingExpSetting record);
}