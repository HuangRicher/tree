package com.tongzhu.treehouse.constant;

import java.util.Arrays;
import java.util.List;

public class WorkerConstant {

    //宅友保护6小时
    public static final int PROTECT_HOUR_6=6;

    //宅友保护24小时
    public static final int PROTECT_HOUR_24=24;

    //宅友保护72小时
    public static final int PROTECT_HOUR_72=72;

    //宅友保护6小时消耗30钻石
    public static final int PROTECT_6_CONSUME=30;

    //宅友保护24小时消耗100钻石
    public static final int PROTECT_24_CONSUME=100;

    //宅友保护72小时消耗240钻石
    public static final int PROTECT_72_CONSUME=240;

    //首次获得宅友保护时间为2小时
    public static final int PROTECT_FREE_HOUR_2=2;

    //降低宅友身价比率
    public static final float REDUCE_PRICE_RATE=0.9f;

    //是否可以操作降低宅友身价 0:不可以
    public static final Boolean CAN_NOT_REDUCE_SELLING_PRICE=false;

    //是否可以操作降低宅友身价 1：可以
    public static final Boolean CAN_REDUCE_SELLING_PRICE=true;

    //当天最多被转让的次数
    public static final int MAX_EXCHANGE_COUNT=3;

    //购买推荐宅友个数
    public static final int RECOMMEND_COUNT=25;

    //MPC玩家用户ID
    public final static List<String> MPC_USER_IDS = Arrays.asList("100001","100002","100003","100004","100005","100006","100007","100008","100009","100010",
            "100011","100012","100013","100014","100015","100016","100017","100018","100019","100020");
}
