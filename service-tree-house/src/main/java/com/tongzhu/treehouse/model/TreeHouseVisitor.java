package com.tongzhu.treehouse.model;

public class TreeHouseVisitor {
    private String id;

    private String treeHouseId;

    private String visitorId;

    private Integer status;

    private Boolean canSpeak;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId == null ? null : treeHouseId.trim();
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId == null ? null : visitorId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getCanSpeak() {
        return canSpeak;
    }

    public void setCanSpeak(Boolean canSpeak) {
        this.canSpeak = canSpeak;
    }
}