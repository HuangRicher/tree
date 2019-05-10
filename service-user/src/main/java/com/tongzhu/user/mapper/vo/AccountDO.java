package com.tongzhu.user.mapper.vo;

import com.tongzhu.user.model.AccountUser;

import java.io.Serializable;

public class AccountDO extends AccountUser implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
