package com.tongzhu.usergoods.service.impl;

import java.util.*;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.RabbitMQConstant;
import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.domain.UserMailSingle;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.service.*;
import com.tongzhu.usergoods.util.ItemGoodsUtil;
import com.tongzhu.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.domain.GeneralityGoods;
import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.mapper.UserGoodsMapper;
import com.tongzhu.usergoods.mapper.UserWeddingRingLevelMapper;
import com.tongzhu.usergoods.mapper.ext.UserGoodsExtMapper;
import com.tongzhu.util.CommonUtil;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;


@Service
//@CacheConfig(cacheNames="user_goods")
public class UserGoodsServiceImpl implements UserGoodsService {

    private Logger log = LoggerFactory.getLogger(UserGoodsServiceImpl.class);

    @Autowired
    private UserGoodsMapper userGoodsMapper;

    @Autowired
    private UserGoodsExtMapper userGoodsExtMapper;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private UserWeddingRingLevelMapper userWeddingRingLevelMapper;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private UserWeddingRingLevelService userWeddingRingLevelService;

    @Autowired
    private UserGoodsWarehouseService userGoodsWarehouseService;

    @Autowired
    private UserMailSingleService userMailSingleService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;


    /**
     * 查找xx用户的xx物品
     *
     * @param userId
     * @param goodsId
     * @return
     */
    //@Cacheable(key="#p0+'-'+#p1")
    @Override
    public UserGoods getByUserIdAndGoodsId(String userId, String goodsId) {
        UserGoodsExample example = new UserGoodsExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId).andStatusEqualTo(PacksackConstant.GOODS_STATUS_NORMAL);
        List<UserGoods> userGoodsList = userGoodsMapper.selectByExample(example);
        if (userGoodsList != null && userGoodsList.size() > 0) {
            UserGoods userGoods = userGoodsList.get(0);
            if (userGoods.getType() == PacksackConstant.GIFT_TYPE_EQUIPMENT && userGoods.getType() == PacksackConstant.GIFT_TYPE_ARSENAL) {
                if (userGoods.getAmount() <= 0) {
                    return null;
                }
            }
            return userGoods;
        } else {
            return null;
        }
    }

    @Override
    public UserGoods findByUserIdAndGoodsIdNoneWith0(String userId, String goodsId) {
        UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
        UserGoods goods = new UserGoods();
        if (userGoods != null) {
            switch (userGoods.getType()) {
                case 1://道具
                    goods.setGoodsId(userGoods.getGoodsId());
                    break;
                case 2: //装备
                    EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
                    goods.setGoodsId(equipmentInfo.getId() + "");
                    break;
                case 3:// 武器
                    ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
                    goods.setGoodsId(arsenalInfo.getId() + "");
                    break;
            }
            goods.setType(userGoods.getType());
            goods.setId(userGoods.getGoodsId());
            goods.setAmount(userGoods.getAmount());
            return goods;
        }
        return null;
    }


    //@Cacheable(key="#p0")
    @Override
    public List<UserGoods> findByUserId(String userId) {
        return userGoodsExtMapper.selectByUserId(userId);
    }


    /**
     * 给用户增加物品数量
     *
     * @param userId
     * @param goodsMapList
     * @return
     */
    @Override
    public List<UserGoods> addUserGoods(String userId, List<UserGoods> goodsMapList) {
        synchronized (userId.intern()) {
            List<UserGoods> goodsList = new ArrayList<>();
            for (int i = 0; i < goodsMapList.size(); i++) {
                UserGoods userGoodsList = goodsMapList.get(i);
                String goodsId = userGoodsList.getGoodsId();
                int amount = userGoodsList.getAmount();
                UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
                UserGoods goods = new UserGoods();
                int number = this.getBackpacksNumber(userId, PacksackConstant.PROP_PLACE_KNAPSACK);
                PropInfo propInfo = propInfoService.getPropInfo(goodsId);
                if (propInfo == null) {
                    continue;
                }
                if (userGoods != null) {
                    if (propInfo.getType() != PacksackConstant.PROP_TYPE_RESOURCE && number >= PacksackConstant.KNAPSACK_CAPACITY && userGoods.getAmount() <= 0) {
                        UserMailSingle userMailSingle = new UserMailSingle();
                        userMailSingle.setReceiverId(userId);
                        userMailSingle.setType(0);
                        userMailSingle.setTitle("有未领取的道具请查收");
                        userMailSingle.setContent("请尽快整理背包，奖励有效时间15天");
                        Map map = new HashMap();
                        map.put(goodsId, amount);
                        Map retMap = new HashMap();
                        retMap.put("prop", map);
                        userMailSingle.setAccessory(JSONObject.toJSONString(retMap));
                        userMailSingleService.addUserMailSingle(userMailSingle);
                        continue;
                    }
                    goods.setGoodsId(userGoods.getGoodsId());
                    userGoods.setAmount(userGoods.getAmount() + amount);
                    this.updateByUserId(userGoods);
                    goods.setAmount(userGoods.getAmount());
                    goodsList.add(goods);
                    if (GoodsConstant.GOODS_MONEY.equals(goods.getGoodsId())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put(RabbitMQConstant.USER_ID, userId);
                        map.put(RabbitMQConstant.TYPE, RabbitMQConstant.TYPE_MONEY);
                        buildingService.sendMQMsg(JSON.toJSONString(map));
                    }
                } else {
                    if (propInfo.getType() != PacksackConstant.PROP_TYPE_RESOURCE && number >= PacksackConstant.KNAPSACK_CAPACITY) {
                        UserMailSingle userMailSingle = new UserMailSingle();
                        userMailSingle.setReceiverId(userId);
                        userMailSingle.setType(0);
                        userMailSingle.setContent("请尽快整理背包，奖励有效时间15天");
                        userMailSingle.setTitle("背包已满，奖励发送至邮箱");
                        Map map = new HashMap();
                        map.put(goodsId, amount);
                        Map retMap = new HashMap();
                        retMap.put("prop", map);
                        userMailSingle.setAccessory(JSONObject.toJSONString(retMap));
                        userMailSingleService.addUserMailSingle(userMailSingle);
                        continue;
                    }
                    userGoods = new UserGoods();
                    Integer time = propInfo.getTime();
                    if (time > 0) {
                        userGoods.setSurplusDate(DateUtil.computingTime(time));
                    }
                    userGoods.setId(UUIDUtil.generateUUID());
                    userGoods.setUserId(userId);
                    userGoods.setGoodsId(goodsId);
                    userGoods.setAmount(amount);
                    userGoods.setCreateDate(new Date());
                    userGoods.setUpdateDate(new Date());
                    userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_KNAPSACK);
                    userGoods.setType(PacksackConstant.GOODS_TYPE_PROP);
                    userGoods.setStatus(PacksackConstant.GOODS_STATUS_NORMAL);
                    //保存数据到数据库
                    userGoodsMapper.insertSelective(userGoods);
                    goods.setAmount(userGoods.getAmount());
                    goods.setGoodsId(userGoods.getGoodsId());
                    goodsList.add(goods);
                    //金币增加，提示是否可以升级技能，建筑等级
                    if (GoodsConstant.GOODS_MONEY.equals(goods.getGoodsId())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put(RabbitMQConstant.USER_ID, userId);
                        map.put(RabbitMQConstant.TYPE, RabbitMQConstant.TYPE_MONEY);
                        buildingService.sendMQMsg(JSON.toJSONString(map));
                    }
                }

            }
            return goodsList;
        }
    }


    /**
     * 减少一个物品信息
     *
     * @return 返回当前数量
     */

    //@CachePut(key="#p0+'-'+#p1")
    @Override
    public List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsIdList, boolean bo) {
        synchronized (userId.intern()) {
            List<UserGoods> goodsList = new ArrayList<>();
            for (UserGoods map : goodsIdList) {
                String goodsId = map.getGoodsId();
                int amount = map.getAmount();
                UserGoods firstUserGoods = this.getByUserIdAndGoodsId(userId, goodsId);
                if (firstUserGoods != null) {
                    if (firstUserGoods.getAmount() - amount < 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            for (UserGoods map : goodsIdList) {
                String goodsId = map.getGoodsId();
                int amount = map.getAmount();
                UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
                UserGoods goods = new UserGoods();
                if (userGoods != null) {
                    goods.setGoodsId(userGoods.getGoodsId());
                    goods.setType(userGoods.getType());
                    if (userGoods.getAmount() - amount < 0) {
                        return null;
                    } else {
                        userGoods.setAmount(userGoods.getAmount() - amount);
                        if (GoodsConstant.GOODS_JEWEL.equals(userGoods.getGoodsId())) {
                            userService.updateCrunchies(userId, PacksackConstant.CRUNCHIES_ZS_TYPE, amount);
                        }
                        if (bo) {
                            if (userGoods.getUpdateDate() != null && !DateUtil.isSameDate(new Date(), userGoods.getUpdateDate())) {
                                userGoods.setNumber(amount);
                            } else {
                                userGoods.setNumber(userGoods.getNumber() + amount);
                            }
                        }
                        this.updateByUserId(userGoods);
                        goods.setAmount(userGoods.getAmount());
                        goodsList.add(goods);
                    }
                } else {
                    return null;
                }
            }
            return goodsList;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGoods subUserGoodsSingle(String userId, String goodsId, int multiple, boolean bo) {
        UserGoods userGoods = new UserGoods();
        userGoods.setGoodsId(goodsId);
        userGoods.setAmount(multiple);
        List<UserGoods> userGoodsList = new ArrayList<>();
        userGoodsList.add(userGoods);
        List<UserGoods> retUserGoods = this.subUserGoods(userId, userGoodsList, bo);
        if (retUserGoods != null && retUserGoods.size() > 0) {
            return retUserGoods.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGoods addUserGoodsSingle(String userId, String goodsId, Integer amount) {
        UserGoods userGoods = new UserGoods();
        userGoods.setGoodsId(goodsId);
        userGoods.setAmount(amount);
        List<UserGoods> userGoodsList = new ArrayList<>();
        userGoodsList.add(userGoods);
        List<UserGoods> retUserGoodsList = addUserGoods(userId, userGoodsList);
        if (retUserGoodsList != null && retUserGoodsList.size() > 0) {
            return retUserGoodsList.get(0);
        }
        return null;
    }

    @Override
    public int getBackpacksNumber(String userId, Integer settingPosition) {
        return userGoodsExtMapper.getBackpacksNumber(userId, settingPosition);
    }


    @Override
    public int destroyUserGoods(String userId, String goodsid) {
        UserGoods byUserIdAndGoodsId = this.getByUserIdAndGoodsId(userId, goodsid);
        if (byUserIdAndGoodsId == null) {
            return 0;
        }
        if (byUserIdAndGoodsId.getSettingPosition() == PacksackConstant.PROP_PLACE_WEAPON) {
            return 0;
        }
        if (byUserIdAndGoodsId.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
            return 0;
        }
        if (byUserIdAndGoodsId.getAmount() <= 0) {
            return 0;
        }
        if (byUserIdAndGoodsId.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(byUserIdAndGoodsId.getGoodsId());
            if (propInfo == null) {
                return 0;
            }
            if (propInfo.getDestroy() == PacksackConstant.GOODS_OPERATION_CANNOT) {
                return 0;
            }
        } else if (byUserIdAndGoodsId.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(byUserIdAndGoodsId.getGoodsId());
            if (equipmentInfo == null) {
                return 0;
            }
            if (equipmentInfo.getDestroy() == PacksackConstant.GOODS_OPERATION_CANNOT) {
                return 0;
            }
        } else {
            return 0;
        }
        byUserIdAndGoodsId.setStatus(PacksackConstant.GOODS_STATUS_REMOVE);
        byUserIdAndGoodsId.setUpdateDate(new Date());
        return userGoodsMapper.updateByPrimaryKey(byUserIdAndGoodsId);
    }

    @Override
    public void addVigourForSchedule() {
        userGoodsExtMapper.addVigourForSchedule();
    }


    @Override
    public UserGoods subUserGoodsById(String id, Integer amount, boolean bo) {
        UserGoods userGoods = this.getUserGoodsById(id);
        UserGoods goods = new UserGoods();
        if (userGoods != null) {
            goods.setGoodsId(userGoods.getGoodsId());
            goods.setType(userGoods.getType());
            if (userGoods.getAmount() - amount < 0) {
                GeneralityGoods generalityGoods = this.queryGeneralityGoods(userGoods.getGoodsId(), userGoods.getType());
                throw new CheckException(generalityGoods.getName() + "余额不足！");
            } else {
                userGoods.setAmount(userGoods.getAmount() - amount);
                if (GoodsConstant.GOODS_JEWEL.equals(userGoods.getGoodsId())) {
                    userService.updateCrunchies(userGoods.getUserId(), PacksackConstant.CRUNCHIES_ZS_TYPE, amount);
                }
                if (bo) {
                    if (userGoods.getUpdateDate() != null && !DateUtil.isSameDate(new Date(), userGoods.getUpdateDate())) {
                        userGoods.setNumber(amount);
                    } else {
                        userGoods.setNumber(userGoods.getNumber() + amount);
                    }
                }
                this.updateByUserId(userGoods);
                goods.setAmount(userGoods.getAmount());
            }
        } else {
            throw new CheckException("余额不足！");
        }
        return goods;
    }

    @Override
    public int queryBackpackSpace(String userId, List<String> goodsIdList) {
        int i = userGoodsExtMapper.queryExistGoods(userId, goodsIdList);
        int backpacksNumber = getBackpacksNumber(userId, PacksackConstant.PROP_PLACE_KNAPSACK);
        return PacksackConstant.KNAPSACK_CAPACITY - backpacksNumber - goodsIdList.size() + i;
    }

    @Override
    public UserGoods buyProps(String userId, String goodsId, Integer amount, Integer use) {
        PropInfo propInfo = propInfoService.getPropInfo(goodsId);
        String conversionPropId = propInfo.getConversionPropId();
        Integer conversionAmount = propInfo.getConversionAmount();
        int i = conversionAmount * amount;
        UserGoods userGoods = this.getByUserIdAndGoodsId(userId, conversionPropId);
        if (userGoods == null || userGoods.getAmount() < i) {
            throw new CheckException(ItemGoodsUtil.getNameByGoodsId(conversionPropId) + "余额不足！");
        }
        UserGoods goods = this.subUserGoodsById(userGoods.getId(), i,true);
        if (use == PacksackConstant.GOODS_USE_NO) {
            addUserGoodsSingle(userId, goodsId, amount);
        }
        return goods;
    }

    @Override
    public GeneralityGoods queryGeneralityGoods(String userId, String goodsId) {
        UserGoods userGoods = getByUserIdAndGoodsId(userId, goodsId);
        GeneralityGoods generalityGoods = new GeneralityGoods();
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
            if (propInfo != null) {
                BeanUtils.copyProperties(generalityGoods, propInfo);
                return generalityGoods;
            }
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo != null) {
                BeanUtils.copyProperties(generalityGoods, equipmentInfo);
                return generalityGoods;
            }
        } else {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            if (arsenalInfo != null) {
                BeanUtils.copyProperties(generalityGoods, arsenalInfo);
                return generalityGoods;
            }
        }
        return null;
    }

    @Override
    public GeneralityGoods queryGeneralityGoods(String goodsId, Integer type) {
        GeneralityGoods generalityGoods = new GeneralityGoods();
        if (type == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(goodsId);
            if (propInfo != null) {
                BeanUtils.copyProperties(generalityGoods, propInfo);
                return generalityGoods;
            }
        } else if (type == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(goodsId);
            if (equipmentInfo != null) {
                BeanUtils.copyProperties(generalityGoods, equipmentInfo);
                return generalityGoods;
            }
        } else {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(goodsId);
            if (arsenalInfo != null) {
                BeanUtils.copyProperties(generalityGoods, arsenalInfo);
                return generalityGoods;
            }
        }
        return null;
    }

    @Override
    public List<GeneralityGoods> queryGoodsWeddingRing(String userId) {
        List<EquipmentInfo> equipmentInfoList = userGoodsExtMapper.queryGoodsWeddingRing(userId, AdornEquipEnum.WEDDING_RING.getId());
        List<GeneralityGoods> generalityGoodsList = new ArrayList<>();
        GeneralityGoods generalityGoods = new GeneralityGoods();
        for (EquipmentInfo equipmentInfo : equipmentInfoList) {
            generalityGoods.setId(equipmentInfo.getId());
            generalityGoods.setIntensifyId(equipmentInfo.getIntensifyId());
            generalityGoods.setLevel(equipmentInfo.getLevel());
            generalityGoods.setName(equipmentInfo.getName());
            generalityGoodsList.add(generalityGoods);
        }
        return generalityGoodsList;
    }

    @Override
    public Integer getMinEnchantlvl(String userId) {
        return userGoodsExtMapper.getMinEnchantlvl(userId);
    }

    @Override
    @Transactional
    public UserGoods addEquipAndWeapon(String userId, Integer type, Integer id) {
        UserGoods userGoods = new UserGoods();
        String goodsId = null;
        if (PacksackConstant.GOODS_TYPE_EQUIPMENT == type) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfoByIdAndOriginal(id + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (equipmentInfo == null) {
                return null;
            }
            if (equipmentInfo.getWearPosition().intValue() != AdornEquipEnum.WEDDING_RING.getId().intValue() && equipmentInfo.getWearPosition().intValue() != AdornEquipEnum.FASHION.getId()) {
                UserGoods byUserIdAndGoodsId = this.getByUserIdAndGoodsId(userId, equipmentInfo.getIntensifyId());
                if (byUserIdAndGoodsId != null && byUserIdAndGoodsId.getAmount() > 0) {
                    return null;
                }

                UserGoodsWarehouse userGoodsWarehouse = userGoodsWarehouseService.getUserGoodsWarehouseByUserIdAndGoodsId(userId, equipmentInfo.getIntensifyId());
                if (userGoodsWarehouse != null && userGoodsWarehouse.getAmount() > 0) {
                    return null;
                }
            } else {
                equipmentInfo.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                equipmentInfo.setIntensifyId(UUIDUtil.generateUUID());
                equipmentInfoService.insert(equipmentInfo);
            }

            userGoods.setId(UUIDUtil.generateUUID());
            userGoods.setGoodsId(equipmentInfo.getIntensifyId());
            userGoods.setUpdateDate(new Date());
            userGoods.setType(type);
            userGoods.setStatus(PacksackConstant.GOODS_STATUS_NORMAL);
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_KNAPSACK);
            userGoods.setCreateDate(new Date());
            userGoods.setAmount(1);
            userGoods.setUserId(userId);
            if (equipmentInfo.getTime() > 0) {
                Date date = DateUtil.computingTime(equipmentInfo.getTime());
                userGoods.setSurplusDate(date);
            }
            goodsId = equipmentInfo.getId() + "";
        } else if (PacksackConstant.GOODS_TYPE_WEAPON == type) {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal(id + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (arsenalInfo == null) {
                return null;
            }
            UserGoods byUserIdAndGoodsId = this.getByUserIdAndGoodsId(userId, arsenalInfo.getIntensifyId());
            if (byUserIdAndGoodsId != null && byUserIdAndGoodsId.getAmount() > 0) {
                return null;
            }

            userGoods.setId(UUIDUtil.generateUUID());
            userGoods.setGoodsId(arsenalInfo.getIntensifyId());
            userGoods.setUpdateDate(new Date());
            userGoods.setType(type);
            userGoods.setStatus(PacksackConstant.GOODS_STATUS_NORMAL);
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_WEAPON);
            userGoods.setCreateDate(new Date());
            userGoods.setAmount(1);
            userGoods.setUserId(userId);
            if (arsenalInfo.getTime() > 0) {
                Date date = DateUtil.computingTime(arsenalInfo.getTime());
                userGoods.setSurplusDate(date);
            }
            goodsId = arsenalInfo.getId() + "";
        } else {
            return null;
        }
        int number = this.getBackpacksNumber(userId, PacksackConstant.PROP_PLACE_KNAPSACK);
        if (number >= PacksackConstant.KNAPSACK_CAPACITY) {
            UserMailSingle userMailSingle = new UserMailSingle();
            userMailSingle.setReceiverId(userId);
            userMailSingle.setType(0);
            userMailSingle.setContent("请尽快整理背包，奖励有效时间15天");
            userMailSingle.setTitle("背包已满，奖励发送至邮箱");
            Map map = new HashMap();
            map.put(goodsId, userGoods.getAmount());
            Map retMap = new HashMap();
            if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
                retMap.put("equipment", map);
            } else {
                retMap.put("arsenal", map);
            }

            userMailSingle.setAccessory(JSONObject.toJSONString(retMap));
            userMailSingleService.addUserMailSingle(userMailSingle);
            return null;
        } else {
            userGoodsMapper.insertSelective(userGoods);
            return userGoods;
        }
    }

    @Override
    public List<Map> addEquipAndWeaponList(String userId, Map<String, Integer> map) {
        List<Map> list = new ArrayList<>();
        map.forEach((id, type) -> {
            UserGoods userGoods = addEquipAndWeapon(userId, type, Integer.parseInt(id));
            if (userGoods != null) {
                list.add(ObjectUtil.getGoodsMap(userGoods.getGoodsId(), id, type, userGoods.getAmount(), 0));
            }
        });
        return list;
    }

    @Override
    public Integer getSumGemLevel(String userId) {
        return userGoodsExtMapper.getSumGemLevel(userId);
    }

    @Override
    @Transactional
    public Map<String, Object> wearEquipment(String userId, String goodsId) throws Exception {
        UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
        if (userGoods == null) {
            throw new Exception("装备不存在");
        }
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
        if (userAdornEquip == null) {
            userAdornEquip = new UserAdornEquip();
            userAdornEquip.setUserId(userId);
            userAdornEquip.setId(UUIDUtil.generateUUID());
            userAdornEquip.setAlwaysFighting(0.0);
            userAdornEquipService.addUserAddornEquip(userAdornEquip);
        }

        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(goodsId);
        String wearPosition = AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition());
        if (wearPosition == null) {
            throw new Exception("装备配置错误");
        }

        // 增加戒指等级记录
        if (equipmentInfo.getWearPosition() == AdornEquipEnum.WEDDING_RING.getId()) {
            UserWeddingRingLevel userWeddingRingLevelByUserId = userWeddingRingLevelService.getUserWeddingRingLevelByUserId(userId);
            if (userWeddingRingLevelByUserId == null) {
                userWeddingRingLevelByUserId = new UserWeddingRingLevel();
                userWeddingRingLevelByUserId.setCreateDate(new Date());
                userWeddingRingLevelByUserId.setIntensifyId(equipmentInfo.getIntensifyId());
                userWeddingRingLevelByUserId.setLevel((equipmentInfo.getQuality() - 1) * 10 + 1);
                userWeddingRingLevelByUserId.setUserId(userId);
                userWeddingRingLevelService.insertSelective(userWeddingRingLevelByUserId);
            } else {
                userWeddingRingLevelByUserId.setIntensifyId(equipmentInfo.getIntensifyId());
                userWeddingRingLevelByUserId.setLevel((equipmentInfo.getQuality() - 1) * 10 + 1);
                userWeddingRingLevelService.updateByPrimaryKeySelective(userWeddingRingLevelByUserId);
            }
        }
        Map<String, Object> map = new HashMap<>();
        String element = (String) CommonUtil.getObjectElement(userAdornEquip, wearPosition);
        if (userGoods.getId().equals(element)) {
            return null;
        }
        userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
        if (StringUtils.isEmpty(element)) {
            CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()), userGoods.getId());
            userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + equipmentInfo.getFightingCapacity());
            userAdornEquip.setVitality(userAdornEquip.getVitality() + equipmentInfo.getVitality());
            userAdornEquip.setAttack(userAdornEquip.getAttack() + equipmentInfo.getAttack());
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + equipmentInfo.getSpellAttacks());
            userAdornEquip.setPdef(userAdornEquip.getPdef() + equipmentInfo.getPdef());
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + equipmentInfo.getMagdef());
            userAdornEquip.setCrit(userAdornEquip.getCrit() + equipmentInfo.getCrit());
            userAdornEquip.setDodge(userAdornEquip.getDodge() + equipmentInfo.getDodge());
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + equipmentInfo.getHitRate());
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + equipmentInfo.getDefenseCrit());
            userGoods.setUpdateDate(new Date());
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_ADORN);
            Map wearGoods = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, 1, equipmentInfo.getEnchantlvl());
            map.put("wearGoods", wearGoods);
        } else {
            UserGoods userGoodsBy = this.getUserGoodsById(element);
            userGoodsBy.setUpdateDate(new Date());
            userGoodsBy.setSettingPosition(PacksackConstant.PROP_PLACE_KNAPSACK);
            this.updateByUserId(userGoodsBy);
            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoodsBy.getGoodsId());
            CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()), userGoods.getId());
            userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + equipmentInfo.getFightingCapacity() - equipment.getFightingCapacity());
            userAdornEquip.setVitality(userAdornEquip.getVitality() + equipmentInfo.getVitality() - equipment.getVitality());
            userAdornEquip.setAttack(userAdornEquip.getAttack() + equipmentInfo.getAttack() - equipment.getAttack());
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + equipmentInfo.getSpellAttacks() - equipment.getSpellAttacks());
            userAdornEquip.setPdef(userAdornEquip.getPdef() + equipmentInfo.getPdef() - equipment.getPdef());
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + equipmentInfo.getMagdef() - equipment.getMagdef());
            userAdornEquip.setCrit(userAdornEquip.getCrit() + equipmentInfo.getCrit() - equipment.getCrit());
            userAdornEquip.setDodge(userAdornEquip.getDodge() + equipmentInfo.getDodge() - equipment.getDodge());
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + equipmentInfo.getHitRate() - equipment.getHitRate());
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + equipmentInfo.getDefenseCrit() - equipment.getDefenseCrit());
            userGoods.setUpdateDate(new Date());
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_ADORN);
            Map wearGoods = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, 1, equipmentInfo.getEnchantlvl());
            map.put("wearGoods", wearGoods);
            Map dischargeGoods = ObjectUtil.getGoodsMap(equipment.getIntensifyId(), equipment.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, 1, equipment.getEnchantlvl());
            map.put("dischargeGoods", dischargeGoods);
        }
        userAdornEquipService.updateUserAddornEquip(userAdornEquip);
        this.updateByUserId(userGoods);
        map.put("userAdornEquip", userAdornEquip);
        return map;
    }

    @Override
    public Map<String, Object> wearWeapons(String userId, String goodsId) throws Exception {
        UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
        if (userAdornEquip == null) {
            userAdornEquip = new UserAdornEquip();
            userAdornEquip.setUserId(userId);
            userAdornEquip.setAlwaysFighting(0.0);
            userAdornEquip.setId(UUIDUtil.generateUUID());
            userAdornEquipService.addUserAddornEquip(userAdornEquip);
        }

        Map<String, Object> map = new HashMap<>();
        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(goodsId);
        String weapon = userAdornEquip.getWeapon();
        userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
        if (StringUtils.isEmpty(weapon)) {
            userAdornEquip.setWeapon(userGoods.getId());
            userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfo.getFightingCapacity());
            userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfo.getVitality());
            userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfo.getAttack());
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfo.getSpellAttacks());
            userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfo.getPdef());
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfo.getMagdef());
            userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfo.getCrit());
            userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfo.getDodge());
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfo.getHitRate());
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfo.getDefenseCrit());
            userGoods.setUpdateDate(new Date());
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_ADORN);
            userGoods.setUpdateDate(new Date());
            Map wearGoods = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, 1, arsenalInfo.getEnchantlvl());
            map.put("wearGoods", wearGoods);
        } else {
            UserGoods userGoodsDemount = this.getUserGoodsById(weapon);
            if (userGoodsDemount == null) {
                throw new Exception("装备配置错误");
            }
            userGoodsDemount.setUpdateDate(new Date());
            userGoodsDemount.setSettingPosition(PacksackConstant.PROP_PLACE_WEAPON);
            this.updateByUserId(userGoodsDemount);
            ArsenalInfo arsenalInfoDemount = arsenalInfoService.getArsenalInfo(userGoodsDemount.getGoodsId());
            userAdornEquip.setWeapon(userGoods.getId());
            userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfo.getFightingCapacity() - arsenalInfoDemount.getFightingCapacity());
            userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfo.getVitality() - arsenalInfoDemount.getVitality());
            userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfo.getAttack() - arsenalInfoDemount.getAttack());
            userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfo.getSpellAttacks() - arsenalInfoDemount.getSpellAttacks());
            userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfo.getPdef() - arsenalInfoDemount.getPdef());
            userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfo.getMagdef() - arsenalInfoDemount.getMagdef());
            userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfo.getCrit() - arsenalInfoDemount.getCrit());
            userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfo.getDodge() - arsenalInfoDemount.getDodge());
            userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfo.getHitRate() - arsenalInfoDemount.getHitRate());
            userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfo.getDefenseCrit() - arsenalInfoDemount.getDefenseCrit());
            userGoods.setUpdateDate(new Date());
            userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_ADORN);
            Map wearGoods = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, 1, arsenalInfo.getEnchantlvl());
            map.put("wearGoods", wearGoods);
            Map dischargeGoods = ObjectUtil.getGoodsMap(arsenalInfoDemount.getIntensifyId(), arsenalInfoDemount.getId(), PacksackConstant.GOODS_TYPE_WEAPON, 1, arsenalInfoDemount.getEnchantlvl());
            map.put("dischargeGoods", dischargeGoods);
        }
        userAdornEquipService.updateUserAddornEquip(userAdornEquip);
        this.updateByUserId(userGoods);
        map.put("userAdornEquip", userAdornEquip);
        return map;
    }

    @Override
    public List<UserGoods> openBox(String goodsId, Integer amount, String consumeGoods) {
        return null;
    }

    @Override
    public List<UserGoods> openBox(String goodsId, Integer amount) {
        return null;
    }

    @Transactional
    @Override
    public UserGoods transferWarehouse(String userId, String goodsId, Integer amount) {
        UserGoods userGoods = this.getByUserIdAndGoodsId(userId, goodsId);
        if (userGoods == null || userGoods.getAmount() < amount) {
            return null;
        }
        Integer number = userGoodsWarehouseService.getUserGoodsNumberByUserId(userId);
        UserGoodsWarehouse userGoodsWarehouseByUserIdAndGoodsId = userGoodsWarehouseService.getUserGoodsWarehouseByUserIdAndGoodsId(userId, goodsId);
        if (number == null || (PacksackConstant.WAREHOUSE_CAPACITY <= number &&
                (userGoodsWarehouseByUserIdAndGoodsId == null || userGoodsWarehouseByUserIdAndGoodsId.getAmount() <= 0))) {
            return null;
        }
        UserGoods goods = this.subUserGoodsSingle(userId, goodsId, amount, false);
        if (goods == null) {
            return null;
        }
        UserGoodsWarehouse userGoodsWarehouse = userGoodsWarehouseService.getUserGoodsWarehouseById(userGoods.getId());
        if (userGoodsWarehouse == null) {
            userGoodsWarehouse = new UserGoodsWarehouse();
            userGoodsWarehouse.setGoodsId(goodsId);
            userGoodsWarehouse.setId(userGoods.getId());
            userGoodsWarehouse.setType(userGoods.getType());
            userGoodsWarehouse.setUserId(userId);
            userGoodsWarehouse.setAmount(amount);
            userGoodsWarehouse.setSurplusDate(userGoods.getSurplusDate());
            userGoodsWarehouseService.insertSelective(userGoodsWarehouse);
        } else {
            userGoodsWarehouse.setAmount(userGoodsWarehouse.getAmount() + amount);
            userGoodsWarehouseService.update(userGoodsWarehouse);
        }

        return goods;
    }

    @Transactional
    @Override
    public UserGoods transferKnapsack(String userId, String goodsId, Integer amount) {
        UserGoodsWarehouse userGoodsWarehouse = userGoodsWarehouseService.getUserGoodsWarehouseByUserIdAndGoodsId(userId, goodsId);
        if (userGoodsWarehouse == null || userGoodsWarehouse.getAmount() < amount) {
            return null;
        }
        int number = this.getBackpacksNumber(userId, PacksackConstant.PROP_PLACE_KNAPSACK);
        UserGoods byUserIdAndGoodsId = this.getByUserIdAndGoodsId(userId, goodsId);
        if (PacksackConstant.KNAPSACK_CAPACITY <= number && byUserIdAndGoodsId.getAmount() <= 0) {
            return null;
        }
        UserGoods userGoods = this.addUserById(userGoodsWarehouse.getId(), amount);
        if (userGoods == null) {
            return null;
        }

        userGoodsWarehouse.setAmount(userGoodsWarehouse.getAmount() - amount);
        userGoodsWarehouseService.update(userGoodsWarehouse);
        return userGoods;
    }

    @Override
    public UserGoods addUserById(String id, Integer amount) {
        UserGoods userGoodsById = this.getUserGoodsById(id);
        if (userGoodsById == null) {
            return null;
        }
        userGoodsById.setAmount(userGoodsById.getAmount() + amount);
        this.updateByUserId(userGoodsById);
        return userGoodsById;
    }

    @Override
    public boolean addMailByUserGoodsList(String userId, List<UserGoods> userGoodsList) {
        Map map = new HashMap();
        for (UserGoods userGoods : userGoodsList) {
            map.put(userGoods.getGoodsId(), userGoods.getAmount());
        }
        UserMailSingle userMailSingle = new UserMailSingle();
        userMailSingle.setReceiverId(userId);
        userMailSingle.setType(0);
        userMailSingle.setTitle("有未领取的道具请查收");
        userMailSingle.setContent("请尽快整理背包，奖励有效时间15天");
        Map retMap = new HashMap();
        retMap.put("prop", map);
        userMailSingle.setAccessory(JSONObject.toJSONString(retMap));
        userMailSingleService.addUserMailSingle(userMailSingle);
        return true;
    }


    @Override
    public UserGoods getUserGoodsById(String id) {
        return userGoodsMapper.selectByPrimaryKey(id);
    }


    /**
     * 初始化用户背包信息
     */
    @Transactional
    @Override
    public void initUserGoods(String userId) {

        UserGoods goodsMoney = new UserGoods();//金币
        goodsMoney.setId(UUIDUtil.generateUUID());
        goodsMoney.setUserId(userId);
        goodsMoney.setGoodsId(GoodsConstant.GOODS_MONEY);
        goodsMoney.setAmount(1500);
        goodsMoney.setCreateDate(new Date());
        userGoodsMapper.insertSelective(goodsMoney);

        UserGoods goodsWood = new UserGoods();//钻石
        goodsWood.setId(UUIDUtil.generateUUID());
        goodsWood.setUserId(userId);
        goodsWood.setGoodsId(GoodsConstant.GOODS_JEWEL);
        goodsWood.setAmount(20);
        goodsWood.setCreateDate(new Date());
        userGoodsMapper.insertSelective(goodsWood);

        UserGoods goodsVigour = new UserGoods();//精力
        goodsVigour.setId(UUIDUtil.generateUUID());
        goodsVigour.setUserId(userId);
        goodsVigour.setGoodsId(GoodsConstant.GOODS_VIGOUR);
        goodsVigour.setAmount(10);
        goodsVigour.setCreateDate(new Date());
        userGoodsMapper.insertSelective(goodsVigour);

    }


    //@CachePut(key="p0.userId+'-'+p0.goodsId")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGoods updateByUserId(UserGoods userGoods) {
        userGoods.setUpdateDate(new Date());
        userGoodsMapper.updateByPrimaryKeySelective(userGoods);
        return userGoods;
    }

    //@CacheEvict(key="#p0+'-'+#p1")
    @Override
    public void deleteByUserIdAndGoodsId(String userId, int goodsId) {
        UserGoodsExample example = new UserGoodsExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId + "");
        userGoodsMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public UserGoods deleteWeddingRingByUserIdAndGoodsId(String userId) throws Exception {
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userId);
        String weddingRing = (String) CommonUtil.getObjectElement(userAdornEquip, AdornEquipEnum.WEDDING_RING.getWearPosition());
        UserGoods userGoods = this.getUserGoodsById(weddingRing);
        if (userGoods == null) {
            return null;
        }
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return null;
            }
            if (equipmentInfo.getWearPosition() != AdornEquipEnum.WEDDING_RING.getId()) {
                return null;
            }
            if (equipmentInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_NO) {
                equipmentInfoService.deleteByPrimaryKey(equipmentInfo.getIntensifyId());
                userWeddingRingLevelMapper.deleteByPrimaryKey(userId);
            }

            UserGoodsExample example = new UserGoodsExample();
            example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(userGoods.getGoodsId());
            userGoodsMapper.deleteByExample(example);
            if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                String element = (String) CommonUtil.getObjectElement(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()));
                if (!userGoods.getId().equals(element)) {
                    throw new Exception();
                }
                CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()), new String());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() - equipmentInfo.getFightingCapacity());
                userAdornEquip.setVitality(userAdornEquip.getVitality() - equipmentInfo.getVitality());
                userAdornEquip.setAttack(userAdornEquip.getAttack() - equipmentInfo.getAttack());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() - equipmentInfo.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() - equipmentInfo.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() - equipmentInfo.getMagdef());
                userAdornEquip.setCrit(userAdornEquip.getCrit() - equipmentInfo.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() - equipmentInfo.getDodge());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() - equipmentInfo.getHitRate());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() - equipmentInfo.getDefenseCrit());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }
            return userGoods;
        } else {
            return null;
        }
    }
}
