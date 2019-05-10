package com.tongzhu.usergoods.service;


import com.tongzhu.usergoods.domain.GeneralityGoods;
import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.model.UserAdornEquip;
import com.tongzhu.usergoods.model.UserGoods;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


public interface UserGoodsService {

    UserGoods getByUserIdAndGoodsId(String userId, String goodsId);

    UserGoods findByUserIdAndGoodsIdNoneWith0(String userId, String goodsId);

    List<UserGoods> findByUserId(String userId);


    UserGoods updateByUserId(UserGoods userGoods);

    void deleteByUserIdAndGoodsId(String userId, int goodsId);

    /**
     * 删除婚戒 false 删除失败 true 删除成功
     *
     * @param userId
     * @return
     */
    UserGoods deleteWeddingRingByUserIdAndGoodsId(String userId) throws Exception;

    void initUserGoods(String userId);

    List<UserGoods> addUserGoods(String userId, List<UserGoods> goodsMapList);

    List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsIdList,boolean bo);


    UserGoods subUserGoodsSingle(String userId, String goodsId, int multiple,boolean bo);

    UserGoods addUserGoodsSingle(String userId, String goodsId, Integer amount);

    /**
     * 获取用户仓库或者背包的物品数量
     *
     * @param userId
     * @param settingPosition
     * @return
     */
    int getBackpacksNumber(String userId, Integer settingPosition);


    /**
     * 摧毁物品
     *
     * @param
     * @return
     */
    int destroyUserGoods(String userId, String goodsid);

    UserGoods getUserGoodsById(String id);

    /**
     * 给所有用户增加1点精力
     */
    void addVigourForSchedule();


    /**
     * 减少用户物品数量
     *
     * @param id
     * @param amount
     * @return
     */
    UserGoods subUserGoodsById(String id, Integer amount,boolean bo);

    /**
     * 查询该用户存入这些道具后背包还剩多少空间
     *
     * @param userId
     * @param goodsIdList 存入的道具
     * @return
     */
    int queryBackpackSpace(String userId, List<String> goodsIdList);

    /**
     * 购买物品
     *
     * @param userId
     * @param goodsId
     * @param amount
     * @param use     0 购买并使用 1 购买但不使用
     * @return
     */
    UserGoods buyProps(String userId, String goodsId, Integer amount, Integer use);

    /**
     * 根据userId和goodsId查询物品基础属性
     *
     * @param userId
     * @param goodsId
     * @return
     */
    GeneralityGoods queryGeneralityGoods(String userId, String goodsId);

    /**
     * 根据goodsId和type查询物品基础属性
     *
     * @param goodsId
     * @param type
     * @return
     */
    GeneralityGoods queryGeneralityGoods(String goodsId, Integer type);


    /**
     * 查询用户拥有的婚戒
     *
     * @param userId
     * @return
     */
    List<GeneralityGoods> queryGoodsWeddingRing(String userId);

    /**
     * 获取用户佩戴装备的最低强化等级
     *
     * @param userId
     * @return
     */
    Integer getMinEnchantlvl(String userId);

    /**
     * 增加装备或者武器
     *
     * @param userId
     * @param type   2 装备 3 武器
     * @param id     表id
     * @return
     */
    UserGoods addEquipAndWeapon(String userId, Integer type, Integer id);

    List<Map> addEquipAndWeaponList(String userId, Map<String, Integer> map);

    Integer getSumGemLevel(String userId);

    /**
     * 穿戴装备 返回数据  穿上的装备 替换下的装备 角色详情
     * @param userId
     * @param goodsId
     * @return
     * @throws Exception
     */
    Map<String,Object> wearEquipment(String userId, String goodsId) throws Exception;

    /**
     * 穿戴武器 返回数据  穿上的装备 替换下的装备 角色详情
     * @param userId
     * @param goodsId
     * @return
     */
    Map<String,Object> wearWeapons(String userId, String goodsId) throws Exception;
    /**
     * 打开主线探索宝箱（有消耗）
     *
     * @param goodsId
     * @param amount
     * @param consumeGoods
     * @return
     */
    List<UserGoods> openBox(String goodsId, Integer amount, String consumeGoods);

    /**
     * 打开主线探索宝箱
     *
     * @param goodsId
     * @param amount
     * @return
     */
    List<UserGoods> openBox(String goodsId, Integer amount);

    /**
     * 将用户物品放入仓库
     *
     * @param userId
     * @param goodsId
     * @param amount
     * @return
     */
    UserGoods transferWarehouse(String userId, String goodsId, Integer amount);

    /**
     * 将用户物品从仓库取出
     *
     * @param userId
     * @param goodsId
     * @param amount
     * @return
     */
    UserGoods transferKnapsack(String userId, String goodsId, Integer amount);

    /**
     * 增加用户物品
     */
    UserGoods addUserById(String id,Integer amount);


    /**
     * 讲物品发送邮件给用户
     * @param userId
     * @param userGoodsList
     * @return
     */
    boolean addMailByUserGoodsList(String userId, List<UserGoods> userGoodsList);
}
