package com.tongzhu.friend.mapper.vo;


import com.tongzhu.friend.model.Friend;

import java.io.Serializable;

public class FriendVO extends Friend implements Serializable {

    private int amount; //用户拥有金币数量

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
