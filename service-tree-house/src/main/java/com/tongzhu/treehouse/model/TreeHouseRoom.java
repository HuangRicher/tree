package com.tongzhu.treehouse.model;

import java.util.Date;

public class TreeHouseRoom {
    private String id;

    private String userId;

    private String treeHouseId;

    private Integer roomId;

    private String workerId;

    private Integer workTypeId;

    private Integer status;

    private Date protectStartDate;

    private Date protectEndDate;

    private Date reducePriceDate;

    private Boolean isWxFriend;

    private Boolean isGameFriend;

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

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId == null ? null : treeHouseId.trim();
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId == null ? null : workerId.trim();
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getProtectStartDate() {
        return protectStartDate;
    }

    public void setProtectStartDate(Date protectStartDate) {
        this.protectStartDate = protectStartDate;
    }

    public Date getProtectEndDate() {
        return protectEndDate;
    }

    public void setProtectEndDate(Date protectEndDate) {
        this.protectEndDate = protectEndDate;
    }

    public Date getReducePriceDate() {
        return reducePriceDate;
    }

    public void setReducePriceDate(Date reducePriceDate) {
        this.reducePriceDate = reducePriceDate;
    }

    public Boolean getIsWxFriend() {
        return isWxFriend;
    }

    public void setIsWxFriend(Boolean isWxFriend) {
        this.isWxFriend = isWxFriend;
    }

    public Boolean getIsGameFriend() {
        return isGameFriend;
    }

    public void setIsGameFriend(Boolean isGameFriend) {
        this.isGameFriend = isGameFriend;
    }
}