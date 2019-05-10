package com.tongzhu.welfare.model;

import java.util.Date;

public class BuildingInfo {
    private Integer id;

    private String name;

    private Integer type;

    private String detailed;

    private Integer openGrade;

    private Integer openCondition;

    private Integer outputGold;

    private Integer havePlaced;

    private Date createDate;

    private Date updateDate;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed == null ? null : detailed.trim();
    }

    public Integer getOpenGrade() {
        return openGrade;
    }

    public void setOpenGrade(Integer openGrade) {
        this.openGrade = openGrade;
    }

    public Integer getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(Integer openCondition) {
        this.openCondition = openCondition;
    }

    public Integer getOutputGold() {
        return outputGold;
    }

    public void setOutputGold(Integer outputGold) {
        this.outputGold = outputGold;
    }

    public Integer getHavePlaced() {
        return havePlaced;
    }

    public void setHavePlaced(Integer havePlaced) {
        this.havePlaced = havePlaced;
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
}