package com.tongzhu.servicemessage.service;

import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.service.impl.ChatHistoryMessageServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = ChatHistoryMessageServiceHystrix.class)
public interface ChatHistoryMessageService {

    @PostMapping(value = "/messageResource/addHistoryMessage/{id}/{userId}/{friendUserId}/{message}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addHistoryMessage(@PathVariable("id") String id,@PathVariable("userId") String userId,
                                  @PathVariable("friendUserId") String friendUserId,
                                  @PathVariable("message") String message);

    @PostMapping(value = "/messageResource/countUnReadMessage/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countUnReadMessage(@PathVariable("userId")String userId);


    @PostMapping(value = "/messageResource/readMessage/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void readMessage(@PathVariable("id")String id);

}
