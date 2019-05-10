package com.tongzhu.treehouse.mapper.vo;

import com.tongzhu.treehouse.model.TreeHouseRoom;

import java.io.Serializable;
import java.util.Date;

public class TreeHouseRoomDO extends TreeHouseRoom implements Serializable {

    private String userName;
    private Integer sellingPrice;
    private String portraitUrl;
    private Integer userStatus;
    private Date loginDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
