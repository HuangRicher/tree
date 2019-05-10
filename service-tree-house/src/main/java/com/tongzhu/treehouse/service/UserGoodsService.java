package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.service.impl.UserGoodsServiceHystrix;
import com.tongzhu.treehouse.service.vo.PropInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback = UserGoodsServiceHystrix.class)
public interface UserGoodsService {


    @PostMapping(value = "/userGoodsResource/subUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> subUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);

    /**
     * 减少用户单个物品
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/userGoodsResource/subUserGoodsSingle/{userId}/{goodsId}/{multiple}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods subUserGoodsSingle(@PathVariable("userId") String userId,
                                        @PathVariable("goodsId") String goodsId,
                                        @PathVariable("multiple") int multiple);

    @PostMapping(value = "/userGoodsResource/addUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> addUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);


    @PostMapping(value = "/userGoodsResource/getByUserIdAndGoodsId/{userId}/{goodsId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods getGoodsCount(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId);

    /**
     * 查找背包中的花种子
     * @param userId
     * @return
     */
    @PostMapping(value = "/userGoodsResource/querySeedsPropList/{userId}/",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<PropInfoVO> querySeedsPropList(@PathVariable("userId") String userId);

    /**
     * 购买道具
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping(value = "/propResource/buyProp/{userId}/{goodsId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods buyProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId);

}
