package com.tongzhu.welfare.service.impl;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.UserMailSingle;
import com.tongzhu.welfare.service.UserMailSingleService;

@Component
public class UserMailSingleServiceHystrix implements UserMailSingleService {

	@Override
	public int addUserMailSingle(UserMailSingle userMailSingle) {
		throw new RuntimeException("error");
	}

}
