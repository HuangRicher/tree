package com.tongzhu.usergoods.enums;

public enum EnchantlvlEnum {

    one(1, 2014),
    two(2, 2015),
    three(3, 2016),
    four(4, 2017),
    five(5, 2018),
    six(6, 2019),
    seven(7, 2020),
    eight(8, 2021),
    nine(9, 2022),
    ten(10, 2023),
    eleven(11, 2024),
    twelve(12, 2025),
    thirteen(13, 2026),
    fourteen(14, 2027),
    fifteen(15, 2028);
    /**
     * 全身强化等级任务
     */

    private Integer enchantlvl;

    private Integer taskId;


    public static Integer getTaskId(Integer id) {
        for (EnchantlvlEnum adornEquipEnum : EnchantlvlEnum.values()) {
            if (adornEquipEnum.getEnchantlvl() == id) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    EnchantlvlEnum(Integer enchantlvl, Integer taskId) {
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
