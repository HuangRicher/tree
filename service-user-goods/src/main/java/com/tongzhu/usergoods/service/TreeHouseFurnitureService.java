package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.service.impl.TreeHouseFurnitureServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseFurnitureServiceHystrix.class)
public interface TreeHouseFurnitureService {

    @RequestMapping(value = "/treeHouseFurnitureResource/addFurniture/{userId}/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addFurniture(@PathVariable("userId") String userId,@PathVariable("goodsId") String goodsId);


    @RequestMapping(value = "/treeHouseResource/addTreeHouseWorkSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addTreeHouseWorkSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

}
