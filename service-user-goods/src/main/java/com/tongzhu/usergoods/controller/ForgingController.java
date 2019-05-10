package com.tongzhu.usergoods.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.enums.EnchantlvlEnum;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.po.CompoundPO;
import com.tongzhu.usergoods.po.ForgePO;
import com.tongzhu.usergoods.redis.RedisService;
import com.tongzhu.usergoods.service.*;
import com.tongzhu.util.CommonUtil;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Administrator on 2018/11/16 0016.
 */
@Api(value = "锻造controller", tags = {"锻造API"})
@RestController
@RequestMapping(value = "/forging")
public class ForgingController {


    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CompoundInfoService compoundInfoService;

    @Autowired
    private ForgeInfoService forgeInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "合成物品", notes = "请求参数说明 [userId 用户id] [basicsId 被合成物品物品表id] [amount 被合成物品数量] [compositeId 合成物物品id]  [consume 消耗品id][consumeAmount 消耗品数量] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[consumables 消耗品详情] [basics 被合成物品详情] [composite 合成品数量详情]")})
    @PostMapping(value = "/compose", produces = "application/json")
    public BaseReturn compose(@RequestBody CompoundPO compoundPO) {

        if (StringUtils.isEmpty(compoundPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(compoundPO.getBasicsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(compoundPO.getCompositeId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        UserGoods basicsUserGoods = userGoodsService.getByUserIdAndGoodsId(compoundPO.getUserId(), compoundPO.getBasicsId());

        if (basicsUserGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "合成物品不存在");
        }
        if (basicsUserGoods.getAmount() < compoundPO.getAmount()) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "合成物品数量不足");
        }

        CompoundInfo compoundInfo = compoundInfoService.getCompoundInfo(basicsUserGoods.getGoodsId()
                , compoundPO.getCompositeId());
        if (compoundInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "合成配置不存在!");
        }
//        if (compoundInfo.getBasicItemsAmount().intValue() != compoundPO.getAmount().intValue()) {
//            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "合成配置错误!");
//        }

        if (!compoundPO.getConsume().equals(compoundInfo.getCost())) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
        }


        Integer basicItemsAmount = compoundInfo.getBasicItemsAmount();
        Integer compositeAmount = compoundInfo.getCompositeAmount();
        Integer costAmount = compoundInfo.getCostAmount();


        int basicItem = compoundPO.getAmount() - compoundPO.getAmount() % basicItemsAmount;

        int composite = compoundPO.getAmount() / basicItemsAmount * compositeAmount;

        int costSum = compoundPO.getAmount() / basicItemsAmount * costAmount;


        if (compoundPO.getConsumeAmount().intValue() != costSum) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
        }

        UserGoods userGoodsCost = userGoodsService.getByUserIdAndGoodsId(compoundPO.getUserId(), compoundInfo.getCost());
        if (userGoodsCost == null || userGoodsCost.getAmount() < costSum) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoodsCost.getGoodsId());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品配置错误");
            }
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, propInfo.getName() + "不足");
        }

        Map retMap = new HashMap();
        if (basicsUserGoods.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(basicsUserGoods.getGoodsId());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该物品不存在");
            }
            if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "物品已经过期!");
            }
            UserGoods userGoods = userGoodsService.subUserGoodsById(basicsUserGoods.getId(), basicItem,true);
            Map map = new HashMap();
            map.put("id", basicsUserGoods.getGoodsId());
            map.put("goodsId", basicsUserGoods.getGoodsId());
            map.put("type", basicsUserGoods.getType());
            map.put("amount", userGoods.getAmount());
            retMap.put("basics", map);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "合成物品类型错误!");
        }

        if (compoundInfo.getCompositeType() == PacksackConstant.GOODS_TYPE_PROP) {
            UserGoods userGoods = userGoodsService.addUserGoodsSingle(basicsUserGoods.getUserId(), compoundInfo.getComposite(), composite);
            Map map = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, userGoods.getAmount(), null);
            retMap.put("composite", map);
        } else if (compoundInfo.getCompositeType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfoByIdAndOriginal(compoundInfo.getComposite(), PacksackConstant.GOODS_ORIGINAL_YES);
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "合成的物品不存在");
            }
            UserGoods userGoods = userGoodsService.addEquipAndWeapon(compoundPO.getUserId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, equipmentInfo.getId());
            if (userGoods != null) {
                Map map = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, userGoods.getAmount(), equipmentInfo.getEnchantlvl());
                retMap.put("composite", map);
            }

        } else {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal(compoundInfo.getComposite(), PacksackConstant.GOODS_ORIGINAL_YES);
            if (arsenalInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "合成的物品不存在");
            }
            UserGoods userGoods = userGoodsService.addEquipAndWeapon(compoundPO.getUserId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, arsenalInfo.getId());
            if (userGoods != null) {
                Map map = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, userGoods.getAmount(), arsenalInfo.getEnchantlvl());
                retMap.put("composite", map);
            }
        }
        UserGoods userGoods = userGoodsService.subUserGoodsSingle(userGoodsCost.getUserId(), userGoodsCost.getGoodsId(), costSum,true);
        Map map = ObjectUtil.getGoodsMap(userGoodsCost.getGoodsId(), userGoodsCost.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, userGoods.getAmount(), null);
        retMap.put("consumables", map);
        return new BaseReturn("合成成功!", retMap);
    }


    @ApiOperation(value = "强化", notes = "请求参数说明 [userId 用户id] [strongFossil 强化石id] [id 强化的id道具] [consume 消耗品数量] [safeguard 保护石id 可以不传]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[goods 强化道具详情][safeguard 保护石详情][strongFossil 强化石详情][consumables 消耗品详情]")})
    @PostMapping(value = "/strength", produces = "application/json")
    public BaseReturn strength(@RequestBody ForgePO forgePO) {
        if (StringUtils.isEmpty(forgePO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(forgePO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "道具id为空");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgePO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包中不存在该物品");
        }

        Integer enchantlvl = 0;
        EquipmentInfo equipmentInfo = null;
        ArsenalInfo arsenalInfo = null;
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在");
            }
            if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已过期");
            }
            if (equipmentInfo.getWearPosition() == AdornEquipEnum.WEDDING_RING.getId() || equipmentInfo.getWearPosition() == AdornEquipEnum.FASHION.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不能强化");
            }
            enchantlvl = equipmentInfo.getEnchantlvl();
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_WEAPON) {
            arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            if (arsenalInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器不存在");
            } else if (arsenalInfo.getExpirationTime() != null && !DateUtil.compareDate(arsenalInfo.getExpirationTime())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器已过期");
            }
            enchantlvl = arsenalInfo.getEnchantlvl();
        }

        ForgeInfo forgeInfo = forgeInfoService.getForgeInfoByEnchantlvl(enchantlvl);
        if (forgeInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化配置不存在");
        }

        if (forgeInfo.getConsumeAmount() != null && forgeInfo.getConsumeAmount().intValue() != forgePO.getConsume().intValue()) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗物品计算错误");
        }
        if (forgePO.getSafeguard() != null && forgeInfo.getSafeguard() != null && !forgeInfo.getSafeguard().equals(forgePO.getSafeguard())) {
            PropInfo propInfo = propInfoService.getPropInfo(forgeInfo.getSafeguard());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "保护石配置错误");
            }
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化需要:" + propInfo.getName());
        }
        if (!forgeInfo.getStrongFossil().equals(forgePO.getStrongFossil())) {
            PropInfo propInfo = propInfoService.getPropInfo(forgeInfo.getStrongFossil());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化石配置错误");
            }
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化需要:" + propInfo.getName());
        }
        UserGoods userGoodsSafeguard = null;
        if (forgePO.getSafeguard() != null && forgeInfo.getSafeguard() != null) {
            userGoodsSafeguard = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgeInfo.getSafeguard());
            if (userGoodsSafeguard == null || userGoodsSafeguard.getAmount().intValue() < forgeInfo.getSafeguardNumber().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "保护石数量不足");
            }
        }
        UserGoods userGoodsStrongFossil = null;
        if (forgeInfo.getStrongFossil() != null) {
            userGoodsStrongFossil = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgeInfo.getStrongFossil());
            if (userGoodsStrongFossil == null || userGoodsStrongFossil.getAmount().intValue() < forgeInfo.getStrongFossilNumber().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化石数量不足");
            }
        }
        UserGoods userGoodsConsume = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgeInfo.getConsume());
        if (userGoodsConsume == null || userGoodsConsume.getAmount().intValue() < forgeInfo.getConsumeAmount().intValue()) {
            PropInfo propInfo = propInfoService.getPropInfo(forgeInfo.getConsume());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "强化配置错误");
            }
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, propInfo.getName() + "数量不足");
        }
        // 更新任务
        taskService.taskProgress(PacksackConstant.TASK_TYPE, 1, userGoods.getUserId());

        Map retMap = new HashMap();
        double ran = RandomUtils.nextDouble(0, 100);
        if (forgeInfo.getSuccessRate() <= ran) {
            if (forgePO.getSafeguard() == null) {
                ForgeInfo forgeInfoFailure = forgeInfoService.getForgeInfoByEnchantlvl(forgeInfo.getFailure() - 1);
                //  没有保护石
                if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
                    EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
                    EquipmentInfo equipmentInfoOriginal = equipmentInfoService.getEquipmentInfoByIdAndOriginal(equipmentInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

                    equipmentInfo.setVitalityIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getVitality()));
                    equipmentInfo.setAttackIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getAttack()));
                    equipmentInfo.setSpellAttacksIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getSpellAttacks()));
                    equipmentInfo.setPdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getPdef()));
                    equipmentInfo.setMagdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getMagdef()));
                    equipmentInfo.setCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getCrit()));
                    equipmentInfo.setDodgeIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getDodge()));
                    equipmentInfo.setHitRateIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getHitRate()));
                    equipmentInfo.setDefenseCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * equipmentInfoOriginal.getDefenseCrit()));

                    equipmentInfo.setEnchantlvl(forgeInfo.getFailure());

                    equipmentInfo.setVitality(equipmentInfo.getVitalityIntensify() + equipmentInfo.getVitality() - equipment.getVitalityIntensify());
                    equipmentInfo.setAttack(equipmentInfo.getAttackIntensify() + equipmentInfo.getAttack() - equipment.getAttackIntensify());
                    equipmentInfo.setSpellAttacks(equipmentInfo.getSpellAttacksIntensify() + equipmentInfo.getSpellAttacks() - equipment.getSpellAttacksIntensify());
                    equipmentInfo.setPdef(equipmentInfo.getPdefIntensify() + equipmentInfo.getPdef() - equipment.getPdefIntensify());
                    equipmentInfo.setMagdef(equipmentInfo.getMagdefIntensify() + equipmentInfo.getMagdef() - equipment.getMagdefIntensify());
                    equipmentInfo.setCrit(equipmentInfo.getCritIntensify() + equipmentInfo.getCrit() - equipment.getCritIntensify());
                    equipmentInfo.setDodge(equipmentInfo.getDodgeIntensify() + equipmentInfo.getDodge() - equipment.getDodgeIntensify());
                    equipmentInfo.setHitRate(equipmentInfo.getHitRateIntensify() + equipmentInfo.getHitRate() - equipment.getHitRateIntensify());
                    equipmentInfo.setDefenseCrit(equipmentInfo.getDefenseCritIntensify() + equipmentInfo.getDefenseCrit() - equipment.getDefenseCritIntensify());
                    equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                            equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                            + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
                    equipmentInfo.setUpdateDate(new Date());
                    equipmentInfoService.update(equipmentInfo);
                    Map map = new HashMap();
                    map.put("id", equipmentInfo.getIntensifyId());
                    map.put("goodsId", equipmentInfo.getId());
                    map.put("type", userGoods.getType());
                    map.put("amount", userGoods.getAmount());
                    map.put("enchantlvl", equipmentInfo.getEnchantlvl());
                    retMap.put("goods", map);
                    if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
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
                        userAdornEquipService.updateUserAddornEquip(userAdornEquip);
                    }
                } else {
                    ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
                    ArsenalInfo arsenalInfoOriginal = arsenalInfoService.getArsenalInfoByIdAndOriginal(arsenalInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);


                    arsenalInfo.setVitalityIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getVitality()));
                    arsenalInfo.setAttackIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getAttack()));
                    arsenalInfo.setSpellAttacksIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getSpellAttacks()));
                    arsenalInfo.setPdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getPdef()));
                    arsenalInfo.setMagdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getMagdef()));
                    arsenalInfo.setCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getCrit()));
                    arsenalInfo.setDodgeIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getDodge()));
                    arsenalInfo.setHitRateIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getHitRate()));
                    arsenalInfo.setDefenseCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * arsenalInfoOriginal.getDefenseCrit()));

                    arsenalInfo.setEnchantlvl(forgeInfo.getFailure());

                    arsenalInfo.setVitality(arsenalInfo.getVitalityIntensify() + arsenalInfo.getVitality() - arsenal.getVitalityIntensify());
                    arsenalInfo.setAttack(arsenalInfo.getAttackIntensify() + arsenalInfo.getAttack() - arsenal.getAttackIntensify());
                    arsenalInfo.setSpellAttacks(arsenalInfo.getSpellAttacksIntensify() + arsenalInfo.getSpellAttacks() - arsenal.getSpellAttacksIntensify());
                    arsenalInfo.setPdef(arsenalInfo.getPdefIntensify() + arsenalInfo.getPdef() - arsenal.getPdefIntensify());
                    arsenalInfo.setMagdef(arsenalInfo.getMagdefIntensify() + arsenalInfo.getMagdef() - arsenal.getMagdefIntensify());
                    arsenalInfo.setCrit(arsenalInfo.getCritIntensify() + arsenalInfo.getCrit() - arsenal.getCritIntensify());
                    arsenalInfo.setDodge(arsenalInfo.getDodgeIntensify() + arsenalInfo.getDodge() - arsenal.getDodgeIntensify());
                    arsenalInfo.setHitRate(arsenalInfo.getHitRateIntensify() + arsenalInfo.getHitRate() - arsenal.getHitRateIntensify());
                    arsenalInfo.setDefenseCrit(arsenalInfo.getDefenseCritIntensify() + arsenalInfo.getDefenseCrit() - arsenal.getDefenseCritIntensify());
                    arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                            arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                            + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
                    arsenalInfo.setUpdateDate(new Date());

                    arsenalInfoService.update(arsenalInfo);
                    Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), arsenalInfo.getEnchantlvl());
                    goodsMap.put("enchantlvl", arsenalInfo.getEnchantlvl());
                    retMap.put("goods", goodsMap);

                    if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                        userAdornEquip.setWeapon(userGoods.getId());
                        userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfo.getFightingCapacity() - arsenal.getFightingCapacity());
                        userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfo.getVitality() - arsenal.getVitality());
                        userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfo.getAttack() - arsenal.getAttack());
                        userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfo.getSpellAttacks() - arsenal.getSpellAttacks());
                        userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfo.getPdef() - arsenal.getPdef());
                        userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfo.getMagdef() - arsenal.getMagdef());
                        userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfo.getCrit() - arsenal.getCrit());
                        userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfo.getDodge() - arsenal.getDodge());
                        userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfo.getHitRate() - arsenal.getHitRate());
                        userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfo.getDefenseCrit() - arsenal.getDefenseCrit());
                        userAdornEquipService.updateUserAddornEquip(userAdornEquip);
                    }
                }
            }
            // 减去保护石
            if (userGoodsSafeguard != null) {
                UserGoods userGoodsRet = userGoodsService.subUserGoodsById(userGoodsSafeguard.getId(), forgeInfo.getSafeguardNumber(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsSafeguard.getGoodsId(), userGoodsSafeguard.getGoodsId(), userGoodsSafeguard.getType(), userGoodsRet.getAmount(), null);
                retMap.put("safeguard", goodsMap);
            }
            // 减去强化石
            if (userGoodsStrongFossil != null) {
                UserGoods userGoodsRet = userGoodsService.subUserGoodsById(userGoodsStrongFossil.getId(), forgeInfo.getStrongFossilNumber(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsStrongFossil.getGoodsId(), userGoodsStrongFossil.getGoodsId(), userGoodsStrongFossil.getType(), userGoodsRet.getAmount(), null);
                retMap.put("strongFossil", goodsMap);
            }
            // 减去金币
            if (userGoodsConsume != null) {
                UserGoods consume = userGoodsService.subUserGoodsById(userGoodsConsume.getId(), forgeInfo.getConsumeAmount() - forgeInfo.getRefundConsume(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsConsume.getGoodsId(), userGoodsConsume.getGoodsId(), userGoodsConsume.getType(), consume.getAmount(), null);
                retMap.put("consumables", goodsMap);
            }
            retMap.put("result", 1);
            return new BaseReturn("强化失败", retMap);
        } else {
            if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
                EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
                EquipmentInfo equipmentInfoOriginal = equipmentInfoService.getEquipmentInfoByIdAndOriginal(equipmentInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

                equipmentInfo.setVitalityIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getVitality()));
                equipmentInfo.setAttackIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getAttack()));
                equipmentInfo.setSpellAttacksIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getSpellAttacks()));
                equipmentInfo.setPdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getPdef()));
                equipmentInfo.setMagdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getMagdef()));
                equipmentInfo.setCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getCrit()));
                equipmentInfo.setDodgeIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getDodge()));
                equipmentInfo.setHitRateIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getHitRate()));
                equipmentInfo.setDefenseCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginal.getDefenseCrit()));

                equipmentInfo.setEnchantlvl(forgeInfo.getSucceed());

                equipmentInfo.setVitality(equipmentInfo.getVitalityIntensify() + equipmentInfo.getVitality() - equipment.getVitalityIntensify());
                equipmentInfo.setAttack(equipmentInfo.getAttackIntensify() + equipmentInfo.getAttack() - equipment.getAttackIntensify());
                equipmentInfo.setSpellAttacks(equipmentInfo.getSpellAttacksIntensify() + equipmentInfo.getSpellAttacks() - equipment.getSpellAttacksIntensify());
                equipmentInfo.setPdef(equipmentInfo.getPdefIntensify() + equipmentInfo.getPdef() - equipment.getPdefIntensify());
                equipmentInfo.setMagdef(equipmentInfo.getMagdefIntensify() + equipmentInfo.getMagdef() - equipment.getMagdefIntensify());
                equipmentInfo.setCrit(equipmentInfo.getCritIntensify() + equipmentInfo.getCrit() - equipment.getCritIntensify());
                equipmentInfo.setDodge(equipmentInfo.getDodgeIntensify() + equipmentInfo.getDodge() - equipment.getDodgeIntensify());
                equipmentInfo.setHitRate(equipmentInfo.getHitRateIntensify() + equipmentInfo.getHitRate() - equipment.getHitRateIntensify());
                equipmentInfo.setDefenseCrit(equipmentInfo.getDefenseCritIntensify() + equipmentInfo.getDefenseCrit() - equipment.getDefenseCritIntensify());
                equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                        equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                        + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
                equipmentInfo.setUpdateDate(new Date());

                if (equipmentInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) { // 原型武器
                    equipmentInfo.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                    equipmentInfo.setIntensifyId(UUIDUtil.generateUUID());
                    equipmentInfoService.insert(equipmentInfo);
                    userGoods.setGoodsId(equipmentInfo.getIntensifyId());
                    userGoods.setUpdateDate(new Date());
                    userGoodsService.updateByUserId(userGoods);
                } else {
                    equipmentInfoService.update(equipmentInfo);
                }
                Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), userGoods.getType(), userGoods.getAmount(), equipmentInfo.getEnchantlvl());
                goodsMap.put("enchantlvl", equipmentInfo.getEnchantlvl());
                retMap.put("goods", goodsMap);

                if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                    UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                    userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + equipmentInfo.getFightingCapacity() - equipment.getFightingCapacity());
                    CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()), userGoods.getId());
                    userAdornEquip.setVitality(userAdornEquip.getVitality() + equipmentInfo.getVitality() - equipment.getVitality());
                    userAdornEquip.setAttack(userAdornEquip.getAttack() + equipmentInfo.getAttack() - equipment.getAttack());
                    userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + equipmentInfo.getSpellAttacks() - equipment.getSpellAttacks());
                    userAdornEquip.setPdef(userAdornEquip.getPdef() + equipmentInfo.getPdef() - equipment.getPdef());
                    userAdornEquip.setMagdef(userAdornEquip.getMagdef() + equipmentInfo.getMagdef() - equipment.getMagdef());
                    userAdornEquip.setCrit(userAdornEquip.getCrit() + equipmentInfo.getCrit() - equipment.getCrit());
                    userAdornEquip.setDodge(userAdornEquip.getDodge() + equipmentInfo.getDodge() - equipment.getDodge());
                    userAdornEquip.setHitRate(userAdornEquip.getHitRate() + equipmentInfo.getHitRate() - equipment.getHitRate());
                    userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + equipmentInfo.getDefenseCrit() - equipment.getDefenseCrit());
                    userAdornEquipService.updateUserAddornEquip(userAdornEquip);
                }
            } else {

                ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
                ArsenalInfo arsenalInfoOriginal = arsenalInfoService.getArsenalInfoByIdAndOriginal(arsenalInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);
                arsenalInfo.setVitalityIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getVitality()));
                arsenalInfo.setAttackIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getAttack()));
                arsenalInfo.setSpellAttacksIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getSpellAttacks()));
                arsenalInfo.setPdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getPdef()));
                arsenalInfo.setMagdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getMagdef()));
                arsenalInfo.setCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getCrit()));
                arsenalInfo.setDodgeIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getDodge()));
                arsenalInfo.setHitRateIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getHitRate()));
                arsenalInfo.setDefenseCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginal.getDefenseCrit()));

                arsenalInfo.setEnchantlvl(forgeInfo.getSucceed());

                arsenalInfo.setVitality(arsenalInfo.getVitalityIntensify() + arsenalInfo.getVitality() - arsenal.getVitalityIntensify());
                arsenalInfo.setAttack(arsenalInfo.getAttackIntensify() + arsenalInfo.getAttack() - arsenal.getAttackIntensify());
                arsenalInfo.setSpellAttacks(arsenalInfo.getSpellAttacksIntensify() + arsenalInfo.getSpellAttacks() - arsenal.getSpellAttacksIntensify());
                arsenalInfo.setPdef(arsenalInfo.getPdefIntensify() + arsenalInfo.getPdef() - arsenal.getPdefIntensify());
                arsenalInfo.setMagdef(arsenalInfo.getMagdefIntensify() + arsenalInfo.getMagdef() - arsenal.getMagdefIntensify());
                arsenalInfo.setCrit(arsenalInfo.getCritIntensify() + arsenalInfo.getCrit() - arsenal.getCritIntensify());
                arsenalInfo.setDodge(arsenalInfo.getDodgeIntensify() + arsenalInfo.getDodge() - arsenal.getDodgeIntensify());
                arsenalInfo.setHitRate(arsenalInfo.getHitRateIntensify() + arsenalInfo.getHitRate() - arsenal.getHitRateIntensify());
                arsenalInfo.setDefenseCrit(arsenalInfo.getDefenseCritIntensify() + arsenalInfo.getDefenseCrit() - arsenal.getDefenseCritIntensify());
                arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                        arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                        + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
                arsenalInfo.setUpdateDate(new Date());

                if (arsenalInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) { // 原型武器
                    arsenalInfo.setIntensifyId(UUIDUtil.generateUUID());
                    arsenalInfo.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                    arsenalInfoService.insert(arsenalInfo);
                    userGoods.setUpdateDate(new Date());
                    userGoods.setGoodsId(arsenalInfo.getIntensifyId());
                    userGoodsService.updateByUserId(userGoods);
                } else {
                    arsenalInfoService.update(arsenalInfo);
                }
                Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), arsenalInfo.getEnchantlvl());
                goodsMap.put("enchantlvl", arsenalInfo.getEnchantlvl());
                retMap.put("goods", goodsMap);

                if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                    UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                    userAdornEquip.setWeapon(userGoods.getId());
                    userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfo.getFightingCapacity() - arsenal.getFightingCapacity());
                    userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfo.getVitality() - arsenal.getVitality());
                    userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfo.getAttack() - arsenal.getAttack());
                    userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfo.getSpellAttacks() - arsenal.getSpellAttacks());
                    userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfo.getPdef() - arsenal.getPdef());
                    userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfo.getMagdef() - arsenal.getMagdef());
                    userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfo.getCrit() - arsenal.getCrit());
                    userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfo.getHitRate() - arsenal.getHitRate());
                    userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfo.getDodge() - arsenal.getDodge());
                    userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfo.getDefenseCrit() - arsenal.getDefenseCrit());
                    userAdornEquipService.updateUserAddornEquip(userAdornEquip);
                }
            }
            // 减去保护石
            if (userGoodsSafeguard != null) {
                UserGoods userGoodsRet = userGoodsService.subUserGoodsById(userGoodsSafeguard.getId(), forgeInfo.getSafeguardNumber(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsSafeguard.getGoodsId(), userGoodsSafeguard.getGoodsId(), userGoodsSafeguard.getType(), userGoodsRet.getAmount(), null);
                retMap.put("safeguard", goodsMap);
            }
            // 减去强化石
            if (userGoodsStrongFossil != null) {
                UserGoods userGoodsRet = userGoodsService.subUserGoodsById(userGoodsStrongFossil.getId(), forgeInfo.getStrongFossilNumber(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsStrongFossil.getGoodsId(), userGoodsStrongFossil.getGoodsId(), userGoodsStrongFossil.getType(), userGoodsRet.getAmount(), null);
                retMap.put("strongFossil", goodsMap);
            }
            // 减去金币
            if (userGoodsConsume != null) {
                UserGoods consume = userGoodsService.subUserGoodsById(userGoodsConsume.getId(), forgeInfo.getConsumeAmount(),true);
                Map goodsMap = ObjectUtil.getGoodsMap(userGoodsConsume.getGoodsId(), userGoodsConsume.getGoodsId(), userGoodsConsume.getType(), consume.getAmount(), null);
                retMap.put("consumables", goodsMap);
            }

            Integer minEnchantlvl = userGoodsService.getMinEnchantlvl(userGoods.getUserId());
            if (minEnchantlvl != null && EnchantlvlEnum.getTaskId(minEnchantlvl) != null) {
                taskService.taskBranchFinish(EnchantlvlEnum.getTaskId(minEnchantlvl), userGoods.getUserId());
            }
            retMap.put("result", 2);
            return new BaseReturn("强化成功", retMap);
        }
    }


    @ApiOperation(value = "转移", notes = "请求参数说明 [userId 用户id] [id 被转移的道具] [transferId 目标道具] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[basics 被转移道具详情][transfer 目标道具详情][consumables 消耗品详情]")})
    @PostMapping(value = "/transfer", produces = "application/json")
    public BaseReturn transfer(@RequestBody ForgePO forgePO) {
        if (StringUtils.isEmpty(forgePO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "被转移装备id不能为空!");
        }
        if (StringUtils.isEmpty(forgePO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(forgePO.getTransferId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "目标装备id不能为空!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgePO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "被转移道具不存在");
        }
        UserGoods userGoodsTransfer = userGoodsService.getByUserIdAndGoodsId(forgePO.getUserId(), forgePO.getTransferId());
        if (userGoodsTransfer == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "目标道具不存在");
        }
        if (!userGoods.getType().equals(userGoodsTransfer.getType())) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "类型不一致，不能转移");
        }

        UserGoods byUserIdAndGoodsId = userGoodsService.getByUserIdAndGoodsId(userGoods.getUserId(), GoodsConstant.GOODS_MONEY);
        if (byUserIdAndGoodsId.getAmount() < 2000) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "金币不足，转移失败");
        }

        Map retMap = new HashMap();
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipmentInfoTransfer = equipmentInfoService.getEquipmentInfo(userGoodsTransfer.getGoodsId());
            if (equipmentInfo == null || equipmentInfoTransfer == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包物品异常");
            }
            if (equipmentInfo.getEnchantlvl() < equipmentInfoTransfer.getEnchantlvl()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "被转移的装备不能低于目标装备");
            }

            if (equipmentInfo.getEnchantlvl() <= 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "被转移的装备等级过低");
            }

            EquipmentInfo equipmentTransfeer = equipmentInfoService.getEquipmentInfo(userGoodsTransfer.getGoodsId());
            EquipmentInfo equipmentInfoOriginalTransfeer = equipmentInfoService.getEquipmentInfoByIdAndOriginal(equipmentInfoTransfer.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

            ForgeInfo forgeInfo = forgeInfoService.getForgeInfoByEnchantlvl(equipmentInfo.getEnchantlvl() - 1);
            equipmentInfoTransfer.setVitalityIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getVitality()));
            equipmentInfoTransfer.setAttackIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getAttack()));
            equipmentInfoTransfer.setSpellAttacksIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getSpellAttacks()));
            equipmentInfoTransfer.setPdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getPdef()));
            equipmentInfoTransfer.setMagdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getMagdef()));
            equipmentInfoTransfer.setCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getCrit()));
            equipmentInfoTransfer.setDodgeIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getDodge()));
            equipmentInfoTransfer.setHitRateIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getHitRate()));
            equipmentInfoTransfer.setDefenseCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * equipmentInfoOriginalTransfeer.getDefenseCrit()));

            equipmentInfoTransfer.setEnchantlvl(equipmentInfo.getEnchantlvl());
            equipmentInfoTransfer.setVitality(equipmentInfoTransfer.getVitalityIntensify() + equipmentInfoTransfer.getVitality() - equipmentTransfeer.getVitalityIntensify());
            equipmentInfoTransfer.setAttack(equipmentInfoTransfer.getAttackIntensify() + equipmentInfoTransfer.getAttack() - equipmentTransfeer.getAttackIntensify());
            equipmentInfoTransfer.setSpellAttacks(equipmentInfoTransfer.getSpellAttacksIntensify() + equipmentInfoTransfer.getSpellAttacks() - equipmentTransfeer.getSpellAttacksIntensify());
            equipmentInfoTransfer.setPdef(equipmentInfoTransfer.getPdefIntensify() + equipmentInfoTransfer.getPdef() - equipmentTransfeer.getPdefIntensify());
            equipmentInfoTransfer.setMagdef(equipmentInfoTransfer.getMagdefIntensify() + equipmentInfoTransfer.getMagdef() - equipmentTransfeer.getMagdefIntensify());
            equipmentInfoTransfer.setCrit(equipmentInfoTransfer.getCritIntensify() + equipmentInfoTransfer.getCrit() - equipmentTransfeer.getCritIntensify());
            equipmentInfoTransfer.setDodge(equipmentInfoTransfer.getDodgeIntensify() + equipmentInfoTransfer.getDodge() - equipmentTransfeer.getDodgeIntensify());
            equipmentInfoTransfer.setHitRate(equipmentInfoTransfer.getHitRateIntensify() + equipmentInfoTransfer.getHitRate() - equipmentTransfeer.getHitRateIntensify());
            equipmentInfoTransfer.setDefenseCrit(equipmentInfoTransfer.getDefenseCritIntensify() + equipmentInfoTransfer.getDefenseCrit() - equipmentTransfeer.getDefenseCritIntensify());
            equipmentInfoTransfer.setFightingCapacity((double) Math.round(equipmentInfoTransfer.getVitality() * 0.2 + equipmentInfoTransfer.getAttack() + equipmentInfoTransfer.getSpellAttacks() +
                    equipmentInfoTransfer.getPdef() + equipmentInfoTransfer.getMagdef() + equipmentInfoTransfer.getCrit() * 1.5
                    + equipmentInfoTransfer.getDodge() * 1.5 + equipmentInfoTransfer.getHitRate() * 1.5 + equipmentInfoTransfer.getDefenseCrit() * 1.5));
            equipmentInfoTransfer.setUpdateDate(new Date());

            if (equipmentInfoTransfer.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) {
                equipmentInfoTransfer.setIntensifyId(UUIDUtil.generateUUID());
                equipmentInfoTransfer.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                equipmentInfoService.insert(equipmentInfoTransfer);
                userGoodsTransfer.setGoodsId(equipmentInfoTransfer.getIntensifyId());
                userGoodsTransfer.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoodsTransfer);
            } else {
                equipmentInfoService.update(equipmentInfoTransfer);
            }
            Map goodsMapTransfer = ObjectUtil.getGoodsMap(equipmentInfoTransfer.getIntensifyId(), equipmentInfoTransfer.getId(), userGoodsTransfer.getType(), userGoodsTransfer.getAmount(), equipmentInfoTransfer.getEnchantlvl());
            goodsMapTransfer.put("enchantlvl", equipmentInfoTransfer.getEnchantlvl());
            retMap.put("transfer", goodsMapTransfer);


            if (userGoodsTransfer.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoodsTransfer.getUserId());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + equipmentInfoTransfer.getFightingCapacity() - equipmentTransfeer.getFightingCapacity());
                userAdornEquip.setVitality(userAdornEquip.getVitality() + equipmentInfoTransfer.getVitality() - equipmentTransfeer.getVitality());
                CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfoTransfer.getWearPosition()), userGoodsTransfer.getId());
                userAdornEquip.setAttack(userAdornEquip.getAttack() + equipmentInfoTransfer.getAttack() - equipmentTransfeer.getAttack());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + equipmentInfoTransfer.getSpellAttacks() - equipmentTransfeer.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() + equipmentInfoTransfer.getPdef() - equipmentTransfeer.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() + equipmentInfoTransfer.getMagdef() - equipmentTransfeer.getMagdef());
                userAdornEquip.setCrit(userAdornEquip.getCrit() + equipmentInfoTransfer.getCrit() - equipmentTransfeer.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() + equipmentInfoTransfer.getDodge() - equipmentTransfeer.getDodge());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() + equipmentInfoTransfer.getHitRate() - equipmentTransfeer.getHitRate());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + equipmentInfoTransfer.getDefenseCrit() - equipmentTransfeer.getDefenseCrit());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }

            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipmentInfoOriginal = equipmentInfoService.getEquipmentInfoByIdAndOriginal(equipmentInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

            equipmentInfo.setEnchantlvl(0);
            equipmentInfo.setVitality(equipmentInfo.getVitality() - equipmentInfo.getVitalityIntensify());
            equipmentInfo.setAttack(equipmentInfo.getAttack() - equipmentInfo.getAttackIntensify());
            equipmentInfo.setSpellAttacks(equipmentInfo.getSpellAttacks() - equipmentInfo.getSpellAttacksIntensify());
            equipmentInfo.setPdef(equipmentInfo.getPdef() - equipmentInfo.getPdefIntensify());
            equipmentInfo.setMagdef(equipmentInfo.getMagdef() - equipmentInfo.getMagdefIntensify());
            equipmentInfo.setCrit(equipmentInfo.getCrit() - equipmentInfo.getCritIntensify());
            equipmentInfo.setDodge(equipmentInfo.getDodge() - equipmentInfo.getDodgeIntensify());
            equipmentInfo.setDefenseCrit(equipmentInfo.getDefenseCrit() - equipmentInfo.getDefenseCritIntensify());
            equipmentInfo.setHitRate(equipmentInfo.getHitRate() - equipmentInfo.getHitRateIntensify());
            equipmentInfo.setVitalityIntensify(equipmentInfoOriginal.getVitalityIntensify());
            equipmentInfo.setAttackIntensify(equipmentInfoOriginal.getAttackIntensify());
            equipmentInfo.setSpellAttacksIntensify(equipmentInfoOriginal.getSpellAttacksIntensify());
            equipmentInfo.setPdefIntensify(equipmentInfoOriginal.getPdefIntensify());
            equipmentInfo.setMagdefIntensify(equipmentInfoOriginal.getMagdefIntensify());
            equipmentInfo.setCritIntensify(equipmentInfoOriginal.getCritIntensify());
            equipmentInfo.setDodgeIntensify(equipmentInfoOriginal.getDodgeIntensify());
            equipmentInfo.setHitRateIntensify(equipmentInfoOriginal.getHitRateIntensify());
            equipmentInfo.setDefenseCritIntensify(equipmentInfoOriginal.getDefenseCritIntensify());
            equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() +
                    equipmentInfo.getSpellAttacks() +
                    equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                    + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
            equipmentInfo.setUpdateDate(new Date());
            equipmentInfoService.update(equipmentInfo);
            Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), userGoods.getType(), userGoods.getAmount(), equipmentInfo.getEnchantlvl());
            goodsMap.put("enchantlvl", equipmentInfo.getEnchantlvl());
            retMap.put("basics", goodsMap);
            if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + equipmentInfo.getFightingCapacity() - equipment.getFightingCapacity());
                userAdornEquip.setVitality(userAdornEquip.getVitality() + equipmentInfo.getVitality() - equipment.getVitality());
                userAdornEquip.setAttack(userAdornEquip.getAttack() + equipmentInfo.getAttack() - equipment.getAttack());
                CommonUtil.voluationBean(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()), userGoods.getId());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + equipmentInfo.getSpellAttacks() - equipment.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() + equipmentInfo.getPdef() - equipment.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() + equipmentInfo.getMagdef() - equipment.getMagdef());
                userAdornEquip.setCrit(userAdornEquip.getCrit() + equipmentInfo.getCrit() - equipment.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() + equipmentInfo.getDodge() - equipment.getDodge());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() + equipmentInfo.getHitRate() - equipment.getHitRate());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + equipmentInfo.getDefenseCrit() - equipment.getDefenseCrit());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }
        } else {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ArsenalInfo arsenalInfoTransfer = arsenalInfoService.getArsenalInfo(userGoodsTransfer.getGoodsId());
            if (arsenalInfo.getEnchantlvl() < arsenalInfoTransfer.getEnchantlvl()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "被转移的装备不能低于目标装备");
            }
            if (arsenalInfo.getEnchantlvl() <= 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "被转移的装备等级过低");
            }

            ArsenalInfo arsenalTransfeer = arsenalInfoService.getArsenalInfo(userGoodsTransfer.getGoodsId());
            ArsenalInfo arsenalInfoOriginalTransfeer = arsenalInfoService.getArsenalInfoByIdAndOriginal(arsenalInfoTransfer.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

            ForgeInfo forgeInfo = forgeInfoService.getForgeInfoByEnchantlvl(arsenalInfo.getEnchantlvl() - 1);
            arsenalInfoTransfer.setVitalityIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getVitality()));
            arsenalInfoTransfer.setAttackIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getAttack()));
            arsenalInfoTransfer.setSpellAttacksIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getSpellAttacks()));
            arsenalInfoTransfer.setPdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getPdef()));
            arsenalInfoTransfer.setMagdefIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getMagdef()));
            arsenalInfoTransfer.setCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getCrit()));
            arsenalInfoTransfer.setDodgeIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getDodge()));
            arsenalInfoTransfer.setHitRateIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getHitRate()));
            arsenalInfoTransfer.setDefenseCritIntensify(Math.ceil(forgeInfo.getReinforceScale() / 100 * arsenalInfoOriginalTransfeer.getDefenseCrit()));

            arsenalInfoTransfer.setEnchantlvl(arsenalInfo.getEnchantlvl());
            arsenalInfoTransfer.setVitality(arsenalInfoTransfer.getVitalityIntensify() + arsenalInfoTransfer.getVitality() - arsenalTransfeer.getVitalityIntensify());
            arsenalInfoTransfer.setAttack(arsenalInfoTransfer.getAttackIntensify() + arsenalInfoTransfer.getAttack() - arsenalTransfeer.getAttackIntensify());
            arsenalInfoTransfer.setSpellAttacks(arsenalInfoTransfer.getSpellAttacksIntensify() + arsenalInfoTransfer.getSpellAttacks() - arsenalTransfeer.getSpellAttacksIntensify());
            arsenalInfoTransfer.setPdef(arsenalInfoTransfer.getPdefIntensify() + arsenalInfoTransfer.getPdef() - arsenalTransfeer.getPdefIntensify());
            arsenalInfoTransfer.setMagdef(arsenalInfoTransfer.getMagdefIntensify() + arsenalInfoTransfer.getMagdef() - arsenalTransfeer.getMagdefIntensify());
            arsenalInfoTransfer.setCrit(arsenalInfoTransfer.getCritIntensify() + arsenalInfoTransfer.getCrit() - arsenalTransfeer.getCritIntensify());
            arsenalInfoTransfer.setDodge(arsenalInfoTransfer.getDodgeIntensify() + arsenalInfoTransfer.getDodge() - arsenalTransfeer.getDodgeIntensify());
            arsenalInfoTransfer.setHitRate(arsenalInfoTransfer.getHitRateIntensify() + arsenalInfoTransfer.getHitRate() - arsenalTransfeer.getHitRateIntensify());
            arsenalInfoTransfer.setDefenseCrit(arsenalInfoTransfer.getDefenseCritIntensify() + arsenalInfoTransfer.getDefenseCrit() - arsenalTransfeer.getDefenseCritIntensify());
            arsenalInfoTransfer.setFightingCapacity((double) Math.round(arsenalInfoTransfer.getVitality() * 0.2 + arsenalInfoTransfer.getAttack() + arsenalInfoTransfer.getSpellAttacks() +
                    arsenalInfoTransfer.getPdef() + arsenalInfoTransfer.getMagdef() + arsenalInfoTransfer.getCrit() * 1.5
                    + arsenalInfoTransfer.getDodge() * 1.5 + arsenalInfoTransfer.getHitRate() * 1.5 + arsenalInfoTransfer.getDefenseCrit() * 1.5));
            arsenalInfoTransfer.setUpdateDate(new Date());

            if (arsenalInfoTransfer.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) {
                arsenalInfoTransfer.setIntensifyId(UUIDUtil.generateUUID());
                arsenalInfoTransfer.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                arsenalInfoService.insert(arsenalInfoTransfer);
                userGoodsTransfer.setGoodsId(arsenalInfoTransfer.getIntensifyId());
                userGoodsTransfer.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoodsTransfer);
            } else {
                arsenalInfoService.update(arsenalInfoTransfer);
            }
            Map goodsMapTransfer = ObjectUtil.getGoodsMap(arsenalInfoTransfer.getIntensifyId(), arsenalInfoTransfer.getId(), userGoodsTransfer.getType(), userGoodsTransfer.getAmount(), arsenalInfoTransfer.getEnchantlvl());
            goodsMapTransfer.put("enchantlvl", arsenalInfoTransfer.getEnchantlvl());
            retMap.put("transfer", goodsMapTransfer);

            if (userGoodsTransfer.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoodsTransfer.getUserId());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfoTransfer.getFightingCapacity() - arsenalTransfeer.getFightingCapacity());
                userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfoTransfer.getVitality() - arsenalTransfeer.getVitality());
                userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfoTransfer.getAttack() - arsenalTransfeer.getAttack());
                userAdornEquip.setWeapon(userGoodsTransfer.getId());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfoTransfer.getSpellAttacks() - arsenalTransfeer.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfoTransfer.getPdef() - arsenalTransfeer.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfoTransfer.getMagdef() - arsenalTransfeer.getMagdef());
                userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfoTransfer.getCrit() - arsenalTransfeer.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfoTransfer.getDodge() - arsenalTransfeer.getDodge());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfoTransfer.getHitRate() - arsenalTransfeer.getHitRate());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfoTransfer.getDefenseCrit() - arsenalTransfeer.getDefenseCrit());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }
            userGoodsService.updateByUserId(userGoodsTransfer);

            ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ArsenalInfo arsenalInfoOriginal = arsenalInfoService.getArsenalInfoByIdAndOriginal(arsenalInfo.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);

            arsenalInfo.setEnchantlvl(PacksackConstant.GOODS_MINLEVEL);
            arsenalInfo.setVitality(arsenalInfo.getVitality() - arsenalInfo.getVitalityIntensify());
            arsenalInfo.setAttack(arsenalInfo.getAttack() - arsenalInfo.getAttackIntensify());
            arsenalInfo.setSpellAttacks(arsenalInfo.getSpellAttacks() - arsenalInfo.getSpellAttacksIntensify());
            arsenalInfo.setPdef(arsenalInfo.getPdef() - arsenalInfo.getPdefIntensify());
            arsenalInfo.setMagdef(arsenalInfo.getMagdef() - arsenalInfo.getMagdefIntensify());
            arsenalInfo.setCrit(arsenalInfo.getCrit() - arsenalInfo.getCritIntensify());
            arsenalInfo.setDodge(arsenalInfo.getDodge() - arsenalInfo.getDodgeIntensify());
            arsenalInfo.setDefenseCrit(arsenalInfo.getDefenseCrit() - arsenalInfo.getDefenseCritIntensify());
            arsenalInfo.setHitRate(arsenalInfo.getHitRate() - arsenalInfo.getHitRateIntensify());
            arsenalInfo.setVitalityIntensify(arsenalInfoOriginal.getVitalityIntensify());
            arsenalInfo.setAttackIntensify(arsenalInfoOriginal.getAttackIntensify());
            arsenalInfo.setSpellAttacksIntensify(arsenalInfoOriginal.getSpellAttacksIntensify());
            arsenalInfo.setPdefIntensify(arsenalInfoOriginal.getPdefIntensify());
            arsenalInfo.setMagdefIntensify(arsenalInfoOriginal.getMagdefIntensify());
            arsenalInfo.setCritIntensify(arsenalInfoOriginal.getCritIntensify());
            arsenalInfo.setDodgeIntensify(arsenalInfoOriginal.getDodgeIntensify());
            arsenalInfo.setHitRateIntensify(arsenalInfoOriginal.getHitRateIntensify());
            arsenalInfo.setDefenseCritIntensify(arsenalInfoOriginal.getDefenseCritIntensify());
            arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                    arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                    + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
            arsenalInfo.setUpdateDate(new Date());
            arsenalInfoService.update(arsenalInfo);
            Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), arsenalInfo.getEnchantlvl());
            goodsMap.put("enchantlvl", arsenalInfo.getEnchantlvl());
            retMap.put("basics", goodsMap);
            if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + arsenalInfo.getFightingCapacity() - arsenal.getFightingCapacity());
                userAdornEquip.setWeapon(userGoods.getId());
                userAdornEquip.setVitality(userAdornEquip.getVitality() + arsenalInfo.getVitality() - arsenal.getVitality());
                userAdornEquip.setAttack(userAdornEquip.getAttack() + arsenalInfo.getAttack() - arsenal.getAttack());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + arsenalInfo.getSpellAttacks() - arsenal.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() + arsenalInfo.getPdef() - arsenal.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() + arsenalInfo.getMagdef() - arsenal.getMagdef());
                userAdornEquip.setCrit(userAdornEquip.getCrit() + arsenalInfo.getCrit() - arsenal.getCrit());
                userAdornEquip.setDodge(userAdornEquip.getDodge() + arsenalInfo.getDodge() - arsenal.getDodge());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() + arsenalInfo.getHitRate() - arsenal.getHitRate());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + arsenalInfo.getDefenseCrit() - arsenal.getDefenseCrit());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            }
        }

        UserGoods userGoodsRe = userGoodsService.subUserGoodsSingle(userGoods.getUserId(), GoodsConstant.GOODS_MONEY, 2000,true);
        Map goodsMap = ObjectUtil.getGoodsMap(userGoodsRe.getGoodsId(), userGoodsRe.getGoodsId(), userGoodsRe.getType(), userGoodsRe.getAmount(), null);
        retMap.put("consumables", goodsMap);

        // 更新支线任务
        Integer minEnchantlvl = userGoodsService.getMinEnchantlvl(userGoods.getUserId());
        if (minEnchantlvl != null && EnchantlvlEnum.getTaskId(minEnchantlvl) != null) {
            taskService.taskBranchFinish(EnchantlvlEnum.getTaskId(minEnchantlvl), userGoods.getUserId());
        }
        return new BaseReturn("转移成功", retMap);
    }


}