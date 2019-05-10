package com.tongzhu.fishing.model;

import java.util.Date;

public class FishingInfo {
    private Integer id;

    private String userId;

    private Date updateTime;

    private Integer multiple;

    private Integer numberFishingSum;

    private Integer numberFishingToday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public Integer getNumberFishingSum() {
        return numberFishingSum;
    }

    public void setNumberFishingSum(Integer numberFishingSum) {
        this.numberFishingSum = numberFishingSum;
    }

    public Integer getNumberFishingToday() {
        return numberFishingToday;
    }

    public void setNumberFishingToday(Integer numberFishingToday) {
        this.numberFishingToday = numberFishingToday;
    }
}