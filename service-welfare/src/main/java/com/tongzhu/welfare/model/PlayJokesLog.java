package com.tongzhu.welfare.model;

public class PlayJokesLog {
    private Integer id;

    private String playJokesDay;

    private String loveTreeId;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayJokesDay() {
        return playJokesDay;
    }

    public void setPlayJokesDay(String playJokesDay) {
        this.playJokesDay = playJokesDay == null ? null : playJokesDay.trim();
    }

    public String getLoveTreeId() {
        return loveTreeId;
    }

    public void setLoveTreeId(String loveTreeId) {
        this.loveTreeId = loveTreeId == null ? null : loveTreeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}