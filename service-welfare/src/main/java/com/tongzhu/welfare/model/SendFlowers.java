package com.tongzhu.welfare.model;

import java.util.Date;

public class SendFlowers {
    private Integer id;

    private String sengId;

    private String receiveId;

    private Integer num;

    private Date createdTime;

    private Integer isFriend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSengId() {
        return sengId;
    }

    public void setSengId(String sengId) {
        this.sengId = sengId == null ? null : sengId.trim();
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId == null ? null : receiveId.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }
}