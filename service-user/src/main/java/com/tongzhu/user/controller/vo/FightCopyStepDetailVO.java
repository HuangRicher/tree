package com.tongzhu.user.controller.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FightCopyStepDetailVO implements Serializable {

    /**
     * type
     *  1.[玩家a]使用了技能xxx，对[玩家b]造成了xxx点伤害（暴击伤害），动画效果id
     *  2.[玩家a]使用xx对[玩家b]进行攻击，造成了xxx点伤害（暴击伤害）
     *  3.[玩家a]向[玩家b]投掷了一件xxx，造成了xxx点伤害（暴击伤害）
     *  4.[玩家a]使用xxx对[玩家b]进行攻击，被[玩家B]躲开了（格挡了）
     *  5.[玩家a]瞬间向[玩家b]投掷了3件武器，造成了xxx点伤害（暴击伤害）
     *  6.[玩家a]向[玩家b]投掷了一件xxx，被[玩家B]躲开了（格挡了）
     *  99: 战斗结束
     *  100:战斗中玩家换人
     *  98：回合开始
     *
     *  buffId
     *   1: 被击晕了
     *   2: 被定身了
     *   3: 中毒了
     *   4: 被沉默了
     *   5: 处于虚弱状态
     *   6: 失明了
     *   7: 命中提升了
     *   8:
     *   9: 攻击力提升了
     *  10: 普攻抗性提升了
     *  11: 回复了生命值
     *  12: 对自己使用了净化
     *  13: 自身受到了xxx点反噬伤害
     *  14: 触发了先发制人
     *  15: 抵抗了眩晕
     *  16: 使中毒效果暂缓了
     *  17: 抵抗了定身
     *  18: 格挡了一次攻击
     */
    private Integer type;

    private Integer actId;

    private Boolean bj;

    private Integer skillId;

    private Integer selfIndex; //己方参与者

    private Integer opponentIndex;  //对方参与者

    private Map<String,Object> attacker;//攻击者

    private String attackerName;

    private Map<String,Object> defender;//被攻击者

    private String defenderName;

    private String actionName; //攻击名称

    private Integer hurtCount;//伤害点数

    private Map<String,Object> bloodCount; //回复了的生命值；

    private Map<String,Object> toxicosisCount;//中毒伤害数；

    private Integer hurtBackCount;//反噬伤害

    private Map<String,Object> buffUserId; //buff作用对象

    private Map<String,Object> buff; //buff

    private String winnerUserId;

    private Integer fightingCount;//战斗回合数

    private Map<String,Object> deadPlayer;

    private List<String> weaponIdList;

    private Integer category; //类别  1：闪避   2：格挡

    private List<Map<String,String>> weaponList;  //丢弃的武器ID列表

    public Integer selfFuryValue;//己方怒气值

    public Integer opponentFuryValue;//对方怒气值

    public Integer selfBlood;//己方血量值

    public Integer opponentBlood;//对方血量值

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getHurtCount() {
        return hurtCount;
    }

    public void setHurtCount(Integer hurtCount) {
        this.hurtCount = hurtCount;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Boolean getBj() {
        return bj;
    }

    public void setBj(Boolean bj) {
        this.bj = bj;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(String winnerUserId) {
        this.winnerUserId = winnerUserId;
    }

    public List<Map<String, String>> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<Map<String, String>> weaponList) {
        this.weaponList = weaponList;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }


    public Integer getFightingCount() {
        return fightingCount;
    }

    public void setFightingCount(Integer fightingCount) {
        this.fightingCount = fightingCount;
    }


    public Map<String, Object> getAttacker() {
        return attacker;
    }

    public void setAttacker(Map<String, Object> attacker) {
        this.attacker = attacker;
    }

    public Map<String, Object> getDefender() {
        return defender;
    }

    public void setDefender(Map<String, Object> defender) {
        this.defender = defender;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public Map<String, Object> getBuffUserId() {
        return buffUserId;
    }

    public void setBuffUserId(Map<String, Object> buffUserId) {
        this.buffUserId = buffUserId;
    }

    public Map<String, Object> getBloodCount() {
        return bloodCount;
    }

    public void setBloodCount(Map<String, Object> bloodCount) {
        this.bloodCount = bloodCount;
    }

    public Map<String, Object> getToxicosisCount() {
        return toxicosisCount;
    }

    public void setToxicosisCount(Map<String, Object> toxicosisCount) {
        this.toxicosisCount = toxicosisCount;
    }

    public Integer getHurtBackCount() {
        return hurtBackCount;
    }

    public void setHurtBackCount(Integer hurtBackCount) {
        this.hurtBackCount = hurtBackCount;
    }

    public Map<String, Object> getDeadPlayer() {
        return deadPlayer;
    }

    public void setDeadPlayer(Map<String, Object> deadPlayer) {
        this.deadPlayer = deadPlayer;
    }

    public Map<String, Object> getBuff() {
        return buff;
    }

    public void setBuff(Map<String, Object> buff) {
        this.buff = buff;
    }

    public List<String> getWeaponIdList() {
        return weaponIdList;
    }

    public void setWeaponIdList(List<String> weaponIdList) {
        this.weaponIdList = weaponIdList;
    }

    public Integer getSelfIndex() {
        return selfIndex;
    }

    public void setSelfIndex(Integer selfIndex) {
        this.selfIndex = selfIndex;
    }

    public Integer getOpponentIndex() {
        return opponentIndex;
    }

    public void setOpponentIndex(Integer opponentIndex) {
        this.opponentIndex = opponentIndex;
    }

    public Integer getSelfFuryValue() {
        return selfFuryValue;
    }

    public void setSelfFuryValue(Integer selfFuryValue) {
        this.selfFuryValue = selfFuryValue;
    }

    public Integer getOpponentFuryValue() {
        return opponentFuryValue;
    }

    public void setOpponentFuryValue(Integer opponentFuryValue) {
        this.opponentFuryValue = opponentFuryValue;
    }

    public Integer getSelfBlood() {
        return selfBlood;
    }

    public void setSelfBlood(Integer selfBlood) {
        this.selfBlood = selfBlood;
    }

    public Integer getOpponentBlood() {
        return opponentBlood;
    }

    public void setOpponentBlood(Integer opponentBlood) {
        this.opponentBlood = opponentBlood;
    }
}
