package com.tongzhu.welfare.model;

import java.util.Date;

public class WelfareReceiveDayLog {
    private Integer id;

    private String userId;

    private Integer rewardsType;

    private String rewardsContent;

    private Date receiveTime;

    private Integer rewardsDay;

    private Integer receiveAward;

    private Integer week;

    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRewardsType() {
        return rewardsType;
    }

    public void setRewardsType(Integer rewardsType) {
        this.rewardsType = rewardsType;
    }

    public String getRewardsContent() {
        return rewardsContent;
    }

    public void setRewardsContent(String rewardsContent) {
        this.rewardsContent = rewardsContent == null ? null : rewardsContent.trim();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getRewardsDay() {
        return rewardsDay;
    }

    public void setRewardsDay(Integer rewardsDay) {
        this.rewardsDay = rewardsDay;
    }

    public Integer getReceiveAward() {
        return receiveAward;
    }

    public void setReceiveAward(Integer receiveAward) {
        this.receiveAward = receiveAward;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}