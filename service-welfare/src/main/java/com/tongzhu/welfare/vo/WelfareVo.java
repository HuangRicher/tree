package com.tongzhu.welfare.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WelfareVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value = "福利ID")
    private Integer welfareId;
	@ApiModelProperty(value = "天数：1.2.3.4.5.6.7")
    private Integer dateNum;
	@ApiModelProperty(value = "奖励类型：1：金币；2：宝箱")
    private Integer rewardsType;
	@ApiModelProperty(value = "奖励数量")
    private Integer rewardsNum;
	@ApiModelProperty(value = "是否已领取: 1:不能领取;2:未领取；3：已领取")
    private Integer isReceive;
	@ApiModelProperty(value = "goodsId")
    private Integer goodsId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(Integer welfareId) {
		this.welfareId = welfareId;
	}

	public Integer getDateNum() {
		return dateNum;
	}

	public void setDateNum(Integer dateNum) {
		this.dateNum = dateNum;
	}

	public Integer getRewardsType() {
		return rewardsType;
	}

	public void setRewardsType(Integer rewardsType) {
		this.rewardsType = rewardsType;
	}

	public Integer getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
	}

	public Integer getRewardsNum() {
		return rewardsNum;
	}

	public void setRewardsNum(Integer rewardsNum) {
		this.rewardsNum = rewardsNum;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
}