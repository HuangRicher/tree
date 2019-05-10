package com.tongzhu.user.mapper;

import com.tongzhu.user.model.FightRankingSetting;
import com.tongzhu.user.model.FightRankingSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FightRankingSettingMapper {
    int countByExample(FightRankingSettingExample example);

    int deleteByExample(FightRankingSettingExample example);

    int insert(FightRankingSetting record);

    int insertSelective(FightRankingSetting record);

    List<FightRankingSetting> selectByExample(FightRankingSettingExample example);

    int updateByExampleSelective(@Param("record") FightRankingSetting record, @Param("example") FightRankingSettingExample example);

    int updateByExample(@Param("record") FightRankingSetting record, @Param("example") FightRankingSettingExample example);
}