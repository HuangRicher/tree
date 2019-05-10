package com.tongzhu.welfare.model;

import java.io.Serializable;

public class BuildingMarriageSetting implements Serializable {
    private Integer id;

    private Integer grade;

    private Integer upgradeGold;

    private Integer upgradeTime;

    private Integer placedNum;

    private Integer roomGrade;

    @Override  
    public String toString() {  
        return "BuildingMarriageSetting [id=" + id + 
        		", upgradeGold=" + upgradeGold +
        		", upgradeTime=" + upgradeTime + 
        		", placedNum=" + placedNum + 
        		", roomGrade=" + roomGrade + 
        		", grade=" + grade +
        		"]";
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUpgradeGold() {
        return upgradeGold;
    }

    public void setUpgradeGold(Integer upgradeGold) {
        this.upgradeGold = upgradeGold;
    }

    public Integer getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(Integer upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public Integer getPlacedNum() {
        return placedNum;
    }

    public void setPlacedNum(Integer placedNum) {
        this.placedNum = placedNum;
    }

    public Integer getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(Integer roomGrade) {
        this.roomGrade = roomGrade;
    }
}