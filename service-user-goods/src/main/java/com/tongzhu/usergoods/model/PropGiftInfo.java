package com.tongzhu.usergoods.model;

public class PropGiftInfo {
    private String id;

    private String propId;

    private Integer stdmodeAmount;

    private Integer status;

    private Integer vipLevel;

    private String giftBagItems;

    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId == null ? null : propId.trim();
    }

    public Integer getStdmodeAmount() {
        return stdmodeAmount;
    }

    public void setStdmodeAmount(Integer stdmodeAmount) {
        this.stdmodeAmount = stdmodeAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getGiftBagItems() {
        return giftBagItems;
    }

    public void setGiftBagItems(String giftBagItems) {
        this.giftBagItems = giftBagItems == null ? null : giftBagItems.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}