package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.user.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/update")
    public BaseReturn update(String token,String key,String value){
        redisService.set(key,value);
        return new BaseReturn("success",redisService.get(key));
    }


    @PostMapping("/remove")
    public BaseReturn remove(String token,String key){
        redisService.remove(key);
        return new BaseReturn("success");
    }
}
