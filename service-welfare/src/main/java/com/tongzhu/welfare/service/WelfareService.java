package com.tongzhu.welfare.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import com.tongzhu.welfare.vo.WelfareVo;

public interface WelfareService {
	
	/**
	 * 根据用户ID获取七天奖励的信息列表
	 * @param userId		用户ID
	 * @param userDetail
	 * @return
	 */
	List<WelfareVo> getRewardsListByUserId(String userId,User userDetail);
	
	/**
	 * 根据用户ID领取七天奖励
	 * @param userId		用户ID
	 * @param dateNum
	 * @return
	 */
	ReceiveGoldVo receiveRewardsByUserId(String userId, int dateNum);
	
	/**
	 * 根据用户ID获取月奖励的信息列表
	 * @param userId		用户ID
	 * @param userDetail
	 * @return
	 */
	List<WelfareVo> getMonthRewardsListByUserId(String userId, User userDetail);
	
	/**
	 * 根据用户ID领取月内登录奖励
	 * @param userId		用户ID
	 * @param dateNum
	 * @return
	 */
	ReceiveGoldVo receiveMonthRewardsByUserId(String userId, int dateNum);
	
	/**
	 * 根据用户ID获取月内登录天数
	 * @param userId		用户ID
	 * @return
	 */
	int getDaysByUserId(String userId);
	
	/**
	 * 增加月内登录记录
	 * @param userId		用户ID
	 * @return
	 */
	int addDaysByUserId(String userId);
	
	/**
	 *  判断当前用户是否有可领取的福利
	 * @param userId		用户ID
	 * @return
	 */
	int getWelfareStatusByUserId(String userId);
	
	/**
	 * 获取用户离线收益		
	 * @param userId		用户ID
	 * @return
	 */
	JSONObject getOfflineByUserId(String userId);

}
