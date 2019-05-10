package com.tongzhu.user.model;

public class ExplorationBind {
    private String id;

    private String userId;

    private Integer explorationId;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}