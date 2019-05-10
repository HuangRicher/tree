package com.tongzhu.fishing.model;

import java.util.Date;

public class FishingSecretOperation {
    private Integer id;

    private Integer numberFishing;

    private Date createDate;

    private Date updateDate;

    private String commonFishAwards;

    private Integer commonTotal;

    private String seniorFishAwards;

    private Integer seniorTotal;

    private String rareFishAwards;

    private Integer rareTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberFishing() {
        return numberFishing;
    }

    public void setNumberFishing(Integer numberFishing) {
        this.numberFishing = numberFishing;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCommonFishAwards() {
        return commonFishAwards;
    }

    public void setCommonFishAwards(String commonFishAwards) {
        this.commonFishAwards = commonFishAwards == null ? null : commonFishAwards.trim();
    }

    public Integer getCommonTotal() {
        return commonTotal;
    }

    public void setCommonTotal(Integer commonTotal) {
        this.commonTotal = commonTotal;
    }

    public String getSeniorFishAwards() {
        return seniorFishAwards;
    }

    public void setSeniorFishAwards(String seniorFishAwards) {
        this.seniorFishAwards = seniorFishAwards == null ? null : seniorFishAwards.trim();
    }

    public Integer getSeniorTotal() {
        return seniorTotal;
    }

    public void setSeniorTotal(Integer seniorTotal) {
        this.seniorTotal = seniorTotal;
    }

    public String getRareFishAwards() {
        return rareFishAwards;
    }

    public void setRareFishAwards(String rareFishAwards) {
        this.rareFishAwards = rareFishAwards == null ? null : rareFishAwards.trim();
    }

    public Integer getRareTotal() {
        return rareTotal;
    }

    public void setRareTotal(Integer rareTotal) {
        this.rareTotal = rareTotal;
    }
}