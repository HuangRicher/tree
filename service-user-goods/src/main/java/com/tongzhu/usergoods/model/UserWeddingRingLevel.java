package com.tongzhu.usergoods.model;

import java.util.Date;

public class UserWeddingRingLevel {
    private String userId;

    private String intensifyId;

    private Integer level;

    private Date createDate;

    private Date updateDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIntensifyId() {
        return intensifyId;
    }

    public void setIntensifyId(String intensifyId) {
        this.intensifyId = intensifyId == null ? null : intensifyId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}