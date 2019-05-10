package com.tongzhu.user.service;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.domain.FightParameter;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.user.model.UserRole;
import com.tongzhu.user.model.UserSkill;

import java.util.List;
import java.util.Map;

public interface FightCopyCommonService {

    boolean checkIsFirstAttack(List<UserSkillVO> userSkills, List<SkillSetting> list);

    void removeMap(Map<String, Map<String, Float>> map, String removeKey);

    int getUserAggressivity(Combatant peer, ArsenalInfoVO arsenalInfoVO);

    int getUseCrrit(Combatant peer, ArsenalInfoVO arsenalInfoVO);

    int getUseDefenceCrit(Combatant peer, ArsenalInfoVO arsenalInfoVO);

    int getUserHitRate(Combatant peer, ArsenalInfoVO arsenalInfoVO);

    int getUserDodge(Combatant peer, ArsenalInfoVO arsenalInfoVO);

    int getUserDefence(Combatant peerA, Combatant peerB, ArsenalInfoVO arsenalInfoVO);

    void subCountOne(Map<String, Map<String, Float>> dataMap);

    Integer useInitiativeSkill(Combatant peerA, Combatant peerB, Integer skillId, JSONObject object, Integer attackCount,
                               Integer defenceCount, Boolean isCounterattack, Boolean isFireBuff,
                               Integer effectSelfCount, List<String> weaponIdList);

    int computeHurtCount(Combatant peerA, Integer attackCount, Integer defenceCount);

    void freezeSkill(Combatant peer, Integer skillId);

    void reduceFreezeCount(List<UserSkillVO> skillVOList);

    List<UserSkill> findInitiativeSkillByUserIdAndType(Combatant combatant);

    boolean checkIsCleanDebuff(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData);

    int solveDebuffResult(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, Boolean isCleanDeBuff, FightParameter fightParameter);

    int useBigAndSkill(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, FightParameter fightParameter,List<Combatant> groupB,Map<String,Integer> awardMap,List<Map<String,Object>> defendGroup);

    int useWeapon(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, FightParameter fightParameter,List<Combatant> groupB);

    int computeResult(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, FightParameter fightParameter);

    List<Combatant> getCombatant(List<Combatant> fightGroup);

    void getCombatant(List<Map<String,Object>> fightGroup,Combatant combatant);

    List<Map<String,Object>> getUserDetail(Integer group, String userId, List<String> ids);

    int getUserFullBlood(Integer hp, String userId, UserRole userRole);

    List<Map<String,Object>> getUserDetail(Integer group, String userId, List<String> ids, List<MonsterDO> monsterList, Integer exp);

}
