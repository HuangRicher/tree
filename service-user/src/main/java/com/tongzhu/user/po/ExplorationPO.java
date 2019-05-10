package com.tongzhu.user.po;

import java.io.Serializable;

public class ExplorationPO implements Serializable {

    private String userId;
    private Integer explorationId;
    private Integer passId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
