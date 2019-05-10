package com.tongzhu.welfare.service;

import java.util.List;
import java.util.Map;

import com.tongzhu.welfare.domain.PropInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.GeneralityGoods;
import com.tongzhu.welfare.domain.UserAdornEquip;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.service.impl.UserGoodsServiceHystrix;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback = UserGoodsServiceHystrix.class)
public interface UserGoodsService {


    @RequestMapping(value = "/userGoodsResource/subUserGoods/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> subUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);

    @RequestMapping(value = "/userGoodsResource/deleteByUserIdAndGoodsId/{userId}/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteByUserIdAndGoodsId(@PathVariable("userId") String userId,@PathVariable("goodsId") Integer goodsId);

    @RequestMapping(value = "/propResource/getPropInfo/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PropInfo getPropInfo(@PathVariable("goodsId") Integer goodsId);

    @RequestMapping(value = "/userGoodsResource/addUserGoods/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> addUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);


    @RequestMapping(value = "/userGoodsResource/getByUserIdAndGoodsId/{userId}/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods getGoodsCount(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId);

	/**
	 * 删除婚戒
	 * @param userId
	 * @param goodsId
	 * @return
	 */
    @RequestMapping(value = "/userGoodsResource/deleteWeddingRingByUserIdAndGoodsId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods deleteWeddingRingByUserIdAndGoodsId(@PathVariable("userId") String userId);

    /**
     * 查询婚戒
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userGoodsResource/queryGoodsWeddingRing/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<GeneralityGoods> queryGoodsWeddingRing(@PathVariable("userId") String userId);
    
    /**
     *  增加装备或者武器
     * @param userId
     * @param type 2 装备 3 武器
     * @param id
     * @return
     */
    @RequestMapping(value = "/userGoodsResource/addEquipAndWeapon/{userId}/{type}/{id}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addEquipAndWeapon(@PathVariable("userId") String userId,@PathVariable("type") Integer type,@PathVariable("id") Integer id);
    
    /**
     * 穿戴装备
     * @param userId
     * @param goodsId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userGoodsResource/wearEquipment/{userId}/{goodsId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, Object> wearEquipment(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId);

    @RequestMapping(value = "/userGoodsResource/getUserFightingCapacity/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserAdornEquip getUserFightingCapacity(@PathVariable("userId") String userId);

    /**
     * @param userId
     * @param vitality 生命属性加成
     * @param attack 物理攻击属性加成
     * @param spellAttacks 法术攻击属性加成
     * @param pdef 物理防御属性加成
     * @param magdef 法术防御属性加成
     * @param crit 暴击属性加成
     * @param dodge 闪避属性加成
     * @param hitRate 命中属性加成
     * @param defenseCrit 抗暴击属性加成
     * @return
     */
    @PostMapping("/userAdornResource/addUserAdornEquipAttribute/{userId}/{vitality}/{attack}/{spellAttacks}/{pdef}/{magdef}/{crit}/{dodge}/{hitRate}/{defenseCrit}")
    UserAdornEquip addUserAdornEquipAttribute(@PathVariable("userId") String userId, @PathVariable("vitality") Double vitality,
                                                     @PathVariable("attack") Double attack,
                                                     @PathVariable("spellAttacks") Double spellAttacks,
                                                     @PathVariable("pdef") Double pdef,
                                                     @PathVariable("magdef") Double magdef,
                                                     @PathVariable("crit") Double crit,
                                                     @PathVariable("dodge") Double dodge,
                                                     @PathVariable("hitRate") Double hitRate,
                                                     @PathVariable("defenseCrit") Double defenseCrit);
    /**
     * 查询改用户存入这些道具后背包还剩多少空间
     *
     * @param userId
     * @param goodsIdList 存入的道具
     * @return
     */
    @PostMapping(value = "/userGoodsResource/queryBackpackSpace/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int queryBackpackSpace(@PathVariable("userId")String userId, @RequestBody  List<String> goodsIdList);


    /**
     * 获取用户戒指id
     *
     * @return
     */
    @PostMapping(value = "/userGoodsResource/getUserWeddingRingId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Integer getUserWeddingRingId(@PathVariable("userId")String userId);

    /**
     *  获取用户戒指id 升级需要的幸福值
     *
     * @return
     */
    @PostMapping(value = "/userGoodsResource/getUserWeddingRingHappinessNum/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map getUserWeddingRingHappinessNum(@PathVariable("userId")String userId);




}
