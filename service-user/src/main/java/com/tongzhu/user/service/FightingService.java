package com.tongzhu.user.service;

import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;

import java.util.List;

public interface FightingService {

    boolean pvpFightProcess(Combatant peerA, Combatant peerB, List<FightStepDetailVO> fightingStepList);
}
