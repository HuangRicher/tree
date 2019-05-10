package com.tongzhu.usergoods.enums;

public enum AdornEquipEnum {
    NECKLACE(1, "necklace"),

    CUFF(2, "cuff"),

    JEWELRY(3, "jewelry"),

    RING(4, "ring"),

    HEAD(5, "head"),

    CLOTHING(6, "clothing"),

    TROUSERS(7, "trousers"),

    SHOE(8, "shoe"),

    FASHION(9, "fashion"),

    WEDDING_RING(10, "weddingRing");
    // 1:项链、2：护腕、3：玉佩、4:戒指、5:头盔、6:衣服、7:裤子、8：鞋子、9：时装、10：婚戒
    private Integer id;
    // 对应tz_user_adorn_equip  实体类对应的字段类型
    private String wearPosition;

    public static String getWearPosition(Integer id) {
        for (AdornEquipEnum adornEquipEnum : AdornEquipEnum.values()) {
            if (adornEquipEnum.getId() == id) {
                return adornEquipEnum.getWearPosition();
            }
        }
        return null;
    }

    AdornEquipEnum(Integer id, String wearPosition) {
        this.id = id;
        this.wearPosition = wearPosition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWearPosition() {
        return wearPosition;
    }

    public void setWearPosition(String wearPosition) {
        this.wearPosition = wearPosition;
    }
}
