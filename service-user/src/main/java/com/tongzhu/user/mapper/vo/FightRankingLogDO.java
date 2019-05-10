package com.tongzhu.user.mapper.vo;

import com.tongzhu.user.model.FightRankingLog;

import java.io.Serializable;

public class FightRankingLogDO extends FightRankingLog implements Serializable {

    private String participantName;

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
