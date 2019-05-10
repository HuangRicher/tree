package com.tongzhu.welfare.model;

import java.util.Date;

public class LoveTree {
    private String id;

    private Date createdTime;

    private String engagementId;

    private Integer loveTreeGrade;

    private Date updateTime;

    private Integer expLimit;

    private Integer expNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(String engagementId) {
        this.engagementId = engagementId == null ? null : engagementId.trim();
    }

    public Integer getLoveTreeGrade() {
        return loveTreeGrade;
    }

    public void setLoveTreeGrade(Integer loveTreeGrade) {
        this.loveTreeGrade = loveTreeGrade;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getExpLimit() {
        return expLimit;
    }

    public void setExpLimit(Integer expLimit) {
        this.expLimit = expLimit;
    }

    public Integer getExpNum() {
        return expNum;
    }

    public void setExpNum(Integer expNum) {
        this.expNum = expNum;
    }
}