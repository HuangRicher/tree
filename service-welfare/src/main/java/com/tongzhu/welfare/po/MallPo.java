package com.tongzhu.welfare.po;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MallPo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value = "商品标签")
    private Integer goodsType;//用户ID
	@ApiModelProperty(value = "页号")
    private int pageNum;
	@ApiModelProperty(value = "每页大小")
    private int pageSize;
	@ApiModelProperty(value = "购买的商品iD")
    private Integer goodsId;
	@ApiModelProperty(value = "购买的商品数量")
    private Integer num;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}