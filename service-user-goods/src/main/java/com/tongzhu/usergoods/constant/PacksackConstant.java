package com.tongzhu.usergoods.constant;

/**
 * Created by Administrator on 2018/10/9 0009.
 */
public class PacksackConstant {


    /**
     * 道具存在位置   0 背包 1仓库 2 已佩戴 3 其它 4武器库
     */
    public static final Integer PROP_PLACE_KNAPSACK = 0;

//    public static final Integer PROP_PLACE_WAREHOUSE = 1;

    public static final Integer PROP_PLACE_ADORN = 2;

    public static final Integer PROP_PLACE_OTHER = 3;

    public static final Integer PROP_PLACE_WEAPON = 4;
    /**
     * 背包物品类型   1 道具 2装备 3 武器
     */

    public static final Integer GOODS_TYPE_PROP = 1;

    public static final Integer GOODS_TYPE_EQUIPMENT = 2;

    public static final Integer GOODS_TYPE_WEAPON = 3;


    /**
     * 仓库最大存储容量
     */
    public static final Integer WAREHOUSE_CAPACITY = 120;

    /**
     * 背包最大存储容量
     */
    public static final Integer KNAPSACK_CAPACITY = 120;

    /**
     * 道具类型  0 资源
     */
    public static final Integer PROP_TYPE_RESOURCE = 0;

    /**
     * 道具类型  1 装备
     */
    public static final Integer PROP_TYPE_EQUIP = 1;

    /**
     * 道具类型  2 宝石
     */
    public static final Integer PROP_TYPE_GEM = 2;

    /**
     * 道具类型  3 宠物
     */
    public static final Integer PROP_TYPE_PET = 3;

    /**
     * 道具类型  4 材料
     */
    public static final Integer PROP_TYPE_MATERIALS = 4;

    /**
     * 道具类型  5 药水
     */
    public static final Integer PROP_TYPE_LIQUID = 5;

    /**
     * 道具类型  6 武器
     */
    public static final Integer PROP_TYPE_WEAPON = 6;

    /**
     * 道具类型  7 其它
     */
    public static final Integer PROP_TYPE_OTHER = 7;


    /**
     * 道具佩戴位置  0 无要求
     */
    public static final Integer PROP_ADORN_ALL = 0;


    /**
     * 道具佩戴位置  1 头部 tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_HEAD = 1;

    public static final String USER_ADORN_EQUIP_HEAD = "head";

    /**
     * 道具佩戴位置   2 衣服  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_CLOTHING = 2;

    public static final String USER_ADORN_EQUIP_CLOTHING = "clothing";

    /**
     * 道具佩戴位置  3 裤子 tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_TROUSERS = 3;

    public static final String USER_ADORN_EQUIP_TROUSERS = "trousers";

    /**
     * 道具佩戴位置  4 鞋子  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_SHOE = 4;

    public static final String USER_ADORN_EQUIP_SHOE = "shoe";

    /**
     * 道具佩戴位置 5 时装  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_FASHION = 5;

    public static final String USER_ADORN_EQUIP_FASHION = "fashion";

    /**
     * 道具佩戴位置  6 婚戒  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_WEDDING_RING = 6;

    public static final String USER_ADORN_EQUIP_WEDDING_RING = "weddingRing";

    /**
     * 道具佩戴位置  7 武器  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_WEAPON = 7;

    public static final String USER_ADORN_EQUIP_WEAPON = "weapon";

    /**
     * 道具佩戴位置  8 戒指  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_RING = 8;

    public static final String USER_ADORN_EQUIP_RING = "ring";


    /**
     * 道具佩戴位置   9 护腕  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_CUFF = 9;

    public static final String USER_ADORN_EQUIP_CUFF = "cuff";

    /**
     * 道具佩戴位置   10 项链  tz_user_adorn_equip  实体类对应的字段类型
     */
    public static final Integer PROP_ADORN_NECKLACE = 10;

    public static final String USER_ADORN_EQUIP_NECKLACE = "necklace";

    /**
     * 物品状态 0 正常 1 摧毁
     */

    public static final Integer GOODS_STATUS_NORMAL = 0;

    public static final Integer GOODS_STATUS_REMOVE = 1;

    /**
     * 物品是否可以叠加 0 可以 1 不可以
     */
    public static final Integer GOODS_SUPERPOSITION_YES = 0;

    public static final Integer GOODS_SUPERPOSITION_NO = 1;


    /**
     * redis 常量
     */

    /**
     * 礼包奖励随机抽取
     */
    public static final String GIFT_BAG_ITEMS = "GIFT_BAG_ITEMS";

    public static final String GIFT_BAG_ITEMS_WEIGHT = "GIFT_BAG_ITEMS_WEIGHT";

    /**
     * 0 可以 1 不可以
     */
    public static final Integer GOODS_OPERATION_MAY = 1;

    public static final Integer GOODS_OPERATION_CANNOT = 0;


    /**
     * 装备数量
     */
    public static final Integer GOODS_INITIALIZE_AMOUNT = 1;

    /**
     * 0 通用 1 卫士 2 刺客 3 工匠 4 法师
     */
    public static final String GODOS_PROFESSION_COMMONALITY = "0";

    public static final String GODOS_PROFESSION_GUARDIANS = "1";

    public static final String GODOS_PROFESSION_ASSASSIN = "2";

    public static final String GODOS_PROFESSION_CRAFTS = "3";

    public static final String GOODS_PROFESSION_MASTER = "4";

    /**
     * limitTime(0:不限时,1限时,2过期)
     */
    public static final Integer GOODS_UNLIMITED = 0;

    public static final Integer GOODS_LIMITTIME = 1;

    public static final Integer GOODS_OVERDUE = 2;

    /**
     * 是否为原型武器 0 原型武器 1 不是原型武器
     */
    public static final Integer GOODS_ORIGINAL_YES = 0;

    public static final Integer GOODS_ORIGINAL_NO = 1;

    /**
     * use 购买物品是否立即使用 0 使用 1 不使用
     */
    public static final Integer GOODS_USE_YES = 0;

    public static final Integer GOODS_USE_NO = 1;

    /**
     * 装备最低等级
     */
    public static final Integer GOODS_MINLEVEL = 0;

    /**
     * 装备最低等级
     */
    public static final Integer TASK_TYPE = 1;

    /**
     * 0 默认礼包类型   1 道具卡 2装备卡 3武器卡 4 宠物卡礼包 5 经验卡礼包
     */
    public static final Integer GIFT_TYPE = 0;
    public static final Integer GIFT_TYPE_PROP = 1;
    public static final Integer GIFT_TYPE_EQUIPMENT = 2;
    public static final Integer GIFT_TYPE_ARSENAL = 3;
    public static final Integer GIFT_TYPE_PET = 4;
    public static final Integer GIFT_TYPE_EXP = 5;
    public static final Integer GIFT_TYPE_TREE_HOUSE = 6;

    /**
     * 升阶
     */
    public static final Integer GOODS_UPGRADE_NO = 0;

    /**
     * 战斗力月榜 type
     */

    public static final Integer CRUNCHIES_FIGHTING_TYPE = 1;
    /**
     * 消费钻石月榜 type
     */

    public static final Integer CRUNCHIES_ZS_TYPE = 3;
}
