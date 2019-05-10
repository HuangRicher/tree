package com.tongzhu.welfare.model;

public class BridegroomLog {
    private Integer id;

    private String bridegroomDay;

    private String loveTreeId;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBridegroomDay() {
        return bridegroomDay;
    }

    public void setBridegroomDay(String bridegroomDay) {
        this.bridegroomDay = bridegroomDay == null ? null : bridegroomDay.trim();
    }

    public String getLoveTreeId() {
        return loveTreeId;
    }

    public void setLoveTreeId(String loveTreeId) {
        this.loveTreeId = loveTreeId == null ? null : loveTreeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}