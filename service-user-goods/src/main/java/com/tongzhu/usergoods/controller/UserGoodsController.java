package com.tongzhu.usergoods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.constant.PetConstant;
import com.tongzhu.usergoods.constant.PropConstant;
import com.tongzhu.usergoods.domain.Role;
import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.enums.AdornEquipEnum;
import com.tongzhu.usergoods.enums.EnchantlvlEnum;
import com.tongzhu.usergoods.enums.GemLevelEnum;
import com.tongzhu.usergoods.mapper.vo.ArsenalInfoVO;
import com.tongzhu.usergoods.mapper.vo.EquipmentInfoVO;
import com.tongzhu.usergoods.mapper.vo.PetVO;
import com.tongzhu.usergoods.mapper.vo.PropInfoVO;
import com.tongzhu.usergoods.model.*;
import com.tongzhu.usergoods.model.EquipmentInfo;
import com.tongzhu.usergoods.model.PropInfo;
import com.tongzhu.usergoods.model.UserGoods;
import com.tongzhu.usergoods.po.UserGoodsPO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Api(value = "背包controller", tags = {"背包API"})
@RestController
@RequestMapping(value = "/userGoods")
public class UserGoodsController {

    private static Logger log = LoggerFactory.getLogger(UserGoodsController.class);

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private UserAdornEquipService userAdornEquipService;

    @Autowired
    private PropGiftInfoService propGiftInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TreeHouseFurnitureService treeHouseFurnitureService;


    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserPetService userPetService;

    @Autowired
    private PetLevelInfoService petLevelInfoService;

    @Autowired
    private UserWeddingRingLevelService userWeddingRingLevelService;

    @Autowired
    private UserGoodsWarehouseService userGoodsWarehouseService;

    @Autowired
    private TaskService taskService;


