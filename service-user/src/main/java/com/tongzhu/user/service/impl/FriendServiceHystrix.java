package com.tongzhu.user.service.impl;

import com.tongzhu.user.domain.Friend;
import com.tongzhu.user.domain.FriendDO;
import com.tongzhu.user.service.FriendService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendServiceHystrix implements FriendService {
    @Override
    public Friend checkIsMyFriend(String userId, String otherUserId) {
        throw new RuntimeException("error");
    }

    @Override
    public void updateFriendFightStatus(String userId, String friendId, Integer status) {

    }

    @Override
    public List<FriendDO> findFriendList(String userId) {
        return null;
    }

    @Override
    public void addFriend(Friend friend) {

    }
}
