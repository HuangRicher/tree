package com.tongzhu.usergoods.service;


import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.service.impl.BuildingServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = BuildingServiceHystrix.class)
public interface BuildingService {

    /**
     * 通知发生变动
     * @param content   为json的string,目前需要传userId type
     */
    @PostMapping(value = "/buildingResource/sendMsg/{content}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMQMsg(@PathVariable("content") String content);
}
