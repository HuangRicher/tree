package com.tongzhu.friend.service.vo;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/1 0001.
 */
public class TaskActivityVO {

    /**
     * 宝箱id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 活跃度奖励id
     */
    private Integer activityRewardsId;
    /**
     * 活跃宝箱开启情况
     */
    private Integer status;
    /**
     * 活跃宝箱任务是否已经达到开启条件
     */
    private Integer performance;

    /**
     * 活跃度
     */
    private Integer liveness;
    /**
     * 奖励
     */
    private String award;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getActivityRewardsId() {
        return activityRewardsId;
    }

    public void setActivityRewardsId(Integer activityRewardsId) {
        this.activityRewardsId = activityRewardsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getLiveness() {
        return liveness;
    }

    public void setLiveness(Integer liveness) {
        this.liveness = liveness;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
