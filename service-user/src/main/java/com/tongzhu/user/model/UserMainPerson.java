package com.tongzhu.user.model;

public class UserMainPerson {
    private String userId;

    private String mainPersonName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMainPersonName() {
        return mainPersonName;
    }

    public void setMainPersonName(String mainPersonName) {
        this.mainPersonName = mainPersonName == null ? null : mainPersonName.trim();
    }
}