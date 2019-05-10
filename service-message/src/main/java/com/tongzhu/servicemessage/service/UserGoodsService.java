package com.tongzhu.servicemessage.service;


import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.domain.UserGoods;
import com.tongzhu.servicemessage.service.impl.UserGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback = UserGoodsServiceHystrix.class)
public interface UserGoodsService {


    @PostMapping(value = "/userGoodsResource/addUserGoodsSingle/{userId}/{goodsId}/{amount}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addUserGoodsSingle(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId,
                                 @PathVariable("amount") Integer amount);


    @PostMapping(value = "/userGoodsResource/subUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> subUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);

}
