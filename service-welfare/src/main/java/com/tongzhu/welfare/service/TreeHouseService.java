package com.tongzhu.welfare.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.TreeHouse;
import com.tongzhu.welfare.service.impl.TreeHouseServiceHystrix;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseServiceHystrix.class)
public interface TreeHouseService {

    @RequestMapping(value = "/treeHouseResource/add/{treeHouseId}/{level}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void updateTreeHouseLevel( @PathVariable("treeHouseId") String treeHouseId,@PathVariable("level") int level);

    @RequestMapping(value = "/treeHouseResource/findByUserId/{otherUserId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouse findByUserId(@PathVariable("otherUserId") String otherUserId);
    
    @RequestMapping(value = "/treeHouseResource/updateTreeHouseLevel/{treeHouseUserId}/{level}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void unlockTreeHouseFlowerpot(@PathVariable("treeHouseUserId") String treeHouseUserId,@PathVariable("level") Integer level);
    
    @RequestMapping(value = "/treeHouseResource/subByUserId",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouse subByUserId(@RequestBody TreeHouse treeHouse);
}
