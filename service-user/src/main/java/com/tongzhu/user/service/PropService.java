package com.tongzhu.user.service;

import com.tongzhu.user.domain.PropInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}")
public interface PropService {


    @RequestMapping(value = "/propResource/getPropInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PropInfo getPropInfo(@PathVariable("goodsId") String goodsId);


}
