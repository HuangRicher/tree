package com.tongzhu.treehouse.service.vo;


import java.io.Serializable;
import java.util.List;


public class UserWorkTypeVO implements Serializable {

    private Integer status;

    private Integer workTypeId;

    private Integer level;

    private Integer unlockLevel;

    private List<WorkPositionVO> positionList;

    private boolean haveIncome;

    private String totalYieldRate;//工种下的所有收益率（按分钟）

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUnlockLevel() {
        return unlockLevel;
    }

    public void setUnlockLevel(Integer unlockLevel) {
        this.unlockLevel = unlockLevel;
    }

    public List<WorkPositionVO> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<WorkPositionVO> positionList) {
        this.positionList = positionList;
    }

    public boolean isHaveIncome() {
        return haveIncome;
    }

    public void setHaveIncome(boolean haveIncome) {
        this.haveIncome = haveIncome;
    }

    public String getTotalYieldRate() {
        return totalYieldRate;
    }

    public void setTotalYieldRate(String totalYieldRate) {
        this.totalYieldRate = totalYieldRate;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }
}
