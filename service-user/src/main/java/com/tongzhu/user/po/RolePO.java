package com.tongzhu.user.po;

import java.io.Serializable;

public class RolePO implements Serializable {

    private String account;

    private String openId;

    private String userId;

    private String otherUserId;

    private Integer roleId;

    private String roleName;

    private Integer roleTitle;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(Integer roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
