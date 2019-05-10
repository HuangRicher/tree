package com.tongzhu.usergoods.microservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.constant.PropConstant;
import com.tongzhu.usergoods.domain.GeneralityGoods;
import com.tongzhu.usergoods.mapper.vo.ArsenalInfoVO;
import com.tongzhu.usergoods.mapper.vo.EquipmentInfoVO;
import com.tongzhu.usergoods.mapper.vo.PropInfoVO;

@RestController
@RequestMapping("/userGoodsResource")
public class UserGoodsResource {

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private UserWeddingRingLevelService userWeddingRingLevelService;

    @Autowired
    private WeddingRingUpgradeInfoService weddingRingUpgradeInfoService;

    /**
     * 初始化用户物品  金币  钻石  阳光  雨露  木头  精力
     *
     * @param userId
     */
    @PostMapping("/initUserGoods/{userId}")
    public void initUserGoods(@PathVariable("userId") String userId) {
        userGoodsService.initUserGoods(userId);
    }

    /**
     * 减少用户物品
     *
     * @param userId
     * @param goodsIdList
     * @return
     */
    @PostMapping("/subUserGoods/{userId}")
    public List<UserGoods> subUserGoods(@PathVariable("userId") String userId,
                                        @RequestBody List<UserGoods> goodsIdList) {
        return userGoodsService.subUserGoods(userId, goodsIdList, true);
    }

    /**
     * 减少用户单个物品
     *
     * @param userId
     * @return
     */
    @PostMapping("/subUserGoodsSingle/{userId}/{goodsId}/{multiple}")
    public UserGoods subUserGoodsSingle(@PathVariable("userId") String userId,
                                        @PathVariable("goodsId") String goodsId,
                                        @PathVariable("multiple") int multiple) {
        return userGoodsService.subUserGoodsSingle(userId, goodsId, multiple, true);
    }

    /**
     * 增加用户单个物品 只能增加道具
     *
     * @param userId
     * @return
     */
    @PostMapping("/addUserGoodsSingle/{userId}/{goodsId}/{amount}")
    public UserGoods addUserGoodsSingle(@PathVariable("userId") String userId,
                                        @PathVariable("goodsId") String goodsId,
                                        @PathVariable("amount") Integer amount) {
        return userGoodsService.addUserGoodsSingle(userId, goodsId, amount);
    }


    /**
     * 增加用户物品
     *
     * @param userId
     * @param goodsList
     * @return
     */
    @PostMapping("/addUserGoods/{userId}")
    public List<UserGoods> addUserGoods(@PathVariable("userId") String userId,
                                        @RequestBody List<UserGoods> goodsList) {
        return userGoodsService.addUserGoods(userId, goodsList);
    }


    /**
     * 查找xx用户xx物品
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/getByUserIdAndGoodsId/{userId}/{goodsId}")
    public UserGoods getByUserIdAndGoodsId(@PathVariable("userId") String userId,
                                           @PathVariable("goodsId") String goodsId) {
        return userGoodsService.getByUserIdAndGoodsId(userId, goodsId);
    }


    /**
     * 查找用户的所有物品
     *
     * @param userId
     * @return
     */
    @PostMapping("/findByUserId/{userId}")
    List<UserGoods> findByUserId(@PathVariable("userId") String userId) {
        return userGoodsService.findByUserId(userId);
    }


    /**
     * 查找xx用户xx物品(没有返回数量0）
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/findByUserIdAndGoodsIdNoneWith0/{userId}/{goodsId}")
    public UserGoods findByUserIdAndGoodsIdNoneWith0(@PathVariable("userId") String userId,
                                                     @PathVariable("goodsId") String goodsId) {
        return userGoodsService.findByUserIdAndGoodsIdNoneWith0(userId, goodsId);
    }


    /**
     * 删除用户的xx物品
     *
     * @param userId
     * @param goodsId
     */
    @PostMapping("/deleteByUserIdAndGoodsId")
    public void deleteByUserIdAndGoodsId(@RequestParam("userId") String userId,
                                         @RequestParam("goodsId") Integer goodsId) {
        userGoodsService.deleteByUserIdAndGoodsId(userId, goodsId);
    }

