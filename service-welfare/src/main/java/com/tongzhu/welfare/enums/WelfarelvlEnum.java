package com.tongzhu.welfare.enums;

public enum WelfarelvlEnum {
	
    one(10, 2057),
    two(20, 2058),
    three(30, 2059),
    four(40, 2060),
    five(50, 2061),
    six(60, 2062),
    seven(70, 2063),
    eight(80, 2064),
    nine(90, 2065);
    
    
/*    ten(10, 2023),
    eleven(11, 2024),
    twelve(12, 2025),
    thirteen(13, 2026),
    fourteen(14, 2027),
    fifteen(15, 2028);*/


    private Integer enchantlvl;

    private Integer taskId;


    public static Integer getTaskId(Integer id) {
        for (WelfarelvlEnum adornEquipEnum : WelfarelvlEnum.values()) {
            if (adornEquipEnum.getEnchantlvl() == id) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    WelfarelvlEnum(Integer enchantlvl, Integer taskId) {
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
