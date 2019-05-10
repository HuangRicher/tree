package com.tongzhu.usergoods.domain;

import java.util.Date;

public class LoveTreeInfo {
    private String id;

    private String userId;

    private Date createdTime;

    private String engagementId;

    private Long loveNum;

    private Integer loveGrade;

    private Long happinessNum;

    private Integer weddingRingGrade;

    private String loveTreeId;

    private String weddingRingId;

    private String properties;

    private Integer playJokesNum;

    private Integer weddingNum;

    private String otherId;

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

    public Long getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(Long loveNum) {
        this.loveNum = loveNum;
    }

    public Integer getLoveGrade() {
        return loveGrade;
    }

    public void setLoveGrade(Integer loveGrade) {
        this.loveGrade = loveGrade;
    }

    public Long getHappinessNum() {
        return happinessNum;
    }

    public void setHappinessNum(Long happinessNum) {
        this.happinessNum = happinessNum;
    }

    public Integer getWeddingRingGrade() {
        return weddingRingGrade;
    }

    public void setWeddingRingGrade(Integer weddingRingGrade) {
        this.weddingRingGrade = weddingRingGrade;
    }

    public String getLoveTreeId() {
        return loveTreeId;
    }

    public void setLoveTreeId(String loveTreeId) {
        this.loveTreeId = loveTreeId == null ? null : loveTreeId.trim();
    }

    public String getWeddingRingId() {
        return weddingRingId;
    }

    public void setWeddingRingId(String weddingRingId) {
        this.weddingRingId = weddingRingId == null ? null : weddingRingId.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public Integer getPlayJokesNum() {
        return playJokesNum;
    }

    public void setPlayJokesNum(Integer playJokesNum) {
        this.playJokesNum = playJokesNum;
    }

    public Integer getWeddingNum() {
        return weddingNum;
    }

    public void setWeddingNum(Integer weddingNum) {
        this.weddingNum = weddingNum;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId == null ? null : otherId.trim();
    }
}