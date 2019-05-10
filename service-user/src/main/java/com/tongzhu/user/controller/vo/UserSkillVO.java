package com.tongzhu.user.controller.vo;

import java.io.Serializable;

public class UserSkillVO implements Serializable {
    private String id;
    private String userId;
    private Integer skillId;
    private Integer skillLevel;
    private Integer freezeCount;
    private Integer type;
    private Integer coolingSetting;
    private Boolean canUse=true;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getFreezeCount() {
        return freezeCount;
    }

    public void setFreezeCount(Integer freezeCount) {
        this.freezeCount = freezeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCoolingSetting() {
        return coolingSetting;
    }

    public void setCoolingSetting(Integer coolingSetting) {
        this.coolingSetting = coolingSetting;
    }

    public Boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }
}
