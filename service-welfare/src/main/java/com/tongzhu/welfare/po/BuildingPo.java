package com.tongzhu.welfare.po;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BuildingPo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;//用户ID
	@ApiModelProperty(value = "建筑类型")
    private Integer buildingType;//用户ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(Integer buildingType) {
		this.buildingType = buildingType;
	}
	
	
}