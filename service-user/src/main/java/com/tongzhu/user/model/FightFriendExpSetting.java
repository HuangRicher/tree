package com.tongzhu.user.model;

public class FightFriendExpSetting {
    private Integer roleLevel;

    private Integer dayMax;

    private Integer successExp;

    private Integer failExp;

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getDayMax() {
        return dayMax;
    }

    public void setDayMax(Integer dayMax) {
        this.dayMax = dayMax;
    }

    public Integer getSuccessExp() {
        return successExp;
    }

    public void setSuccessExp(Integer successExp) {
        this.successExp = successExp;
    }

    public Integer getFailExp() {
        return failExp;
    }

    public void setFailExp(Integer failExp) {
        this.failExp = failExp;
    }
}