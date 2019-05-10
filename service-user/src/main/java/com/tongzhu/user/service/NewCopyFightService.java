package com.tongzhu.user.service;

import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.model.SkillSetting;

import java.util.List;
import java.util.Map;

public interface NewCopyFightService {

    void pveFightProcess(List<Map<String, Object>> attackGroup, List<Map<String, Object>> defendGroup, Map<String, Integer> awardMap, List<Combatant> selfGroup, List<Combatant> opponentGroup, List<FightStepDetailVO> fightingStepList) throws Exception;

    boolean checkIsFirstAttack(List<UserSkillVO> skillList, List<SkillSetting> skillSettingList);

    List<Map<String,Object>> getUserDetail(Integer group, String userId, List<String> ids, Integer copyId, Integer exp);
}
