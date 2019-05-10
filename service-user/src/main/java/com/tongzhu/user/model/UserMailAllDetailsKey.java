package com.tongzhu.user.model;

public class UserMailAllDetailsKey {
    private String mailallId;

    private String receiverId;

    public String getMailallId() {
        return mailallId;
    }

    public void setMailallId(String mailallId) {
        this.mailallId = mailallId == null ? null : mailallId.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }
}