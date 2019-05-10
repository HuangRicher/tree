package com.tongzhu.user.mapper;

import com.tongzhu.user.model.FightRanking;
import com.tongzhu.user.model.FightRankingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FightRankingMapper {
    int countByExample(FightRankingExample example);

    int deleteByExample(FightRankingExample example);

    int deleteByPrimaryKey(String userId);

    int insert(FightRanking record);

    int insertSelective(FightRanking record);

    List<FightRanking> selectByExample(FightRankingExample example);

    FightRanking selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") FightRanking record, @Param("example") FightRankingExample example);

    int updateByExample(@Param("record") FightRanking record, @Param("example") FightRankingExample example);

    int updateByPrimaryKeySelective(FightRanking record);

    int updateByPrimaryKey(FightRanking record);
}