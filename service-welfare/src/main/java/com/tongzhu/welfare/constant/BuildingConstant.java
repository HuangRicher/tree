package com.tongzhu.welfare.constant;

/**
 * 建筑常量类
 *
 * @author 吴恒斌
 * @date 2018年10月31日
 */
public class BuildingConstant {
    
    
    /**
     * 建筑是否产出金币：是
     **/
    public static final Integer BUILDING_OUTPUT_TRUE = 1;
    /**
     * 建筑是否产出金币：否
     **/
    public static final Integer BUILDING_OUTPUT_FALSE = 2;
    
    /**
     * 建筑是否已开启：是 :0
     **/
    public static final Integer BUILDING_OPEN_TRUE = 0;
    /**
     * 建筑是否已开启：否 :1
     **/
    public static final Integer BUILDING_OPEN_FALSE = 1;
    
    /**
     * 物品类型：道具
     */
    public static final Integer Prop = 1;   
    /**
     * 物品类型：装备
     */
    public static final Integer Equip = 2;  
    /**
     * 物品类型：武器
     */
    public static final Integer Weapon = 3; 
    
    /**
     * 建筑是否可领金币：否
     **/
    public static final Integer BUILDING_GOLD_FALSE = 1;
    /**
     * 建筑是否可领金币：是
     **/
    public static final Integer BUILDING_GOLD_TRUE = 2;
    
    /**
     * 建筑升级状态：升级中
     **/
    public static final Integer BUILDING_UPDATE_TRUE = 1;
    /**
     * 建筑升级状态：升级完成
     **/
    public static final Integer BUILDING_UPDATE_FALSE = 2;
    /**
     * 建筑升级状态：建筑可升级
     **/
    public static final Integer BUILDING_UPDATE_READY = 3;
    
    /**
     * 建筑升级状态编码：建筑可升级的编码
     **/
    public static final String BUILDING_UPDATE_READY_STRING = "build";
    
    /**
     * 提醒红点
     */
    public static final String RED_TIP="redTip";
    
    /**
     * 建筑开启等级：达到对应等级开启
     **/
    public static final Integer BUILDING_OPEN_GRADE = 1;
    /**
     * 建筑开启条件：结婚后开启
     **/
    public static final Integer BUILDING_OPEN_MARRY = 2;
    
    
    /**
     * 建筑类型：树屋
     **/
    public static final Integer BUILDING_TYPE_HOUSE = 1;
    /**
     * 建筑类型：金库
     **/
    public static final Integer BUILDING_TYPE_COFFERS = 2;
    /**
     * 建筑类型：福利社
     **/
    public static final Integer BUILDING_TYPE_WELFARE = 3;
    /**
     * 建筑类型：婚房
     **/
    public static final Integer BUILDING_TYPE_MARRIAGE = 4;
    /**
     * 建筑类型：教堂
     **/
    public static final Integer BUILDING_TYPE_CHURCH = 5;
    /**
     * 建筑类型：铁匠铺
     **/
    public static final Integer BUILDING_TYPE_SMITHY = 6;
    /**
     * 建筑类型：宠物店
     **/
    public static final Integer BUILDING_TYPE_PETSHOP = 7;
    /**
     * 建筑类型：酒馆
     **/
    public static final Integer BUILDING_TYPE_WINESHOP = 8;
    /**
     * 建筑类型：雕像
     **/
    public static final Integer BUILDING_TYPE_STATUE = 9;
    
    
    /**
     * 树屋每级增加属性值：46
     **/
    public static final Double BUILDING_TYPE_HOUSE_NUM = 46.0;
    /**
     * 金库每级增加属性值：4
     **/
    public static final Double BUILDING_TYPE_COFFERS_NUM = 4.0;
    /**
     * 福利社每级增加属性值：4
     **/
    public static final Double BUILDING_TYPE_WELFARE_NUM = 4.0;
    /**
     * 酒馆每级增加属性值：2
     **/
    public static final Double BUILDING_TYPE_MARRIAGE_NUM = 2.0;
    /**
     * 铁匠铺每级增加属性值：2
     **/
    public static final Double BUILDING_TYPE_CHURCH_NUM = 2.0;
    /**
     * 教堂每级增加属性值：2
     **/
    public static final Double BUILDING_TYPE_SMITHY_NUM = 2.0;
    /**
     * 宠物店每级增加属性值：2
     **/
    public static final Double BUILDING_TYPE_PETSHOP_NUM = 2.0;
    /**
     * 婚房每级增加属性值：7
     **/
    public static final Double BUILDING_TYPE_WINESHOP_NUM = 7.0;
    /**
     * 每级增加属性值：0.0
     **/
    public static final Double BUILDING_TYPE_ZERO_NUM = 0.0;
}
