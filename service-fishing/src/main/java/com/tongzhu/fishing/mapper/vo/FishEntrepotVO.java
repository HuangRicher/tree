package com.tongzhu.fishing.mapper.vo;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/11 0011.
 */
public class FishEntrepotVO {

    private String userName;

    private String goodsName;

    private Date createDate;

    private Integer amount;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
