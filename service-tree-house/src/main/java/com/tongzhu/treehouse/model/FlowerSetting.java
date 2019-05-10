package com.tongzhu.treehouse.model;

public class FlowerSetting {
    private String id;

    private Integer moneyAward;

    private Integer enviromentAward;

    private String otherAward;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getMoneyAward() {
        return moneyAward;
    }

    public void setMoneyAward(Integer moneyAward) {
        this.moneyAward = moneyAward;
    }

    public Integer getEnviromentAward() {
        return enviromentAward;
    }

    public void setEnviromentAward(Integer enviromentAward) {
        this.enviromentAward = enviromentAward;
    }

    public String getOtherAward() {
        return otherAward;
    }

    public void setOtherAward(String otherAward) {
        this.otherAward = otherAward == null ? null : otherAward.trim();
    }
}