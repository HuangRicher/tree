package com.tongzhu.usergoods.model;

public class UpgradeInfo {
    private Integer id;

    private String upgradeId;

    private Integer upgradeAmount;

    private String consume;

    private Integer consumeAmount;

    private Integer type;

    private String wearPosition;

    private Integer quality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(String upgradeId) {
        this.upgradeId = upgradeId == null ? null : upgradeId.trim();
    }

    public Integer getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(Integer upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume == null ? null : consume.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWearPosition() {
        return wearPosition;
    }

    public void setWearPosition(String wearPosition) {
        this.wearPosition = wearPosition == null ? null : wearPosition.trim();
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}