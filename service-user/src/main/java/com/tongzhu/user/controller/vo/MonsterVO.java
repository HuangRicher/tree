package com.tongzhu.user.controller.vo;

import com.tongzhu.user.model.UserSkill;

import java.io.Serializable;
import java.util.List;

public class MonsterVO implements Serializable {

    private Float phAtk;

    private Float mfAtk;

    private Float phDef;

    private Float mfDef;

    private Float hp;

    private Float accuracy;

    private Float miss;

    private Float critical;

    private Float dcritical;

    private List<UserSkill> skillList;


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

    public List<UserSkill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<UserSkill> skillList) {
        this.skillList = skillList;
    }
}
