package com.tongzhu.fishing.model;

import java.io.Serializable;
import java.util.Date;

public class UserActivityProps implements Serializable {
    private Integer id;

    private Integer activityId;

    private String gamePropsName;

    private Integer dropProbability;

    private String introduce;

    private String gamePropsPicture;

    private Date creationStartDate;

    private Date recomposeEndDate;

    private Integer status;

    private Integer commonFisheryProbability;

    private Integer seniorFisheryProbability;

    private Integer rareFisheryProbability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getGamePropsName() {
        return gamePropsName;
    }

    public void setGamePropsName(String gamePropsName) {
        this.gamePropsName = gamePropsName == null ? null : gamePropsName.trim();
    }

    public Integer getDropProbability() {
        return dropProbability;
    }

    public void setDropProbability(Integer dropProbability) {
        this.dropProbability = dropProbability;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getGamePropsPicture() {
        return gamePropsPicture;
    }

    public void setGamePropsPicture(String gamePropsPicture) {
        this.gamePropsPicture = gamePropsPicture == null ? null : gamePropsPicture.trim();
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

    public Integer getCommonFisheryProbability() {
        return commonFisheryProbability;
    }

    public void setCommonFisheryProbability(Integer commonFisheryProbability) {
        this.commonFisheryProbability = commonFisheryProbability;
    }

    public Integer getSeniorFisheryProbability() {
        return seniorFisheryProbability;
    }

    public void setSeniorFisheryProbability(Integer seniorFisheryProbability) {
        this.seniorFisheryProbability = seniorFisheryProbability;
    }

    public Integer getRareFisheryProbability() {
        return rareFisheryProbability;
    }

    public void setRareFisheryProbability(Integer rareFisheryProbability) {
        this.rareFisheryProbability = rareFisheryProbability;
    }
}