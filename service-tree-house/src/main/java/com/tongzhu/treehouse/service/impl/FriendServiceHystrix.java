package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.FriendDO;
import com.tongzhu.treehouse.domain.UserFriend;
import com.tongzhu.treehouse.service.FriendService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendServiceHystrix implements FriendService {
    @Override
    public UserFriend checkIsMyFriend(String userId, String otherUserId) {
        throw new RuntimeException("error");
    }

    @Override
    public void updateIntimacy(String userId, String otherUserId, int num) {
        throw new RuntimeException("error");
    }

    @Override
    public List<FriendDO> findFriendList(String userId) {
        return null;
    }
}
