package com.tongzhu.user.controller.vo;

import java.io.Serializable;

public class Building implements Serializable {

    private Integer phAtk;
    private Integer mfAtk;
    private Integer phDef;
    private Integer mfDef;
    private Integer hp;
    private Integer accuracy;
    private Integer miss;
    private Integer critical;
    private Integer dcritical;

    public Integer getPhAtk() {
        return phAtk;
    }

    public void setPhAtk(Integer phAtk) {
        this.phAtk = phAtk;
    }

    public Integer getMfAtk() {
        return mfAtk;
    }

    public void setMfAtk(Integer mfAtk) {
        this.mfAtk = mfAtk;
    }

    public Integer getPhDef() {
        return phDef;
    }

    public void setPhDef(Integer phDef) {
        this.phDef = phDef;
    }

    public Integer getMfDef() {
        return mfDef;
    }

    public void setMfDef(Integer mfDef) {
        this.mfDef = mfDef;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getMiss() {
        return miss;
    }

    public void setMiss(Integer miss) {
        this.miss = miss;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getDcritical() {
        return dcritical;
    }

    public void setDcritical(Integer dcritical) {
        this.dcritical = dcritical;
    }
}
