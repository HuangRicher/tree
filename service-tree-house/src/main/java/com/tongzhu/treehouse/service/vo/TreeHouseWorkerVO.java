package com.tongzhu.treehouse.service.vo;

import com.tongzhu.treehouse.domain.UserGoods;

import java.io.Serializable;
import java.util.List;

public class TreeHouseWorkerVO implements Serializable {

    private Integer workerCount;

    /**
     *当前值
     */
    private Integer currentValue;

    /**
     *达到下个等级的值
     */
    private Integer nextValue;

    //升级消耗物品 json串
    private List<UserGoods> consumeGoods;

    public List<UserGoods> getConsumeGoods() {
        return consumeGoods;
    }

    public void setConsumeGoods(List<UserGoods> consumeGoods) {
        this.consumeGoods = consumeGoods;
    }

    public Integer getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(Integer workerCount) {
        this.workerCount = workerCount;
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
}
