package com.tongzhu.welfare.service.impl;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.service.MessageService;

@Component
public class MessageServiceHystrix implements MessageService {

	@Override
	public void sendMessageToSomeBody(String userId, String message) {
		throw new RuntimeException("error");
	}

	@Override
	public void joinMarry(String userId, String marryId) {
		throw new RuntimeException("error");
	}

	@Override
	public void sendBroadcastToAllBody(String message) {
		throw new RuntimeException("error");
	}

	@Override
	public void sendWeddingCruiseToSomeBody(Integer weddingType, String boyName, String girlName) {
		throw new RuntimeException("error");
	}
}
