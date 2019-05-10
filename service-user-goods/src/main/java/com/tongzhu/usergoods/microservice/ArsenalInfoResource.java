package com.tongzhu.usergoods.microservice;


import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.model.ArsenalInfo;
import com.tongzhu.usergoods.model.UserGoods;
import com.tongzhu.usergoods.service.ArsenalInfoService;
import com.tongzhu.usergoods.service.UserGoodsService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/arsenalResource")
public class ArsenalInfoResource {


    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private UserGoodsService userGoodsService;


    @PostMapping("/getArsenalInfo/{goodsId}")
    public ArsenalInfo getArsenalInfo(@PathVariable("goodsId") String goodsId) {
        return arsenalInfoService.getArsenalInfo(goodsId);
    }

    @PostMapping("/getArsenalInfoByIdAndOriginal/{goodsId}")
    public ArsenalInfo getArsenalInfoByIdAndOriginal(@PathVariable("goodsId") String goodsId) {
        return arsenalInfoService.getArsenalInfoByIdAndOriginal(goodsId, PacksackConstant.GOODS_ORIGINAL_YES);
    }

    @PostMapping("/addGoodsWeapon/{userId}/{goodsId}")
    public void addGoodsWeapon(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId) {
        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal(goodsId, PacksackConstant.GOODS_ORIGINAL_YES);
        if (arsenalInfo == null) {
            return;
        }
        userGoodsService.addEquipAndWeapon(userId, PacksackConstant.GOODS_TYPE_WEAPON, arsenalInfo.getId());
    }

}
