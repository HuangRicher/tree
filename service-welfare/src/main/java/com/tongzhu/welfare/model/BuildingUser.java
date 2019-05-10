package com.tongzhu.welfare.model;

import java.util.Date;

public class BuildingUser {
    private Integer id;

    private String userId;

    private Integer buildingId;

    private String buildingName;

    private Integer buildingGrade;

    private Integer buildingStatus;

    private Integer buildingGold;

    private Integer buildingUpgrade;

    private String buildingDetailed;

    private Integer buildingType;

    private Integer biuldingOutputMin;

    private Integer buildingOutputMax;

    private Integer buildingOutputRate;

    private Integer outputGold;

    private Date createdTime;

    private Date updateEndTime;

    private Integer buildingOpen;

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

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public Integer getBuildingGrade() {
        return buildingGrade;
    }

    public void setBuildingGrade(Integer buildingGrade) {
        this.buildingGrade = buildingGrade;
    }

    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public Integer getBuildingGold() {
        return buildingGold;
    }

    public void setBuildingGold(Integer buildingGold) {
        this.buildingGold = buildingGold;
    }

    public Integer getBuildingUpgrade() {
        return buildingUpgrade;
    }

    public void setBuildingUpgrade(Integer buildingUpgrade) {
        this.buildingUpgrade = buildingUpgrade;
    }

    public String getBuildingDetailed() {
        return buildingDetailed;
    }

    public void setBuildingDetailed(String buildingDetailed) {
        this.buildingDetailed = buildingDetailed == null ? null : buildingDetailed.trim();
    }

    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getBiuldingOutputMin() {
        return biuldingOutputMin;
    }

    public void setBiuldingOutputMin(Integer biuldingOutputMin) {
        this.biuldingOutputMin = biuldingOutputMin;
    }

    public Integer getBuildingOutputMax() {
        return buildingOutputMax;
    }

    public void setBuildingOutputMax(Integer buildingOutputMax) {
        this.buildingOutputMax = buildingOutputMax;
    }

    public Integer getBuildingOutputRate() {
        return buildingOutputRate;
    }

    public void setBuildingOutputRate(Integer buildingOutputRate) {
        this.buildingOutputRate = buildingOutputRate;
    }

    public Integer getOutputGold() {
        return outputGold;
    }

    public void setOutputGold(Integer outputGold) {
        this.outputGold = outputGold;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateEndTime() {
        return updateEndTime;
    }

    public void setUpdateEndTime(Date updateEndTime) {
        this.updateEndTime = updateEndTime;
    }

    public Integer getBuildingOpen() {
        return buildingOpen;
    }

    public void setBuildingOpen(Integer buildingOpen) {
        this.buildingOpen = buildingOpen;
    }
}