package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.servicemessage.redis.RedisService;
import com.tongzhu.servicemessage.service.OperationService;
import com.tongzhu.servicemessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;



    @Override
    public void updateForUserOnlineState(String userId, Integer status) {
        userService.updateForUserOnlineState(userId,status);
    }

    @Override
    public void saveUsersComeInTreeHouse(String treeHouseUserId, String userId) throws CheckException {
        List<String> users=(List<String>)redisService.get(treeHouseUserId);
        if(users.size()>=3)
            throw new CheckException("对方树屋人满为患，无法进入！");
        users.add(userId);
        redisService.set(treeHouseUserId,users);
    }

    @Override
    public void removeUsersOutTreeHouse(String treeHouseUserId, String userId) {
        List<String> users=(List<String>)redisService.get(treeHouseUserId);
        Iterator<String> it=users.iterator();
        while (it.hasNext()){
            String uid=it.next();
            if(userId.equals(uid)){
                it.remove();
            }
        }
        redisService.set(treeHouseUserId,users);
    }

}
