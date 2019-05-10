package com.tongzhu.treehouse.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "${feign-service.message}")
public interface ChatMessageService {

    @PostMapping(value = "/messageResource/sendMessageToSomeBody/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMessageToSomeBody(@PathVariable("userId") String userId, @RequestParam("message") String message);

    @PostMapping(value = "/messageResource/joinMarry/{userId}/{marryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void joinMarry(@PathVariable("userId") String userId, @PathVariable("marryId") String marryId);

    @PostMapping("/messageResource/sendMessageToAll")
    @ResponseBody
    void sendMessageToAll(@RequestParam("message") String message);

    @PostMapping(value = "/messageResource/appointWork/{userId}/{message}/{workerUserId}/{workType}/{grade}/{sex}/{exp}/{money}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void appointWork(@PathVariable("userId") String userId,@PathVariable("workerUserId") String workerUserId,
                            @PathVariable("workType") Integer workType,@PathVariable("grade") Integer grade,
                            @PathVariable("sex") Integer sex,@PathVariable("exp") Integer exp,
                            @PathVariable("message") String message,@PathVariable("money") Integer money);

    @PostMapping(value = "/messageResource/sendMessageToAllInTreeHouse/{userId}/{message}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMessageToAllInTreeHouse(@PathVariable("userId") String userId,@PathVariable("message") String message);

    @PostMapping(value = "/messageResource/queryOnLineUser",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String,Integer> queryOnLineUser();
}