    /**
     * 删除用户的婚戒
     *
     * @param userId
     */
    @PostMapping("/deleteWeddingRingByUserIdAndGoodsId/{userId}")
    public UserGoods deleteWeddingRingByUserIdAndGoodsId(@PathVariable("userId") String userId) throws Exception {
        return userGoodsService.deleteWeddingRingByUserIdAndGoodsId(userId);
    }

    /**
     * 每隔15分钟恢复1点精力
     */
    @PostMapping("/addVigourForSchedule")
    public void addVigourForSchedule() {
        userGoodsService.addVigourForSchedule();
    }


    @PostMapping("/getUserWarePropInfo/{userId}")
    public List<PropInfo> getUserWarePropInfo(@PathVariable("userId") String userId) {
        return propInfoService.getUserWarePropInfo(userId);
    }

    /**
     * 查找用户所有的武器（包括已佩戴，武器库中的）
     *
     * @param userId
     * @return
     */
    @PostMapping("/findUserAllWeaponList/{userId}")
    public List<ArsenalInfoVO> findUserAllWeaponList(@PathVariable("userId") String userId) {
        Pager<ArsenalInfoVO> pager = arsenalInfoService.queryWeaponList(userId, 1, 999, null);
        return pager.getContent();
    }

    /**
     * 查找用户未拥有的武器（quality =1）
     *
     * @param userId
     * @return
     */
    @PostMapping("/findUserAllNotGetWeaponList/{userId}/{profession}")
    public List<ArsenalInfo> findUserAllNotGetWeaponList(@PathVariable("userId") String userId, @PathVariable("profession") String profession) {
        List<ArsenalInfo> arsenalInfoList = arsenalInfoService.findUserAllNotGetWeaponList(userId, profession);
        return arsenalInfoList;
    }

    /**
     * 查找用户已佩戴的武器
     *
     * @param userId
     * @return
     */
    @PostMapping("/findUserWornWeapon/{userId}")
    public ArsenalInfoVO findUserWornWeapon(@PathVariable("userId") String userId) {
        Pager<ArsenalInfoVO> pager = arsenalInfoService.queryWeaponList(userId, 1, 999, 2);
        return pager.getContent().isEmpty() ? null : pager.getContent().get(0);
    }

    /**
     * 查找用户武器库中的武器
     *
     * @param userId
     * @return
     */
    @PostMapping("/findUserArsenalList/{userId}")
    public List<ArsenalInfoVO> findUserArsenalList(@PathVariable("userId") String userId) {
        Pager<ArsenalInfoVO> pager = arsenalInfoService.queryWeaponList(userId, 1, 999, 4);
        return pager.getContent();
    }

    @PostMapping("/queryPropList/{userId}")
    public Pager<PropInfoVO> queryPropList(@PathVariable("userId") String userId) {
        return propInfoService.queryPropList(userId, 1, PacksackConstant.KNAPSACK_CAPACITY, PacksackConstant.PROP_PLACE_KNAPSACK);
    }

    /**
     * 查找背包中的花种子
     *
     * @param userId
     * @return
     */
    @PostMapping("/querySeedsPropList/{userId}/")
    public List<PropInfoVO> querySeedsPropList(@PathVariable("userId") String userId) {
        Pager<PropInfoVO> pager = propInfoService.queryPropListByType(userId, 1, PacksackConstant.KNAPSACK_CAPACITY, PacksackConstant.PROP_PLACE_KNAPSACK, PropConstant.TYPE_SEEDS);
        return pager.getContent();
    }

    @PostMapping("/queryEquipmentInfoList/{userId}")
    public Pager<EquipmentInfoVO> queryEquipmentInfoList(@PathVariable("userId") String userId) {
        return equipmentInfoService.queryEquipmentInfoList(userId, 1, PacksackConstant.KNAPSACK_CAPACITY, null);
    }

