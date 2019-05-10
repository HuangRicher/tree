package com.tongzhu.user.service;

import com.tongzhu.user.domain.EquipmentInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}")
public interface EquipmentService {


    @RequestMapping(value = "/equipmentResource/getEquipmentInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    EquipmentInfo getEquipmentInfo(@PathVariable("goodsId") String goodsId);

    @RequestMapping(value = "/equipmentResource/getEquipmentInfoByIdAndOriginal/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    EquipmentInfo getEquipmentInfoByIdAndOriginal(@PathVariable("goodsId") String goodsId);


}
