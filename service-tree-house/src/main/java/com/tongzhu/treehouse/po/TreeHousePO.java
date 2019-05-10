package com.tongzhu.treehouse.po;

import java.io.Serializable;

public class TreeHousePO implements Serializable {

    private String userId;
    private String treeHouseId;
    private int cultureCount;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTreeHouseId() {
        return treeHouseId;
    }

    public void setTreeHouseId(String treeHouseId) {
        this.treeHouseId = treeHouseId;
    }

    public int getCultureCount() {
        return cultureCount;
    }

    public void setCultureCount(int cultureCount) {
        this.cultureCount = cultureCount;
    }
}
