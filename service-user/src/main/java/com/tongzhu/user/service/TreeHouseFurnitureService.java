package com.tongzhu.user.service;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.service.impl.TreeHouseFurnitureServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseFurnitureServiceHystrix.class)
public interface TreeHouseFurnitureService {

    @RequestMapping(value = "/treeHouseFurnitureResource/addMultiFurniture/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addMultiFurniture(@PathVariable("userId") String userId, @RequestBody Map<String,Integer> furnitureMap);
}
