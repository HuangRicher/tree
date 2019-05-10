package com.tongzhu.user.service;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.domain.TreeHouseRoom;
import com.tongzhu.user.service.impl.TreeHouseRoomServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseRoomServiceHystrix.class)
public interface TreeHouseRoomService {


    @RequestMapping(value = "/treeHouseRoomResource/findWorkersByUserIdAndWorkerId/{userId}/{workerId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouseRoom findWorkersByUserIdAndWorkerId(@PathVariable("userId") String userId, @PathVariable("workerId") String workerId);


    @RequestMapping(value = "/treeHouseRoomResource/updateReducePriceDate/{userId}/{workerId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateReducePriceDate(@RequestParam("userId") String userId, @RequestParam("workerId") String workerId);



    @RequestMapping(value = "/treeHouseRoomResource/countWorkersByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countWorkersByUserId(@PathVariable("userId") String userId);



    @RequestMapping(value = "/treeHouseRoomResource/countRoomsByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countRoomsByUserId(@PathVariable("userId") String userId);



    @RequestMapping(value = "/treeHouseRoomResource/checkIsWorkerByWorkerId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouseRoom checkIsWorkerByWorkerId(@PathVariable("userId") String userId);



}
