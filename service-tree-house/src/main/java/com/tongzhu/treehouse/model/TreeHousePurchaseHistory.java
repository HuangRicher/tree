package com.tongzhu.treehouse.model;

import java.util.Date;

public class TreeHousePurchaseHistory {
    private String id;

    private Date creationStartDate;

    private Date recomposeEndDate;

    private String sellUserId;

    private Integer monetaryAmount;

    private Integer sellingPrice;

    private String purchaserUserId;

    private String bargainorUserId;

    private Integer type;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreationStartDate() {
        return creationStartDate;
    }

    public void setCreationStartDate(Date creationStartDate) {
        this.creationStartDate = creationStartDate;
    }

    public Date getRecomposeEndDate() {
        return recomposeEndDate;
    }

    public void setRecomposeEndDate(Date recomposeEndDate) {
        this.recomposeEndDate = recomposeEndDate;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId == null ? null : sellUserId.trim();
    }

    public Integer getMonetaryAmount() {
        return monetaryAmount;
    }

    public void setMonetaryAmount(Integer monetaryAmount) {
        this.monetaryAmount = monetaryAmount;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPurchaserUserId() {
        return purchaserUserId;
    }

    public void setPurchaserUserId(String purchaserUserId) {
        this.purchaserUserId = purchaserUserId == null ? null : purchaserUserId.trim();
    }

    public String getBargainorUserId() {
        return bargainorUserId;
    }

    public void setBargainorUserId(String bargainorUserId) {
        this.bargainorUserId = bargainorUserId == null ? null : bargainorUserId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}