    /**
     * 将用户物品放入仓库
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "将用户物品放入仓库", notes = "请求参数说明 [id 物品主键id] [userId 用户id] [amount 数量]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述] [goods 背包物品数量] [warehouse  仓库物品数量]")
    })
    @PostMapping(value = "transferWarehouse", produces = "application/json")
    public BaseReturn transferWarehouse(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该物品不存在!");
        }

        String id = null;
        Object goodsId = null;
        Integer enchantlvl = null;
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
            }
            if (propInfo.getType() == PacksackConstant.PROP_TYPE_RESOURCE) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "资源不可以存入仓库!");
            }
//            if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
//                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "物品已经过期!");
//            }
            id = propInfo.getId();
            goodsId = propInfo.getId();
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "物品不存在!");
            }
//            if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
//                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "物品已经过期!");
//            }
            id = equipmentInfo.getIntensifyId();
            goodsId = equipmentInfo.getId();
            enchantlvl = equipmentInfo.getEnchantlvl();
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_STORAGE_ERROR, "该物品不能存入仓库");
        }


        // 判断物品是否存在背包里面
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_KNAPSACK) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该物品不存在背包中，无法存入仓库!");
        }
        if (userGoods.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品已经被摧毁!");
        }
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品已使用完!");
        }
//        if (userGoods.getSurplusDate() != null && !DateUtil.compareDate(userGoods.getSurplusDate())) {
//            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具已经过期!");
//        }

        //将物品存入仓库
        UserGoods goods = userGoodsService.transferWarehouse(userGoodsPO.getUserId(), userGoodsPO.getId(), userGoodsPO.getAmount());
        if (goods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "仓库已满!");
        }
        Map map = new HashMap();

        map.put("goods", ObjectUtil.getGoodsMap(id, goodsId, goods.getType(), goods.getAmount(), enchantlvl));
        UserGoodsWarehouse userGoodsWarehouse = userGoodsWarehouseService.getUserGoodsWarehouseById(userGoods.getId());
        map.put("warehouse", ObjectUtil.getGoodsMap(id, goodsId, userGoodsWarehouse.getType(), userGoodsWarehouse.getAmount(), enchantlvl));
        return new BaseReturn("操作成功!", map);
    }

    /**
     * 将物品从仓库中取出
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "将物品从仓库中取出", notes = "请求参数说明 [id 物品主键id]  [userId 用户id] [amount 数量 ]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述] [goods 背包物品数量] [warehouse  仓库物品数量]")
    })
    @PostMapping(value = "transferKnapsack", produces = "application/json")
    public BaseReturn transferKnapsack(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        UserGoodsWarehouse userGoodsWarehouse = userGoodsWarehouseService.getUserGoodsWarehouseByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());

        if (userGoodsWarehouse == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该物品在仓库中不存在!");
        }
        if (userGoodsWarehouse.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品已使用完!");
        }
        if (userGoodsWarehouse == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该物品不存在!");
        }
        if (userGoodsWarehouse.getAmount().intValue() < userGoodsPO.getAmount().intValue()) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数量不足，不能取出!");
        }

        String id = null;
        Object goodsId = null;
        Integer enchantlvl = null;
        if (userGoodsWarehouse.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoodsWarehouse.getGoodsId());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
            }
//            if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
//                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "物品已经过期!");
//            }
            id = propInfo.getId();
            goodsId = propInfo.getId();
        }
        if (userGoodsWarehouse.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoodsWarehouse.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
            }
//            if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
//                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具已经过期!");
//            }
            id = equipmentInfo.getIntensifyId();
            goodsId = equipmentInfo.getId();
            enchantlvl = equipmentInfo.getEnchantlvl();
        }


        UserGoods goods = userGoodsService.transferKnapsack(userGoodsPO.getUserId(), userGoodsPO.getId(), userGoodsPO.getAmount());
        if (goods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "背包已满!");
        }
        Map map = new HashMap();
        map.put("goods", ObjectUtil.getGoodsMap(id, goodsId, userGoodsWarehouse.getType(), goods.getAmount(), enchantlvl));
        map.put("warehouse", ObjectUtil.getGoodsMap(id, goodsId, userGoodsWarehouse.getType(), userGoodsWarehouse.getAmount() - userGoodsPO.getAmount(), enchantlvl));
        return new BaseReturn("操作成功!", map);
    }


    /**
     * 查询仓库物品
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询仓库物品", notes = "请求参数说明 [userId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = " {equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [goodsId 装备唯一键id]} " +
                    "        {propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量]} " +
                    " { equipmentInfoTime propInfoTime key 道具在背包中的主键id value (0:不限时,1限时,2过期)} "
            )
    })
    @PostMapping(value = "queryWarehouseUserGoods", produces = "application/json")
    public BaseReturn queryWarehouseUserGoods(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        Map<String, Object> map = new HashMap<>();
        Pager<PropInfoVO> pagerProp = propInfoService.queryWarehousePropList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY);
        for (PropInfoVO propInfoVO : pagerProp.getContent()) {
            if (propInfoVO.getSurplusDate() == null) {
                propInfoVO.setLimitTime(PacksackConstant.GOODS_UNLIMITED);
            } else if (propInfoVO.getSurplusDate().getTime() > new Date().getTime()) {
                propInfoVO.setLimitTime(PacksackConstant.GOODS_LIMITTIME);
            } else {
                propInfoVO.setLimitTime(PacksackConstant.GOODS_OVERDUE);
            }
        }
        for (PropInfoVO propInfoVO : pagerProp.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "goodsId", "amount", "limitTime");
        }
        map.put("propInfoList", pagerProp.getContent());
        Pager<EquipmentInfoVO> pagerEquipment = equipmentInfoService.queryWarehouseEquipmentInfoList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY);
        for (EquipmentInfoVO equipmentInfoVO : pagerEquipment.getContent()) {
            if (null == equipmentInfoVO.getSurplusDate()) {
                equipmentInfoVO.setLimitTime(PacksackConstant.GOODS_UNLIMITED);
            } else if (equipmentInfoVO.getSurplusDate().getTime() < new Date().getTime()) {
                equipmentInfoVO.setLimitTime(PacksackConstant.GOODS_OVERDUE);
            } else {
                equipmentInfoVO.setLimitTime(PacksackConstant.GOODS_LIMITTIME);
            }
        }

        for (EquipmentInfoVO equipmentInfoVO : pagerEquipment.getContent()) {
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "enchantlvl");
        }
        map.put("equipmentInfoList", pagerEquipment.getContent());

        return new BaseReturn("操作成功!", map);
    }


    /**
     * 摧毁物品
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "摧毁物品", notes = "请求参数说明 [id 物品主键id]  [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述]")})
    @PostMapping(value = "destroyUserGoods", produces = "application/json")
    public BaseReturn destroyUserGoods(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在!");
        }
        if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_WEAPON) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器无法摧毁!");
        }
        if (userGoods.getSettingPosition() == PacksackConstant.PROP_PLACE_ADORN) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "佩戴中，无法销毁!");
        }
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在");
        }
        String id = null;
        String goodsId = null;
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
            if (propInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
            }
            if (propInfo.getDestroy() == PacksackConstant.GOODS_OPERATION_CANNOT) {
                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_DESTROY_ERROR, "该道具不能销毁!");
            }
            id = propInfo.getId();
            goodsId = propInfo.getId();
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            if (equipmentInfo == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
            }
            if (equipmentInfo.getDestroy() == PacksackConstant.GOODS_OPERATION_CANNOT) {
                return new BaseReturn(BaseReturnCode.PROCESS_GOODS_DESTROY_ERROR, "该道具不能销毁!");
            }
            id = equipmentInfo.getIntensifyId();
            goodsId = equipmentInfo.getId() + "";
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_DESTROY_ERROR, "该道具不能销毁!");
        }

        int i = userGoodsService.destroyUserGoods(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (i == 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_DESTROY_ERROR, "销毁失败!");

        }
        Map goodsMap = ObjectUtil.getGoodsMap(id, goodsId, userGoods.getType(), 0, null);
        return new BaseReturn("操作成功!", goodsMap);
    }

    /**
     * 穿戴上装备
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "穿戴上装备", notes = "请求参数说明 [id 物品主键id]  [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 物品表主键id] [equipmentId 装备初始化id] [roleFight 角色战斗力] [wearGoods 穿戴的装备] [dischargeGoods 被替换的装备]")})
    @PostMapping(value = "wearEquipment", produces = "application/json")
    public BaseReturn wearEquipment(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在!");
        }
        if (userGoods.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已经被摧毁!");
        }
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已使用完!");
        }

        // 判断装备是否在背包中
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_KNAPSACK) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不在背包中，无法穿戴!");
        }
        if (userGoods.getType() != PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_TYPE_ERROR, "该道具不是装备类型");
        }
        if (userGoods.getSurplusDate() != null && !DateUtil.compareDate(userGoods.getSurplusDate())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "装备已经过期!");
        }
        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
        if (equipmentInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
        }


        // 判断道具是否是装备类型
        User user = userService.findByUserId(userGoods.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在!");
        }

        UserRole userRoleByUserId = roleService.getUserRoleByUserId(userGoods.getUserId());
        if (userRoleByUserId.getRoleLevel() < equipmentInfo.getLevel()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_INSUFFICIENT_LEVEL, "等级不够");
        }

        if (equipmentInfo.getSex() != 0 && user.getSex() != equipmentInfo.getSex()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_SEX_ERROR, "装备性别属性与角色性别不一致");
        }
        if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "装备已经过期!");
        }
        Role role = roleService.findByRole(user.getRoleId());
        if (!PacksackConstant.GODOS_PROFESSION_COMMONALITY.equals(equipmentInfo.getProfession()) && !equipmentInfo.getProfession().equals(role.getProfession())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_PROFESSION_ERROR, "职业不匹配，穿戴失败!");
        }
        if (equipmentInfo.getWearPosition() == AdornEquipEnum.WEDDING_RING.getId()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "婚戒不能主动带上!");
        }
        try {
            Map<String, Object> map = userGoodsService.wearEquipment(userGoods.getUserId(), userGoods.getGoodsId());

            Integer minEnchantlvl = userGoodsService.getMinEnchantlvl(userGoods.getUserId());
            if (minEnchantlvl != null && EnchantlvlEnum.getTaskId(minEnchantlvl) != null) {
                taskService.taskBranchFinish(EnchantlvlEnum.getTaskId(minEnchantlvl), userGoods.getUserId());
            }

            Integer sumGemLevel = userGoodsService.getSumGemLevel(userGoods.getUserId());
            if (sumGemLevel != null && GemLevelEnum.getTaskId(sumGemLevel) != null) {
                taskService.taskBranchFinish(GemLevelEnum.getTaskId(sumGemLevel), userGoods.getUserId());
            }
            UserAdornEquip userAdornEquip = (UserAdornEquip) map.get("userAdornEquip");
            map.put("roleFight", userAdornEquip.getAlwaysFighting());
            map.remove("userAdornEquip");
            return new BaseReturn("操作成功!", map);
        } catch (Exception e) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "穿戴失败");
        }
    }


    /**
     * 卸下装备
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "卸下装备", notes = "请求参数说明 [id 物品主键id]  [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 物品表主键id] [equipmentId 装备初始化id] [roleFight 角色战斗力]")})
    @PostMapping(value = "dischargeEquipment", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn dischargeEquipment(@RequestBody UserGoodsPO userGoodsPO) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备不存在!");
        }
        if (userGoods.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已经被摧毁!");
        }
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备已使用完!");
        }
        // 判断装备是否已经佩戴
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_ADORN) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "装备没有佩戴，无法卸下!");
        }
        // 获取装备信息
        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
        if (equipmentInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "装备不存在!");
        }
        if (equipmentInfo.getWearPosition() == AdornEquipEnum.WEDDING_RING.getId()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "婚戒不能主动脱下!");
        }
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());

        String element = (String) CommonUtil.getObjectElement(userAdornEquip, AdornEquipEnum.getWearPosition(equipmentInfo.getWearPosition()));
        if (!userGoods.getId().equals(element)) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据异常!");
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
        userGoods.setUpdateDate(new Date());
        userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_KNAPSACK);
        userGoodsService.updateByUserId(userGoods);


        Map goodsMap = ObjectUtil.getGoodsMap(equipmentInfo.getIntensifyId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, 1, equipmentInfo.getEnchantlvl());
        goodsMap.put("roleFight", userAdornEquip.getAlwaysFighting());
        goodsMap.put("enchantlvl", equipmentInfo.getEnchantlvl());
        return new BaseReturn("操作成功!", goodsMap);
    }


    /**
     * 穿戴上武器
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "穿戴上武器", notes = "请求参数说明 [userId 用户ID] [id 物品id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 用户穿戴表主键id] [userId 用户id] [head 头盔][clothing 衣服][trousers 裤子][shoe 鞋子][fashion 时装][weddingRing 婚戒]" +
                    "[weapon 武器][ring 戒指][cuff 护腕][necklace 项链][jewelry 首饰][alwaysFighting 总战斗力][vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成]" +
                    "[pdef 物理防御属性加成][magdef 法术防御属性加成] [crit 暴击属性加成][dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成]")})
    @PostMapping(value = "wearWeapons", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn wearWeapons(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器不存在!");
        }
        if (userGoods.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器已经被摧毁!");
        }
        if (userGoods.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器已使用完!");
        }


        if (userGoods.getType() != PacksackConstant.GOODS_TYPE_WEAPON) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_TYPE_ERROR, "该道具不是武器类型");
        }

        // 判断装备是否在武器库中
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_WEAPON) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该武器已佩戴!");
        }
        if (userGoods.getSurplusDate() != null && !DateUtil.compareDate(userGoods.getSurplusDate())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "武器已经过期!");
        }


        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
        if (arsenalInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器不存在!");
        }

        // 判断用户等级是否满足
        User user = userService.findByUserId(userGoods.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在!");
        }
        UserRole userRoleByUserId = roleService.getUserRoleByUserId(userGoods.getUserId());
        if (userRoleByUserId.getRoleLevel() < arsenalInfo.getLevel()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_INSUFFICIENT_LEVEL, "等级不够");
        }
        if (arsenalInfo.getSex() != 0 && user.getSex() != arsenalInfo.getSex()) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_SEX_ERROR, "武器性别属性与角色性别不一致");
        }
        if (arsenalInfo.getExpirationTime() != null && !DateUtil.compareDate(arsenalInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "武器已经过期!");
        }

        Role role = roleService.findByRole(user.getRoleId());
        if (!PacksackConstant.GODOS_PROFESSION_COMMONALITY.equals(arsenalInfo.getProfession()) && !arsenalInfo.getProfession().equals(role.getProfession())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_PROFESSION_ERROR, "职业不匹配，穿戴失败!");
        }
        try {
            Map<String, Object> map = userGoodsService.wearWeapons(userGoods.getUserId(), userGoods.getGoodsId());

            Integer minEnchantlvl = userGoodsService.getMinEnchantlvl(userGoods.getUserId());
            if (minEnchantlvl != null && EnchantlvlEnum.getTaskId(minEnchantlvl) != null) {
                taskService.taskBranchFinish(EnchantlvlEnum.getTaskId(minEnchantlvl), userGoods.getUserId());
            }

            Integer sumGemLevel = userGoodsService.getSumGemLevel(userGoods.getUserId());
            if (sumGemLevel != null && GemLevelEnum.getTaskId(sumGemLevel) != null) {
                taskService.taskBranchFinish(GemLevelEnum.getTaskId(sumGemLevel), userGoods.getUserId());
            }

            UserAdornEquip userAdornEquip = (UserAdornEquip) map.get("userAdornEquip");
            map.put("roleFight", userAdornEquip.getAlwaysFighting());
            map.remove("userAdornEquip");
            return new BaseReturn("操作成功!", map);
        } catch (Exception e) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "穿戴失败");
        }
    }

    /**
     * 卸下武器
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "卸下武器", notes = "请求参数说明 [userId 用户ID] [goodsId 物品id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 用户穿戴表主键id] [userId 用户id] [head 头盔][clothing 衣服][trousers 裤子][shoe 鞋子][fashion 时装][weddingRing 婚戒]" +
                    "[weapon 武器][ring 戒指][cuff 护腕][necklace 项链][jewelry 首饰][alwaysFighting 总战斗力][vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成]" +
                    "[pdef 物理防御属性加成][magdef 法术防御属性加成] [crit 暴击属性加成][dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成]")})
    @PostMapping(value = "dischargeWeapons", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn dischargeWeapons(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器不存在!");
        }
        // 判断装备是否已经佩戴
        if (userGoods.getSettingPosition() != PacksackConstant.PROP_PLACE_ADORN) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器没有佩戴，无法卸下!");
        }
        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
        if (arsenalInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "武器不存在!");
        }

        // 获取装备信息
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());

        String weapon = userAdornEquip.getWeapon();
        if (!userGoods.getId().equals(weapon)) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "数据异常!");
        }
        userAdornEquip.setWeapon("");
        userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() - arsenalInfo.getFightingCapacity());
        userAdornEquip.setVitality(userAdornEquip.getVitality() - arsenalInfo.getVitality());
        userAdornEquip.setAttack(userAdornEquip.getAttack() - arsenalInfo.getAttack());
        userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() - arsenalInfo.getSpellAttacks());
        userAdornEquip.setPdef(userAdornEquip.getPdef() - arsenalInfo.getPdef());
        userAdornEquip.setMagdef(userAdornEquip.getMagdef() - arsenalInfo.getMagdef());
        userAdornEquip.setCrit(userAdornEquip.getCrit() - arsenalInfo.getCrit());
        userAdornEquip.setDodge(userAdornEquip.getDodge() - arsenalInfo.getDodge());
        userAdornEquip.setHitRate(userAdornEquip.getHitRate() - arsenalInfo.getHitRate());
        userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() - arsenalInfo.getDefenseCrit());
        userAdornEquipService.updateUserAddornEquip(userAdornEquip);
        userGoods.setUpdateDate(new Date());
        userGoods.setSettingPosition(PacksackConstant.PROP_PLACE_WEAPON);
        userGoods.setType(PacksackConstant.GOODS_TYPE_WEAPON);
        userGoodsService.updateByUserId(userGoods);
        Map goodsMap = ObjectUtil.getGoodsMap(arsenalInfo.getIntensifyId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, 1, arsenalInfo.getEnchantlvl());
        goodsMap.put("roleFight", userAdornEquip.getAlwaysFighting());
        goodsMap.put("enchantlvl", arsenalInfo.getEnchantlvl());
        return new BaseReturn("操作成功!", goodsMap);
    }


    /**
     * 用户获得武器
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "用户获得武器", notes = "请求参数说明 [userId 用户ID] [goodsId 武器id] [数量 固定单个] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述]")})
    @PostMapping(value = "addGoodsWeapon", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn addGoodsWeapon(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal(userGoodsPO.getGoodsId(), PacksackConstant.GOODS_ORIGINAL_YES);
        if (arsenalInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "武器不存在!");
        }
        if (arsenalInfo.getExpirationTime() != null && !DateUtil.compareDate(arsenalInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "武器已经过期!");
        }
        UserGoods userGoods = userGoodsService.addEquipAndWeapon(userGoodsPO.getUserId(), PacksackConstant.GOODS_TYPE_WEAPON, arsenalInfo.getId());
        if (userGoods != null) {
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), arsenalInfo.getId(), PacksackConstant.GOODS_TYPE_WEAPON, userGoods.getAmount(), arsenalInfo.getEnchantlvl());
            return new BaseReturn("操作成功!", goodsMap);
        }
        return new BaseReturn("操作成功!");
    }

    /**
     * 用户获得装备
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "用户获得装备", notes = "请求参数说明 [userId 用户ID] [goodsId 装备id] [数量 固定单个] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述]")})
    @PostMapping(value = "addGoodsEquipment", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn addGoodsEquipment(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfoByIdAndOriginal(userGoodsPO.getGoodsId(), PacksackConstant.GOODS_ORIGINAL_YES);
        if (equipmentInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "装备不存在!");
        }
        if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "装备已经过期!");
        }
        UserGoods userGoods = userGoodsService.addEquipAndWeapon(userGoodsPO.getUserId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, equipmentInfo.getId());
        if (userGoods != null) {
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), equipmentInfo.getId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, userGoods.getAmount(), equipmentInfo.getEnchantlvl());
            return new BaseReturn("操作成功!", goodsMap);
        }
        return new BaseReturn("操作成功!");
    }


    /**
     * 用户获得道具物品
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "用户获得道具物品", notes = "请求参数说明 [userId 用户ID] [goodsId 装备id] [amount 数量 ] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述]")})
    @PostMapping(value = "addGoodsProp", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn addGoodsProp(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        PropInfo propInfo = propInfoService.getPropInfo(userGoodsPO.getGoodsId());
        if (propInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_USER_GOODS_INEXISTENCE, "道具不存在!");
        }
        if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具已经过期!");
        }

        UserGoods userGoods = userGoodsService.addUserGoodsSingle(userGoodsPO.getUserId(), userGoodsPO.getGoodsId(), userGoodsPO.getAmount());
        return new BaseReturn("操作成功!", userGoods);
    }

    /**
     * 出售装备
     *
     * @param userGoodsPO
     * @return
     */

