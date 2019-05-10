package com.tongzhu.welfare.model;

import java.util.Date;

public class EngagementLog {
    private String id;

    private String userId;

    private String otherId;

    private Date createdTime;

    private Integer marryStatus;

    private String userRingId;

    private String otherRingId;

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

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId == null ? null : otherId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getUserRingId() {
        return userRingId;
    }

    public void setUserRingId(String userRingId) {
        this.userRingId = userRingId == null ? null : userRingId.trim();
    }

    public String getOtherRingId() {
        return otherRingId;
    }

    public void setOtherRingId(String otherRingId) {
        this.otherRingId = otherRingId == null ? null : otherRingId.trim();
    }
}