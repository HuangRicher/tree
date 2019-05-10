package com.tongzhu.fishing.model;

import java.util.Date;

public class FishInfo {
    private Integer id;

    private Integer commonProbability;

    private Integer seniorProbability;

    private Integer rareProbability;

    private Date createDate;

    private Date updateDate;

    private String reward;

    private String rewardProbability;

    private Integer status;

    private Integer boutique;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommonProbability() {
        return commonProbability;
    }

    public void setCommonProbability(Integer commonProbability) {
        this.commonProbability = commonProbability;
    }

    public Integer getSeniorProbability() {
        return seniorProbability;
    }

    public void setSeniorProbability(Integer seniorProbability) {
        this.seniorProbability = seniorProbability;
    }

    public Integer getRareProbability() {
        return rareProbability;
    }

    public void setRareProbability(Integer rareProbability) {
        this.rareProbability = rareProbability;
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward == null ? null : reward.trim();
    }

    public String getRewardProbability() {
        return rewardProbability;
    }

    public void setRewardProbability(String rewardProbability) {
        this.rewardProbability = rewardProbability == null ? null : rewardProbability.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBoutique() {
        return boutique;
    }

    public void setBoutique(Integer boutique) {
        this.boutique = boutique;
    }
}