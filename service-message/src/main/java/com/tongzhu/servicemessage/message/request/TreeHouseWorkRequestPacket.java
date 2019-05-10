package com.tongzhu.servicemessage.message.request;

import java.io.Serializable;

public class TreeHouseWorkRequestPacket extends RequestPacket implements Serializable {

    private String treeHouseUserId;
    private Integer workType;
    private Integer grade;

    public TreeHouseWorkRequestPacket() {
        super();
    }

    public TreeHouseWorkRequestPacket(String type, String content, String chatWay, String treeHouseUserId, Integer workType, Integer grade) {
        super(type, content, chatWay);
        this.treeHouseUserId = treeHouseUserId;
        this.workType = workType;
        this.grade = grade;
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
