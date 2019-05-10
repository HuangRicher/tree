package com.tongzhu.user.po;

import java.io.Serializable;
import java.util.List;

public class FightingPO implements Serializable {
    private String userId;
    private String opponentId;
    private Integer type;
    private List<String> selfIds;
    private List<String> opponentIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSelfIds() {
        return selfIds;
    }

    public void setSelfIds(List<String> selfIds) {
        this.selfIds = selfIds;
    }

    public List<String> getOpponentIds() {
        return opponentIds;
    }

    public void setOpponentIds(List<String> opponentIds) {
        this.opponentIds = opponentIds;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