    @PostMapping("/queryArsenalInfoList/{userId}")
    public Pager<ArsenalInfoVO> queryArsenalInfoList(@PathVariable("userId") String userId) {
        return arsenalInfoService.queryWeaponList(userId, 1, PacksackConstant.KNAPSACK_CAPACITY, PacksackConstant.PROP_PLACE_KNAPSACK);
    }


    /**
     * 查找用户已穿戴的装备
     *
     * @param userId
     * @return
     */
    @PostMapping("/queryDressedEquipmentList/{userId}")
    public List<EquipmentInfoVO> queryDressedEquipmentList(@PathVariable("userId") String userId) {
        List<EquipmentInfoVO> content = equipmentInfoService.queryEquipmentInfoList(userId, 1, 999, PacksackConstant.PROP_PLACE_ADORN).getContent();
        return content;
    }


    /**
     * 查询改用户存入这些道具后背包还剩多少空间
     *
     * @param userId
     * @param goodsIdList 存入的道具
     * @return
     */
    @PostMapping("/queryBackpackSpace/{userId}")
    public Integer queryBackpackSpace(@PathVariable("userId") String userId,
                                      @RequestBody List<String> goodsIdList) {
        return userGoodsService.queryBackpackSpace(userId, goodsIdList);
    }

    /**
     * 查询用户拥有戒指
     *
     * @param userId
     * @return
     */
    @PostMapping("/queryGoodsWeddingRing/{userId}")
    public List<GeneralityGoods> queryGoodsWeddingRing(@PathVariable("userId") String userId) {
        return userGoodsService.queryGoodsWeddingRing(userId);
    }


    /**
     * 增加装备或者武器
     *
     * @param userId
     * @param type   2 装备 3 武器
     * @param id
     * @return
     */
    @PostMapping("/addEquipAndWeapon/{userId}/{type}/{id}")
    public UserGoods addEquipAndWeapon(@PathVariable("userId") String userId, @PathVariable("type") Integer type, @PathVariable("id") Integer id) {
        return userGoodsService.addEquipAndWeapon(userId, type, id);
    }


