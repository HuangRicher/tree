package com.tongzhu.welfare.enums;

public enum ChurchlvlEnum {
	
    one(10, 2102),
    two(20, 2103),
    three(30, 2104),
    four(40, 2105),
    five(50, 2106),
    six(60, 2107),
    seven(70, 2108),
    eight(80, 2109),
    nine(90, 2110);
    
    
/*    ten(10, 2023),
    eleven(11, 2024),
    twelve(12, 2025),
    thirteen(13, 2026),
    fourteen(14, 2027),
    fifteen(15, 2028);*/


    private Integer enchantlvl;

    private Integer taskId;


    public static Integer getTaskId(Integer id) {
        for (ChurchlvlEnum adornEquipEnum : ChurchlvlEnum.values()) {
            if (adornEquipEnum.getEnchantlvl() == id) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    ChurchlvlEnum(Integer enchantlvl, Integer taskId) {
        this.enchantlvl = enchantlvl;
        this.taskId = taskId;
    }

    public Integer getEnchantlvl() {
        return enchantlvl;
    }

    public void setEnchantlvl(Integer enchantlvl) {
        this.enchantlvl = enchantlvl;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
