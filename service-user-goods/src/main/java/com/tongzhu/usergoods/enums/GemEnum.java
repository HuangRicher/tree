package com.tongzhu.usergoods.enums;

public enum GemEnum {
    HEAD(1,"gemRhombus"),

    CLOTHING(2,"gemRoundness"),

    TROUSERS(3,"gemHexagon");


    // 1:菱形、2：圆形、3：六边形
    private Integer id;
    private String gem;

    public static String getGem(Integer id) {
        for (GemEnum adornEquipEnum : GemEnum.values()) {
            if (adornEquipEnum.getId() == id) {
                return adornEquipEnum.getGem();
            }
        }
        return null;
    }

    GemEnum(Integer id, String gem) {
        this.id = id;
        this.gem = gem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGem() {
        return gem;
    }

    public void setGem(String gem) {
        this.gem = gem;
    }
}
