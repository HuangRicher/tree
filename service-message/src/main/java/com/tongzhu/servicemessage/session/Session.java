package com.tongzhu.servicemessage.session;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable {
    // 用户唯一性标识
    private String sessionId;
    private String userId;
    private String userName;
    private Date loginDate;
    private Integer roleLevel;

    public Session() {
    }
    public Session(String sessionId,String userId, Date loginDate) {
        this.sessionId=sessionId;
        this.userId = userId;
        this.loginDate=loginDate;
    }

    public Session(String sessionId,String userId, String userName,Date loginDate,Integer roleLevel) {
        this.sessionId=sessionId;
        this.userId = userId;
        this.userName = userName;
        this.loginDate=loginDate;
        this.roleLevel=roleLevel;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
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

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
