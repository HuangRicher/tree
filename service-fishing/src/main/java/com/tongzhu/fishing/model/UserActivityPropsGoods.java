package com.tongzhu.fishing.model;

import java.io.Serializable;
import java.util.Date;

public class UserActivityPropsGoods  implements Serializable {
    private String id;

    private String userId;

    private Integer activityId;

    private Integer propsId;

    private Date creationStartDate;

    private Date recomposeEndDate;

    private Integer status;

    private Integer amount;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPropsId() {
        return propsId;
    }

    public void setPropsId(Integer propsId) {
        this.propsId = propsId;
    }

    public Date getCreationStartDate() {
        return creationStartDate;
    }

    public void setCreationStartDate(Date creationStartDate) {
        this.creationStartDate = creationStartDate;
    }

    public Date getRecomposeEndDate() {
        return recomposeEndDate;
    }

    public void setRecomposeEndDate(Date recomposeEndDate) {
        this.recomposeEndDate = recomposeEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}