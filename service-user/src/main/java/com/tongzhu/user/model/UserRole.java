package com.tongzhu.user.model;

public class UserRole {
    private String userId;

    private Integer roleId;

    private String spouse;

    private Long experience;

    private Integer blood;

    private String sociaty;

    private Integer roleTitle;

    private String userName;

    private Integer roleLevel;

    private Integer charmNum;

    private Integer sex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse == null ? null : spouse.trim();
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public String getSociaty() {
        return sociaty;
    }

    public void setSociaty(String sociaty) {
        this.sociaty = sociaty == null ? null : sociaty.trim();
    }

    public Integer getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(Integer roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getCharmNum() {
        return charmNum;
    }

    public void setCharmNum(Integer charmNum) {
        this.charmNum = charmNum;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}