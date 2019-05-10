package com.tongzhu.treehouse.service.vo;

import java.io.Serializable;
import java.util.Date;

public class TreeHousePurchaseHistoryVO implements Serializable {

    private Date creationTime;
    private String sellUserId;
    private String sellName;
    private String bargainorUserId;
    private String bargainorName;
    private String purchaserUserId;
    private String purchaserName;
    private Integer monetaryAmount;
    private Integer sellingPrice;
    private Integer type;
    private String id;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getBargainorUserId() {
        return bargainorUserId;
    }

    public void setBargainorUserId(String bargainorUserId) {
        this.bargainorUserId = bargainorUserId;
    }

    public String getBargainorName() {
        return bargainorName;
    }

    public void setBargainorName(String bargainorName) {
        this.bargainorName = bargainorName;
    }

    public String getPurchaserUserId() {
        return purchaserUserId;
    }

    public void setPurchaserUserId(String purchaserUserId) {
        this.purchaserUserId = purchaserUserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
