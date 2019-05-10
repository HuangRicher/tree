package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.FriendDO;
import com.tongzhu.treehouse.domain.UserFriend;
import com.tongzhu.treehouse.service.impl.FriendServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = FriendServiceHystrix.class)
public interface FriendService {

    @RequestMapping(value = "/friendResource/checkIsMyFriend/{userId}/{otherUserId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserFriend checkIsMyFriend(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId);

    @RequestMapping(value = "/friendResource/updateIntimacy/{userId}/{otherUserId}/{num}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateIntimacy(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId,@PathVariable("num") int num);

    @PostMapping(value = "/friendResource/findFriendList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FriendDO> findFriendList(@PathVariable("userId") String userId);
}
