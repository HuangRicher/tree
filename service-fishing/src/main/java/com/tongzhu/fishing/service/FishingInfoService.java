package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.FishInfo;
import com.tongzhu.fishing.model.FishingInfo;

import java.util.List;

public interface FishingInfoService {
	/**
	 * 增加记录
	 * @param fishingInfo
	 * @return
	 */
	int addFishingInfo(FishingInfo fishingInfo);

	/**
	 * 获取用户钓鱼数据
	 * @param userId
	 * @return
	 */
	FishingInfo getFishingInfo(String userId);

	/**
	 * 更新记录
	 * @param fishingInfo
	 * @return
	 */
    int renewalFishingInfo(FishingInfo fishingInfo);



	List<FishInfo> lottery(String userId, Integer multiple, String consumables, Integer amount) throws Exception;
}
