package com.tongzhu.user.afight;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.FightConstant;
import com.tongzhu.user.action.ActionMap;
import com.tongzhu.user.action.ActionMapUtil;
import com.tongzhu.user.action.FightController;
import com.tongzhu.user.constant.*;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.MonsterData;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.domain.FightParameter;
import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.user.model.UserSkill;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

@FightController
public class FightDataController {

    private Logger logger= LoggerFactory.getLogger(FightDataController.class);


    @ActionMap(key = "checkIsCleanDebuff")
    public boolean checkIsCleanDebuff(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData) {
        boolean isCleanDeBuff=false;
        // skill_34 1040  自身有debuff时，xx%概率触发，净化所有debuff，接下来3回合不受debuff影响
        // skill_32 1038  每回合有xx%概率移除自身所有debuff
        if(peerA.getBuff()!=null && peerA.getBuff().get(BuffConstant.BUFF_1040)!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1040).get("count")!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1040).get("count")>0){
            //以前回合触发了1040
            isCleanDeBuff=true;
            //去掉所有debuff标识
            if(peerA.getDebuff()!=null){
                removeMap(peerA.getDebuff(),null);
            }
        }else{
            //第一回合开始或以前回合没有触发1040 根据概率触发
            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_32,1);
            skillIds.put(SkillConstant.SKILL_34,1);
            List<UserSkillVO> userSkills= FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
            if(!CollectionUtils.isEmpty(userSkills)){
                for(UserSkillVO skill:userSkills){
                    SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerA.getSkillSettingList());
                    if(skillSetting!=null && StringUtils.isNotBlank(skillSetting.getPro())){
                        JSONObject object= JSON.parseObject(skillSetting.getPro());
                        if(object.getFloat(BuffConstant.BUFF_1038)!=null){ //1038,每回合有xx%概率移除自身所有debuff  skillId:32 心如止水
                            isCleanDeBuff= RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1038).floatValue());
                            if(isCleanDeBuff) {
                                FightStepDetailVO stepDetail=new FightStepDetailVO();
                                FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_32,false,0,null,stepDetail);
                                resultData.add(stepDetail);
                                //触发成功，去掉所有debuff标识
                                if(peerA.getDebuff()!=null){
                                    removeMap(peerA.getDebuff(),null);
                                }
                                break;
                            }
                        }
                        if(object.getFloat(BuffConstant.BUFF_1040)!=null){//1040,自身有debuff时，xx%概率触发，净化所有debuff，接下来3回合不受debuff影响  skillId: 34 菩提之光
                            isCleanDeBuff=RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1040).floatValue());
                            if(isCleanDeBuff) {
                                //记录buff 回合数
                                Map<String, Map<String, Float>> dataTemp=peerA.getBuff();
                                if(dataTemp==null)
                                    dataTemp=new HashMap<>();
                                Map<String,Float> dataMap1040=new HashMap<>();
                                dataMap1040.put("count",4f);
                                dataTemp.put(BuffConstant.BUFF_1040,dataMap1040);
                                peerA.setBuff(dataTemp);

                                FightStepDetailVO stepDetail=new FightStepDetailVO();
                                FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_34,false,1,null,stepDetail);
                                resultData.add(stepDetail);
                                //触发成功，去掉所有debuff标识
                                this.removeMap(peerA.getDebuff(),null);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return isCleanDeBuff;
    }

    /**
     *
     * @param peerA
     * @param peerB
     * @param resultData
     * @param isCleanDeBuff
     * @param fightParameter
     * @return 1: B 死亡   2：A 死亡   0：跳过本回合,本回合结束  3：继续下一步
     */
    @ActionMap(key="solveDebuffResult")
    public int solveDebuffResult(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, Boolean isCleanDeBuff, FightParameter fightParameter){
        //净化失败
        if(!isCleanDeBuff) {
            //被眩晕1004 ,x%概率击晕对手1回合 skillId: 2 组合拳
            if (peerA.getDebuff() != null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1004) &&
                    peerA.getDebuff().get(BuffConstant.BUFF_1004).get("count") > 0) {
                //眩晕效果生效则直接跳过回合(有中毒，则要计算中毒伤害)
                //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 致命感染
                if (peerA.getDebuff() != null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014) &&
                        peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count") > 0) {
                    if (peerA.getBuff() != null && peerA.getBuff().get(BuffConstant.BUFF_1044) != null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") != null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") > 0) {
                        //有中毒暂缓的buff,不扣血
                    }else{
                        //扣除血量后是否致死
                        int toxicosisCount = (int) (peerA.getFullBlood() * 0.02);
                        fightParameter.setToxicosisCount(toxicosisCount);
                        if (peerA.getBlood() - toxicosisCount <= 0) {
                            return 2;
                        }
                    }
                }
                return 0;
            }
            //被眩晕1005 ,x%概率击晕对手2回合 skillId: 15 雷暴冲击
            if (peerA.getDebuff() != null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1005) &&
                    peerA.getDebuff().get(BuffConstant.BUFF_1005).get("count") > 0) {
                //眩晕效果生效则直接跳过回合(有中毒，则要计算中毒伤害)
                //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 致命感染
                if (peerA.getDebuff() != null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014) &&
                        peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count") > 0) {
                    if (peerA.getBuff() != null && peerA.getBuff().get(BuffConstant.BUFF_1044) != null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") != null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") > 0) {
                        //有中毒暂缓的buff,不扣血
                    }else{
                       //无中毒暂缓的buff
                        //扣除血量后是否致死
                        int toxicosisCount = (int) (peerA.getFullBlood() * 0.02);
                        fightParameter.setToxicosisCount(toxicosisCount);
                        if (peerA.getBlood() - toxicosisCount <= 0) {
                            return 2;
                        }
                    }
                }
                return 0;
            }
            //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 致命感染
            if (peerA.getDebuff() != null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014) &&
                    peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count") > 0) {
                boolean isCountToxicosisHurt = true;//是否中毒
                if (peerA.getBuff() != null && peerA.getBuff().get(BuffConstant.BUFF_1044) != null &&
                        peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") != null &&
                        peerA.getBuff().get(BuffConstant.BUFF_1044).get("count") > 0) {
                    //有中毒暂缓的buff,不扣血
                    isCountToxicosisHurt = false;
                } else {
                    //无中毒暂缓的buff，扣除血量
                    Map<Integer, Integer> skillIds = new HashMap<>();
                    //1044,xx%概率使中毒效果暂缓3回合  skillId:38 缓毒术
                    skillIds.put(SkillConstant.SKILL_38,1);
                    List<UserSkillVO> userSkills = FightingUtil.findUserSkillByBuffIds(peerA, skillIds);
                    if (CollectionUtils.isEmpty(userSkills)) {
                        isCountToxicosisHurt = true;
                    } else {
                        SkillSetting skillSetting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_38, userSkills.get(0).getSkillLevel(), peerA.getSkillSettingList());
                        if (skillSetting != null) {
                            if (StringUtils.isNotBlank(skillSetting.getPro())) {
                                JSONObject ooc = JSON.parseObject(skillSetting.getPro());
                                if (ooc.getFloat(BuffConstant.BUFF_1044) != null) {
                                    if (RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1044).floatValue())) {
                                        isCountToxicosisHurt = false;
                                        Map<String, Map<String, Float>> dataTemp9 = peerB.getBuff();
                                        if (dataTemp9 == null)
                                            dataTemp9 = new HashMap<>();
                                        Map<String, Float> dataMap91044 = new HashMap<>();
                                        dataMap91044.put("count", 3f);
                                        dataTemp9.put(BuffConstant.BUFF_1044, dataMap91044);
                                        peerA.setBuff(dataTemp9);
                                    }
                                }
                            }
                        }
                    }
                }
                if (isCountToxicosisHurt) {
                    //扣除血量后是否致死
                    int toxicosisCount = (int) (peerA.getFullBlood() * 0.02);
                    fightParameter.setToxicosisCount(toxicosisCount);
                    if (peerA.getBlood() - toxicosisCount <= 0) {
                        //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                        Map<Integer, Integer> skillIds = new HashMap<>();
                        skillIds.put(SkillConstant.SKILL_22, 1);
                        List<UserSkillVO> userSkills1 = FightingUtil.findUserSkillByBuffIds(peerA, skillIds);
                        boolean isFire1027 = false;
                        if (!CollectionUtils.isEmpty(userSkills1)) {
                            UserSkillVO skill = userSkills1.get(0);
                            SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill.getSkillLevel(), peerA.getSkillSettingList());
                            JSONObject object = JSON.parseObject(setting.getPro());
                            if (object.getFloat(BuffConstant.BUFF_1027) != null) {
                                isFire1027 = RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1027).floatValue());
                            }
                        }
                        if (isFire1027) {//触发22技能 1027
                            //复活，恢复血量，继续下一步
                            peerA.setBlood(1);
                            FightCopyUtil.freezeSkill(peerA, SkillConstant.SKILL_22, false);
                        } else {
                            //判定A死亡 回合结束给B相应奖励  结算
                            return 2;
                        }
                    }
                }
            }
        }
        return 3;
    }

    /**
     *
     * @param peerA
     * @param peerB
     * @param resultData
     * @param fightParameter
     * @param awardMap
     * @return 1: B 死亡   2：A 死亡   0：跳过本回合,本回合结束  3：继续下一步
     */
    @ActionMap(key="useBigAndSkill")
    public int useBigAndSkill(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData,
                              FightParameter fightParameter,Map<String,Integer> awardMap) throws Exception {
        float A1=40;
        float B1=40;
        float C1=55;
        //使用大招，技能
        //加载增益buff  增益buff包含且不限于：命中提升、暴击提升、攻击提升、生命值提升
        //命中提升1007、暴击提升1042、攻击提升1023、1024、生命值每回合回复1018
        //恢复血量 40%概率使用主动回复技能，包含且不限于：1017、1018、1025
        // 1018 接下来每回合回血xx%，持续3回合  skillId: 12 活力饼干
        Combatant playerA=null;
        if(peerA.getGroup().equals(1)){
            playerA=peerA;
        }else{
            playerA=peerA.getCombatantList().get(0);
        }
        if(playerA.getBuff()!=null && playerA.getBuff().get(BuffConstant.BUFF_1018)!=null &&
                playerA.getBuff().get(BuffConstant.BUFF_1018).get("count")!=null &&
                playerA.getBuff().get(BuffConstant.BUFF_1018).get("count")>0){

            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_12,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(playerA,skillIds);
            if(!CollectionUtils.isEmpty(userSkills)){
                UserSkillVO skill=userSkills.get(0);
                SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_12,skill.getSkillLevel(),playerA.getSkillSettingList());
                if(skillSetting!=null){
                    if(StringUtils.isNotBlank(skillSetting.getPro())){
                        JSONObject object=JSON.parseObject(skillSetting.getPro());
                        if(object.getFloat(BuffConstant.BUFF_1018)!=null){
                            int blood=playerA.getFullBlood();
                            int addBlood=(int)(blood*object.getBigDecimal(BuffConstant.BUFF_1018).floatValue()/100f);
                            int bloodCount=addBlood;
                            //fightParameter.setBloodCount(bloodCount);
                            blood=(playerA.getBlood()+addBlood)>playerA.getFullBlood()?playerA.getFullBlood():(playerA.getBlood()+addBlood);
                            playerA.setBlood(blood);
                        }
                    }
                }
            }
        }
        //1017  立即回血xx% ;1018 接下来每回合回血xx%，持续3回合; skillId: 12 活力饼干
        // 1025 生命值回复xx%，每次战斗只能触发一次               skillId: 19  圣光术
        Map<Integer,Integer> skillIds=new HashMap<>();
        skillIds.put(SkillConstant.SKILL_12,1);
        skillIds.put(SkillConstant.SKILL_19,1);
        List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(playerA,skillIds);
        float bloodRate=(float)playerA.getBlood()/playerA.getFullBlood();
        if(!CollectionUtils.isEmpty(userSkills) && bloodRate<0.3 && RandomUtil.probabilityEvent(A1)){
            int hurtCount=0;
            //成功释放技能，自身回复对应血量，获得对应buff，回合结束
            UserSkillVO skill=userSkills.get(0);
            //判断是否标记了沉默debuff
            //被沉默1016 ,有xx%概率沉默对手，持续2回合  不能放大招和技能  skillId: 11 奥术诅咒
            if(playerA.getDebuff()!=null && playerA.getDebuff().containsKey(BuffConstant.BUFF_1016) &&
                    playerA.getDebuff().get(BuffConstant.BUFF_1016).get("count")>0 ) {
                //标记了沉默debuff,不能使用大招和技能，去使用武器
                return 3;
            }else{
                //没有被沉默
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(skill.getSkillId().equals(SkillConstant.SKILL_12)){
                    UserSkill userSkill=new UserSkill();
                    userSkill.setLevel(skill.getSkillLevel());
                    userSkill.setSkillId(skill.getSkillId());
                    ActionMapUtil.invoke(SkillConstant.SKILL_12+"",playerA,null,userSkill,fightParameter,stepDetail);
                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(playerA,fightParameter.getToxicosisCount(),stepDetail);
                    }
                    if(playerA.getFuryValue()!=null){
                        int furyA = playerA.getFuryValue();
                        furyA = furyA + FightCopyUtil.getFuryValueForFightTo(peerB.getCombatantList().get(0));
                        playerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
                    }
                    if(peerB.getFuryValue()!=null){
                        int furyB = peerB.getFuryValue();
                        furyB=furyB + FightCopyUtil.getFuryValueForBeFight(playerA);
                        peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
                    }
                    Map<String,Object> map11=new HashMap<>();
                    Map<String,Object> map22=new HashMap<>();
                    map11.put("group",playerA.getGroup());
                    map11.put("index",playerA.getGroupIndex());
                    stepDetail.setAttacker(map11);

                    map22.put("group",peerB.getGroup());
                    map22.put("index",peerB.getGroupIndex());
                    stepDetail.setDefender(map22);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                    stepDetail.setBj(false);
                    resultData.add(stepDetail);
                    return 0;
                }else if(skill.getSkillId().equals(SkillConstant.SKILL_19)){
                    UserSkill userSkill=new UserSkill();
                    userSkill.setSkillId(skill.getSkillId());
                    userSkill.setLevel(skill.getSkillLevel());
                    ActionMapUtil.invoke(SkillConstant.SKILL_19+"",playerA,null,userSkill,fightParameter,stepDetail);
                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(playerA,fightParameter.getToxicosisCount(),stepDetail);
                    }

                    if(playerA.getFuryValue()!=null){
                        int furyA = playerA.getFuryValue();
                        furyA = furyA + FightCopyUtil.getFuryValueForFightTo(peerB.getCombatantList().get(0));
                        playerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
                    }
                    if(peerB.getFuryValue()!=null){
                        int furyB = peerB.getFuryValue();
                        furyB=furyB + FightCopyUtil.getFuryValueForBeFight(peerA);
                        peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
                    }
                    Map<String,Object> map11=new HashMap<>();
                    Map<String,Object> map22=new HashMap<>();
                    map11.put("group",playerA.getGroup());
                    map11.put("index",playerA.getGroupIndex());
                    stepDetail.setAttacker(map11);

                    map22.put("group",peerB.getGroup());
                    map22.put("index",peerB.getGroupIndex());
                    stepDetail.setDefender(map22);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                    stepDetail.setBj(false);
                    resultData.add(stepDetail);
                    return 0;
                }
            }
        }else{
            //怒气已满触发大招 对玩家造成220%伤害  40%概率触发大招
            int hurtCount=0;
            if(playerA.getFuryValue()!=null && playerA.getFuryValue()>= UserSkillConstant.MAX_FURY_VALUE && RandomUtil.probabilityEvent(B1)){
                //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                int furyA = FightCopyUtil.getFuryValueForFightTo(peerB.getCombatantList().get(0));
                peerA.setFuryValue(furyA);
                if(peerA.getGroup().equals(1)){
                    List<Map<String,Object>> deadplayerList=null;
                    Map<String,Object> map11=new HashMap<>();
                    map11.put("group",peerA.getGroup());
                    map11.put("index",peerA.getGroupIndex());
                    stepDetail.setAttacker(map11);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_8);
                    stepDetail.setBj(false);
                    int i=0,j=0;
                    while (!CollectionUtils.isEmpty(peerB.getCombatantList()) && i<peerB.getCombatantList().size()){
                        MonsterData monsterData=new MonsterData();
                        int tempi=i;
                        i++;
                        int attackCount=FightCopyUtil.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                        int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB.getCombatantList().get(tempi),peerA.getWornWeapon());//防御力

                        //触发大招 ,毁天灭地，对玩家造成220%伤害，对普通怪物造成500%伤害，对精英怪物造成750%伤害，对BOSS造成1000%伤害
                        if(peerB.getCombatantList().get(tempi).getMonster().getType()!=null){
                            if(peerB.getCombatantList().get(tempi).getMonster().getType()== MonsterConstant.TYPE_COMMON){
                                attackCount = attackCount * 5;
                            }
                            if(peerB.getCombatantList().get(tempi).getMonster().getType()==MonsterConstant.TYPE_CREAM){
                                attackCount = (int) (attackCount * 7.5);
                            }
                            if(peerB.getCombatantList().get(tempi).getMonster().getType()==MonsterConstant.TYPE_BOSS){
                                attackCount = attackCount * 10;
                            }
                        }
                        if(peerB.getCombatantList().get(tempi).getBuff()!=null && peerB.getCombatantList().get(tempi).getBuff().containsKey(BuffConstant.BUFF_1010) &&
                                peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("count")>0 ){

                            //玩家B有1010 buff, 减少所所受的x%普攻伤害，持续3回合  skillId: 5 守护天使
                            Float rate=(peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("data")==null?0f:peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("data"));
                            attackCount=attackCount-(int)(attackCount*(rate/100f));
                        }


                        hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);

                        ActionMapUtil.invoke("big",peerA,null,stepDetail);

                        int oldBlood=peerB.getCombatantList().get(tempi).getBlood();
                        if(oldBlood<=hurtCount){
                            //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                            Map<Integer, Integer> skillIdList = new HashMap<>();
                            skillIdList.put(SkillConstant.SKILL_22, 1);
                            List<UserSkillVO> userSkill22 = FightingUtil.findUserSkillByBuffIds(peerB.getCombatantList().get(tempi), skillIdList);
                            if (!CollectionUtils.isEmpty(userSkill22)) {
                                UserSkillVO skill = userSkill22.get(0);
                                SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill.getSkillLevel(), peerB.getCombatantList().get(tempi).getSkillSettingList());
                                JSONObject object = JSON.parseObject(setting.getPro());
                                if (object.getFloat(BuffConstant.BUFF_1027) != null &&
                                        RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1027).floatValue())) {
                                    //复活，恢复血量，继续下一步
                                    peerB.getCombatantList().get(tempi).setBlood(1);
                                    Map<String,Integer> defender=new HashMap<>();
                                    defender.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                    defender.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                    monsterData.setHurtCount(oldBlood-1);
                                    monsterData.setDefender(defender);
                                    monsterData.setBj(false);
                                    List<MonsterData> monsterDataList=stepDetail.getMonsterData();
                                    if(CollectionUtils.isEmpty(monsterDataList)){
                                        monsterDataList=new ArrayList<>();
                                        monsterDataList.add(monsterData);
                                        stepDetail.setMonsterData(monsterDataList);
                                    }else {
                                        monsterDataList.add(monsterData);
                                        stepDetail.setMonsterData(monsterDataList);
                                    }
                                }else{
                                    i=tempi;
                                    if(CollectionUtils.isEmpty(deadplayerList)){
                                        deadplayerList=new ArrayList<>();
                                        Map<String,Object> deadplayer=new HashMap<>();
                                        deadplayer.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                        deadplayer.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                        deadplayerList.add(deadplayer);
                                        //stepDetail.setDeadPlayerList(deadplayerList);
                                    }else{
                                        Map<String,Object> deadplayer=new HashMap<>();
                                        deadplayer.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                        deadplayer.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                        deadplayerList.add(deadplayer);
                                        //stepDetail.setDeadPlayerList(deadplayerList);
                                    }
                                    Map<String,Integer> defender=new HashMap<>();
                                    defender.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                    defender.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                    monsterData.setHurtCount(oldBlood);
                                    monsterData.setDefender(defender);
                                    monsterData.setBj(false);
                                    List<MonsterData> monsterDataList=stepDetail.getMonsterData();
                                    if(CollectionUtils.isEmpty(monsterDataList)){
                                        monsterDataList=new ArrayList<>();
                                        monsterDataList.add(monsterData);
                                        stepDetail.setMonsterData(monsterDataList);
                                    }else {
                                        monsterDataList.add(monsterData);
                                        stepDetail.setMonsterData(monsterDataList);
                                    }
                                    logger.debug("怪物{}[{}]被kill掉了一只",peerB.getCombatantList().get(tempi).getUserName(),peerB.getCombatantList().get(tempi).getGroupIndex());
                                    FightCopyUtil.addAward(awardMap,peerB.getCombatantList().get(tempi));
                                    FightCopyUtil.removeCombatant(peerB.getCombatantList(),tempi);
                                }
                            }else{
                                i=tempi;
                                if(CollectionUtils.isEmpty(deadplayerList)){
                                    deadplayerList=new ArrayList<>();
                                    Map<String,Object> deadplayer=new HashMap<>();
                                    deadplayer.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                    deadplayer.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                    deadplayerList.add(deadplayer);
                                    //stepDetail.setDeadPlayerList(deadplayerList);
                                }else{
                                    Map<String,Object> deadplayer=new HashMap<>();
                                    deadplayer.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                    deadplayer.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                    deadplayerList.add(deadplayer);
                                    //stepDetail.setDeadPlayerList(deadplayerList);
                                }
                                Map<String,Integer> defender=new HashMap<>();
                                defender.put("group",peerB.getCombatantList().get(tempi).getGroup());
                                defender.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                                monsterData.setHurtCount(oldBlood);
                                monsterData.setDefender(defender);
                                monsterData.setBj(false);
                                List<MonsterData> dataList=stepDetail.getMonsterData();
                                if(dataList==null){
                                    dataList=new ArrayList<>();
                                    dataList.add(monsterData);
                                    stepDetail.setMonsterData(dataList);
                                }else{
                                    dataList.add(monsterData);
                                }
                                logger.debug("怪物{}[{}]被kill掉了一只",peerB.getCombatantList().get(tempi).getUserName(),peerB.getCombatantList().get(tempi).getGroupIndex());
                                FightCopyUtil.addAward(awardMap,peerB.getCombatantList().get(tempi));
                                FightCopyUtil.removeCombatant(peerB.getCombatantList(),tempi);
                            }
                        }else{
                            peerB.getCombatantList().get(tempi).setBlood(oldBlood-hurtCount);
                            Map<String,Integer> defender=new HashMap<>();
                            defender.put("group",peerB.getCombatantList().get(tempi).getGroup());
                            defender.put("index",peerB.getCombatantList().get(tempi).getGroupIndex());
                            monsterData.setHurtCount(hurtCount);
                            monsterData.setDefender(defender);
                            monsterData.setBj(false);
                            List<MonsterData> monsterDataList=stepDetail.getMonsterData();
                            if(CollectionUtils.isEmpty(monsterDataList)){
                                monsterDataList=new ArrayList<>();
                                monsterDataList.add(monsterData);
                                stepDetail.setMonsterData(monsterDataList);
                            }else {
                                monsterDataList.add(monsterData);
                                stepDetail.setMonsterData(monsterDataList);
                            }
                        }
                    }
                    stepDetail.setSelfFuryValue(peerA.getFuryValue());
                    stepDetail.setOpponentFuryValue(0);
                    if(CollectionUtils.isEmpty(peerA.getCombatantList())){
                        stepDetail.setSelfBlood(peerA.getBlood());
                        stepDetail.setOpponentBlood(0);
                        resultData.add(stepDetail);
                        if(deadplayerList!=null){
                            FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                            deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                            deadStepDetail.setDeadPlayerList(deadplayerList);
                            resultData.add(deadStepDetail);
                        }
                        return 1;
                    }else{
                        int blood=0;
                        for(Combatant cb:peerB.getCombatantList()) {
                            blood += cb.getBlood();
                        }
                        stepDetail.setSelfBlood(peerA.getBlood());
                        stepDetail.setOpponentBlood(blood);
                        resultData.add(stepDetail);
                        if(deadplayerList!=null){
                            FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                            deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                            deadStepDetail.setDeadPlayerList(deadplayerList);
                            resultData.add(deadStepDetail);
                        }
                        return 0;
                    }
                }
            }else{
                //=======================================================================================================
                //怒气未满或无怒气 释放主动技能 55%概率随机使用一个技能
                //检测当前是否有可释放的主动技能
                List<UserSkill> skillList=findInitiativeSkillByUserIdAndType(playerA);
                if(!CollectionUtils.isEmpty(skillList) && RandomUtil.probabilityEvent(C1)) {
                    //成功释放技能  结算伤害
                    UserSkill useSkill=null;
                    for(UserSkill skill:skillList){
                        //技能ID为 7 ，10要先判断是否有足够武器，向对方投掷3件武器（包括已佩戴的）或 打掉对方1件武器（不包括已佩戴的）
                        if (skill.getSkillId() == SkillConstant.SKILL_7) {
                            if (playerA.getWeaponList() != null && playerA.getWeaponList().size() >= 3){
                                useSkill = skill;
                                break;
                            }
                        }else if (skill.getSkillId() == SkillConstant.SKILL_10) {
                            if (!CollectionUtils.isEmpty(peerB.getWeaponList())){
                                useSkill = skill;
                                break;
                            }
                        }else if(skill.getSkillId().equals(SkillConstant.SKILL_12) || skill.getSkillId().equals(SkillConstant.SKILL_19)){
                            continue;
                        }else{
                            useSkill = skill;
                            break;
                        }
                    }

                    //有可释放的主动技能，且没有标记沉默(1016 有xx%概率沉默对手，持续2回合 skillId: 11 奥术诅咒
                    if (useSkill!=null && (playerA.getDebuff()==null || (playerA.getDebuff()!=null &&
                            !playerA.getDebuff().containsKey(BuffConstant.BUFF_1016)))) {
                        //有释放主动技能，不使用武器攻击
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        if(playerA.getFuryValue()!=null){
                            int furyA = playerA.getFuryValue();
                            furyA = furyA + FightCopyUtil.getFuryValueForFightTo(peerB.getCombatantList().get(0));
                            playerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
                        }
                        Map<String,Object> map11=new HashMap<>();
                        map11.put("group",playerA.getGroup());
                        map11.put("index",playerA.getGroupIndex());
                        stepDetail.setAttacker(map11);
                        //--------------------------------------------------------------------------------
                        if(peerA.getGroup().equals(1)) {
                            List<Map<String, Object>> deadplayerList = null;
                            int i = 0;
                            if (useSkill.getSkillId() == SkillConstant.SKILL_5) {
                                hurtCount = (int) ActionMapUtil.invoke(SkillConstant.SKILL_5 + "", playerA, peerB.getCombatantList().get(0), useSkill, 0, 0, stepDetail, null);
                            } else {
                                while (!CollectionUtils.isEmpty(peerB.getCombatantList()) && i < peerB.getCombatantList().size()) {
                                    MonsterData monsterData = new MonsterData();
                                    monsterData.setBj(false);
                                    int tempi = i;
                                    i++;
                                    int attackCount = FightCopyUtil.getUserAggressivity(playerA, playerA.getWornWeapon());//攻击力
                                    int defenceCount = FightCopyUtil.getUserDefence(playerA, peerB.getCombatantList().get(tempi), playerA.getWornWeapon());//防御力
                                    //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
                                    // 1023 skill: 21 神之力量  造成%d%%+%d伤害，自身攻击力提升%d%%，持续2回合
                                    if (playerA.getBuff() != null && playerA.getBuff().containsKey(BuffConstant.BUFF_1023) &&
                                            playerA.getBuff().get(BuffConstant.BUFF_1023).get("count") > 0) {

                                        attackCount = (int) (attackCount * playerA.getBuff().get(BuffConstant.BUFF_1023).get("data"));
                                    }

                                    //1024  skill: 18 真意百破道  对对手造成%d%%+%d伤害，获得强力buff，接下来每1回合增加%d%%攻击，持续4回合
                                    if (playerA.getBuff() != null && playerA.getBuff().containsKey(BuffConstant.BUFF_1024) &&
                                            playerA.getBuff().get(BuffConstant.BUFF_1024).get("count") > 0) {

                                        attackCount = (int) (attackCount * playerA.getBuff().get(BuffConstant.BUFF_1024).get("data"));
                                    }
                                    hurtCount = (int) ActionMapUtil.invoke(useSkill.getSkillId() + "", playerA, peerB.getCombatantList().get(tempi), useSkill, attackCount, defenceCount, stepDetail, monsterData);
                                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_8);
                                    //检测玩家B是否有虚弱buff
                                    //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
                                    if (peerB.getCombatantList().get(tempi).getDebuff() != null && peerB.getCombatantList().get(tempi).getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                                            peerB.getCombatantList().get(tempi).getDebuff().get(BuffConstant.BUFF_1020).get("count") > 0) {
                                        //玩家B有虚弱debuff,必暴击  加暴击算伤害
                                        //初始暴击伤害比例 150%
                                        hurtCount = (int) (hurtCount * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                                        monsterData.setBj(true);
                                    } else {
                                        //玩家B无虚弱debuff  代入暴击公式计算是否暴击  暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
                                        int critCount = FightCopyUtil.getUseCrrit(playerA, playerA.getWornWeapon());
                                        int defenceCritCount = FightCopyUtil.getUseDefenceCrit(peerB.getCombatantList().get(tempi), playerA.getWornWeapon());
                                        float bjRate = Math.min(critCount / (critCount + defenceCritCount * 19f), 0.5f);
                                        if (RandomUtil.probabilityEvent(bjRate * 100)) {
                                            //初始暴击伤害比例 150%
                                            hurtCount = (int) (hurtCount * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                                            monsterData.setBj(true);
                                        }
                                    }

                                    //检测玩家B是否有1010技能
                                    if (peerB.getCombatantList().get(tempi).getBuff() != null && peerB.getCombatantList().get(tempi).getBuff().containsKey(BuffConstant.BUFF_1010) &&
                                            peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("count") > 0) {
                                        //玩家B有1010 buff, 减少所受的x%普攻伤害，持续3回合  skillId: 5 守护天使
                                        Float rate = (peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("data") == null ? 0f : peerB.getCombatantList().get(tempi).getBuff().get(BuffConstant.BUFF_1010).get("data"));
                                        hurtCount = hurtCount - (int) (hurtCount * (rate / 100f));
                                    }
                                    int oldBlood = peerB.getCombatantList().get(tempi).getBlood();
                                    if (oldBlood <= hurtCount) {
                                        //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                                        Map<Integer, Integer> skillIdList = new HashMap<>();
                                        skillIdList.put(SkillConstant.SKILL_22, 1);
                                        List<UserSkillVO> userSkill22 = FightingUtil.findUserSkillByBuffIds(peerB.getCombatantList().get(tempi), skillIdList);
                                        if (!CollectionUtils.isEmpty(userSkill22)) {
                                            UserSkillVO skill = userSkill22.get(0);
                                            SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill.getSkillLevel(), peerB.getCombatantList().get(tempi).getSkillSettingList());
                                            JSONObject object = JSON.parseObject(setting.getPro());
                                            if (object.getFloat(BuffConstant.BUFF_1027) != null && RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1027).floatValue())) {
                                                //复活，恢复血量，继续下一步
                                                peerB.getCombatantList().get(tempi).setBlood(1);
                                                Map<String, Integer> defender = new HashMap<>();
                                                defender.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                defender.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                monsterData.setHurtCount(oldBlood - 1);
                                                monsterData.setDefender(defender);
                                                List<MonsterData> monsterDataList = stepDetail.getMonsterData();
                                                if (CollectionUtils.isEmpty(monsterDataList)) {
                                                    monsterDataList = new ArrayList<>();
                                                    monsterDataList.add(monsterData);
                                                    stepDetail.setMonsterData(monsterDataList);
                                                } else {
                                                    monsterDataList.add(monsterData);
                                                    stepDetail.setMonsterData(monsterDataList);
                                                }
                                            } else {
                                                i = tempi;
                                                if (CollectionUtils.isEmpty(deadplayerList)) {
                                                    deadplayerList = new ArrayList<>();
                                                    Map<String, Object> deadplayer = new HashMap<>();
                                                    deadplayer.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                    deadplayer.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                    deadplayerList.add(deadplayer);
                                                    //stepDetail.setDeadPlayerList(deadplayerList);
                                                } else {
                                                    Map<String, Object> deadplayer = new HashMap<>();
                                                    deadplayer.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                    deadplayer.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                    deadplayerList.add(deadplayer);
                                                    //stepDetail.setDeadPlayerList(deadplayerList);
                                                }
                                                Map<String, Integer> defender = new HashMap<>();
                                                defender.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                defender.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                monsterData.setHurtCount(oldBlood);
                                                monsterData.setDefender(defender);
                                                List<MonsterData> monsterDataList = stepDetail.getMonsterData();
                                                if (CollectionUtils.isEmpty(monsterDataList)) {
                                                    monsterDataList = new ArrayList<>();
                                                    monsterDataList.add(monsterData);
                                                    stepDetail.setMonsterData(monsterDataList);
                                                } else {
                                                    monsterDataList.add(monsterData);
                                                    stepDetail.setMonsterData(monsterDataList);
                                                }
                                                logger.debug("怪物{}[{}]被kill掉了一只", peerB.getCombatantList().get(tempi).getUserName(), peerB.getCombatantList().get(tempi).getGroupIndex());
                                                FightCopyUtil.addAward(awardMap, peerB.getCombatantList().get(tempi));
                                                FightCopyUtil.removeCombatant(peerB.getCombatantList(), tempi);
                                            }
                                        } else {
                                            i = tempi;
                                            if (CollectionUtils.isEmpty(deadplayerList)) {
                                                deadplayerList = new ArrayList<>();
                                                Map<String, Object> deadplayer = new HashMap<>();
                                                deadplayer.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                deadplayer.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                deadplayerList.add(deadplayer);
                                                //stepDetail.setDeadPlayerList(deadplayerList);
                                            } else {
                                                Map<String, Object> deadplayer = new HashMap<>();
                                                deadplayer.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                                deadplayer.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                                deadplayerList.add(deadplayer);
                                                //stepDetail.setDeadPlayerList(deadplayerList);
                                            }
                                            Map<String, Integer> defender = new HashMap<>();
                                            defender.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                            defender.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                            monsterData.setHurtCount(oldBlood);
                                            monsterData.setDefender(defender);
                                            List<MonsterData> monsterDataList = stepDetail.getMonsterData();
                                            if (CollectionUtils.isEmpty(monsterDataList)) {
                                                monsterDataList = new ArrayList<>();
                                                monsterDataList.add(monsterData);
                                                stepDetail.setMonsterData(monsterDataList);
                                            } else {
                                                monsterDataList.add(monsterData);
                                                stepDetail.setMonsterData(monsterDataList);
                                            }
                                            logger.debug("怪物{}[{}]被kill掉了一只", peerB.getCombatantList().get(tempi).getUserName(), peerB.getCombatantList().get(tempi).getGroupIndex());
                                            FightCopyUtil.addAward(awardMap, peerB.getCombatantList().get(tempi));
                                            FightCopyUtil.removeCombatant(peerB.getCombatantList(), tempi);
                                        }
                                    } else {
                                        peerB.getCombatantList().get(tempi).setBlood(oldBlood - hurtCount);
                                        Map<String, Integer> defender = new HashMap<>();
                                        defender.put("group", peerB.getCombatantList().get(tempi).getGroup());
                                        defender.put("index", peerB.getCombatantList().get(tempi).getGroupIndex());
                                        monsterData.setHurtCount(hurtCount);
                                        monsterData.setDefender(defender);
                                        List<MonsterData> monsterDataList = stepDetail.getMonsterData();
                                        if (CollectionUtils.isEmpty(monsterDataList)) {
                                            monsterDataList = new ArrayList<>();
                                            monsterDataList.add(monsterData);
                                            stepDetail.setMonsterData(monsterDataList);
                                        } else {
                                            monsterDataList.add(monsterData);
                                            stepDetail.setMonsterData(monsterDataList);
                                        }
                                    }
                                }
                            }
                            stepDetail.setSelfFuryValue(peerA.getFuryValue());
                            stepDetail.setOpponentFuryValue(0);
                            if(CollectionUtils.isEmpty(peerB.getCombatantList())){
                                stepDetail.setSelfBlood(peerA.getBlood());
                                stepDetail.setOpponentBlood(0);
                                resultData.add(stepDetail);
                                if(deadplayerList!=null){
                                    FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                                    deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                                    deadStepDetail.setDeadPlayerList(deadplayerList);
                                    resultData.add(deadStepDetail);
                                }
                                return 1;
                            }else{
                                int blood=0;
                                for(Combatant cb:peerB.getCombatantList()) {
                                    blood += cb.getBlood();
                                }
                                stepDetail.setSelfBlood(peerA.getBlood());
                                stepDetail.setOpponentBlood(blood);
                                resultData.add(stepDetail);
                                if(deadplayerList!=null){
                                    FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                                    deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                                    deadStepDetail.setDeadPlayerList(deadplayerList);
                                    resultData.add(deadStepDetail);
                                }
                                return 0;
                            }
                            //------------------------------------------------------------------------------------------
                        }else {
                            List<Map<String,Object>> deadplayerList=null;
                            if(peerB.getFuryValue()!=null){
                                int furyB = peerB.getFuryValue();
                                furyB=furyB +FightCopyUtil.getFuryValueForBeFight(peerA);
                                peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
                            }
                            Map<String,Object> map22=new HashMap<>();
                            map22.put("group",peerB.getGroup());
                            map22.put("index",peerB.getGroupIndex());
                            stepDetail.setDefender(map22);
                            stepDetail.setBj(false);
                            int attackCount = FightCopyUtil.getUserAggressivity(playerA, playerA.getWornWeapon());//攻击力
                            int defenceCount = FightCopyUtil.getUserDefence(playerA, peerB, playerA.getWornWeapon());//防御力
                            //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
                            // 1023 skill: 21 神之力量  造成%d%%+%d伤害，自身攻击力提升%d%%，持续2回合
                            if(playerA.getBuff()!=null && playerA.getBuff().containsKey(BuffConstant.BUFF_1023) &&
                                    playerA.getBuff().get(BuffConstant.BUFF_1023).get("count")>0){

                                attackCount=(int)(attackCount*playerA.getBuff().get(BuffConstant.BUFF_1023).get("data"));
                            }

                            //1024  skill: 18 真意百破道  对对手造成%d%%+%d伤害，获得强力buff，接下来每1回合增加%d%%攻击，持续4回合
                            if(playerA.getBuff()!=null && playerA.getBuff().containsKey(BuffConstant.BUFF_1024) &&
                                    playerA.getBuff().get(BuffConstant.BUFF_1024).get("count")>0){

                                attackCount=(int)(attackCount*playerA.getBuff().get(BuffConstant.BUFF_1024).get("data"));
                            }
                            hurtCount = (int)ActionMapUtil.invoke(useSkill.getSkillId()+"",playerA, peerB, useSkill,attackCount,defenceCount,stepDetail);
                            //检测玩家B是否有虚弱buff
                            //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
                            if (peerB.getDebuff() != null && peerB.getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                                    peerB.getDebuff().get(BuffConstant.BUFF_1020).get("count") > 0) {
                                //玩家B有虚弱debuff,必暴击  加暴击算伤害
                                //初始暴击伤害比例 150%
                                hurtCount = (int) (hurtCount * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                                stepDetail.setBj(true);
                            } else {
                                //玩家B无虚弱debuff  代入暴击公式计算是否暴击  暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
                                int critCount = FightCopyUtil.getUseCrrit(playerA, playerA.getWornWeapon());
                                int defenceCritCount = FightCopyUtil.getUseDefenceCrit(peerB, playerA.getWornWeapon());
                                float bjRate = Math.min(critCount / (critCount + defenceCritCount * 19), 0.5f);
                                if (RandomUtil.probabilityEvent(bjRate*100)) {
                                    //初始暴击伤害比例 150%
                                    hurtCount = (int) (hurtCount * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                                    stepDetail.setBj(true);
                                }
                            }
                            //检测玩家B是否有1010技能
                            if(peerB.getBuff()!=null && peerB.getBuff().containsKey(BuffConstant.BUFF_1010) &&
                                    peerB.getBuff().get(BuffConstant.BUFF_1010).get("count")>0 ){
                                //玩家B有1010 buff, 减少所受的x%普攻伤害，持续3回合  skillId: 5 守护天使
                                Float rate=(peerB.getBuff().get(BuffConstant.BUFF_1010).get("data")==null?0f:peerB.getBuff().get(BuffConstant.BUFF_1010).get("data"));
                                hurtCount=hurtCount-(int)(hurtCount*(rate/100f));
                            }
                            if(peerB.getBlood()<=hurtCount){
                                //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                                Map<Integer, Integer> skillIdList = new HashMap<>();
                                skillIdList.put(SkillConstant.SKILL_22, 1);
                                List<UserSkillVO> userSkill22 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                                if (!CollectionUtils.isEmpty(userSkill22)) {
                                    UserSkillVO skill = userSkill22.get(0);
                                    SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill.getSkillLevel(), peerB.getSkillSettingList());
                                    JSONObject object = JSON.parseObject(setting.getPro());
                                    if (object.getFloat(BuffConstant.BUFF_1027) != null && RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1027).floatValue())) {
                                        //复活，恢复血量，继续下一步
                                        peerB.setBlood(1);
                                        int blood=0;
                                        for(Combatant cb:peerA.getCombatantList()) {
                                            blood += cb.getBlood();
                                        }
                                        stepDetail.setSelfBlood(peerB.getBlood());
                                        stepDetail.setOpponentBlood(blood);
                                        stepDetail.setHurtCount(peerB.getBlood()-1);
                                        stepDetail.setSelfFuryValue(peerB.getFuryValue());
                                        stepDetail.setOpponentFuryValue(0);
                                        resultData.add(stepDetail);
                                        return 0;
                                    }else{
                                        //判定B死亡
                                        if(CollectionUtils.isEmpty(deadplayerList)){
                                            deadplayerList=new ArrayList<>();
                                            Map<String,Object> deadplayer=new HashMap<>();
                                            deadplayer.put("index",peerB.getGroupIndex());
                                            deadplayer.put("group",peerB.getGroup());
                                            deadplayerList.add(deadplayer);
                                            //stepDetail.setDeadPlayerList(deadplayerList);
                                        }else{
                                            Map<String,Object> deadplayer=new HashMap<>();
                                            deadplayer.put("index",peerB.getGroupIndex());
                                            deadplayer.put("group",peerB.getGroup());
                                            deadplayerList.add(deadplayer);
                                            //stepDetail.setDeadPlayerList(deadplayerList);
                                        }
                                        int blood=0;
                                        for(Combatant cb:peerA.getCombatantList()) {
                                            blood += cb.getBlood();
                                        }
                                        stepDetail.setSelfBlood(0);
                                        stepDetail.setOpponentBlood(blood);
                                        stepDetail.setSelfFuryValue(peerB.getFuryValue());
                                        stepDetail.setHurtCount(peerB.getBlood());
                                        stepDetail.setOpponentFuryValue(0);
                                        resultData.add(stepDetail);
                                        return 1;
                                    }
                                }else{
                                    //判定B死亡
                                    if(CollectionUtils.isEmpty(deadplayerList)){
                                        deadplayerList=new ArrayList<>();
                                        Map<String,Object> deadplayer=new HashMap<>();
                                        deadplayer.put("index",peerB.getGroupIndex());
                                        deadplayer.put("group",peerB.getGroup());
                                        deadplayerList.add(deadplayer);
                                        //stepDetail.setDeadPlayerList(deadplayerList);
                                    }else{
                                        Map<String,Object> deadplayer=new HashMap<>();
                                        deadplayer.put("index",peerB.getGroupIndex());
                                        deadplayer.put("group",peerB.getGroup());
                                        deadplayerList.add(deadplayer);
                                        //stepDetail.setDeadPlayerList(deadplayerList);
                                    }
                                    int blood=0;
                                    for(Combatant cb:peerA.getCombatantList()) {
                                        blood += cb.getBlood();
                                    }
                                    stepDetail.setHurtCount(peerB.getBlood());
                                    stepDetail.setSelfBlood(0);
                                    stepDetail.setOpponentBlood(blood);
                                    stepDetail.setSelfFuryValue(peerB.getFuryValue());
                                    stepDetail.setOpponentFuryValue(0);
                                    resultData.add(stepDetail);
                                    if(deadplayerList!=null){
                                        FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                                        deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                                        deadStepDetail.setDeadPlayerList(deadplayerList);
                                        resultData.add(deadStepDetail);
                                    }
                                    return 1;
                                }
                            }else{
                                peerB.setBlood(peerB.getBlood()-hurtCount);
                                int blood=0;
                                for(Combatant cb:peerA.getCombatantList()) {
                                    blood += cb.getBlood();
                                }
                                stepDetail.setHurtCount(hurtCount>0?hurtCount:null);
                                stepDetail.setSelfBlood(0);
                                stepDetail.setOpponentBlood(blood);
                                stepDetail.setSelfFuryValue(peerB.getFuryValue());
                                stepDetail.setOpponentFuryValue(0);
                                resultData.add(stepDetail);
                                if(deadplayerList!=null){
                                    FightStepDetailVO deadStepDetail=new FightStepDetailVO();
                                    deadStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                                    deadStepDetail.setDeadPlayerList(deadplayerList);
                                    resultData.add(deadStepDetail);
                                }
                                return 0;
                            }

                            /*else{
                                //检测B是否有1036Buff  有20%概率在承受攻击时，使对方遭受%s%的伤害反噬 skillId: 30  反击术
                                Map<Integer, Integer> skillIdList = new HashMap<>();
                                skillIdList.put(SkillConstant.SKILL_30, 1);
                                List<UserSkillVO> userSkill30 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                                if (!CollectionUtils.isEmpty(userSkill30)) {
                                    UserSkillVO skill = userSkill30.get(0);
                                    SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_30, skill.getSkillLevel(), peerA.getCombatantList().get(0).getSkillSettingList());
                                    JSONObject object = JSON.parseObject(setting.getPro());
                                    if (object.getFloat(BuffConstant.BUFF_1036) != null && RandomUtil.probabilityEvent(20)) {
                                        float rate=object.getFloat(BuffConstant.BUFF_1036);
                                        int hurtBackCount=(int)rate*peerA.getCombatantList().get(0).getFullBlood();
                                        if(peerA.getCombatantList().get(0).getBlood()<=hurtBackCount){
                                            //直接扣除A的对应血量，致死
                                            logger.debug("怪物{}[{}]被kill掉了一只",peerA.getCombatantList().get(0).getUserName(),peerA.getCombatantList().get(0).getGroupIndex());
                                            FightCopyUtil.removeCombatant(peerA.getCombatantList(),0);

                                            resultData.add(stepDetail);
                                            return 1;
                                        }else{
                                            //直接扣除A的对应血量，不致死
                                            return 3;
                                        }
                                    }else{
                                        //1036——30技能触发失败
                                        return 3;
                                    }
                                }else{
                                    //1036——30技能触发失败
                                    return 3;
                                }
                            }*/
                        }
                    }else {
                        //没有释放主动技能，则使用武器攻击
                        return 3;
                    }
                }else{
                    //没有释放主动技能，则使用武器攻击
                    return 3;
                }
                //==========================================================================================================
            }
        }
        return 3;
    }


    @ActionMap(key = "useWeapon")
    public int useWeapon(Combatant peerA, Combatant peerB, FightStepDetailVO stepDetail, FightParameter fightParameter,List<Combatant> groupB){
        Map<String,String> deleteWeapon=new HashMap<>();
        float D1=30;
        float D2=30;
        float D3=40;
        //使用武器攻击
        // 检测武器库里是否还有武器
        List<ArsenalInfoVO> arsenalInfoVOList=peerA.getWeaponList();
        int rand=RandomUtil.random(1,1000);
        int hurtCount=0;
        if(!CollectionUtils.isEmpty(arsenalInfoVOList)){
            if(rand<=D1*10 && peerA.getWornWeapon()!=null && arsenalInfoVOList.size()>1){
                //武器库里有武器 30%概率投掷当前武器，随机装备上另一件武器
                //投掷武器 计算伤害
                int attackCount=FightCopyUtil.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                stepDetail.setActionName(peerA.getWornWeapon().getName());
                List<String> weaponIdList=new ArrayList<>();
                weaponIdList.add(peerA.getWornWeapon().getId());
                Map<String,Object> lostWeapon=new HashMap<>();
                lostWeapon.put("group",peerA.getGroup());
                lostWeapon.put("index",peerA.getGroupIndex());
                lostWeapon.put("weaponIdList",weaponIdList);
                stepDetail.setLostWeapon(lostWeapon);
                //stepDetail.setWeaponIdList(weaponIdList);
                FightCopyUtil.removeArsenalInfoVO(peerA.getWeaponList(),peerA.getWornWeapon());

                peerA.setWornWeapon(peerA.getWeaponList().get(0));
                Map<String,Object> map11=new HashMap<>();
                Map<String,Object> map22=new HashMap<>();
                map11.put("group",peerA.getGroup());
                map11.put("index",peerA.getGroupIndex());
                stepDetail.setAttacker(map11);

                map22.put("group",peerB.getGroup());
                map22.put("index",peerB.getGroupIndex());
                stepDetail.setDefender(map22);

                stepDetail.setHurtCount(hurtCount);
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_3);

            }else if(rand>D1*10 && rand<=(D1+D2)*10 && peerA.getWornWeapon()!=null && arsenalInfoVOList.size()>1){
                //武器库里有武器 30%概率丢弃当前武器，随机换一个武器并进行装备并攻击
                //使用武器攻击  计算伤害

                //标记了定身debuff 定身1011 ,x%概率让对方定身，持续4回合 skillId: 6 百步定身
                if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                        peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ) {
                    //标记了定身debuff  跳过该回合
                    //客户端提示：玩家A被定身了，无法攻击
                    //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);
                    return 0;
                }else{
                    //List<String> weaponIdList=new ArrayList<>();
                    //weaponIdList.add(peerA.getWornWeapon().getGoodsId());
                    //stepDetail.setWeaponIdList(weaponIdList);
                    FightCopyUtil.removeArsenalInfoVO(peerA.getWeaponList(),peerA.getWornWeapon());
                    peerA.setWornWeapon(peerA.getWeaponList().get(0));
                    int attackCount=FightCopyUtil.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                    int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                    hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                    Map<String,Object> map11=new HashMap<>();
                    Map<String,Object> map22=new HashMap<>();
                    map11.put("group",peerA.getGroup());
                    map11.put("index",peerA.getGroupIndex());
                    stepDetail.setAttacker(map11);

                    map22.put("group",peerB.getGroup());
                    map22.put("index",peerB.getGroupIndex());
                    stepDetail.setDefender(map22);
                    stepDetail.setActionName(peerA.getWornWeapon().getName());
                    stepDetail.setHurtCount(hurtCount);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                }
            }else{
                //40%概率roll空
                //检测当前手上是否有武器
                ArsenalInfoVO arsenalInfoVO=peerA.getWornWeapon();
                if(arsenalInfoVO!=null){
                    //手上有武器,使用当前武器攻击
                    //标记了定身debuff 定身1011 ,x%概率让对方定身，持续4回合 skillId: 6 百步定身
                    if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                            peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ) {
                        //标记了定身debuff  跳过该回合
                        //客户端提示：玩家A被定身了，无法攻击
                        //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);
                        return 0;
                    }else{
                        int attackCount=FightCopyUtil.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                        int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                        hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                        Map<String,Object> map11=new HashMap<>();
                        Map<String,Object> map22=new HashMap<>();
                        map11.put("group",peerA.getGroup());
                        map11.put("index",peerA.getGroupIndex());
                        stepDetail.setAttacker(map11);

                        map22.put("group",peerB.getGroup());
                        map22.put("index",peerB.getGroupIndex());
                        stepDetail.setDefender(map22);

                        stepDetail.setActionName(peerA.getWornWeapon().getName());
                        stepDetail.setHurtCount(hurtCount);
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                    }
                }else{
                    //手上无武器,使用徒手进行攻击
                    if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                            peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ) {
                        //标记了定身debuff  跳过该回合
                        //客户端提示：玩家A被定身了，无法攻击
                        //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);
                        return 0;
                    }else{
                        int attackCount=FightCopyUtil.getUserAggressivity(peerA,null);//攻击力
                        int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,null);//防御力
                        hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                        Map<String,Object> map11=new HashMap<>();
                        Map<String,Object> map22=new HashMap<>();
                        map11.put("group",peerA.getGroup());
                        map11.put("index",peerA.getGroupIndex());
                        stepDetail.setAttacker(map11);

                        map22.put("group",peerB.getGroup());
                        map22.put("index",peerB.getGroupIndex());
                        stepDetail.setDefender(map22);
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                        stepDetail.setActionName("拳头");
                        stepDetail.setHurtCount(hurtCount);
                    }
                }

            }
        }else{
            //检测当前手上是否有武器
            ArsenalInfoVO arsenalInfoVO=peerA.getWornWeapon();
            if(arsenalInfoVO!=null){
                //手上有武器,使用当前武器攻击
                //标记了定身debuff 定身1011 ,x%概率让对方定身，持续4回合 skillId: 6 百步定身
                if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                        peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ) {
                    //标记了定身debuff  跳过该回合
                    //客户端提示：玩家A被定身了，无法攻击
                    //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);
                    return 0;
                }else{
                    int attackCount=FightCopyUtil.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                    int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                    hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                    Map<String,Object> map11=new HashMap<>();
                    Map<String,Object> map22=new HashMap<>();
                    map11.put("group",peerA.getGroup());
                    map11.put("index",peerA.getGroupIndex());
                    stepDetail.setAttacker(map11);

                    map22.put("group",peerB.getGroup());
                    map22.put("index",peerB.getGroupIndex());
                    stepDetail.setDefender(map22);

                    stepDetail.setActionName(peerA.getWornWeapon().getName());
                    stepDetail.setHurtCount(hurtCount);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                }
            }else{
                //手上无武器,使用徒手进行攻击
                //标记了定身debuff 定身1011 ,x%概率让对方定身，持续4回合 skillId: 6 百步定身
                if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                        peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ) {
                    //标记了定身debuff  跳过该回合
                    //客户端提示：玩家A被定身了，无法攻击
                    //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);
                    return 0;
                }else{
                    int attackCount=FightCopyUtil.getUserAggressivity(peerA,null);//攻击力
                    int defenceCount=FightCopyUtil.getUserDefence(peerA,peerB,null);//防御力
                    hurtCount= FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
                    Map<String,Object> map11=new HashMap<>();
                    Map<String,Object> map22=new HashMap<>();
                    map11.put("group",peerA.getGroup());
                    map11.put("index",peerA.getGroupIndex());
                    stepDetail.setAttacker(map11);

                    map22.put("group",peerB.getGroup());
                    map22.put("index",peerB.getGroupIndex());
                    stepDetail.setDefender(map22);
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                    stepDetail.setActionName("拳头");
                    stepDetail.setHurtCount(hurtCount);
                }
            }

        }
        //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害（1023、1024）
        // 1023 skill: 21 神之力量  造成%d%%+%d伤害，自身攻击力提升%d%%，持续2回合
        if(peerA.getBuff()!=null && peerA.getBuff().containsKey(BuffConstant.BUFF_1023) &&
                peerA.getBuff().get(BuffConstant.BUFF_1023).get("count")>0){

        }

        //1024  skill: 18 真意百破道  对对手造成%d%%+%d伤害，获得强力buff，接下来每1回合增加%d%%攻击，持续4回合
        if(peerA.getBuff()!=null && peerA.getBuff().containsKey(BuffConstant.BUFF_1024) &&
                peerA.getBuff().get(BuffConstant.BUFF_1024).get("count")>0){

        }
        stepDetail.setBj(false);
        //检测玩家B是否有虚弱buff
        //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
        if(peerB.getDebuff()!=null && peerB.getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                peerB.getDebuff().get(BuffConstant.BUFF_1020).get("count")>0 ){
            //玩家B有虚弱debuff,必暴击  加暴击算伤害
            //初始暴击伤害比例 150%
             hurtCount=(int)(hurtCount*FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
             stepDetail.setBj(true);
        }else{
            //玩家B无虚弱debuff 再根据玩家A的暴击增益buff，代入暴击公式计算是否暴击（1042）暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
            int critCount=FightCopyUtil.getUseCrrit(peerA,peerA.getWornWeapon());
            int defenceCritCount=FightCopyUtil.getUseDefenceCrit(peerB,peerA.getWornWeapon());
            float bjRate=Math.min(critCount/(critCount+defenceCritCount*19),0.5f);
            if(RandomUtil.probabilityEvent(bjRate*100)) {
                //加暴击伤害
                //初始暴击伤害比例 150%
                hurtCount = (int) (hurtCount * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                stepDetail.setBj(true);
            }
        }
        //根据玩家A的命中增益buff，1007 代入命中公式计算是否命中  命中率= max(攻击方命中/（攻击方命中+被攻击者闪避*修正系数c）, 最小命中率)
        int accuracy=FightCopyUtil.getUserHitRate(peerA,peerA.getWornWeapon());
        int miss=FightCopyUtil.getUserDodge(peerB,peerB.getWornWeapon());
        float hitRate=(float)Math.max(accuracy/(accuracy+miss*0.05263 ),50f);

        if(RandomUtil.probabilityEvent(hitRate)){
            //命中，检测玩家A是否有失明buff
            //失明1021 ,有xx%概率使对方失明，持续2回合 ;失明后闪避无效  skillId: 16   太阳拳
            if((peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1021) &&
                    peerA.getDebuff().get(BuffConstant.BUFF_1021).get("count")>0 )){
                //失明，回合结束
                //FightingUtil.generateFightingStep(peerA,peerB,2,16,false,3,null,stepDetail);
                if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_3)){
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_6);
                }
                if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_2)){
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                }
                stepDetail.setCategory(1);
                stepDetail.setHurtCount(0);
                return 0;
            }else{
                //未失明，检测玩家B是否有1010技能
                if(peerB.getBuff()!=null && peerB.getBuff().containsKey(BuffConstant.BUFF_1010) &&
                        peerB.getBuff().get(BuffConstant.BUFF_1010).get("count")>0 ){
                    //玩家B有1010 buff, 减少所受的x%普攻伤害，持续3回合  skillId: 5 守护天使
                    Float rate=(peerB.getBuff().get(BuffConstant.BUFF_1010).get("data")==null?0f:peerB.getBuff().get(BuffConstant.BUFF_1010).get("data"));
                    hurtCount=hurtCount-(int)(hurtCount*(rate/100f));
                }
                //判断玩家B是否触发1046 buff ,格挡一次攻击 skill:40 格挡攻击
                Map<Integer,Integer> skillIds=new HashMap<>();
                skillIds.put(SkillConstant.SKILL_40,1);
                List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
                if(!CollectionUtils.isEmpty(userSkills)){
                    UserSkillVO skill=userSkills.get(0);
                    SkillSetting setting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerB.getSkillSettingList());
                    if(setting!=null && StringUtils.isNotBlank(setting.getPro()) &&
                            JSON.parseObject(setting.getPro()).getFloat(BuffConstant.BUFF_1046)!=null &&
                            RandomUtil.probabilityEvent(JSON.parseObject(setting.getPro()).getBigDecimal(BuffConstant.BUFF_1046).floatValue())){

                        //格挡成功，回合结束
                        if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_3)){
                            stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_6);
                        }
                        if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_2)){
                            stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                        }
                        stepDetail.setCategory(2);
                        stepDetail.setHurtCount(0);
                        return 0;
                    }else{
                        //格挡失败,
                        if(peerB.getBlood()<=hurtCount){
                            //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                            Map<Integer, Integer> skillIdList = new HashMap<>();
                            skillIdList.put(SkillConstant.SKILL_22, 1);
                            List<UserSkillVO> userSkill22 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                            if (!CollectionUtils.isEmpty(userSkill22)) {
                                UserSkillVO skill22 = userSkill22.get(0);
                                SkillSetting setting22 = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill22.getSkillLevel(), peerB.getSkillSettingList());
                                JSONObject object22 = JSON.parseObject(setting22.getPro());
                                if (object22.getFloat(BuffConstant.BUFF_1027) != null && RandomUtil.probabilityEvent(object22.getBigDecimal(BuffConstant.BUFF_1027).floatValue())) {
                                    //复活，恢复血量，回合结束
                                    int blood=peerB.getBlood();
                                    peerB.setBlood(1);
                                    //FightingUtil.generateFightingStep(peerA,peerB,2,skill22.getSkillId(),false,1,null,stepDetail);
                                    stepDetail.setHurtCount(blood-1);
                                    return 0;
                                }else{
                                    //判定B死亡
                                    stepDetail.setHurtCount(peerB.getBlood());
                                    return 1;
                                }
                            }else{
                                //判定B死亡
                                stepDetail.setHurtCount(peerB.getBlood());
                                return 1;
                            }
                        }else{
                            //直接扣除怪的对应血量，不致死，回合结束
                            peerB.setBlood(peerB.getBlood()-hurtCount);
                            stepDetail.setHurtCount(hurtCount);
                            return 0;
                        }
                        /*else{
                            //检测B是否有1036Buff  有20%概率在承受攻击时，使对方遭受%s%的伤害反噬 skillId: 30  反击术
                            Map<Integer, Integer> skillIdList = new HashMap<>();
                            skillIdList.put(SkillConstant.SKILL_30, 1);
                            List<UserSkillVO> userSkill30 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                            if (!CollectionUtils.isEmpty(userSkill30)) {
                                UserSkillVO skill30 = userSkill30.get(0);
                                SkillSetting setting30 = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_30, skill30.getSkillLevel(), peerA.getSkillSettingList());
                                JSONObject object30 = JSON.parseObject(setting30.getPro());
                                if (object30.getFloat(BuffConstant.BUFF_1036) != null && RandomUtil.probabilityEvent(20)) {
                                    float rate=object30.getFloat(BuffConstant.BUFF_1036);
                                    int hurtBackCount=(int)rate*peerA.getFullBlood();
                                    if(peerA.getBlood()<=hurtBackCount){
                                        //直接扣除A的对应血量，致死
                                        return 1;
                                    }else{
                                        //直接扣除A的对应血量，不致死，回合结束
                                        return 0;
                                    }
                                }else{
                                    //1036——30技能触发失败，回合结束
                                    return 0;
                                }
                            }else{
                                //1036——30技能触发失败，回合结束
                                return 0;
                            }
                        }*/
                    }
                }else{
                    //格挡失败,
                    if(peerB.getBlood()<=hurtCount){
                        //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 浴火重生
                        Map<Integer, Integer> skillIdList = new HashMap<>();
                        skillIdList.put(SkillConstant.SKILL_22, 1);
                        List<UserSkillVO> userSkill22 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                        if (!CollectionUtils.isEmpty(userSkill22)) {
                            UserSkillVO skill22 = userSkill22.get(0);
                            SkillSetting setting22 = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_22, skill22.getSkillLevel(), peerB.getSkillSettingList());
                            JSONObject object22 = JSON.parseObject(setting22.getPro());
                            if (object22.getFloat(BuffConstant.BUFF_1027) != null && RandomUtil.probabilityEvent(object22.getBigDecimal(BuffConstant.BUFF_1027).floatValue())) {
                                //复活，恢复血量，回合结束
                                stepDetail.setHurtCount(peerB.getBlood()-1);
                                peerB.setBlood(1);
                                return 0;
                            }else{
                                //判定B死亡
                                stepDetail.setHurtCount(peerB.getBlood());
                                return 1;
                            }
                        }else{
                            //判定B死亡
                            stepDetail.setHurtCount(peerB.getBlood());
                            return 1;
                        }
                    }else {
                        //直接扣除怪的对应血量，不致死，回合结束
                        peerB.setBlood(peerB.getBlood()-hurtCount);
                        stepDetail.setHurtCount(hurtCount);
                        return 0;
                    }
                    /*else{
                        //检测B是否有1036Buff  有20%概率在承受攻击时，使对方遭受%s%的伤害反噬 skillId: 30  反击术
                        Map<Integer, Integer> skillIdList = new HashMap<>();
                        skillIdList.put(SkillConstant.SKILL_30, 1);
                        List<UserSkillVO> userSkill30 = FightingUtil.findUserSkillByBuffIds(peerB, skillIdList);
                        if (!CollectionUtils.isEmpty(userSkill30)) {
                            UserSkillVO skill30 = userSkill30.get(0);
                            SkillSetting setting30 = FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_30, skill30.getSkillLevel(), peerA.getSkillSettingList());
                            JSONObject object30 = JSON.parseObject(setting30.getPro());
                            if (object30.getFloat(BuffConstant.BUFF_1036) != null && RandomUtil.probabilityEvent(20)) {
                                float rate=object30.getFloat(BuffConstant.BUFF_1036);
                                int hurtBackCount=(int)rate*peerA.getFullBlood();
                                if(peerA.getBlood()<=hurtBackCount){
                                    //直接扣除A的对应血量，致死
                                    return 1;
                                }else{
                                    //直接扣除A的对应血量，不致死，回合结束
                                    return 0;
                                }
                            }else{
                                //1036——30技能触发失败，回合结束
                                return 0;
                            }
                        }else{
                            //1036——30技能触发失败，回合结束
                            return 0;
                        }
                    }*/
                }
            }
        }else{
            //未命中，回合结束
            if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_3)){
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_6);
            }
            if(stepDetail.getType().equals(FightingConstant.FIGHT_STEP_TYPE_2)){
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
            }
            stepDetail.setCategory(1);
            stepDetail.setHurtCount(0);
            return 0;
        }
    }



    private void removeMap(Map<String, Map<String,Float>> map, String removeKey){
        Iterator<Map.Entry<String,Map<String,Float>>> it = map.entrySet().iterator();
        if(StringUtils.isBlank(removeKey)){
            while(it.hasNext()){
                it.remove();
            }
        }else{
            while(it.hasNext()){
                Map.Entry<String,Map<String,Float>> entry=it.next();
                String key=entry.getKey();
                if(removeKey.equals(key)){
                    it.remove();
                }
            }
        }
    }


    public List<UserSkill> findInitiativeSkillByUserIdAndType(Combatant combatant){
        List<UserSkill> skillList=new ArrayList<>();
        for(UserSkillVO skillVO:combatant.getSkillList()){
            if(skillVO.getType().equals(UserSkillConstant.INITIATIVE_TYPE_SKILL) && skillVO.getFreezeCount()<=0 && skillVO.getCanUse()){
                UserSkill skill=new UserSkill();
                skill.setSkillId(skillVO.getSkillId());
                skill.setLevel(skillVO.getSkillLevel());
                skill.setId(skillVO.getId());
                skill.setUserId(combatant.getUserId());
                skillList.add(skill);
            }
        }
        return skillList;
    }

    private void releaseSkill(Combatant peerA,Combatant peerB,UserSkill skill,FightParameter fightParameter,List<FightStepDetailVO> resultData,List<Combatant> groupB){

        Boolean isCounterattack=false;
        Boolean isFireBuff=false;
        List<String> weaponIdList=new ArrayList<>();
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(skill.getSkillId(), skill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            int attackCount = FightCopyUtil.getUserAggressivity(peerA, peerA.getWornWeapon());//攻击力
            int defenceCount = FightCopyUtil.getUserDefence(peerA, peerB, peerA.getWornWeapon());//防御力
            int hurtCount = this.useInitiativeSkill(peerA, peerB, skill.getSkillId(), object, attackCount,
                    defenceCount,isCounterattack,isFireBuff,fightParameter.getHurtBackCount(),weaponIdList);
            fightParameter.setHurtCount(hurtCount);
            FightCopyUtil.freezeSkill(peerA,skill.getSkillId(), true);
        }

        //fightParameter.setSkip2(true);

        //增加双方怒气
        if(peerB.getFuryValue()!=null){
            int furyB = peerB.getFuryValue();
            furyB = furyB + RandomUtil.random(12, 16);
            peerB.setFuryValue(furyB > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyB);
        }
        if(peerA.getFuryValue()!=null){
            int furyA = peerA.getFuryValue();
            furyA = furyA + RandomUtil.random(8, 12);
            peerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
        }

        //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
        //检测玩家B是否有虚弱buff
        //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
        if (peerB.getDebuff() != null && peerB.getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                peerB.getDebuff().get(BuffConstant.BUFF_1020).get("count") > 0) {
            //玩家B有虚弱debuff,必暴击  加暴击算伤害
            //初始暴击伤害比例 150%
            int hurtCount = (int) (fightParameter.getHurtCount() * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
            fightParameter.setHurtCount(hurtCount);
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            if(fightParameter.getToxicosisCount()>0){
                FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
            }
            if(fightParameter.getHurtBackCount()>0){
                stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                fightParameter.setHurtBackCount(0);
            }
            FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),true,isFireBuff?2:3,hurtCount,stepDetail,weaponIdList);

            if(peerA.getGroup()==1){
                stepDetail.setSelfFuryValue(peerA.getFuryValue());
                stepDetail.setOpponentFuryValue(peerB.getFuryValue());
            }else{
                stepDetail.setSelfFuryValue(peerB.getFuryValue());
                stepDetail.setOpponentFuryValue(peerA.getFuryValue());
            }

            if(peerA.getGroup()==1){
                int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                    for(Combatant cb:groupB.get(0).getCombatantList()){
                        if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                            blood+=cb.getBlood();
                        }
                    }
                }
                stepDetail.setSelfBlood(peerA.getBlood());
                stepDetail.setOpponentBlood(blood);
            }else{
                int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                    for(Combatant cb:groupB.get(0).getCombatantList()){
                        if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                            blood+=cb.getBlood();
                        }
                    }
                }
                stepDetail.setSelfBlood(blood);
                stepDetail.setOpponentBlood(peerA.getBlood());
            }
            resultData.add(stepDetail);
        } else {
            //玩家B无虚弱debuff  代入暴击公式计算是否暴击  暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
            int critCount = FightCopyUtil.getUseCrrit(peerA, peerA.getWornWeapon());
            int defenceCritCount = FightCopyUtil.getUseDefenceCrit(peerB, peerA.getWornWeapon());
            float bjRate = Math.min(critCount / (critCount + defenceCritCount * 19), 0.5f);
            if (RandomUtil.probabilityEvent(bjRate*100)) {
                //初始暴击伤害比例 150%
                int hurtCount = (int) (fightParameter.getHurtCount() * FightConstant.BIG_SKILL_HURT_RATE_MONSTER);
                fightParameter.setHurtCount(hurtCount);
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                if(fightParameter.getHurtBackCount()>0){
                    Map<String,Object> hurtBackMap=new HashMap<>();
                    hurtBackMap.put("group",peerA.getGroup());
                    hurtBackMap.put("index",peerA.getGroupIndex());
                    hurtBackMap.put("value",fightParameter.getHurtBackCount());
                    stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                    fightParameter.setHurtBackCount(0);
                }
                FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),true,isFireBuff?2:3,hurtCount,stepDetail,weaponIdList);

                if(peerA.getGroup()==1){
                    stepDetail.setSelfFuryValue(peerA.getFuryValue());
                    stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                }else{
                    stepDetail.setSelfFuryValue(peerB.getFuryValue());
                    stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                }

                if(peerA.getGroup()==1){
                    int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                    if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                        for(Combatant cb:groupB.get(0).getCombatantList()){
                            if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                                blood+=cb.getBlood();
                            }
                        }
                    }
                    stepDetail.setSelfBlood(peerA.getBlood());
                    stepDetail.setOpponentBlood(blood);
                }else{
                    int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                    if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                        for(Combatant cb:groupB.get(0).getCombatantList()){
                            if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                                blood+=cb.getBlood();
                            }
                        }
                    }
                    stepDetail.setSelfBlood(blood);
                    stepDetail.setOpponentBlood(peerA.getBlood());
                }
                resultData.add(stepDetail);
            } else {
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                if(fightParameter.getHurtBackCount()>0){
                    Map<String,Object> hurtBackMap=new HashMap<>();
                    hurtBackMap.put("group",peerA.getGroup());
                    hurtBackMap.put("index",peerA.getGroupIndex());
                    hurtBackMap.put("value",fightParameter.getHurtBackCount());
                    stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                    fightParameter.setHurtBackCount(0);
                }
                FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,isFireBuff?1:3,fightParameter.getHurtCount(),stepDetail,weaponIdList);

                if(peerA.getGroup()==1){
                    stepDetail.setSelfFuryValue(peerA.getFuryValue());
                    stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                }else{
                    stepDetail.setSelfFuryValue(peerB.getFuryValue());
                    stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                }

                if(peerA.getGroup()==1){
                    int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                    if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                        for(Combatant cb:groupB.get(0).getCombatantList()){
                            if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                                blood+=cb.getBlood();
                            }
                        }
                    }
                    stepDetail.setSelfBlood(peerA.getBlood());
                    stepDetail.setOpponentBlood(blood);
                }else{
                    int blood=(peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount());
                    if(!CollectionUtils.isEmpty(groupB.get(0).getCombatantList())){
                        for(Combatant cb:groupB.get(0).getCombatantList()){
                            if(!(cb.getGroupIndex().equals(peerB.getGroupIndex()))){
                                blood+=cb.getBlood();
                            }
                        }
                    }
                    stepDetail.setSelfBlood(blood);
                    stepDetail.setOpponentBlood(peerA.getBlood());
                }
                resultData.add(stepDetail);
            }
        }
        if(isCounterattack){
            if(skill.getSkillId()==SkillConstant.SKILL_2){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                if(fightParameter.getHurtBackCount()>0){
                    Map<String,Object> hurtBackMap=new HashMap<>();
                    hurtBackMap.put("group",peerA.getGroup());
                    hurtBackMap.put("index",peerA.getGroupIndex());
                    hurtBackMap.put("value",fightParameter.getHurtBackCount());
                    stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                    fightParameter.setHurtBackCount(0);
                }
                FightingUtil.generateFightingStep(peerB,peerA,2,SkillConstant.SKILL_37,false,1,null,stepDetail);

                resultData.add(stepDetail);
            }
            if(skill.getSkillId()==SkillConstant.SKILL_6){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                if(fightParameter.getHurtBackCount()>0){
                    Map<String,Object> hurtBackMap=new HashMap<>();
                    hurtBackMap.put("group",peerA.getGroup());
                    hurtBackMap.put("index",peerA.getGroupIndex());
                    hurtBackMap.put("value",fightParameter.getHurtBackCount());
                    stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                    fightParameter.setHurtBackCount(0);
                }
                FightingUtil.generateFightingStep(peerB,peerA,2,SkillConstant.SKILL_39,false,1,null,stepDetail);

                resultData.add(stepDetail);
            }
            if(skill.getSkillId()==SkillConstant.SKILL_9){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                if(fightParameter.getHurtBackCount()>0){
                    Map<String,Object> hurtBackMap=new HashMap<>();
                    hurtBackMap.put("group",peerA.getGroup());
                    hurtBackMap.put("index",peerA.getGroupIndex());
                    hurtBackMap.put("value",fightParameter.getHurtBackCount());
                    stepDetail.setHurtBackCount(fightParameter.getHurtBackCount());
                    fightParameter.setHurtBackCount(0);
                }
                FightingUtil.generateFightingStep(peerB,peerA,2,SkillConstant.SKILL_38,false,1,null,stepDetail);

                resultData.add(stepDetail);
            }
        }
    }

    public Integer useInitiativeSkill(Combatant peerA, Combatant peerB, Integer skillId, JSONObject object,
                                      Integer attackCount, Integer defenceCount,Boolean isCounterattack,
                                      Boolean isFireBuff,Integer effectSelfCount,List<String> weaponIdList) {

        /** 攻击技能如果携带buff，则计算玩家B的debuff抗性技能，包含且不限于(1043、1044、1045) */
        Integer hurtCount=0;
        switch (skillId){
            case 1:
                //desc="该回合命中提升%f%%，给对方造成%f%%+%f伤害"
                Map<String, Map<String, Float>> dataTemp1=peerA.getBuff();
                if(dataTemp1==null)
                    dataTemp1=new HashMap<>();
                Map<String,Float> dataMap11003=new HashMap<>();
                dataMap11003.put("count",1f);
                dataMap11003.put("useState",0f);
                dataMap11003.put("data",object.getDouble(BuffConstant.BUFF_1013).floatValue());
                dataTemp1.put(BuffConstant.BUFF_1013,dataMap11003);

                peerA.setBuff(dataTemp1);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 2:
                //desc="对敌人连赏10大巴掌，造成%f%%+%f伤害，有%f%%概率击晕对手1回合"     // 37技能反击
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1004).floatValue())){
                    isFireBuff=true;
                    if(peerB.getBuff()!=null && peerB.getBuff().get(BuffConstant.BUFF_1043)!=null &&
                            peerB.getBuff().get(BuffConstant.BUFF_1043).get("count")!=null &&
                            peerB.getBuff().get(BuffConstant.BUFF_1043).get("count")>0){
                        isCounterattack=true;
                    }else {
                        Map<Integer,Integer> skillIds=new HashMap<>();
                        skillIds.put(SkillConstant.SKILL_37,1);
                        List<UserSkillVO> userSkills= FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
                        if(userSkills.isEmpty()){
                            isCounterattack=false;
                        }else{
                            SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(userSkills.get(0).getSkillId(),userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
                            if(skillSetting!=null){
                                if(StringUtils.isNotBlank(skillSetting.getPro())){
                                    JSONObject ooc= JSON.parseObject(skillSetting.getPro());
                                    if(ooc.getFloat(BuffConstant.BUFF_1043)!=null){ //1043,xx%概率抵抗眩晕  skillId:37 返魂术
                                        if(RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1043).floatValue())){
                                            isCounterattack=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(!isCounterattack){
                        Map<String, Map<String, Float>> dataTemp2=peerB.getDebuff();
                        if(dataTemp2==null)
                            dataTemp2=new HashMap<>();
                        Map<String,Float> dataMap21004=new HashMap<>();
                        dataMap21004.put("count",1f);
                        dataMap21004.put("useState",0f);
                        dataTemp2.put(BuffConstant.BUFF_1004,dataMap21004);
                        peerB.setDebuff(dataTemp2);
                    }
                }
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 3:
                // desc="一位身背龟壳的老大爷发明的绝招，发出气功波造成%f%%伤害，给自身增加%f%%命中buff，维持3回合"
                Map<String, Map<String, Float>> dataTemp3=peerA.getBuff();
                if(dataTemp3==null)
                    dataTemp3=new HashMap<>();

                Map<String,Float> dataMap31007=new HashMap<>();
                dataMap31007.put("count",3f);
                dataMap31007.put("useState",0f);
                dataMap31007.put("data",object.getDouble(BuffConstant.BUFF_1007).floatValue());
                dataTemp3.put(BuffConstant.BUFF_1007,dataMap31007);
                peerA.setBuff(dataTemp3);


                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 4:
                //desc="对同性造成%f%%伤害，对异性造成%f%%伤害"
                if(peerA.getSex()==peerB.getSex()){
                    attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1008)/100f));
                }else{
                    attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1009)/100f));
                }
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 5:
                //desc="减少所受的%f%%普攻伤害，持续3回合"
                Map<String, Map<String, Float>> dataTemp5=peerA.getBuff();
                if(dataTemp5==null)
                    dataTemp5=new HashMap<>();
                Map<String,Float> dataMap51010=new HashMap<>();
                dataMap51010.put("count",3f);
                dataMap51010.put("useState",0f);
                dataMap51010.put("data",object.getDouble(BuffConstant.BUFF_1010).floatValue());
                dataTemp5.put(BuffConstant.BUFF_1010,dataMap51010);
                peerA.setBuff(dataTemp5);

                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 6:
                //desc="%f%%概率让对方定身，不能使用近战武器攻击，持续4回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1011).floatValue())){
                    isFireBuff=true;
                    if(peerB.getBuff()!=null && peerB.getBuff().get(BuffConstant.BUFF_1045)!=null &&
                            peerB.getBuff().get(BuffConstant.BUFF_1045).get("count")!=null &&
                            peerB.getBuff().get(BuffConstant.BUFF_1045).get("count")>0){
                        isCounterattack=true;
                    }else {
                        Map<Integer,Integer> skillIds=new HashMap<>();
                        skillIds.put(SkillConstant.SKILL_39,1);
                        List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
                        if(userSkills.isEmpty()){
                            isCounterattack=false;
                        }else{
                            SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(userSkills.get(0).getSkillId(),userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
                            if(skillSetting!=null){
                                if(StringUtils.isNotBlank(skillSetting.getPro())){
                                    JSONObject ooc= JSON.parseObject(skillSetting.getPro());
                                    if(ooc.getFloat(BuffConstant.BUFF_1045)!=null){ //1045,%d%%概率抵抗定身  skillId:39 解除束缚
                                        if(RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1045).floatValue())){
                                            isCounterattack=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(!isCounterattack){
                        Map<String, Map<String, Float>> dataTemp6=peerB.getDebuff();
                        if(dataTemp6==null)
                            dataTemp6=new HashMap<>();
                        Map<String,Float> dataMap61011=new HashMap<>();
                        dataMap61011.put("count",4f);
                        dataMap61011.put("useState",0f);
                        dataTemp6.put(BuffConstant.BUFF_1011,dataMap61011);
                        peerB.setDebuff(dataTemp6);
                    }
                }
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 7:
                //desc="瞬间向对方投掷3件武器，每件武器造成%f%%的伤害"
                if(peerA.getWeaponList()!=null && peerA.getWeaponList().size()>=3){
                    weaponIdList.add(peerA.getWeaponList().get(0).getId());
                    int i = 0;
                    List<ArsenalInfoVO> del1=new ArrayList<>();
                    Iterator<ArsenalInfoVO> iterator1 = peerA.getWeaponList().listIterator();
                    while (iterator1.hasNext() && i < 1) {
                        i = 2;
                        del1.add(iterator1.next());
                    }
                    peerA.getWeaponList().removeAll(del1);

                    weaponIdList.add(peerA.getWeaponList().get(0).getId());
                    List<ArsenalInfoVO> del2=new ArrayList<>();
                    int j = 0;
                    Iterator<ArsenalInfoVO> iterator2 = peerA.getWeaponList().listIterator();
                    while (iterator2.hasNext() && j < 1) {
                        j = 2;
                        del2.add(iterator2.next());
                    }
                    peerA.getWeaponList().removeAll(del2);

                    weaponIdList.add(peerA.getWeaponList().get(0).getId());
                    List<ArsenalInfoVO> del3=new ArrayList<>();
                    int k = 0;
                    Iterator<ArsenalInfoVO> iterator3 = peerA.getWeaponList().listIterator();
                    while (iterator3.hasNext() && k < 1) {
                        k = 2;
                        del3.add(iterator3.next());
                    }
                    peerA.getWeaponList().removeAll(del3);

                    attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1012)/100));
                }
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 8:
                //desc="造成%f%%+%f伤害，该回合暴击增加%f%%"
                Map<String, Map<String, Float>> dataTemp8=peerA.getBuff();
                if(dataTemp8==null)
                    dataTemp8=new HashMap<>();

                Map<String,Float> dataMap81013=new HashMap<>();
                dataMap81013.put("count",1f);
                dataMap81013.put("useState",0f);
                dataMap81013.put("data",object.getFloat(BuffConstant.BUFF_1013)/100f);
                dataTemp8.put(BuffConstant.BUFF_1013,dataMap81013);

                peerA.setBuff(dataTemp8);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 9:
                //desc="造成%f%%+%f伤害，有%f%%概率让对方中毒，每回合损失2%生命值，持续4回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1014).floatValue())){
                    isFireBuff=true;
                    Map<String, Map<String, Float>> dataTemp9=peerB.getDebuff();
                    if(dataTemp9==null)
                        dataTemp9=new HashMap<>();
                    Map<String,Float> dataMap91014=new HashMap<>();
                    dataMap91014.put("count",5f);
                    dataMap91014.put("useState",0f);
                    dataTemp9.put(BuffConstant.BUFF_1014,dataMap91014);
                    peerB.setDebuff(dataTemp9);
                }
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 10:
                //desc="造成%f%%+%f伤害，有%f%%概率打掉对方1件武器"
                if(peerB.getWeaponList()!=null && !(peerB.getWeaponList().isEmpty()) && RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1015).floatValue())){
                    weaponIdList.add(peerB.getWeaponList().get(0).getId());
                    int i = 0;
                    List<ArsenalInfoVO> del1=new ArrayList<>();
                    Iterator<ArsenalInfoVO> iterator1 = peerA.getWeaponList().listIterator();
                    while (iterator1.hasNext() && i < 1) {
                        i = 2;
                        del1.add(iterator1.next());
                    }
                    peerA.getWeaponList().removeAll(del1);
                }
                attackCount=(int)(attackCount*(object.getDouble(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 11:
                //desc="对手造成%f%%+%f的伤害，有%f%%几率沉默对手，持续2回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1016).floatValue())){
                    Map<String, Map<String, Float>> dataTemp11=peerB.getDebuff();
                    if(dataTemp11==null)
                        dataTemp11=new HashMap<>();
                    Map<String,Float> dataMap111016=new HashMap<>();
                    dataMap111016.put("count",2f);
                    dataMap111016.put("useState",0f);
                    dataMap111016.put("data",object.getDouble(BuffConstant.BUFF_1016).floatValue());
                    dataTemp11.put(BuffConstant.BUFF_1016,dataMap111016);
                    peerB.setDebuff(dataTemp11);
                }
                attackCount=(int)(attackCount*(object.getDouble(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 12:
                //desc="吃下饼干立即回血%f%%，接下来每回合回血%f%%，持续回血3回合"
                Map<String, Map<String, Float>> dataTemp12=peerA.getBuff();
                if(dataTemp12==null)
                    dataTemp12=new HashMap<>();

                Map<String,Float> dataMap121018=new HashMap<>();
                dataMap121018.put("count",3f);
                dataMap121018.put("useState",1f);
                dataMap121018.put("data",object.getFloat(BuffConstant.BUFF_1018)/100f);
                dataTemp12.put(BuffConstant.BUFF_1018,dataMap121018);
                peerA.setBuff(dataTemp12);

                int bloodCount=(int)(peerA.getBlood()+peerA.getFullBlood()*(object.getDouble(BuffConstant.BUFF_1017)/100f));
                peerA.setBlood(bloodCount);
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 13:
                //desc="使对方造成%f%%+%f伤害，自身受到%f%%血量的伤害"
                effectSelfCount=(int)(peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1019)/100f));
                int bloodCount13=peerA.getBlood()-effectSelfCount;
                peerA.setBlood(bloodCount13);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 14:
                //desc="对对方造成%f%%+%f伤害，有%f%%概率使对方虚弱，持续2回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1020).floatValue())){
                    Map<String, Map<String, Float>> dataTemp14=peerB.getDebuff();
                    if(dataTemp14==null)
                        dataTemp14=new HashMap<>();
                    Map<String,Float> dataMap141020=new HashMap<>();
                    dataMap141020.put("count",2f);
                    dataMap141020.put("useState",0f);
                    dataMap141020.put("data",object.getDouble(BuffConstant.BUFF_1020).floatValue());
                    dataTemp14.put(BuffConstant.BUFF_1020,dataMap141020);
                    peerB.setDebuff(dataTemp14);
                }
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 15:
                //desc="对对方造成%f%%+%f伤害，有%f%%概率击晕对手2回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1005).floatValue())){
                    Map<String, Map<String, Float>> dataTemp15=peerB.getDebuff();
                    if(dataTemp15==null)
                        dataTemp15=new HashMap<>();
                    Map<String,Float> dataMap151005=new HashMap<>();
                    dataMap151005.put("count",2f);
                    dataMap151005.put("useState",0f);
                    dataTemp15.put(BuffConstant.BUFF_1005,dataMap151005);
                    peerB.setDebuff(dataTemp15);
                }
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 16:
                //desc="对对方造成%f%%+%f伤害，有%f%%概率使对手失明2回合"
                if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1021).floatValue())){
                    Map<String, Map<String, Float>> dataTemp16=peerB.getDebuff();
                    if(dataTemp16==null)
                        dataTemp16=new HashMap<>();
                    Map<String,Float> dataMap161021=new HashMap<>();
                    dataMap161021.put("count",2f);
                    dataMap161021.put("useState",0f);
                    dataTemp16.put(BuffConstant.BUFF_1021,dataMap161021);
                    peerB.setDebuff(dataTemp16);
                }

                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 17:
                //desc="对对方造成%f%%+%f伤害，减少对手%f%%当前怒气值"
                int fury=peerB.getFuryValue();
                fury=(int)(fury-fury*(object.getFloat(BuffConstant.BUFF_1022)/100f));
                peerB.setFuryValue(fury);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 18:
                //desc="对对手造成%f%%+%f伤害，获得强力buff，接下来每1回合增加%f%%攻击，持续4回合"
                Map<String, Map<String, Float>> dataTemp18=peerA.getBuff();
                if(dataTemp18==null)
                    dataTemp18=new HashMap<>();
                Map<String,Float> dataMap181024=new HashMap<>();
                dataMap181024.put("count",4f);
                dataMap181024.put("useState",0f);
                dataMap181024.put("data",object.getFloat(BuffConstant.BUFF_1024)/100f);
                dataTemp18.put(BuffConstant.BUFF_1024,dataMap181024);
                peerA.setBuff(dataTemp18);

                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 19:
                //desc="生命值回复%f%%，每次战斗只能用1次"
                int blood19=peerA.getBlood();
                //int cout=(int)peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1025)/100f);
                blood19=(int)(blood19+peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1025)/100f));
                peerA.setBlood(blood19>peerA.getFullBlood()?peerA.getFullBlood():blood19);
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 20:
                /** 对对手造成%f%%+%f伤害，50%概率在攻击时附带%f%%吸血效果 */
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                if(RandomUtil.probabilityEvent(50)){
                    int blood20=peerA.getBlood();
                    blood20=(int)(blood20+hurtCount*(object.getFloat(BuffConstant.BUFF_1026)/100f));
                    peerA.setBlood(blood20>peerA.getFullBlood()?peerA.getFullBlood():blood20);
                }
                break;
            case 21:
                /** 造成%d%%+%d伤害，自身攻击力提升%d%%，持续2回合 */
                Map<String, Map<String, Float>> dataTemp21=peerA.getBuff();
                if(dataTemp21==null)
                    dataTemp21=new HashMap<>();
                Map<String,Float> dataMap211023=new HashMap<>();
                dataMap211023.put("count",2f);
                dataMap211023.put("useState",0f);
                dataMap211023.put("data",object.getFloat(BuffConstant.BUFF_1023)/100f);
                dataTemp21.put(BuffConstant.BUFF_1023,dataMap211023);
                peerA.setBuff(dataTemp21);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                //hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
        }
        return hurtCount;
    }

}
