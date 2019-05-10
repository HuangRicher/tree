package com.tongzhu.treehouse.mapper.vo;

import java.io.Serializable;

public class TreeHouseRoomCountVO implements Serializable {

    private String userId;
    private int amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
