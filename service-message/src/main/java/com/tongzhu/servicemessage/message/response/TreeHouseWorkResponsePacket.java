package com.tongzhu.servicemessage.message.response;

import java.io.Serializable;

public class TreeHouseWorkResponsePacket extends ResponsePacket implements Serializable {

    private String senderName;
    private String senderUserId;
    private Integer sex;
    private String header;
    private String address;
    private Integer vip;
    private String treeHouseUserId;
    private Integer workType;
    private Integer grade;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public String getTreeHouseUserId() {
        return treeHouseUserId;
    }

    public void setTreeHouseUserId(String treeHouseUserId) {
        this.treeHouseUserId = treeHouseUserId;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
