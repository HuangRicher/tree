package com.tongzhu.welfare.po;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SendFlowersPo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value = "收花人ID")
    private String receiveId;
	@ApiModelProperty(value = "送花goodsId")
    private Integer goodsId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	
}