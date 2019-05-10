package com.tongzhu.user.service.vo;

public class CopyExtraAwardVO {

    private String id;

    private Integer level;

    private Integer amount;

    private String goodsId;

    private Object consumeGoods;

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
        this.goodsId = goodsId;
    }

    public Object getConsumeGoods() {
        return consumeGoods;
    }

    public void setConsumeGoods(Object consumeGoods) {
        this.consumeGoods = consumeGoods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
