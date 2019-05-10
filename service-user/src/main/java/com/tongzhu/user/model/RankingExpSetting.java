package com.tongzhu.user.model;

public class RankingExpSetting {
    private Integer roleLevel;

    private Integer winExp;

    private Integer failExp;

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getWinExp() {
        return winExp;
    }

    public void setWinExp(Integer winExp) {
        this.winExp = winExp;
    }

    public Integer getFailExp() {
        return failExp;
    }

    public void setFailExp(Integer failExp) {
        this.failExp = failExp;
    }
}