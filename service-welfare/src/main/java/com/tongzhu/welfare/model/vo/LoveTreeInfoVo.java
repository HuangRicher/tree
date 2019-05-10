package com.tongzhu.welfare.model.vo;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

@ApiModel
public class LoveTreeInfoVo {

	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "用户配偶ID")
    private String otherId;
	@ApiModelProperty(value = "情缘值")
    private Long loveNum;
	@ApiModelProperty(value = "幸福值")
    private Long happinessNum;
	@ApiModelProperty(value = "爱情树增益属性")
    private Map<String, Integer> properties;
	@ApiModelProperty(value = "爱情树等级")
    private Integer treeGrade;
	@ApiModelProperty(value = "订婚ID")
    private String engagementId;
	@ApiModelProperty(value = "男主人名称")
    private String userName;
	@ApiModelProperty(value = "男主人模型ID")
    private String userRoleId;
	@ApiModelProperty(value = "女主人名称")
    private String grilName;
	@ApiModelProperty(value = "女主人模型ID")
    private String grilRoleId;
	@ApiModelProperty(value = "剩余嬉闹数")
    private Integer playJokesNum;
	@ApiModelProperty(value = "剩余洞房数")
    private Integer weddingNum;
	@ApiModelProperty(value = "爱情树当前等级最高经验值")
    private Integer expLimit;
	@ApiModelProperty(value = "爱情树当前经验值")
    private Integer expNum;
	@ApiModelProperty(value = "男宠物模型ID")
    private String userPetId;
	@ApiModelProperty(value = "女宠物模型ID")
    private String grilPetId;
	@ApiModelProperty(value = "婚戒ID")
    private String ringId;
	@ApiModelProperty(value = "简化婚戒ID")
    private String goodsId;
	@ApiModelProperty(value = "婚戒等级")
    private Integer ringGradeNum;
	@ApiModelProperty(value = "爱情树ID")
    private String id;
	@ApiModelProperty(value = "对方在线状态")
    private Integer otherSatatus;
	@ApiModelProperty(value = "升级戒指需要的幸福值")
    private Long upgradeHappinessNum;
	@ApiModelProperty(value = "下一等级戒指模型")
    private Integer upgradeGoodsId;


	public Integer getOtherSatatus() {
		return otherSatatus;
	}

	public void setOtherSatatus(Integer otherSatatus) {
		this.otherSatatus = otherSatatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getRingId() {
		return ringId;
	}

	public void setRingId(String ringId) {
		this.ringId = ringId;
	}

	public Integer getRingGradeNum() {
		return ringGradeNum;
	}

	public void setRingGradeNum(Integer ringGradeNum) {
		this.ringGradeNum = ringGradeNum;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTreeGrade() {
		return treeGrade;
	}

	public void setTreeGrade(Integer treeGrade) {
		this.treeGrade = treeGrade;
	}

	public String getEngagementId() {
		return engagementId;
	}

	public void setEngagementId(String engagementId) {
		this.engagementId = engagementId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getGrilName() {
		return grilName;
	}

	public void setGrilName(String grilName) {
		this.grilName = grilName;
	}

	public String getGrilRoleId() {
		return grilRoleId;
	}

	public void setGrilRoleId(String grilRoleId) {
		this.grilRoleId = grilRoleId;
	}

	public Integer getPlayJokesNum() {
		return playJokesNum;
	}

	public void setPlayJokesNum(Integer playJokesNum) {
		this.playJokesNum = playJokesNum;
	}

	public Integer getWeddingNum() {
		return weddingNum;
	}

	public void setWeddingNum(Integer weddingNum) {
		this.weddingNum = weddingNum;
	}

	public Integer getExpLimit() {
		return expLimit;
	}

	public void setExpLimit(Integer expLimit) {
		this.expLimit = expLimit;
	}

	public Integer getExpNum() {
		return expNum;
	}

	public void setExpNum(Integer expNum) {
		this.expNum = expNum;
	}

	public String getUserPetId() {
		return userPetId;
	}

	public void setUserPetId(String userPetId) {
		this.userPetId = userPetId;
	}

	public String getGrilPetId() {
		return grilPetId;
	}

	public void setGrilPetId(String grilPetId) {
		this.grilPetId = grilPetId;
	}

	public Long getLoveNum() {
		return loveNum;
	}

	public void setLoveNum(Long loveNum) {
		this.loveNum = loveNum;
	}

	public Long getHappinessNum() {
		return happinessNum;
	}

	public void setHappinessNum(Long happinessNum) {
		this.happinessNum = happinessNum;
	}

	public Map<String, Integer> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Integer> properties) {
		this.properties = properties;
	}

	public Long getUpgradeHappinessNum() {
		return upgradeHappinessNum;
	}

	public void setUpgradeHappinessNum(Long upgradeHappinessNum) {
		this.upgradeHappinessNum = upgradeHappinessNum;
	}

	public Integer getUpgradeGoodsId() {
		return upgradeGoodsId;
	}

	public void setUpgradeGoodsId(Integer upgradeGoodsId) {
		this.upgradeGoodsId = upgradeGoodsId;
	}
}