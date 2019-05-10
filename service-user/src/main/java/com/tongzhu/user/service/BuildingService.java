package com.tongzhu.user.service;

import java.util.List;

import com.tongzhu.user.domain.BuildingVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.user.config.FeignConfig;
import com.tongzhu.user.domain.BuildingGoldVo;
import com.tongzhu.user.service.impl.BuildingServiceHystrix;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = BuildingServiceHystrix.class)
public interface BuildingService {

    @RequestMapping(value = "/buildingResource/initBuildingUser/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void initBuildingUser(@PathVariable("userId") String userId);

    @RequestMapping(value = "/buildingResource/getGoldStatusByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<BuildingGoldVo> getGoldStatusByUserId(@PathVariable("userId") String userId);

    @RequestMapping(value = "/buildingResource/getBuildingByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<BuildingVo> getBuildingByUserId(@PathVariable("userId") String userId);
    
    @RequestMapping(value = "/buildingResource/updateBuildingUser/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateBuildingUser(@PathVariable("userId") String userId);

    /**
     * 通知发生变动
     * @param content   为json的string,目前需要传userId type
     */
    @PostMapping(value = "/buildingResource/sendMsg/{content}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMQMsg(@PathVariable("content") String content);
}
