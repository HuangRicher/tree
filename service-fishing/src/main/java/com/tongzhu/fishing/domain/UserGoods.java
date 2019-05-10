package com.tongzhu.fishing.domain;

import java.util.Date;

public class UserGoods extends UserGoodsKey {
    private String id;

    private Integer amount;

    private Integer rewardId;

    private Integer rewardAmount;

    private Date surplusDate;

    private Date createDate;

    private Date updateDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}