    /**
     * 增加装备或者武器并佩戴（多个）
     *
     * @param userId
     * @param map    key  2 装备 3 武器
     * @return
     */
    @PostMapping("/addUseMultiEquipAndWeapon/{userId}")
    public void addUseMultiEquipAndWeapon(@PathVariable("userId") String userId, @RequestBody Map<Integer, List<String>> map) throws Exception {
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            for (String id : entry.getValue()) {
                UserGoods userGoods = userGoodsService.addEquipAndWeapon(userId, entry.getKey(), Integer.valueOf(id));
                if (2 == entry.getKey()) userGoodsService.wearEquipment(userId, userGoods.getGoodsId());
                if (3 == entry.getKey()) userGoodsService.wearWeapons(userId, userGoods.getGoodsId());
            }
        }
    }

    /**
     * 增加装备或者武器
     *
     * @param userId
     * @param map    武器装备id 武器装备类型
     * @return
     */
    @PostMapping("/addEquipAndWeaponList/{userId}")
    public List<Map> addEquipAndWeaponList(@PathVariable("userId") String userId, @RequestBody Map<String, Integer> map) {
        return userGoodsService.addEquipAndWeaponList(userId, map);
    }


    /**
     * 穿戴装备
     *
     * @param userId
     * @param goodsId
     * @return
     * @throws Exception
     */
    @PostMapping("/wearEquipment/{userId}/{goodsId}")
    public Map<String, Object> wearEquipment(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId) throws Exception {
        return userGoodsService.wearEquipment(userId, goodsId);
    }

    /**
     * 打开主线探索宝箱（有消耗）
     *
     * @param goodsId
     * @param amount
     * @param consumeGoods
     * @return
     */
    @PostMapping("/openBox/{goodsId}/{amount}/{consumeGoods}")
    List<UserGoods> openBox(@PathVariable("goodsId") String goodsId,
                            @PathVariable("amount") Integer amount,
                            @PathVariable("consumeGoods") String consumeGoods) {
        return userGoodsService.openBox(goodsId, amount, consumeGoods);
    }


    /**
     * 打开主线探索宝箱
     *
     * @param goodsId
     * @param amount
     * @return
     */
    @PostMapping("/openBox/{goodsId}/{amount}")
    List<UserGoods> openBox(@PathVariable("goodsId") String goodsId, @PathVariable("amount") Integer amount) {
        return userGoodsService.openBox(goodsId, amount);
    }

    /**
     * 获得玩家角色（装备，道具，武器，宠物）战斗力
     *
     * @param userId
     * @return
     */
    @PostMapping("/getUserFightingCapacity/{userId}")
    UserAdornEquip getUserFightingCapacity(@PathVariable("userId") String userId) {
        return userAdornEquipService.getUserAdornEquip(userId);
    }

    /**
     * 摧毁物品 返回值为0 则摧毁失败
     *
     * @param userId
     * @return
     */
    @PostMapping("/destroyUserGoods/{userId}/{goodsId}")
    int destroyUserGoods(@PathVariable("userId") String userId, @PathVariable("goodsId") String goodsId) {
        return userGoodsService.destroyUserGoods(userId, goodsId);
    }

    /**
     * 批量发邮箱
     *
     * @return
     */
    @PostMapping("/addMailByUserGoodsList/{userId}")
    boolean addMailByUserGoodsList(@PathVariable("userId") String userId, @RequestBody List<UserGoods> userGoodsList) {
        return userGoodsService.addMailByUserGoodsList(userId, userGoodsList);
    }

    /**
     * 获取用户戒指id
     *
     * @return
     */
    @PostMapping("/getUserWeddingRingId/{userId}")
    Integer getUserWeddingRingId(@PathVariable("userId") String userId) {
        UserWeddingRingLevel userWeddingRingLevelByUserId = userWeddingRingLevelService.getUserWeddingRingLevelByUserId(userId);
        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userWeddingRingLevelByUserId.getIntensifyId());
        return equipmentInfo.getId();
    }

    /**
     * 获取用户戒指id 升级需要的幸福值
     *
     * @return
     */
    @PostMapping("/getUserWeddingRingHappinessNum/{userId}")
    Map getUserWeddingRingHappinessNum(@PathVariable("userId") String userId) {
        UserWeddingRingLevel userWeddingRingLevelByUserId = userWeddingRingLevelService.getUserWeddingRingLevelByUserId(userId);
        if (userWeddingRingLevelByUserId == null) {
            return new HashMap();
        }
        Integer upgradeGoodsId = 0;
        if (userWeddingRingLevelByUserId.getLevel() / 10 > 0 && (userWeddingRingLevelByUserId.getLevel() + 1) % 10 == 1) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfoByWearPositionAndQuality(AdornEquipEnum.WEDDING_RING.getId(), userWeddingRingLevelByUserId.getLevel() / 10 + 1);
            if (equipmentInfo != null) {
                upgradeGoodsId = equipmentInfo.getId();
            }
        } else {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userWeddingRingLevelByUserId.getIntensifyId());
            upgradeGoodsId = equipmentInfo.getId();
        }
        WeddingRingUpgradeInfo weddingRingUpgradeInfoByLevel = weddingRingUpgradeInfoService.getWeddingRingUpgradeInfoByLevel(userWeddingRingLevelByUserId.getLevel() + 1);
        Map retMap = new HashMap();
        retMap.put("upgradeHappinessNum", weddingRingUpgradeInfoByLevel.getHappinessNum());
        retMap.put("upgradeGoodsId", upgradeGoodsId);
        retMap.put("level", userWeddingRingLevelByUserId.getLevel());
        return retMap;
    }


}
