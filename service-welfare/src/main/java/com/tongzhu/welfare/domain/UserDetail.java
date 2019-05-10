package com.tongzhu.welfare.domain;

import java.io.Serializable;

public class UserDetail implements Serializable {

    //用户ID
    private String userId;

    //用户名称
    private String userName;

    //头像
    private String portraitUrl;

    //身份状态  0：自由身 1：工作中
    private Integer status;

    //身价
    private Integer sellingPrice;

    //工作状态 0：空闲中，1：工作中
    private Integer workStatus;

    //宅友数量
    private String workerCount;

    //主人名称
    private String masterName;

    //个性签名
    private String autograph;

    //留言分页数据
    private Object data;

    //是否能留言(好友才可留言)
    private Boolean canSendMessage;

    //是否为游戏好友
    private Boolean isGameFriend;

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(String workerCount) {
        this.workerCount = workerCount;
    }

    public Boolean getCanSendMessage() {
        return canSendMessage;
    }

    public void setCanSendMessage(Boolean canSendMessage) {
        this.canSendMessage = canSendMessage;
    }

    public Boolean getIsGameFriend() {
        return isGameFriend;
    }

    public void setIsGameFriend(Boolean isGameFriend) {
        this.isGameFriend = isGameFriend;
    }
}
