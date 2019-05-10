package com.tongzhu.user.mapper.vo;


import com.tongzhu.user.model.User;

import java.io.Serializable;

public class UserRankingVO extends User implements Serializable {
    private Integer serialNo;

    private Integer amount;

    private Integer roleLevel;

    private Integer charmNum;

    private Integer alwaysFighting;

    private Integer promote;


    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getCharmNum() {
        return charmNum;
    }

    public void setCharmNum(Integer charmNum) {
        this.charmNum = charmNum;
    }

    public Integer getAlwaysFighting() {
        return alwaysFighting;
    }

    public void setAlwaysFighting(Integer alwaysFighting) {
        this.alwaysFighting = alwaysFighting;
    }

    public Integer getPromote() {
        return promote;
    }

    public void setPromote(Integer promote) {
        this.promote = promote;
    }
}
