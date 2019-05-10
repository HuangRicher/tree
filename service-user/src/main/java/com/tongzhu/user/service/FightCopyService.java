package com.tongzhu.user.service;

import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.model.FightCopy;

import java.util.List;
import java.util.Map;

public interface FightCopyService {
    List<FightCopy> findAll();
    FightCopy getById(Integer id);
    List<FightCopy> findByType(Integer type);
    void pveFightProcess(List<Map<String,Object>> attackGroup,List<Map<String,Object>> defendGroup,Map<String,Integer> awardMap,
                         List<Combatant> groupA, List<Combatant> groupB, List<FightStepDetailVO> fightingStepList);
}
