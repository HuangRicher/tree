package com.tongzhu.servicemessage.domain;


import java.util.Date;

public class MarryLogVo {

    private String id;
    private String userName;
    private String otherName;
    private String userId;
    private String otherId;
    private String userRoleId;
    private String otherRoleId;
    private Integer marryType;
    private Integer userJoyfulNum;
    private Integer otherJoyfulNum;
    private Date beginTime;
    private Date endTime;
	
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
    
    
}