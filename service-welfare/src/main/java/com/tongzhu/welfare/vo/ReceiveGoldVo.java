package com.tongzhu.welfare.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ReceiveGoldVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "物品唯一ID")
    private String id;
	@ApiModelProperty(value = "物品的表ID")
    private int goodsId;
	@ApiModelProperty(value = "类型:1:道具；2：装备；3：武器")
    private int type;
	@ApiModelProperty(value = "数量")
    private int amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}