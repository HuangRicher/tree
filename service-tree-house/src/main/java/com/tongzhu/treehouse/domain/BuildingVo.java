package com.tongzhu.treehouse.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class BuildingVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "建筑名称")
    private String buildingName;
	@ApiModelProperty(value = "建筑类型")
    private int buildingType;
	@ApiModelProperty(value = "角色等级")
    private int roleGrade;
	@ApiModelProperty(value = "当前等级")
    private int currentGrade;
	@ApiModelProperty(value = "下一当前等级")
    private int nextCurrentGrade;
	@ApiModelProperty(value = "金币产出速率，为0时表示当前建筑不使用该参数")
    private int goldOutPut;
	@ApiModelProperty(value = "容纳人数，为0时表示当前建筑不使用该参数")
    private int containNum;
	@ApiModelProperty(value = "容纳物件，为0时表示当前建筑不使用该参数")
    private int containThingNum;
	@ApiModelProperty(value = "下一角色等级")
    private int nextRoleGrade;
	@ApiModelProperty(value = "下一金币产出速率，为0时表示当前建筑不使用该参数")
    private int nextGoldOutPut;
	@ApiModelProperty(value = "下一容纳人数，为0时表示当前建筑不使用该参数")
    private int nextContainNum;
	@ApiModelProperty(value = "下一容纳物件，为0时表示当前建筑不使用该参数")
    private int nextContainThingNum;
	@ApiModelProperty(value = "最大金币容量，为0时表示当前建筑不使用该参数")
    private int goldMax;
	@ApiModelProperty(value = "下一最大金币容量，为0时表示当前建筑不使用该参数")
    private int nextGoldMax;
	@ApiModelProperty(value = "升级消耗金币")
    private int updateGold;
	@ApiModelProperty(value = "升级消耗时间")
    private int updateTime;
	@ApiModelProperty(value = "当前建筑状态：1：升级中；2：升级完成;3：可升级")
    private int buildingStatus;
	@ApiModelProperty(value = "人气值")
    private int ambienceCount;
	@ApiModelProperty(value = "娱乐值")
    private int amusementCount;
	@ApiModelProperty(value = "环境值")
    private int environmentCount;
	@ApiModelProperty(value = "树屋等级")
    private int treeGrade;
	

	public int getTreeGrade() {
		return treeGrade;
	}
	public void setTreeGrade(int treeGrade) {
		this.treeGrade = treeGrade;
	}
	public int getAmbienceCount() {
		return ambienceCount;
	}
	public void setAmbienceCount(int ambienceCount) {
		this.ambienceCount = ambienceCount;
	}
	public int getAmusementCount() {
		return amusementCount;
	}
	public void setAmusementCount(int amusementCount) {
		this.amusementCount = amusementCount;
	}
	public int getEnvironmentCount() {
		return environmentCount;
	}
	public void setEnvironmentCount(int environmentCount) {
		this.environmentCount = environmentCount;
	}
	public int getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(int buildingType) {
		this.buildingType = buildingType;
	}
	public int getCurrentGrade() {
		return currentGrade;
	}
	public void setCurrentGrade(int currentGrade) {
		this.currentGrade = currentGrade;
	}
	public int getNextCurrentGrade() {
		return nextCurrentGrade;
	}
	public void setNextCurrentGrade(int nextCurrentGrade) {
		this.nextCurrentGrade = nextCurrentGrade;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public int getRoleGrade() {
		return roleGrade;
	}
	public void setRoleGrade(int roleGrade) {
		this.roleGrade = roleGrade;
	}
	public int getGoldOutPut() {
		return goldOutPut;
	}
	public void setGoldOutPut(int goldOutPut) {
		this.goldOutPut = goldOutPut;
	}
	public int getContainNum() {
		return containNum;
	}
	public void setContainNum(int containNum) {
		this.containNum = containNum;
	}
	public int getContainThingNum() {
		return containThingNum;
	}
	public void setContainThingNum(int containThingNum) {
		this.containThingNum = containThingNum;
	}
	public int getNextRoleGrade() {
		return nextRoleGrade;
	}
	public void setNextRoleGrade(int nextRoleGrade) {
		this.nextRoleGrade = nextRoleGrade;
	}
	public int getNextGoldOutPut() {
		return nextGoldOutPut;
	}
	public void setNextGoldOutPut(int nextGoldOutPut) {
		this.nextGoldOutPut = nextGoldOutPut;
	}
	public int getNextContainNum() {
		return nextContainNum;
	}
	public void setNextContainNum(int nextContainNum) {
		this.nextContainNum = nextContainNum;
	}
	public int getNextContainThingNum() {
		return nextContainThingNum;
	}
	public void setNextContainThingNum(int nextContainThingNum) {
		this.nextContainThingNum = nextContainThingNum;
	}
	public int getGoldMax() {
		return goldMax;
	}
	public void setGoldMax(int goldMax) {
		this.goldMax = goldMax;
	}
	public int getNextGoldMax() {
		return nextGoldMax;
	}
	public void setNextGoldMax(int nextGoldMax) {
		this.nextGoldMax = nextGoldMax;
	}
	public int getUpdateGold() {
		return updateGold;
	}
	public void setUpdateGold(int updateGold) {
		this.updateGold = updateGold;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	public int getBuildingStatus() {
		return buildingStatus;
	}
	public void setBuildingStatus(int buildingStatus) {
		this.buildingStatus = buildingStatus;
	}
	
}