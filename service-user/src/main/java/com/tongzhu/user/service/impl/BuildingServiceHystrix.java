package com.tongzhu.user.service.impl;

import java.util.List;

import com.tongzhu.user.domain.BuildingVo;
import org.springframework.stereotype.Component;

import com.tongzhu.user.domain.BuildingGoldVo;
import com.tongzhu.user.service.BuildingService;

@Component
public class BuildingServiceHystrix implements BuildingService {

	@Override
	public void initBuildingUser(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public List<BuildingGoldVo> getGoldStatusByUserId(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public List<BuildingVo> getBuildingByUserId(String userId) {
		return null;
	}

	@Override
	public void updateBuildingUser(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public void sendMQMsg(String content) {

	}
}
