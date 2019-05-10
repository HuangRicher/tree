package com.tongzhu.welfare.constant;

/**
 * 结婚常量类
 * @author 吴恒斌
 * @date 2018年11月10日
 */
public class MarryConstant {
	
	
	/** 订婚状态：0:待确认**/
	public static final Integer MARRY_DECIDED  = 0;
	/** 订婚状态：1:订婚成功**/
	public static final Integer MARRY_TRUE = 1;
	/** 订婚状态：2:订婚失败(取消订婚)**/
	public static final Integer MARRY_FALSE = 2;
	/** 订婚状态：3:订婚失效（已离婚）**/
	public static final Integer MARRY_OVERDUE = 3;
	/** 订婚状态：4:成功举行婚礼**/
	public static final Integer MARRY_SUCCESS = 4;
	
	/** 消息类型：9:取消婚礼**/
	public static final Integer MARRY_CANCLE = 9;
	
	/** 消息类型：8:邀请参加婚礼**/
	public static final Integer MARRY_JOIN = 8;
	
	/** 进入婚礼现场类型：1:来宾入口**/
	public static final Integer MARRY_GUEST = 1;
	/** 进入婚礼现场类型：2:主人入口**/
	public static final Integer MARRY_MASTER = 2;
	
	/** 请求教堂信息类型：1：当前婚礼**/
	public static final Integer MARRY_CHURCH_CURRENT = 1;
	/** 请求教堂信息类型：2：举办婚礼**/
	public static final Integer MARRY_CHURCH_PLACE = 2;
	/** 请求教堂信息类型：3：气球巡游**/
	public static final Integer MARRY_CHURCH_PARADE = 3;
	/** 请求教堂信息类型：4：结婚记录**/
	public static final Integer MARRY_CHURCH_LOG = 4;
	
	/** 求婚条件 ：亲密度大于100**/
	public static final Integer MARRY_CONDITIONS_INTIMACY = 100;
	
	/** 婚礼现场 ：好友人数 ：8**/
	public static final Integer MARRY_SCENE_FRIEND = 8;
	
	/** 婚礼现场 ：观礼人数 ：20**/
	public static final Integer MARRY_SCENE_ATTEND = 20;
	
	/** 婚礼现场 ：敬酒次数：3**/
	public static final Integer MARRY_SCENE_TOAST_NUM = 3;
	
	/** 结婚类型 ：1：普通婚礼**/
	public static final Integer MARRY_TYPE_GENERAL = 1;
	/** 普通婚礼免费喜糖次数：0**/
	public static final Integer MARRY_GENERAL_JOYFUL_NUM = 0;
	/** 结婚类型：2：浪漫婚礼**/
	public static final Integer MARRY_TYPE_ROMANCE = 2;
	/** 浪漫婚礼免费喜糖次数：3**/
	public static final Integer MARRY_ROMANCE_JOYFUL_NUM = 3;
	/** 结婚类型：3：豪华婚礼**/
	public static final Integer MARRY_TYPE_LUXURY = 3;
	/** 豪华婚礼免费喜糖次数：5**/
	public static final Integer MARRY_LUXURY_JOYFUL_NUM = 5;
	/** 结婚类型：4：豪华预约婚礼**/
	public static final Integer MARRY_TYPE_LUXURY_BOOK = 4;
	
	/** 请求巡游信息类型：1：普通巡游**/
	public static final Integer MARRY_CRUISE_GENERAL = 1;
	/** 普通巡游消耗钻石： 36 **/
	public static final Integer MARRY_CRUISE_GENERAL_NUM = 4288;
	/** 请求巡游信息类型：2：浪漫巡游**/
	public static final Integer MARRY_CRUISE_ROMANCE = 2;
	/** 浪漫巡游消耗钻石： 68 **/
	public static final Integer MARRY_CRUISE_ROMANCE_NUM = 9688;
	/** 请求巡游信息类型：3：豪华巡游**/
	public static final Integer MARRY_CRUISE_LUXURY = 3;
	/** 豪华巡游消耗钻石： 258 **/
	public static final Integer MARRY_CRUISE_LUXURY_NUM = 25888;
	
	/** 处理求婚请求类型 ： 1：接受**/
	public static final Integer MARRY_RECEIVE_RESULT = 1;
	/** 处理求婚请求类型 ： 2：拒绝**/
	public static final Integer MARRY_RECEIVE_RESULT2 = 2;
	
	/** 结婚戒指类型：1：同心戒指**/
	public static final String MARRY_RING_GENERAL = "10001";
	/** 结婚戒指类型：2：比翼双飞戒指**/
	public static final String MARRY_RING_ROMANCE = "10002";
	
	/** 结婚消耗品：经验:50004**/
	public static final String MARRY_CONSUMABLES_EXP = "50004";
	
	/** 结婚消耗品：心意石**/
	public static final String MARRY_CONSUMABLES_STONE = "11401";
	/** 结婚消耗品：金币**/
	public static final String MARRY_CONSUMABLES_COIN = "50001";
	/** 结婚消耗品：钻石**/
	public static final String MARRY_CONSUMABLES_DIAMOND = "50002";
	
