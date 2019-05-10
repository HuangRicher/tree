package com.tongzhu.user.po;

import java.io.Serializable;

public class FightRankingPO implements Serializable {
    private String userId;

    private String participantId;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
