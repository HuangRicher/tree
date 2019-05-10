package com.tongzhu.welfare.model;

import java.util.Date;

public class ToastLog {
    private String id;

    private String userId;

    private String marryId;

    private Date createdTime;

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

    public String getMarryId() {
        return marryId;
    }

    public void setMarryId(String marryId) {
        this.marryId = marryId == null ? null : marryId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}