package com.tongzhu.welfare.model;

import java.util.Date;

public class WelfareReceiveMonthLog {
    private Integer id;

    private String userId;

    private Integer rewardsType;

    private String rewardsContent;

    private Date receiveTime;

    private Date lastTime;

    private Integer countDay;

    private Integer rewardsDay;

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

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getCountDay() {
        return countDay;
    }

    public void setCountDay(Integer countDay) {
        this.countDay = countDay;
    }

    public Integer getRewardsDay() {
        return rewardsDay;
    }

    public void setRewardsDay(Integer rewardsDay) {
        this.rewardsDay = rewardsDay;
    }
}