package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.UserGoods;
import com.tongzhu.friend.service.impl.UserGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback =UserGoodsServiceHystrix.class)
public interface UserGoodsService {




    @RequestMapping(value = "/userGoodsResource/addUserGoodsSingle/{userId}/{goodsId}/{amount}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addUserGoodsSingle(@PathVariable("userId")String userId, @PathVariable("goodsId") String goodsId, @PathVariable("amount")Integer amount);


    @RequestMapping(value = "/userGoodsResource/subUserGoods/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> subUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);

}
