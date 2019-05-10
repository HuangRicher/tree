package com.tongzhu.servicemessage.service;

import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.service.impl.FriendServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = FriendServiceHystrix.class)
public interface FriendService {

    @PostMapping(value = "/friendResource/countFriendToBeConfirmByFriendId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Integer countFriendToBeConfirmByFriendId(@PathVariable("userId") String userId);
}
