package com.tongzhu.fishing.constant;

public class ConstantValue {
    /**
     * 自定义协议的开始标志
     */
    public static final int HEAD_DATA = 0X76;

    /**
     * 钓鱼鱼塘缓存前缀
     */
//	public static final String REDIS_GOFISHING_POND_PREFIX = "goFishing_";


    /**
     * 背包缓存前缀
     */
    public static final String REDIS_USER_GOODS_PREFIX = "userGoods_";

    /**
     * 渔场品质
     */
    public static final String REDIS_FISHING_QUALITY = "QUALITY_";


    private final String PREFIX = "TREEHOUSE_";

    public static final String COMMONFISHING = "COMMONFISHING_";

    public static final String SENIOR = "SENIOR_";

    public static final String RARE = "RARE_";


    public static final String COMMONFISHING_FISH = "COMMONFISHING_FISH";

    public static final String SENIOR_FISH = "SENIOR_FISH";

    public static final String RARE_FISH = "RARE_FISH";

    public static final String COMMONFISHING_PROBABILITY = "COMMONFISHING_PROBABILITY";

    public static final String SENIOR_PROBABILITY = "SENIOR_PROBABILITY";

    public static final String RARE_PROBABILITY = "RARE_PROBABILITY";


    public static final String FISH_PRIZES = "FISH_PRIZES_";

    public static final String FISH_PRIZES_WEIGHT = "FISH_PRIZES_WEIGHT_";


    /**
     * 转盘配置
     */
    public static final String DIAL_CONFIG_JSON = "DIAL_CONFIG_JSON";

    /**
     * 转盘冻结时间
     */
    public static final String DIAL_FREEZE_TIME = "DIAL_FREEZE_TIME_";
    /**
     * 转盘倍率随机
     */
    public static final String DIAL_RATE_RANDOM = "DIAL_RATE_RANDOM";
    /**
     * 转盘倍率随机权重
     */
    public static final String DIAL_RATE_RANDOM_WEIGHT = "DIAL_RATE_RANDOM_WEIGHT";

    /**
     * 转盘随机
     */
    public static final String DIAL_RANDOM = "DIAL_RANDOM_";
    /**
     * 转盘随机权重
     */
    public static final String DIAL_RANDOM_WEIGHT = "DIAL_RANDOM_WEIGHT_";

    /**
     * 转盘随机权重
     */
    public static final String DIAL_COUNT = "DIAL_COUNT_";

    /**
     * 天降财神配置
     */
    public static final String DIAL_DROP_COINS = "DIAL_DROP_COINS";

    /**
     * 砸金库任务编码
     */
    public static final Integer TASK_TYPE_ZJK = 4;

    /**
     * 砸金库消息类型编码
     */
    public static final Integer ZJK_MESSAGE = 4;



    public static final String LOTTERY_FISH = "LOTTERY_FISH";
    public static final String PROBABILITY_LOTTERY_FISH = "PROBABILITY_LOTTERY_FISH";

    public static final Integer FISH_BOUTIQUE_NO = 0;
    public static final Integer FISH_BOUTIQUE_YES = 1;

    /**
     * 抽奖钓鱼日常任务编码
     */
    public static final Integer TASK_TYPE_CJDY = 5;



    /**
     * 逛街配置
     */
    public static final String DIAL_SHOPPING_INFO = "DIAL_SHOPPING_INFO";
    public static final String PROBABILITY_DIAL_SHOPPING_INFO = "PROBABILITY_DIAL_SHOPPING_INFO";
    public static final Integer TASK_TYPE_GJ = 12;


    public static final Integer DIAL_SHOPPING_GJ = 1;
    public static final Integer DIAL_SHOPPING_KDY = 2;
    public static final Integer DIAL_SHOPPING_ZF = 3;
    public static final Integer DIAL_SHOPPING_YX = 4;
    public static final Integer DIAL_SHOPPING_JKSQ = 5;
}
