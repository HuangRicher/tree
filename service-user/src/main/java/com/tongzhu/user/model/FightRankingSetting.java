package com.tongzhu.user.model;

public class FightRankingSetting {
    private Integer rankId;

    private Integer grade;

    private String name;

    private Integer maxRank;

    private Integer minRank;

    private Integer firstRank;

    private Integer awardMoneyDay;

    private Integer awardMoneySeason;

    private Integer awardHonorSeason;

    private Integer afterRankId;

    private Integer afterGrade;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(Integer maxRank) {
        this.maxRank = maxRank;
    }

    public Integer getMinRank() {
        return minRank;
    }

    public void setMinRank(Integer minRank) {
        this.minRank = minRank;
    }

    public Integer getFirstRank() {
        return firstRank;
    }

    public void setFirstRank(Integer firstRank) {
        this.firstRank = firstRank;
    }

    public Integer getAwardMoneyDay() {
        return awardMoneyDay;
    }

    public void setAwardMoneyDay(Integer awardMoneyDay) {
        this.awardMoneyDay = awardMoneyDay;
    }

    public Integer getAwardMoneySeason() {
        return awardMoneySeason;
    }

    public void setAwardMoneySeason(Integer awardMoneySeason) {
        this.awardMoneySeason = awardMoneySeason;
    }

    public Integer getAwardHonorSeason() {
        return awardHonorSeason;
    }

    public void setAwardHonorSeason(Integer awardHonorSeason) {
        this.awardHonorSeason = awardHonorSeason;
    }

    public Integer getAfterRankId() {
        return afterRankId;
    }

    public void setAfterRankId(Integer afterRankId) {
        this.afterRankId = afterRankId;
    }

    public Integer getAfterGrade() {
        return afterGrade;
    }

    public void setAfterGrade(Integer afterGrade) {
        this.afterGrade = afterGrade;
    }
}