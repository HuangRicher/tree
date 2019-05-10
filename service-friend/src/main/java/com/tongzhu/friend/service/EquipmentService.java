package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.EquipmentInfo;
import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.service.impl.EquipmentServiceHystrix;
import com.tongzhu.friend.service.impl.UserGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback =EquipmentServiceHystrix.class)
public interface EquipmentService {


    @RequestMapping(value = "/equipmentResource/getEquipmentInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    EquipmentInfo getEquipmentInfo(@PathVariable("goodsId") String goodsId);


}
