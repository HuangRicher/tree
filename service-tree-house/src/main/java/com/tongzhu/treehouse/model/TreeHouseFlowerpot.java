package com.tongzhu.treehouse.model;

import java.util.Date;

public class TreeHouseFlowerpot {
    private String id;

    private String treeHouseId;

    private String goodsId;

    private Integer lockLevel;

    private Integer lockStatus;

    private Date sowingDate;

    private Integer waterCount;

    private Date lastWaterDate;

    private Integer spreadManureCount;

    private Date lastSpreadManureDate;

    private Integer flowerStatus;

    private Date changeDate;

    private String plantId;

    private Integer dayGainCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId == null ? null : treeHouseId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getLockLevel() {
        return lockLevel;
    }

    public void setLockLevel(Integer lockLevel) {
        this.lockLevel = lockLevel;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(Date sowingDate) {
        this.sowingDate = sowingDate;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(Integer waterCount) {
        this.waterCount = waterCount;
    }

    public Date getLastWaterDate() {
        return lastWaterDate;
    }

    public void setLastWaterDate(Date lastWaterDate) {
        this.lastWaterDate = lastWaterDate;
    }

    public Integer getSpreadManureCount() {
        return spreadManureCount;
    }

    public void setSpreadManureCount(Integer spreadManureCount) {
        this.spreadManureCount = spreadManureCount;
    }

    public Date getLastSpreadManureDate() {
        return lastSpreadManureDate;
    }

    public void setLastSpreadManureDate(Date lastSpreadManureDate) {
        this.lastSpreadManureDate = lastSpreadManureDate;
    }

    public Integer getFlowerStatus() {
        return flowerStatus;
    }

    public void setFlowerStatus(Integer flowerStatus) {
        this.flowerStatus = flowerStatus;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId == null ? null : plantId.trim();
    }

    public Integer getDayGainCount() {
        return dayGainCount;
    }

    public void setDayGainCount(Integer dayGainCount) {
        this.dayGainCount = dayGainCount;
    }
}