package com.tongzhu.treehouse.model;

public class TreeHouseRoomSetting {
    private Integer id;

    private Integer workerCount;

    private Integer experienceMin;

    private Integer experienceMax;

    private Integer flourishingRate;

    private Integer dayUse;

    private String consumeGoods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(Integer workerCount) {
        this.workerCount = workerCount;
    }

    public Integer getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(Integer experienceMin) {
        this.experienceMin = experienceMin;
    }

    public Integer getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(Integer experienceMax) {
        this.experienceMax = experienceMax;
    }

    public Integer getFlourishingRate() {
        return flourishingRate;
    }

    public void setFlourishingRate(Integer flourishingRate) {
        this.flourishingRate = flourishingRate;
    }

    public Integer getDayUse() {
        return dayUse;
    }

    public void setDayUse(Integer dayUse) {
        this.dayUse = dayUse;
    }

    public String getConsumeGoods() {
        return consumeGoods;
    }

    public void setConsumeGoods(String consumeGoods) {
        this.consumeGoods = consumeGoods == null ? null : consumeGoods.trim();
    }
}