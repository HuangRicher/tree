package com.tongzhu.usergoods.po;

/**
 * Created by Administrator on 2018/11/16 0016.
 */
public class CompoundPO {


    private String basicsId;

    private String compositeId;

    private Integer amount;

    private String userId;

    private String consume;

    private Integer consumeAmount;

    public String getBasicsId() {
        return basicsId;
    }

    public void setBasicsId(String basicsId) {
        this.basicsId = basicsId;
    }

    public String getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
