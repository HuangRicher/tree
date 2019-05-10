package com.tongzhu.user.controller.vo;

import com.tongzhu.user.domain.ArsenalInfoVO;
import com.tongzhu.user.domain.EquipmentInfoVO;
import com.tongzhu.user.model.Monster;
import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.user.service.vo.UserAdornEquip;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Combatant implements Serializable {

    //通用信息
    private String userId;
    private Integer fullBlood;
    private Integer blood;
    private Integer furyValue;
    private Integer sex;
    private String userName;
    private Integer roleId;
    private Integer roleLevel;
    private ArsenalInfoVO wornWeapon;
    private List<UserSkillVO> skillList;
    private List<ArsenalInfoVO> weaponList;
    private List<EquipmentInfoVO> equipmentList;
    private Integer group;
    private Integer groupIndex;
    private List<Combatant> combatantList;
    private Integer addBloodCountRecord;
    private UserAdornEquip userAdornEquip;
    //建筑增加属性
    private Building building;

    //怪物信息
    private Float realHurt;
    private String drop;
    private String talk;
    private Integer limit;
    private String mode;
    private Integer type;
    private Float phAtk;
    private Float mfAtk;
    private Float phDef;
    private Float mfDef;
    private Float hp;
    private Float accuracy;
    private Float miss;
    private Float critical;
    private Float dcritical;

    //打败一个小怪获得的经验
    private Integer expAward;

    private Monster monster;

    private RoleLevelSetting roleLevelSetting;

    private Map<String, Map<String,Float>> buff;  //buff
    private Map<String,Map<String,Float>> debuff;

    List<SkillSetting> skillSettingList;

    //药水属性加成
    private Float addLife=0f;  //生命药水
    private Float addAttack=0f; //攻击药水
    private Float addDefend=0f; //防御药水
    private Float addAccuracy=0f; //命中药水
    private Float addMiss=0f;  //闪避药水
    private Float addCritical=0f;  //暴击药水
    private Float addDisCritical=0f;  //抗暴击药水
    private Map<String,Integer> awardData;//奖励

    public UserAdornEquip getUserAdornEquip() {
        return userAdornEquip;
    }

    public void setUserAdornEquip(UserAdornEquip userAdornEquip) {
        this.userAdornEquip = userAdornEquip;
    }

    public Integer getAddBloodCountRecord() {
        return addBloodCountRecord;
    }

    public void setAddBloodCountRecord(Integer addBloodCountRecord) {
        this.addBloodCountRecord = addBloodCountRecord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFullBlood() {
        return fullBlood;
    }

    public void setFullBlood(Integer fullBlood) {
        this.fullBlood = fullBlood;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getFuryValue() {
        return furyValue;
    }

    public void setFuryValue(Integer furyValue) {
        this.furyValue = furyValue;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public ArsenalInfoVO getWornWeapon() {
        return wornWeapon;
    }

    public void setWornWeapon(ArsenalInfoVO wornWeapon) {
        this.wornWeapon = wornWeapon;
    }

    public List<ArsenalInfoVO> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<ArsenalInfoVO> weaponList) {
        this.weaponList = weaponList;
    }

    public Map<String, Map<String, Float>> getBuff() {
        return buff;
    }

    public void setBuff(Map<String, Map<String, Float>> buff) {
        this.buff = buff;
    }

    public Map<String, Map<String, Float>> getDebuff() {
        return debuff;
    }

    public void setDebuff(Map<String, Map<String, Float>> debuff) {
        this.debuff = debuff;
    }

    public List<UserSkillVO> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<UserSkillVO> skillList) {
        this.skillList = skillList;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }

    public List<Combatant> getCombatantList() {
        return combatantList;
    }

    public void setCombatantList(List<Combatant> combatantList) {
        this.combatantList = combatantList;
    }

    public Float getRealHurt() {
        return realHurt;
    }

    public void setRealHurt(Float realHurt) {
        this.realHurt = realHurt;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getPhAtk() {
        return phAtk;
    }

    public void setPhAtk(Float phAtk) {
        this.phAtk = phAtk;
    }

    public Float getMfAtk() {
        return mfAtk;
    }

    public void setMfAtk(Float mfAtk) {
        this.mfAtk = mfAtk;
    }

    public Float getPhDef() {
        return phDef;
    }

    public void setPhDef(Float phDef) {
        this.phDef = phDef;
    }

    public Float getMfDef() {
        return mfDef;
    }

    public void setMfDef(Float mfDef) {
        this.mfDef = mfDef;
    }

    public Float getHp() {
        return hp;
    }

    public void setHp(Float hp) {
        this.hp = hp;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Float getMiss() {
        return miss;
    }

    public void setMiss(Float miss) {
        this.miss = miss;
    }

    public Float getCritical() {
        return critical;
    }

    public void setCritical(Float critical) {
        this.critical = critical;
    }

    public Float getDcritical() {
        return dcritical;
    }

    public void setDcritical(Float dcritical) {
        this.dcritical = dcritical;
    }

    public List<EquipmentInfoVO> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentInfoVO> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public RoleLevelSetting getRoleLevelSetting() {
        return roleLevelSetting;
    }

    public void setRoleLevelSetting(RoleLevelSetting roleLevelSetting) {
        this.roleLevelSetting = roleLevelSetting;
    }

    public Float getAddLife() {
        return addLife;
    }

    public void setAddLife(Float addLife) {
        this.addLife = addLife;
    }

    public Float getAddAttack() {
        return addAttack;
    }

    public void setAddAttack(Float addAttack) {
        this.addAttack = addAttack;
    }

    public Float getAddDefend() {
        return addDefend;
    }

    public void setAddDefend(Float addDefend) {
        this.addDefend = addDefend;
    }

    public Float getAddAccuracy() {
        return addAccuracy;
    }

    public void setAddAccuracy(Float addAccuracy) {
        this.addAccuracy = addAccuracy;
    }

    public Float getAddMiss() {
        return addMiss;
    }

    public void setAddMiss(Float addMiss) {
        this.addMiss = addMiss;
    }

    public Float getAddCritical() {
        return addCritical;
    }

    public void setAddCritical(Float addCritical) {
        this.addCritical = addCritical;
    }

    public Float getAddDisCritical() {
        return addDisCritical;
    }

    public void setAddDisCritical(Float addDisCritical) {
        this.addDisCritical = addDisCritical;
    }

    public Map<String, Integer> getAwardData() {
        return awardData;
    }

    public void setAwardData(Map<String, Integer> awardData) {
        this.awardData = awardData;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public List<SkillSetting> getSkillSettingList() {
        return skillSettingList;
    }

    public void setSkillSettingList(List<SkillSetting> skillSettingList) {
        this.skillSettingList = skillSettingList;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getExpAward() {
        return expAward;
    }

    public void setExpAward(Integer expAward) {
        this.expAward = expAward;
    }
}
