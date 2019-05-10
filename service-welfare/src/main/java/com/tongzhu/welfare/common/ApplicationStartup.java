package com.tongzhu.welfare.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tongzhu.welfare.service.BuildingService;

@Component
public class ApplicationStartup implements ApplicationRunner {
 
	@Autowired
	private BuildingService service;
 
	@Override
	@Async
	public void run(ApplicationArguments arg) throws Exception {
		// 加载字典表缓存
		service.cacheBuildingSettingInfo();
	}
}