package com.tongzhu.friend.model;

import java.util.Date;

public class DailyTaskRecord {
    private String id;

    private String userId;

    private Integer dailyTaskId;

    private Integer progress;

    private Integer goal;

    private Date updateDate;

    private Date finishDate;

    private Integer status;

    private Integer receiveAward;

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

    public Integer getDailyTaskId() {
        return dailyTaskId;
    }

    public void setDailyTaskId(Integer dailyTaskId) {
        this.dailyTaskId = dailyTaskId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReceiveAward() {
        return receiveAward;
    }

    public void setReceiveAward(Integer receiveAward) {
        this.receiveAward = receiveAward;
    }
}