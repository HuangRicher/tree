package com.tongzhu.user.controller.vo;

import java.io.Serializable;

public class FightFriendVO implements Serializable {

    private Integer intimacy;
    private String userId;
    private String name;
    private String portraitUrl;
    private Integer sex;
    private Integer status;
    private Integer roleId;
    private Integer roleLevel;
    private Boolean isLovers;
    private Long fightingCapacity;
    //好友战斗状态
    private Integer fightStatus;
    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Boolean getIsLovers() {
        return isLovers;
    }

    public void setIsLovers(Boolean isLovers) {
        this.isLovers = isLovers;
    }

    public Long getFightingCapacity() {
        return fightingCapacity;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        this.fightingCapacity = fightingCapacity;
    }

    public Integer getFightStatus() {
        return fightStatus;
    }

    public void setFightStatus(Integer fightStatus) {
        this.fightStatus = fightStatus;
    }
}
