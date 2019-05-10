package com.tongzhu.fishing.service;

import com.tongzhu.fishing.config.FeignConfig;
import com.tongzhu.fishing.domain.TreeHouse;
import com.tongzhu.fishing.service.impl.TreeHouseServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/9/30 0030.
 */
@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseServiceHystrix.class)
public interface TreeHouseService {

    @RequestMapping(value = "/treeHouseResource/findByUserId/{userId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouse findByUserId(@PathVariable("userId") String userId);

}
