package com.tongzhu.treehouse.domain;

public class UserDetail {
    private String userId;
    private String portraitUrl;
    private Integer sellingPrice;
    private String userName;
    private Boolean isGameFriend;
    private Integer status;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsGameFriend() {
        return isGameFriend;
    }

    public void setGameFriend(Boolean gameFriend) {
        isGameFriend = gameFriend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
