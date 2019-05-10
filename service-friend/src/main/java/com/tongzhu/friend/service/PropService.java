package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.service.impl.PropServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback =PropServiceHystrix.class)
public interface PropService {


    @RequestMapping(value = "/propResource/getPropInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PropInfo getPropInfo(@PathVariable("goodsId")String goodsId);


}
