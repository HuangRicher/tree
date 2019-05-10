package com.tongzhu.welfare.po;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WelfarePo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value = "福利天数")
    private Integer dataNum;//用户ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDataNum() {
		return dataNum;
	}

	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}
	
	
}