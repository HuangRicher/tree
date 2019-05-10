package com.tongzhu.welfare.model;

public class WelfareBoxSetting {
    private Integer id;

    private Integer boxType;

    private String rewards;

    private String boxName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards == null ? null : rewards.trim();
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }
}