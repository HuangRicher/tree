package com.tongzhu.fishing.pond;


import com.tongzhu.fishing.constant.FishingConstant;
import com.tongzhu.fishing.model.FishMermaidModel;
import com.tongzhu.fishing.model.FishOctopusModel;
import com.tongzhu.fishing.util.SpringContextUtil;

/**
 * 稀有鱼塘
 * @author 海乐乐
 * @date 2018年8月10日
 */
public class RareFishPond extends SeniorFishPond{
	

	public RareFishPond(){
		super.chance = FishingConstant.FISHING_GROUND_RARE_CHANCE;
		super.quality = FishingConstant.FISHING_GROUND_QUALITY_RARE;
		//稀有鱼塘新增美人鱼、大章鱼
		super.getList().add(SpringContextUtil.getBean(FishOctopusModel.class));
		super.getList().add(SpringContextUtil.getBean(FishMermaidModel.class));
	}
	

}
