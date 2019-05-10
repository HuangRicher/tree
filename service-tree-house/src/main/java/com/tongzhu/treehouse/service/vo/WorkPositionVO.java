package com.tongzhu.treehouse.service.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WorkPositionVO implements Serializable {

    private String userId;

    private String userName;

    private String workPositionId;  //

    private Boolean isWxFriend;  //是否为微信好友

    private Boolean isGameFriend;  //是否为游戏好友

    private String portraitUrl;		//头像图片

    private Integer status;    //工位状态

    private Integer workStatus;  //工位工作状态

    private Integer order;  //工位序号

    private List<Map> consume;    //解锁需消耗物品

    private Integer progress;     //打工进度

    private String incomeDetail; //收益详情

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Boolean isGameFriend() {
        return isGameFriend;
    }

    public void setGameFriend(Boolean gameFriend) {
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

    public List<Map> getConsume() {
        return consume;
    }

    public void setConsume(List<Map> consume) {
        this.consume = consume;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getWorkPositionId() {
        return workPositionId;
    }

    public void setWorkPositionId(String workPositionId) {
        this.workPositionId = workPositionId;
    }

    public Boolean isWxFriend() {
        return isWxFriend;
    }

    public void setWxFriend(Boolean wxFriend) {
        isWxFriend = wxFriend;
    }

    public String getIncomeDetail() {
        return incomeDetail;
    }

    public void setIncomeDetail(String incomeDetail) {
        this.incomeDetail = incomeDetail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
