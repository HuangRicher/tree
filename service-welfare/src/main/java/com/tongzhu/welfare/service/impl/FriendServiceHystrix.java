package com.tongzhu.welfare.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.Friend;
import com.tongzhu.welfare.model.vo.FriendDO;
import com.tongzhu.welfare.service.FriendService;

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

	@Override
	public void taskBranchFinish(Integer taskId, String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public List<FriendDO> findFriendList(String userId) {
		throw new RuntimeException("error");
	}
}
