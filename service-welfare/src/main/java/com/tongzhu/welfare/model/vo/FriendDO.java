package com.tongzhu.welfare.model.vo;

import java.io.Serializable;

public class FriendDO implements Serializable {

    private String id;
    private Integer intimacy;
    private String userId;
    private String name;
    private String portraitUrl;
    private Integer sex;
    //用户在线状态
    private Integer status;
    //好友战斗状态
    private Integer fightStatus;

    private Integer roleId;
    private Integer roleLevel;
    private String spouse;
    private Boolean isLovers;

    private Boolean showRedTip;

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

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public Boolean getIsLovers() {
        return isLovers;
    }

    public void setIsLovers(Boolean isLovers) {
        this.isLovers = isLovers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getShowRedTip() {
        return showRedTip;
    }

    public void setShowRedTip(Boolean showRedTip) {
        this.showRedTip = showRedTip;
    }

    public Integer getFightStatus() {
        return fightStatus;
    }

    public void setFightStatus(Integer fightStatus) {
        this.fightStatus = fightStatus;
    }
}
