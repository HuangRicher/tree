package com.tongzhu.welfare.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.service.impl.MessageServiceHystrix;

@FeignClient(value = "${feign-service.message}",configuration = FeignConfig.class,fallback = MessageServiceHystrix.class)
public interface MessageService {
    
    @RequestMapping(value = "/messageResource/sendMessageToSomeBody/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMessageToSomeBody(@PathVariable("userId") String userId,@RequestParam("message") String message);
    
    @RequestMapping(value = "/messageResource/sendBroadcastToAllBody/{message}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendBroadcastToAllBody(@RequestParam("message") String message);
    
    @RequestMapping(value = "/messageResource/joinMarry/{userId}/{marryId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void joinMarry(@PathVariable("userId") String userId,@RequestParam("marryId") String marryId);
    
    @RequestMapping(value = "/messageResource/sendWeddingCruiseToSomeBody/{weddingType}/{boyName}/{girlName}",method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendWeddingCruiseToSomeBody(@PathVariable("weddingType") Integer weddingType ,@PathVariable("boyName") String boyName,@PathVariable("girlName") String girlName);
    
}
