package com.tongzhu.welfare.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MarryLogVo {
	
	@ApiModelProperty(value = "记录ID")
    private String id;
	@ApiModelProperty(value = "用户名称")
    private String userName;
	@ApiModelProperty(value = "配偶名称")
    private String otherName;
	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "配偶ID")
    private String otherId;
	@ApiModelProperty(value = "用户模型ID")
    private String userRoleId;
	@ApiModelProperty(value = "配偶模型ID")
    private String otherRoleId;
	@ApiModelProperty(value = "婚礼类型")
    private Integer marryType;
	@ApiModelProperty(value = "男主人的撒喜糖次数")
    private Integer userJoyfulNum;
	@ApiModelProperty(value = "女主人的撒喜糖次数")
    private Integer otherJoyfulNum;
	@ApiModelProperty(value = "婚礼开始时间")
    private Date beginTime;
	@ApiModelProperty(value = "婚礼结束时间")
    private Date endTime;
	@ApiModelProperty(value = "婚礼id")
    private String engagementId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserJoyfulNum() {
		return userJoyfulNum;
	}

	public void setUserJoyfulNum(Integer userJoyfulNum) {
		this.userJoyfulNum = userJoyfulNum;
	}

	public Integer getOtherJoyfulNum() {
		return otherJoyfulNum;
	}

	public void setOtherJoyfulNum(Integer otherJoyfulNum) {
		this.otherJoyfulNum = otherJoyfulNum;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getOtherRoleId() {
		return otherRoleId;
	}

	public void setOtherRoleId(String otherRoleId) {
		this.otherRoleId = otherRoleId;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public Integer getMarryType() {
		return marryType;
	}

	public void setMarryType(Integer marryType) {
		this.marryType = marryType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEngagementId() {
		return engagementId;
	}

	public void setEngagementId(String engagementId) {
		this.engagementId = engagementId;
	}
}