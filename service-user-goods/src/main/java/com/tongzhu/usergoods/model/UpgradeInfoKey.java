package com.tongzhu.usergoods.model;

public class UpgradeInfoKey {
    private String upgradeId;

    private Integer level;

    public String getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(String upgradeId) {
        this.upgradeId = upgradeId == null ? null : upgradeId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}