package com.tongzhu.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.message}")
public interface ChatMessageService {

    @PostMapping(value = "/messageResource/sendSkillRedTipToSomeBody/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendSkillRedTipToSomeBody(@PathVariable("userId") String userId);


    @PostMapping(value = "/messageResource/sendMessageToSomeBody/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void  sendMessageToSomeBody(@PathVariable("userId") String userId, @RequestParam("message") String message);


    @PostMapping(value = "/messageResource/sendMessageToAll",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMessageToAll(@RequestParam("message") String message);
}
