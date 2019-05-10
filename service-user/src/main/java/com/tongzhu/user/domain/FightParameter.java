package com.tongzhu.user.domain;

import java.io.Serializable;

public class FightParameter implements Serializable {

    private Boolean skip1;
    private Boolean skip2;
    private Boolean skip3;
    private Integer hurtCount=0;
    private Integer toxicosisCount; //中毒伤害数
    private Integer bloodCount;     //生命回复值
    private Integer hurtBackCount;  //反噬伤害数
    private Integer type;

    public FightParameter() {
    }

    public FightParameter(Integer hurtCount, Integer toxicosisCount, Integer bloodCount, Integer hurtBackCount) {
        this.hurtCount = hurtCount;
        this.toxicosisCount = toxicosisCount;
        this.bloodCount = bloodCount;
        this.hurtBackCount = hurtBackCount;
    }

    public Boolean getSkip1() {
        return skip1;
    }

    public void setSkip1(Boolean skip1) {
        this.skip1 = skip1;
    }

    public Boolean getSkip2() {
        return skip2;
    }

    public void setSkip2(Boolean skip2) {
        this.skip2 = skip2;
    }

    public Boolean getSkip3() {
        return skip3;
    }

    public void setSkip3(Boolean skip3) {
        this.skip3 = skip3;
    }

    public Integer getHurtCount() {
        return hurtCount;
    }

    public void setHurtCount(Integer hurtCount) {
        this.hurtCount = hurtCount;
    }

    public Integer getToxicosisCount() {
        return toxicosisCount;
    }

    public void setToxicosisCount(Integer toxicosisCount) {
        this.toxicosisCount = toxicosisCount;
    }

    public Integer getBloodCount() {
        return bloodCount;
    }

    public void setBloodCount(Integer bloodCount) {
        this.bloodCount = bloodCount;
    }

    public Integer getHurtBackCount() {
        return hurtBackCount;
    }

    public void setHurtBackCount(Integer hurtBackCount) {
        this.hurtBackCount = hurtBackCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
