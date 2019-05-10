package com.tongzhu.user.util;

import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightCopyStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FightingCopyUtil {

    private static Logger logger= LoggerFactory.getLogger(FightingCopyUtil.class);

    /**
     *
     * @param peerA
     * @param peerB
     * @param sp   类型 1：普通攻击   2：技能攻击
     * @param actId
     *        53--56：使用大招攻击
     *        991：普通攻击
     *        992：投掷武器
     *        993：徒手攻击
     *        1：使用了技能1
     *        2：使用了技能2
     *        ......
     *        52：使用了技能52
     * @param mode  模式 1-1：命中  1-2：闪避  1-3：格挡
     *                  2-1:释放  2-2：暴击  2-3：触发
     * @param hurtCount
     * @param stepDetail
     */
    public static void generateFightingStep(Combatant peerA, Combatant peerB, int sp, int actId, boolean bj, int mode, Integer hurtCount,FightCopyStepDetailVO stepDetail,Object ... args){
        Map<String,Object> map11=new HashMap<>();
        Map<String,Object> map22=new HashMap<>();
        map11.put("group",peerA.getGroup());
        map11.put("index",peerA.getGroupIndex());
        stepDetail.setAttacker(map11);
        map22.put("group",peerB.getGroup());
        map22.put("index",peerB.getGroupIndex());
        stepDetail.setDefender(map22);

        stepDetail.setSkillId(actId);
        switch (sp){
            /**
             * type
             *  1.[玩家a]使用了技能xxx，对[玩家b]造成了xxx点伤害（暴击伤害），动画效果id
             *  2.[玩家a]使用xx对[玩家b]进行攻击，造成了xxx点伤害（暴击伤害）
             *  3.[玩家a]向[玩家b]投掷了一件xxx，造成了xxx点伤害（暴击伤害）
             *  4.[玩家a]使用xxx对[玩家b]进行攻击，被[玩家B]躲开了（格挡了）
             *  5.[玩家a]瞬间向[玩家b]投掷了3件武器，造成了xxx点伤害（暴击伤害）
             *  6.[玩家a]向[玩家b]投掷了一件xxx，被[玩家B]躲开了（格挡了）
             *  7.[玩家a]使用了技能xxx，动画效果id
             *  99: 战斗结束
             *  100:战斗中玩家换人
             *  98：回合开始
             *  actionId
             *   1: 击晕
             *   2: 定身
             *   3: 中毒
             *   4: 沉默
             *   5: 虚弱
             *   6: 失明
             *   7: 命中提升了
             *   8: 暴击提升
             *   9: 攻击力提升了
             *  10: 铁布衫
             *  11: 生命值回复
             *  12: 复活
             *  13: 抵抗眩晕
             *  14: 中毒暂缓
             *  15: 抵抗定身
             *  16: 格挡攻击
             *  17: 打掉武器
             *  18: 怒气下降
             *  19: 吸血
             *  20: 狂风暴雨
             *  21: 攻击miss
             *  22: 净化
             *  23: 菩提之光
             *  24: 反噬伤害
             *  25: 反击
             *  26: 先发制人
             */
            case 1:
                switch (actId){
                    case 53: case 54: case 55: case 56:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        stepDetail.setBj(false);
                        if(peerA.getRoleId()==1 || peerA.getRoleId()==2){
                            stepDetail.setSkillId(53);
                            stepDetail.setActionName("卫士大招");
                        }
                        if(peerA.getRoleId()==3 || peerA.getRoleId()==4){
                            stepDetail.setSkillId(54);
                            stepDetail.setActionName("刺客大招");
                        }
                        if(peerA.getRoleId()==5 || peerA.getRoleId()==6){
                            stepDetail.setSkillId(55);
                            stepDetail.setActionName("工匠大招");
                        }
                        if(peerA.getRoleId()==7 || peerA.getRoleId()==8){
                            stepDetail.setSkillId(56);
                            stepDetail.setActionName("法师大招");
                        }
                        stepDetail.setHurtCount(hurtCount);
                        break;
                    case 994:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        stepDetail.setBj(false);
                        stepDetail.setSkillId(null);
                        stepDetail.setActionName("普通攻击");
                        stepDetail.setHurtCount(hurtCount);
                        break;
                    case 991: case 992: case 993:
                        if(bj){
                            stepDetail.setBj(true);
                            if(actId==SkillConstant.SKILL_991){
                                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                                if(peerA.getWornWeapon()==null && args!=null)
                                    stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                else
                                    stepDetail.setActionName(peerA.getWornWeapon().getName());
                                stepDetail.setSkillId(null);
                            }else if(actId==SkillConstant.SKILL_992){
                                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_3);
                                if(peerA.getWornWeapon()==null && args!=null)
                                    stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                else
                                    stepDetail.setActionName(peerA.getWornWeapon().getName());
                                stepDetail.setSkillId(null);
                            }else{
                                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                                stepDetail.setActionName("拳头");
                                stepDetail.setSkillId(null);
                            }
                            stepDetail.setHurtCount(hurtCount);
                        }else {
                            stepDetail.setBj(false);
                            switch (mode){
                                case 1:
                                    if(actId==SkillConstant.SKILL_991){
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                                        if(peerA.getWornWeapon()==null && args!=null){
                                            stepDetail.setActionName(((Map<String,String>)args[0]).get("name"));
                                            List<String> weaponIdList=new ArrayList<>();
                                            weaponIdList.add(((Map<String,String>)args[0]).get("id"));
                                            stepDetail.setWeaponIdList(weaponIdList);
                                        }
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setSkillId(null);
                                    }else if(actId==SkillConstant.SKILL_992){
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_3);
                                        if(peerA.getWornWeapon()==null && args!=null)
                                            stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setSkillId(null);
                                    }else{
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_2);
                                        stepDetail.setActionName("拳头");
                                        stepDetail.setSkillId(null);
                                    }
                                    stepDetail.setHurtCount(hurtCount);
                                    break;
                                case 2:
                                    stepDetail.setCategory(1);
                                    Map<String,Object> map2=new HashMap<>();
                                    map2.put("group",peerB.getGroup());
                                    map2.put("index",peerB.getGroupIndex());
                                    stepDetail.setBuffUserId(map2);
                                    Map<String,Object> buffMap1=new HashMap<>();
                                    buffMap1.put("id",21);
                                    buffMap1.put("amount",1);
                                    stepDetail.setBuff(buffMap1);
                                    if(actId==SkillConstant.SKILL_991){
                                        if(peerA.getWornWeapon()==null && args!=null)
                                            stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                                        stepDetail.setSkillId(null);
                                    }else if(actId==SkillConstant.SKILL_992) {
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_6);
                                        if(peerA.getWornWeapon()==null && args!=null)
                                            stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setSkillId(null);
                                    }else {
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                                        stepDetail.setActionName("拳头");
                                        stepDetail.setSkillId(null);
                                    }
                                    break;
                                case 3:
                                    stepDetail.setCategory(2);
                                    Map<String,Object> map23=new HashMap<>();
                                    map23.put("group",peerB.getGroup());
                                    map23.put("index",peerB.getGroupIndex());
                                    stepDetail.setBuffUserId(map23);
                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",16);
                                    buffMap2.put("amount",1);
                                    stepDetail.setBuff(buffMap2);
                                    if(actId==SkillConstant.SKILL_991){
                                        if(peerA.getWornWeapon()==null && args!=null)
                                            stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                                        stepDetail.setSkillId(null);
                                    }else if(actId==SkillConstant.SKILL_992) {
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_6);
                                        if(peerA.getWornWeapon()==null && args!=null)
                                            stepDetail.setActionName(((HashMap<String,String>)args[0]).get("name"));
                                        else
                                            stepDetail.setActionName(peerA.getWornWeapon().getName());
                                        stepDetail.setSkillId(null);
                                    }else{
                                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_4);
                                        stepDetail.setActionName("拳头");
                                        stepDetail.setSkillId(null);
                                    }
                                    break;
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (actId){
                    case 1: case 2: case 3: case 4: case 8: case 9: case 10: case 11: case 13: case 14: case 15: case 16: case 17: case 18: case 20: case 51: case 52:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        switch (mode){
                                case 1:
                                    stepDetail.setBj(bj);
                                    stepDetail.setHurtCount(hurtCount);
                                    break;
                                case 2:
                                    stepDetail.setBj(bj);
                                    stepDetail.setHurtCount(hurtCount);
                                    break;
                                case 3:
                                    stepDetail.setBj(bj);
                                    stepDetail.setHurtCount(hurtCount);
                                    if(actId==SkillConstant.SKILL_2){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",1);
                                        buffMap2.put("amount",1);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_3){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",7);
                                        buffMap2.put("amount",3);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerA.getGroup());
                                        map33.put("index",peerA.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_9){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",3);
                                        buffMap2.put("amount",4);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_10){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",17);
                                        buffMap2.put("amount",1);
                                        stepDetail.setBuff(buffMap2);
                                        stepDetail.setWeaponIdList((List<String>)args[0]);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerA.getGroup());
                                        map33.put("index",peerA.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_11){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",4);
                                        buffMap2.put("amount",2);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_13){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",25);
                                        buffMap2.put("amount",1);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerA.getGroup());
                                        map33.put("index",peerA.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_14){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",5);
                                        buffMap2.put("amount",2);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_15){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",1);
                                        buffMap2.put("amount",2);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_16){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",6);
                                        buffMap2.put("amount",2);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_17){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",18);
                                        buffMap2.put("amount",1);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerB.getGroup());
                                        map33.put("index",peerB.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }
                                    if(actId==SkillConstant.SKILL_18){
                                        Map<String,Object> buffMap2=new HashMap<>();
                                        buffMap2.put("id",9);
                                        buffMap2.put("amount",4);
                                        stepDetail.setBuff(buffMap2);
                                        Map<String,Object> map33=new HashMap<>();
                                        map33.put("group",peerA.getGroup());
                                        map33.put("index",peerA.getGroupIndex());
                                        stepDetail.setBuffUserId(map33);
                                    }

                                    break;
                            }
                        break;
                    case 5: case 12: case 19: case 21: case 22:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                        stepDetail.setBj(bj);
                        switch (mode){
                            case 3:
                                if(actId==SkillConstant.SKILL_5){
                                    Map<String,Object> map33=new HashMap<>();
                                    map33.put("group",peerA.getGroup());
                                    map33.put("index",peerA.getGroupIndex());
                                    //stepDetail.setEffectOpponent(false);
                                    stepDetail.setBuffUserId(map33);

                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",10);
                                    buffMap2.put("amount",3);
                                    stepDetail.setBuff(buffMap2);
                                }
                                if(actId== SkillConstant.SKILL_12){
                                    Map<String,Object> map33=new HashMap<>();
                                    map33.put("group",peerA.getGroup());
                                    map33.put("index",peerA.getGroupIndex());
                                    stepDetail.setBuffUserId(map33);

                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",27);
                                    buffMap2.put("amount",1);
                                    stepDetail.setBuff(buffMap2);
                                }
                                if(actId==SkillConstant.SKILL_19){
                                    Map<String,Object> map33=new HashMap<>();
                                    map33.put("group",peerA.getGroup());
                                    map33.put("index",peerA.getGroupIndex());
                                    stepDetail.setBuffUserId(map33);

                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",11);
                                    buffMap2.put("amount",1);
                                    stepDetail.setBuff(buffMap2);
                                }
                                if(actId==SkillConstant.SKILL_21){
                                    Map<String,Object> map33=new HashMap<>();
                                    map33.put("group",peerA.getGroup());
                                    map33.put("index",peerA.getGroupIndex());
                                    stepDetail.setBuffUserId(map33);

                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",9);
                                    buffMap2.put("amount",2);
                                    stepDetail.setBuff(buffMap2);
                                }
                                if(actId==SkillConstant.SKILL_22){
                                    peerA.setBlood(1);
                                    Map<String,Object> map33=new HashMap<>();
                                    map33.put("group",peerA.getGroup());
                                    map33.put("index",peerA.getGroupIndex());
                                    stepDetail.setBuffUserId(map33);

                                    Map<String,Object> buffMap2=new HashMap<>();
                                    buffMap2.put("id",12);
                                    buffMap2.put("amount",1);
                                    stepDetail.setBuff(buffMap2);
                                }
                                break;
                        }
                        break;
                    case 6:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        stepDetail.setBj(bj);
                        if(mode==3){
                            Map<String,Object> map33=new HashMap<>();
                            map33.put("group",peerB.getGroup());
                            map33.put("index",peerB.getGroupIndex());
                            stepDetail.setBuffUserId(map33);

                            Map<String,Object> buffMap2=new HashMap<>();
                            buffMap2.put("id",2);
                            buffMap2.put("amount",4);
                            stepDetail.setBuff(buffMap2);
                        }
                        break;
                    case 7:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_5);
                        if(mode==1){
                            stepDetail.setBj(false);
                            stepDetail.setHurtCount(hurtCount);
                        }
                        if(mode==2){
                            stepDetail.setBj(true);
                            stepDetail.setHurtCount(hurtCount);
                        }
                        break;
                    case 30:
                        stepDetail.setBj(false);
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        Map<String,Object> map33=new HashMap<>();
                        map33.put("group",peerB.getGroup());
                        map33.put("index",peerB.getGroupIndex());
                        stepDetail.setBuffUserId(map33);

                        Map<String,Object> buffMap2=new HashMap<>();
                        buffMap2.put("id",25);
                        buffMap2.put("amount",1);
                        stepDetail.setBuff(buffMap2);

                        stepDetail.setHurtCount(hurtCount);
                        break;
                    case 32:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                        Map<String,Object> map332=new HashMap<>();
                        map332.put("group",peerA.getGroup());
                        map332.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map332);

                        Map<String,Object> buffMap232=new HashMap<>();
                        buffMap232.put("id",12);
                        buffMap232.put("amount",1);
                        stepDetail.setBuff(buffMap232);
                        break;
                    case 34:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                        Map<String,Object> map334=new HashMap<>();
                        map334.put("group",peerA.getGroup());
                        map334.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map334);

                        Map<String,Object> buffMap234=new HashMap<>();
                        buffMap234.put("id",12);
                        buffMap234.put("amount",4);
                        stepDetail.setBuff(buffMap234);
                        //stepDetail.setHurtCount(0);
                        break;
                    case 33:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                        Map<String,Object> map333=new HashMap<>();
                        map333.put("group",peerA.getGroup());
                        map333.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map333);

                        Map<String,Object> buffMap233=new HashMap<>();
                        buffMap233.put("id",26);
                        buffMap233.put("amount",1);
                        stepDetail.setBuff(buffMap233);
                        //stepDetail.setHurtCount(0);
                        break;
                    case 37:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        Map<String,Object> map337=new HashMap<>();
                        map337.put("group",peerA.getGroup());
                        map337.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map337);

                        Map<String,Object> buffMap237=new HashMap<>();
                        buffMap237.put("id",13);
                        buffMap237.put("amount",1);
                        stepDetail.setBuff(buffMap237);

                        //stepDetail.setHurtCount(0);
                        break;
                    case 38:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_7);
                        Map<String,Object> map338=new HashMap<>();
                        map338.put("group",peerA.getGroup());
                        map338.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map338);

                        Map<String,Object> buffMap238=new HashMap<>();
                        buffMap238.put("id",14);
                        buffMap238.put("amount",3);
                        stepDetail.setBuff(buffMap238);

                        //stepDetail.setHurtCount(0);
                        break;
                    case 39:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        Map<String,Object> map339=new HashMap<>();
                        map339.put("group",peerA.getGroup());
                        map339.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map339);

                        Map<String,Object> buffMap239=new HashMap<>();
                        buffMap239.put("id",15);
                        buffMap239.put("amount",1);
                        stepDetail.setBuff(buffMap239);
                        //stepDetail.setHurtCount(0);
                        break;
                    case 40:
                        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_1);
                        Map<String,Object> map340=new HashMap<>();
                        map340.put("group",peerA.getGroup());
                        map340.put("index",peerA.getGroupIndex());
                        stepDetail.setBuffUserId(map340);

                        Map<String,Object> buffMap240=new HashMap<>();
                        buffMap240.put("id",16);
                        buffMap240.put("amount",1);
                        stepDetail.setBuff(buffMap240);
                        //stepDetail.setHurtCount(0);
                        break;
                }
                break;
        }
    }

    public static  List<UserSkillVO> findUserSkillByBuffIds(Combatant combatant,Map<Integer,Integer> skillIds){
        List<UserSkillVO> result=new ArrayList<>();
        List<UserSkillVO> skillVOList=combatant.getSkillList();
        skillVOList.forEach(skill->{
            if(skillIds.containsKey(skill.getSkillId()) && (skill.getFreezeCount()==null ||skill.getFreezeCount()==0)){
                result.add(skill);
            }
        });
        return result;
    }

    public static void dealToxicosisCount(Combatant peerA,Integer toxicosisCount,FightCopyStepDetailVO stepDetail){
        Map<String,Object> toxicosisMap=new HashMap<>();
        toxicosisMap.put("group",peerA.getGroup());
        toxicosisMap.put("index",peerA.getGroupIndex());
        toxicosisMap.put("value",toxicosisCount);
        stepDetail.setToxicosisCount(toxicosisMap);
        toxicosisCount=0;
    }

    public static void dealBloodCount(Combatant peerA,Integer bloodCount,FightCopyStepDetailVO stepDetail){
        Map<String,Object> bloodCountMap=new HashMap<>();
        bloodCountMap.put("group",peerA.getGroup());
        bloodCountMap.put("index",peerA.getGroupIndex());
        bloodCountMap.put("value",bloodCount);
        stepDetail.setBloodCount(bloodCountMap);
        bloodCount=0;
    }
}
