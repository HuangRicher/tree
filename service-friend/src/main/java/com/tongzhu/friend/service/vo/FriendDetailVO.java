package com.tongzhu.friend.service.vo;

import java.io.Serializable;

public class FriendDetailVO implements Serializable {
    private String userId;     //好友用户ID

    private String name;   //好友姓名

    private String autograph;   //个性签名

    private Integer  quIntegeressence; //精粹

    private String onlineState; //在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前

    private String portraitUrl;//头像URL

    private Integer status;//状态 0-自由身 1-打工中

    private Integer sellingPrice; //金币数量(身价)

    private Integer workerCount;  //宅友数量

    private Integer chivalrousCount;  //侠义值

    private String petName; //守护兽

    private String masterName;//主人姓名

    private Boolean isGameFriend; //是否为游戏好友

    private String protectTimeDetail; //保护时间详情

    private Boolean canReduceSellingPrice;//是否可以降低宅友身价

    private Integer roleId;

    private Integer roleLevel;

    private Integer sex;

    private Integer intimacy;

    private Boolean isLovers;

    private String maxFriendNumInfo;

    public Boolean getIsGameFriend() {
        return isGameFriend;
    }

    public void setIsGameFriend(Boolean isGameFriend) {
        this.isGameFriend = isGameFriend;
    }

    public Boolean getCanReduceSellingPrice() {
        return canReduceSellingPrice;
    }

    public void setCanReduceSellingPrice(Boolean canReduceSellingPrice) {
        this.canReduceSellingPrice = canReduceSellingPrice;
    }

    public String getProtectTimeDetail() {
        return protectTimeDetail;
    }

    public void setProtectTimeDetail(String protectTimeDetail) {
        this.protectTimeDetail = protectTimeDetail;
    }

    public Integer getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(Integer workerCount) {
        this.workerCount = workerCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuIntegeressence() {
        return quIntegeressence;
    }

    public void setQuIntegeressence(Integer quIntegeressence) {
        this.quIntegeressence = quIntegeressence;
    }

    public String getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(String onlineState) {
        this.onlineState = onlineState;
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getChivalrousCount() {
        return chivalrousCount;
    }

    public void setChivalrousCount(Integer chivalrousCount) {
        this.chivalrousCount = chivalrousCount;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGameFriend() {
        return isGameFriend;
    }

    public void setGameFriend(Boolean gameFriend) {
        isGameFriend = gameFriend;
    }

    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
    }

    public Boolean getIsLovers() {
        return isLovers;
    }

    public void setLovers(Boolean lovers) {
        isLovers = lovers;
    }

    public String getMaxFriendNumInfo() {
        return maxFriendNumInfo;
    }

    public void setMaxFriendNumInfo(String maxFriendNumInfo) {
        this.maxFriendNumInfo = maxFriendNumInfo;
    }
}
