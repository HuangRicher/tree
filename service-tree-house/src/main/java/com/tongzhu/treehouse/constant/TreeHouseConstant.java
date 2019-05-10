package com.tongzhu.treehouse.constant;

import java.util.HashMap;
import java.util.Map;

public class TreeHouseConstant {

    //培养一次
    public static final int CULTURE_TYPE_ONE=1;

    //培养十次
    public static final int CULTURE_TYPE_TEN=10;



    //升级培养类型 培养
    public static final int UPGRADE_TYPE_CULTURE=1;

    //升级培养类型 突破
    public static final int UPGRADE_TYPE_BREAK=2;

    //升级培养类型 宅友上限
    public static final int UPGRADE_TYPE_WORKER=3;


    //树屋最高等级
    public static final int TREE_HOUSE_MAC_LEVEL=50;


    //树屋房间最大数
    public static final int TREE_HOUSE_ROOM_MAX_COUNT=25;


    //是否为游戏好友 0:不是
    public static final Boolean NOT_GAME_FRIEND=false;

    //是否为游戏好友
    public static final Boolean IS_GAME_FRIEND=true;

    //指派工种等级 1：初级  2：中级  3：高级
    public static final Integer SIGN_WORK_GRADE_PRIMARY=1;

    public static final Integer SIGN_WORK_GRADE_MEDIUM=2;

    public static final Integer SIGN_WORK_GRADE_SENIOR=3;

    public static final Integer GRADE_PRIMARY_CONSUME_MONEY=1500;

    public static final Integer GRADE_MEDIUM_CONSUME_MONEY=3000;

    public static final Integer GRADE_SENIOR_CONSUME_MONEY=4500;

    public static final Integer GRADE_PRIMARY_MONEY=7500;

    public static final Integer GRADE_MEDIUM_MONEY=12500;

    public static final Integer GRADE_SENIOR_MONEY=17500;

    public static final Integer GRADE_PRIMARY_EXP=8000;

    public static final Integer GRADE_MEDIUM_EXP=12000;

    public static final Integer GRADE_SENIOR_EXP=16000;

    public static final Integer GRADE_PRIMARY_ENV=15;

    public static final Integer GRADE_MEDIUM_ENV=45;

    public static final Integer GRADE_SENIOR_ENV=90;


    //当天最大人气值
    public static final Integer ATMOSPHERE_MAX=138240;

    public static final Integer DAY_COME_IN_TREE_HOUSE_MAX=100;

    //每分钟人气
    public static final Integer ATMOSPHERE_EVERY_MINUTE=24;

    public static final HashMap<String, Map<String,Integer>> map = new HashMap<String, Map<String,Integer>>() {
        {
            put("work-1", new HashMap<String,Integer>(){{put("money",7500);put("exp",8000);}});
            put("work-2", new HashMap<String,Integer>(){{put("money",12500);put("exp",12000);}});
            put("work-3", new HashMap<String,Integer>(){{put("money",17500);put("exp",16000);}});
            put("allocating-1", new HashMap<String,Integer>(){{put("money",1500);}});
            put("allocating-2", new HashMap<String,Integer>(){{put("money",3000);}});
            put("allocating-3", new HashMap<String,Integer>(){{put("money",4500);}});
            put("sweep-1", new HashMap<String,Integer>(){{put("env",15);}});
            put("sweep-2", new HashMap<String,Integer>(){{put("env",45);}});
            put("sweep-3", new HashMap<String,Integer>(){{put("env",90);}});
        }
    };

}
