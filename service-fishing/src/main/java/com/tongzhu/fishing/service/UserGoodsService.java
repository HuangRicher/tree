package com.tongzhu.fishing.service;

import com.tongzhu.fishing.domain.UserGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(value = "${feign-service.user-goods}")
public interface UserGoodsService {

    @RequestMapping(value = "/userGoodsResource/getByUserIdAndGoodsId/{userId}/{goodsId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods getGoodsCount(@PathVariable("userId") String userId, @PathVariable("goodsId") String  goodsId);

    @RequestMapping(value = "/userGoodsResource/subUserGoodsSingle/{userId}/{goodsId}/{multiple}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods subUserGoodsSingle(@PathVariable("userId")String userId, @PathVariable("goodsId") String goodsId,@PathVariable("multiple") Integer multiple);

    /**
     * 增加单个用户单个物品
     * @param userId
     * @param goodsId
     * @param amount
     */
    @RequestMapping(value = "/userGoodsResource/addUserGoodsSingle/{userId}/{goodsId}/{amount}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addUserGoodsSingle(@PathVariable("userId")String userId, @PathVariable("goodsId") String goodsId, @PathVariable("amount")Integer amount);

    @RequestMapping(value = "/friendResource/checriend/{userId}/{goodsConch}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String,Object> getGoodsCountMap(@PathVariable("userId")String userId,@PathVariable("goodsConch") Integer goodsConch);
}
