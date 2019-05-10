package com.tongzhu.usergoods.model;

public class CompoundInfo extends CompoundInfoKey {
    private Integer basicItemsAmount;

    private Integer compositeType;

    private Integer compositeAmount;

    private String cost;

    private Integer costAmount;

    public Integer getBasicItemsAmount() {
        return basicItemsAmount;
    }

    public void setBasicItemsAmount(Integer basicItemsAmount) {
        this.basicItemsAmount = basicItemsAmount;
    }

    public Integer getCompositeType() {
        return compositeType;
    }

    public void setCompositeType(Integer compositeType) {
        this.compositeType = compositeType;
    }

    public Integer getCompositeAmount() {
        return compositeAmount;
    }

    public void setCompositeAmount(Integer compositeAmount) {
        this.compositeAmount = compositeAmount;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    public Integer getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Integer costAmount) {
        this.costAmount = costAmount;
    }
}