package com.tongzhu.user.model;

public class UserRoleTitle {
    private String userId;

    private Integer roleTitle;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(Integer roleTitle) {
        this.roleTitle = roleTitle;
    }
}