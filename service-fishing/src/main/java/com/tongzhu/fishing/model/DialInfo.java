package com.tongzhu.fishing.model;

import java.util.Date;

public class DialInfo {
    private Integer id;

    private String name;

    private Integer probability;

    private Date createDate;

    private Date updateDate;

    private Integer status;

    private String award;

    private Integer count;

    private Integer forHeavy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getForHeavy() {
        return forHeavy;
    }

    public void setForHeavy(Integer forHeavy) {
        this.forHeavy = forHeavy;
    }
}