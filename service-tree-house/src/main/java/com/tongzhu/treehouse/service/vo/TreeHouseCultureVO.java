package com.tongzhu.treehouse.service.vo;

import com.tongzhu.treehouse.domain.UserGoods;

import java.io.Serializable;
import java.util.List;

public class TreeHouseCultureVO implements Serializable {

    //树屋Id
    private String treeHouseId;

    //树屋级别
    private Integer level;

    //当前值
    private Integer currentValue;

    //达到下个等级的值
    private Integer nextValue;

    // 1：培养   2：突破
    private Integer type;

    //升级消耗 json串
    private List<UserGoods> upgradeConsumeGoods;

    //突破消耗 json串
    private List<UserGoods> breakConsumeGoods;

    //升级奖励 json串
    private List<UserGoods> upgradeAward;

    //突破奖励 json串
    private List<UserGoods> breakAward;


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getNextValue() {
        return nextValue;
    }

    public void setNextValue(Integer nextValue) {
        this.nextValue = nextValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId;
    }

    public List<UserGoods> getUpgradeConsumeGoods() {
        return upgradeConsumeGoods;
    }

    public void setUpgradeConsumeGoods(List<UserGoods> upgradeConsumeGoods) {
        this.upgradeConsumeGoods = upgradeConsumeGoods;
    }

    public List<UserGoods> getBreakConsumeGoods() {
        return breakConsumeGoods;
    }

    public void setBreakConsumeGoods(List<UserGoods> breakConsumeGoods) {
        this.breakConsumeGoods = breakConsumeGoods;
    }

    public List<UserGoods> getUpgradeAward() {
        return upgradeAward;
    }

    public void setUpgradeAward(List<UserGoods> upgradeAward) {
        this.upgradeAward = upgradeAward;
    }

    public List<UserGoods> getBreakAward() {
        return breakAward;
    }

    public void setBreakAward(List<UserGoods> breakAward) {
        this.breakAward = breakAward;
    }
}
