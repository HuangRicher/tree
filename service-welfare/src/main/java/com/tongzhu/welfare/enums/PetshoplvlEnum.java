package com.tongzhu.welfare.enums;

public enum PetshoplvlEnum {
	
    one(10, 2093),
    two(20, 2094),
    three(30, 2095),
    four(40, 2096),
    five(50, 2097),
    six(60, 2098),
    seven(70, 2099),
    eight(80, 2100),
    nine(90, 2101);
    
    
/*    ten(10, 2023),
    eleven(11, 2024),
    twelve(12, 2025),
    thirteen(13, 2026),
    fourteen(14, 2027),
    fifteen(15, 2028);*/


    private Integer enchantlvl;

    private Integer taskId;


    public static Integer getTaskId(Integer id) {
        for (PetshoplvlEnum adornEquipEnum : PetshoplvlEnum.values()) {
            if (adornEquipEnum.getEnchantlvl() == id) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    PetshoplvlEnum(Integer enchantlvl, Integer taskId) {
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
