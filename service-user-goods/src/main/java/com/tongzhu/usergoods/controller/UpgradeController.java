package com.tongzhu.usergoods.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.domain.LoveTreeInfo;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.po.UpgradePO;
import com.tongzhu.usergoods.service.*;
import com.tongzhu.util.ObjectUtil;
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

import java.util.*;


/**
 * Created by Administrator on 2018/12/22 0022.
 */

@Api(value = "升阶controller", tags = {"升阶API"})
@RestController
@RequestMapping(value = "/upgrade")
public class UpgradeController {


    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UpgradeInfoService upgradeInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private ForgeInfoService forgeInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private WeddingRingUpgradeInfoService weddingRingUpgradeInfoService;

    @Autowired
    private UserWeddingRingLevelService userWeddingRingLevelService;

    @Autowired
    private MarryService marryService;


    /**
     * 升阶武器装备
     *
     * @param upgradePO
     * @return
     */
    @ApiOperation(value = "升阶武器装备", notes = "请求参数说明 [userId 用户ID] [afterGoodsId 升阶后的id] [consume 消耗品id][consumeAmount 消耗品数量]" +
            "[goodsId 装备id][quality 装备品质 ][upgradeAmount 强化需要道具数量][upgradeId 强化需要道具id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{upgradeGoods 宝石详情}{consumeGoods 消耗品详情}{goods 道具详情}{delGoods 升阶前的装备详情}")})
    @PostMapping(value = "/degreeElevation", produces = "application/json")
    public BaseReturn degreeElevation(@RequestBody UpgradePO upgradePO) {
        if (StringUtils.isEmpty(upgradePO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        if (StringUtils.isEmpty(upgradePO.getConsume())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(upgradePO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }


        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(upgradePO.getUserId(), upgradePO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在");
        }


        UserRole userRoleByUserId = roleService.getUserRoleByUserId(userGoods.getUserId());
        UserGoods userGoodsConsume = userGoodsService.getByUserIdAndGoodsId(upgradePO.getUserId(), upgradePO.getConsume());
        UserGoods userGoodsUpgrad = null;
        if (!StringUtils.isEmpty(upgradePO.getUpgradeId())) {
            userGoodsUpgrad = userGoodsService.getByUserIdAndGoodsId(upgradePO.getUserId(), upgradePO.getUpgradeId());
        }
        Map retMap = new HashMap();
        if (userGoods.getType().intValue() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo.getQuality() != upgradePO.getQuality()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备品质错误");
            }
            UpgradeInfo upgradeInfo = upgradeInfoService.getUpgradeInfoByQualityAndWearPosition(equipmentInfo.getQuality(), equipmentInfo.getWearPosition(), userGoods.getType());
            if (upgradeInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶配置错误");
            }

            if (upgradeInfo.getType() != userGoods.getType()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石错误");
            }

            if (!upgradeInfo.getConsume().equals(upgradePO.getConsume())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
            }
            if (upgradeInfo.getConsumeAmount().intValue() != upgradePO.getConsumeAmount().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }

            if (userGoodsConsume == null || userGoodsConsume.getAmount() < upgradeInfo.getConsumeAmount()) {
                PropInfo propInfo = propInfoService.getPropInfo(userGoodsConsume.getGoodsId());
                if (propInfo == null) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量不足");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, propInfo.getName() + "数量不足");
                }
            }

            if (!StringUtils.isEmpty(upgradeInfo.getUpgradeId()) && !upgradeInfo.getUpgradeId().equals(upgradePO.getUpgradeId())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石错误");
            }

            if (upgradeInfo.getUpgradeAmount() > 0 && !upgradeInfo.getUpgradeAmount().equals(upgradePO.getUpgradeAmount())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石数量错误");
            }
            if (!StringUtils.isEmpty(upgradePO.getUpgradeId()) && (userGoodsUpgrad == null || userGoodsUpgrad.getAmount() < upgradeInfo.getUpgradeAmount())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石数量不足");
            }


            if (equipmentInfo.getUpgrade() == PacksackConstant.GOODS_UPGRADE_NO) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不能升阶");
            }
            if (!equipmentInfo.getUpgrade().equals(upgradePO.getAfterGoodsId())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶后装备参数错误");
            }
            EquipmentInfo afterEquipmentInfo = equipmentInfoService.getEquipmentInfoByIdAndOriginal(equipmentInfo.getUpgrade() + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (afterEquipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶后装备配置不存在");
            }
            if (userRoleByUserId.getRoleLevel() < afterEquipmentInfo.getLevel()) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_INSUFFICIENT_LEVEL, "角色等级不够");
            }


            if (equipmentInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) {
                Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, userGoods.getAmount(), equipmentInfo.getEnchantlvl());
                retMap.put("delGoods", goodsMap);
                userGoods.setGoodsId(afterEquipmentInfo.getIntensifyId());
                userGoods.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoods);

                equipmentInfo = afterEquipmentInfo;
            } else {
                if (equipmentInfo.getEnchantlvl() > 0) {
                    ForgeInfo forgeInfoFailure = forgeInfoService.getForgeInfoByEnchantlvl(equipmentInfo.getEnchantlvl() - 1);
                    afterEquipmentInfo.setVitalityIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getVitality()));
                    afterEquipmentInfo.setAttackIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getAttack()));
                    afterEquipmentInfo.setSpellAttacksIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getSpellAttacks()));
                    afterEquipmentInfo.setPdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getPdef()));
                    afterEquipmentInfo.setMagdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getMagdef()));
                    afterEquipmentInfo.setCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getCrit()));
                    afterEquipmentInfo.setHitRateIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getHitRate()));
                    afterEquipmentInfo.setDodgeIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getDodge()));
                    afterEquipmentInfo.setDefenseCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterEquipmentInfo.getDefenseCrit()));
                }
                afterEquipmentInfo.setAttackGem(equipmentInfo.getAttackGem());
                afterEquipmentInfo.setCritGem(equipmentInfo.getCritGem());
                afterEquipmentInfo.setDodgeGem(equipmentInfo.getDodgeGem());
                afterEquipmentInfo.setMagdefGem(equipmentInfo.getMagdefGem());
                afterEquipmentInfo.setPdefGem(equipmentInfo.getPdefGem());
                afterEquipmentInfo.setVitalityGem(equipmentInfo.getVitalityGem());
                afterEquipmentInfo.setDefenseCritGem(equipmentInfo.getDefenseCritGem());
                afterEquipmentInfo.setHitRateGem(equipmentInfo.getHitRateGem());
                afterEquipmentInfo.setSpellAttacksGem(equipmentInfo.getSpellAttacksGem());

                afterEquipmentInfo.setVitality(afterEquipmentInfo.getVitalityIntensify() + afterEquipmentInfo.getVitality() + equipmentInfo.getVitalityGem());
                afterEquipmentInfo.setAttack(afterEquipmentInfo.getAttackIntensify() + afterEquipmentInfo.getAttack() + equipmentInfo.getAttackGem());
                afterEquipmentInfo.setSpellAttacks(afterEquipmentInfo.getSpellAttacksIntensify() + afterEquipmentInfo.getSpellAttacks() + equipmentInfo.getSpellAttacksGem());
                afterEquipmentInfo.setPdef(afterEquipmentInfo.getPdefIntensify() + afterEquipmentInfo.getPdef() + equipmentInfo.getPdefGem());
                afterEquipmentInfo.setMagdef(afterEquipmentInfo.getMagdefIntensify() + afterEquipmentInfo.getMagdef() + equipmentInfo.getMagdefGem());
                afterEquipmentInfo.setCrit(afterEquipmentInfo.getCritIntensify() + afterEquipmentInfo.getCrit() + equipmentInfo.getCritGem());
                afterEquipmentInfo.setDodge(afterEquipmentInfo.getDodgeIntensify() + afterEquipmentInfo.getDodge() + equipmentInfo.getDodgeGem());
                afterEquipmentInfo.setHitRate(afterEquipmentInfo.getHitRateIntensify() + afterEquipmentInfo.getHitRate() + equipmentInfo.getHitRateGem());
                afterEquipmentInfo.setDefenseCrit(afterEquipmentInfo.getDefenseCritIntensify() + afterEquipmentInfo.getDefenseCrit() + equipmentInfo.getDefenseCritGem());
                afterEquipmentInfo.setFightingCapacity((double) Math.round(afterEquipmentInfo.getVitality() * 0.2 + afterEquipmentInfo.getAttack() + afterEquipmentInfo.getSpellAttacks() +
                        afterEquipmentInfo.getPdef() + afterEquipmentInfo.getMagdef() + afterEquipmentInfo.getCrit() * 1.5
                        + afterEquipmentInfo.getDodge() * 1.5 + afterEquipmentInfo.getHitRate() * 1.5 + afterEquipmentInfo.getDefenseCrit() * 1.5));
                afterEquipmentInfo.setIntensifyId(equipmentInfo.getIntensifyId());
                afterEquipmentInfo.setEnchantlvl(equipmentInfo.getEnchantlvl());
                afterEquipmentInfo.setGemHexagon(equipmentInfo.getGemHexagon());
                afterEquipmentInfo.setGemRhombus(equipmentInfo.getGemRhombus());
                afterEquipmentInfo.setGemRoundness(equipmentInfo.getGemRoundness());
                afterEquipmentInfo.setOriginal(equipmentInfo.getOriginal());
                equipmentInfoService.update(afterEquipmentInfo);
                equipmentInfo = afterEquipmentInfo;
            }
            Map goodsMap = ObjectUtil.getGoodsMap(afterEquipmentInfo.getIntensifyId(), afterEquipmentInfo.getId(), userGoods.getType(), userGoods.getAmount(), afterEquipmentInfo.getEnchantlvl());
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
        } else if (userGoods.getType().intValue() == PacksackConstant.GOODS_TYPE_WEAPON) {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ArsenalInfo arsenal = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            if (arsenalInfo.getQuality() != upgradePO.getQuality()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备品质错误");
            }

            UpgradeInfo upgradeInfo = upgradeInfoService.getUpgradeInfoByQualityAndWearPosition(arsenalInfo.getQuality(), 0, userGoods.getType());
            if (upgradeInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶配置错误");
            }

            if (upgradeInfo.getType() != userGoods.getType()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石错误");
            }

            if (!upgradeInfo.getConsume().equals(upgradePO.getConsume())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品类型错误");
            }
            if (upgradeInfo.getConsumeAmount().intValue() != upgradePO.getConsumeAmount()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }
            if (userGoodsConsume == null || userGoodsConsume.getAmount() < upgradeInfo.getConsumeAmount()) {
                PropInfo propInfo = propInfoService.getPropInfo(userGoodsConsume.getGoodsId());
                if (propInfo == null) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量不足");
                } else {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, propInfo.getName() + "数量不足");
                }
            }


            if (!StringUtils.isEmpty(upgradeInfo.getUpgradeId()) && !upgradeInfo.getUpgradeId().equals(upgradePO.getUpgradeId())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石错误");
            }
            if (upgradeInfo.getUpgradeAmount() > 0 && !upgradeInfo.getUpgradeAmount().equals(upgradePO.getUpgradeAmount())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石数量错误");
            }
            if (!StringUtils.isEmpty(upgradePO.getUpgradeId()) && (userGoodsUpgrad == null || userGoodsUpgrad.getAmount() < upgradeInfo.getUpgradeAmount())) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶宝石数量不足");
            }


            if (arsenalInfo.getUpgrade() == PacksackConstant.GOODS_UPGRADE_NO) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不能升阶");
            }
            if (arsenalInfo.getUpgrade().intValue() != upgradePO.getAfterGoodsId().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶后装备参数错误");
            }
            ArsenalInfo afterArsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal(arsenalInfo.getUpgrade() + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (afterArsenalInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "升阶后装备配置不存在");
            }
            if (userRoleByUserId.getRoleLevel() < afterArsenalInfo.getLevel()) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_INSUFFICIENT_LEVEL, "角色等级不够");
            }
            if (arsenalInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) {
                Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, userGoods.getAmount(), arsenalInfo.getEnchantlvl());
                retMap.put("delGoods", goodsMap);
                userGoods.setGoodsId(afterArsenalInfo.getIntensifyId());
                userGoods.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoods);
                arsenalInfo = afterArsenalInfo;
            } else {
                if (arsenalInfo.getEnchantlvl() > 0) {
                    ForgeInfo forgeInfoFailure = forgeInfoService.getForgeInfoByEnchantlvl(arsenalInfo.getEnchantlvl() - 1);
                    afterArsenalInfo.setVitalityIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getVitality()));
                    afterArsenalInfo.setAttackIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getAttack()));
                    afterArsenalInfo.setSpellAttacksIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getSpellAttacks()));
                    afterArsenalInfo.setPdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getPdef()));
                    afterArsenalInfo.setMagdefIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getMagdef()));
                    afterArsenalInfo.setCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getCrit()));
                    afterArsenalInfo.setDodgeIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getDodge()));
                    afterArsenalInfo.setHitRateIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getHitRate()));
                    afterArsenalInfo.setDefenseCritIntensify(Math.ceil(forgeInfoFailure.getReinforceScale() / 100 * afterArsenalInfo.getDefenseCrit()));
                }
                afterArsenalInfo.setAttackGem(arsenalInfo.getAttackGem());
                afterArsenalInfo.setCritGem(arsenalInfo.getCritGem());
                afterArsenalInfo.setDodgeGem(arsenalInfo.getDodgeGem());
                afterArsenalInfo.setMagdefGem(arsenalInfo.getMagdefGem());
                afterArsenalInfo.setPdefGem(arsenalInfo.getPdefGem());
                afterArsenalInfo.setVitalityGem(arsenalInfo.getVitalityGem());
                afterArsenalInfo.setDefenseCritGem(arsenalInfo.getDefenseCritGem());
                afterArsenalInfo.setHitRateGem(arsenalInfo.getHitRateGem());
                afterArsenalInfo.setSpellAttacksGem(arsenalInfo.getSpellAttacksGem());

                afterArsenalInfo.setVitality(afterArsenalInfo.getVitalityIntensify() + afterArsenalInfo.getVitality() + afterArsenalInfo.getVitalityGem());
                afterArsenalInfo.setAttack(afterArsenalInfo.getAttackIntensify() + afterArsenalInfo.getAttack() + afterArsenalInfo.getAttackGem());
                afterArsenalInfo.setSpellAttacks(afterArsenalInfo.getSpellAttacksIntensify() + afterArsenalInfo.getSpellAttacks() + afterArsenalInfo.getSpellAttacksGem());
                afterArsenalInfo.setPdef(afterArsenalInfo.getPdefIntensify() + afterArsenalInfo.getPdef() + afterArsenalInfo.getPdefGem());
                afterArsenalInfo.setMagdef(afterArsenalInfo.getMagdefIntensify() + afterArsenalInfo.getMagdef() + afterArsenalInfo.getMagdefGem());
                afterArsenalInfo.setCrit(afterArsenalInfo.getCritIntensify() + afterArsenalInfo.getCrit() + afterArsenalInfo.getCritGem());
                afterArsenalInfo.setDodge(afterArsenalInfo.getDodgeIntensify() + afterArsenalInfo.getDodge() + afterArsenalInfo.getDodgeGem());
                afterArsenalInfo.setHitRate(afterArsenalInfo.getHitRateIntensify() + afterArsenalInfo.getHitRate() + afterArsenalInfo.getHitRateGem());
                afterArsenalInfo.setDefenseCrit(afterArsenalInfo.getDefenseCritIntensify() + afterArsenalInfo.getDefenseCrit() + afterArsenalInfo.getDefenseCritGem());
                afterArsenalInfo.setFightingCapacity((double) Math.round(afterArsenalInfo.getVitality() * 0.2 + afterArsenalInfo.getAttack() + afterArsenalInfo.getSpellAttacks() +
                        afterArsenalInfo.getPdef() + afterArsenalInfo.getMagdef() + afterArsenalInfo.getCrit() * 1.5
                        + afterArsenalInfo.getDodge() * 1.5 + afterArsenalInfo.getHitRate() * 1.5 + afterArsenalInfo.getDefenseCrit() * 1.5));
                afterArsenalInfo.setIntensifyId(arsenalInfo.getIntensifyId());
                afterArsenalInfo.setEnchantlvl(arsenalInfo.getEnchantlvl());
                afterArsenalInfo.setGemHexagon(arsenalInfo.getGemHexagon());
                afterArsenalInfo.setGemRhombus(arsenalInfo.getGemRhombus());
                afterArsenalInfo.setGemRoundness(arsenalInfo.getGemRoundness());
                afterArsenalInfo.setOriginal(arsenalInfo.getOriginal());
                arsenalInfoService.update(afterArsenalInfo);
                arsenalInfo = afterArsenalInfo;
            }

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
            Map goodsMap = ObjectUtil.getGoodsMap(afterArsenalInfo.getIntensifyId(), afterArsenalInfo.getId(), userGoods.getType(), userGoods.getAmount(), afterArsenalInfo.getEnchantlvl());
            retMap.put("goods", goodsMap);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备类型错误");
        }

        if (!StringUtils.isEmpty(upgradePO.getUpgradeId())) {
            UserGoods upgradeUserGoods = userGoodsService.subUserGoodsSingle(upgradePO.getUserId(), upgradePO.getUpgradeId(), upgradePO.getUpgradeAmount(), true);
            if (upgradeUserGoods != null) {
                Map upgradeUserGoodsMap = ObjectUtil.getGoodsMap(upgradePO.getUpgradeId(), upgradePO.getUpgradeId(), PacksackConstant.GOODS_TYPE_PROP, upgradeUserGoods.getAmount(), null);
                retMap.put("upgradeGoods", upgradeUserGoodsMap);
            }
        }
        if (!StringUtils.isEmpty(upgradePO.getConsume())) {
            UserGoods consumeGoods = userGoodsService.subUserGoodsSingle(upgradePO.getUserId(), upgradePO.getConsume(), upgradePO.getConsumeAmount(), true);
            if (consumeGoods != null) {
                Map consumeGoodsMap = ObjectUtil.getGoodsMap(upgradePO.getConsume(), upgradePO.getConsume(), PacksackConstant.GOODS_TYPE_PROP, consumeGoods.getAmount(), null);
                retMap.put("consumeGoods", consumeGoodsMap);
            }
        }
        return new BaseReturn("操作成功!", retMap);
    }


    @ApiOperation(value = "婚戒升级", notes = "请求参数说明 [userId 用户ID]  [consumeAmount 消耗品数量] [id 爱情树id]" +
            "[goodsId 装备id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{upgradeGoods 宝石详情}{consumeGoods 消耗品详情}{goods 道具详情}")})
    @PostMapping(value = "/weddingRingUpgrade", produces = "application/json")
    public BaseReturn weddingRingUpgrade(@RequestBody UpgradePO upgradePO) {
        if (StringUtils.isEmpty(upgradePO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(upgradePO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(upgradePO.getUserId(), upgradePO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒不存在");
        }
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_ADORN) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未佩戴婚戒");
        }

        Map retMap = new HashMap();
        if (userGoods.getType().intValue() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            EquipmentInfo equipment = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo.getWearPosition() != AdornEquipEnum.WEDDING_RING.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不是婚戒");
            }

            UserWeddingRingLevel userWeddingRingLevel = userWeddingRingLevelService.getUserWeddingRingLevelByUserId(userGoods.getUserId());
            if (userWeddingRingLevel == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒不存在");
            }
            WeddingRingUpgradeInfo weddingRingUpgradeInfo = weddingRingUpgradeInfoService.getWeddingRingUpgradeInfoByLevel(userWeddingRingLevel.getLevel() + 1);
            if (weddingRingUpgradeInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒升级失败");
            }
            if (weddingRingUpgradeInfo.getHappinessNum().intValue() != upgradePO.getConsumeAmount().intValue()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "消耗品数量错误");
            }

            LoveTreeInfo loveTreeInfo = marryService.getLoveTreeInfoObject(upgradePO.getId(), upgradePO.getUserId());
            if (loveTreeInfo == null || loveTreeInfo.getHappinessNum() < weddingRingUpgradeInfo.getHappinessNum()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "幸福值不足");
            }


            if (userWeddingRingLevel.getLevel() / 10 > 0 && (userWeddingRingLevel.getLevel() + 1) % 10 == 1) {
                equipmentInfo = equipmentInfoService.getEquipmentInfoByWearPositionAndQuality(AdornEquipEnum.WEDDING_RING.getId(), weddingRingUpgradeInfo.getLevel() / 10 + 1);
                if (equipmentInfo == null) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒升级失败");
                }
                equipmentInfo.setIntensifyId(equipment.getIntensifyId());
                equipmentInfo.setOriginal(equipment.getOriginal());
            }

            equipmentInfo.setAttack(weddingRingUpgradeInfo.getAttack());
            equipmentInfo.setMagdef(weddingRingUpgradeInfo.getMagdef());
            equipmentInfo.setPdef(weddingRingUpgradeInfo.getPdef());
            equipmentInfo.setSpellAttacks(weddingRingUpgradeInfo.getSpellAttacks());
            equipmentInfo.setVitality(weddingRingUpgradeInfo.getVitality());
            equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                    equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                    + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));

            if (equipmentInfo.getOriginal() == PacksackConstant.GOODS_ORIGINAL_YES) {
                equipmentInfo.setOriginal(PacksackConstant.GOODS_ORIGINAL_NO);
                equipmentInfo.setIntensifyId(UUIDUtil.generateUUID());
                equipmentInfoService.insert(equipmentInfo);
                userGoods.setGoodsId(equipmentInfo.getIntensifyId());
                userGoods.setUpdateDate(new Date());
                userGoodsService.updateByUserId(userGoods);
            } else {
                equipmentInfoService.update(equipmentInfo);
            }
            marryService.minusHappinessByUserId(upgradePO.getUserId(), weddingRingUpgradeInfo.getHappinessNum().intValue(), equipmentInfo.getIntensifyId());

            userWeddingRingLevel.setLevel(userWeddingRingLevel.getLevel() + 1);
            userWeddingRingLevel.setIntensifyId(equipmentInfo.getIntensifyId());
            userWeddingRingLevelService.updateByPrimaryKeySelective(userWeddingRingLevel);

            Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, 1, equipmentInfo.getEnchantlvl());
            goodsMap.put("ringLevel", userWeddingRingLevel.getLevel());
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
            WeddingRingUpgradeInfo weddingRing = weddingRingUpgradeInfoService.getWeddingRingUpgradeInfoByLevel(userWeddingRingLevel.getLevel() + 1);
            Integer upgradeGoodsId = equipmentInfo.getId();
            if (userWeddingRingLevel.getLevel() / 10 > 0 && (userWeddingRingLevel.getLevel() + 1) % 10 == 1) {
                equipmentInfo = equipmentInfoService.getEquipmentInfoByWearPositionAndQuality(AdornEquipEnum.WEDDING_RING.getId(), weddingRingUpgradeInfo.getLevel() / 10 + 1);
                if (equipmentInfo != null) {
                    upgradeGoodsId = equipmentInfo.getId();
                }

            }
            retMap.put("upgradeHappinessNum", weddingRing.getHappinessNum());
            retMap.put("upgradeGoodsId", upgradeGoodsId);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备类型错误");
        }
        return new BaseReturn("操作成功!", retMap);
    }


    @ApiOperation(value = "获取升级后戒指属性", notes = "请求参数说明 [userId 用户ID]   [goodsId 装备id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{upgradeGoods 宝石详情}{consumeGoods 消耗品详情}{goods 道具详情}")})
    @PostMapping(value = "/getWeddingRing", produces = "application/json")
    public BaseReturn getWeddingRing(@RequestBody UpgradePO upgradePO) {
        if (StringUtils.isEmpty(upgradePO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(upgradePO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(upgradePO.getUserId(), upgradePO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒不存在");
        }
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_ADORN) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未佩戴婚戒");
        }

        Map map = new HashMap();
        if (userGoods.getType().intValue() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo.getWearPosition() != AdornEquipEnum.WEDDING_RING.getId()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该装备不是婚戒");
            }

            UserWeddingRingLevel userWeddingRingLevel = userWeddingRingLevelService.getUserWeddingRingLevelByUserId(userGoods.getUserId());
            if (userWeddingRingLevel == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒不存在");
            }
            WeddingRingUpgradeInfo weddingRingUpgradeInfo = weddingRingUpgradeInfoService.getWeddingRingUpgradeInfoByLevel(userWeddingRingLevel.getLevel() + 1);
            if (weddingRingUpgradeInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "婚戒升级失败");
            }
            Map map1 = new HashMap();
            map1.put("attack", equipmentInfo.getAttack());
            map1.put("magdef", equipmentInfo.getMagdef());
            map1.put("pdef", equipmentInfo.getPdef());
            map1.put("spellAttacks", equipmentInfo.getSpellAttacks());
            map1.put("vitality", equipmentInfo.getVitality());
            map1.put("equipFight", equipmentInfo.getFightingCapacity());
            map.put("equip",map1);

            equipmentInfo.setAttack(weddingRingUpgradeInfo.getAttack());
            equipmentInfo.setMagdef(weddingRingUpgradeInfo.getMagdef());
            equipmentInfo.setPdef(weddingRingUpgradeInfo.getPdef());
            equipmentInfo.setSpellAttacks(weddingRingUpgradeInfo.getSpellAttacks());
            equipmentInfo.setVitality(weddingRingUpgradeInfo.getVitality());
            equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                    equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                    + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
            Map retMap = new HashMap();
            retMap.put("attack", equipmentInfo.getAttack());
            retMap.put("magdef", equipmentInfo.getMagdef());
            retMap.put("pdef", equipmentInfo.getPdef());
            retMap.put("spellAttacks", equipmentInfo.getSpellAttacks());
            retMap.put("vitality", equipmentInfo.getVitality());
            retMap.put("equipFight", equipmentInfo.getFightingCapacity());
            map.put("afterEquip",retMap);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备类型错误");
        }
        return new BaseReturn("操作成功!", map);
    }


}
