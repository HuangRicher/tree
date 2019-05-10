package com.tongzhu.user.model;

public class ExplorationUser {
    private String id;

    private String userId;

    private Integer explorationId;

    private Integer passId;

    private Integer starCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getExplorationId() {
        return explorationId;
    }

    public void setExplorationId(Integer explorationId) {
        this.explorationId = explorationId;
    }

    public Integer getPassId() {
        return passId;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }
}