package com.tongzhu.usergoods.po;

/**
 * Created by Administrator on 2018/12/22 0022.
 */
public class UpgradePO {
    private String upgradeId;

    private Integer upgradeAmount;

    private String consume;

    private Integer consumeAmount;

    private String userId;

    private String goodsId;

    private Integer afterGoodsId;

    private Integer quality;

    private String id;

    public String getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(String upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Integer getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(Integer upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

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

    public Integer getAfterGoodsId() {
        return afterGoodsId;
    }

    public void setAfterGoodsId(Integer afterGoodsId) {
        this.afterGoodsId = afterGoodsId;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
