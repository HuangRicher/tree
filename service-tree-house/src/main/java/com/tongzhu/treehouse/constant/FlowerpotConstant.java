package com.tongzhu.treehouse.constant;

public class FlowerpotConstant {



    public static final Integer LOCK_STATUS=0;

    public static final Integer UNLOCK_STATUS=1;

    //果实成熟的时间变为90分钟(播种期60分钟，成长期30分钟，即将成熟5分钟)，其他玩家可以偷产出(有上限)
    //树屋花播种期60分钟(单位秒)
    public static final Integer FLOWER_SOW_TIME=60*60;
    //树屋花成长期30分钟(单位秒)
    public static final Integer FLOWER_GROWTH_TIME=(60+30)*60;
    //树屋花即将成熟5分钟(单位秒)
    public static final Integer FLOWER_MATURE_TIME=(60+30+5)*60;

    //播种状态
    public static final Integer FLOWER_STATUS_1=1;
    //成长状态
    public static final Integer FLOWER_STATUS_2=2;
    //即将成熟状态
    public static final Integer FLOWER_STATUS_3=3;
    //收获状态
    public static final Integer FLOWER_STATUS_4=4;

}
