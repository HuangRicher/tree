package com.tongzhu.welfare.po;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MarryPo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "用户求婚戒指ID")
    private String userRingId;
	@ApiModelProperty(value = "订婚ID")
    private String engagementId;
	@ApiModelProperty(value = "被求婚人ID")
    private String otherUserId;
	@ApiModelProperty(value = "求婚处理类型：1：接受求婚；2：拒绝求婚")
    private Integer type;
	@ApiModelProperty(value = "请求教堂信息类型：1：当前婚礼；2：举办婚礼；3：气球巡游；4：结婚记录")
    private Integer churchType;
	@ApiModelProperty(value = "婚礼类型：1：普通婚礼；2：浪漫婚礼；3：豪华婚礼；4：豪华预约婚礼")
    private Integer marryType;
	@ApiModelProperty(value = "巡游类型：1：普通巡游；2：浪漫巡游；3：豪华巡游礼")
    private Integer cruiseType;
	@ApiModelProperty(value = "页码数")
    private int pageNum;
	@ApiModelProperty(value = "页码大小")
    private int pageSize;
	@ApiModelProperty(value = "婚礼ID")
    private String marryId;
	@ApiModelProperty(value = "订婚ID")
    private String engagementLogId;
	@ApiModelProperty(value = "进入婚礼类型：1：来宾入口；2：主人入口；")
    private Integer joinType;
	@ApiModelProperty(value = "豪华预约婚礼开始时间")
    private Long marryDate;
	@ApiModelProperty(value = "爱情树ID")
    private String loveTreeId;
	
	
	
	public String getLoveTreeId() {
		return loveTreeId;
	}

	public void setLoveTreeId(String loveTreeId) {
		this.loveTreeId = loveTreeId;
	}

	public Integer getJoinType() {
		return joinType;
	}

	public Long getMarryDate() {
		return marryDate;
	}


	public void setMarryDate(Long marryDate) {
		this.marryDate = marryDate;
	}


	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getCruiseType() {
		return cruiseType;
	}

	public void setCruiseType(Integer cruiseType) {
		this.cruiseType = cruiseType;
	}

	public String getEngagementId() {
		return engagementId;
	}

	public void setEngagementId(String engagementId) {
		this.engagementId = engagementId;
	}

	public String getUserRingId() {
		return userRingId;
	}

	public void setUserRingId(String userRingId) {
		this.userRingId = userRingId;
	}

	public String getEngagementLogId() {
		return engagementLogId;
	}

	public void setEngagementLogId(String engagementLogId) {
		this.engagementLogId = engagementLogId;
	}

	public String getMarryId() {
		return marryId;
	}

	public void setMarryId(String marryId) {
		this.marryId = marryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(String otherUserId) {
		this.otherUserId = otherUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChurchType() {
		return churchType;
	}
	public void setChurchType(Integer churchType) {
		this.churchType = churchType;
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

	public Integer getMarryType() {
		return marryType;
	}

	public void setMarryType(Integer marryType) {
		this.marryType = marryType;
	}
	
}