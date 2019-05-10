package com.tongzhu.user.model;

public class Monster {
    private String monsterId;

    private Integer level;

    private String name;

    private String head;

    private String mode;

    private Integer type;

    private Float phAtk;

    private Float mfAtk;

    private Float phDef;

    private Float mfDef;

    private Float hp;

    private Float accuracy;

    private Float miss;

    private Float critical;

    private Float dcritical;

    private Integer skill1Id;

    private Integer skill1Lv;

    private Integer skill2Id;

    private Integer skill2Lv;

    private Integer skill3Id;

    private Integer skill3Lv;

    private Float realHurt;

    private String drop;

    private String talk;

    private Integer limit;

    private Integer roleType;

    public String getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId == null ? null : monsterId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getPhAtk() {
        return phAtk;
    }

    public void setPhAtk(Float phAtk) {
        this.phAtk = phAtk;
    }

    public Float getMfAtk() {
        return mfAtk;
    }

    public void setMfAtk(Float mfAtk) {
        this.mfAtk = mfAtk;
    }

    public Float getPhDef() {
        return phDef;
    }

    public void setPhDef(Float phDef) {
        this.phDef = phDef;
    }

    public Float getMfDef() {
        return mfDef;
    }

    public void setMfDef(Float mfDef) {
        this.mfDef = mfDef;
    }

    public Float getHp() {
        return hp;
    }

    public void setHp(Float hp) {
        this.hp = hp;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Float getMiss() {
        return miss;
    }

    public void setMiss(Float miss) {
        this.miss = miss;
    }

    public Float getCritical() {
        return critical;
    }

    public void setCritical(Float critical) {
        this.critical = critical;
    }

    public Float getDcritical() {
        return dcritical;
    }

    public void setDcritical(Float dcritical) {
        this.dcritical = dcritical;
    }

    public Integer getSkill1Id() {
        return skill1Id;
    }

    public void setSkill1Id(Integer skill1Id) {
        this.skill1Id = skill1Id;
    }

    public Integer getSkill1Lv() {
        return skill1Lv;
    }

    public void setSkill1Lv(Integer skill1Lv) {
        this.skill1Lv = skill1Lv;
    }

    public Integer getSkill2Id() {
        return skill2Id;
    }

    public void setSkill2Id(Integer skill2Id) {
        this.skill2Id = skill2Id;
    }

    public Integer getSkill2Lv() {
        return skill2Lv;
    }

    public void setSkill2Lv(Integer skill2Lv) {
        this.skill2Lv = skill2Lv;
    }

    public Integer getSkill3Id() {
        return skill3Id;
    }

    public void setSkill3Id(Integer skill3Id) {
        this.skill3Id = skill3Id;
    }

    public Integer getSkill3Lv() {
        return skill3Lv;
    }

    public void setSkill3Lv(Integer skill3Lv) {
        this.skill3Lv = skill3Lv;
    }

    public Float getRealHurt() {
        return realHurt;
    }

    public void setRealHurt(Float realHurt) {
        this.realHurt = realHurt;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop == null ? null : drop.trim();
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk == null ? null : talk.trim();
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}