package com.tongzhu.welfare.model;

public class WelfareMonthSetting {
    private Integer id;

    private Integer dateNum;

    private Integer rewardsType;

    private String rewardsContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDateNum() {
        return dateNum;
    }

    public void setDateNum(Integer dateNum) {
        this.dateNum = dateNum;
    }

    public Integer getRewardsType() {
        return rewardsType;
    }

    public void setRewardsType(Integer rewardsType) {
        this.rewardsType = rewardsType;
    }

    public String getRewardsContent() {
        return rewardsContent;
    }

    public void setRewardsContent(String rewardsContent) {
        this.rewardsContent = rewardsContent == null ? null : rewardsContent.trim();
    }
}