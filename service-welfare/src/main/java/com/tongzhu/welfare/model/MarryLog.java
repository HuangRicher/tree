package com.tongzhu.welfare.model;

import java.util.Date;

public class MarryLog {
    private String id;

    private String userId;

    private String otherId;

    private Date createdTime;

    private Integer marryType;

    private Date beginTime;

    private Date endTime;

    private String engagementId;

    private Integer userJoyfulNum;

    private Integer otherJoyfulNum;

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

    public Integer getMarryType() {
        return marryType;
    }

    public void setMarryType(Integer marryType) {
        this.marryType = marryType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(String engagementId) {
        this.engagementId = engagementId == null ? null : engagementId.trim();
    }

    public Integer getUserJoyfulNum() {
        return userJoyfulNum;
    }

    public void setUserJoyfulNum(Integer userJoyfulNum) {
        this.userJoyfulNum = userJoyfulNum;
    }

    public Integer getOtherJoyfulNum() {
        return otherJoyfulNum;
    }

    public void setOtherJoyfulNum(Integer otherJoyfulNum) {
        this.otherJoyfulNum = otherJoyfulNum;
    }
}