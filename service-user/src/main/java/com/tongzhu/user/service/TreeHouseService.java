package com.tongzhu.user.service;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.domain.TreeHouse;
import com.tongzhu.user.service.impl.TreeHouseServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseServiceHystrix.class)
public interface TreeHouseService {

    @PostMapping(value = "/treeHouseResource/add",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void add(@RequestBody TreeHouse treeHouse);


    @PostMapping(value = "/treeHouseResource/findByUserId/{otherUserId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouse findByUserId(@PathVariable("otherUserId") String otherUserId);


}
