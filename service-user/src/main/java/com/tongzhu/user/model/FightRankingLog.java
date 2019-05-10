package com.tongzhu.user.model;

import java.util.Date;

public class FightRankingLog {
    private String id;

    private String userId;

    private String participantId;

    private Date fightDate;

    private Integer fightResult;

    private Integer changeScore;

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

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId == null ? null : participantId.trim();
    }

    public Date getFightDate() {
        return fightDate;
    }

    public void setFightDate(Date fightDate) {
        this.fightDate = fightDate;
    }

    public Integer getFightResult() {
        return fightResult;
    }

    public void setFightResult(Integer fightResult) {
        this.fightResult = fightResult;
    }

    public Integer getChangeScore() {
        return changeScore;
    }

    public void setChangeScore(Integer changeScore) {
        this.changeScore = changeScore;
    }
}