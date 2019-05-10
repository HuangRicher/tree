package com.tongzhu.usergoods.microservice;


import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.model.PropInfo;
import com.tongzhu.usergoods.model.UserGoods;
import com.tongzhu.usergoods.service.PropInfoService;
import com.tongzhu.usergoods.service.UserGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/propResource")
public class PropResource {


    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private UserGoodsService userGoodsService;


    @PostMapping("/getPropInfo/{goodsId}")
    public PropInfo getPropInfo(@PathVariable("goodsId") String goodsId){
        return propInfoService.getPropInfo(goodsId);
    }

    /**
     * 购买并使用道具
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/buyAndUseProp/{userId}/{goodsId}")
    public UserGoods buyAndUseProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId){
        return userGoodsService.buyProps(userId,goodsId,1,0);
    }

    /**
     * 购买道具
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/buyProp/{userId}/{goodsId}")
    public UserGoods buyProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId){
        return userGoodsService.buyProps(userId,goodsId,1, PacksackConstant.GOODS_USE_NO);
    }

    /**
     * 购买道具(数量可变）
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/buyProp/{userId}/{goodsId}/{amount}")
    public UserGoods buyProp(@PathVariable("userId") String userId,
                             @PathVariable("goodsId") String goodsId,
                             @PathVariable("amount") Integer amount){
        return userGoodsService.buyProps(userId,goodsId,amount, PacksackConstant.GOODS_USE_NO);
    }


    /**
     * 使用道具
     * @param userId
     * @param goodsId
     * @param multiple
     * @return
     */
    @PostMapping("/onlyUseProp/{userId}/{goodsId}/{multiple}")
    public UserGoods onlyUseProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId,@PathVariable("multiple") Integer multiple){
        return userGoodsService.subUserGoodsSingle(userId,goodsId,multiple,true);
    }
}
