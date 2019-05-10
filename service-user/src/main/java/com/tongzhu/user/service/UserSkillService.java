package com.tongzhu.user.service;

import com.tongzhu.user.model.Skill;
import com.tongzhu.user.model.UserSkill;

import java.util.List;
import java.util.Map;

public interface UserSkillService {

    /**
     * 查找用户拥有的技能
     * @param userId
     * @return
     */
    List<UserSkill> findByUserId(String userId);

    void add(UserSkill userSkill);

    /**
     * 升级技能(一级）
     * @param id
     *
     */
    Map<String,Object> upgradeSkillLevel(String id);

    /**
     * 升级技能(多级）
     * @param userId
     * @param skillId
     */
    Map<String,Integer> upgradeSkillSomeLevel(String userId, Integer skillId);


    UserSkill findByUserIdAndSkillId(String userId,Integer skillId);

    /**
     * 查找用户拥有的xx类型的技能
     * @param userId
     * @param skillType
     * @return
     */
    List<UserSkill> findByUserIdAndType(String userId, Integer skillType);

    /**
     * 查找用户拥有的某些技能
     * @param userId
     * @param skillIds
     * @return
     */
    List<UserSkill> findByUserIdAndSkillIdList(String userId, List<Integer> skillIds);

    /**
     * 一键升级所有的xx类型的技能
     * @param userId
     * @param skillType
     */
    Map<String,Integer> upgradeAllSkillByType(String userId, Integer skillType);

    /**
     * 查找用户拥有的能获得xxBuff的技能
     * @param userId
     * @param buffId
     * @return
     */
    List<UserSkill> findByUserIdAndBuffIdLike(String userId,String buffId);

    /**
     * 查找玩家未获得的技能
     * @param userId
     * @return
     */
    List<Skill> findUserNoneSkill(String userId);

    UserSkill getById(String id);

    /**
     * 判断玩家的技能是否可升级
     * @param userId
     * @return
     */
    boolean checkSkillCanUpgrade(String userId);

    boolean checkSkillCanUpgrade(String userId,Integer skillType);

    void deleteByUserId(String s);
}
