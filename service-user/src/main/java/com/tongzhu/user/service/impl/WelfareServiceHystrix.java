package com.tongzhu.user.service.impl;

import org.springframework.stereotype.Component;

import com.tongzhu.user.service.WelfareService;

@Component
public class WelfareServiceHystrix implements WelfareService {

	@Override
	public void addDaysByUserId(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public int getWelfareStatusByUserId(String userId) {
		throw new RuntimeException("error");
	}

}
