package com.tongzhu.servicemessage.service;

import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.service.impl.ChatHistoryMessageServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = ChatHistoryMessageServiceHystrix.class)
public interface TaskMessageService {



    @PostMapping(value = "/taskResource/accomplishTaskCount/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int accomplishTaskCount(@PathVariable("userId") String userId);

    @PostMapping(value = "/taskResource/accomplishTaskBranchCount/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int accomplishTaskBranchCount(@PathVariable("userId") String userId);

}
