package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.service.FriendService;
import org.springframework.stereotype.Component;

@Component
public class FriendServiceHystrix implements FriendService {
    @Override
    public Integer countFriendToBeConfirmByFriendId(String userId) {
        return null;
    }
}
