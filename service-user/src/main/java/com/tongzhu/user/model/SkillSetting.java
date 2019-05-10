package com.tongzhu.user.model;

import java.io.Serializable;

public class SkillSetting implements Serializable {
    private Integer id;

    private Integer skillId;

    private Integer roleLevel;

    private Integer skillLevel;

    private Integer consumeMoney;

    private Integer consumeExperience;

    private Integer coolingTime;

    private String pro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Integer getConsumeExperience() {
        return consumeExperience;
    }

    public void setConsumeExperience(Integer consumeExperience) {
        this.consumeExperience = consumeExperience;
    }

    public Integer getCoolingTime() {
        return coolingTime;
    }

    public void setCoolingTime(Integer coolingTime) {
        this.coolingTime = coolingTime;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro == null ? null : pro.trim();
    }
}