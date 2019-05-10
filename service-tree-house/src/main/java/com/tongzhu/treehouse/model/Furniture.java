package com.tongzhu.treehouse.model;

public class Furniture {
    private String id;

    private String name;

    private String categoryId;

    private Integer useableTime;

    private Integer type;

    private Integer location;

    private Integer outputRate;

    private Integer outputValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Integer getUseableTime() {
        return useableTime;
    }

    public void setUseableTime(Integer useableTime) {
        this.useableTime = useableTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getOutputRate() {
        return outputRate;
    }

    public void setOutputRate(Integer outputRate) {
        this.outputRate = outputRate;
    }

    public Integer getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(Integer outputValue) {
        this.outputValue = outputValue;
    }
}