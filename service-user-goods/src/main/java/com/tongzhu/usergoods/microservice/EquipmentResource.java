package com.tongzhu.usergoods.microservice;

import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.model.EquipmentInfo;
import com.tongzhu.usergoods.service.EquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipmentResource")
public class EquipmentResource {

    @Autowired
    private EquipmentInfoService equipmentInfoService;


    @PostMapping("/getEquipmentInfo/{goodsId}")
    public EquipmentInfo getEquipmentInfo(@PathVariable("goodsId") String goodsId){
        return equipmentInfoService.getEquipmentInfo(goodsId);
    }

    @PostMapping("/getEquipmentInfoByIdAndOriginal/{goodsId}")
    public EquipmentInfo getEquipmentInfoByIdAndOriginal(@PathVariable("goodsId") String goodsId){
        return equipmentInfoService.getEquipmentInfoByIdAndOriginal(goodsId,PacksackConstant.GOODS_ORIGINAL_YES);
    }


}
