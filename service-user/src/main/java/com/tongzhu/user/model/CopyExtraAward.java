package com.tongzhu.user.model;

public class CopyExtraAward {
    private String id;

    private Integer level;

    private Integer amount;

    private String goodsId;

    private Float rate;

    private String name;

    private String consumeGoods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getConsumeGoods() {
        return consumeGoods;
    }

    public void setConsumeGoods(String consumeGoods) {
        this.consumeGoods = consumeGoods == null ? null : consumeGoods.trim();
    }
}