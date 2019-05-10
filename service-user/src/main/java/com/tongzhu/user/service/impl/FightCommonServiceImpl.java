package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.FightConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.user.constant.*;
import com.tongzhu.user.controller.vo.Building;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class FightCommonServiceImpl implements FightCommonService {

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillSettingService skillSettingService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private CopyExpSettingService copyExpSettingService;

    @Autowired
    private NPCAttributeService npcAttributeService;


    private static final String FIGHT_PVE="_fight_pve_";
    private static final String USE_LIQUID="_use_liquid_";
    private static final String FIGHT_PVE_OPPONENT="_fight_pve_opponent_";
    private static final String FIGHT_PVE_SELF="_fight_pve_self_";


    @Override
    public boolean checkIsFirstAttack(List<UserSkillVO> userSkills,List<SkillSetting> list) {
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


    @Override
    public void removeMap(Map<String, Map<String,Float>> map, String removeKey){
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

    @Override
    public List<Map<String, Object>> getUserDetail(Integer group, String userId, List<String> ids, List<MonsterDO> monsterList, Integer exp) {
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Combatant> combatantList=new ArrayList<>();
        if(group==1){
            int groupIndex=0;
            for(String id:ids){
                groupIndex++;
                User user=userService.findByUserId(id);
                UserRole userRole=userRoleService.getByUserId(user.getUserId());
                List<BuildingVo> buildingVos=buildingService.getBuildingByUserId(id);

                Map<String,Object> selfMap=new HashMap<>();
                Combatant combatant=new Combatant();
                Building building=new Building();
                int buHp=0;
                for(BuildingVo build:buildingVos){
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_HOUSE){
                        building.setHp(46*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_COFFERS){
                        building.setPhDef(4*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_WELFARE){
                        building.setMfDef(4*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_WINESHOP){
                        building.setAccuracy(2*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_SMITHY){
                        building.setMiss(2*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_CHURCH){
                        building.setCritical(2*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_PETSHOP){
                        building.setDcritical(2*build.getCurrentGrade());
                    }
                    if(build.getBuildingType()== BuildingConstant.BUILDING_TYPE_MARRIAGE){
                        building.setMfAtk(7*build.getCurrentGrade());
                        building.setPhAtk(7*build.getCurrentGrade());
                    }
                }
                combatant.setBuilding(building);
                int blood=this.getUserFullBlood(building.getHp(),user.getUserId(),userRole);
                selfMap.put("portraitUrl",user.getPortraitUrl());
                selfMap.put("name",user.getName());
                selfMap.put("userId",user.getUserId());
                selfMap.put("roleId",user.getRoleId());
                selfMap.put("blood",blood);
                selfMap.put("furyValue",0);
                selfMap.put("sex",user.getSex());
                selfMap.put("roleLevel",userRole.getRoleLevel());
                ArsenalInfoVO weapon=userGoodsService.findUserWornWeapon(id);

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
                List<UserSkill> skillList=userSkillService.findByUserId(id);
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
                List<SkillSetting> skillSettings=skillSettingService.selectUserSkillSettings(id);
                combatant.setSkillSettingList(skillSettings);
                if(userId.equals(id)){
                    selfMap.put("skillList",viewSkillList);
                    if(redisService.get(USE_LIQUID+userId)!=null){
                        Map<String,Integer> data=(Map<String,Integer>)redisService.get(USE_LIQUID +userId);
                        float rate=0f;
                        if(data.get(LiquidMedicineConstant.LIFE)!=null){
                            rate=LiquidMedicineConstant.getAttrByGoodsId(LiquidMedicineConstant.LIFE);
                            blood=(int)(blood+blood*rate);
                            combatant.setBlood(blood);
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
                    }
                } else{
                    selfMap.put("skillList",new ArrayList<>());
                }
                selfMap.put("weaponList",new ArrayList<>());

                //武器
                List<ArsenalInfoVO> arsenalInfoVOList=userGoodsService.findUserAllWeaponList(id);
                combatant.setWeaponList(arsenalInfoVOList);

                //装备
                List<EquipmentInfoVO> equipments=userGoodsService.queryDressedEquipmentList(id);
                combatant.setEquipmentList(equipments);

                //角色等级配置属性
                RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
                combatant.setRoleLevelSetting(setting);

                mapList.add(selfMap);
                combatantList.add(combatant);

            }
            redisService.set(FIGHT_PVE_SELF+userId,combatantList);
        }

        if(group==2 && monsterList!=null && !monsterList.isEmpty()){
            //UserRole userRole=userRoleService.getByUserId(userId);
            int groupIndex=0;
            int tempGroup=0;
            int i=0;
            for(MonsterDO monster:monsterList){
                i++;
                if(i<=3){
                    Map<String,Object> monsterMap=new HashMap<>();
                    monsterMap.put("blood",monster.getHp().intValue());
                    //monsterMap.put("portraitUrl",monster.getHead());
                    //monsterMap.put("name",monster.getName());
                    monsterMap.put("userId",monster.getMonsterId());
                    if(monster.getType()==3)//Boss有怒气
                        monsterMap.put("furyValue",0);
                    monsterMap.put("roleLevel",monster.getLevel());
                    monsterMap.put("headUrl",monster.getHead());
                    monsterMap.put("model",monster.getMode());
                    monsterMap.put("name",monster.getName());
                    monsterMap.put("skillList",new ArrayList<>());
                    monsterMap.put("weaponList",new ArrayList<>());
                    //monsterMap.put("count",1);
                    mapList.add(monsterMap);
                }
                if(tempGroup<monster.getGroupId()){
                    tempGroup=monster.getGroupId();
                    groupIndex++;

                    Combatant combatant=new Combatant();
                    List<Combatant> children=new ArrayList<>();
                    Combatant child=new Combatant();
                    combatant.setExpAward(exp);
                    child.setExpAward(exp);
                    combatant.setGroup(2);
                    child.setGroup(2);
                    combatant.setGroupIndex(groupIndex);
                    child.setGroupIndex(groupIndex);//TODO
                    combatant.setBlood(monster.getHp().intValue());
                    child.setBlood(monster.getHp().intValue());
                    combatant.setFullBlood(monster.getHp().intValue());
                    child.setFullBlood(monster.getHp().intValue());
                    combatant.setFuryValue(0);
                    child.setFuryValue(0);
                    combatant.setRoleLevel(monster.getLevel());
                    child.setRoleLevel(monster.getLevel());
                    combatant.setSex(1);
                    child.setSex(1);
                    combatant.setUserId(monster.getMonsterId());
                    child.setUserId(monster.getMonsterId());
                    combatant.setUserName(monster.getName());
                    child.setUserName(monster.getName());
                    child.setMonster(monster);
                    List<UserSkillVO> skillVOList=new ArrayList<>();
                    List<SkillSetting> settingParams=new ArrayList<>();
                    if(monster.getSkill1Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill1Id());
                        skillVO.setSkillLevel(monster.getSkill1Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill1Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill1Id());
                        setting.setSkillLevel(monster.getSkill1Lv());
                        settingParams.add(setting);
                    }
                    if(monster.getSkill2Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill2Id());
                        skillVO.setSkillLevel(monster.getSkill2Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill2Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill2Id());
                        setting.setSkillLevel(monster.getSkill2Lv());
                        settingParams.add(setting);
                    }
                    if(monster.getSkill3Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill3Id());
                        skillVO.setSkillLevel(monster.getSkill3Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill3Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill3Id());
                        setting.setSkillLevel(monster.getSkill3Lv());
                        settingParams.add(setting);
                    }
                    child.setSkillList(skillVOList);
                    if(!settingParams.isEmpty()){
                        List<SkillSetting> skillSettings=skillSettingService.selectSkillSettingBySkillIdAndLevel(settingParams);
                        child.setSkillSettingList(skillSettings);
                    }
                    //武器
                    child.setWeaponList(new ArrayList<>());
                    //装备
                    child.setEquipmentList(new ArrayList<>());
                    children.add(child);
                    combatant.setCombatantList(children);
                    combatant.setSkillList(skillVOList);
                    combatantList.add(combatant);
                }else{
                    /*float blood=(float)(mapList.get(mapList.size()-1).get("blood"))+monster.getHp();
                    mapList.get(mapList.size()-1).put("blood",blood);
                    int count=(int)(mapList.get(mapList.size()-1).get("count"))+1;
                    mapList.get(mapList.size()-1).put("count",count);*/

                    Combatant combatant=combatantList.get(combatantList.size()-1);
                    List<Combatant> children=combatant.getCombatantList();
                    Combatant child=new Combatant();
                    child.setGroup(2);
                    child.setGroupIndex(groupIndex);//TODO
                    int bloodCount=combatant.getBlood()+monster.getHp().intValue();
                    combatant.setBlood(bloodCount);
                    child.setBlood(monster.getHp().intValue());
                    combatant.setFullBlood(bloodCount);
                    child.setFullBlood(monster.getHp().intValue());
                    child.setFuryValue(0);
                    child.setRoleLevel(monster.getLevel());
                    child.setSex(1);
                    child.setUserId(monster.getMonsterId());
                    child.setUserName(monster.getName());
                    child.setMonster(monster);
                    List<UserSkillVO> skillVOList=new ArrayList<>();
                    List<SkillSetting> settingParams=new ArrayList<>();
                    if(monster.getSkill1Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill1Id());
                        skillVO.setSkillLevel(monster.getSkill1Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill1Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill1Id());
                        setting.setSkillLevel(monster.getSkill1Lv());
                        settingParams.add(setting);
                    }
                    if(monster.getSkill2Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill2Id());
                        skillVO.setSkillLevel(monster.getSkill2Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill2Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill2Id());
                        setting.setSkillLevel(monster.getSkill2Lv());
                        settingParams.add(setting);
                    }
                    if(monster.getSkill3Id()>0){
                        UserSkillVO skillVO=new UserSkillVO();
                        skillVO.setSkillId(monster.getSkill3Id());
                        skillVO.setSkillLevel(monster.getSkill3Lv());
                        Skill ski=skillService.findBySkillId(monster.getSkill3Id());
                        skillVO.setFreezeCount(0);
                        skillVO.setType(ski.getType());
                        skillVO.setCoolingSetting(ski.getCoolingCircle());
                        skillVOList.add(skillVO);

                        SkillSetting setting=new SkillSetting();
                        setting.setSkillId(monster.getSkill3Id());
                        setting.setSkillLevel(monster.getSkill3Lv());
                        settingParams.add(setting);
                    }
                    child.setSkillList(skillVOList);
                    if(settingParams!=null && !settingParams.isEmpty()){
                        List<SkillSetting> skillSettings=skillSettingService.selectSkillSettingBySkillIdAndLevel(settingParams);
                        child.setSkillSettingList(skillSettings);
                    }

                    //武器
                    child.setWeaponList(new ArrayList<>());
                    //装备
                    child.setEquipmentList(new ArrayList<>());
                    children.add(child);
                }
            }

            redisService.set(FIGHT_PVE_OPPONENT+userId,combatantList);
        }

        return mapList;
    }

    /**
     * 计算攻击力
     * @param peer
     * @return
     */
    @Override
    public int getUserAggressivity(Combatant peer, ArsenalInfoVO arsenalInfoVO){
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
            attackCount+=peer.getMonster().getPhAtk()+peer.getMonster().getMfAtk();
        }
        return attackCount;
    }

    /**
     * 计算暴击
     * @param peer
     * @return
     */
    @Override
    public int getUseCrrit(Combatant peer,ArsenalInfoVO arsenalInfoVO){
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
    @Override
    public int getUseDefenceCrit(Combatant peer,ArsenalInfoVO arsenalInfoVO){
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

    /**
     * 计算命中
     * @param peer
     * @return
     */
    @Override
    public int getUserHitRate(Combatant peer,ArsenalInfoVO arsenalInfoVO){
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
    @Override
    public int getUserDodge(Combatant peer,ArsenalInfoVO arsenalInfoVO){
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

    /**
     * 计算防御力 A攻击B
     * @param peerA
     * @param peerB
     * @return
     */
    @Override
    public int getUserDefence(Combatant peerA,Combatant peerB,ArsenalInfoVO arsenalInfoVO){
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
                if(peerA.getMonster()!=null && peerA.getMonster().getRoleType()== MonsterConstant.ROLE_TYPE_WARRIOR){
                    for(EquipmentInfoVO equipment:equipments){//装备物理防御
                        defenceCount+=equipment.getPdef().intValue();
                    }
                    RoleLevelSetting setting01=peerB.getRoleLevelSetting();
                    if(setting01!=null)//角色物理防御
                        defenceCount+=(int)(setting01.getCkPhDef()+setting01.getFsPhDef()+setting01.getGjPhDef()+setting01.getWsPhDef());

                    if(arsenalInfoVO!=null)//佩戴武器物理防御
                        defenceCount+=arsenalInfoVO.getPdef().intValue();
                }
                if(peerA.getMonster()!=null && peerA.getMonster().getRoleType()==MonsterConstant.ROLE_TYPE_SORCERER){
                    for(EquipmentInfoVO equipment:equipments){//装备法术防御
                        defenceCount+=equipment.getMagdef().intValue();
                    }
                    RoleLevelSetting setting02=peerB.getRoleLevelSetting();
                    if(setting02!=null)//角色法术防御
                        defenceCount+=(int)(setting02.getCkMfDef()+setting02.getFsMfDef()+setting02.getGjMfDef()+setting02.getWsMfDef());

                    if(arsenalInfoVO!=null)//佩戴武器法术防御
                        defenceCount+=arsenalInfoVO.getMagdef().intValue();
                }
                break;
        }
        return defenceCount;
    }

    @Override
    public void subCountOne(Map<String, Map<String, Float>> dataMap){
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

    /**
     * 主动技能 peerA对peerB使用技能
     * @param peerA
     * @param peerB
     * @param skillId
     * @param object
     * @return
     */
    @Override
    public Integer useInitiativeSkill(Combatant peerA, Combatant peerB, Integer skillId, JSONObject object,
                                       Integer attackCount, Integer defenceCount,Boolean isCounterattack,Boolean isFireBuff,Integer effectSelfCount,List<String> weaponIdList) {

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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                            SkillSetting skillSetting=this.getSkillSettingBySkillIdAndLevel(userSkills.get(0).getSkillId(),userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 4:
                //desc="对同性造成%f%%伤害，对异性造成%f%%伤害"
                if(peerA.getSex()==peerB.getSex()){
                    attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1008)/100f));
                }else{
                    attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1009)/100f));
                }
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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

                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                            SkillSetting skillSetting=this.getSkillSettingBySkillIdAndLevel(userSkills.get(0).getSkillId(),userSkills.get(0).getSkillLevel(),peerB.getSkillSettingList());
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
                hurtCount=0;//computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 13:
                //desc="使对方造成%f%%+%f伤害，自身受到%f%%血量的伤害"
                effectSelfCount=(int)(peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1019)/100f));
                int bloodCount13=peerA.getBlood()-effectSelfCount;
                peerA.setBlood(bloodCount13);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 17:
                //desc="对对方造成%f%%+%f伤害，减少对手%f%%当前怒气值"
                int fury=peerB.getFuryValue();
                fury=(int)(fury-fury*(object.getFloat(BuffConstant.BUFF_1022)/100f));
                peerB.setFuryValue(fury);
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 19:
                //desc="生命值回复%f%%，每次战斗只能用1次"
                int blood19=peerA.getBlood();
                blood19=(int)(blood19+peerA.getFullBlood()*(object.getFloat(BuffConstant.BUFF_1025)/100f));
                peerA.setBlood(blood19>peerA.getFullBlood()?peerA.getFullBlood():blood19);
                peerA.setAddBloodCountRecord(peerA.getBlood()-blood19);
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
            case 20:
                /** 对对手造成%f%%+%f伤害，50%概率在攻击时附带%f%%吸血效果 */
                attackCount=(int)(attackCount*(object.getFloat(BuffConstant.BUFF_1001)/100f)+object.getFloat(BuffConstant.BUFF_1002));
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
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
                hurtCount=computeHurtCount(peerA,attackCount,defenceCount);
                break;
        }
        return hurtCount;
    }

    @Override
    public int computeHurtCount(Combatant peerA,Integer attackCount,Integer defenceCount){
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

    @Override
    public void freezeSkill(Combatant peer, Integer skillId){
        List<UserSkillVO> skillVOS=peer.getSkillList();
        for(UserSkillVO skillVO:skillVOS){
            if(skillVO.getSkillId()==skillId){
                skillVO.setFreezeCount(skillVO.getCoolingSetting());
            }
        }
    }

    @Override
    public void reduceFreezeCount(List<UserSkillVO> skillVOList){
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

    @Override
    public List<UserSkill> findInitiativeSkillByUserIdAndType(Combatant combatant){
        List<UserSkill> skillList=new ArrayList<>();
        for(UserSkillVO skillVO:combatant.getSkillList()){
            if(skillVO.getType().equals(UserSkillConstant.INITIATIVE_TYPE_SKILL) && skillVO.getFreezeCount()<=0){
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

    @Override
    public boolean checkIsCleanDebuff(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData) {
        boolean isCleanDeBuff=false;
        if(peerA.getBuff()!=null && peerA.getBuff().get(BuffConstant.BUFF_1040)!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1040).get("count")!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1040).get("count")>0){
            //以前回合触发了1040
            isCleanDeBuff=true;
        }else{
            //第一回合开始或以前回合没有触发1040 根据概率触发
            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_32,1);
            skillIds.put(SkillConstant.SKILL_34,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
            if(userSkills!=null && !userSkills.isEmpty()){
                for(UserSkillVO skill:userSkills){
                    SkillSetting skillSetting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerA.getSkillSettingList());
                    if(skillSetting!=null && StringUtils.isNotBlank(skillSetting.getPro())){
                        JSONObject object= JSON.parseObject(skillSetting.getPro());
                        if(object.getFloat(BuffConstant.BUFF_1038)!=null){ //1038,每回合有xx%概率移除自身所有debuff  skillId:32 心如止水
                            isCleanDeBuff= RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1038).floatValue());
                            if(isCleanDeBuff) {
                                FightStepDetailVO stepDetail=new FightStepDetailVO();
                                FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,0,null,stepDetail);
                                resultData.add(stepDetail);
                                //触发成功，去掉所有debuff标识
                                if(peerA.getDebuff()!=null){
                                    this.removeMap(peerA.getDebuff(),null);
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
                                FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,1,null,stepDetail);
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

    @Override
    public int solveDebuffResult(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData, Boolean isCleanDeBuff, FightParameter fightParameter) {
        if(isCleanDeBuff){
            fightParameter.setSkip1(false);
        }else{
            //被眩晕1004 ,x%概率击晕对手1回合 skillId: 2 降龙十巴掌
            if(peerA.getDebuff()!=null &&peerA.getDebuff().containsKey(BuffConstant.BUFF_1004)&& peerA.getDebuff().get(BuffConstant.BUFF_1004).get("count")>0){
                //眩晕效果生效则直接跳过回合
                //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 五毒掌
                if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014)&&
                        peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")>0 && peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")<=4){
                    //扣除血量后是否致死
                    int toxicosisCount=(int)(peerA.getFullBlood()*0.02);
                    fightParameter.setToxicosisCount(toxicosisCount);
                    if(peerA.getBlood()-toxicosisCount<=0){
                        //判定A死亡 回合结束给B相应奖励  结算
                        this.reduceFreezeCount(peerA.getSkillList());
                        //减一次所有的buff debuff
                        if(peerA.getBuff()!=null){
                            this.subCountOne(peerA.getBuff());
                        }
                        if(peerA.getDebuff()!=null){
                            this.subCountOne(peerA.getDebuff());
                        }
                        return 2;
                    }
                }
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    this.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    this.subCountOne(peerA.getDebuff());
                }
                return 0;
            }
            //被眩晕1005 ,x%概率击晕对手2回合 skillId: 15 大力金刚掌
            if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1005)&& peerA.getDebuff().get(BuffConstant.BUFF_1005).get("count")>0){
                //眩晕效果生效则直接跳过回合
                //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 五毒掌
                if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014)&&
                        peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")>0 && peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")<=4){
                    //扣除血量后是否致死
                    int toxicosisCount=(int)(peerA.getFullBlood()*0.02);
                    fightParameter.setToxicosisCount(toxicosisCount);
                    if(peerA.getBlood()-toxicosisCount<=0){
                        //判定A死亡 回合结束给B相应奖励  结算
                        this.reduceFreezeCount(peerA.getSkillList());
                        //减一次所有的buff debuff
                        if(peerA.getBuff()!=null){
                            this.subCountOne(peerA.getBuff());
                        }
                        if(peerA.getDebuff()!=null){
                            this.subCountOne(peerA.getDebuff());
                        }
                        return 2;
                    }
                }
                this.reduceFreezeCount(peerA.getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    this.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    this.subCountOne(peerA.getDebuff());
                }
                return 0;
            }
            //被中毒1014  ,有xx%概率让对方中毒，每回合损失2%生命值，持续4回合  skillId:9 五毒掌
            if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1014)&&
                    peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")>0){
                boolean isCounterattack=false;
                fightParameter.setSkip1(false);
                if(peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")==5){
                    if(peerA.getBuff()!=null && peerA.getBuff().get(BuffConstant.BUFF_1044)!=null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count")!=null &&
                            peerA.getBuff().get(BuffConstant.BUFF_1044).get("count")>0){
                        isCounterattack=true;
                    }else {
                        Map<Integer,Integer> skillIds=new HashMap<>();
                        skillIds.put(SkillConstant.SKILL_38,1);
                        List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
                        if(userSkills==null || userSkills.isEmpty()){
                            isCounterattack=false;
                        }else{
                            SkillSetting skillSetting=this.getSkillSettingBySkillIdAndLevel(userSkills.get(0).getSkillId(),userSkills.get(0).getSkillLevel(),peerA.getSkillSettingList());
                            if(skillSetting!=null){
                                if(StringUtils.isNotBlank(skillSetting.getPro())){
                                    JSONObject ooc= JSON.parseObject(skillSetting.getPro());
                                    if(ooc.getFloat(BuffConstant.BUFF_1044)!=null){ //1044,xx%概率使中毒效果暂缓3回合  skillId:38 缓毒术
                                        if(RandomUtil.probabilityEvent(ooc.getBigDecimal(BuffConstant.BUFF_1044).floatValue())){
                                            isCounterattack=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Map<String, Map<String, Float>> dataTemp9=peerB.getDebuff();
                    if(dataTemp9==null)
                        dataTemp9=new HashMap<>();
                    Map<String,Float> dataMap91014=new HashMap<>();
                    if(!isCounterattack){
                        dataMap91014.put("count",4f);
                    }else {
                        FightStepDetailVO stepDetail1=new FightStepDetailVO();
                        FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_38,false,3,null,stepDetail1);
                        resultData.add(stepDetail1);
                        dataMap91014.put("count",9f);
                    }
                    dataTemp9.put(BuffConstant.BUFF_1014,dataMap91014);
                    peerA.setDebuff(dataTemp9);
                }
                if(peerA.getDebuff().get(BuffConstant.BUFF_1014).get("count")==6){
                    Map<String, Map<String, Float>> dataTemp9=peerB.getDebuff();
                    if(dataTemp9==null)
                        dataTemp9=new HashMap<>();
                    Map<String,Float> dataMap91014=new HashMap<>();
                    dataMap91014.put("count",4f);
                    dataTemp9.put(BuffConstant.BUFF_1014,dataMap91014);
                    peerA.setDebuff(dataTemp9);
                }
                if(!isCounterattack){
                    //扣除血量后是否致死
                    int toxicosisCount=(int)(peerA.getFullBlood()*0.02);
                    fightParameter.setToxicosisCount(toxicosisCount);
                    if(peerA.getBlood()-toxicosisCount<=0){
                        //检测是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血  skillId: 22 回光返照
                        Map<Integer,Integer> skillIds=new HashMap<>();
                        skillIds.put(SkillConstant.SKILL_22,1);
                        List<UserSkillVO> userSkills1=FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
                        boolean isFire1027=false;
                        if(userSkills1!=null && !userSkills1.isEmpty()){
                            UserSkillVO skill=userSkills1.get(0);
                            SkillSetting setting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerA.getSkillSettingList());
                            JSONObject object=JSON.parseObject(setting.getPro());
                            if(object.getFloat(BuffConstant.BUFF_1027)!=null){
                                isFire1027=RandomUtil.probabilityEvent(object.getBigDecimal(BuffConstant.BUFF_1027).floatValue());
                            }
                        }
                        if(isFire1027){//触发1027技能
                            //复活，恢复血量，继续下一步
                            peerA.setBlood(1);
                            int bloodCount=1-(peerA.getBlood()-toxicosisCount);
                            fightParameter.setBloodCount(bloodCount);
                            FightStepDetailVO stepDetail2=new FightStepDetailVO();
                            if(toxicosisCount>0){
                                FightingUtil.dealToxicosisCount(peerA,toxicosisCount,stepDetail2);
                            }
                            FightingUtil.dealBloodCount(peerA,bloodCount,stepDetail2);
                            FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_22,false,1,null,stepDetail2);
                            resultData.add(stepDetail2);
                            fightParameter.setSkip1(false);
                        }else{
                            //判定A死亡 回合结束给B相应奖励  结算
                            this.reduceFreezeCount(peerA.getSkillList());
                            //减一次所有的buff debuff
                            if(peerA.getBuff()!=null){
                                this.subCountOne(peerA.getBuff());
                            }
                            if(peerA.getDebuff()!=null){
                                this.subCountOne(peerA.getDebuff());
                            }
                            return 2;
                        }
                    }else{
                        fightParameter.setSkip1(false);
                    }
                }
            }
            fightParameter.setSkip1(false);
        }
        return 3;
    }

    @Override
    public int useBigAndSkill(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData,FightParameter fightParameter) {
        float A1=40;
        float B1=40;
        float C1=55;
        //使用大招，技能
        //加载增益buff  增益buff包含且不限于：命中提升、暴击提升、攻击提升、生命值提升
        //命中提升1007、暴击提升1042、攻击提升1023、1024、生命值每回合回复1018
        //恢复血量 40%概率使用主动回复技能，包含且不限于：1017、1018、1025
        // 1018 接下来每回合回血xx%，持续3回合  skillId: 12 活力饼干
        if(peerA.getBuff()!=null && peerA.getBuff().get(BuffConstant.BUFF_1018)!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1018).get("count")!=null &&
                peerA.getBuff().get(BuffConstant.BUFF_1018).get("count")>0){

            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_12,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
            UserSkillVO skill=userSkills.get(0);
            SkillSetting skillSetting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerA.getSkillSettingList());
            if(skillSetting!=null){
                if(StringUtils.isNotBlank(skillSetting.getPro())){
                    JSONObject object=JSON.parseObject(skillSetting.getPro());
                    if(object.getFloat(BuffConstant.BUFF_1018)!=null){
                        int blood=peerA.getBlood();
                        int addBlood=(int)(blood*object.getBigDecimal(BuffConstant.BUFF_1018).floatValue()/100);
                        int bloodCount=addBlood;
                        fightParameter.setBloodCount(bloodCount);
                        blood=(blood+addBlood)>peerA.getFullBlood()?peerA.getFullBlood():(blood+addBlood);
                        peerA.setBlood(blood);

                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,3,null,stepDetail);

                        if(fightParameter.getToxicosisCount()>0){
                            FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                        }
                        if(bloodCount>0){
                            FightingUtil.dealBloodCount(peerA,bloodCount,stepDetail);
                        }
                        resultData.add(stepDetail);
                    }
                }
            }
        }else{
            //1017  立即回血xx% ;1018 接下来每回合回血xx%，持续3回合; skillId: 12 活力饼干
            // 1025 生命值回复xx%，每次战斗只能触发一次               skillId: 19  圣光术
            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_12,1);
            skillIds.put(SkillConstant.SKILL_19,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerA,skillIds);
            float bloodRate=peerA.getBlood()/peerA.getFullBlood();
            if(userSkills==null || userSkills.isEmpty())
                fightParameter.setSkip2(false);
            if((userSkills!=null && !userSkills.isEmpty()) && bloodRate<0.3 && RandomUtil.probabilityEvent(A1)){
                //成功释放技能，自身回复对应血量，获得对应buff，回合结束
                UserSkillVO skill=userSkills.get(0);
                SkillSetting setting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerA.getSkillSettingList());
                if(setting!=null && StringUtils.isNotBlank(setting.getPro())){
                    JSONObject object=JSON.parseObject(setting.getPro());
                    if(object.getFloat(BuffConstant.BUFF_1017)!=null){ //1017 1018在同一技能出现
                        int blood=peerA.getBlood();
                        int addBlood=(int)(blood*object.getBigDecimal(BuffConstant.BUFF_1017).floatValue()/100);
                        int bloodCount=addBlood;
                        fightParameter.setBloodCount(bloodCount);
                        peerA.setBlood((blood+addBlood)>peerA.getFullBlood()?peerA.getFullBlood():(blood+addBlood));
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,3,null,stepDetail);
                        if(fightParameter.getToxicosisCount()>0){
                            FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                        }
                        if(bloodCount>0){
                            FightingUtil.dealBloodCount(peerA,bloodCount,stepDetail);
                        }
                        resultData.add(stepDetail);
                    }

                    if(object.getFloat(BuffConstant.BUFF_1025)!=null){
                        int blood=peerA.getBlood();
                        int addBlood=(int)(blood*object.getBigDecimal(BuffConstant.BUFF_1025).floatValue()/100);
                        int bloodCount=addBlood;
                        fightParameter.setBloodCount(bloodCount);
                        peerA.setBlood(blood+addBlood>peerA.getFullBlood()?peerA.getFullBlood():blood+addBlood);
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,3,null,stepDetail);
                        if(fightParameter.getToxicosisCount()>0){
                            FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                        }
                        if(bloodCount>0){
                            FightingUtil.dealBloodCount(peerA,bloodCount,stepDetail);
                        }
                        resultData.add(stepDetail);
                    }
                }
            }
        }
        //判断是否标记了沉默debuff
        //被沉默1016 ,有xx%概率沉默对手，持续2回合  不能放大招和技能  skillId: 11 排云掌
        if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1016) &&
                peerA.getDebuff().get(BuffConstant.BUFF_1016).get("count")>0 ){
            //标记了沉默debuff,不能使用大招和技能，去使用武器

            //FightStepDetailVO stepDetail=new FightStepDetailVO();
            //FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_11,false,3,null,stepDetail);

            //resultData.add(stepDetail);
            fightParameter.setSkip2(false);
        }else{
            //血量>=30%
            float bloodRate=(float)peerA.getBlood()/peerA.getFullBlood();
            if(bloodRate>=0.3){
                //怒气已满触发大招 对玩家造成220%伤害  40%概率触发大招
                if(peerA.getMonster()==null && peerA.getFuryValue()>= UserSkillConstant.MAX_FURY_VALUE && RandomUtil.probabilityEvent(B1)){
                    //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
                    fightParameter.setSkip2(true);
                    peerA.setFuryValue(0);
                    int attackCount=this.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                    int defenceCount=this.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                    int hurtCount= this.computeHurtCount(peerA,attackCount,defenceCount);
                    //给B玩家增加怒气
                    int furyB = peerB.getFuryValue();
                    furyB=furyB + RandomUtil.random(5, 7);
                    peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
                    //触发大招 ,毁天灭地，对玩家造成220%伤害，对普通怪物造成500%伤害，对精英怪物造成750%伤害，对BOSS造成1000%伤害
                    if(peerB.getMonster()!=null && peerB.getMonster().getType()!=null){
                        if(peerB.getMonster().getType()==MonsterConstant.TYPE_COMMON){
                            hurtCount = (int) (hurtCount * 5);
                        }
                        if(peerB.getMonster().getType()==MonsterConstant.TYPE_CREAM){
                            hurtCount = (int) (hurtCount * 7.5);
                        }
                        if(peerB.getMonster().getType()==MonsterConstant.TYPE_BOSS){
                            hurtCount = (int) (hurtCount * 10);
                        }
                    }else{
                        hurtCount = (int) (hurtCount * 2.2);
                    }

                    fightParameter.setHurtCount(hurtCount);
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                    }

                    FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_53,false,1,hurtCount,stepDetail);

                    if(peerA.getGroup()==1){
                        stepDetail.setSelfFuryValue(peerA.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                    }else{
                        stepDetail.setSelfFuryValue(peerB.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                    }
                    if(peerA.getGroup()==1){
                        stepDetail.setSelfBlood(peerA.getBlood());
                        stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                    }else{
                        stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                        stepDetail.setOpponentBlood(peerA.getBlood());
                    }
                    resultData.add(stepDetail);
                }else{
                    //怒气未满 释放的主动技能 55%概率随机使用一个技能
                    //检测当前是否有可释放的主动技能
                    List<UserSkill> skillList=this.findInitiativeSkillByUserIdAndType(peerA);
                    if((skillList!=null && !skillList.isEmpty()) && RandomUtil.probabilityEvent(C1)) {
                        boolean canRelease = true;
                        //成功释放技能  结算伤害
                        UserSkill skill = skillList.get(0);
                        //技能ID为 7 ，10要先判断是否有足够武器，向对方投掷3件武器（包括已佩戴的）或 打掉对方1件武器（不包括已佩戴的）
                        if (skill.getSkillId() == SkillConstant.SKILL_7) {
                            if (peerA.getWeaponList() != null && peerA.getWeaponList().size() >= 3)
                                canRelease = true;
                            else
                                canRelease = false;
                        }
                        if (skill.getSkillId() == SkillConstant.SKILL_10) {
                            if (peerB.getWeaponList() != null && !(peerB.getWeaponList().isEmpty()))
                                canRelease = true;
                            else
                                canRelease = false;
                        }
                        if (canRelease) {
                            Boolean isCounterattack=false;
                            Boolean isFireBuff=false;
                            List<String> weaponIdList=new ArrayList<>();
                            SkillSetting setting = this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(), skill.getLevel(),peerA.getSkillSettingList());
                            if (setting != null && StringUtils.isNotBlank(setting.getPro())) {
                                JSONObject object = JSON.parseObject(setting.getPro());
                                int attackCount = this.getUserAggressivity(peerA, peerA.getWornWeapon());//攻击力
                                int defenceCount = this.getUserDefence(peerA, peerB, peerA.getWornWeapon());//防御力
                                int hurtCount = this.useInitiativeSkill(peerA, peerB, skill.getSkillId(), object, attackCount, defenceCount,isCounterattack,isFireBuff,fightParameter.getHurtBackCount(),weaponIdList);
                                fightParameter.setHurtCount(hurtCount);
                                this.freezeSkill(peerA,skill.getSkillId());
                            }

                            fightParameter.setSkip2(true);

                            //增加双方怒气
                            int furyB = peerB.getFuryValue();
                            furyB = furyB + RandomUtil.random(8, 10);
                            peerB.setFuryValue(furyB > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyB);
                            int furyA = peerA.getFuryValue();
                            furyA = furyA + RandomUtil.random(5, 7);
                            peerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);

                            //根据玩家A的攻击增益buff标记，代入伤害公式计算伤害，得出最终伤害
                            //检测玩家B是否有虚弱buff
                            //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
                            if (peerB.getDebuff() != null && peerB.getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                                    peerB.getDebuff().get(BuffConstant.BUFF_1020).get("count") > 0) {
                                //玩家B有虚弱debuff,必暴击  加暴击算伤害
                                //初始暴击伤害比例 150%
                                int hurtCount = (int) (fightParameter.getHurtCount() * FightConstant.BIG_SKILL_HURT_RATE_PLAYER);
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
                                    stepDetail.setSelfBlood(peerA.getBlood());
                                    stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                                }else{
                                    stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                                    stepDetail.setOpponentBlood(peerA.getBlood());
                                }
                                resultData.add(stepDetail);
                            } else {
                                //玩家B无虚弱debuff  代入暴击公式计算是否暴击  暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
                                int critCount = this.getUseCrrit(peerA, peerA.getWornWeapon());
                                int defenceCritCount = this.getUseDefenceCrit(peerB, peerA.getWornWeapon());
                                float bjRate = Math.min(critCount / (critCount + defenceCritCount * 19), 50);
                                if (RandomUtil.probabilityEvent(bjRate)) {
                                    //初始暴击伤害比例 150%
                                    int hurtCount = (int) (fightParameter.getHurtCount() * FightConstant.BIG_SKILL_HURT_RATE_PLAYER);
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
                                        stepDetail.setSelfBlood(peerA.getBlood());
                                        stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                                    }else{
                                        stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
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
                                        stepDetail.setSelfBlood(peerA.getBlood());
                                        stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                                    }else{
                                        stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
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
                        }else {
                            //没有释放主动技能，则使用武器攻击
                            fightParameter.setSkip2(false);
                        }
                    }else{
                        //没有释放主动技能，则使用武器攻击
                        fightParameter.setSkip2(false);
                    }
                }
            }
        }
        return 3;
    }

    @Override
    public int useWeapon(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData,FightParameter fightParameter) {
        Map<String,String> deleteWeapon=new HashMap<>();
        float D1=30;
        float D2=30;
        float D3=40;
        //使用武器攻击
        //标记了定身debuff 定身1011 ,x%概率让对方定身，持续4回合 skillId: 6 百步定身
        if(peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1011) &&
                peerA.getDebuff().get(BuffConstant.BUFF_1011).get("count")>0 ){
            //标记了定身debuff  跳过该回合
            //客户端提示：玩家A被定身了，无法攻击
            fightParameter.setSkip3(true);
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            if(fightParameter.getToxicosisCount()>0){
                FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
            }
            FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_6,false,3,null,stepDetail);

            resultData.add(stepDetail);
        }else{
            //增加双方怒气
            int furyB = peerB.getFuryValue();
            furyB = furyB + RandomUtil.random(8, 10);
            peerB.setFuryValue(furyB > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyB);
            int furyA = peerA.getFuryValue();
            furyA = furyA + RandomUtil.random(5, 7);
            peerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);

            // 检测武器库里是否还有武器
            List<ArsenalInfoVO> arsenalInfoVOList=peerA.getWeaponList();
            int rand=RandomUtil.random(1,1000);
            if(arsenalInfoVOList!=null && !arsenalInfoVOList.isEmpty() && rand<=D1*10 && arsenalInfoVOList.size()>1){
                //武器库里有武器 30%概率投掷当前武器，随机装备上另一件武器
                //投掷武器 计算伤害
                int attackCount=this.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                int defenceCount=this.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力

                List<ArsenalInfoVO> del1=new ArrayList<>();
                Iterator<ArsenalInfoVO> iterator1 = peerA.getWeaponList().listIterator();
                while (iterator1.hasNext()) {
                    ArsenalInfoVO weap=iterator1.next();
                    if(peerA.getWornWeapon()!=null && peerA.getWornWeapon().getId().equals(weap.getId())){
                        deleteWeapon.put("id",weap.getGoodsId());
                        deleteWeapon.put("name",weap.getName());
                        del1.add(weap);
                        peerA.setWornWeapon(null);
                        break;
                    }
                }
                peerA.getWeaponList().removeAll(del1);
                if(peerA.getWeaponList()!=null && peerA.getWeaponList().size()>0)
                    peerA.setWornWeapon(peerA.getWeaponList().get(0));
                int hurtCount= this.computeHurtCount(peerA,attackCount,defenceCount);
                fightParameter.setHurtCount(hurtCount);
                fightParameter.setType(3);
            }else if(arsenalInfoVOList!=null && !arsenalInfoVOList.isEmpty() && rand>D1*10 && rand<=(D1+D2)*10 && arsenalInfoVOList.size()>1){
                //武器库里有武器 30%概率丢弃当前武器，随机换一个武器并进行装备并攻击
                //使用武器攻击  计算伤害
                List<ArsenalInfoVO> del1=new ArrayList<>();
                Iterator<ArsenalInfoVO> iterator1 = peerA.getWeaponList().listIterator();
                while (iterator1.hasNext()) {
                    ArsenalInfoVO weap=iterator1.next();
                    if(peerA.getWornWeapon()!=null && peerA.getWornWeapon().getId().equals(weap.getId())){
                        deleteWeapon.put("id",weap.getGoodsId());
                        deleteWeapon.put("name",weap.getName());
                        del1.add(weap);
                        peerA.setWornWeapon(null);
                        break;
                    }
                }
                peerA.getWeaponList().removeAll(del1);
                if(peerA.getWeaponList()!=null && peerA.getWeaponList().size()>0)
                    peerA.setWornWeapon(peerA.getWeaponList().get(0));

                int attackCount=this.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                int defenceCount=this.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                int hurtCount= this.computeHurtCount(peerA,attackCount,defenceCount);
                fightParameter.setHurtCount(hurtCount);
                fightParameter.setType(2);
            }else{
                //40%概率roll空
                //检测当前手上是否有武器
                ArsenalInfoVO arsenalInfoVO=peerA.getWornWeapon();
                if(arsenalInfoVO!=null){
                    //手上有武器,使用当前武器攻击
                    int attackCount=this.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
                    int defenceCount=this.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
                    int hurtCount= this.computeHurtCount(peerA,attackCount,defenceCount);
                    fightParameter.setHurtCount(hurtCount);
                    fightParameter.setType(2);
                }else{
                    //手上无武器,使用徒手进行攻击
                    int attackCount=this.getUserAggressivity(peerA,null);//攻击力
                    int defenceCount=this.getUserDefence(peerA,peerB,null);//防御力
                    int hurtCount= this.computeHurtCount(peerA,attackCount,defenceCount);
                    fightParameter.setHurtCount(hurtCount);
                    fightParameter.setType(1);
                }
            }
            //根据玩家A的命中增益buff，1003 代入命中公式计算是否命中  命中率= max(攻击方命中/（攻击方命中+被攻击者闪避*修正系数c）, 最小命中率)
            int accuracy=this.getUserHitRate(peerA,peerA.getWornWeapon());
            int miss=this.getUserDodge(peerB,peerB.getWornWeapon());
            float hitRate=(float)Math.max(accuracy/(accuracy+miss*0.05263 ),50f);
            //检测玩家A是否有失明buff
            //失明1021 ,有xx%概率使对方失明，持续2回合 ;失明后闪避无效  skillId: 16   太阳拳
            if((peerA.getDebuff()!=null && peerA.getDebuff().containsKey(BuffConstant.BUFF_1021) &&
                    peerA.getDebuff().get(BuffConstant.BUFF_1021).get("count")>0 )){

                FightStepDetailVO stepDetail=new FightStepDetailVO();
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                FightingUtil.generateFightingStep(peerA,peerB,2,16,false,3,null,stepDetail);

                resultData.add(stepDetail);

                //玩家A失明 该回合结束
                this.reduceFreezeCount(peerA.getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    this.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    this.subCountOne(peerA.getDebuff());
                }
                return 0;
            }else{
                //玩家A未命中 该回合结束
                if(!RandomUtil.probabilityEvent(hitRate)){
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    switch (fightParameter.getType()){
                        case 1://徒手进行攻击
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_993  ,false,2,null,stepDetail);
                            break;
                        case 2://使用当前武器攻击
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_991,false,2,null,stepDetail,deleteWeapon);
                            break;
                        case 3://投掷当前武器
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_992,false,2,null,stepDetail,deleteWeapon);
                            break;
                    }
                    if(peerA.getGroup()==1){
                        stepDetail.setSelfFuryValue(peerA.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                    }else{
                        stepDetail.setSelfFuryValue(peerB.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                    }

                    if(peerA.getGroup()==1){
                        stepDetail.setSelfBlood(peerA.getBlood());
                        stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                    }else{
                        stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                        stepDetail.setOpponentBlood(peerA.getBlood());
                    }

                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                    }
                    resultData.add(stepDetail);
                    this.reduceFreezeCount(peerA.getSkillList());
                    //减一次所有的buff debuff
                    if(peerA.getBuff()!=null){
                        this.subCountOne(peerA.getBuff());
                    }
                    if(peerA.getDebuff()!=null){
                        this.subCountOne(peerA.getDebuff());
                    }
                    return 0;
                }
                //检测玩家B是否有虚弱buff
                //虚弱1020 ,有xx%概率使对方虚弱，持续2回合
                if(peerB.getDebuff()!=null && peerB.getDebuff().containsKey(BuffConstant.BUFF_1020) &&
                        peerB.getDebuff().get(BuffConstant.BUFF_1020).get("count")>0 ){
                    //玩家B有虚弱debuff,必暴击  加暴击算伤害
                    //初始暴击伤害比例 150%
                    int hurtCount=(int)(fightParameter.getHurtCount()*FightConstant.BIG_SKILL_HURT_RATE_PLAYER);
                    fightParameter.setHurtCount(hurtCount);
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    switch (fightParameter.getType()){
                        case 1://徒手进行攻击
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_993,true,1,hurtCount,stepDetail);
                            break;
                        case 2://使用当前武器攻击
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_991,true,1,hurtCount,stepDetail,deleteWeapon);
                            break;
                        case 3://投掷当前武器
                            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_992,true,1,hurtCount,stepDetail,deleteWeapon);
                            break;
                    }
                    if(peerA.getGroup()==1){
                        stepDetail.setSelfFuryValue(peerA.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                    }else{
                        stepDetail.setSelfFuryValue(peerB.getFuryValue());
                        stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                    }

                    if(peerA.getGroup()==1){
                        stepDetail.setSelfBlood(peerA.getBlood());
                        stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                    }else{
                        stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                        stepDetail.setOpponentBlood(peerA.getBlood());
                    }

                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                    }
                    resultData.add(stepDetail);
                    fightParameter.setSkip3(false);
                }else{
                    //玩家B无虚弱buff  加暴击算伤害
                    //再根据玩家A的暴击增益buff，代入暴击公式计算是否暴击  暴击率= min(攻击方暴击/（攻击方暴击+被攻击方抗暴击*修正系数d）, 最大暴击率)
                    int critCount=this.getUseCrrit(peerA,peerA.getWornWeapon());
                    int defenceCritCount=this.getUseDefenceCrit(peerB,peerA.getWornWeapon());
                    float bjRate=Math.min(critCount/(critCount+defenceCritCount*19),50);
                    if(RandomUtil.probabilityEvent(bjRate)){
                        //加暴击伤害
                        //初始暴击伤害比例 130%
                        int hurtCount=(int)(fightParameter.getHurtCount()*FightConstant.BIG_SKILL_HURT_RATE_PLAYER);
                        fightParameter.setHurtCount(hurtCount);
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        switch (fightParameter.getType()){
                            case 1://徒手进行攻击
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_993,true,1,hurtCount,stepDetail);
                                break;
                            case 2://使用当前武器攻击
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_991,true,1,hurtCount,stepDetail,deleteWeapon);
                                break;
                            case 3://投掷当前武器
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_992,true,1,hurtCount,stepDetail,deleteWeapon);
                                break;
                        }
                        if(peerA.getGroup()==1){
                            stepDetail.setSelfFuryValue(peerA.getFuryValue());
                            stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                        }else{
                            stepDetail.setSelfFuryValue(peerB.getFuryValue());
                            stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                        }

                        if(peerA.getGroup()==1){
                            stepDetail.setSelfBlood(peerA.getBlood());
                            stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                        }else{
                            stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                            stepDetail.setOpponentBlood(peerA.getBlood());
                        }
                        if(fightParameter.getToxicosisCount()>0){
                            FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                        }
                        resultData.add(stepDetail);
                    }else{
                        //不加暴击伤害
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        switch (fightParameter.getType()){
                            case 1://徒手进行攻击
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_993,false,1,fightParameter.getHurtCount(),stepDetail);
                                break;
                            case 2://使用当前武器攻击
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_991,false,1,fightParameter.getHurtCount(),stepDetail,deleteWeapon);
                                break;
                            case 3://投掷当前武器
                                FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_992,false,1,fightParameter.getHurtCount(),stepDetail,deleteWeapon);
                                break;
                        }
                        if(peerA.getGroup()==1){
                            stepDetail.setSelfFuryValue(peerA.getFuryValue());
                            stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                        }else{
                            stepDetail.setSelfFuryValue(peerB.getFuryValue());
                            stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                        }
                        if(peerA.getGroup()==1){
                            stepDetail.setSelfBlood(peerA.getBlood());
                            stepDetail.setOpponentBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                        }else{
                            stepDetail.setSelfBlood((peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()))<0?0:peerB.getBlood()-(stepDetail.getHurtCount()==null?0:stepDetail.getHurtCount()));
                            stepDetail.setOpponentBlood(peerA.getBlood());
                        }
                        if(fightParameter.getToxicosisCount()>0){
                            FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                        }
                        resultData.add(stepDetail);
                    }
                    fightParameter.setSkip3(false);
                }
            }
        }
        return 3;
    }

    @Override
    public List<Map<String, Object>> getUserDetail(Integer group, String userId, List<String> ids) {
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Combatant> combatantList=new ArrayList<>();
        int groupIndex=0;
        UserRole CurrentUserRole=userRoleService.getByUserId(userId);
        for(String id:ids){
            int userRoleLevel=0;
            groupIndex++;
            User user=userService.findByUserId(id);
            UserRole userRole=userRoleService.getByUserId(user.getUserId());
            userRoleLevel=userRole.getRoleLevel();
            Map<String,Object> selfMap=new HashMap<>();
            Combatant combatant=new Combatant();
            int blood=0;
            if(RoleConstant.NPC_IDS.contains(","+id+",")){
                userRoleLevel+=CurrentUserRole.getRoleLevel();
                NPCAttribute attribute=npcAttributeService.findByLevelId(userRole.getRoleLevel()+CurrentUserRole.getRoleLevel());
                UserAdornEquip adornEquip=userGoodsService.getUserAdornEquipAttribute(id,
                        (double)attribute.getHp(),
                        (double)attribute.getPhAtk(),(double)attribute.getMfAtk(),(double)attribute.getPhDef(),(double)attribute.getMfDef(),
                        (double)attribute.getCritical(),(double)attribute.getMiss(),(double)attribute.getAccuracy(),(double)attribute.getDcritical());
                blood+=adornEquip.getVitality();
            }else{
                UserAdornEquip adornEquip=userGoodsService.getUserFightingCapacity(id);
                blood+=adornEquip.getVitality();
            }
            selfMap.put("portraitUrl",user.getPortraitUrl());
            selfMap.put("name",user.getName());
            selfMap.put("userId",user.getUserId());
            selfMap.put("roleId",user.getRoleId());
            selfMap.put("blood",blood);
            selfMap.put("furyValue",0);
            selfMap.put("sex",user.getSex());
            selfMap.put("roleLevel",userRoleLevel);
            ArsenalInfoVO weapon=userGoodsService.findUserWornWeapon(id);

            combatant.setGroup(group);
            combatant.setGroupIndex(groupIndex);
            combatant.setBlood(blood);
            combatant.setFullBlood(blood);
            combatant.setFuryValue(0);
            combatant.setRoleId(userRole.getRoleId());
            combatant.setRoleLevel(userRoleLevel);
            combatant.setSex(user.getSex());
            combatant.setUserId(user.getUserId());
            combatant.setUserName(user.getName());
            combatant.setWornWeapon(weapon);

            //技能
            List<UserSkill> skillList=userSkillService.findByUserId(id);
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
            List<SkillSetting> skillSettings=skillSettingService.selectUserSkillSettings(id);
            combatant.setSkillSettingList(skillSettings);
            if(userId.equals(id))
                selfMap.put("skillList",viewSkillList);
            else
                selfMap.put("skillList",new ArrayList<>());
            selfMap.put("weaponList",new ArrayList<>());

            //武器
            List<ArsenalInfoVO> arsenalInfoVOList=userGoodsService.findUserArsenalList(id);
            if(weapon!=null) arsenalInfoVOList.add(weapon);
            combatant.setWeaponList(arsenalInfoVOList);

            //道具
            List<EquipmentInfoVO> equipments=userGoodsService.queryDressedEquipmentList(id);
            combatant.setEquipmentList(equipments);

            //角色等级配置属性
            RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            combatant.setRoleLevelSetting(setting);

            mapList.add(selfMap);
            combatantList.add(combatant);
        }
        if(group==1)
            redisService.set("_fight_self_"+userId,combatantList);
        if(group==2)
            redisService.set("_fight_opponent_"+userId,combatantList);
        return mapList;
    }

    @Override
    public int getUserFullBlood(Integer hp, String userId, UserRole userRole) {
        List<PropInfo> propInfoList=userGoodsService.getUserWarePropInfo(userId);
        RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
        int blood=0;
        for(PropInfo propInfo:propInfoList){
            blood+=propInfo.getVitality();
        }
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
        blood+=hp;
        return blood;
    }

    @Override
    public List<Combatant> getCombatant(List<Combatant> fightGroup) {
        List<Combatant> attackGroup=new ArrayList<>();
        for(Combatant combatant:fightGroup){
            Combatant cb=new Combatant();
            cb.setUserId(combatant.getUserId());
            List<UserSkillVO> skillList=new ArrayList<>();
            if(!CollectionUtils.isEmpty(combatant.getSkillList())){
                for(UserSkillVO uk:combatant.getSkillList()){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setId(uk.getId());
                    skillVO.setSkillId(uk.getSkillId());
                    skillList.add(skillVO);
                }
            }
            cb.setSkillList(skillList);
            List<ArsenalInfoVO> weaponList=new ArrayList<>();
            if(!CollectionUtils.isEmpty(combatant.getWeaponList())){
                for(ArsenalInfoVO we:combatant.getWeaponList()){
                    ArsenalInfoVO weapon=new ArsenalInfoVO();
                    weapon.setEquipmentId(we.getEquipmentId());
                    weapon.setId(we.getId());
                    weaponList.add(weapon);
                }
            }
            cb.setWeaponList(weaponList);
            cb.setAddDisCritical(null);
            cb.setAddCritical(null);
            cb.setAddMiss(null);
            cb.setAddAccuracy(null);
            cb.setAddDefend(null);
            cb.setAddAttack(null);
            cb.setAddLife(null);
            attackGroup.add(cb);
        }
        return attackGroup;
    }

    @Override
    public int computeResult(Combatant peerA, Combatant peerB, List<FightStepDetailVO> resultData,FightParameter fightParameter) {
        //得出最终伤害,判断扣除血量后是否致死
        if(peerB.getBuff()!=null && peerB.getBuff().containsKey(BuffConstant.BUFF_1010) &&
                peerB.getBuff().get(BuffConstant.BUFF_1010).get("count")>0 ){
            //玩家B有1010 buff, 减少所所受的x%普攻伤害，持续3回合  skillId: 5 铁布衫
            Float rate=peerB.getBuff().get(BuffConstant.BUFF_1010).get("data");
            int hurtCount=fightParameter.getHurtCount()-(int)(fightParameter.getHurtCount()*(rate/100f));
            fightParameter.setHurtCount(hurtCount);
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_5,false,3,hurtCount,stepDetail);
            if(fightParameter.getToxicosisCount()>0){
                FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
            }
            resultData.add(stepDetail);
        }

        //判断玩家B是否触发1046技能 ,格挡一次攻击
        int tempHurtCount=fightParameter.getHurtCount();
        if(fightParameter.getType()>0 && peerB.getBuff()!=null && peerB.getBuff().containsKey(BuffConstant.BUFF_1046) &&
                peerB.getBuff().get(BuffConstant.BUFF_1046).get("count")>0 ){
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_40,false,1,null,stepDetail);
            if(fightParameter.getToxicosisCount()>0){
                FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
            }
            resultData.add(stepDetail);
            fightParameter.setHurtCount(0);
        }

        if((peerB.getBlood()-fightParameter.getHurtCount())>0){
            //检测B是否拥有1036技能 有xx%概率在承受攻击时，使对方遭受5%的伤害反噬  skillId: 30  反击术
            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_30,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
            float fireRate=0;
            UserSkillVO skill=null;
            if(userSkills!=null && !userSkills.isEmpty()){
                skill=userSkills.get(0);
                SkillSetting setting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerB.getSkillSettingList());
                if(setting!=null && StringUtils.isNotBlank(setting.getPro())){
                    JSONObject object=JSON.parseObject(setting.getPro());
                    if(object.getFloat(BuffConstant.BUFF_1036)!=null){
                        fireRate=object.getBigDecimal(BuffConstant.BUFF_1036).floatValue();
                    }
                }
            }
            //遭受5%的伤害反噬
            if(fireRate>0 && RandomUtil.probabilityEvent(fireRate)){
                int hurtBackCount=(int)(tempHurtCount*0.05);
                fightParameter.setHurtBackCount(hurtBackCount);
                int count=peerA.getBlood()-hurtBackCount;
                if(count>0){
                    //结算伤害
                    int bloodB=peerB.getBlood();
                    peerB.setBlood(bloodB-fightParameter.getHurtCount());
                    peerA.setBlood(count);
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    FightingUtil.generateFightingStep(peerA,peerB,2,SkillConstant.SKILL_30,false,1,fightParameter.getHurtCount(),stepDetail);
                    if(fightParameter.getToxicosisCount()>0){
                        FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                    }
                    if(hurtBackCount>0){
                        Map<String,Object> hurtBackMap=new HashMap<>();
                        hurtBackMap.put("group",peerA.getGroup());
                        hurtBackMap.put("index",peerA.getGroupIndex());
                        hurtBackMap.put("value",hurtBackCount);
                        stepDetail.setHurtBackCount(hurtBackCount);
                        hurtBackCount=0;
                    }
                    resultData.add(stepDetail);
                }else{
                    //判定A死亡 结束战斗
                    this.reduceFreezeCount(peerA.getSkillList());
                    //减一次所有的buff debuff
                    if(peerA.getBuff()!=null){
                        this.subCountOne(peerA.getBuff());
                    }
                    if(peerA.getDebuff()!=null){
                        this.subCountOne(peerA.getDebuff());
                    }
                    return 2;
                }
            }else {
                //结算伤害
                int bloodB=peerB.getBlood();
                peerB.setBlood(bloodB-fightParameter.getHurtCount());
            }
        }else{
            //检测B是否拥有1027技能 有xx%概率在收到致死攻击时变为1点血 skillId: 22 回光返照
            Map<Integer,Integer> skillIds=new HashMap<>();
            skillIds.put(SkillConstant.SKILL_22,1);
            List<UserSkillVO> userSkills=FightingUtil.findUserSkillByBuffIds(peerB,skillIds);
            float fireRate=0;
            UserSkillVO skill=null;
            if(userSkills!=null && !userSkills.isEmpty()){
                skill=userSkills.get(0);
                SkillSetting setting=this.getSkillSettingBySkillIdAndLevel(skill.getSkillId(),skill.getSkillLevel(),peerB.getSkillSettingList());
                if(setting!=null && StringUtils.isNotBlank(setting.getPro())){
                    JSONObject object=JSON.parseObject(setting.getPro());
                    if(object.getFloat(BuffConstant.BUFF_1027)!=null){
                        fireRate=object.getBigDecimal(BuffConstant.BUFF_1027).floatValue();
                    }
                }
            }
            if(fireRate>0 && RandomUtil.probabilityEvent(fireRate)){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                FightingUtil.generateFightingStep(peerA,peerB,2,skill.getSkillId(),false,1,null,stepDetail);
                if(fightParameter.getToxicosisCount()>0){
                    FightingUtil.dealToxicosisCount(peerA,fightParameter.getToxicosisCount(),stepDetail);
                }
                resultData.add(stepDetail);
                //复活，回复对应血量
                peerB.setBlood(1);
            }else{
                //判定B死亡 结束战斗
                this.reduceFreezeCount(peerA.getSkillList());
                //减一次所有的buff debuff
                if(peerA.getBuff()!=null){
                    this.subCountOne(peerA.getBuff());
                }
                if(peerA.getDebuff()!=null){
                    this.subCountOne(peerA.getDebuff());
                }
                return 1;
            }
        }
        return 3;
    }

    private SkillSetting getSkillSettingBySkillIdAndLevel(int skillId,int level,List<SkillSetting> list){
        for(SkillSetting skillSetting:list){
            if(skillSetting.getSkillId()==skillId && skillSetting.getSkillLevel()==level){
                return skillSetting;
            }
        }
        return null;
    }
}
