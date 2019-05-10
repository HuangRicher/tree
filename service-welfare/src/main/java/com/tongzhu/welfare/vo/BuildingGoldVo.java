package com.tongzhu.welfare.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BuildingGoldVo implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@ApiModelProperty(value = "建筑类型")
    private int buildingType;
	@ApiModelProperty(value = "建筑金币状态：1：不可领金币；2：可领金币")
    private int goldStatus;
	
	public int getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(int buildingType) {
		this.buildingType = buildingType;
	}
	public int getGoldStatus() {
		return goldStatus;
	}
	public void setGoldStatus(int goldStatus) {
		this.goldStatus = goldStatus;
	}
}