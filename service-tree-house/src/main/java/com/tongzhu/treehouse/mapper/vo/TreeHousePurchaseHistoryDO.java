package com.tongzhu.treehouse.mapper.vo;

import com.tongzhu.treehouse.model.TreeHousePurchaseHistory;

import java.io.Serializable;

public class TreeHousePurchaseHistoryDO extends TreeHousePurchaseHistory  implements Serializable {
    /**
     * 购买宅友的玩家名称
     */
   private String purchaserName;
    /**
     * 出售宅友的玩家名称
     */
   private String bargainorName;
    /**
     * 宅友名称
     */
   private String sellName;


    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getBargainorName() {
        return bargainorName;
    }

    public void setBargainorName(String bargainorName) {
        this.bargainorName = bargainorName;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }
}