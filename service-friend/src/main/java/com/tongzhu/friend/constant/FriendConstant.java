package com.tongzhu.friend.constant;
/**
 * 好友常量类
 */
public class FriendConstant {

    /** 好友上限个数 **/
    public static final Integer MAX_FRIEND_NUM = 100;

    /** 好友申请数量不超过50条 **/
    public static final int MAX_APPLY_FOR_FRIEND_COUNT =50;

    /** 增加的亲密度*/
    public static final int INTIMACY=5;

    /*同意好友请求*/
    public static final int TYPE_AGREE=1;

    //拒绝好友请求
    public static final int TYPE_DISAGREE=2;


    //好友在线
    public static final int STATUS_ONLINE=1;

    //好友离线
    public static final int STATUS_OFFLINE=0;

}
