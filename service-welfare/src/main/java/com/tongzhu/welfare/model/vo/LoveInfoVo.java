package com.tongzhu.welfare.model.vo;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoveInfoVo {
	
	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "情缘值")
    private Long loveNum;
	@ApiModelProperty(value = "订婚ID")
    private String engagementId;
	@ApiModelProperty(value = "下一级情缘值")
    private Long nextLoveNum;
	@ApiModelProperty(value = "情缘等级")
    private Integer gradeNum;
	@ApiModelProperty(value = "情缘属性")
    private List<String> propertyList;
	@ApiModelProperty(value = "情缘等级属性")
    private Map<String, Integer> propertyAllList;
	@ApiModelProperty(value = "第1颗星")
    private Integer property1;
	@ApiModelProperty(value = "第2颗星")
    private Integer property2;
	@ApiModelProperty(value = "第3颗星")
    private Integer property3;
	@ApiModelProperty(value = "第4颗星")
    private Integer property4;
	@ApiModelProperty(value = "第5颗星")
    private Integer property5;
	@ApiModelProperty(value = "第6颗星")
    private Integer property6;
	@ApiModelProperty(value = "第7颗星")
    private Integer property7;
	@ApiModelProperty(value = "第8颗星")
    private Integer property8;
	@ApiModelProperty(value = "第9颗星")
    private Integer property9;
	@ApiModelProperty(value = "第10颗星")
    private Integer property10;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProperty1() {
		return property1;
	}

	public void setProperty1(Integer property1) {
		this.property1 = property1;
	}

	public Integer getProperty2() {
		return property2;
	}

	public void setProperty2(Integer property2) {
		this.property2 = property2;
	}

	public Integer getProperty3() {
		return property3;
	}

	public void setProperty3(Integer property3) {
		this.property3 = property3;
	}

	public Integer getProperty4() {
		return property4;
	}

	public void setProperty4(Integer property4) {
		this.property4 = property4;
	}

	public Integer getProperty5() {
		return property5;
	}

	public void setProperty5(Integer property5) {
		this.property5 = property5;
	}

	public Integer getProperty6() {
		return property6;
	}

	public void setProperty6(Integer property6) {
		this.property6 = property6;
	}

	public Integer getProperty7() {
		return property7;
	}

	public void setProperty7(Integer property7) {
		this.property7 = property7;
	}

	public Integer getProperty8() {
		return property8;
	}

	public void setProperty8(Integer property8) {
		this.property8 = property8;
	}

	public Integer getProperty9() {
		return property9;
	}

	public void setProperty9(Integer property9) {
		this.property9 = property9;
	}

	public Integer getProperty10() {
		return property10;
	}

	public void setProperty10(Integer property10) {
		this.property10 = property10;
	}

	public String getEngagementId() {
		return engagementId;
	}

	public void setEngagementId(String engagementId) {
		this.engagementId = engagementId;
	}

	public Long getLoveNum() {
		return loveNum;
	}

	public void setLoveNum(Long loveNum) {
		this.loveNum = loveNum;
	}

	public Long getNextLoveNum() {
		return nextLoveNum;
	}

	public void setNextLoveNum(Long nextLoveNum) {
		this.nextLoveNum = nextLoveNum;
	}

	public Integer getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(Integer gradeNum) {
		this.gradeNum = gradeNum;
	}

	public List<String> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<String> propertyList) {
		this.propertyList = propertyList;
	}

	public Map<String, Integer> getPropertyAllList() {
		return propertyAllList;
	}

	public void setPropertyAllList(Map<String, Integer> propertyAllList) {
		this.propertyAllList = propertyAllList;
	}

	
}