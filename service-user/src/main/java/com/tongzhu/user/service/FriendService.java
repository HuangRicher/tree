package com.tongzhu.user.service;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.domain.Friend;
import com.tongzhu.user.domain.FriendDO;
import com.tongzhu.user.service.impl.FriendServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = FriendServiceHystrix.class)
public interface FriendService {

    @PostMapping(value = "/friendResource/checkIsMyFriend/{userId}/{otherUserId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Friend checkIsMyFriend(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId);


    @PostMapping(value = "/friendResource/updateFriendFightStatus/{userId}/{friendId}/{status}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateFriendFightStatus(@PathVariable("userId") String userId,
                                        @PathVariable("friendId") String friendId,
                                        @PathVariable("status") Integer status);


    @PostMapping(value = "/friendResource/findFriendList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FriendDO> findFriendList(@PathVariable("userId") String userId);

    @PostMapping(value = "/friendResource/addFriend/",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addFriend(@RequestBody Friend friend);
}
