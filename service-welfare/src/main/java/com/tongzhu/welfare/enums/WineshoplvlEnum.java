package com.tongzhu.welfare.enums;

public enum WineshoplvlEnum {
	
    one(10, 2084),
    two(20, 2085),
    three(30, 2086),
    four(40, 2087),
    five(50, 2088),
    six(60, 2089),
    seven(70, 2090),
    eight(80, 2091),
    nine(90, 2092);
    
    
/*    ten(10, 2023),
    eleven(11, 2024),
    twelve(12, 2025),
    thirteen(13, 2026),
    fourteen(14, 2027),
    fifteen(15, 2028);*/


    private Integer enchantlvl;

    private Integer taskId;


    public static Integer getTaskId(Integer id) {
        for (WineshoplvlEnum adornEquipEnum : WineshoplvlEnum.values()) {
            if (adornEquipEnum.getEnchantlvl() == id) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    WineshoplvlEnum(Integer enchantlvl, Integer taskId) {
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
