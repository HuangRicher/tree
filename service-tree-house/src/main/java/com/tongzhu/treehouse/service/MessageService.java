package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserMessage;
import com.tongzhu.treehouse.service.impl.MessageServiceHystrix;
import com.tongzhu.treehouse.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = MessageServiceHystrix.class)
public interface MessageService {

    @PostMapping(value = "/messageResource/addUserMessage",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int addUserMessage(@RequestBody UserMessage userMessage);


    @PostMapping(value = "/messageResource/inviteFriendToTreeHouse/{userId}/{receiverIds}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void inviteFriendToTreeHouse(@PathVariable("userId") String userId, @PathVariable("receiverIds") String receiverIds);
}
