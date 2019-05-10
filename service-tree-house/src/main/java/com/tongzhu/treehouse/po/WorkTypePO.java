package com.tongzhu.treehouse.po;

import java.io.Serializable;

public class WorkTypePO implements Serializable {
    private String userId;
    private int workTypeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(int workTypeId) {
        this.workTypeId = workTypeId;
    }
}
