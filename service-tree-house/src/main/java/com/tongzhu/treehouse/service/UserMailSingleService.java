package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.domain.UserMailSingle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.user}")
public interface UserMailSingleService {

    @PostMapping(value = "/userMailSingleResource/addUserMailSingle",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int addUserMailSingle(@RequestBody UserMailSingle userMailSingle);
}
