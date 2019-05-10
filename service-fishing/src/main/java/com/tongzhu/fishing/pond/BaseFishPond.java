package com.tongzhu.fishing.pond;


import com.tongzhu.fishing.constant.FishingConstant;
import com.tongzhu.fishing.model.*;
import com.tongzhu.fishing.util.SpringContextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础鱼塘
 * @author 海乐乐
 * @date 2018年8月9日
 */
public class BaseFishPond {
		
	protected List<FishBaseModel> list;
	protected Integer chance;//概率
	protected Integer quality;//品质 
	/**
	 * 基础鱼塘初始化
	 */
	public BaseFishPond(){
		quality = FishingConstant.FISHING_GROUND_QUALITY_ORDINARY ;
		list = new ArrayList<FishBaseModel>();		
		list.add(SpringContextUtil.getBean(FishClownModel.class));
		list.add(SpringContextUtil.getBean(FishBatsModel.class));
		list.add(SpringContextUtil.getBean(FishButterflyModel.class));
		list.add(SpringContextUtil.getBean(FishGuppyModel.class));
		list.add(SpringContextUtil.getBean(FishLanternModel.class));
		list.add(SpringContextUtil.getBean(FishTunasModel.class));	
		
		list.add(SpringContextUtil.getBean(FishTurtleModel.class));
		list.add(SpringContextUtil.getBean(FishShrimpModel.class));
		list.add(SpringContextUtil.getBean(FishDolphinModel.class));
		list.add(SpringContextUtil.getBean(FishCrabModel.class));
		
	}
	
	public List<FishBaseModel> getList() {
		return list;
	}

	public Integer getChance() {
		return chance;
	}

	public Integer getQuality() {
		return quality;
	}

	
}
