package com.tongzhu.welfare.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.UserMailSingle;
import com.tongzhu.welfare.domain.UserMessage;
import com.tongzhu.welfare.service.impl.UserGoodsServiceHystrix;
import com.tongzhu.welfare.service.impl.UserMailSingleServiceHystrix;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserMailSingleServiceHystrix.class)
public interface UserMailSingleService {

    @RequestMapping(value = "/userMailSingleResource/addUserMailSingle",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int addUserMailSingle(@RequestBody UserMailSingle userMailSingle);
}
