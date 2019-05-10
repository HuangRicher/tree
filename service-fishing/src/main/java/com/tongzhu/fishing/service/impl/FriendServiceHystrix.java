package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.domain.Friend;
import com.tongzhu.fishing.service.FriendService;
import org.springframework.stereotype.Component;

@Component
public class FriendServiceHystrix implements FriendService {
    @Override
    public Friend checkIsMyFriend(String userId, String otherUserId) {
        throw new RuntimeException("error");
    }

	@Override
	public void updateIntimacy(String userId, String friendId, int num) {
		throw new RuntimeException("error");
	}

	@Override
	public Integer getIntimacy(String userId, String friendId) {
		throw new RuntimeException("error");
	}
}
