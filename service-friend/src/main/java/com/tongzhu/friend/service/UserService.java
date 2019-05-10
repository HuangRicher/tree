package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.GameNPC;
import com.tongzhu.friend.domain.User;
import com.tongzhu.friend.domain.UserDetail;
import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserServiceHystrix.class)
public interface UserService {


    @PostMapping(value = "/userResource/getUserDetail/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserDetail getUserDetail(@PathVariable("userId")String userId);



    @PostMapping(value = "/userResource/findByUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User findByUserId(@PathVariable("userId")String userId);

    @PostMapping(value = "/roleResource/updateRoleExp/{userId}/{exp}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String,Object> updateRoleExp(@PathVariable("userId") String userId, @PathVariable("exp") Integer exp);

    @PostMapping(value = "/userResource/getUserByUserIdOrName/{searchUser}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserDetail> getUserByUserIdOrName(@PathVariable("searchUser")String searchUser);

    @PostMapping(value = "/userResource/selectRecommendFriends/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<User> selectRecommendFriends(@PathVariable("userId")String userId);

    @PostMapping(value = "/userResource/recommendFriendByOppositeSex/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User recommendFriendByOppositeSex(@PathVariable("userId")String userId);

    @PostMapping(value = "/roleResource/getUserRoleByUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserRole getUserRoleByUserId(@PathVariable("userId") String userId);

}
