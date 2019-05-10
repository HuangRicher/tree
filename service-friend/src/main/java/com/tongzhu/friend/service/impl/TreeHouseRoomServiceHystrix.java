package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.TreeHouseRoom;
import com.tongzhu.friend.service.TreeHouseRoomService;
import com.tongzhu.friend.service.vo.FriendDetailVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeHouseRoomServiceHystrix implements TreeHouseRoomService {
    @Override
    public int countWorkersByUserId(String userId) {
        //return 0;
        throw new RuntimeException("error");
    }

    @Override
    public int countRoomsByUserId(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public TreeHouseRoom checkIsWorkerByWorkerId(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public void updateIsGameFriend(String userId, String friendId, Boolean isGameFriend) {
        throw new RuntimeException("error");
    }

    @Override
    public List<FriendDetailVO> findWorkersList(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public List<FriendDetailVO> findFriendWorkersListWithSelf(String userId, String friendId) {
        throw new RuntimeException("error");
    }
}
