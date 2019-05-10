package com.tongzhu.user.service.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/10/10 0010.
 */
public class UserGoodsVO implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 物品id
     */
    private Integer goodsId;

    /**
     * 物品数量
     */
    private Integer amount;
    /**
     * 过期时间
     */
    private Date surplusDate;

    /**
     * 获取时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     *  0 背包 1仓库 2 已佩戴 3 其它
     */
    private Integer settingPosition;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 道具类型  0 资源、1 装备、2 宝石、3礼包、4材料、5药水 6 武器 7 其他（0 ，6 不存在仓库和背包）
     */
    private Integer type;
    /**
     * 0白、1绿、2蓝、3紫、4橙、5红、6金  7无
     */
    private Integer quality;

    /**
     * 道具增加的战斗力
     */
    private Double fightingCapacity;
    /**
     * 道具图片路径
     */
    private String pictureUrl;

    /**
     * 道具使用要求玩家的等级
     */
    private Integer level;
    /**
     * 职业要求 0 无要求
     */
    private Integer occupation;

    /**
     * 性别要求 0 无要求 1 男 2 女
     */
    private Integer sex;

    /**
     * 道具使用场景 0 无要求
     */
    private Integer scene;

    /**
     * 道具说明
     */
    private String description;

    /**
     * 道具获取途径
     */
    private String acquiringWay;

    /**
     * 道具到期时间，超过这段时间道具就失效
     */
    private Date expirationTime;

    /**
     * 售价
     */
    private BigDecimal sellingPrice;

    /**
     * 装备佩戴位置 0 无要求 1 头部 2 衣服 3 裤子 4 鞋子 5 时装 6 婚戒 7 武器 8 戒指 9 护腕 10 项链
     */
    private Integer wearPosition;

    private Integer conversionPropId;

    private Integer conversionAmount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getSurplusDate() {
        return surplusDate;
    }

    public void setSurplusDate(Date surplusDate) {
        this.surplusDate = surplusDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSettingPosition() {
        return settingPosition;
    }

    public void setSettingPosition(Integer settingPosition) {
        this.settingPosition = settingPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Double getFightingCapacity() {
        return fightingCapacity;
    }

    public void setFightingCapacity(Double fightingCapacity) {
        this.fightingCapacity = fightingCapacity;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcquiringWay() {
        return acquiringWay;
    }

    public void setAcquiringWay(String acquiringWay) {
        this.acquiringWay = acquiringWay;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getWearPosition() {
        return wearPosition;
    }

    public void setWearPosition(Integer wearPosition) {
        this.wearPosition = wearPosition;
    }

    public Integer getConversionPropId() {
        return conversionPropId;
    }

    public void setConversionPropId(Integer conversionPropId) {
        this.conversionPropId = conversionPropId;
    }

    public Integer getConversionAmount() {
        return conversionAmount;
    }

    public void setConversionAmount(Integer conversionAmount) {
        this.conversionAmount = conversionAmount;
    }
}
