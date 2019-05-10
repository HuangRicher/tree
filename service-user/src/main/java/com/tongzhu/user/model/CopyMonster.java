package com.tongzhu.user.model;

public class CopyMonster {
    private String id;

    private Integer copyId;

    private String monsterId;

    private Integer groupId;

    private Integer limit;

    private Integer randomStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public String getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId == null ? null : monsterId.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getRandomStatus() {
        return randomStatus;
    }

    public void setRandomStatus(Integer randomStatus) {
        this.randomStatus = randomStatus;
    }
}