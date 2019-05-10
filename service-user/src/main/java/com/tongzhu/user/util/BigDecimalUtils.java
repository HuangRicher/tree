package com.tongzhu.user.util;

import java.math.BigDecimal;


/**
 * 为避免金额计算精度丢失，将double类型转换为BigDecimal再进行相关计算
 */
public class BigDecimalUtils {

    /**
     * 向上进位
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundUp(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_UP);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundDown(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_DOWN);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundCeiling(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_CEILING);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundFloor(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfUp(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfDown(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfEven(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * @param: @param
     *             newScale
     * @param: @param
     *             b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundUnnecessary(int newScale, BigDecimal b) {
        return b.setScale(newScale, BigDecimal.ROUND_UNNECESSARY);
    }

    /*public static void main(String[] args) {
        BigDecimal b = new BigDecimal("541.251");
        System.out.println("执行数值：" + b.toString());
        // 如果b为正数，则进行RoundUp操作，否则，RoundDown操作
        System.out.println("setScaleRoundCeiling:" + setScaleRoundCeiling(1, b));
        // 如果b为正数，则进行RoundDown操作，否则，RoundUp操作
        System.out.println("setScaleRoundFloor:" + setScaleRoundFloor(1, b));
        // 进位
        System.out.println("setScaleRoundUp:" + setScaleRoundUp(-1, b).intValue());
        // 截位
        System.out.println("setScaleRoundDown:" + setScaleRoundDown(1, b));
        // 四舍五入，截取小数 > .5 进位
        System.out.println("setScaleRoundHalfDown:" + setScaleRoundHalfDown(2, b));
        // 四舍五入，截取小数 >= .5 进位
        System.out.println("setScaleRoundHalfUp:" + setScaleRoundHalfUp(2, b));
        // 如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP ；如果它为偶数，则作 ROUND_HALF_DOWN 。
        // 奇数/偶数根据舍弃位数后，数值的最后一位判断，比如4.123，保留1位，为4.1，则1是奇数
        System.out.println("setScaleRoundHalfEven:" + setScaleRoundHalfEven(1, b));
        // 该“伪舍入模式”实际是指明所要求的操作必须是精确的，，因此不需要舍入操作。
        System.out.println("setScaleRoundUnnecessary:" + setScaleRoundUnnecessary(3, b));
    }*/
}

