package com.tongzhu.user.constant;

/**
 * 排行榜常量类
 */
public class RanklingListConstant {

    //身价排行榜显示30条记录
    public static final int SELLING_PRICE_RANKING_SHOW_COUNT=30;

    //土豪排行榜显示30条记录
    public static final int MONEY_RANKING_SHOW_COUNT=30;

    //角色等级排行总榜显示50条记录
    public static final int ROLE_LEVEL_RANKING_SHOW_COUNT=50;
    public static final String ROLE_LEVEL_REDIS="ROLE_LEVEL_REDIS";

    //角色魅力值排行总榜显示50条记录
    public static final int CHARM_NUM_RANKING_SHOW_COUNT=50;
    public static final int MONTH_CHARM_NUM_RANKING_SHOW_COUNT=50;
    public static final String CHARM_NUM_REDIS="CHARM_NUM_REDIS";
    public static final String MONTH_CHARM_NUM_REDIS="MONTH_CHARM_NUM_REDIS";


    //角色战斗力排行总榜显示50条记录
    public static final int FIGHTING_RANKING_SHOW_COUNT=50;
    public static final int MONTH_FIGHTING_RANKING_SHOW_COUNT=50;
    public static final String FIGHTING_REDIS="FIGHTING_REDIS";
    public static final String MONTH_FIGHTING_REDIS="MONTH_FIGHTING_REDIS";

    //钻石消费
    public static final int MONTH_ZS_RANKING_SHOW_COUNT=50;
    public static final String MONTH_ZS_REDIS="MONTH_FIGHTING_REDIS";
    // 1 战斗力月榜 2 魅力月榜 3 幸福值月榜
    /**
     * 战斗力月榜
     */
    public static final int  MONTH_FIGHTING_TYPE=1;

    /**
     * 2 魅力月榜
     */
    public static final int MONTH_CHARM_NUM_TYPE=2;
    /**
     * 3 钻石消费月榜
     */
    public static final int MONTH_ZS_NUM_TYPE=3;


    //拥有宠物排行总榜显示50条记录
    public static final int USER_PET_RANKING_SHOW_COUNT=50;
    public static final String USER_PET_REDIS="USER_PET_REDIS";
}
