package com.tongzhu.treehouse.model;

public class WorkPositionSetting {
    private Integer id;

    private Integer workTypeId;

    private Integer positionOrder;

    private String unlockGoods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Integer getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(Integer positionOrder) {
        this.positionOrder = positionOrder;
    }

    public String getUnlockGoods() {
        return unlockGoods;
    }

    public void setUnlockGoods(String unlockGoods) {
        this.unlockGoods = unlockGoods == null ? null : unlockGoods.trim();
    }
}