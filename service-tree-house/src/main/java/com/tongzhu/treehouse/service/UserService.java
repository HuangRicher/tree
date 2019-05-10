package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.User;
import com.tongzhu.treehouse.domain.UserMessage;
import com.tongzhu.treehouse.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserServiceHystrix.class)
public interface UserService {


    @RequestMapping(value = "/userResource/findByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User findByUserId(@PathVariable("userId") String userId);


    @RequestMapping(value = "/userResource/selectUserForBuyByRand",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<User> selectUserForBuyByRand(@RequestParam("minSellingPrice") Integer minSellingPrice,
                                      @RequestParam("maxSellingPrice")Integer maxSellingPrice,
                                      @RequestParam("userId")String userId,
                                      @RequestParam("maxExchangeCount")Integer maxExchangeCount,
                                      @RequestParam("count")Integer count);

    @RequestMapping(value="/userResource/selectFriendsForBuyByRand",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<User> selectFriendsForBuyByRand(@RequestParam("userId")String userId,
                                         @RequestParam("status")Integer status,
                                         @RequestParam("exchangeCount")Integer exchangeCount,
                                         @RequestParam("sellingPrice")Integer sellingPrice,
                                         @RequestParam("count")Integer count);

    @RequestMapping(value = "/userResource/reduceSellingPrice/{userId}/{workerId}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User reduceSellingPrice(@PathVariable("userId") String userId, @PathVariable("workerId") String workerId);


    @RequestMapping(value = "/userResource/updateUserSellingPrice/{userId}/{sellingPrice}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateUserSellingPrice(@PathVariable("userId") String userId, @PathVariable("sellingPrice") Integer sellingPrice);


    @RequestMapping(value = "/userResource/countAllUser",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countAllUser();

    @RequestMapping(value = "/userResource/addUserMessage",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int addUserMessage(@RequestBody UserMessage userMessage);

}
