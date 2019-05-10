package com.tongzhu.fishing.model;

public class DialUserRecordKey {
    private String userId;

    private Integer dialId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getDialId() {
        return dialId;
    }

    public void setDialId(Integer dialId) {
        this.dialId = dialId;
    }
}