	/** 结婚增加幸福值：浪漫婚礼：500 **/
	public static final Integer MARRY_HAPPINESS_ROMANCE_NUM = 500;
	/** 结婚增加幸福值：豪华婚礼：3600**/
	public static final Integer MARRY_HAPPINESS_LUXURY_NUM = 3600;
	/** 巡游增加幸福值：普通巡游：1500 **/
	public static final Integer MARRY_CRUISE_GENERAL_HAPPINESS_NUM = 1500;
	/** 巡游增加幸福值：浪漫巡游：3500**/
	public static final Integer MARRY_CRUISE_ROMANCE_HAPPINESS_NUM = 3500;
	/** 巡游增加幸福值：豪华巡游：10000**/
	public static final Integer MARRY_CRUISE_LUXURY_HAPPINESS_NUM = 10000;
	
	/** 喜糖随机物品配置 **/
	public static final String MARRY_JOYFUL = "[{ "+"goodsId"+": 11210,"+"weight"+": 140"+"}, {"+"goodsId"+": 11220,"+"weight"+": 140"+"},"
			+ "{"+"goodsId"+": 11230,"+"weight"+": 140"+"},{"+"goodsId"+": 11240,"+"weight"+": 140"+"},{"+"goodsId"+": 11901,"+"weight"+": 100"+"},"
			+ "{"+"goodsId"+": 11250,"+"weight"+": 80"+"},{"+"goodsId"+": 11251,"+"weight"+": 80"+"},{"+"goodsId"+": 11252,"+"weight"+": 80"+"},"
			+ "{"+"goodsId"+": 11903,"+"weight"+": 3"+"},{"+"goodsId"+": 11253,"+"weight"+": 80"+"},{"+"goodsId"+": 11319,"+"weight"+": 17"+"}]";
	
	/** 单次喜糖所消耗的钻石：15**/
	public static final Integer MARRY_JOYFUL_NUM = 150;
	
	/** 结婚消耗品：使用心意石数量: 99 **/
	public static final Integer MARRY_CONSUMABLES_STONE_NUM = 99;
	/** 结婚消耗品：使用金币数量 : 300000**/
	public static final Integer MARRY_CONSUMABLES_COIN_NUM = 300000;
	/** 浪漫结婚消耗品：使用钻石数量: 3500**/
	public static final Integer MARRY_CONSUMABLES_DIAMOND_NUM = 5888;
	/** 豪华结婚消耗品：使用钻石数量: 298**/
	public static final Integer MARRY_CONSUMABLES_DIAMOND_2NUM = 36888;
	/** 突破爱情树的必备材料: "11402"**/
	public static final String MARRY_CONSUMABLES_LOVE_STONE = "11402";
	/** 婚礼时发送祝福赠送经验值： 1688**/
	public static final Integer MARRY_WISH_NUM = 1688;
	/** 婚礼时敬酒赠送经验值: 888**/
	public static final Integer MARRY_TOAST_NUM = 888;
	
	/** 角色的性别：1：男**/
	public static final Integer MARRY_BOY_TYPE = 1;
	/** 角色的性别：2：女**/
	public static final Integer MARRY_GRIL_TYPE = 2;
	
	/** 用户是否在线 ： 0：离线**/
	public static final Integer MARRY_OFF_LINE = 0;
	/** 用户是否在线： 1：在线 **/
	public static final Integer MARRY_ON_LINE = 1;
	
	/** 增物品类型 ： 2：装备**/
	public static final Integer MARRY_ADD_TYPE_EQUIP = 2;
	/** 增物品类型 ： 3：武器**/
	public static final Integer MARRY_ADD_TYPE_WEAPON = 3;
	
	/** 爱情树初始化时的经验值上限：600**/
	public static final Integer MARRY_TREE_EXPLIMIT = 600;
	/** 爱情树初始化时的嬉闹次数：3**/
	public static final Integer MARRY_TREE_PLAYJOKES = 3;
	/** 爱情树初始化时的洞房次数：3**/
	public static final Integer MARRY_TREE_WEDDING = 3;
	/** 爱情树增加经验时的暴击率：10**/
	public static  Integer MARRY_LOVE_PLAYJOKES_MIN = 10;
	/** 爱情树最高等级： 50 **/
	public static final Integer MARRY_TREE_GRADE_MAX = 50;
	/** 情缘最高等级： 90 **/
	public static final Integer MARRY_LOVE_GRADE_MAX = 90;
	
	/** 每次嬉闹增加的情缘值最大值为：150**/
	public static  Integer MARRY_LOVE_PLAYJOKES_MAX = 150;
	/** 每次嬉闹增加的情缘值最小值为：75**/
	public static  Integer MARRY_LOVE_CRIT_NUM = 75;
	
	/** 每次洞房增加的情缘值最大值为：500**/
	public static  Integer MARRY_LOVE_WEDDING_MAX = 500;
	/** 每次洞房增加的情缘值最小值为：200**/
	public static  Integer MARRY_LOVE_WEDDING_MIN = 200;
	
	/** 储存求婚时被求婚人ID的redis常量名：Engagement**/
	public static  String MARRY_ENGAGEMENT_LIST= "Engagement";
	
	/** 支线任务：用戒指求婚：2011**/
	public static  Integer MARRY_TASK_BRANCH_NUM = 2011;
	
	/** 支线任务：举行婚礼：2012**/
	public static  Integer MARRY_TASK_BRANCH_NUM2 = 2012;
	
	/** 支线任务：气球巡游：2013**/
	public static  Integer MARRY_TASK_BRANCH_NUM3 = 2013;
}
