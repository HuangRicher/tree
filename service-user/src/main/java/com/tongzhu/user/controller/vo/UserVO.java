package com.tongzhu.user.controller.vo;

import com.tongzhu.user.model.User;

import java.io.Serializable;

public class UserVO extends User implements Serializable {

    private Integer treeHouseLevel;//树屋等级

    public Integer getTreeHouseLevel() {
        return treeHouseLevel;
    }

    public void setTreeHouseLevel(Integer treeHouseLevel) {
        this.treeHouseLevel = treeHouseLevel;
    }

}
