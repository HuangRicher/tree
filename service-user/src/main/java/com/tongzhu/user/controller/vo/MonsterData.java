package com.tongzhu.user.controller.vo;

import java.util.Map;

public class MonsterData {

    private Map<String,Integer> defender;

    private Integer hurtCount;

    private Boolean bj;

    private Map<String,Integer> buffUserId;

    private Map<String,Integer> buff;

    public Map<String, Integer> getDefender() {
        return defender;
    }

    public void setDefender(Map<String, Integer> defender) {
        this.defender = defender;
    }

    public Integer getHurtCount() {
        return hurtCount;
    }

    public void setHurtCount(Integer hurtCount) {
        this.hurtCount = hurtCount;
    }

    public Boolean getBj() {
        return bj;
    }

    public void setBj(Boolean bj) {
        this.bj = bj;
    }

    public Map<String, Integer> getBuffUserId() {
        return buffUserId;
    }

    public void setBuffUserId(Map<String, Integer> buffUserId) {
        this.buffUserId = buffUserId;
    }

    public Map<String, Integer> getBuff() {
        return buff;
    }

    public void setBuff(Map<String, Integer> buff) {
        this.buff = buff;
    }
}
