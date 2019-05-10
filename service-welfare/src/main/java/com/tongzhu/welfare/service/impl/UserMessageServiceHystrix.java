package com.tongzhu.welfare.service.impl;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.UserMessage;
import com.tongzhu.welfare.service.UserMessageService;

@Component
public class UserMessageServiceHystrix implements UserMessageService {

	@Override
	public int addUserMessage(UserMessage userMessage) {
		throw new RuntimeException("error");
	}
}
