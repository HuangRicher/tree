package com.tongzhu.user.domain;


public class PropInfoVO {

    /**
     * 背包主键id
     */
    private String id;
    /**
     * 物品主键id
     */
    private String goodsId;
    /**
     * 物品数量
     */
    private Integer amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return Integer.parseInt(goodsId);
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}