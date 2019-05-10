package com.tongzhu.usergoods.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/19 0019.
 */
public class GemPO implements Serializable {

    private String userId;

    private String goodsId;

    private String gemId;

    private String gemRhombus;
    private String consumeRhombus;
    private Integer amountRhombus;

    private String gemRoundness;
    private String consumeRoundness;
    private Integer amountRoundness;

    private String gemHexagon;
    private String consumeHexagon;
    private Integer amountHexagon;

    private String consume;
    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGemId() {
        return gemId;
    }

    public void setGemId(String gemId) {
        this.gemId = gemId;
    }

    public String getGemRhombus() {
        return gemRhombus;
    }

    public void setGemRhombus(String gemRhombus) {
        this.gemRhombus = gemRhombus;
    }

    public String getConsumeRhombus() {
        return consumeRhombus;
    }

    public void setConsumeRhombus(String consumeRhombus) {
        this.consumeRhombus = consumeRhombus;
    }

    public Integer getAmountRhombus() {
        return amountRhombus;
    }

    public void setAmountRhombus(Integer amountRhombus) {
        this.amountRhombus = amountRhombus;
    }

    public String getGemRoundness() {
        return gemRoundness;
    }

    public void setGemRoundness(String gemRoundness) {
        this.gemRoundness = gemRoundness;
    }

    public String getConsumeRoundness() {
        return consumeRoundness;
    }

    public void setConsumeRoundness(String consumeRoundness) {
        this.consumeRoundness = consumeRoundness;
    }

    public Integer getAmountRoundness() {
        return amountRoundness;
    }

    public void setAmountRoundness(Integer amountRoundness) {
        this.amountRoundness = amountRoundness;
    }

    public String getGemHexagon() {
        return gemHexagon;
    }

    public void setGemHexagon(String gemHexagon) {
        this.gemHexagon = gemHexagon;
    }

    public String getConsumeHexagon() {
        return consumeHexagon;
    }

    public void setConsumeHexagon(String consumeHexagon) {
        this.consumeHexagon = consumeHexagon;
    }

    public Integer getAmountHexagon() {
        return amountHexagon;
    }

    public void setAmountHexagon(Integer amountHexagon) {
        this.amountHexagon = amountHexagon;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
