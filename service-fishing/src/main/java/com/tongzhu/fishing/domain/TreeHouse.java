package com.tongzhu.fishing.domain;

import java.util.Date;

public class TreeHouse {
    private String id;

    private String userId;

    private Integer level;

    private Integer cultureValue;

    private Integer breakValue;

    private Integer flourishingRate;

    private Date startDate;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCultureValue() {
        return cultureValue;
    }

    public void setCultureValue(Integer cultureValue) {
        this.cultureValue = cultureValue;
    }

    public Integer getBreakValue() {
        return breakValue;
    }

    public void setBreakValue(Integer breakValue) {
        this.breakValue = breakValue;
    }

    public Integer getFlourishingRate() {
        return flourishingRate;
    }

    public void setFlourishingRate(Integer flourishingRate) {
        this.flourishingRate = flourishingRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}