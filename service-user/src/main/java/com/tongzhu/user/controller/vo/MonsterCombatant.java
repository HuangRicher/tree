package com.tongzhu.user.controller.vo;


import java.io.Serializable;
import java.util.List;

public class MonsterCombatant implements Serializable {
    private String userId;
    private Integer fullBlood;
    private Integer blood;
    private Integer furyValue;
    private String userName;
    private Integer roleId;
    private Integer roleLevel;
    private Integer group;
    private Integer groupIndex;

    List<MonsterVO> monsterList;

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

    public List<MonsterVO> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<MonsterVO> monsterList) {
        this.monsterList = monsterList;
    }
}
