package com.tongzhu.user.afight;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.user.action.ActionMap;
import com.tongzhu.user.action.FightController;
import com.tongzhu.user.constant.BuffConstant;
import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.SkillConstant;
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
import org.springframework.util.CollectionUtils;

import java.util.*;

@FightController
public class SkillDataController {

    @ActionMap(key = "1")
    public int userSkill_1(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail, MonsterData monsterData){
        //1 心灵引爆 造成%d%%+%d伤害，该回合提升暴击%d
        int hurtCount=0;
        stepDetail.setSkillId(1);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp1=peerA.getBuff();
            if(dataTemp1==null)
                dataTemp1=new HashMap<>();
            Map<String,Float> dataMap11003=new HashMap<>();
            dataMap11003.put("count",1f);
            dataMap11003.put("data",object.getDouble(BuffConstant.BUFF_1013).floatValue());
            dataTemp1.put(BuffConstant.BUFF_1013,dataMap11003);

            peerA.setBuff(dataTemp1);
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "2")
    public int userSkill_2(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //组合拳  对敌人连续攻击，造成%d%%+%d伤害，有%d%%概率击晕对手1回合
        int hurtCount=0;
        stepDetail.setSkillId(2);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            boolean isCounterattack=true;//是否抵抗眩晕
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1004).floatValue())){
                Map<Integer,Integer> skillIds=new HashMap<>();
                skillIds.put(SkillConstant.SKILL_37,1);
                List<UserSkillVO> userSkills= FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
                if(!CollectionUtils.isEmpty(userSkills)){
                    SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_37,userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
                    if(skillSetting!=null){
                        if(StringUtils.isNotBlank(skillSetting.getPro())){
                            JSONObject ooc= JSON.parseObject(skillSetting.getPro());
                            if(ooc.getFloat(BuffConstant.BUFF_1043)!=null){ //1043,xx%概率抵抗眩晕  skillId:37 返魂术
                                if(RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1043).floatValue())){
                                    isCounterattack=true;
                                }else{
                                    isCounterattack=false;
                                }
                            }else{
                                isCounterattack=false;
                            }
                        }else{
                            isCounterattack=false;
                        }
                    }else{
                        isCounterattack=false;
                    }
                }else{
                    isCounterattack=false;
                }

                if(!isCounterattack){
                    Map<String, Map<String, Float>> dataTemp2=peerB.getDebuff();
                    if(dataTemp2==null)
                        dataTemp2=new HashMap<>();
                    Map<String,Float> dataMap21004=new HashMap<>();
                    dataMap21004.put("count",1f);
                    dataTemp2.put(BuffConstant.BUFF_1004,dataMap21004);
                    peerB.setDebuff(dataTemp2);

                    Map<String,Integer> buffMap2=new HashMap<>();
                    buffMap2.put("id",1);
                    buffMap2.put("amount",1);
                    monsterData.setBuff(buffMap2);
                    Map<String,Integer> map33=new HashMap<>();
                    map33.put("group",peerB.getGroup());
                    map33.put("index",peerB.getGroupIndex());
                    monsterData.setBuffUserId(map33);
                }
            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "3")
    public int userSkill_3(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //风暴之眼  对敌人造成%d伤害，给自身增加%d%%命中buff，维持3回合
        int hurtCount=0;
        stepDetail.setSkillId(3);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp3=peerA.getBuff();
            if(dataTemp3==null)
                dataTemp3=new HashMap<>();
            Map<String,Float> dataMap31007=new HashMap<>();
            dataMap31007.put("count",3f);
            dataMap31007.put("data",object.getDouble(BuffConstant.BUFF_1007).floatValue());
            dataTemp3.put(BuffConstant.BUFF_1007,dataMap31007);
            peerA.setBuff(dataTemp3);

            Map<String,Object> buffMap2=new HashMap<>();
            buffMap2.put("id",7);
            buffMap2.put("amount",3);
            stepDetail.setBuff(buffMap2);
            Map<String,Object> map33=new HashMap<>();
            map33.put("group",peerA.getGroup());
            map33.put("index",peerA.getGroupIndex());
            stepDetail.setBuffUserId(map33);

            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "4")
    public int userSkill_4(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //魅惑术  对同性造成%d%%伤害，对异性造成%d%%伤害
        int hurtCount=0;
        stepDetail.setSkillId(4);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(peerA.getSex()==peerB.getSex()){
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1008)/100f));
            }else{
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1009)/100f));
            }
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "5")
    public int userSkill_5(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //守护天使 减少所受的%d%%普攻伤害，持续3回合
        int hurtCount=0;
        stepDetail.setSkillId(5);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp5=peerA.getBuff();
            if(dataTemp5==null)
                dataTemp5=new HashMap<>();
            Map<String,Float> dataMap51010=new HashMap<>();
            dataMap51010.put("count",3f);
            dataMap51010.put("data",object.getDouble(BuffConstant.BUFF_1010).floatValue());
            dataTemp5.put(BuffConstant.BUFF_1010,dataMap51010);
            peerA.setBuff(dataTemp5);

            Map<String,Object> map33=new HashMap<>();
            map33.put("group",peerA.getGroup());
            map33.put("index",peerA.getGroupIndex());
            stepDetail.setBuffUserId(map33);

            Map<String,Object> buffMap2=new HashMap<>();
            buffMap2.put("id",10);
            buffMap2.put("amount",3);
            stepDetail.setBuff(buffMap2);

            //hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "6")
    public int userSkill_6(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 冰封魔印 %d%%概率让对方定身，不能使用近战武器攻击，持续4回合
        int hurtCount=0;
        stepDetail.setSkillId(6);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1011).floatValue())){
                boolean isCounterattack=true;//是否抵抗定身
                Map<Integer,Integer> skillIds=new HashMap<>();
                skillIds.put(SkillConstant.SKILL_39,1);
                List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
                if(CollectionUtils.isEmpty(userSkills)){
                    isCounterattack=false;
                }else{
                    SkillSetting skillSetting=FightCopyUtil.getSkillSettingBySkillIdAndLevel(SkillConstant.SKILL_39,userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
                    if(skillSetting!=null){
                        if(StringUtils.isNotBlank(skillSetting.getPro())){
                            JSONObject ooc= JSON.parseObject(skillSetting.getPro());
                            if(ooc.getFloat(BuffConstant.BUFF_1045)!=null){ //1045,%d%%概率抵抗定身  skillId:39 解除束缚
                                if(RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1045).floatValue())){
                                    isCounterattack=true;
                                }else {
                                    isCounterattack=false;
                                }
                            }else {
                                isCounterattack=false;
                            }
                        }else {
                            isCounterattack=false;
                        }
                    }else{
                        isCounterattack=false;
                    }
                }
                if(!isCounterattack){
                    Map<String, Map<String, Float>> dataTemp6=peerB.getDebuff();
                    if(dataTemp6==null)
                        dataTemp6=new HashMap<>();
                    Map<String,Float> dataMap61011=new HashMap<>();
                    dataMap61011.put("count",4f);
                    dataTemp6.put(BuffConstant.BUFF_1011,dataMap61011);
                    peerB.setDebuff(dataTemp6);

                    Map<String,Integer> map33=new HashMap<>();
                    map33.put("group",peerB.getGroup());
                    map33.put("index",peerB.getGroupIndex());
                    monsterData.setBuffUserId(map33);

                    Map<String,Integer> buffMap2=new HashMap<>();
                    buffMap2.put("id",2);
                    buffMap2.put("amount",4);
                    monsterData.setBuff(buffMap2);
                }
            }
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "7")
    public int userSkill_7(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //狂风骤雨 瞬间向对方投掷3件武器，每件武器造成%d%%的伤害
        int hurtCount=0;
        stepDetail.setSkillId(7);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_5);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            List<String> weaponIdList=new ArrayList<>();
            Map<String,Object> loseWeapon=new HashMap<>();
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

                loseWeapon.put("group",peerA.getGroup());
                loseWeapon.put("index",peerA.getGroupIndex());
                loseWeapon.put("weaponIdList",weaponIdList);
                stepDetail.setLostWeapon(loseWeapon);
            }
            attackCount+=(int)(3*(attackCount*(object.getFloat(BuffConstant.BUFF_1012)/100f)));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "8")
    public int userSkill_8(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 回旋踢 造成%d%%+%d伤害，该回合暴击增加%d%%
        int hurtCount=0;
        stepDetail.setSkillId(8);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp8=peerA.getBuff();
            if(dataTemp8==null)
                dataTemp8=new HashMap<>();

            Map<String,Float> dataMap81013=new HashMap<>();
            dataMap81013.put("count",1f);
            dataMap81013.put("data",object.getFloat(BuffConstant.BUFF_1013)/100f);
            dataTemp8.put(BuffConstant.BUFF_1013,dataMap81013);
            peerA.setBuff(dataTemp8);

            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "9")
    public int userSkill_9(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 致命感染  造成%d%%+%d伤害，有%d%%概率让对方中毒，每回合损失2%生命值，持续4回合
        int hurtCount=0;
        stepDetail.setSkillId(9);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1014).floatValue())){
                Map<String, Map<String, Float>> dataTemp9=peerB.getDebuff();
                if(dataTemp9==null)
                    dataTemp9=new HashMap<>();
                Map<String,Float> dataMap91014=new HashMap<>();
                dataMap91014.put("count",4f);
                dataTemp9.put(BuffConstant.BUFF_1014,dataMap91014);
                peerB.setDebuff(dataTemp9);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",3);
                buffMap2.put("amount",4);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "10")
    public int userSkill_10(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 近身格斗 造成%d%%+%d伤害，有%d%%概率打掉对方1件武器
        int hurtCount=0;
        stepDetail.setSkillId(10);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            List<String> weaponIdList=new ArrayList<>();
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

                Map<String,Object> buffMap2=new HashMap<>();
                buffMap2.put("id",17);
                buffMap2.put("amount",1);
                stepDetail.setBuff(buffMap2);
                //stepDetail.setWeaponIdList(weaponIdList);
                Map<String,Object> lostWeapon=new HashMap<>();
                lostWeapon.put("group",peerB.getGroup());
                lostWeapon.put("index",peerB.getGroupIndex());
                lostWeapon.put("weaponIdList",weaponIdList);
                stepDetail.setLostWeapon(lostWeapon);

                Map<String,Object> map33=new HashMap<>();
                map33.put("group",peerA.getGroup());
                map33.put("index",peerA.getGroupIndex());
                stepDetail.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getDouble(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "11")
    public int userSkill_11(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 奥术诅咒 对手造成%d%%+%d的伤害，有%d%%几率沉默对手，持续2回合
        int hurtCount=0;
        stepDetail.setSkillId(11);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1016).floatValue())){
                Map<String, Map<String, Float>> dataTemp11=peerB.getDebuff();
                if(dataTemp11==null)
                    dataTemp11=new HashMap<>();
                Map<String,Float> dataMap111016=new HashMap<>();
                dataMap111016.put("count",2f);
                dataMap111016.put("data",object.getDouble(BuffConstant.BUFF_1016).floatValue());
                dataTemp11.put(BuffConstant.BUFF_1016,dataMap111016);
                peerB.setDebuff(dataTemp11);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",4);
                buffMap2.put("amount",2);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getDouble(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "12")
    public int userSkill_12(Combatant peerA, Combatant peerB, UserSkill useSkill, FightParameter fightParameter, FightStepDetailVO stepDetail){
        // 活力饼干  吃下饼干立即回血%d%%，接下来每回合回血%d%%，持续回血3回合

        stepDetail.setSkillId(12);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
        Map<String,Object> map33=new HashMap<>();
        map33.put("group",peerA.getGroup());
        map33.put("index",peerA.getGroupIndex());
        stepDetail.setBuffUserId(map33);
        Map<String,Object> buffMap2=new HashMap<>();
        buffMap2.put("id",27);
        buffMap2.put("amount",1);
        stepDetail.setBuff(buffMap2);

        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp12=peerA.getBuff();
            if(dataTemp12==null)
                dataTemp12=new HashMap<>();
            Map<String,Float> dataMap121018=new HashMap<>();
            dataMap121018.put("count",4f);
            dataMap121018.put("data",object.getFloat(BuffConstant.BUFF_1018)/100f);
            dataTemp12.put(BuffConstant.BUFF_1018,dataMap121018);
            peerA.setBuff(dataTemp12);
            int addBloodCount=(int)(peerA.getFullBlood()*(object.getDouble(BuffConstant.BUFF_1017)/100f));

            int bloodCount=(peerA.getBlood()+addBloodCount)>peerA.getFullBlood()?peerA.getFullBlood():peerA.getBlood()+addBloodCount;
            peerA.setBlood(bloodCount);
            fightParameter.setBloodCount((peerA.getBlood()+addBloodCount)>peerA.getFullBlood()?peerA.getFullBlood()-peerA.getBlood():addBloodCount);
            Map<String,Object> addBloodMap=new HashMap<>();
            addBloodMap.put("group",peerA.getGroup());
            addBloodMap.put("index",peerA.getGroupIndex());
            addBloodMap.put("value",(peerA.getBlood()+addBloodCount)>peerA.getFullBlood()?peerA.getFullBlood()-peerA.getBlood():addBloodCount);
            stepDetail.setBloodCount(addBloodMap);
            FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        }
        return 0;
    }

    @ActionMap(key = "13")
    public int userSkill_13(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //背水一战  使对方造成%d%%+%d伤害，自身受到%d%%血量的伤害
        int hurtCount=0;
        stepDetail.setSkillId(13);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            int effectSelfCount=(int)(peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1019)/100f));
            int bloodCount13=peerA.getBlood()-effectSelfCount;
            peerA.setBlood(bloodCount13);

            Map<String,Object> buffMap2=new HashMap<>();
            buffMap2.put("id",25);
            buffMap2.put("amount",1);
            stepDetail.setBuff(buffMap2);
            Map<String,Object> map33=new HashMap<>();
            map33.put("group",peerA.getGroup());
            map33.put("index",peerA.getGroupIndex());
            stepDetail.setBuffUserId(map33);

            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "14")
    public int userSkill_14(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        //梦境缠绕 对对方造成%d%%+%d伤害，有%d%%概率使对方虚弱，持续2回合
        int hurtCount=0;
        stepDetail.setSkillId(14);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1020).floatValue())){
                Map<String, Map<String, Float>> dataTemp14=peerB.getDebuff();
                if(dataTemp14==null)
                    dataTemp14=new HashMap<>();
                Map<String,Float> dataMap141020=new HashMap<>();
                dataMap141020.put("count",2f);
                dataMap141020.put("data",object.getDouble(BuffConstant.BUFF_1020).floatValue());
                dataTemp14.put(BuffConstant.BUFF_1020,dataMap141020);
                peerB.setDebuff(dataTemp14);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",5);
                buffMap2.put("amount",2);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "15")
    public int userSkill_15(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 雷暴冲击  对对方造成%d%%+%d伤害，有%d%%概率击晕对手2回合
        int hurtCount=0;
        stepDetail.setSkillId(15);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1005).floatValue())){
                Map<String, Map<String, Float>> dataTemp15=peerB.getDebuff();
                if(dataTemp15==null)
                    dataTemp15=new HashMap<>();
                Map<String,Float> dataMap151005=new HashMap<>();
                dataMap151005.put("count",2f);
                dataTemp15.put(BuffConstant.BUFF_1005,dataMap151005);
                peerB.setDebuff(dataTemp15);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",1);
                buffMap2.put("amount",2);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);

            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "16")
    public int userSkill_16(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 烈阳打击 对对方造成%d%%+%d伤害，有%d%%概率使对手失明2回合
        int hurtCount=0;
        stepDetail.setSkillId(16);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(RandomUtil.probabilityEvent(object.getDouble(BuffConstant.BUFF_1021).floatValue())){
                Map<String, Map<String, Float>> dataTemp16=peerB.getDebuff();
                if(dataTemp16==null)
                    dataTemp16=new HashMap<>();
                Map<String,Float> dataMap161021=new HashMap<>();
                dataMap161021.put("count",2f);
                dataTemp16.put(BuffConstant.BUFF_1021,dataMap161021);
                peerB.setDebuff(dataTemp16);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",6);
                buffMap2.put("amount",2);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "17")
    public int userSkill_17(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 破坏彗星 对对方造成%d%%+%d伤害，减少对手%d%%当前怒气值
        int hurtCount=0;
        stepDetail.setSkillId(17);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            if(peerB.getFuryValue()!=null){
                int fury=peerB.getFuryValue();
                fury=(int)(fury-fury*(object.getFloat(BuffConstant.BUFF_1022)/100f));
                peerB.setFuryValue(fury);

                Map<String,Integer> buffMap2=new HashMap<>();
                buffMap2.put("id",18);
                buffMap2.put("amount",1);
                monsterData.setBuff(buffMap2);
                Map<String,Integer> map33=new HashMap<>();
                map33.put("group",peerB.getGroup());
                map33.put("index",peerB.getGroupIndex());
                monsterData.setBuffUserId(map33);
            }
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "18")
    public int userSkill_18(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 真意百破道 对对手造成%d%%+%d伤害，获得强力buff，接下来每1回合增加%d%%攻击，持续4回合
        int hurtCount=0;
        stepDetail.setSkillId(18);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp18=peerA.getBuff();
            if(dataTemp18==null)
                dataTemp18=new HashMap<>();
            Map<String,Float> dataMap181024=new HashMap<>();
            dataMap181024.put("count",4f);
            dataMap181024.put("data",object.getFloat(BuffConstant.BUFF_1024)/100f);
            dataTemp18.put(BuffConstant.BUFF_1024,dataMap181024);
            peerA.setBuff(dataTemp18);

            Map<String,Object> buffMap2=new HashMap<>();
            buffMap2.put("id",9);
            buffMap2.put("amount",4);
            stepDetail.setBuff(buffMap2);
            Map<String,Object> map33=new HashMap<>();
            map33.put("group",peerA.getGroup());
            map33.put("index",peerA.getGroupIndex());
            stepDetail.setBuffUserId(map33);

            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "19")
    public int userSkill_19(Combatant peerA, Combatant peerB, UserSkill useSkill, FightParameter fightParameter, FightStepDetailVO stepDetail){
        // 圣光术 生命值回复%d%%，每次战斗只能用1次
        stepDetail.setSkillId(19);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
        Map<String,Object> map33=new HashMap<>();
        map33.put("group",peerA.getGroup());
        map33.put("index",peerA.getGroupIndex());
        stepDetail.setBuffUserId(map33);
        Map<String,Object> buffMap2=new HashMap<>();
        buffMap2.put("id",11);
        buffMap2.put("amount",1);
        stepDetail.setBuff(buffMap2);

        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            int blood19=peerA.getBlood();
            int addBlood=(int)(peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1025)/100f));
            fightParameter.setBloodCount(addBlood);
            blood19=blood19+addBlood;
            peerA.setBlood(blood19>peerA.getFullBlood()?peerA.getFullBlood():blood19);

            Map<String,Object> addBloodMap=new HashMap<>();
            addBloodMap.put("group",peerA.getGroup());
            addBloodMap.put("index",peerA.getGroupIndex());
            addBloodMap.put("value",(peerA.getBlood()+addBlood)>peerA.getFullBlood()?peerA.getFullBlood()-peerA.getBlood():addBlood);
            stepDetail.setAddBlood(addBloodMap);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),false);
        return 0;
    }

    @ActionMap(key = "20")
    public int userSkill_20(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 嗜血一击 对对手造成%d%%+%d伤害，50%概率在攻击时附带%d%%吸血效果
        int hurtCount=0;
        stepDetail.setSkillId(20);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
            if(RandomUtil.probabilityEvent(50)){
                int blood20=peerA.getBlood();
                int addBlood=(int)(hurtCount*(object.getFloat(BuffConstant.BUFF_1026)/100f));
                blood20=blood20+addBlood;
                peerA.setBlood(blood20>peerA.getFullBlood()?peerA.getFullBlood():blood20);

                Map<String,Object> map33=new HashMap<>();
                map33.put("group",peerA.getGroup());
                map33.put("index",peerA.getGroupIndex());
                stepDetail.setBuffUserId(map33);

                Map<String,Object> buffMap2=new HashMap<>();
                buffMap2.put("id",19);
                buffMap2.put("amount",1);
                stepDetail.setBuff(buffMap2);

                Map<String,Object> addBloodMap=new HashMap<>();
                addBloodMap.put("group",peerA.getGroup());
                addBloodMap.put("index",peerA.getGroupIndex());
                addBloodMap.put("value",(peerA.getBlood()+addBlood)>peerA.getFullBlood()?peerA.getFullBlood()-peerA.getBlood():addBlood);
                stepDetail.setAddBlood(addBloodMap);
            }
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "21")
    public int userSkill_21(Combatant peerA, Combatant peerB, UserSkill useSkill, Integer attackCount, Integer defenceCount, FightStepDetailVO stepDetail,MonsterData monsterData){
        // 神之力量 造成%d%%+%d伤害，自身攻击力提升%d%%，持续2回合
        int hurtCount=0;
        stepDetail.setSkillId(21);
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
        SkillSetting setting = FightCopyUtil.getSkillSettingBySkillIdAndLevel(useSkill.getSkillId(), useSkill.getLevel(),peerA.getSkillSettingList());
        if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
            JSONObject object = JSON.parseObject(setting.getPro());
            Map<String, Map<String, Float>> dataTemp21=peerA.getBuff();
            if(dataTemp21==null)
                dataTemp21=new HashMap<>();
            Map<String,Float> dataMap211023=new HashMap<>();
            dataMap211023.put("count",2f);
            dataMap211023.put("data",object.getFloat(BuffConstant.BUFF_1023)/100f);
            dataTemp21.put(BuffConstant.BUFF_1023,dataMap211023);
            peerA.setBuff(dataTemp21);
            attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
            hurtCount=FightCopyUtil.computeHurtCount(peerA,attackCount,defenceCount);
        }
        FightCopyUtil.freezeSkill(peerA,useSkill.getSkillId(),true);
        return hurtCount;
    }

    @ActionMap(key = "big")
    public int userSkill_big(Combatant peerA,Combatant peerB,FightStepDetailVO stepDetail) throws Exception {
        switch (peerA.getRoleId()){
            case 1: case 2://ws
                stepDetail.setSkillId(53);
                stepDetail.setActionName("卫士大招");
                break;
            case 3: case 4://ck
                stepDetail.setSkillId(54);
                stepDetail.setActionName("刺客大招");
                break;
            case 5: case 6://gj
                stepDetail.setSkillId(55);
                stepDetail.setActionName("工匠大招");
                break;
            case 7: case 8://fs
                stepDetail.setSkillId(56);
                stepDetail.setActionName("法师大招");
                break;
        }
        return 0;
    }
}
