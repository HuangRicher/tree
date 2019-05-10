package com.tongzhu.user.service;

import com.tongzhu.common.Pager;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "${feign-service.user-goods}")
public interface UserGoodsService {

    @PostMapping(value = "/userGoodsResource/initUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void initUserGoods(@PathVariable("userId") String userId);

    @PostMapping(value = "/userGoodsResource/findByUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> findByUserId(@PathVariable("userId") String userId);

    @PostMapping(value = "/userGoodsResource/addVigourForSchedule",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addVigourForSchedule();

    @PostMapping(value = "/userGoodsResource/getUserWarePropInfo/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<PropInfo> getUserWarePropInfo(@PathVariable("userId")String userId);

    @PostMapping(value = "/userGoodsResource/subUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> subUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);


    @PostMapping(value = "/userGoodsResource/addUserGoods/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> addUserGoods(@PathVariable("userId") String userId, @RequestBody List<UserGoods> goodsList);


    /**
     * 查找用户已佩戴的武器
     * @param userId
     * @return
     */
    @PostMapping(value = "/userGoodsResource/findUserWornWeapon/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ArsenalInfoVO findUserWornWeapon(@PathVariable("userId")String userId);


    /**
     * 查找用户所有的武器（包括已佩戴，武器库中的）
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/userGoodsResource/findUserAllWeaponList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ArsenalInfoVO> findUserAllWeaponList(@PathVariable("userId") String userId);

    /**
     * 查找用户武器库中的武器
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/userGoodsResource/findUserArsenalList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ArsenalInfoVO> findUserArsenalList(@PathVariable("userId") String userId);


    @PostMapping(value = "/userGoodsResource/queryPropList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Pager<PropInfoVO> queryPropList(@PathVariable("userId") String userId);


    @PostMapping(value = "/userGoodsResource/queryEquipmentInfoList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Pager<EquipmentInfoVO> queryEquipmentInfoList(@PathVariable("userId") String userId);

    @PostMapping(value = "/userGoodsResource/queryArsenalInfoList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Pager<ArsenalInfoVO> queryArsenalInfoList(@PathVariable("userId") String userId);


    @PostMapping(value = "/userGoodsResource/queryDressedEquipmentList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<EquipmentInfoVO> queryDressedEquipmentList(@PathVariable("userId")String userId);



    @PostMapping(value = "/userGoodsResource/getByUserIdAndGoodsId/{userId}/{goodsId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods getByUserIdAndGoodsId(@PathVariable("userId")String userId, @PathVariable("goodsId")String goodsId);

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

    @PostMapping(value = "/userGoodsResource/addUserGoodsSingle/{userId}/{goodsId}/{amount}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addUserGoodsSingle(@PathVariable("userId")String userId, @PathVariable("goodsId") String goodsId, @PathVariable("amount")Integer amount);


    /**
     * 购买并使用道具
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping(value = "/propResource/buyAndUseProp/{userId}/{goodsId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods buyAndUseProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId);


    /**
     * 使用道具
     * @param userId
     * @param goodsId
     * @param multiple
     * @return
     */
    @PostMapping(value = "/propResource/onlyUseProp/{userId}/{goodsId}/{multiple}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods onlyUseProp(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId,@PathVariable("multiple") Integer multiple);

    /**
     * 打开主线探索宝箱（有消耗）
     * @param goodsId
     * @param amount
     * @param consumeGoods
     * @return
     */
    @PostMapping(value = "/userGoodsResource/openBox/{goodsId}/{amount}/{consumeGoods}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> openBox(@PathVariable("goodsId") String goodsId,
                            @PathVariable("amount")Integer amount,
                            @PathVariable("consumeGoods") String consumeGoods);


    /**
     * 打开主线探索宝箱
     * @param goodsId
     * @param amount
     * @return
     */
    @PostMapping(value = "/userGoodsResource/openBox/{goodsId}/{amount}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<UserGoods> openBox(@PathVariable("goodsId") String goodsId, @PathVariable("amount") Integer amount);

    /**
     * 增加装备或者武器（单个）
     *
     * @param userId
     * @param type   2 装备 3 武器
     * @param id
     * @return
     */
    @PostMapping(value = "/userGoodsResource/addEquipAndWeapon/{userId}/{type}/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addEquipAndWeapon(@PathVariable("userId") String userId, @PathVariable("type") Integer type, @PathVariable("id") String id);

    /**
     * 增加装备或者武器并佩戴（多个）
     *
     * @param userId
     * @param map key: 2 装备 3 武器
     * @return
     */
    @PostMapping(value = "/userGoodsResource/addUseMultiEquipAndWeapon/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserGoods addUseMultiEquipAndWeapon(@PathVariable("userId") String userId, @RequestBody Map<Integer,List<String>> map);

    /**
     * 增加装备或者武器
     * @param userId
     * @param map id type 2 装备 3 武器
     * @return
     */
    @PostMapping(value = "/userGoodsResource/addEquipAndWeaponList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Map> addEquipAndWeaponList(@PathVariable("userId") String userId,@RequestBody Map<String,Integer> map);





    /**
     * 获得玩家角色（装备，道具，武器，宠物）战斗力
     * @param userId
     * @return
     */
    @PostMapping("/userGoodsResource/getUserFightingCapacity/{userId}")
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
    @ResponseBody
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
     * 获取增加后的属性不进行数据库更新
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
    @PostMapping("/userAdornResource/getUserAdornEquipAttribute/{userId}/{vitality}/{attack}/{spellAttacks}/{pdef}/{magdef}/{crit}/{dodge}/{hitRate}/{defenseCrit}")
    UserAdornEquip getUserAdornEquipAttribute(@PathVariable("userId") String userId, @PathVariable("vitality") Double vitality,
                                                     @PathVariable("attack") Double attack,
                                                     @PathVariable("spellAttacks") Double spellAttacks,
                                                     @PathVariable("pdef") Double pdef,
                                                     @PathVariable("magdef") Double magdef,
                                                     @PathVariable("crit") Double crit,
                                                     @PathVariable("dodge") Double dodge,
                                                     @PathVariable("hitRate") Double hitRate,
                                                     @PathVariable("defenseCrit") Double defenseCrit);

    /**
     * 查找用户未拥有的武器（quality =1）
     *
     * @param userId
     * @return
     */
    @PostMapping("/userGoodsResource/findUserAllNotGetWeaponList/{userId}/{profession}")
    @ResponseBody
    List<ArsenalInfo> findUserAllNotGetWeaponList(@PathVariable("userId") String userId, @PathVariable("profession") Integer profession);


    /**
     * @return
     */
    @PostMapping("/userGoodsResource/addMailByUserGoodsList/{userId}")
    @ResponseBody
    boolean addMailByUserGoodsList(@PathVariable("userId") String userId, @RequestBody List<UserGoods> userGoodsList);
}
