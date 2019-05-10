package com.tongzhu.user.service.impl;

import com.tongzhu.user.domain.TreeHouseRoom;
import com.tongzhu.user.service.TreeHouseRoomService;
import org.springframework.stereotype.Component;

@Component
public class TreeHouseRoomServiceHystrix implements TreeHouseRoomService {
    @Override
    public TreeHouseRoom findWorkersByUserIdAndWorkerId(String userId, String workerId) {
       throw new RuntimeException("error");
    }

    @Override
    public void updateReducePriceDate(String userId, String workerId) {
        throw new RuntimeException("error");
    }

    @Override
    public int countWorkersByUserId(String userId) {
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
}
