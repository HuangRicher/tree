package com.tongzhu.welfare.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WeddingLogVo {
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "配偶名")
    private String otherName;
    @ApiModelProperty(value = "序号")
    private Integer orderNo;
    @ApiModelProperty(value = "时间")
    private Date createdTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}