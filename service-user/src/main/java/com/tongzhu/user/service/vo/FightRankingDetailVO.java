package com.tongzhu.user.service.vo;

import com.tongzhu.user.mapper.vo.FightRankingLogDO;
import com.tongzhu.user.model.FightRankingLog;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FightRankingDetailVO implements Serializable {

    private String seasonName;

    private Map<String,Object> seasonAward;

    private String currentGrade;

    private Integer currentScore;

    private Integer fullScore;

    private Integer currentHonor;

    private Float currentWinRate;

    private Integer fightCount;

    private List<FightRankingLogDO> fightRankingLogList;

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public Map<String, Object> getSeasonAward() {
        return seasonAward;
    }

    public void setSeasonAward(Map<String, Object> seasonAward) {
        this.seasonAward = seasonAward;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = currentGrade;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getFullScore() {
        return fullScore;
    }

    public void setFullScore(Integer fullScore) {
        this.fullScore = fullScore;
    }

    public Integer getCurrentHonor() {
        return currentHonor;
    }

    public void setCurrentHonor(Integer currentHonor) {
        this.currentHonor = currentHonor;
    }

    public Float getCurrentWinRate() {
        return currentWinRate;
    }

    public void setCurrentWinRate(Float currentWinRate) {
        this.currentWinRate = currentWinRate;
    }

    public Integer getFightCount() {
        return fightCount;
    }

    public void setFightCount(Integer fightCount) {
        this.fightCount = fightCount;
    }

    public List<FightRankingLogDO> getFightRankingLogList() {
        return fightRankingLogList;
    }

    public void setFightRankingLogList(List<FightRankingLogDO> fightRankingLogList) {
        this.fightRankingLogList = fightRankingLogList;
    }
}
