package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.ArsenalInfo;
import com.tongzhu.friend.service.impl.ArsenalServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback =ArsenalServiceHystrix.class)
public interface ArsenalService {


    @RequestMapping(value = "/arsenalResource/getArsenalInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ArsenalInfo getArsenalInfo(@PathVariable("goodsId") String goodsId);


}
