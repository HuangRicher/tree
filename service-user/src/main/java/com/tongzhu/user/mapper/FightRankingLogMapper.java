package com.tongzhu.user.mapper;

import com.tongzhu.user.model.FightRankingLog;
import com.tongzhu.user.model.FightRankingLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FightRankingLogMapper {
    int countByExample(FightRankingLogExample example);

    int deleteByExample(FightRankingLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(FightRankingLog record);

    int insertSelective(FightRankingLog record);

    List<FightRankingLog> selectByExample(FightRankingLogExample example);

    FightRankingLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FightRankingLog record, @Param("example") FightRankingLogExample example);

    int updateByExample(@Param("record") FightRankingLog record, @Param("example") FightRankingLogExample example);

    int updateByPrimaryKeySelective(FightRankingLog record);

    int updateByPrimaryKey(FightRankingLog record);
}