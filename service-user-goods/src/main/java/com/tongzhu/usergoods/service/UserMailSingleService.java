package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.domain.UserMailSingle;
import com.tongzhu.usergoods.service.impl.UserMailSingleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserMailSingleServiceHystrix.class)
public interface UserMailSingleService {

    @RequestMapping(value = "/userMailSingleResource/addUserMailSingle",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int addUserMailSingle(@RequestBody UserMailSingle userMailSingle);
}
