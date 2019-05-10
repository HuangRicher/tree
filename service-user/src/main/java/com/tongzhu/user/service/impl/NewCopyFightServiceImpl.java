package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.action.ActionMapUtil;
import com.tongzhu.user.afight.FightCopyUtil;
import com.tongzhu.user.afight.MonsterCache;
import com.tongzhu.user.constant.*;
import com.tongzhu.user.controller.vo.Building;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.model.*;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class NewCopyFightServiceImpl implements NewCopyFightService {
    private Logger logger= LoggerFactory.getLogger(NewCopyFightServiceImpl.class);

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillSettingService skillSettingService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;

    @Autowired
    private UserCopySettingService userCopySettingService;

    @Autowired
    private ArsenalService arsenalService;




    @Override
    public void pveFightProcess(List<Map<String,Object>> attackGroup,List<Map<String,Object>> defendGroup,Map<String,Integer> awardMap,
                                List<Combatant> groupA, List<Combatant> groupB, List<FightStepDetailVO> fightingStepList) throws Exception {
        boolean isContinue=true;
        int fightCase=6; // fightCase  1:peerA胜利   2：peerB胜利  0：战斗继续

        logger.debug("{}--先攻->{}",groupA.get(0).getUserName(),groupB.get(0).getUserName());
        Combatant peerA=groupA.get(0);
        Combatant peerB=groupB.get(0);
        //1:peerA胜利   2：peerB胜利  0：跳过本回合  3：继续下一步
        fightCase=fight(peerA,peerB,fightingStepList,groupA,awardMap,defendGroup);
        if(fightCase==1){//B被kill
            FightCopyUtil.removeCombatant(groupB,0);
            if(!CollectionUtils.isEmpty(groupB)){
                if(peerB.getGroup().equals(1)){
                    FightCopyUtil.getCombatant(attackGroup,groupB.get(0));
                }else{
                    FightCopyUtil.getCombatant(defendGroup,groupB.get(0));
                }
            }
        }
        if(fightCase==2){//A被kill
            FightCopyUtil.removeCombatant(groupA,0);
            if(!CollectionUtils.isEmpty(groupA)){
                if(peerA.getGroup().equals(1)){
                    FightCopyUtil.getCombatant(attackGroup,groupA.get(0));
                }else{
                    FightCopyUtil.getCombatant(defendGroup,groupA.get(0));
                }
            }
        }
        if(fightCase==1 || fightCase==2){
            if(peerA.getGroup().equals(1)){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                fightingStepList.add(stepDetail);
            }else{
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                fightingStepList.add(stepDetail);
            }
        }

        fightMainLoad(attackGroup,defendGroup,awardMap,groupA,groupB,isContinue,fightingStepList,fightCase);

    }

    @Override
    public boolean checkIsFirstAttack(List<UserSkillVO> userSkills, List<SkillSetting> list) {
        boolean isSelfFirst=false;
        for(UserSkillVO skill:userSkills){
            //33技能触发1039 buff: xx%概率开局先进入攻击回合并且额外获得一次攻击机会
            if(skill.getSkillId()== SkillConstant.SKILL_33){
                SkillSetting setting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),list);
                if(setting!=null && StringUtils.isNotBlank(setting.getPro())){
                    JSONObject object= JSON.parseObject(setting.getPro());
                    float rate=object.getBigDecimal(BuffConstant.BUFF_1039).floatValue();
                    if(RandomUtil.probabilityEvent(rate)) {
                        isSelfFirst=true;
                        break;
                    }
                }
            }
        }
        return isSelfFirst;
    }

    private void fightMainLoad(List<Map<String,Object>> attackGroup,List<Map<String,Object>> defendGroup,
                               Map<String,Integer> awardMap,List<Combatant> groupA, List<Combatant> groupB,
                               boolean isContinue, List<FightStepDetailVO> fightingStepList,Integer fightCase) throws Exception {
        //单轮战斗最多25个回合
        int count=0;
        int temp=0;
        boolean notFirst=false;
        boolean isFirst=false;
        while (!groupA.isEmpty() && !groupB.isEmpty() && count<25){
            if(isFirst){
                if(groupA.get(0).getGroup().equals(2)){
                    if(StringUtils.isNotBlank(groupA.get(0).getTalk())){
                        FightStepDetailVO stepDetail2=new FightStepDetailVO();
                        stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_96);
                        stepDetail2.setGroup(2);
                        stepDetail2.setIndex(1);
                        stepDetail2.setContent(groupA.get(0).getTalk());
                        fightingStepList.add(stepDetail2);
                    }
                }
                if(groupB.get(0).getGroup().equals(2)){
                    if(StringUtils.isNotBlank(groupB.get(0).getTalk())){
                        FightStepDetailVO stepDetail2=new FightStepDetailVO();
                        stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_96);
                        stepDetail2.setGroup(2);
                        stepDetail2.setIndex(1);
                        stepDetail2.setContent(groupB.get(0).getTalk());
                        fightingStepList.add(stepDetail2);
                    }
                }
                isFirst=false;
            }

            count++;
            temp=count;
            //groupA 攻击 groupB
            if(count>1 || notFirst){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_98);
                stepDetail.setFightingCount(count);
                fightingStepList.add(stepDetail);
            }
            Combatant peerA1=groupA.get(0);
            Combatant peerB1=groupB.get(0);
            int preCount=groupB.size();
            //1:peerA胜利   2：peerB胜利  0：跳过本回合  3：继续下一步
            logger.debug("回合[{}]:{}-->{}",temp,peerA1.getUserName(),peerB1.getUserName());
            fightCase=fight(peerA1,peerB1,fightingStepList,groupB,awardMap,defendGroup);
            int afterCount=groupB.size();
            if(afterCount< preCount){
                count=0;
                notFirst=true;
            }
            if(fightCase==1){//B被kill
                FightCopyUtil.removeCombatant(groupB,0);
                if(!CollectionUtils.isEmpty(groupB)){
                    if(peerB1.getGroup().equals(1)){
                        FightCopyUtil.getCombatant(attackGroup,groupB.get(0));
                    }else{
                        isFirst=true;
                        FightCopyUtil.getCombatant(defendGroup,groupB.get(0));
                    }
                }
            }
            if(fightCase==2){//A被kill
                FightCopyUtil.removeCombatant(groupA,0);
                if(!CollectionUtils.isEmpty(groupA)){
                    if(peerA1.getGroup().equals(1)){
                        FightCopyUtil.getCombatant(attackGroup,groupA.get(0));
                    }else{
                        isFirst=true;
                        FightCopyUtil.getCombatant(defendGroup,groupA.get(0));
                    }
                }
            }

            if(groupA.isEmpty()) break;
            if(groupB.isEmpty()) break;

            if(fightCase==1 || fightCase==2){
                count=0;
                notFirst=true;
                if(peerA1.getGroup().equals(1)){
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                    stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                    fightingStepList.add(stepDetail);
                }else{
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                    stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                    fightingStepList.add(stepDetail);
                }
            }


            //groupB 攻击 groupA

            Combatant peerA2=groupA.get(0);
            Combatant peerB2=groupB.get(0);
            logger.debug("回合[{}]:{}--->{}",temp,peerB2.getUserName(),peerA2.getUserName());
            //1:peerB胜利   2：peerA胜利  0：跳过本回合  3：继续下一步
            fightCase=fight(peerB2,peerA2,fightingStepList,groupB,awardMap,defendGroup);
            if(fightCase==1){//A被kill
                FightCopyUtil.removeCombatant(groupA,0);
                if(!CollectionUtils.isEmpty(groupA)){
                    if(peerA2.getGroup().equals(1)){
                        FightCopyUtil.getCombatant(attackGroup,groupA.get(0));
                    }else{
                        isFirst=true;
                        FightCopyUtil.getCombatant(defendGroup,groupA.get(0));
                    }
                }
            }
            if(fightCase==2){//B被kill
                FightCopyUtil.removeCombatant(groupB,0);
                if(!CollectionUtils.isEmpty(groupB)){
                    if(peerB2.getGroup().equals(1)){
                        FightCopyUtil.getCombatant(attackGroup,groupB.get(0));
                    }else{
                        isFirst=true;
                        FightCopyUtil.getCombatant(defendGroup,groupB.get(0));
                    }
                }
            }
            if(groupA.isEmpty()) break;
            if(groupB.isEmpty()) break;
            if(fightCase==1 || fightCase==2){
                count=0;
                notFirst=true;
                if(peerA2.getGroup().equals(1)){
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                    stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                    fightingStepList.add(stepDetail);
                }else{
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                    stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                    fightingStepList.add(stepDetail);
                }
            }
        }
    }

    /**
     *
     * @param peerA
     * @param peerB
     * @param resultData
     * @param groupB
     * @param awardMap
     * @param defendGroup
     * @return 1: B 死亡   2：A 死亡   0：跳过本回合,本回合结束  3：继续下一步
     * @throws Exception
     */
    public int fight(Combatant peerA,Combatant peerB,List<FightStepDetailVO> resultData,List<Combatant> groupB,
                     Map<String,Integer> awardMap,List<Map<String,Object>> defendGroup) throws Exception {
        Integer hurtCount=0;
        Integer toxicosisCount=0; //中毒伤害数
        Integer bloodCount=0;     //生命回复值
        Integer hurtBackCount=0;  //反噬伤害数
        FightParameter fightParameter=new FightParameter(hurtCount,toxicosisCount,bloodCount,hurtBackCount);
        //检测debuff
        //检测是否拥有技能 1038,每回合有xx%概率移除自身所有debuff  skillId:32 心如止水
        //               1040,自身有debuff时，xx%概率触发，净化所有debuff，接下来3回合不受debuff影响  skillId: 34 菩提之光
        boolean isCleanDeBuff=false;
        if(peerA.getGroup()==1){
            isCleanDeBuff=(boolean)ActionMapUtil.invoke("checkIsCleanDebuff",peerA,peerB.getCombatantList().get(0),resultData);
        }else if(peerA.getGroup()==2){
            isCleanDeBuff=(boolean)ActionMapUtil.invoke("checkIsCleanDebuff",peerA.getCombatantList().get(0),peerB,resultData);
        }
        if(peerA.getGroup()==1){
            int resolveDebuffResult=(int)ActionMapUtil.invoke("solveDebuffResult",peerA,peerB.getCombatantList().get(0),resultData,isCleanDeBuff,fightParameter);
            if(resolveDebuffResult==2){//peerA中毒死亡
                FightCopyUtil.reduceFreezeCount(peerA.getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getDebuff());
                }
                return 2;
            }
            if(resolveDebuffResult==0){//0：跳过本回合
                FightCopyUtil.reduceFreezeCount(peerA.getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getDebuff());
                }
                return 0;
            }
        }else if(peerA.getGroup()==2){
            int resolveDebuffResult=(int)ActionMapUtil.invoke("solveDebuffResult",peerA.getCombatantList().get(0),peerB,resultData,isCleanDeBuff,fightParameter);
            if(resolveDebuffResult==2){//peerA中毒死亡
                FightCopyUtil.reduceFreezeCount(peerA.getCombatantList().get(0).getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getCombatantList().get(0).getBuff());
                }
                if(peerA.getDebuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getCombatantList().get(0).getDebuff());
                }
                return 2;
            }
            if(resolveDebuffResult==0){//0：跳过本回合
                FightCopyUtil.reduceFreezeCount(peerA.getCombatantList().get(0).getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getCombatantList().get(0).getBuff());
                }
                if(peerA.getDebuff()!=null){
                    FightCopyUtil.subCountOne(peerA.getCombatantList().get(0).getDebuff());
                }
                return 0;
            }
        }

        //使用大招，技能
        int useBigAndSkill= (int)ActionMapUtil.invoke("useBigAndSkill",peerA,peerB,resultData,fightParameter,awardMap);
        if(useBigAndSkill<3){
            return useBigAndSkill;
        }


        if(peerA.getGroup().equals(1)){
            //使用武器攻击
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightStepDetailVO stepDetail2=null;
            int useWeapon= (int)ActionMapUtil.invoke("useWeapon",peerA,peerB.getCombatantList().get(0),stepDetail,fightParameter,groupB);
            if(peerA.getFuryValue()!=null){
                int furyA = peerA.getFuryValue();
                furyA = furyA + RandomUtil.random(8, 12);
                peerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
            }
            if(toxicosisCount>0){
                FightCopyUtil.dealToxicosisCount(peerA,toxicosisCount,stepDetail);
            }
            if(useWeapon==1){//怪死亡

                stepDetail2=new FightStepDetailVO();
                stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                List<Map<String,Object>> list=new ArrayList<>();
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerB.getCombatantList().get(0).getGroup());
                deadPlayer.put("index",peerB.getCombatantList().get(0).getGroupIndex());
                list.add(deadPlayer);
                stepDetail2.setDeadPlayerList(list);
                FightCopyUtil.addAward(awardMap,peerB.getCombatantList().get(0));
                FightCopyUtil.removeCombatant(peerB.getCombatantList(),0);
            }


            if(CollectionUtils.isEmpty(peerB.getCombatantList())){
                //怪全部死亡
                stepDetail.setSelfBlood(peerA.getBlood());
                stepDetail.setSelfFuryValue(peerA.getFuryValue());
                stepDetail.setOpponentFuryValue(0);
                stepDetail.setOpponentBlood(0);
                resultData.add(stepDetail);
                if(stepDetail2!=null) resultData.add(stepDetail2);
                FightCopyUtil.reduceFreezeCount(peerA.getSkillList());
                return 1;
            }else{
                int blood=0;
                for(Combatant cb:peerB.getCombatantList()) {
                    blood += cb.getBlood();
                }
                stepDetail.setSelfBlood(peerA.getBlood());
                stepDetail.setSelfFuryValue(peerA.getFuryValue());
                stepDetail.setOpponentFuryValue(0);
                stepDetail.setOpponentBlood(blood);
                resultData.add(stepDetail);
                if(stepDetail2!=null) resultData.add(stepDetail2);
                FightCopyUtil.reduceFreezeCount(peerA.getSkillList());
                return 0;
            }
        }else{
            int attackCount=FightCopyUtil.getUserAggressivity(peerA.getCombatantList().get(0),peerA.getCombatantList().get(0).getWornWeapon());//攻击力
            int defenceCount=FightCopyUtil.getUserDefence(peerA.getCombatantList().get(0),peerB,peerA.getCombatantList().get(0).getWornWeapon());//防御力
            hurtCount= FightCopyUtil.computeHurtCount(peerA.getCombatantList().get(0),attackCount,defenceCount);
            fightParameter.setHurtCount(hurtCount);

            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightStepDetailVO stepDetail2=null;

            Map<String,Object> map11=new HashMap<>();
            Map<String,Object> map22=new HashMap<>();
            map11.put("group",peerA.getCombatantList().get(0).getGroup());
            map11.put("index",peerA.getCombatantList().get(0).getGroupIndex());
            stepDetail.setAttacker(map11);

            map22.put("group",peerB.getGroup());
            map22.put("index",peerB.getGroupIndex());
            stepDetail.setDefender(map22);
            stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
            stepDetail.setBj(false);
            stepDetail.setSkillId(null);
            stepDetail.setActionName("普通攻击");
            stepDetail.setHurtCount(hurtCount);

            //FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_994  ,false,1,hurtCount,stepDetail);

            if(peerB.getFuryValue()!=null){
                int furyB = peerB.getFuryValue();
                furyB=furyB + RandomUtil.random(12, 16);
                peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
            }
            int blood=0;
            for(Combatant cb:peerA.getCombatantList()) {
                blood += cb.getBlood();
            }
            if(peerB.getBlood()<=hurtCount){//玩家死亡

                stepDetail2=new FightStepDetailVO();
                stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                List<Map<String,Object>> list=new ArrayList<>();
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerB.getGroup());
                deadPlayer.put("index",peerB.getGroupIndex());
                list.add(deadPlayer);
                stepDetail2.setDeadPlayerList(list);

                stepDetail.setSelfFuryValue(peerB.getFuryValue());
                stepDetail.setOpponentFuryValue(0);
                stepDetail.setSelfBlood(0);
                stepDetail.setOpponentBlood(blood);
                stepDetail.setHurtCount(peerB.getBlood());
                if(toxicosisCount>0){
                    FightCopyUtil.dealToxicosisCount(peerA.getCombatantList().get(0),toxicosisCount,stepDetail);
                }
                resultData.add(stepDetail);
                if(stepDetail2!=null) resultData.add(stepDetail2);
                FightCopyUtil.reduceFreezeCount(peerA.getCombatantList().get(0).getSkillList());
                return 1;
            }else{
                peerB.setBlood(peerB.getBlood()-hurtCount);
                stepDetail.setSelfBlood(peerB.getBlood());
                stepDetail.setOpponentBlood(blood);
                stepDetail.setSelfFuryValue(peerB.getFuryValue());
                stepDetail.setOpponentFuryValue(0);
                if(toxicosisCount>0){
                    FightCopyUtil.dealToxicosisCount(peerA.getCombatantList().get(0),toxicosisCount,stepDetail);
                }
                resultData.add(stepDetail);
                FightCopyUtil.reduceFreezeCount(peerA.getCombatantList().get(0).getSkillList());
                return 0;
            }
        }
    }


    private SkillSetting getSkillSettingBySkillIdAndLevel(int skillId,int level,List<SkillSetting> list){
        for(SkillSetting skillSetting:list){
            if(skillSetting.getSkillId()==skillId && skillSetting.getSkillLevel()==level){
                return skillSetting;
            }
        }
        return null;
    }


    @Override
    public List<Map<String, Object>> getUserDetail(Integer group, String userId, List<String> ids, Integer copyId, Integer exp) {
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Combatant> combatantList=new ArrayList<>();
        if(group==1){
            List<UserCopySetting> settings=userCopySettingService.findByCopyId(copyId);
            int groupIndex=0;
            for(String id:ids){
                groupIndex++;
                User user=userService.findByUserId(id);
                UserRole userRole=userRoleService.getByUserId(user.getUserId());
                Map<String,Object> selfMap=new HashMap<>();
                Combatant combatant=new Combatant();
                //Building building=new Building();
                int blood=0;
                UserAdornEquip adornEquip=userGoodsService.getUserFightingCapacity(id);
                blood+=adornEquip.getVitality();
                //combatant.setBuilding(building);
                selfMap.put("portraitUrl",user.getPortraitUrl());
                selfMap.put("name",user.getName());
                selfMap.put("userId",user.getUserId());
                selfMap.put("roleId",user.getRoleId());
                selfMap.put("blood",blood);
                selfMap.put("furyValue",0);
                selfMap.put("sex",user.getSex());
                selfMap.put("roleLevel",userRole.getRoleLevel());
                ArsenalInfoVO weapon=userGoodsService.findUserWornWeapon(id);

                combatant.setUserAdornEquip(adornEquip);
                combatant.setGroup(group);
                combatant.setGroupIndex(groupIndex);
                combatant.setBlood(blood);
                combatant.setFullBlood(blood);
                combatant.setFuryValue(0);
                combatant.setRoleId(userRole.getRoleId());
                combatant.setRoleLevel(userRole.getRoleLevel());
                combatant.setSex(user.getSex());
                combatant.setUserId(user.getUserId());
                combatant.setUserName(user.getName());
                combatant.setWornWeapon(weapon);

                //技能
                List<UserSkill> skillList=new ArrayList<>();
                if(999==copyId){
                    //各个职业技能 通用
                    List<Integer> skillIds=Arrays.asList(3,5,7,9,11,13,15,17,19,21);
                    for(int skillId:skillIds){
                        UserSkill userSkill=new UserSkill();
                        userSkill.setUserId(userId);
                        userSkill.setLevel(1);
                        userSkill.setSkillId(skillId);
                        userSkill.setId(UUIDUtil.generateUUID());
                        skillList.add(userSkill);
                    }
                }else{
                    skillList=userSkillService.findByUserId(id);
                }
                List<UserSkillVO> skillVOList=new ArrayList<>();
                List<UserSkillVO> viewSkillList=new ArrayList<>();
                if(skillList!=null && !skillList.isEmpty()){
                    skillList.forEach((UserSkill skill)->{
                        UserSkillVO skillVO=new UserSkillVO();
                        UserSkillVO viewSkill=new UserSkillVO();
                        Skill ski=skillService.findBySkillId(skill.getSkillId());
                        skillVO.setId(skill.getId());
                        skillVO.setSkillId(skill.getSkillId());
                        skillVO.setSkillLevel(skill.getLevel());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        viewSkill.setId(skill.getId());
                        viewSkill.setSkillId(skill.getSkillId());

                        viewSkillList.add(viewSkill);
                        skillVOList.add(skillVO);
                    });
                }
                combatant.setSkillList(skillVOList);
                if(999==copyId){
                    List<Integer> skillIds=Arrays.asList(3,5,7,9,11,13,15,17,19,21);
                    List<SkillSetting> paramList=new ArrayList<>();
                    skillIds.forEach(skillId->{
                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(skillId);
                        setting.setSkillLevel(1);
                        paramList.add(setting);
                    });
                    List<SkillSetting> skillSettings=skillSettingService.selectSkillSettingBySkillIdAndLevel(paramList);
                    combatant.setSkillSettingList(skillSettings);
                }else{
                    List<SkillSetting> skillSettings=skillSettingService.selectUserSkillSettings(id);
                    combatant.setSkillSettingList(skillSettings);
                }
                if(userId.equals(id)){
                    selfMap.put("skillList",viewSkillList);
                    if(redisService.get(RedisKey.USE_LIQUID+userId)!=null){
                        Map<String,Integer> data=(Map<String,Integer>)redisService.get(RedisKey.USE_LIQUID +userId);
                        float rate=0f;
                        if(data.get(LiquidMedicineConstant.LIFE)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.LIFE);
                            blood=(int)(blood+blood*rate);
                            combatant.setBlood(blood);
                            selfMap.put("blood",blood);
                            combatant.setAddLife(rate);
                        }else{
                            combatant.setAddLife(0f);
                        }
                        if(data.get(LiquidMedicineConstant.ATTACK)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.ATTACK);
                            combatant.setAddAttack(rate);
                        }else{
                            combatant.setAddAttack(0f);
                        }
                        if(data.get(LiquidMedicineConstant.DEFEND)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.DEFEND);
                            combatant.setAddDefend(rate);
                        }else{
                            combatant.setAddDefend(0f);
                        }
                        if(data.get(LiquidMedicineConstant.ACCURACY)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.ACCURACY);
                            combatant.setAddAccuracy(rate);
                        }else{
                            combatant.setAddAccuracy(0f);
                        }
                        if(data.get(LiquidMedicineConstant.MISS)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.MISS);
                            combatant.setAddMiss(rate);
                        }else{
                            combatant.setAddMiss(0f);
                        }
                        if(data.get(LiquidMedicineConstant.CRITICAL)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.CRITICAL);
                            combatant.setAddCritical(rate);
                        }else{
                            combatant.setAddCritical(0f);
                        }
                        if(data.get(LiquidMedicineConstant.DISCRITICAL)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.DISCRITICAL);
                            combatant.setAddDisCritical(rate);
                        }else{
                            combatant.setAddDisCritical(0f);
                        }
                        redisService.remove(RedisKey.USE_LIQUID +userId);
                    }
                } else{
                    selfMap.put("skillList",new ArrayList<>());
                }
                selfMap.put("weaponList",new ArrayList<>());

                //武器
                List<ArsenalInfoVO> arsenalInfoVOList=new ArrayList<>();
                if(999==copyId){
                    List<String> weaponIdList_ws=Arrays.asList("110016","110016","110018","110019","110020","110021","110022","110023","110024","110025");
                    List<String> weaponIdList_ck=Arrays.asList("120016","120016","120018","120019","120020","120021","120022","120023","120024","120025");
                    List<String> weaponIdList_gj=Arrays.asList("130016","130016","130018","130019","130020","130021","130022","130023","130024","130025");
                    List<String> weaponIdList_fs=Arrays.asList("140016","140016","140018","140019","140020","140021","140022","140023","140024","140025");
                    //卫士		刺客		工匠		法师
                    //110016   120016  130016  140016
                    //110017   120017  130017  140017
                    //110018   120018  130018  140018
                    //110019   120019  130019  140019
                    //110020   120020  130020  140020
                    //110021   120021  130021  140021
                    //110022   120022  130022  140022
                    //110023   120023  130023  140023
                    //110024   120024  130024  140024
                    //110025   120025  130025  140025
                    if(1==userRole.getRoleId() || 2==userRole.getRoleId()){
                        for(String weaponId:weaponIdList_ws){
                            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
                            ArsenalInfoVO arsenalInfo1=new ArsenalInfoVO();
                            BeanUtils.copyProperties(info,arsenalInfo1);
                            arsenalInfo1.setId(info.getIntensifyId());
                            arsenalInfo1.setEquipmentId(info.getId());
                            arsenalInfo1.setGoodsId(info.getId()+"");
                            arsenalInfo1.setWear(false);
                            arsenalInfoVOList.add(arsenalInfo1);
                        }
                        arsenalInfoVOList.get(0).setWear(true);
                        combatant.setWornWeapon(arsenalInfoVOList.get(0));
                    }
                    if(3==userRole.getRoleId() || 4==userRole.getRoleId()){
                        for(String weaponId:weaponIdList_ck){
                            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
                            ArsenalInfoVO arsenalInfo2=new ArsenalInfoVO();
                            BeanUtils.copyProperties(info,arsenalInfo2);
                            arsenalInfo2.setId(info.getIntensifyId());
                            arsenalInfo2.setGoodsId(info.getId()+"");
                            arsenalInfo2.setEquipmentId(info.getId());
                            arsenalInfo2.setWear(false);
                            arsenalInfoVOList.add(arsenalInfo2);
                        }
                        arsenalInfoVOList.get(0).setWear(true);
                        combatant.setWornWeapon(arsenalInfoVOList.get(0));
                    }
                    if(5==userRole.getRoleId() || 6==userRole.getRoleId()){
                        for(String weaponId:weaponIdList_gj){
                            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
                            ArsenalInfoVO arsenalInfo3=new ArsenalInfoVO();
                            BeanUtils.copyProperties(info,arsenalInfo3);
                            arsenalInfo3.setId(info.getIntensifyId());
                            arsenalInfo3.setGoodsId(info.getId()+"");
                            arsenalInfo3.setEquipmentId(info.getId());
                            arsenalInfo3.setWear(false);
                            arsenalInfoVOList.add(arsenalInfo3);
                        }
                        arsenalInfoVOList.get(0).setWear(true);
                        combatant.setWornWeapon(arsenalInfoVOList.get(0));
                    }
                    if(7==userRole.getRoleId() || 8==userRole.getRoleId()){
                        for(String weaponId:weaponIdList_fs){
                            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
                            ArsenalInfoVO arsenalInfo4=new ArsenalInfoVO();
                            arsenalInfo4.setId(info.getIntensifyId());
                            arsenalInfo4.setGoodsId(info.getId()+"");
                            arsenalInfo4.setEquipmentId(info.getId());
                            BeanUtils.copyProperties(info,arsenalInfo4);
                            arsenalInfo4.setWear(false);
                            arsenalInfoVOList.add(arsenalInfo4);
                        }
                        arsenalInfoVOList.get(0).setWear(true);
                        combatant.setWornWeapon(arsenalInfoVOList.get(0));
                    }
                }else{
                    arsenalInfoVOList=userGoodsService.findUserAllWeaponList(id);
                }
                combatant.setWeaponList(arsenalInfoVOList);

                //装备
                List<EquipmentInfoVO> equipments=userGoodsService.queryDressedEquipmentList(id);
                combatant.setEquipmentList(equipments);

                //999新手副本ID
                RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
                if(999==copyId){
                    //物理攻击:1110； 法力攻击:1110； 物理防御:740; 法力防御:740; 生命值:7680;
                    // 命中值:316;  闪避值:316; 暴击值:316; 抗暴击值:316;
                    setting.setWsHp(7860);setting.setFsHp(7860);setting.setGjHp(7860);setting.setCkHp(7860);
                    setting.setWsPhAtk(1110);setting.setFsPhAtk(1110);setting.setGjPhAtk(1110);setting.setCkPhAtk(1110);
                    setting.setWsMfAtk(1110);setting.setGjMfAtk(1110);setting.setFsMfAtk(1110);setting.setCkMfAtk(1110);
                    setting.setFsPhDef(740);setting.setWsPhDef(740);setting.setGjPhDef(740);setting.setCkPhDef(740);
                    setting.setFsMfDef(740);setting.setWsMfDef(740);setting.setGjMfDef(740);setting.setCkMfDef(740);
                    setting.setFsAccuracy(316);setting.setWsAccuracy(316);setting.setGjAccuracy(316);setting.setCkAccuracy(316);
                    setting.setFsMiss(316);setting.setWsMiss(316);setting.setGjMiss(316);setting.setCkMiss(316);
                    setting.setFsCritical(316);setting.setWsCritical(316);setting.setGjCritical(316);setting.setCkCritical(316);
                    setting.setFsDcritical(316);setting.setWsDcritical(316);setting.setGjDcritical(316);setting.setCkDcritical(316);
                    combatant.setRoleLevelSetting(setting);
                    int bloods=combatant.getBlood();
                    selfMap.put("blood",bloods+7086);
                    combatant.setBlood(bloods+7086);
                    combatant.setFullBlood(bloods+7086);
                }else{
                    //角色等级配置属性
                    combatant.setRoleLevelSetting(setting);
                }
                mapList.add(selfMap);
                if(!CollectionUtils.isEmpty(settings)){
                    combatant.setTalk(settings.get(0).getContent());
                }
                combatantList.add(combatant);
            }
            redisService.set(RedisKey.FIGHT_PVE_SELF+userId,combatantList);
        }

        if(group==2){
            mapList= MonsterCache.mapList.get(copyId);
            combatantList=MonsterCache.monsters.get(copyId);
            for(Combatant combatant:combatantList){
                combatant.setExpAward(exp);
                if(!CollectionUtils.isEmpty(combatant.getCombatantList())){
                    for(Combatant child:combatant.getCombatantList()){
                        child.setExpAward(exp);
                    }
                }
            }
            redisService.set(RedisKey.FIGHT_PVE_OPPONENT+userId,combatantList);
        }
        return mapList;
    }

    private int getUserFullBlood(Integer hp, String userId, UserRole userRole, Integer copyId) {
        List<PropInfo> propInfoList=userGoodsService.getUserWarePropInfo(userId);
        RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
        int blood=0;
        for(PropInfo propInfo:propInfoList){
            blood+=propInfo.getVitality();
        }
        if(999==copyId){
            blood+=7680;
        }else{
            switch (userRole.getRoleId()){
                case 1:
                case 2:
                    blood+=setting.getWsHp();//生命属性加成
                    break;
                case 3:
                case 4:
                    blood+=setting.getCkHp();//生命属性加成
                    break;
                case 5:
                case 6:
                    blood+=setting.getGjHp();//生命属性加成
                    break;
                case 7:
                case 8:
                    blood+=setting.getFsHp();//生命属性加成
                    break;
            }
        }
        blood+=hp;
        return blood;
    }
}
