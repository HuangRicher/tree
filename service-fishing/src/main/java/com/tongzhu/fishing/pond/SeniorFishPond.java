package com.tongzhu.fishing.pond;


import com.tongzhu.fishing.constant.FishingConstant;
import com.tongzhu.fishing.model.FishPufferModel;
import com.tongzhu.fishing.model.FishSharkModel;
import com.tongzhu.fishing.util.SpringContextUtil;

/**
 * 高级鱼塘
 * @author 海乐乐
 * @date 2018年8月10日
 */
public class SeniorFishPond extends BaseFishPond{

	/**
	 * 鱼塘初始化
	 */
	public SeniorFishPond(){
		super.chance = FishingConstant.FISHING_GROUND_SENIOR_CHANCE;
		super.quality = FishingConstant.FISHING_GROUND_QUALITY_SENIOR;
		//高级鱼塘新增鲨鱼、河豚
		super.getList().add(SpringContextUtil.getBean(FishPufferModel.class));
		super.getList().add(SpringContextUtil.getBean(FishSharkModel.class));
	}

}
