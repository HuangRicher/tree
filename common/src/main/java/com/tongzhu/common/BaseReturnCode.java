package com.tongzhu.common;

public class BaseReturnCode {
	/** 非法操作 **/
	public static final int ILLEGAL = -1;

	/** 参数错误 **/
	public static final int PARAMS_ERROR = 10000;
	
	/** 业务处理失败 **/
	public static final int PROCESS_ERROR = 20000;

	/**
	 * vip等级不够
	 **/
	public static final int PROCESS_VIP_INSUFFICIENT_LEVEL = 20000;

	/**
	 * 用户等级不够
	 **/
	public static final int PROCESS_USER_INSUFFICIENT_LEVEL = 20000;

	/**
	 * 用户性别错误
	 **/
	public static final int PROCESS_USER_SEX_ERROR = 20000;

	/**
	 * 道具不存在
	 **/
	public static final int PROCESS_USER_GOODS_INEXISTENCE = 20000;

	/**
	 * 物品出售失败
	 **/
	public static final int PROCESS_GOODS_SALE_FAILURE = 20000;
	/**
	 * 过期道具
	 **/
	public static final int PROCESS_GOODS_OVERDUE_ERROR = 20000;
	/**
	 * 道具已经绑定
	 **/
	public static final int PROCESS_GOODS_BINDING_ERROR = 20000;
	/**
	 * 道具不能存入仓库
	 **/
	public static final int PROCESS_GOODS_STORAGE_ERROR = 20000;
	/**
	 * 道具不能销毁
	 **/
	public static final int PROCESS_GOODS_DESTROY_ERROR = 20000;

	/**
	 * 道具类型错误
	 **/
	public static final int PROCESS_GOODS_TYPE_ERROR = 20000;

	/**
	 * 装备不能出售
	 **/
	public static final int PROCESS_GOODS_SELL_ERROR = 20000;

	/**
	 * 道具职业要求匹配失败
	 **/
	public static final int PROCESS_GOODS_PROFESSION_ERROR = 20000;

	/**
	 * 装备穿戴位置配置错误
	 **/
	public static final int PROCESS_GOODS_WEARPOSITION_ERROR = 20000;


	/** SessionId错误 **/
	public static final int SESSIONID_ERROR = 30001;
	/** SessionId为空 **/
	public static final int SESSIONID_EMPTY = 30002;
	/** SessionId过期 **/
	public static final int SESSIONID_EXPIRED = 30003;
	/** SessionId解析失败 **/
	public static final int SESSIONID_PARSE_FAIL = 30004;
	
	/** 当期用户没有权限进行此操作 **/
	public static final int PERMISSION_NO = 30050;
	/** 权限解析失败 **/
	public static final int PERMISSION_PARSE_FAIL = 30051;

}
