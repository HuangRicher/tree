package com.tongzhu.fishing.enums;

public enum CoinlvlEnum {

    //弹射分与对应得弹射系统

    zero(0, 10, 0.5),
    one(10, 20, 0.6),
    two(20, 30, 0.7),
    three(30, 40, 0.8),
    four(40, 50, 0.9),
    five(50, 60, 1.0),
    six(60, 70, 1.1),
    seven(70, 80, 1.2),
    eight(80, 90, 1.3),
    nine(90, 95, 1.4),
    nineFive(95, 100, 1.5);

    private Integer coinlvlMin;
    private Integer coinlvlMax;
    private Double taskId;


    public static Double getTaskId(Integer coinlel) {
        for (CoinlvlEnum adornEquipEnum : CoinlvlEnum.values()) {
            if (adornEquipEnum.getCoinlvlMin() <= coinlel && adornEquipEnum.getCoinlvlMax() >= coinlel) {
                return adornEquipEnum.getTaskId();
            }
        }
        return null;
    }

    CoinlvlEnum(Integer coinlvlMin, Integer coinlvlMax, Double taskId) {
        this.coinlvlMin = coinlvlMin;
        this.coinlvlMax = coinlvlMax;
        this.taskId = taskId;
    }

    public Integer getCoinlvlMin() {
        return coinlvlMin;
    }

    public void setCoinlvlMin(Integer coinlvlMin) {
        this.coinlvlMin = coinlvlMin;
    }

    public Integer getCoinlvlMax() {
        return coinlvlMax;
    }

    public void setCoinlvlMax(Integer coinlvlMax) {
        this.coinlvlMax = coinlvlMax;
    }

    public Double getTaskId() {
        return taskId;
    }

    public void setTaskId(Double taskId) {
        this.taskId = taskId;
    }
}
