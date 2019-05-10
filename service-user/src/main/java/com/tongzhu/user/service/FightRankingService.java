package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.FightRankingDO;
import com.tongzhu.user.model.FightRanking;
import com.tongzhu.user.model.User;
import com.tongzhu.user.service.vo.FightRankingDetailVO;

import java.util.List;

public interface FightRankingService {

    void add(String userId);

    List<FightRankingDO> queryOrder50();

    FightRanking getByUserId(String userId);

    User matchFighter(String userId);

    FightRankingDetailVO fightRankingDetail(String userId);

    void sendAwardMail(int type);

    void updateRanking(String userId, int rankCount);
}
