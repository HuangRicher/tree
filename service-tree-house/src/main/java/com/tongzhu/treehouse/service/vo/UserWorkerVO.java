package com.tongzhu.treehouse.service.vo;

import java.io.Serializable;

public class UserWorkerVO implements Serializable {

    private String userId;     //宅友用户ID

    private boolean isWXFriend;  //是否为微信好友

    private boolean isGameFriend;  //是否为游戏好友

    private String portraitUrl;		//头像图片

    private String name;

    private Integer status;   //派工状态

    private Integer workTypeId;  //派工类型ID

    public boolean isWxFriend() {
        return isWXFriend;
    }

    public void setWXFriend(boolean WXFriend) {
        isWXFriend = WXFriend;
    }

    public boolean isGameFriend() {
        return isGameFriend;
    }

    public void setGameFriend(boolean gameFriend) {
        isGameFriend = gameFriend;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
