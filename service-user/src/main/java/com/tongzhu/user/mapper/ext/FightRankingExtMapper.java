package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.mapper.vo.FightRankingDO;
import com.tongzhu.user.model.FightRanking;

import java.util.List;

public interface FightRankingExtMapper {

    List<FightRankingDO> queryOrder50();

    void sendDayAward();

    void sendSeasonAward();
}
