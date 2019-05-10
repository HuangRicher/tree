package com.tongzhu.usergoods.mapper.vo;

import java.util.Date;

public class ArsenalInfoVO {
    /**
     * 背包主键id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 物品主键id
     */
    private String goodsId;
    /**
     * 物品数量
     */
    private Integer amount;

    /**
     * 道具获得时间
     */
    private Date gainDate;

    private Date surplusDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 存放位置 0 背包 1 仓库 2已佩戴 3其它 4 武器库
     */
    private Integer settingPosition;
    /**
     * 物品类型 0 道具类型 1 装备类型 2 武器类型
     */
    private Integer type;

    /**
     * 道具id
     */
    private Integer equipmentId;
    /**
     * 装备名称
     */
    private String name;
    /**
     * 道具品质  0白、1绿、2蓝、3紫、4橙、5红、6金
     */
    private Integer quality;
    /**
     * 道具增加玩家战斗力
     */
    private Double fightingCapacity;
    /**
     * 道具使用要求玩家的等级
     */
    private Integer level;
    /**
     * 性别要求 0 无要求 1 男 2 女
     */
    private Integer sex;
    /**
     * 道具说明
     */
    private String description;
    /**
     * 道具获取途径
     */
    private String acquiringWay;

    /**
     * 道具过期时间
     */
    private Date expirationTime;

    /**
     * 生命属性加成
     */
    private Double vitality;
    /**
     * 物理攻击属性加成
     */
    private Double attack;
    /**
     * 法术攻击属性加成
     */
    private Double spellAttacks;
    /**
     * 物理防御属性加成
     */
    private Double pdef;
    /**
     * 法术防御属性加成
     */
    private Double magdef;
    /**
     * 暴击属性加成
     */
    private Double crit;
    /**
     * 闪避属性加成
     */
    private Double dodge;
    /**
     * 命中属性加成
     */
    private Double hitRate;
    /**
     * 抗暴击属性加成
     */
    private Double defenseCrit;
    /**
     * 可以兑换物品的id
     */
    private Integer conversionPropId;
    /**
     * 兑换物品的数量
     */
    private Integer conversionAmount;
    /**
     * 装备图标编号
     */
    private String iconid;

    private String guardiansMale;

    private String guardiansFemale;

    private String assassinMale;

    private String assassinFemale;

    private String craftsMale;

    private String craftsFemale;

    private String masterMale;

    private String masterFemale;

    private Integer arsenalType;

    private String profession;

    private Integer time;

    private String gemRhombus;

    private String gemRoundness;

    private String gemHexagon;


    /**
     * 是否可以存仓库 0 可以 1 禁止存入仓库
     */
    private Integer storage;
    /**
     * 获得装备是否绑定 0 不绑定 1 绑定
     */
    private Integer binding;
    /**
     * 是否能进行交易 0 可以 1 不可以
     */
    private Integer deal;
    /**
     * 是否能进行出售 0 可以 1 不可以
     */
    private Integer sell;
    /**
     * 是否能进行销毁 0 可以 1 不可以
     */
    private Integer destroy;
    /**
     * 是否能进行镶嵌宝石 0 可以 1 不可以
     */
    private Integer inlay;
    /**
     * 是否为原型装备 0 原型装备 1 否 强化过的就不是原型装备
     */
    private Integer original;

    private Integer enchantlvl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getGainDate() {
        return gainDate;
    }

    public void setGainDate(Date gainDate) {
        this.gainDate = gainDate;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Double getVitality() {
        return vitality;
    }

    public void setVitality(Double vitality) {
        this.vitality = vitality;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getSpellAttacks() {
        return spellAttacks;
    }

    public void setSpellAttacks(Double spellAttacks) {
        this.spellAttacks = spellAttacks;
    }

    public Double getPdef() {
        return pdef;
    }

    public void setPdef(Double pdef) {
        this.pdef = pdef;
    }

    public Double getMagdef() {
        return magdef;
    }

    public void setMagdef(Double magdef) {
        this.magdef = magdef;
    }

    public Double getCrit() {
        return crit;
    }

    public void setCrit(Double crit) {
        this.crit = crit;
    }

    public Double getDodge() {
        return dodge;
    }

    public void setDodge(Double dodge) {
        this.dodge = dodge;
    }

    public Double getHitRate() {
        return hitRate;
    }

    public void setHitRate(Double hitRate) {
        this.hitRate = hitRate;
    }

    public Double getDefenseCrit() {
        return defenseCrit;
    }

    public void setDefenseCrit(Double defenseCrit) {
        this.defenseCrit = defenseCrit;
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

    public String getIconid() {
        return iconid;
    }

    public void setIconid(String iconid) {
        this.iconid = iconid;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getBinding() {
        return binding;
    }

    public void setBinding(Integer binding) {
        this.binding = binding;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Integer getDestroy() {
        return destroy;
    }

    public void setDestroy(Integer destroy) {
        this.destroy = destroy;
    }

    public Integer getInlay() {
        return inlay;
    }

    public void setInlay(Integer inlay) {
        this.inlay = inlay;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public String getGuardiansMale() {
        return guardiansMale;
    }

    public void setGuardiansMale(String guardiansMale) {
        this.guardiansMale = guardiansMale;
    }

    public String getGuardiansFemale() {
        return guardiansFemale;
    }

    public void setGuardiansFemale(String guardiansFemale) {
        this.guardiansFemale = guardiansFemale;
    }

    public String getAssassinMale() {
        return assassinMale;
    }

    public void setAssassinMale(String assassinMale) {
        this.assassinMale = assassinMale;
    }

    public String getAssassinFemale() {
        return assassinFemale;
    }

    public void setAssassinFemale(String assassinFemale) {
        this.assassinFemale = assassinFemale;
    }

    public String getCraftsMale() {
        return craftsMale;
    }

    public void setCraftsMale(String craftsMale) {
        this.craftsMale = craftsMale;
    }

    public String getCraftsFemale() {
        return craftsFemale;
    }

    public void setCraftsFemale(String craftsFemale) {
        this.craftsFemale = craftsFemale;
    }

    public String getMasterMale() {
        return masterMale;
    }

    public void setMasterMale(String masterMale) {
        this.masterMale = masterMale;
    }

    public String getMasterFemale() {
        return masterFemale;
    }

    public void setMasterFemale(String masterFemale) {
        this.masterFemale = masterFemale;
    }

    public Integer getArsenalType() {
        return arsenalType;
    }

    public void setArsenalType(Integer arsenalType) {
        this.arsenalType = arsenalType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getSurplusDate() {
        return surplusDate;
    }

    public void setSurplusDate(Date surplusDate) {
        this.surplusDate = surplusDate;
    }

    public Integer getEnchantlvl() {
        return enchantlvl;
    }

    public void setEnchantlvl(Integer enchantlvl) {
        this.enchantlvl = enchantlvl;
    }

    public String getGemRhombus() {
        return gemRhombus;
    }

    public void setGemRhombus(String gemRhombus) {
        this.gemRhombus = gemRhombus;
    }

    public String getGemRoundness() {
        return gemRoundness;
    }

    public void setGemRoundness(String gemRoundness) {
        this.gemRoundness = gemRoundness;
    }

    public String getGemHexagon() {
        return gemHexagon;
    }

    public void setGemHexagon(String gemHexagon) {
        this.gemHexagon = gemHexagon;
    }
}