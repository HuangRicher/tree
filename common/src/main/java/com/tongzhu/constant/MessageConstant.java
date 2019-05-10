package com.tongzhu.constant;

/**
 * Created by Administrator on 2018/10/19 0019.
 */
public class MessageConstant {

    // 删除
    public static final int STATUS_NORMAL=0;

    // 正常
    public static final int STATUS_REMOVE=1;

    /**
     * 宅友消息类型
     */
    //0-我的宅友被买走了
    public static final int TYPE_WORKER_BUY=0;

    //1-我是自由身，我被别人买走了
    public static final int TYPE_FREE_BUY=1;

    //2-我是别人的宅友，我被买走了
    public static final int TYPE_HASMASTER_BUY=2;

    //11-树屋访客邀请通知
    public static final int TYPE_TREE_HOUSE_INVITE=11;

    /** 消息类型：9:取消婚礼**/
    public static final Integer TYPE_MARRY_CANCLE = 9;

    /** 消息类型：8:邀请参加婚礼**/
    public static final Integer TYPE_MARRY_JOIN = 8;
    //砸金库
    public static final Integer TYPE_POUND_COFFERS= 3;


}
