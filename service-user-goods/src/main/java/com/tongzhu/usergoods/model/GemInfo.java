package com.tongzhu.usergoods.model;

public class GemInfo {
    private String id;

    private String consume;

    private Integer consumeAmount;

    private Integer gemType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume == null ? null : consume.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getGemType() {
        return gemType;
    }

    public void setGemType(Integer gemType) {
        this.gemType = gemType;
    }
}