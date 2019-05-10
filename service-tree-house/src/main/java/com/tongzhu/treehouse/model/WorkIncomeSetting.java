package com.tongzhu.treehouse.model;

public class WorkIncomeSetting {
    private String id;

    private Integer workTypeId;

    private Integer positionId;

    private Integer workTypeLevel;

    private String incomeGoods;

    private Float gameFriendMoreCount;

    private Float wxFriendMoreCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getWorkTypeLevel() {
        return workTypeLevel;
    }

    public void setWorkTypeLevel(Integer workTypeLevel) {
        this.workTypeLevel = workTypeLevel;
    }

    public String getIncomeGoods() {
        return incomeGoods;
    }

    public void setIncomeGoods(String incomeGoods) {
        this.incomeGoods = incomeGoods == null ? null : incomeGoods.trim();
    }

    public Float getGameFriendMoreCount() {
        return gameFriendMoreCount;
    }

    public void setGameFriendMoreCount(Float gameFriendMoreCount) {
        this.gameFriendMoreCount = gameFriendMoreCount;
    }

    public Float getWxFriendMoreCount() {
        return wxFriendMoreCount;
    }

    public void setWxFriendMoreCount(Float wxFriendMoreCount) {
        this.wxFriendMoreCount = wxFriendMoreCount;
    }
}