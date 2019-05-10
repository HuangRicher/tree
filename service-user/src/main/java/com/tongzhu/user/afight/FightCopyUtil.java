package com.tongzhu.user.afight;

import com.alibaba.fastjson.JSONArray;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.user.constant.MonsterConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.domain.EquipmentInfoVO;
import com.tongzhu.user.model.Monster;
import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class FightCopyUtil {

    public static void subCountOne(Map<String, Map<String, Float>> dataMap){
        Iterator<Map.Entry<String,Map<String,Float>>> it = dataMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,Map<String,Float>> entry=it.next();
            Map<String,Float> data=entry.getValue();
            float count=data.get("count");
            if(count>0){
                count=count-1;
                if(count>0){
                    data.put("count",count);
                }else{
                    it.remove();
                }
            }
        }
    }

    public static void reduceFreezeCount(List<UserSkillVO> skillVOList){
        if (!skillVOList.isEmpty()) {
            Iterator<UserSkillVO> it = skillVOList.iterator();
            while(it.hasNext()){
                UserSkillVO userSkillVO=it.next();
                if (userSkillVO.getFreezeCount()!=null && userSkillVO.getFreezeCount() > 0 && userSkillVO.getSkillId()!=19) {
                    int count = userSkillVO.getFreezeCount();
                    userSkillVO.setFreezeCount(count - 1);
                }
            }
        }
    }

    public static void addAward(Map<String,Integer> awardMap, Combatant peer){
        if(peer.getExpAward()!=null){
            if(awardMap.get(GoodsConstant.GOODS_EXP)!=null){
                int exp=awardMap.get(GoodsConstant.GOODS_EXP);
                awardMap.put(GoodsConstant.GOODS_EXP,peer.getExpAward()+exp);
            }else{
                awardMap.put(GoodsConstant.GOODS_EXP,peer.getExpAward());
            }
        }
        if(StringUtils.isNotBlank(peer.getMonster().getDrop())){
            JSONArray array=JSONArray.parseArray(peer.getMonster().getDrop());
            for(int i=0;i<array.size();i++){
                Map<String, Object> map=array.getJSONObject(i);
                if(map.get("rate")!=null && RandomUtil.probabilityEvent((int)map.get("rate"))){
                    for(Map.Entry<String,Object> entry:map.entrySet()){
                        String key=entry.getKey();
                        if(!key.equals("rate")){
                            if(awardMap.get(key)!=null && awardMap.get(key)>0){
                                awardMap.put(key,awardMap.get(key)+(int)entry.getValue());
                            }else{
                                awardMap.put(key,(int)entry.getValue());
                            }
                        }
                    }
                }else{
                    if(map.get("rate")==null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String key = entry.getKey();
                            if (awardMap.get(key) != null && awardMap.get(key) > 0) {
                                awardMap.put(key, awardMap.get(key) + (int) entry.getValue());
                            } else {
                                awardMap.put(key, (int) entry.getValue());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void removeCombatant(List<Combatant>  combatantList,int index){
        ListIterator<Combatant> it=combatantList.listIterator();
        while (it.hasNext()){
            if(index==it.nextIndex()){
                it.next();
                it.remove();
                break;
            }else{
                it.next();
            }
        }
    }

    public static void removeArsenalInfoVO(List<ArsenalInfoVO>  list,ArsenalInfoVO arsenalInfoVO){
        if(!CollectionUtils.isEmpty(list)){
            ListIterator<ArsenalInfoVO> it=list.listIterator();
            while (it.hasNext()){
                if(arsenalInfoVO.getGoodsId().equals(it.next().getGoodsId())){
                    //it.next();
                    it.remove();
                    break;
                }
            }
        }

    }

    /**
     * 计算暴击
     * @param peer
     * @return
     */
    public static int getUseCrrit(Combatant peer, ArsenalInfoVO arsenalInfoVO){
        int critCount=0;

        if(peer.getMonster()==null){
            List<EquipmentInfoVO> equipments=peer.getEquipmentList();
            for(EquipmentInfoVO equipment:equipments){
                critCount+=equipment.getCrit().intValue();
            }
            RoleLevelSetting setting=peer.getRoleLevelSetting();
            if(setting!=null)
                critCount+=setting.getCkCritical()+setting.getFsCritical()+setting.getGjCritical()+setting.getWsCritical();

            if(arsenalInfoVO!=null)
                critCount+=arsenalInfoVO.getCrit().intValue();

            critCount=(int)(critCount*(1+peer.getAddCritical()));
        }else{
            critCount+=peer.getMonster().getCritical();
        }
        if(peer.getBuilding()!=null){
            critCount+=peer.getBuilding().getCritical();
        }
        return critCount;
    }

    /**
     * 计算抗暴击
     * @param peer
     * @return
     */
    public static int getUseDefenceCrit(Combatant peer,ArsenalInfoVO arsenalInfoVO){
        int defenceCritCount=0;
        if(peer.getMonster()==null){
            List<EquipmentInfoVO> equipments=peer.getEquipmentList();
            for(EquipmentInfoVO equipment:equipments){
                defenceCritCount+=equipment.getDefenseCrit().intValue();
            }
            RoleLevelSetting setting=peer.getRoleLevelSetting();
            if(setting!=null)
                defenceCritCount+=setting.getCkDcritical()+setting.getFsDcritical()+setting.getGjDcritical()+setting.getWsDcritical();

            if(arsenalInfoVO!=null)
                defenceCritCount+=arsenalInfoVO.getDefenseCrit().intValue();

            defenceCritCount=(int)(defenceCritCount*(1+peer.getAddDisCritical()));
        }else{
            defenceCritCount+=peer.getMonster().getDcritical();
        }
        if(peer.getBuilding()!=null){
            defenceCritCount+=peer.getBuilding().getDcritical();
        }
        return defenceCritCount;
    }

    public static void freezeSkill(Combatant peer, Integer skillId,boolean canUse){
        List<UserSkillVO> skillVOS=peer.getSkillList();
        for(UserSkillVO skillVO:skillVOS){
            if(skillVO.getSkillId()==skillId){
                if(canUse){
                    skillVO.setFreezeCount(skillVO.getCoolingSetting());
                }else {
                    skillVO.setCanUse(false);
                }

            }
        }
    }

    /**
     * 计算攻击力
     * @param peer
     * @return
     */
    public static int getUserAggressivity(Combatant peer, ArsenalInfoVO arsenalInfoVO){
        int attackCount=0;
        if(peer.getMonster()==null){
            List<EquipmentInfoVO> equipments=peer.getEquipmentList();
            for(EquipmentInfoVO equipment:equipments){
                attackCount+=(int)(equipment.getAttack()+equipment.getSpellAttacks());
            }
            RoleLevelSetting setting=peer.getRoleLevelSetting();
            if(setting!=null){
                switch (peer.getRoleId()){
                    case 1: case 2://卫士
                        attackCount+=setting.getWsPhAtk()+setting.getWsMfAtk();
                        break;
                    case 3: case 4://刺客
                        attackCount+=setting.getCkPhAtk()+setting.getCkMfAtk();
                        break;
                    case 5: case 6://工匠
                        attackCount+=setting.getGjPhAtk()+setting.getGjMfAtk();
                        break;
                    case 7: case 8://法士
                        attackCount+=setting.getFsPhAtk()+setting.getFsMfAtk();
                        break;
                }
            }
            if(peer.getBuilding()!=null){
                attackCount+=peer.getBuilding().getPhAtk();
            }
            if(arsenalInfoVO!=null)
                attackCount+=(int)(arsenalInfoVO.getAttack()+arsenalInfoVO.getSpellAttacks());

            attackCount=(int)(attackCount*(1+peer.getAddAttack()));
        }else{
            //普通怪物用物理攻击-角色的物理防御
            //精英怪物用法力攻击-角色的法力防御
            //BOSS用物理攻击-角色的物理防御
            if(peer.getMonster().getType()==MonsterConstant.TYPE_COMMON || peer.getMonster().getType()==MonsterConstant.TYPE_BOSS){
                attackCount+=peer.getMonster().getPhAtk();
            }
            if(peer.getMonster().getType()==MonsterConstant.TYPE_CREAM){
                attackCount+=peer.getMonster().getMfAtk();
            }
        }
        return attackCount;
    }

    /**
     * 计算防御力 A攻击B
     * @param peerA
     * @param peerB
     * @return
     */
    public static int getUserDefence(Combatant peerA,Combatant peerB,ArsenalInfoVO arsenalInfoVO){
        //卫士，刺客 roleId 1,2,3,4
        //工匠，法师 roleId 5,6,7,8
        //受卫士，刺客攻击，计算物理防御
        //受工匠，法师攻击，计算法术防御
        int roleId=peerA.getRoleId()==null?0:peerA.getRoleId();
        List<EquipmentInfoVO> equipments=peerB.getEquipmentList();
        int defenceCount=0;
        switch (roleId){
            case 1: case 2: case 3: case 4:
                if(peerB.getMonster()!=null){
                    defenceCount+=peerB.getMonster().getPhDef();

                }else{
                    for(EquipmentInfoVO equipment:equipments){//装备物理防御
                        defenceCount+=equipment.getPdef().intValue();
                    }
                    RoleLevelSetting setting1=peerB.getRoleLevelSetting();
                    if(setting1!=null)//角色物理防御
                        defenceCount+=(int)(setting1.getCkPhDef()+setting1.getFsPhDef()+setting1.getGjPhDef()+setting1.getWsPhDef());

                    if(arsenalInfoVO!=null)//佩戴武器物理防御
                        defenceCount+=arsenalInfoVO.getPdef().intValue();
                }
                if(peerB.getBuilding()!=null){
                    defenceCount+=peerB.getBuilding().getPhDef();
                }
                break;
            case 5: case 6: case 7: case 8:
                if(peerB.getMonster()!=null){
                    defenceCount+=peerB.getMonster().getMfDef();
                }else {
                    for(EquipmentInfoVO equipment:equipments){//装备法术防御
                        defenceCount+=equipment.getMagdef().intValue();
                    }
                    RoleLevelSetting setting2=peerB.getRoleLevelSetting();
                    if(setting2!=null)//角色法术防御
                        defenceCount+=(int)(setting2.getCkMfDef()+setting2.getFsMfDef()+setting2.getGjMfDef()+setting2.getWsMfDef());

                    if(arsenalInfoVO!=null)//佩戴武器法术防御
                        defenceCount+=arsenalInfoVO.getMagdef().intValue();
                }
                if(peerB.getBuilding()!=null){
                    defenceCount+=peerB.getBuilding().getMfDef();
                }
                break;
            case 0:
                //普通怪物用物理攻击-角色的物理防御
                //精英怪物用法力攻击-角色的法力防御
                //BOSS用物理攻击-角色的物理防御
                if(peerA.getMonster()!=null){
                    if(peerA.getMonster().getType()==MonsterConstant.TYPE_COMMON || peerA.getMonster().getType()==MonsterConstant.TYPE_BOSS){
                        for(EquipmentInfoVO equipment:equipments){//装备物理防御
                            defenceCount+=equipment.getPdef().intValue();
                        }
                        RoleLevelSetting setting01=peerB.getRoleLevelSetting();
                        if(setting01!=null)//角色物理防御
                            defenceCount+=(int)(setting01.getCkPhDef()+setting01.getFsPhDef()+setting01.getGjPhDef()+setting01.getWsPhDef());

                        if(arsenalInfoVO!=null)//佩戴武器物理防御
                            defenceCount+=arsenalInfoVO.getPdef().intValue();
                    }
                    if(peerA.getMonster().getRoleType()==MonsterConstant.TYPE_CREAM){
                        for(EquipmentInfoVO equipment:equipments){//装备法术防御
                            defenceCount+=equipment.getMagdef().intValue();
                        }
                        RoleLevelSetting setting02=peerB.getRoleLevelSetting();
                        if(setting02!=null)//角色法术防御
                            defenceCount+=(int)(setting02.getCkMfDef()+setting02.getFsMfDef()+setting02.getGjMfDef()+setting02.getWsMfDef());

                        if(arsenalInfoVO!=null)//佩戴武器法术防御
                            defenceCount+=arsenalInfoVO.getMagdef().intValue();
                    }
                }
                break;
        }
        return defenceCount;
    }

    public static int computeHurtCount(Combatant peerA,Integer attackCount,Integer defenceCount){
        /**
         * 玩家攻击输出=玩家攻击力*技能攻击力提高倍数+技能附加伤害
         * 伤害计算公式=攻击者攻击输出*
         * if(攻击者攻击/(攻击者攻击*修正系数a+被攻击者防御*修正系数b)-10%>=0)
         * 攻击者攻击/(攻击者攻击*修正系数a+被攻击者防御*修正系数b)
         * else
         * randbetween(8%,10%)
         */
        double s1=0;//玩家攻击输出
        double s2=0;
        s1=attackCount;
        if(attackCount/(attackCount*1+defenceCount*0.5)-0.1>=0){
            s2=(attackCount/(attackCount*1+defenceCount*0.5));
        } else {
            s2=RandomUtil.random(8,10)/100D;
        }
        int hurtCount=(int)(s1*s2);
        return hurtCount;
    }

    public static void computeBlood(Combatant peerA, Combatant peerB, FightStepDetailVO stepDetail){
        if(peerA.getGroup()==1){
            int blood=0;
            if(!CollectionUtils.isEmpty(peerB.getCombatantList())){
                for(Combatant cb:peerB.getCombatantList()){
                    blood+=cb.getBlood();
                }
            }
            stepDetail.setSelfBlood(peerA.getBlood());
            stepDetail.setOpponentBlood(blood);
        }else{
            int blood=0;
            if(!CollectionUtils.isEmpty(peerA.getCombatantList())){
                for(Combatant cb:peerA.getCombatantList()){
                    blood+=cb.getBlood();
                }
            }
            stepDetail.setSelfBlood(blood);
            stepDetail.setOpponentBlood(peerA.getBlood());
        }
    }

    /**
     * 计算命中
     * @param peer
     * @return
     */
    public static int getUserHitRate(Combatant peer,ArsenalInfoVO arsenalInfoVO){
        int hitCount=0;
        if(peer.getMonster()==null){
            List<EquipmentInfoVO> equipments=peer.getEquipmentList();
            for(EquipmentInfoVO equipment:equipments){
                hitCount+=equipment.getHitRate().intValue();
            }
            RoleLevelSetting setting=peer.getRoleLevelSetting();
            if(setting!=null)
                hitCount+=setting.getCkAccuracy()+setting.getFsAccuracy()+setting.getGjAccuracy()+setting.getWsAccuracy();

            if(arsenalInfoVO!=null)
                hitCount+=arsenalInfoVO.getHitRate().intValue();
            hitCount=(int)(hitCount*(1+peer.getAddAccuracy()));
        }else{
            hitCount+=peer.getMonster().getAccuracy();
        }
        if(peer.getBuilding()!=null){
            hitCount+=peer.getBuilding().getAccuracy();
        }
        return hitCount;
    }

    /**
     * 计算闪避
     * @param peer
     * @return
     */
    public static int getUserDodge(Combatant peer,ArsenalInfoVO arsenalInfoVO){
        int missCount=0;
        if(peer.getMonster()==null){
            List<EquipmentInfoVO> equipments=peer.getEquipmentList();
            for(EquipmentInfoVO equipment:equipments){
                missCount+=equipment.getDodge().intValue();
            }
            RoleLevelSetting setting=peer.getRoleLevelSetting();
            if(setting!=null)
                missCount+=setting.getCkMiss()+setting.getFsMiss()+setting.getGjMiss()+setting.getWsMiss();

            if(arsenalInfoVO!=null)
                missCount+=arsenalInfoVO.getDodge().intValue();
            missCount=(int)(missCount*(1+peer.getAddMiss()));
        }else{
            missCount+=peer.getMonster().getMiss();
        }

        if(peer.getBuilding()!=null){
            missCount+=peer.getBuilding().getMiss();
        }

        return missCount;
    }

    public static SkillSetting getSkillSettingBySkillIdAndLevel(int skillId, int level, List<SkillSetting> list){
        for(SkillSetting skillSetting:list){
            if(skillSetting.getSkillId()==skillId && skillSetting.getSkillLevel()==level){
                return skillSetting;
            }
        }
        return null;
    }

    public static void dealBloodCount(Combatant peerA,Integer bloodCount,FightStepDetailVO stepDetail){
        Map<String,Object> bloodCountMap=new HashMap<>();
        bloodCountMap.put("group",peerA.getGroup());
        bloodCountMap.put("index",peerA.getGroupIndex());
        bloodCountMap.put("value",bloodCount);
        stepDetail.setBloodCount(bloodCountMap);
    }

    public static void dealAddBlood(Combatant peerA,Integer bloodCount,FightStepDetailVO stepDetail){
        Map<String,Object> bloodCountMap=new HashMap<>();
        bloodCountMap.put("group",peerA.getGroup());
        bloodCountMap.put("index",peerA.getGroupIndex());
        bloodCountMap.put("value",bloodCount);
        stepDetail.setAddBlood(bloodCountMap);
    }

    public static void dealToxicosisCount(Combatant peerA,Integer toxicosisCount,FightStepDetailVO stepDetail){
        Map<String,Object> toxicosisMap=new HashMap<>();
        toxicosisMap.put("group",peerA.getGroup());
        toxicosisMap.put("index",peerA.getGroupIndex());
        toxicosisMap.put("value",toxicosisCount);
        stepDetail.setToxicosisCount(toxicosisMap);
    }

    public static Integer getFuryValueForFightTo(Combatant combatant){
        int furyCount=0;
        if(combatant.getMonster().getType()==1){
            furyCount+=RandomUtil.random(0,2);
        }
        if(combatant.getMonster().getType()==2){
            furyCount+=RandomUtil.random(1,3);
        }
        if(combatant.getMonster().getType()==3){
            furyCount+=RandomUtil.random(3,5);
        }
        return furyCount;
    }

    public static Integer getFuryValueForBeFight(Combatant combatant){
        int furyCount=0;
        if(combatant.getMonster()!=null){
            if(combatant.getMonster().getType()==1){
                furyCount+=RandomUtil.random(1,3);
            }
            if(combatant.getMonster().getType()==2){
                furyCount+=RandomUtil.random(2,4);
            }
            if(combatant.getMonster().getType()==3){
                furyCount+=RandomUtil.random(6,8);
            }
        }
        return furyCount;
    }


    public static void getCombatant(List<Map<String,Object>> fightGroup, Combatant combatant) {
        Map<String,Object> monsterMap=new HashMap<>();
        monsterMap.put("userId",combatant.getUserId());
        List<UserSkillVO> skillList=new ArrayList<>();
        if(combatant.getSkillList()!=null && !combatant.getSkillList().isEmpty()){
            combatant.getSkillList().forEach((UserSkillVO skill)->{
                UserSkillVO skillVO=new UserSkillVO();
                skillVO.setId(skill.getId());
                skillVO.setSkillId(skill.getSkillId());
                skillVO.setCanUse(null);
                skillList.add(skillVO);
            });
        }
        monsterMap.put("skillList",skillList);
        List<ArsenalInfoVO> weaponList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(combatant.getWeaponList())){
            for(ArsenalInfoVO arsenalInfoVO:combatant.getWeaponList()){
                ArsenalInfoVO weapon=new ArsenalInfoVO();
                weapon.setEquipmentId(arsenalInfoVO.getEquipmentId());
                weapon.setId(arsenalInfoVO.getId());
                weaponList.add(weapon);
            }
        }
        if(combatant.getCombatantList()!=null){
            Monster monster=combatant.getCombatantList().get(0).getMonster();
            int blood=0;
            for(Combatant cmb:combatant.getCombatantList()){
                blood+=cmb.getMonster().getHp();
            }
            monsterMap.put("blood",blood);
            monsterMap.put("furyValue",0);
            monsterMap.put("roleLevel",monster.getLevel());
            monsterMap.put("headUrl",monster.getHead());
            monsterMap.put("model",monster.getMode());
            monsterMap.put("name",monster.getName());
            monsterMap.put("count",combatant.getCombatantList().size());
        }
        monsterMap.put("weaponList",weaponList);
        fightGroup.add(monsterMap);
    }
}
