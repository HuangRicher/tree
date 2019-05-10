package com.tongzhu.fishing.service;

import com.tongzhu.fishing.config.FeignConfig;
import com.tongzhu.fishing.domain.Friend;
import com.tongzhu.fishing.service.impl.FriendServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = FriendServiceHystrix.class)
public interface FriendService {

    @RequestMapping(value = "/friendResource/checkIsMyFriend/{userId}/{otherUserId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Friend checkIsMyFriend(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId);
    
    @RequestMapping(value = "/friendResource/updateIntimacy/{userId}/{friendId}/{num}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateIntimacy(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId, @PathVariable("num") int num);

    @RequestMapping(value = "/friendResource/getIntimacy/{userId}/{friendId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Integer getIntimacy(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId);
}
