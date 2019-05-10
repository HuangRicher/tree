package com.tongzhu.friend.domain;

import java.util.Date;

public class PropInfo {
    private String id;

    private String name;

    private Integer type;

    private Integer quality;

    private Double fightingCapacity;

    private Integer level;

    private Integer occupation;

    private Integer sex;

    private Integer scene;

    private String description;

    private String acquiringWay;

    private Date expirationTime;

    private Date createDate;

    private Date updateDate;

    private Integer conversionPropId;

    private Integer conversionAmount;

    private String iconid;

    private String role;

    private Integer logRecord;

    private Integer storage;

    private Integer binding;

    private Integer deal;

    private Integer sell;

    private Integer destroy;

    private Integer time;

    private String profession;

    private Integer use;

    private Double vitality;

    private Double attack;

    private Double spellAttacks;

    private Double pdef;

    private Double magdef;

    private Double crit;

    private Double dodge;

    private Double hitRate;

    private Double defenseCrit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.description = description == null ? null : description.trim();
    }

    public String getAcquiringWay() {
        return acquiringWay;
    }

    public void setAcquiringWay(String acquiringWay) {
        this.acquiringWay = acquiringWay == null ? null : acquiringWay.trim();
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        this.iconid = iconid == null ? null : iconid.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getLogRecord() {
        return logRecord;
    }

    public void setLogRecord(Integer logRecord) {
        this.logRecord = logRecord;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Integer getUse() {
        return use;
    }

    public void setUse(Integer use) {
        this.use = use;
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
}