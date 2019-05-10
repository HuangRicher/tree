package com.tongzhu.usergoods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.constant.PetConstant;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.po.PetPO;
import com.tongzhu.usergoods.service.*;
import com.tongzhu.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/7 0007.
 */

@Api(value = "宠物controller", tags = {"宠物API"})
@RestController
@RequestMapping(value = "/pet")
public class PetController {

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserPetService userPetService;

    @Autowired
    private PetLevelInfoService petLevelInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private PropGiftInfoService propGiftInfoService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询用户拥有的宠物
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "查询用户拥有的所有宠物", notes = "请求参数说明 [userId 用户ID]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{[petId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力][level 宠物等级]}{{[petid 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间]}}")})
    @PostMapping(value = "/queryUserPet", produces = "application/json")
    public BaseReturn queryUserPet(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        List<PetInfo> petInfoList = petInfoService.getPetInfoList(petPO.getUserId(), null);
        for (PetInfo petInfo : petInfoList) {
            ObjectUtil.setObjectFieldsEmpty(petInfo, "id", "level", "fightingCapacity", "petId");
        }

        Map map = new HashMap();
        map.put("petInfoList", petInfoList);
        if (petInfoList.size() > 0) {
            PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petInfoList.get(0).getPetId());
            if (petVO == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物详情查询失败");
            }
            map.put("pet", petVO);
        }
        return new BaseReturn("操作成功!", map);
    }

    /**
     * 查询用户拥有的永久宠物
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "查询用户拥有的永久宠物", notes = "请求参数说明 [userId 用户ID]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{[perId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力][level 宠物等级]}{{[petid 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间]}}")})
    @PostMapping(value = "/queryUserPetPerpetual", produces = "application/json")
    public BaseReturn queryUserPetPerpetual(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        List<PetInfo> petInfoList = petInfoService.getPetInfoList(petPO.getUserId(), PetConstant.OVERDUE_PERPETUAL);

        for (PetInfo petInfo : petInfoList) {
            ObjectUtil.setObjectFieldsEmpty(petInfo, "id", "level", "fightingCapacity", "petId");
        }
        Map map = new HashMap();
        map.put("petInfoList", petInfoList);
        if (petInfoList.size() > 0) {
            PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petInfoList.get(0).getPetId());
            if (petVO == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物详情查询失败");
            }
            map.put("pet", petVO);
        }
        return new BaseReturn("操作成功!", map);
    }

    /**
     * 查询用户拥有的限时宠物
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "查询用户拥有的限时宠物", notes = "请求参数说明 [userId 用户ID]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{[perId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]} {{[petid 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间]}}")})
    @PostMapping(value = "/queryUserPetDeadline", produces = "application/json")
    public BaseReturn queryUserPetDeadline(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        List<PetInfo> petInfoList = petInfoService.getPetInfoList(petPO.getUserId(), PetConstant.OVERDUE_DEADLINE);
        for (PetInfo petInfo : petInfoList) {
            ObjectUtil.setObjectFieldsEmpty(petInfo, "id", "fightingCapacity", "petId");
        }
        Map map = new HashMap();
        map.put("petInfoList", petInfoList);
        if (petInfoList.size() > 0) {
            PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petInfoList.get(0).getPetId());
            if (petVO == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物详情查询失败");
            }
            map.put("pet", petVO);
        }
        return new BaseReturn("操作成功!", map);
    }

    /**
     * 查询宠物详情
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "查询宠物详情", notes = "请求参数说明 [userId 用户ID] [petId 宠物唯一id]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{[petid 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间]}")})
    @PostMapping(value = "/queryUserPetDetails", produces = "application/json")
    public BaseReturn queryUserPetDetails(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getPetId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());
        if (petVO == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物详情查询失败");
        }
        return new BaseReturn("操作成功!", petVO);
    }


    /**
     * 喂养宠物
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "喂养宠物", notes = "请求参数说明 [userId 用户ID][petId 宠物id] [goodsId 宠物卡id] [amount 宠物卡数量] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{[petId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    "[crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间] }")})
    @PostMapping(value = "/userFeedPet", produces = "application/json")
    @Transactional
    public BaseReturn userFeedPet(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getPetId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (petPO.getAmount() == null || petPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(petPO.getUserId(), petPO.getGoodsId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品不存在");
        }
        if (userGoods.getAmount().intValue() < petPO.getAmount()) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物卡数量不足");
        }
        PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
        if (propInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品不存在");
        }
        PropGiftInfo propGiftInfo = propGiftInfoService.getPropGiftInfo(propInfo.getId());
        if (propGiftInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置不存在");
        }
        if (propGiftInfo.getType() != PacksackConstant.GIFT_TYPE_EXP) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品格式错误");
        }
        String giftBagItems = propGiftInfo.getGiftBagItems();
        JSONObject jsonObject = JSON.parseObject(giftBagItems);

        Map<String, Object> map = new HashMap<>();


        PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());

        UserRole userRoleByUserId = roleService.getUserRoleByUserId(petPO.getUserId());

        Integer petExt = petLevelInfoService.getPetLevelPet(petVO.getLevel(), userRoleByUserId.getRoleLevel() + PetConstant.PET_USER_LEVEL);

        int amout = ((petExt - petVO.getExp()) / jsonObject.getInteger("exp")) + 1;
        if (petPO.getAmount() > amout) {
            petPO.setAmount(amout);
        }

        if (userRoleByUserId == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在");
        }
        if (petVO == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物不存在");
        }
        if (petVO.getLevel() - userRoleByUserId.getRoleLevel() >= PetConstant.PET_USER_LEVEL && petVO.getUpgradeExp().intValue() - petVO.getExp().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物已超过角色等级太多，暂不能使用此卡");
        }
        if (petVO.getLevel() == PetConstant.LEVEL_MAX && petVO.getUpgradeExp().intValue() - petVO.getExp().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物已经满级无法继续增加经验");
        }
        Integer exp = jsonObject.getInteger("exp") * petPO.getAmount();
        PetVO feedPet = userPetService.userFeedPet(petPO.getUserId(), petPO.getPetId(), exp, null);
        if (feedPet == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物升级失败");
        }
        UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), petPO.getAmount(),true);
        Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
        map.put("pet", feedPet);
        map.put("goods", goodsMap);
        return new BaseReturn("操作成功!", map);
    }


    /**
     * 宠物跟随
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "宠物跟随", notes = "请求参数说明 [userId 用户ID][petId 宠物id] ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{{[petId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成][vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间] } }")})
    @PostMapping(value = "/follow", produces = "application/json")
    public BaseReturn follow(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getPetId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        UserPet userPet = userPetService.getUserPetByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());
        if (userPet == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物不存在");
        }
        if (userPet.getFollow() == PetConstant.PET_FOLLOW_YES) {
            userPet.setFollow(PetConstant.PET_FOLLOW_NO);
            userPetService.updateByPrimaryKey(userPet);
            PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());
            return new BaseReturn("休息一会!", petVO);
        }
        UserPet userPetFollow = userPetService.getUserPetByUserIdAndFollow(petPO.getUserId(), PetConstant.PET_FOLLOW_YES);
        if (userPetFollow != null) {
            userPetFollow.setFollow(PetConstant.PET_FOLLOW_NO);
            userPetFollow.setCreateDate(new Date());
            userPetService.updateByPrimaryKey(userPetFollow);
        }
        userPet.setFollow(PetConstant.PET_FOLLOW_YES);
        userPet.setCreateDate(new Date());
        userPetService.updateByPrimaryKey(userPet);
        PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());
        return new BaseReturn("跟随主人!", petVO);
    }


    /**
     * 修改宠物名称
     *
     * @param petPO
     * @return
     */
    @ApiOperation(value = "修改宠物名称", notes = "请求参数说明 [userId 用户ID][petId 宠物id] ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{{[petId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成] [vitalityAddition 生命属性加成百分比][attackAddition 物理攻击属性加成百分比] " +
                    "[spellAttacksAddition 法术攻击属性加成百分比] [pdefAddition 物理防御属性加成百分比][magdefAddition 法术防御属性加成百分比]" +
                    " [critAddition 暴击属性加成百分比] [dodgeAddition 闪避属性加成百分比] [hitRateAddition 命中属性加成百分比][defenseCritAddition 抗暴击属性加成百分比][level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间][petName 玩家自己修改的宠物名称] [updateDateName 修改名称时间] } }")})
    @PostMapping(value = "/updatePetName", produces = "application/json")
    public BaseReturn updatePetName(@RequestBody PetPO petPO) {
        if (StringUtils.isEmpty(petPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getPetId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }
        if (StringUtils.isEmpty(petPO.getPetName())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        UserPet userPet = userPetService.getUserPetByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());
        if (userPet == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物不存在");
        }
        if (userPet.getExpirationTime() != null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "限时宠物不能修改名称");
        }

        // 判断时间

        userPet.setPetName(petPO.getPetName());
        userPet.setUpdateDateName(new Date());
        userPetService.updateByPrimaryKey(userPet);

        PetVO petVO = userPetService.getPetVOByUserIdAndPetId(petPO.getUserId(), petPO.getPetId());

        return new BaseReturn("操作成功!", petVO);
    }

    /**
     * 根据道具id 获取宠物id
     *
     * @return
     */
    @ApiOperation(value = "根据道具id 获取宠物id", notes = "请求参数说明 [goodsId] ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")})
    @PostMapping(value = "/getPetid", produces = "application/json")
    public BaseReturn getPetid(@RequestBody PetPO petPO) {
        PropGiftInfo propGiftInfo = propGiftInfoService.getPropGiftInfo(petPO.getGoodsId() + "");
        if (propGiftInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物配置错误");
        }
        if (propGiftInfo.getType() != PacksackConstant.GIFT_TYPE_PET) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "不是宠物类型");
        }
        JSONObject jsonObject = JSON.parseObject(propGiftInfo.getGiftBagItems());
        String pet = jsonObject.getString("pet");
        if (StringUtils.isEmpty(pet)) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "配置错误");
        }
        return new BaseReturn("操作成功!", pet);
    }


}
