package com.tongzhu.friend.model;

import java.util.Date;

public class TaskActivityRecord {
    private String id;

    private String userId;

    private Integer activityRewardsId;

    private Integer status;

    private Integer performance;

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

    public Integer getActivityRewardsId() {
        return activityRewardsId;
    }

    public void setActivityRewardsId(Integer activityRewardsId) {
        this.activityRewardsId = activityRewardsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}