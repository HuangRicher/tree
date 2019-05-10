package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.FishInfo;

import java.util.List;

public interface FishInfoService {

	List<FishInfo> getFishInfoList();

	Integer getSumSeniorProbability();

	Integer getSumRrareProbability();

	Integer getSumCommonProbability();

	FishInfo getFishInfoById(Integer id);
}
