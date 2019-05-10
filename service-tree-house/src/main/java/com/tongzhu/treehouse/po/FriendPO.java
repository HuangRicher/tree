package com.tongzhu.treehouse.po;

import java.io.Serializable;

public class FriendPO implements Serializable {
    private String userId; //用户ID

    private String friendId; //好友用户ID

    private int type; //同意：1  拒绝：2

    private int pageNumber; //当前页码

    private int pageSize;  //每页显示记录数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
