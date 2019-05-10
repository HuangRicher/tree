package com.tongzhu.servicemessage.domain;

import java.util.Date;

public class UserGoods {
    private String id;

    private String userId;

    private String goodsId;

    private Integer amount;

    private Date surplusDate;

    private Date createDate;

    private Date updateDate;

    private Integer status;

    private Integer settingPosition;

    private Integer type;

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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getSurplusDate() {
        return surplusDate;
    }

    public void setSurplusDate(Date surplusDate) {
        this.surplusDate = surplusDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSettingPosition() {
        return settingPosition;
    }

    public void setSettingPosition(Integer settingPosition) {
        this.settingPosition = settingPosition;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}