package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.domain.LoveTreeInfo;
import com.tongzhu.usergoods.service.impl.MarryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = MarryServiceHystrix.class)
public interface MarryService {


    /**
     * 获取情缘属性
     * @param userId
     * @return
     */
    @PostMapping(value = "/marryResource/getLoveTreeInfoObject/{loveTreeId}/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    LoveTreeInfo getLoveTreeInfoObject(@PathVariable("loveTreeId") String loveTreeId,@PathVariable("userId") String userId);


    /**
     * 
     * 给指定用户减幸福值（升级戒指）
     * @param userId
     * @param num
     * @param ringId	当该ID为null时，默认只进行减幸福值操作
     * @return
     */
    @PostMapping(value = "/marryResource/minusHappinessByUserId/{userId}/{num}/{ringId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int minusHappinessByUserId(@PathVariable("userId") String userId,@PathVariable("num") Integer num ,@PathVariable("ringId") String ringId);




}
