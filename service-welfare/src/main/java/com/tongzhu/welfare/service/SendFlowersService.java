package com.tongzhu.welfare.service;

import com.tongzhu.welfare.vo.ReceiveGoldVo;

public interface SendFlowersService {

	/**
	 * 给指定的人送花
	 * @param userId
	 * @param receiveId
	 * @param num
	 * @param b 
	 * @return 
	 */
	int sendFlowersById(String userId, String receiveId, int num, boolean b);

	/**
	 * 快速购买鲜花
	 * @param userId
	 * @param goodsId
	 * @param num
	 * @throws Exception
	 */
	ReceiveGoldVo flowersPay(String userId, int goodsId, int num) throws Exception;

}
