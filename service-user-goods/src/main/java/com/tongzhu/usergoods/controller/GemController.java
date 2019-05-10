package com.tongzhu.usergoods.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.enums.GemEnum;
import com.tongzhu.usergoods.enums.GemLevelEnum;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.po.GemPO;
import com.tongzhu.usergoods.service.*;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.CommonUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

@Api(value = "宝石controller", tags = {"宝石API"})
@RestController
@RequestMapping(value = "/gem")
public class GemController {

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private GemInfoService gemInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private TaskService taskService;

    /**
     * 镶嵌宝石
     *
     * @param gemPO
     * @return
     */
    @ApiOperation(value = "镶嵌宝石", notes = "请求参数说明 [userId 用户ID] [goodsId 镶嵌宝石的装备] [amountHexagon 镶嵌六边形宝石孔消耗品数量][amountRhombus 镶嵌菱形宝石孔消耗品类型]" +
            "[amountRoundness 镶嵌圆形宝石孔消耗品数量][consumeHexagon镶嵌六边形宝石孔消耗品类型][镶嵌菱形宝石孔消耗品类型][consumeRoundness镶嵌圆形宝石孔消耗品类型][gemHexagon  镶嵌六边形宝石孔id]" +
            "[gemRhombus镶嵌菱形宝石孔id][gemRoundness镶嵌圆形宝石孔id] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{goods 装备详情 需要更新装备信息} {propGoods 消耗详情  }")})
    @PostMapping(value = "/metagems", produces = "application/json")
    public BaseReturn metagems(@RequestBody GemPO gemPO) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isEmpty(gemPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(gemPO.getGemHexagon()) && StringUtils.isEmpty(gemPO.getGemRhombus()) && StringUtils.isEmpty(gemPO.getGemRoundness())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "宝石id为空");
        }
        if (StringUtils.isEmpty(gemPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "物品id为空");
        }
        if (StringUtils.isEmpty(gemPO.getConsumeHexagon()) && StringUtils.isEmpty(gemPO.getConsumeRhombus()) && StringUtils.isEmpty(gemPO.getConsumeRoundness())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "消耗品id为空");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemPO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品道具不存在");
        }

        PropInfo gemHexagonPropInfo = new PropInfo();
        UserGoods gemHexagonGoods = null;
        UserGoods gemHexagonConsume = null;
        if (!StringUtils.isEmpty(gemPO.getGemHexagon())) {
            gemHexagonPropInfo = propInfoService.getPropInfo(gemPO.getGemHexagon());
            if (gemHexagonPropInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,  "宝石不存在");
            }
            gemHexagonGoods = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemHexagonPropInfo.getId());
            if (gemHexagonGoods == null || gemHexagonGoods.getAmount() <= 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemHexagonPropInfo.getName() + "数量不足");
            }

            GemInfo gemInfoHexagon = gemInfoService.getGemInfoById(gemHexagonPropInfo.getId());
            if (gemInfoHexagon == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemHexagonPropInfo.getName() + "宝石配置错误");
            }
            if (gemInfoHexagon.getGemType() != GemEnum.TROUSERS.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝石类型错误");
            }
            if (!gemInfoHexagon.getConsume().equals(gemPO.getConsumeHexagon())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
            }
            if (gemInfoHexagon.getConsumeAmount().intValue() != gemPO.getAmountHexagon().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
            gemHexagonConsume = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemInfoHexagon.getConsume());
            if (gemHexagonConsume == null || gemHexagonConsume.getAmount().intValue() < gemInfoHexagon.getConsumeAmount().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
        }
        PropInfo gemGemRoundnessPropInfo = new PropInfo();
        UserGoods gemRoundnessGoods = null;
        UserGoods gemRoundnessConsume = null;
        if (!StringUtils.isEmpty(gemPO.getGemRoundness())) {
            gemGemRoundnessPropInfo = propInfoService.getPropInfo(gemPO.getGemRoundness());
            if (gemGemRoundnessPropInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,  "宝石不存在");
            }
            gemRoundnessGoods = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemGemRoundnessPropInfo.getId());
            if (gemRoundnessGoods == null || gemRoundnessGoods.getAmount() <= 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemGemRoundnessPropInfo.getName() + "数量不足");
            }

            GemInfo gemInfoRoundness = gemInfoService.getGemInfoById(gemGemRoundnessPropInfo.getId());
            if (gemInfoRoundness == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemGemRoundnessPropInfo.getName() + "宝石配置错误");
            }
            if (gemInfoRoundness.getGemType() != GemEnum.CLOTHING.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝石类型错误");
            }
            if (!gemInfoRoundness.getConsume().equals(gemPO.getConsumeRoundness())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
            }
            if (gemInfoRoundness.getConsumeAmount().intValue() != gemPO.getAmountRoundness().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
            gemRoundnessConsume = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemInfoRoundness.getConsume());
            if (gemRoundnessConsume == null || gemRoundnessConsume.getAmount().intValue() < gemInfoRoundness.getConsumeAmount().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
        }
        PropInfo gemGemRhombusPropInfo = new PropInfo();
        UserGoods gemRhombusGoods = null;
        UserGoods gemRhombusConsume = null;
        if (!StringUtils.isEmpty(gemPO.getGemRhombus())) {
            gemGemRhombusPropInfo = propInfoService.getPropInfo(gemPO.getGemRhombus());
            if (gemGemRhombusPropInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,  "宝石不存在");
            }
            gemRhombusGoods = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemGemRhombusPropInfo.getId());
            if (gemRhombusGoods == null || gemRhombusGoods.getAmount() <= 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemGemRhombusPropInfo.getName() + "数量不足");
            }

            GemInfo gemInfoRhombus = gemInfoService.getGemInfoById(gemGemRhombusPropInfo.getId());
            if (gemInfoRhombus == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, gemGemRhombusPropInfo.getName() + "宝石配置错误");
            }
            if (gemInfoRhombus.getGemType() != GemEnum.HEAD.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝石类型错误");
            }
            if (!gemInfoRhombus.getConsume().equals(gemPO.getConsumeRhombus())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
            }
            if (gemInfoRhombus.getConsumeAmount().intValue() != gemPO.getAmountRhombus().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
            gemRhombusConsume = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemInfoRhombus.getConsume());
            if (gemRhombusConsume == null || gemRhombusConsume.getAmount().intValue() < gemInfoRhombus.getConsumeAmount().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
        }
        Map retMap = new HashMap();
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具不存在");
            }
            if (equipmentInfo.getWearPosition() == AdornEquipEnum.WEDDING_RING.getId() || equipmentInfo.getWearPosition() == AdornEquipEnum.FASHION.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不能镶嵌宝石");
            }
            Double attack = 0.0;
            Double crit = 0.0;
            Double dodge = 0.0;
            Double magdef = 0.0;
            Double pdef = 0.0;
            Double vitality = 0.0;
            Double defenseCrit = 0.0;
            Double hitRate = 0.0;
            Double spellAttacks = 0.0;

            if (!StringUtils.isEmpty(gemPO.getGemRhombus())) {
                if (!StringUtils.isEmpty(equipmentInfo.getGemRhombus())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                equipmentInfo.setGemRhombus(gemPO.getGemRhombus());
                attack = attack + gemGemRhombusPropInfo.getAttack();
                crit = crit + gemGemRhombusPropInfo.getCrit();
                dodge = dodge + gemGemRhombusPropInfo.getDodge();
                magdef = magdef + gemGemRhombusPropInfo.getMagdef();
                pdef = pdef + gemGemRhombusPropInfo.getPdef();
                vitality = vitality + gemGemRhombusPropInfo.getVitality();
                defenseCrit = defenseCrit + gemGemRhombusPropInfo.getDefenseCrit();
                hitRate = hitRate + gemGemRhombusPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemGemRhombusPropInfo.getSpellAttacks();

            }
            if (!StringUtils.isEmpty(gemPO.getGemRoundness())) {
                if (!StringUtils.isEmpty(equipmentInfo.getGemRoundness())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                equipmentInfo.setGemRoundness(gemPO.getGemRoundness());
                attack = attack + gemGemRoundnessPropInfo.getAttack();
                crit = crit + gemGemRoundnessPropInfo.getCrit();
                dodge = dodge + gemGemRoundnessPropInfo.getDodge();
                magdef = magdef + gemGemRoundnessPropInfo.getMagdef();
                pdef = pdef + gemGemRoundnessPropInfo.getPdef();
                vitality = vitality + gemGemRoundnessPropInfo.getVitality();
                defenseCrit = defenseCrit + gemGemRoundnessPropInfo.getDefenseCrit();
                hitRate = hitRate + gemGemRoundnessPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemGemRoundnessPropInfo.getSpellAttacks();
            }
            if (!StringUtils.isEmpty(gemPO.getGemHexagon())) {
                if (!StringUtils.isEmpty(equipmentInfo.getGemHexagon())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                equipmentInfo.setGemHexagon(gemPO.getGemHexagon());
                attack = attack + gemHexagonPropInfo.getAttack();
                crit = crit + gemHexagonPropInfo.getCrit();
                dodge = dodge + gemHexagonPropInfo.getDodge();
                magdef = magdef + gemHexagonPropInfo.getMagdef();
                pdef = pdef + gemHexagonPropInfo.getPdef();
                vitality = vitality + gemHexagonPropInfo.getVitality();
                defenseCrit = defenseCrit + gemHexagonPropInfo.getDefenseCrit();
                hitRate = hitRate + gemHexagonPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemHexagonPropInfo.getSpellAttacks();
            }

            equipmentInfo.setAttackGem(equipmentInfo.getAttackGem() + attack);
            equipmentInfo.setCritGem(equipmentInfo.getCritGem() + crit);
            equipmentInfo.setDodgeGem(equipmentInfo.getDodgeGem() + dodge);
            equipmentInfo.setMagdefGem(equipmentInfo.getMagdefGem() + magdef);
            equipmentInfo.setPdefGem(equipmentInfo.getPdefGem() + pdef);
            equipmentInfo.setVitalityGem(equipmentInfo.getVitalityGem() + vitality);
            equipmentInfo.setDefenseCritGem(equipmentInfo.getDefenseCritGem() + defenseCrit);
            equipmentInfo.setHitRateGem(equipmentInfo.getHitRateGem() + hitRate);
            equipmentInfo.setSpellAttacksGem(equipmentInfo.getSpellAttacksGem() + spellAttacks);
            equipmentInfo.setAttack(equipmentInfo.getAttack() + attack);
            equipmentInfo.setCrit(equipmentInfo.getCrit() + crit);
            equipmentInfo.setDodge(equipmentInfo.getDodge() + dodge);
            equipmentInfo.setMagdef(equipmentInfo.getMagdef() + magdef);
            equipmentInfo.setPdef(equipmentInfo.getPdef() + pdef);
            equipmentInfo.setVitality(equipmentInfo.getVitality() + vitality);
            equipmentInfo.setDefenseCrit(equipmentInfo.getDefenseCrit() + defenseCrit);
            equipmentInfo.setHitRate(equipmentInfo.getHitRate() + hitRate);
            equipmentInfo.setSpellAttacks(equipmentInfo.getSpellAttacks() + spellAttacks);
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
            goodsMap.put("gemHexagon",equipmentInfo.getGemHexagon());
            goodsMap.put("gemRhombus",equipmentInfo.getGemRhombus());
            goodsMap.put("gemRoundness",equipmentInfo.getGemRoundness());
            retMap.put("goods", goodsMap);
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
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_WEAPON) {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            if (arsenalInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具不存在");
            }
            Double attack = 0.0;
            Double crit = 0.0;
            Double dodge = 0.0;
            Double magdef = 0.0;
            Double pdef = 0.0;
            Double vitality = 0.0;
            Double defenseCrit = 0.0;
            Double hitRate = 0.0;
            Double spellAttacks = 0.0;
            if (!StringUtils.isEmpty(gemPO.getGemRhombus())) {
                if (!StringUtils.isEmpty(arsenalInfo.getGemRhombus())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                arsenalInfo.setGemRhombus(gemPO.getGemRhombus());
                attack = attack + gemGemRhombusPropInfo.getAttack();
                crit = crit + gemGemRhombusPropInfo.getCrit();
                dodge = dodge + gemGemRhombusPropInfo.getDodge();
                magdef = magdef + gemGemRhombusPropInfo.getMagdef();
                pdef = pdef + gemGemRhombusPropInfo.getPdef();
                vitality = vitality + gemGemRhombusPropInfo.getVitality();
                defenseCrit = defenseCrit + gemGemRhombusPropInfo.getDefenseCrit();
                hitRate = hitRate + gemGemRhombusPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemGemRhombusPropInfo.getSpellAttacks();

            }
            if (!StringUtils.isEmpty(gemPO.getGemRoundness())) {
                if (!StringUtils.isEmpty(arsenalInfo.getGemRoundness())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                arsenalInfo.setGemRoundness(gemPO.getGemRoundness());
                attack = attack + gemGemRoundnessPropInfo.getAttack();
                crit = crit + gemGemRoundnessPropInfo.getCrit();
                dodge = dodge + gemGemRoundnessPropInfo.getDodge();
                magdef = magdef + gemGemRoundnessPropInfo.getMagdef();
                pdef = pdef + gemGemRoundnessPropInfo.getPdef();
                vitality = vitality + gemGemRoundnessPropInfo.getVitality();
                defenseCrit = defenseCrit + gemGemRoundnessPropInfo.getDefenseCrit();
                hitRate = hitRate + gemGemRoundnessPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemGemRoundnessPropInfo.getSpellAttacks();
            }
            if (!StringUtils.isEmpty(gemPO.getGemHexagon())) {
                if (!StringUtils.isEmpty(arsenalInfo.getGemHexagon())) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已镶嵌该类型宝石");
                }
                arsenalInfo.setGemHexagon(gemPO.getGemHexagon());
                attack = attack + gemHexagonPropInfo.getAttack();
                crit = crit + gemHexagonPropInfo.getCrit();
                dodge = dodge + gemHexagonPropInfo.getDodge();
                magdef = magdef + gemHexagonPropInfo.getMagdef();
                pdef = pdef + gemHexagonPropInfo.getPdef();
                vitality = vitality + gemHexagonPropInfo.getVitality();
                defenseCrit = defenseCrit + gemHexagonPropInfo.getDefenseCrit();
                hitRate = hitRate + gemHexagonPropInfo.getHitRate();
                spellAttacks = spellAttacks + gemHexagonPropInfo.getSpellAttacks();
            }

            arsenalInfo.setAttackGem(arsenalInfo.getAttackGem() + attack);
            arsenalInfo.setCritGem(arsenalInfo.getCritGem() + crit);
            arsenalInfo.setDodgeGem(arsenalInfo.getDodgeGem() + dodge);
            arsenalInfo.setMagdefGem(arsenalInfo.getMagdefGem() + magdef);
            arsenalInfo.setPdefGem(arsenalInfo.getPdefGem() + pdef);
            arsenalInfo.setVitalityGem(arsenalInfo.getVitalityGem() + vitality);
            arsenalInfo.setDefenseCritGem(arsenalInfo.getDefenseCritGem() + defenseCrit);
            arsenalInfo.setHitRateGem(arsenalInfo.getHitRateGem() + hitRate);
            arsenalInfo.setSpellAttacksGem(arsenalInfo.getSpellAttacksGem() + spellAttacks);
            arsenalInfo.setAttack(arsenalInfo.getAttack() + attack);
            arsenalInfo.setCrit(arsenalInfo.getCrit() + crit);
            arsenalInfo.setDodge(arsenalInfo.getDodge() + dodge);
            arsenalInfo.setMagdef(arsenalInfo.getMagdef() + magdef);
            arsenalInfo.setPdef(arsenalInfo.getPdef() + pdef);
            arsenalInfo.setVitality(arsenalInfo.getVitality() + vitality);
            arsenalInfo.setDefenseCrit(arsenalInfo.getDefenseCrit() + defenseCrit);
            arsenalInfo.setHitRate(arsenalInfo.getHitRate() + hitRate);
            arsenalInfo.setSpellAttacks(arsenalInfo.getSpellAttacks() + spellAttacks);

            arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                    arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                    + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
            arsenalInfo.setUpdateDate(new Date());


            if (arsenalInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) { // 原型武器
                arsenalInfo.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                arsenalInfo.setIntensifyId(UUIDUtil.generateUUID());
                arsenalInfoService.insert(arsenalInfo);
                userGoods.setGoodsId(arsenalInfo.getIntensifyId());
                userGoods.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoods);
            } else {
                arsenalInfoService.update(arsenalInfo);
            }
            Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), arsenalInfo.getEnchantlvl());
            goodsMap.put("gemHexagon",arsenalInfo.getGemHexagon());
            goodsMap.put("gemRhombus",arsenalInfo.getGemRhombus());
            goodsMap.put("gemRoundness",arsenalInfo.getGemRoundness());
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
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具类型错误");
        }

        LinkedHashSet<String> list = new LinkedHashSet();
        if (gemHexagonGoods != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemHexagonGoods.getUserId(), gemHexagonGoods.getGoodsId(), 1,true);
            list.add(goods.getGoodsId());
        }
        if (gemRhombusGoods != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemRhombusGoods.getUserId(), gemRhombusGoods.getGoodsId(), 1,true);
            list.add(goods.getGoodsId());
        }
        if (gemRoundnessGoods != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemRoundnessGoods.getUserId(), gemRoundnessGoods.getGoodsId(), 1,true);
            list.add(goods.getGoodsId());
        }

        if (gemRhombusConsume != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemRhombusConsume.getUserId(), gemRhombusConsume.getGoodsId(), gemPO.getAmountRhombus(),true);
            list.add(goods.getGoodsId());
        }
        if (gemRoundnessConsume != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemRoundnessConsume.getUserId(), gemRoundnessConsume.getGoodsId(), gemPO.getAmountRoundness(),true);
            list.add(goods.getGoodsId());
        }
        if (gemHexagonConsume != null) {
            UserGoods goods = userGoodsService.subUserGoodsSingle(gemHexagonConsume.getUserId(), gemHexagonConsume.getGoodsId(), gemPO.getAmountHexagon(),true);
            list.add(goods.getGoodsId());
        }

        List retList = new ArrayList();
        for (String goods : list) {
            UserGoods userIdAndGoodsId = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), goods);
            Map goodsMap = ObjectUtil.getGoodsMap(goods, goods, PacksackConstant.GOODS_TYPE_PROP, userIdAndGoodsId.getAmount(), null);
            retList.add(goodsMap);
        }
        retMap.put("propGoods",retList);
        Integer sumGemLevel = userGoodsService.getSumGemLevel(userGoods.getUserId());
        if (sumGemLevel != null) {
            Integer taskId = GemLevelEnum.getTaskId(sumGemLevel);
            if (taskId != null) {
                taskService.taskBranchFinish(taskId, userGoods.getUserId());
            }
        }
        return new BaseReturn("操作成功!", retMap);
    }


    /**
     * 摘除宝石
     *
     * @param gemPO
     * @return
     */
    @ApiOperation(value = "摘除宝石", notes = "请求参数说明 [userId 用户ID] [goodsId 镶嵌宝石的装备] [gemId 宝石id] [consume 消耗品id] [amount 消耗品数量] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{consumeGoods 消耗品数量 [amount 数量][goodsId ][id][type]} " +
                    "{goods 装备详情 需要更新装备信息}  {replaceGemId 卸下的宝石详情}")})
    @PostMapping(value = "/removeGem", produces = "application/json")
    public BaseReturn removeGem(@RequestBody GemPO gemPO) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isEmpty(gemPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(gemPO.getGemId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "宝石id为空");
        }
        if (StringUtils.isEmpty(gemPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "物品id为空");
        }
        if (StringUtils.isEmpty(gemPO.getConsume())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "消耗品id为空");
        }
        if (gemPO.getAmount() == null || gemPO.getAmount() < 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "消耗品数量错误");
        }

        GemInfo gemInfo = gemInfoService.getGemInfoById(gemPO.getGemId());
        if (gemInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝石配置错误");
        }
        if (!gemInfo.getConsume().equals(gemPO.getConsume())) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
        }
        if (gemInfo.getConsumeAmount().intValue() != gemPO.getAmount().intValue()) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
        }


        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(gemPO.getUserId(), gemPO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品不存在");
        }
        PropInfo gemPropInfo = propInfoService.getPropInfo(gemPO.getGemId());
        if (gemPropInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宝石不存在");
        }
        List list = new ArrayList();
        list.add(gemPO.getGemId());
        int i = userGoodsService.queryBackpackSpace(gemPO.getUserId(), list);
        if (i < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足，不能摘除宝石");
        }
        Map retMap = new HashMap();
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品不存在,配置错误");
            }
            String gemId = (String) CommonUtil.getObjectElement(equipmentInfo, GemEnum.getGem(gemInfo.getGemType()));
            CommonUtil.voluationBean(equipmentInfo, GemEnum.getGem(gemInfo.getGemType()), "");
            if (StringUtils.isEmpty(gemId)) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未镶嵌此宝石");
            }
            if (!gemId.equals(gemPropInfo.getId())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "处理失败");
            }
            equipmentInfo.setAttackGem(equipmentInfo.getAttackGem() - gemPropInfo.getAttack());
            equipmentInfo.setCritGem(equipmentInfo.getCritGem() - gemPropInfo.getCrit());
            equipmentInfo.setDodgeGem(equipmentInfo.getDodgeGem() - gemPropInfo.getDodge());
            equipmentInfo.setMagdefGem(equipmentInfo.getMagdefGem() - gemPropInfo.getMagdef());
            equipmentInfo.setPdefGem(equipmentInfo.getPdefGem() - gemPropInfo.getPdef());
            equipmentInfo.setVitalityGem(equipmentInfo.getVitalityGem() - gemPropInfo.getVitality());
            equipmentInfo.setDefenseCritGem(equipmentInfo.getDefenseCritGem() - gemPropInfo.getDefenseCrit());
            equipmentInfo.setHitRateGem(equipmentInfo.getHitRateGem() - gemPropInfo.getHitRate());
            equipmentInfo.setSpellAttacksGem(equipmentInfo.getSpellAttacksGem() - gemPropInfo.getSpellAttacks());

            equipmentInfo.setAttack(equipmentInfo.getAttack() - gemPropInfo.getAttack());
            equipmentInfo.setCrit(equipmentInfo.getCrit() - gemPropInfo.getCrit());
            equipmentInfo.setDodge(equipmentInfo.getDodge() - gemPropInfo.getDodge());
            equipmentInfo.setMagdef(equipmentInfo.getMagdef() - gemPropInfo.getMagdef());
            equipmentInfo.setPdef(equipmentInfo.getPdef() - gemPropInfo.getPdef());
            equipmentInfo.setVitality(equipmentInfo.getVitality() - gemPropInfo.getVitality());
            equipmentInfo.setDefenseCrit(equipmentInfo.getDefenseCrit() - gemPropInfo.getDefenseCrit());
            equipmentInfo.setHitRate(equipmentInfo.getHitRate() - gemPropInfo.getHitRate());
            equipmentInfo.setSpellAttacks(equipmentInfo.getSpellAttacks() - gemPropInfo.getSpellAttacks());
            equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                    equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                    + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
            equipmentInfo.setUpdateDate(new Date());
            equipmentInfoService.update(equipmentInfo);
            Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), userGoods.getType(), userGoods.getAmount(), null);
            retMap.put("goods", goodsMap);
            if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
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
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_WEAPON) {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            if (arsenalInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品不存在,配置错误");
            }
            String gemId = (String) CommonUtil.getObjectElement(arsenalInfo, GemEnum.getGem(gemInfo.getGemType()));
            CommonUtil.voluationBean(arsenalInfo, GemEnum.getGem(gemInfo.getGemType()), "");
            if (StringUtils.isEmpty(gemId)) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未镶嵌此宝石");
            }
            if (!gemId.equals(gemPropInfo.getId())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "处理失败");
            }

            arsenalInfo.setAttackGem(arsenalInfo.getAttackGem() - gemPropInfo.getAttack());
            arsenalInfo.setCritGem(arsenalInfo.getCritGem() - gemPropInfo.getCrit());
            arsenalInfo.setDodgeGem(arsenalInfo.getDodgeGem() - gemPropInfo.getDodge());
            arsenalInfo.setMagdefGem(arsenalInfo.getMagdefGem() - gemPropInfo.getMagdef());
            arsenalInfo.setPdefGem(arsenalInfo.getPdefGem() - gemPropInfo.getPdef());
            arsenalInfo.setVitalityGem(arsenalInfo.getVitalityGem() - gemPropInfo.getVitality());
            arsenalInfo.setDefenseCritGem(arsenalInfo.getDefenseCritGem() - gemPropInfo.getDefenseCrit());
            arsenalInfo.setHitRateGem(arsenalInfo.getHitRateGem() - gemPropInfo.getHitRate());
            arsenalInfo.setSpellAttacksGem(arsenalInfo.getSpellAttacksGem() - gemPropInfo.getSpellAttacks());

            arsenalInfo.setAttack(arsenalInfo.getAttack() - gemPropInfo.getAttack());
            arsenalInfo.setCrit(arsenalInfo.getCrit() - gemPropInfo.getCrit());
            arsenalInfo.setDodge(arsenalInfo.getDodge() - gemPropInfo.getDodge());
            arsenalInfo.setMagdef(arsenalInfo.getMagdef() - gemPropInfo.getMagdef());
            arsenalInfo.setPdef(arsenalInfo.getPdef() - gemPropInfo.getPdef());
            arsenalInfo.setVitality(arsenalInfo.getVitality() - gemPropInfo.getVitality());
            arsenalInfo.setDefenseCrit(arsenalInfo.getDefenseCrit() - gemPropInfo.getDefenseCrit());
            arsenalInfo.setHitRate(arsenalInfo.getHitRate() - gemPropInfo.getHitRate());
            arsenalInfo.setSpellAttacks(arsenalInfo.getSpellAttacks() - gemPropInfo.getSpellAttacks());
            arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                    arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                    + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
            arsenalInfo.setUpdateDate(new Date());
            arsenalInfoService.update(arsenalInfo);
            Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), null);
            retMap.put("goods", goodsMap);
            if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
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
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具类型错误");
        }

        UserGoods goods = userGoodsService.addUserGoodsSingle(userGoods.getUserId(), gemPO.getGemId(), 1);
        Map goodsMap = ObjectUtil.getGoodsMap(gemPO.getGemId(), gemPO.getGemId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
        retMap.put("replaceGem", goodsMap);

        if (!StringUtils.isEmpty(gemInfo.getConsume())) {
            UserGoods consumeGoods = userGoodsService.subUserGoodsSingle(userGoods.getUserId(), gemInfo.getConsume(), gemInfo.getConsumeAmount(),true);
            Map consumeGoodsMap = ObjectUtil.getGoodsMap(gemInfo.getConsume(), gemInfo.getConsume(), PacksackConstant.GOODS_TYPE_PROP, consumeGoods.getAmount(), null);
            retMap.put("consumeGoods", consumeGoodsMap);
        }
        return new BaseReturn("操作成功!", retMap);
    }


}
