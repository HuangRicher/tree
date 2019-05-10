package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.domain.UserMailSingle;
import com.tongzhu.usergoods.service.UserMailSingleService;
import org.springframework.stereotype.Component;

@Component
public class UserMailSingleServiceHystrix implements UserMailSingleService {

	@Override
	public int addUserMailSingle(UserMailSingle userMailSingle) {
		throw new RuntimeException("error");
	}

}
