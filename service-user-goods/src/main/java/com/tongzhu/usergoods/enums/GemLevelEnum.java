package com.tongzhu.usergoods.enums;

public enum GemLevelEnum {
    one(24, 2029),
    two(48, 2030),
    three(72, 2031),
    four(96, 2032),
    five(120, 2033),
    six(144, 2034),
    seven(168, 2035),
    eight(192, 2036),
    nine(216, 2037),
    ten(240, 2038);

    /**
     * 宝石等级任务
     */

    private Integer gemLevel;

    private Integer taskId;


    public static Integer getTaskId(Integer taskId) {
        Integer maxLevel = 0;
        Integer task = null;
        for (GemLevelEnum adornEquipEnum : GemLevelEnum.values()) {
            if (adornEquipEnum.getGemLevel() <= taskId) {
                if (adornEquipEnum.getGemLevel() > maxLevel) {
                    maxLevel = adornEquipEnum.getGemLevel();
                    task = adornEquipEnum.getTaskId();
                }
            }
        }
        return task;
    }

    GemLevelEnum(Integer gemLevel, Integer taskId) {
        this.gemLevel = gemLevel;
        this.taskId = taskId;
    }

    public Integer getGemLevel() {
        return gemLevel;
    }

    public void setGemLevel(Integer gemLevel) {
        this.gemLevel = gemLevel;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
