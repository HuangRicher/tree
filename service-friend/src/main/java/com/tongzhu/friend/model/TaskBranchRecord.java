package com.tongzhu.friend.model;

import java.util.Date;

public class TaskBranchRecord extends TaskBranchRecordKey {
    private Integer progress;

    private Integer goal;

    private Date finishDate;

    private Integer status;

    private Integer receiveAward;

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