    @ApiOperation(value = "出售装备", notes = "请求参数说明 [id 物品仓库表id主键] [amount 数量] [userId 用户id] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述] data 卖掉装备后增加的物品{[id 物品仓库表id主键] [userId 用户id][goodsId 物品id][amount 物品拥有数量]" +
                    "[createDate ][status 状态 0 正常 1 删除][settingPosition 物品存放的位置 0 背包 1仓库 2 已佩戴 3 其它 4武器库][type  0 道具 1 装备 2 武器]}")})
    @PostMapping(value = "sellEquipment", produces = "application/json")
    public BaseReturn sellEquipment(@RequestBody UserGoodsPO userGoodsPO) throws Exception {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getGoodsId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null && userGoodsPO.getAmount() > 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "此物品不存在!");
        }

        if (userGoods.getAmount() - userGoodsPO.getAmount() < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品数量不足!");
        }
        if (userGoods.getType() != PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_TYPE_ERROR, "该物品不是装备!");
        }

        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
        if (equipmentInfo.getExpirationTime() != null && !DateUtil.compareDate(equipmentInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "装备已经过期!");
        }
        if (equipmentInfo.getSell() == PacksackConstant.GOODS_OPERATION_CANNOT) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_SELL_ERROR, "装备不能出售!");
        }
        UserGoods userGoodsRet = userGoodsService.addUserGoodsSingle(userGoods.getUserId(), equipmentInfo.getConversionPropId(), userGoodsPO.getAmount());
        userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
        return new BaseReturn("操作成功!", userGoodsRet);
    }

    /**
     * 出售道具物品
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "出售道具物品", notes = "请求参数说明 [userId 用户ID] [goodsId 物品id]  [amount 数量] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[success true 成功] [code 0 成功] [msg 描述] data 卖掉道具后增加的物品{[id 物品仓库表id主键] [userId 用户id][goodsId 物品id][amount 物品拥有数量]" +
                    "[createDate ][status 状态 0 正常 1 删除][settingPosition 物品存放的位置 0 背包 1仓库 2 已佩戴 3 其它 4武器库][type  0 道具 1 装备 2 武器]}")})
    @PostMapping(value = "sellProp", produces = "application/json")
    public BaseReturn sellProp(@RequestBody UserGoodsPO userGoodsPO) throws Exception {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "此物品不存在!");
        }

        if (userGoods.getAmount() - userGoodsPO.getAmount() < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品数量不足!");
        }


        PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
        if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "装备已经过期!");
        }
        if (propInfo.getSell() == PacksackConstant.GOODS_OPERATION_CANNOT) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_SELL_ERROR, "该装备不能出售!");
        }
        Map retMap = new HashMap();
        UserGoods userGoodsRet = userGoodsService.addUserGoodsSingle(userGoods.getUserId(), propInfo.getConversionPropId(), propInfo.getConversionAmount());
        Map goodsMap = ObjectUtil.getGoodsMap(userGoodsRet.getGoodsId(), userGoodsRet.getGoodsId(), 1, userGoodsRet.getAmount(), null);
        retMap.put("retGoods",goodsMap);
        UserGoods userGoods1 = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(), true);
        Map goods = ObjectUtil.getGoodsMap(userGoods1.getGoodsId(), userGoods1.getGoodsId(), 1, userGoods1.getAmount(), null);
        retMap.put("goods",goods);
        return new BaseReturn("操作成功!", retMap);
    }

    /**
     * 查询用户背包的装备
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询用户背包", notes = "请求参数说明 [userId 用户ID]  [settingPosition 0背包 ]")
    @ApiResponses({
            @ApiResponse(code = 200, message = " {equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [goodsId 装备唯一键id]} " +
                    "        {propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量]}  { userGoodsLimitTime key 道具在背包中的主键id value (0:不限时,1限时,2过期)} "
            )
    })
    @PostMapping(value = "queryUserGoods", produces = "application/json")
    public BaseReturn queryUserGoods(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getSettingPosition() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误");
        }

        Map<String, Object> map = new HashMap<>();
        Pager<PropInfoVO> pagerProp = propInfoService.queryPropList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY, userGoodsPO.getSettingPosition());
        for (PropInfoVO propInfoVO : pagerProp.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "goodsId", "amount");
        }
        map.put("propInfoList", pagerProp.getContent());
        Pager<EquipmentInfoVO> pagerEquipment = equipmentInfoService.queryEquipmentInfoList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY, userGoodsPO.getSettingPosition());

        for (EquipmentInfoVO equipmentInfoVO : pagerEquipment.getContent()) {
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId");
        }
        map.put("equipmentInfoList", pagerEquipment.getContent());
        BaseReturn baseReturn = queryUserGoodsTou(userGoodsPO);
        map.put("userGoodsLimitTime", baseReturn.getData());
        return new BaseReturn("操作成功!", map);
    }


    /**
     * 获取用户拥有的武器
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "获取用户拥有的武器", notes = "请求参数说明 [userId 用户ID] [pageNum 页号] " +
            "[pageSize 每页大小] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[currentPage 当前页码] [pageSize 每页记录数] [pageTotal 总页数] [recordTotal 总记录数]" +
                    "[id 背包物品id主键][userId 用户ID] [goodsId 物品id] [amount 物品剩余数量] [gainDate 物品获得时间]  [status 状态 0 正常 1 摧毁]" +
                    "[settingPosition 物品存放的位置 0 背包 1仓库 ]  [type 装备类型  0 道具 1 装备 2 武器][equipmentId 装备基础id]  [name 物品名称]" +
                    "[arsenalType 武器类型 1 普通武器 2 投掷武器] [profession 角色要求 0 通用 1 卫士 2 刺客 3 工匠 4 法师]" +
                    "[quality  物品品质 1白、2绿、3蓝、4紫、5橙、6红、7金  ] [fightingCapacity 道具增加的战斗力][level 道具使用要求玩家的等级]" +
                    "[sex 性别要求 0 无要求 1 男 2 女] [description 道具说明] [acquiringWay 道具获取途径][expirationTime 道具到期时间，超过这段时间道具就失效]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成] [crit 暴击属性加成]" +
                    "[dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成] [conversionPropId 该道具对应的价值（道具id） ]" +
                    "[conversionAmount 对于的数量][original 是否为原型装备 0 原型装备 1 否 强化过的就不是原型装备] [guardiansMale卫士男外观]" +
                    "[guardiansFemale 卫士女外观][assassinMale 刺客男外观][assassinFemale 刺客女外观][craftsMale 工匠男外观] [craftsFemale 工匠女外观]" +
                    "[masterMale 法师男外观][masterFemale 法师女外观] [storage 是否可以存仓库 0 可以 1 禁止存入仓库] [binding 获得装备是否绑定 0 不绑定 1 绑定 绑定的装备不能进行交易出售 只能进行销毁]" +
                    "[deal 是否能进行交易 0 可以 1 不可以 ][sell 是否能进行出售 0 可以 1 不可以][destroy 是否能进行销毁 0 可以 1 不可以] [inlay 是否能进行镶嵌宝石 0 可以 1 不可以]" +
                    "[profession 职业要求 0 通用 1 卫士 2 刺客 3 工匠 4 法师][time 使用时限 0 永久]"
            )
    })
    @PostMapping(value = "queryWeapon", produces = "application/json")
    public BaseReturn queryWeapon(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (userGoodsPO.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");
        Pager<ArsenalInfoVO> pager = arsenalInfoService.queryWeaponList(userGoodsPO.getUserId(), userGoodsPO.getPageNum(), userGoodsPO.getPageSize(), null);
        return new BaseReturn("操作成功!", pager);
    }


    /**
     * 打开礼包道具
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "打开礼包道具", notes = "请求参数说明 [userId 用户ID] [id 物品id] [amount 使用数量]}  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{宠物卡   [intensifyId 宠物唯一id][id 宠物id][fightingCapacity 宠物战斗力]" +
                    "[vitality 生命属性加成][attack 物理攻击属性加成] [spellAttacks 法术攻击属性加成] [pdef 物理防御属性加成][magdef 法术防御属性加成]" +
                    " [crit 暴击属性加成] [dodge 闪避属性加成] [hitRate 命中属性加成][defenseCrit 抗暴击属性加成] [level 等级] [follow 是否跟随 1 不跟随 2 跟随] " +
                    "[exp 当前经验 ][upgradeExp 升级经验] [expirationTime 宠物过期时间] [addTime 限时宠物增加的时间 秒] [addExp 宠物增加的经验]} {goods 礼包道具详情} {打开道具礼包 propGoods 打开后道具数量 }")})
    @PostMapping(value = "openPresent", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public BaseReturn openPresent(@RequestBody UserGoodsPO userGoodsPO) throws Exception {
        log.info("打开礼包");
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "此物品不存在!");
        }
        if (userGoods.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品已被摧毁!");
        }
        if (userGoods.getAmount() - userGoodsPO.getAmount() < 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品数量不足!");
        }

        PropGiftInfo propGiftInfo = propGiftInfoService.getPropGiftInfo(userGoodsPO.getId());
        if (propGiftInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置不存在!");
        }
        if (propGiftInfo.getStatus() != PacksackConstant.GOODS_STATUS_NORMAL) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置不存在!");
        }
        User user = userService.findByUserId(userGoods.getUserId());
        if (user == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在!");
        }
        if (propGiftInfo.getVipLevel() > user.getVip()) {
            return new BaseReturn(BaseReturnCode.PROCESS_VIP_INSUFFICIENT_LEVEL, "vip等级不够!");
        }
//        if (propInfo.getSex() != 0 && user.getSex() != propInfo.getSex()) {
//            return new BaseReturn(BaseReturnCode.PROCESS_USER_SEX_ERROR, "装备性别属性与角色性别不一致");
//        }
        String giftBagItems = propGiftInfo.getGiftBagItems();
        Map repMap = new HashMap();
        if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE) {
            Map<Integer, Map<Object, Integer>> map = new HashMap<>();
            try {
                JSONObject jsonObject = JSON.parseObject(giftBagItems);
                // {"skdflwena12owsdf23u12":"1-1@100-1",1:"10-23,30-45,40-23@100-0"}
                // 礼包数据格式   {1 :"10-23,30-45,40-23@60",2:"10-23,30-45,40-23@60-1"}  key 道具id:道具的数量-出现此数量的概率,道具的数量-出现此数量的概率@此道具出现的概率-道具的类型 0 道具 1 装备 2 武器
                for (int i = 0; i < userGoodsPO.getAmount(); i++) {
                    for (Object key : jsonObject.keySet()) {
                        String value = (String) jsonObject.get(key);
                        String[] splitValue = value.split("@");
                        String[] pl = splitValue[1].split("-");
                        int type = Integer.parseInt(pl[1]);
                        Random random = new Random();
                        int ra = random.nextInt(100);
                        if (Integer.parseInt(pl[0]) > ra) {
                            Map<String, Integer> luckyDrawMap = redisService.getMap(PacksackConstant.GIFT_BAG_ITEMS + propGiftInfo.getPropId(), new Integer(0));
                            Integer sumWeight = (Integer) redisService.get(PacksackConstant.GIFT_BAG_ITEMS_WEIGHT + propGiftInfo.getPropId());
                            if (luckyDrawMap == null) {
                                String str = splitValue[0];
                                String[] randomAssortment = str.split(",");
                                luckyDrawMap = new HashMap<>();
                                int record = 0;
                                for (String ran : randomAssortment) {
                                    String[] split = ran.split("-");
                                    luckyDrawMap.put(CommonUtil.getProbabilityList(record, record + Integer.parseInt(split[1])), Integer.parseInt(split[0]));
                                    record += Integer.parseInt(split[1]);
                                }
                                redisService.setMap(PacksackConstant.GIFT_BAG_ITEMS + propGiftInfo.getPropId() + key, luckyDrawMap);
                            }

                            if (sumWeight == null) {
                                String str = splitValue[0];
                                String[] randomAssortment = str.split(",");
                                sumWeight = 0;
                                for (String ran : randomAssortment) {
                                    String[] split = ran.split("-");
                                    sumWeight = sumWeight + Integer.parseInt(split[1]);
                                }
                                redisService.set(PacksackConstant.GIFT_BAG_ITEMS_WEIGHT + propGiftInfo.getPropId() + key, sumWeight);
                            }
                            Integer itemCount = CommonUtil.getItemCount(luckyDrawMap, sumWeight);
                            if (map.containsKey(type)) {
                                Map<Object, Integer> goodsMap = map.get(type);
                                if (goodsMap.containsKey(key)) {
                                    goodsMap.put(key, goodsMap.get(key) + itemCount);
                                    map.put(type, goodsMap);
                                } else {
                                    goodsMap.put(key, itemCount);
                                    map.put(type, goodsMap);
                                }
                            } else {
                                Map<Object, Integer> goodsMap = new HashMap<>();
                                goodsMap.put(key, itemCount);
                                map.put(type, goodsMap);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "打开礼包错误!");
            }
            List<UserGoods> userGoodsList = new ArrayList<>();
            for (Integer type : map.keySet()) {
                Map<Object, Integer> goodsMap = map.get(type);
                if (type == PacksackConstant.GOODS_TYPE_PROP) {
                    for (Object goodsId : goodsMap.keySet()) {
                        PropInfo prop = propInfoService.getPropInfo(goodsId + "");
                        if (prop == null) {
                            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置异常!");
                        }
                        UserGoods userGoodsRet = userGoodsService.addUserGoodsSingle(userGoods.getUserId(), goodsId + "", goodsMap.get(goodsId));
                        userGoodsList.add(userGoodsRet);
                    }
                } else if (type == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
                    for (Object goodsId : goodsMap.keySet()) {
                        EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(goodsId + "");
                        if (equipmentInfo == null) { //
                            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置异常!");
                        }
                        for (int i = 0; i < goodsMap.get(goodsId); i++) {
                            UserGoods userGoodsEquipment = userGoodsService.addEquipAndWeapon(userGoods.getUserId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, equipmentInfo.getId());
                            if (userGoodsEquipment != null) {
                                userGoodsList.add(userGoodsEquipment);
                            }
                        }
                    }
                } else if (type == PacksackConstant.GOODS_TYPE_WEAPON) {
                    for (Object goodsId : goodsMap.keySet()) {
                        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(goodsId + "");
                        if (arsenalInfo == null) {
                            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "礼包配置异常!");
                        }
                        for (int i = 0; i < goodsMap.get(goodsId); i++) {//
                            UserGoods arsenalUserGoods = userGoodsService.addEquipAndWeapon(userGoods.getUserId(), PacksackConstant.GOODS_TYPE_WEAPON, arsenalInfo.getId());
                            if (arsenalUserGoods != null) {
                                userGoodsList.add(arsenalUserGoods);
                            }
                        }
                    }
                } else {
                    throw new Exception("礼包配置异常");
                }
            }
            userGoodsService.subUserGoodsById(userGoodsPO.getId(), userGoodsPO.getAmount(),true);
        } else if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE_PET) {
            if (userGoodsPO.getAmount() != 1) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "只能使用一张宠物卡!");
            }
            //{"pet":"028233b0155c4e15a3eb1c4b76faf2cd","exp":800}
            JSONObject jsonObject = JSON.parseObject(giftBagItems);
            String pet = jsonObject.getString("pet");
            Integer time = jsonObject.getInteger("time");

            UserPet userPet = userPetService.getUserPetByUserIdAnId(Integer.parseInt(pet), userGoods.getUserId());
            if (userPet == null) {
                PetInfo petInfo = petInfoService.getPetInfoByIdAndLevel(Integer.parseInt(pet), PetConstant.LEVEL_MIN);
                if (petInfo == null) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物卡配置异常!");
                }
                userPet = new UserPet();
                userPet.setUserId(userGoods.getUserId());
                userPet.setExp(0);
                userPet.setCreateDate(new Date());
                userPet.setPetId(petInfo.getPetId());
                userPet.setId(UUIDUtil.generateUUID());
                userPet.setFollow(PetConstant.PET_FOLLOW_NO);
                if (time != null && time > 0) {
                    userPet.setExpirationTime(DateUtil.computingTime(time));
                }
                userPet.setPetName(petInfo.getName());
                userPetService.insert(userPet);
                UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoods.getUserId());
                userAdornEquip.setAttack(userAdornEquip.getAttack() + petInfo.getAttack());
                userAdornEquip.setSpellAttacks(userAdornEquip.getSpellAttacks() + petInfo.getSpellAttacks());
                userAdornEquip.setPdef(userAdornEquip.getPdef() + petInfo.getPdef());
                userAdornEquip.setMagdef(userAdornEquip.getMagdef() + petInfo.getMagdef());
                userAdornEquip.setVitality(userAdornEquip.getVitality() + petInfo.getVitality());
                userAdornEquip.setHitRate(userAdornEquip.getHitRate() + petInfo.getHitRate());
                userAdornEquip.setDodge(userAdornEquip.getDodge() + petInfo.getDodge());
                userAdornEquip.setCrit(userAdornEquip.getCrit() + petInfo.getCrit());
                userAdornEquip.setDefenseCrit(userAdornEquip.getDefenseCrit() + petInfo.getDefenseCrit());
                userAdornEquip.setAlwaysFighting(userAdornEquip.getAlwaysFighting() + petInfo.getFightingCapacity());
                userAdornEquipService.updateUserAddornEquip(userAdornEquip);
            } else {
                if (time != null && time > 0) {
                    userPet.setExpirationTime((DateUtil.computingTime(userPet.getExpirationTime(), time)));
                    userPetService.updateByPrimaryKey(userPet);
                    repMap.put("addTime", time);
                } else {
                    Integer expMin = jsonObject.getInteger("exp_mini");
                    Integer expMax = jsonObject.getInteger("exp_max");
                    Random random = new Random();

                    PetVO petVO = userPetService.getPetVOByUserIdAndPetId(userPet.getUserId(), userPet.getPetId());
                    UserRole userRoleByUserId = roleService.getUserRoleByUserId(userPet.getUserId());
                    if (userRoleByUserId == null) {
                        return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在");
                    }
                    if (petVO == null) {
                        return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物不存在");
                    }
                    if (petVO.getLevel() - userRoleByUserId.getRoleLevel() >= PetConstant.PET_USER_LEVEL && petVO.getUpgradeExp().intValue() - petVO.getExp().intValue() == 0) {
                        return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物已超过角色等级太多，暂不能使用此卡");
                    }
                    if (petVO.getLevel() == PetConstant.LEVEL_MAX) {
                        return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "宠物已经满级无法继续增加经验");
                    }
                    int exp = random.nextInt(expMax - expMin);
                    repMap.put("addExp", expMin + exp);
                    userPetService.userFeedPet(userGoods.getUserId(), userPet.getPetId(), expMin + exp, null);
                }
            }
            UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
            repMap.put("goods", goodsMap);
        } else if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE_PROP) {
            JSONObject jsonObject = JSON.parseObject(giftBagItems);
            List<Map> list = new ArrayList<>();
            LinkedHashSet<String> goodsList = new LinkedHashSet();
            if (userGoods.getGoodsId().equals("11008") || userGoods.getGoodsId().equals("11009")) {
                if (userGoodsPO.getAmount().intValue() > 5) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "已超过该道具使用上限!");
                }

                if (userGoods.getUpdateDate() != null && DateUtil.isSameDate(new Date(), userGoods.getUpdateDate()) && userGoods.getNumber().intValue() + userGoodsPO.getAmount().intValue() > 5) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具已达到使用上限!");
                }
            }
            for (int i = 0; i < userGoodsPO.getAmount(); i++) {
                for (Object key : jsonObject.keySet()) {
                    PropInfo prop = propInfoService.getPropInfo((String) key);
                    if (prop == null) {
                        continue;
                    }
                    UserGoods goods = userGoodsService.addUserGoodsSingle(userGoodsPO.getUserId(), prop.getId(), (Integer) jsonObject.get(key));
                    goodsList.add(goods.getGoodsId());
                }
            }
            for (String goodsId : goodsList) {
                UserGoods byUserIdAndGoodsId = userGoodsService.getByUserIdAndGoodsId(userGoods.getUserId(), goodsId);
                Map goodsMap = ObjectUtil.getGoodsMap(byUserIdAndGoodsId.getGoodsId(), byUserIdAndGoodsId.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, byUserIdAndGoodsId.getAmount(), null);
                list.add(goodsMap);
            }
            repMap.put("propGoods", list);
            UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
            repMap.put("goods", goodsMap);
        } else if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE_EQUIPMENT) {
            JSONObject jsonObject = JSON.parseObject(giftBagItems);
            List<Map> list = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            for (Object key : jsonObject.keySet()) {
                EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfoByIdAndOriginal((String) key, PacksackConstant.GOODS_ORIGINAL_YES);
                if (equipmentInfo == null) {
                    continue;
                }
//                UserGoods equip = userGoodsService.addEquipAndWeapon(userGoodsPO.getUserId(), PacksackConstant.GOODS_TYPE_EQUIPMENT, Integer.parseInt(key + ""));
//                if (equip != null) {
//                    Map goodsMap = ObjectUtil.getGoodsMap(equip.getGoodsId(), Integer.parseInt(key + ""), PacksackConstant.GOODS_TYPE_EQUIPMENT, equip.getAmount(), equipmentInfo.getEnchantlvl());
//                    list.add(goodsMap);
//                }
                map.put(equipmentInfo.getId() + "", PacksackConstant.GOODS_TYPE_EQUIPMENT);
            }
            int number = userGoodsService.getBackpacksNumber(userGoodsPO.getUserId(), PacksackConstant.PROP_PLACE_KNAPSACK);
            if (PacksackConstant.KNAPSACK_CAPACITY - number < map.size()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足!");
            }
            list.addAll(userGoodsService.addEquipAndWeaponList(userGoodsPO.getUserId(), map));
            repMap.put("propGoods", list);
            UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
            repMap.put("goods", goodsMap);
        } else if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE_ARSENAL) {
            JSONObject jsonObject = JSON.parseObject(giftBagItems);
            List<Map> list = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            for (Object key : jsonObject.keySet()) {
                ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfoByIdAndOriginal((String) key, PacksackConstant.GOODS_ORIGINAL_YES);
                if (arsenalInfo == null) {
                    continue;
                }
                map.put(arsenalInfo.getId() + "", PacksackConstant.GOODS_TYPE_WEAPON);
            }
            int number = userGoodsService.getBackpacksNumber(userGoodsPO.getUserId(), PacksackConstant.PROP_PLACE_KNAPSACK);
            if (PacksackConstant.KNAPSACK_CAPACITY - number < map.size()) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足!");
            }
            list.addAll(userGoodsService.addEquipAndWeaponList(userGoodsPO.getUserId(), map));
            repMap.put("propGoods", list);
            UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
            repMap.put("goods", goodsMap);
        } else if (propGiftInfo.getType() == PacksackConstant.GIFT_TYPE_TREE_HOUSE) {
            if (userGoodsPO.getAmount() != 1) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "只能使用一张家具卡!");
            }
            PropInfo prop = propInfoService.getPropInfo(propGiftInfo.getPropId());
            if (prop == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "家具卡不存在!");
            }
            treeHouseFurnitureService.addFurniture(userGoods.getUserId(), userGoods.getGoodsId());
            UserGoods goods = userGoodsService.subUserGoodsById(userGoods.getId(), userGoodsPO.getAmount(),true);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, goods.getAmount(), null);
            repMap.put("goods", goodsMap);
        }
        return new BaseReturn("操作成功!", repMap);
    }


    /**
     * 查询物品数量
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询物品数量", notes = "请求参数说明 [userId 用户ID]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping(value = "/findUserGoodsByGoodsId", produces = "application/json")
    public BaseReturn findUserGoodsByGoodsId(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        }
        if (userGoodsPO.getGoodsId() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "物品ID为空!");
        }
        UserGoods goods = userGoodsService.findByUserIdAndGoodsIdNoneWith0(userGoodsPO.getUserId(), userGoodsPO.getGoodsId());
        if (goods == null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未获得该物品！");
        return new BaseReturn("操作成功!", goods);
    }


    /**
     * 查询用户物品过期时间
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询用户物品过期时间", notes = "请求参数说明 [userId 用户ID][settingPosition 0 背包]  [userId 用户id]  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "key 道具在背包中的主键id value (0:不限时,1限时,2过期)  dressed 穿戴的装备")})
    @PostMapping(value = "/queryUserGoodsTou", produces = "application/json")
    public BaseReturn queryUserGoodsTou(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        }
        if (userGoodsPO.getSettingPosition() == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "参数错误!");
        }

        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoodsPO.getUserId());
        if (userAdornEquip == null) {
            userAdornEquip = new UserAdornEquip();
            userAdornEquip.setAlwaysFighting(0.0);
            userAdornEquip.setUserId(userGoodsPO.getUserId());
            userAdornEquip.setId(UUIDUtil.generateUUID());
            userAdornEquipService.addUserAddornEquip(userAdornEquip);
        }

        Pager<PropInfoVO> pagerPropInfo = propInfoService.queryPropList(userGoodsPO.getUserId(), 0, PacksackConstant.KNAPSACK_CAPACITY, userGoodsPO.getSettingPosition());
        List propLits = new ArrayList();
        for (PropInfoVO propInfoVO : pagerPropInfo.getContent()) {
            Map<String, Object> propMap = new HashMap<>();
            propMap.put("id", propInfoVO.getGoodsId());
            propMap.put("goodsId", propInfoVO.getGoodsId());
            if (propInfoVO.getSurplusDate() == null) {
                propMap.put("limitTime", PacksackConstant.GOODS_UNLIMITED);
            } else if (propInfoVO.getSurplusDate().getTime() > new Date().getTime()) {
                propMap.put("limitTime", PacksackConstant.GOODS_LIMITTIME);
            } else {
                propMap.put("limitTime", PacksackConstant.GOODS_OVERDUE);
            }
            propLits.add(propMap);
        }

        Pager<EquipmentInfoVO> pagerEquipment = equipmentInfoService.queryEquipmentInfoList(userGoodsPO.getUserId(), 0, PacksackConstant.KNAPSACK_CAPACITY, userGoodsPO.getSettingPosition());
        List equipmentLits = new ArrayList();
        for (EquipmentInfoVO equipmentInfoVO : pagerEquipment.getContent()) {
            Map<String, Object> equipmentMap = new HashMap<>();
            equipmentMap.put("id", equipmentInfoVO.getGoodsId());
            equipmentMap.put("equipmentId", equipmentInfoVO.getEquipmentId());
            if (null == equipmentInfoVO.getSurplusDate()) {
                equipmentMap.put("limitTime", PacksackConstant.GOODS_UNLIMITED);
            } else if (equipmentInfoVO.getSurplusDate().getTime() < new Date().getTime()) {
                equipmentMap.put("limitTime", PacksackConstant.GOODS_OVERDUE);
            } else {
                equipmentMap.put("limitTime", PacksackConstant.GOODS_LIMITTIME);
            }
            equipmentLits.add(equipmentMap);
        }
        Pager<ArsenalInfoVO> pagerArsenal = arsenalInfoService.queryWeaponList(userGoodsPO.getUserId(), 0, PacksackConstant.KNAPSACK_CAPACITY, userGoodsPO.getSettingPosition());
        List arsenalLits = new ArrayList();
        for (ArsenalInfoVO arsenalInfoVO : pagerArsenal.getContent()) {
            Map<String, Object> arsenalMap = new HashMap<>();
            arsenalMap.put("id", arsenalInfoVO.getGoodsId());
            arsenalMap.put("equipmentId", arsenalInfoVO.getEquipmentId());
            if (null == arsenalInfoVO.getSurplusDate()) {
                arsenalMap.put("limitTime", PacksackConstant.GOODS_UNLIMITED);
            } else if (arsenalInfoVO.getSurplusDate().getTime() > new Date().getTime()) {
                arsenalMap.put("limitTime", PacksackConstant.GOODS_LIMITTIME);
            } else {
                arsenalMap.put("limitTime", PacksackConstant.GOODS_OVERDUE);
            }
            arsenalLits.add(arsenalMap);
        }

        Pager<EquipmentInfoVO> pager = equipmentInfoService.queryEquipmentInfoList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY, PacksackConstant.PROP_PLACE_ADORN);
        for (EquipmentInfoVO equipmentInfoVO : pager.getContent()) {
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "enchantlvl");
        }

        Map<String, Object> ret = new HashMap<>();

        ret.put("propInfo", propLits);
        ret.put("equipmentInfo", equipmentLits);
        ret.put("dressed", pager.getContent());
        ret.put("arsenalInfo", arsenalLits);
        ret.put("roleFight", userAdornEquip.getAlwaysFighting());
        return new BaseReturn("操作成功!", ret);
    }

    /**
     * 查询背包物品详情
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询背包物品详情", notes = "请求参数说明 [id 道具背包物品id] [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[surplusDate 道具过期时间 如果为空则不限时]  [id 道具背包物品id]" +
                    "[equipmentId 物品id][fightingCapacity 战斗力][reinforceFighting 增强的战斗力][reinforceVitality 增强生命力 ]" +
                    "[reinforceAttack 增强物理攻击属性][reinforceSpellAttacks 增强法术属性]" +
                    "[reinforcePdef 增强物理防御][reinforceMagdef 增强法术防御][reinforceCrit 增强暴击属性]" +
                    "[reinforceDodge 增强闪避属性][reinforceHitRate 增强命中属性][reinforceDefenseCrit 增强抗暴属性] [reinforceLevel 强化等级]")})
    @PostMapping(value = "/queryUserGoodsParticulars", produces = "application/json")
    public BaseReturn queryUserGoodsParticulars(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "背包物品id为空!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }


        UserGoods userGoods = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoods == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "物品不存在!");
        }
        Map reinforceMap = new HashMap();
        Map gemBonusMap = new HashMap();
        Map<String, Object> ret = new HashMap<>();
        ret.put("surplusDate", userGoods.getSurplusDate());
        ret.put("id", userGoods.getId());
        if (userGoods.getType() == PacksackConstant.GOODS_TYPE_PROP) {
            PropInfo propInfo = propInfoService.getPropInfo(userGoods.getGoodsId());
            ret.put("goodsId", Integer.parseInt(propInfo.getId()));
            ret.put("fightingCapacity", propInfo.getFightingCapacity());
            if (propInfo.getType() == PacksackConstant.PROP_TYPE_PET) {
                PropGiftInfo propGiftInfo = propGiftInfoService.getPropGiftInfo(userGoodsPO.getId());
                JSONObject jsonObject = JSON.parseObject(propGiftInfo.getGiftBagItems());
                String pet = jsonObject.getString("pet");
                ret.put("petId", pet);
                UserPet userPet = userPetService.getUserPetByUserIdAnId(Integer.parseInt(pet), userGoods.getUserId());
                if (userPet != null) {

                    if (userPet.getExpirationTime() != null) {
                        Integer time = jsonObject.getInteger("time");
                        Map<String, Object> petMap = new HashMap<>();
                        petMap.put("time", time);

                        ret.put("petMap", petMap);
                    } else {
                        Integer expMin = jsonObject.getInteger("exp_mini");
                        Integer expMax = jsonObject.getInteger("exp_max");
                        Map<String, Object> petMap = new HashMap<>();
                        petMap.put("expMin", expMin);
                        petMap.put("expMax", expMax);
                        ret.put("petMap", petMap);
                    }
                }
            }
        } else if (userGoods.getType() == PacksackConstant.GOODS_TYPE_EQUIPMENT) {
            EquipmentInfo equipmentInfo = equipmentInfoService.getEquipmentInfo(userGoods.getGoodsId());
            ret.put("equipmentId", equipmentInfo.getId());
            ret.put("fightingCapacity", equipmentInfo.getFightingCapacity());
            reinforceMap.put(5, equipmentInfo.getVitalityIntensify() > 0 ? equipmentInfo.getVitalityIntensify() : null);
            reinforceMap.put(1, equipmentInfo.getAttackIntensify() > 0 ? equipmentInfo.getAttackIntensify() : null);
            reinforceMap.put(2, equipmentInfo.getSpellAttacksIntensify() > 0 ? equipmentInfo.getSpellAttacksIntensify() : null);
            reinforceMap.put(3, equipmentInfo.getPdefIntensify() > 0 ? equipmentInfo.getPdefIntensify() : null);
            reinforceMap.put(4, equipmentInfo.getMagdefIntensify() > 0 ? equipmentInfo.getMagdefIntensify() : null);
            reinforceMap.put(8, equipmentInfo.getCritIntensify() > 0 ? equipmentInfo.getCritIntensify() : null);
            reinforceMap.put(7, equipmentInfo.getDodgeIntensify() > 0 ? equipmentInfo.getDodgeIntensify() : null);
            reinforceMap.put(6, equipmentInfo.getHitRateIntensify() > 0 ? equipmentInfo.getHitRateIntensify() : null);
            reinforceMap.put(9, equipmentInfo.getDefenseCritIntensify() > 0 ? equipmentInfo.getDefenseCritIntensify() : null);

            gemBonusMap.put(5, equipmentInfo.getVitalityGem() > 0 ? equipmentInfo.getVitalityGem() : null);
            gemBonusMap.put(1, equipmentInfo.getAttackGem() > 0 ? equipmentInfo.getAttackGem() : null);
            gemBonusMap.put(2, equipmentInfo.getSpellAttacksGem() > 0 ? equipmentInfo.getSpellAttacksGem() : null);
            gemBonusMap.put(3, equipmentInfo.getPdefGem() > 0 ? equipmentInfo.getPdefGem() : null);
            gemBonusMap.put(4, equipmentInfo.getMagdefGem() > 0 ? equipmentInfo.getMagdefGem() : null);
            gemBonusMap.put(8, equipmentInfo.getCritGem() > 0 ? equipmentInfo.getCritGem() : null);
            gemBonusMap.put(7, equipmentInfo.getDodgeGem() > 0 ? equipmentInfo.getDodgeGem() : null);
            gemBonusMap.put(6, equipmentInfo.getHitRateGem() > 0 ? equipmentInfo.getHitRateGem() : null);
            gemBonusMap.put(9, equipmentInfo.getDefenseCritGem() > 0 ? equipmentInfo.getDefenseCritGem() : null);
            ret.put("reinforceLevel", equipmentInfo.getEnchantlvl());
            ret.put("reinforce", reinforceMap);
            ret.put("gemBonus", gemBonusMap);

        } else {
            ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
            ret.put("equipmentId", arsenalInfo.getId());
            ret.put("fightingCapacity", arsenalInfo.getFightingCapacity());
            reinforceMap.put(5, arsenalInfo.getVitalityIntensify() > 0 ? arsenalInfo.getVitalityIntensify() : null);
            reinforceMap.put(1, arsenalInfo.getAttackIntensify() > 0 ? arsenalInfo.getAttackIntensify() : null);
            reinforceMap.put(2, arsenalInfo.getSpellAttacksIntensify() > 0 ? arsenalInfo.getSpellAttacksIntensify() : null);
            reinforceMap.put(3, arsenalInfo.getPdefIntensify() > 0 ? arsenalInfo.getPdefIntensify() : null);
            reinforceMap.put(4, arsenalInfo.getMagdefIntensify() > 0 ? arsenalInfo.getMagdefIntensify() : null);
            reinforceMap.put(8, arsenalInfo.getCritIntensify() > 0 ? arsenalInfo.getCritIntensify() : null);
            reinforceMap.put(7, arsenalInfo.getDodgeIntensify() > 0 ? arsenalInfo.getDodgeIntensify() : null);
            reinforceMap.put(6, arsenalInfo.getHitRateIntensify() > 0 ? arsenalInfo.getHitRateIntensify() : null);
            reinforceMap.put(9, arsenalInfo.getDefenseCritIntensify() > 0 ? arsenalInfo.getDefenseCritIntensify() : null);

            gemBonusMap.put(5, arsenalInfo.getVitalityGem() > 0 ? arsenalInfo.getVitalityGem() : null);
            gemBonusMap.put(1, arsenalInfo.getAttackGem() > 0 ? arsenalInfo.getAttackGem() : null);
            gemBonusMap.put(2, arsenalInfo.getSpellAttacksGem() > 0 ? arsenalInfo.getSpellAttacksGem() : null);
            gemBonusMap.put(3, arsenalInfo.getPdefGem() > 0 ? arsenalInfo.getPdefGem() : null);
            gemBonusMap.put(4, arsenalInfo.getMagdefGem() > 0 ? arsenalInfo.getMagdefGem() : null);
            gemBonusMap.put(8, arsenalInfo.getCritGem() > 0 ? arsenalInfo.getCritGem() : null);
            gemBonusMap.put(7, arsenalInfo.getDodgeGem() > 0 ? arsenalInfo.getDodgeGem() : null);
            gemBonusMap.put(6, arsenalInfo.getHitRateGem() > 0 ? arsenalInfo.getHitRateGem() : null);
            gemBonusMap.put(9, arsenalInfo.getDefenseCritGem() > 0 ? arsenalInfo.getDefenseCritGem() : null);
            ret.put("reinforceLevel", arsenalInfo.getEnchantlvl());
            ret.put("reinforce", reinforceMap);
            ret.put("gemBonus", gemBonusMap);
        }
        return new BaseReturn("操作成功!", ret);
    }

    /**
     * 查询用户穿戴的武器
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "查询用户穿戴的武器", notes = "请求参数说明 [userId 用户id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 背包物品id唯一键] [equipmentId 装备初始化id]")})
    @PostMapping(value = "/queryUserWearEapons", produces = "application/json")
    public BaseReturn queryUserWearEapons(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        UserAdornEquip userAdornEquip = userAdornEquipService.getUserAdornEquip(userGoodsPO.getUserId());
        if (userAdornEquip == null) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "该用户未初始化佩戴表!");
        }
        String weapon = userAdornEquip.getWeapon();
        if (StringUtils.isEmpty(weapon)) {
            return new BaseReturn("未佩戴装备!");
        }
        UserGoods userGoods = userGoodsService.getUserGoodsById(weapon);
        if (userGoods == null) {
            return new BaseReturn("装备数据错误!");
        }
        ArsenalInfo arsenalInfo = arsenalInfoService.getArsenalInfo(userGoods.getGoodsId());
        if (arsenalInfo == null) {
            return new BaseReturn("佩戴的装备不存在!");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", userGoods.getId());
        map.put("equipmentId", arsenalInfo.getId());

        return new BaseReturn("查询成功!", map);
    }

    /**
     * 用户使用道具
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "使用道具", notes = "请求参数说明 [userId 用户id] [id 道具id] [amount 使用数量]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 背包物品id唯一键] [amount 道具剩余数量]")})
    @PostMapping(value = "/useProps", produces = "application/json")
    public BaseReturn useProps(@RequestBody UserGoodsPO userGoodsPO) {

        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (StringUtils.isEmpty(userGoodsPO.getId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "背包道具id为空!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        UserGoods userGoodsById = userGoodsService.getByUserIdAndGoodsId(userGoodsPO.getUserId(), userGoodsPO.getId());
        if (userGoodsById == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "背包中不存在该道具!");
        }
        if (userGoodsById.getAmount() <= 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "道具数量已经使用完!");
        }
        if (userGoodsById.getType() != PacksackConstant.GOODS_TYPE_PROP) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品类型不是道具，不能使用!");
        }
        if (userGoodsById.getSettingPosition() != PacksackConstant.PROP_PLACE_KNAPSACK) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "物品没有在背包中，无法使用");
        }

        if (userGoodsById.getSurplusDate() != null && !DateUtil.compareDate(userGoodsById.getSurplusDate())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具已经过期!");
        }

        PropInfo propInfo = propInfoService.getPropInfo(userGoodsById.getGoodsId());
        if (propInfo == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具不存在!");
        }
        if (propInfo.getExpirationTime() != null && !DateUtil.compareDate(propInfo.getExpirationTime())) {
            return new BaseReturn(BaseReturnCode.PROCESS_GOODS_OVERDUE_ERROR, "道具已经过期!");
        }


        UserGoods userGoods = userGoodsService.subUserGoodsById(userGoodsById.getId(), userGoodsPO.getAmount(),true);
        //使用家具道具卡
        if (propInfo.getType() == PropConstant.TYPE_FURNITURE) {
            treeHouseFurnitureService.addFurniture(userGoodsPO.getUserId(), propInfo.getId());
        }
        Map map = new HashMap();
        map.put("id", userGoods.getGoodsId());
        map.put("amount", userGoods.getAmount());
        map.put("goodsId", userGoods.getGoodsId());
        map.put("type", PacksackConstant.GOODS_TYPE_PROP);
        return new BaseReturn("使用成功!", map);
    }

    /**
     * 测试增加道具
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "增加用户未拥有的道具", notes = "请求参数说明 [userId 用户id]  [amount 批量增加物品种类数量] 每个物品增加5个")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 背包物品id唯一键] [amount 道具剩余数量]")})
    @PostMapping(value = "/testAdd", produces = "application/json")
    public BaseReturn testAdd(@RequestBody UserGoodsPO userGoodsPO) {

        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }

        List<PropInfo> p = propInfoService.getPropInfoList();
        Collections.shuffle(p);
        int i = 0;
        List<Map> list = new ArrayList<>();
        for (PropInfo propInfo : p) {
            if (i > userGoodsPO.getAmount().intValue()) {
                break;
            }
            UserGoods userGoods = userGoodsService.addUserGoodsSingle(userGoodsPO.getUserId(), propInfo.getId(), 5);
            Map goodsMap = ObjectUtil.getGoodsMap(userGoods.getGoodsId(), userGoods.getGoodsId(), PacksackConstant.GOODS_TYPE_PROP, userGoods.getAmount(), null);
            list.add(goodsMap);
            i++;
        }
        return new BaseReturn("增加成功!", list);
    }

    /**
     * 测试往仓库增加道具
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "测试往仓库增加道具", notes = "请求参数说明 [userId 用户id]  [amount 将背包道具加入仓库] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping(value = "/testTransferWarehouse", produces = "application/json")
    public BaseReturn testTransferWarehouse(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        Pager<PropInfoVO> propInfoVOPager = propInfoService.queryPropList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY, PacksackConstant.PROP_PLACE_KNAPSACK);
        int i = 0;
        for (PropInfoVO propInfoVO : propInfoVOPager.getContent()) {
            UserGoodsPO userGoods = new UserGoodsPO();
            userGoods.setId(propInfoVO.getGoodsId());
            userGoods.setAmount(propInfoVO.getAmount());
            userGoods.setUserId(propInfoVO.getUserId());
            BaseReturn baseReturn = transferWarehouse(userGoods);
            i++;
            if (userGoodsPO.getAmount() < i) {
                return new BaseReturn("增加成功!");
            }
        }
        Pager<EquipmentInfoVO> equipmentInfoVOPager = equipmentInfoService.queryEquipmentInfoList(userGoodsPO.getUserId(), 1, PacksackConstant.KNAPSACK_CAPACITY, null);
        for (EquipmentInfoVO equipmentInfoVO : equipmentInfoVOPager.getContent()) {
            UserGoodsPO userGoods = new UserGoodsPO();
            userGoods.setId(equipmentInfoVO.getGoodsId());
            userGoods.setUserId(equipmentInfoVO.getUserId());
            userGoods.setAmount(equipmentInfoVO.getAmount());
            BaseReturn baseReturn = transferWarehouse(userGoods);
            i++;
            if (userGoodsPO.getAmount() < i) {
                return new BaseReturn("增加成功!");
            }
        }
        return new BaseReturn("增加成功!");
    }

    /**
     * 测试往仓库增加道具
     *
     * @param userGoodsPO
     * @return
     */
    @ApiOperation(value = "测试取出仓库道具", notes = "请求参数说明 [userId 用户id]  [amount 将背包道具加入仓库] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping(value = "/testTransferKnapsack", produces = "application/json")
    public BaseReturn testTransferKnapsack(@RequestBody UserGoodsPO userGoodsPO) {
        if (StringUtils.isEmpty(userGoodsPO.getUserId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户id为空!");
        }
        if (userGoodsPO.getAmount() == null || userGoodsPO.getAmount().intValue() <= 0) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "数量有误!");
        }
        int i = 0;
        List<UserGoodsWarehouse> userGoodsWarehousesList = userGoodsWarehouseService.getUserGoodsWarehouseList(userGoodsPO.getUserId());
        for (UserGoodsWarehouse userGoodsWarehouse : userGoodsWarehousesList) {
            UserGoodsPO userGoods = new UserGoodsPO();
            userGoods.setId(userGoodsWarehouse.getGoodsId());
            userGoods.setUserId(userGoodsWarehouse.getUserId());
            userGoods.setAmount(userGoodsWarehouse.getAmount());
            transferKnapsack(userGoods);
            i++;
            if (userGoodsPO.getAmount() < i) {
                break;
            }
        }

        return new BaseReturn("增加成功!");
    }


}
