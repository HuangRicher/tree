package com.tongzhu.fishing.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.friend}")
public interface TaskService {

    @RequestMapping(value = "/taskResource/taskProgress/{type}/{number}/{userId}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void taskProgress(@PathVariable("type") Integer type, @PathVariable("number") Integer number, @PathVariable("userId") String userId);

}
