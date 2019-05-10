package com.tongzhu.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.service.impl.WelfareServiceHystrix;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = WelfareServiceHystrix.class)
public interface WelfareService {

    @RequestMapping(value = "/welfareResource/addDaysByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addDaysByUserId(@PathVariable("userId") String userId);
    
    @RequestMapping(value = "/welfareResource/getWelfareStatusByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int getWelfareStatusByUserId(@PathVariable("userId") String userId);
}
