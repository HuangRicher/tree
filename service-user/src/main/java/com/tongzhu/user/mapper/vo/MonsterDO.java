package com.tongzhu.user.mapper.vo;

import com.tongzhu.user.model.Monster;

import java.io.Serializable;

public class MonsterDO extends Monster implements Serializable {
    private Integer groupId;

    private Integer randomStatus;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRandomStatus() {
        return randomStatus;
    }

    public void setRandomStatus(Integer randomStatus) {
        this.randomStatus = randomStatus;
    }
}
