package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.FightRankingLogDO;
import com.tongzhu.user.model.FightRankingLog;

import java.util.List;

public interface FightRankingLogService {

    List<FightRankingLog> findByUserId(String userId);

    List<FightRankingLogDO> findDOByUserId(String userId);

    void add(String userId, String participantId,Integer fightResult,Integer changeScore);
}
