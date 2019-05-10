package com.tongzhu.treehouse.po;

import java.io.Serializable;

public class WorkPositionPO implements Serializable {

    private String workPositionId;

    private String userId;

    private String workerId;

    private int workTypeId;


    public String getWorkPositionId() {
        return workPositionId;
    }

    public void setWorkPositionId(String workPositionId) {
        this.workPositionId = workPositionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public int getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(int workTypeId) {
        this.workTypeId = workTypeId;
    }
}
