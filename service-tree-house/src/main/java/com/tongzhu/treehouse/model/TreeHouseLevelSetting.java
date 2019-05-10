package com.tongzhu.treehouse.model;

public class TreeHouseLevelSetting {
    private Integer id;

    private Integer level;

    private Integer cultureValue;

    private Integer workTypeId;

    private Integer workTypeLevel;

    private Integer experienceMin;

    private Integer experienceMax;

    private String upgradeConsumeGoods;

    private String upgradeAward;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCultureValue() {
        return cultureValue;
    }

    public void setCultureValue(Integer cultureValue) {
        this.cultureValue = cultureValue;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Integer getWorkTypeLevel() {
        return workTypeLevel;
    }

    public void setWorkTypeLevel(Integer workTypeLevel) {
        this.workTypeLevel = workTypeLevel;
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

    public String getUpgradeConsumeGoods() {
        return upgradeConsumeGoods;
    }

    public void setUpgradeConsumeGoods(String upgradeConsumeGoods) {
        this.upgradeConsumeGoods = upgradeConsumeGoods == null ? null : upgradeConsumeGoods.trim();
    }

    public String getUpgradeAward() {
        return upgradeAward;
    }

    public void setUpgradeAward(String upgradeAward) {
        this.upgradeAward = upgradeAward == null ? null : upgradeAward.trim();
    }
}