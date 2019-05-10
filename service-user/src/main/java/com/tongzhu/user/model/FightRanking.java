package com.tongzhu.user.model;

public class FightRanking {
    private String userId;

    private Integer ranking;

    private Integer grade;

    private Integer fightYear;

    private Integer fightSeason;

    private Integer score;

    private Integer honor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getFightYear() {
        return fightYear;
    }

    public void setFightYear(Integer fightYear) {
        this.fightYear = fightYear;
    }

    public Integer getFightSeason() {
        return fightSeason;
    }

    public void setFightSeason(Integer fightSeason) {
        this.fightSeason = fightSeason;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHonor() {
        return honor;
    }

    public void setHonor(Integer honor) {
        this.honor = honor;
    }
}