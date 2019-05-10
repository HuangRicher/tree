package com.tongzhu.util;

public class ConfigDataUtil {

    public static int addAmbienceBySelf(Integer ambienceCount){
        return ambienceCount+RandomUtil.random(5,8);
    }

    public static int addAmbienceVisitor(Integer ambienceCount){
        return ambienceCount+RandomUtil.random(3,5);
    }

    public static int addAttackFury(int furyValue){
        return furyValue+RandomUtil.random(8, 12);
    }

    public static int addDefenderFury(int furyValue){
        return furyValue+RandomUtil.random(12, 16);
    }

}
