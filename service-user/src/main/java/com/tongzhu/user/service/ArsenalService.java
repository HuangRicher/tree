package com.tongzhu.user.service;

import com.tongzhu.user.domain.ArsenalInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}")
public interface ArsenalService {


    @RequestMapping(value = "/arsenalResource/getArsenalInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ArsenalInfo getArsenalInfo(@PathVariable("goodsId") String goodsId);

    @RequestMapping(value = "/arsenalResource/getArsenalInfoByIdAndOriginal/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ArsenalInfo getArsenalInfoByIdAndOriginal(@PathVariable("goodsId") String goodsId);

    @RequestMapping(value = "/arsenalResource/addGoodsWeapon/{userId}/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addGoodsWeapon(@PathVariable("userId") String userId,@PathVariable("goodsId") String goodsId);
}
