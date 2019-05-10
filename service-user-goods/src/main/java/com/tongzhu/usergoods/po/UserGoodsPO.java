package com.tongzhu.usergoods.po;

import java.io.Serializable;

public class UserGoodsPO implements Serializable {
    private String id;

    private String userId;

    private String goodsId;

    private Integer type;

    private int pageNum;

    private int pageSize;

    private Integer settingPosition;

    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSettingPosition() {
        return settingPosition;
    }

    public void setSettingPosition(Integer settingPosition) {
        this.settingPosition = settingPosition;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
