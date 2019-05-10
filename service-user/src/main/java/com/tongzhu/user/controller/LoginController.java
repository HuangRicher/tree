package com.tongzhu.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.user.constant.LoginConstant;
import com.tongzhu.user.constant.MailConstant;
import com.tongzhu.user.constant.NewPlayerGuideConstant;
import com.tongzhu.user.controller.vo.UserVO;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.mapper.vo.AccountDO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.po.UserPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import com.tongzhu.user.util.*;
import com.tongzhu.util.ObjectUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wxLogin")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private TreeHouseService treeHouseService;
    @Autowired
    private NewPlayerGuideService newPlayerGuideService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleLevelSettingService roleLevelSettingService;
    @Autowired
    private UserMailAllService userMailAllService;
    @Autowired
    private UserMailAllDetailsService userMailAllDetailsService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private WelfareService welfareService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ChatMessageService chatMessageService;


    @ApiOperation(value = "系统维护", notes = "请求参数说明 [account 账号] [password 密码]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping(value = "systemMaintenance", produces = "application/json")
    public BaseReturn systemMaintenance(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆账号！");
        if (StringUtils.isBlank(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆密码！");
        if (!(userPO.getAccount().equals("swsd") && userPO.getPassword().equals("swsd")))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "非法请求！");
        Map<String, Object> msg = new HashMap<>();
        msg.put("code", 0);
        msg.put("type", "repair");
        chatMessageService.sendMessageToAll(JSON.toJSONString(msg));
        return new BaseReturn("发送成功！");
    }

    @ApiOperation(value = "账号登陆", notes = "请求参数说明 [account 账号] [password 密码]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户Id][userName 角色名称][roleId 角色Id][lastLogin 是否最近登录 1：是，0：不是] ")
    })
    @PostMapping(value = "testLogin", produces = "application/json")
    public BaseReturn testLogin(@RequestBody UserPO userPO) throws Exception {
        if (StringUtils.isBlank(userPO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆账号！");
        if (StringUtils.isBlank(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆密码！");

        Account account = accountService.getByAccount(userPO.getAccount());
        if (account == null) {
            Map<String, String> param = new HashMap<>();
            param.put("username", userPO.getAccount());
            param.put("password", userPO.getPassword());
            param.put("scret", LoginConstant.TZ_KEY);

            String data = HttpClientUtil.sendPostDataByJson("http://www.tzsd8.com/ageUser", param);
            if (data == null) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "账号不存在，请注册！");
            } else {
                int add = accountService.add(userPO.getAccount(), userPO.getPassword());
                if (add == 0) {
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "当前封测用户人数已满");
                }
                account = new Account();
                account.setAccount(userPO.getAccount());
                account.setPassword(userPO.getPassword());
                redisService.set(userPO.getAccount() + "header", data);
            }
        }
        if (!account.getPassword().equals(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "账号密码错误！");

        List<AccountDO> list = accountService.findByAccount(userPO.getAccount());
        return new BaseReturn("登陆成功！", list);
    }

    @ApiOperation(value = "注册", notes = "请求参数说明 [account 账号][password 密码][code 短信验证码] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户Id][userName 角色名称][roleId 角色Id][lastLogin 是否最近登录 1：是，0：不是] ")
    })
    @PostMapping(value = "register", produces = "application/json")
    public BaseReturn register(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆账号！");
        if (StringUtils.isBlank(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入密码！");
        if (StringUtils.isBlank(userPO.getCode()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入验证码！");
        Account account = accountService.getByAccount(userPO.getAccount());
        if (account != null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该号码已注册！");
        String code = (String) redisService.get(userPO.getAccount() + "_1");
        if (!userPO.getCode().equals(code))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "验证码错误！");

        int add = accountService.add(userPO.getAccount(), userPO.getPassword());
        if (add == 0) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "当前封测用户人数已满！");
        }
        List<AccountDO> list = accountService.findByAccount(userPO.getAccount());
        return new BaseReturn("注册成功！", list);
    }

    @ApiOperation(value = "密码重置", notes = "请求参数说明 [account 账号][password 密码][code 短信验证码] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数")
    })
    @PostMapping(value = "resetPassword", produces = "application/json")
    public BaseReturn resetPassword(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入登陆账号！");
        if (StringUtils.isBlank(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入密码！");
        if (StringUtils.isBlank(userPO.getCode()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入验证码！");
        Account account = accountService.getByAccount(userPO.getAccount());
        if (account == null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该号码尚未注册！");
        String code = (String) redisService.get(userPO.getAccount() + "_2");
        if (!userPO.getCode().equals(code))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "验证码错误！");

        accountService.updateAccount(userPO.getAccount(), userPO.getPassword());

        return new BaseReturn("密码重置成功！");
    }


    @ApiOperation(value = "角色登陆", notes = "请求参数说明 [account 账号][roleId 角色ID] [password 密码]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [user:openId 微信openID][user:userId 用户ID] [user:name 用户名字]" +
                    "[user:portraitUrl 头像url][user:sex 用户性别][user:autograph 个性签名] [user:treeHouseLevel 树屋等级] [isNewPlayer 是否为新用户]" +
                    "[newPlayerTaskNum 引导任务ID(1.初恋名字  2.参加婚礼  3.树屋布置家具  4.副本战斗  5.婚房  6.背包  7.宠物店  8.匹配第一个新好友  " +
                    "9.树屋播种  10.铁匠铺强化  11.技能升级  12.领取任务奖励  13.PVP好友挑战  14.PVE副本战斗  15.砸金库)]" +
                    "[userRole:roleLevel 用户角色等级][userRole:roleTitle 称号][userRole:roleId 角色ID][userRole:fightingCapacity 战斗力]" +
                    "[userRole:roleLevel 等级][userRole:roleTitle 当前称号]" +
                    "[userRole:spouse 配偶][userRole:sociaty 公会][userRole:experience 经验][userRole:blood 生命][userRole:roleId 角色ID][token 认证key]" +
                    "{equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [enchantlvl 强化等级]  [gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]}" +
                    "{wapon 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [wear true 已装备 false 未装备] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]} " +
                    "{propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量][buildingType 建筑类型][goldStatus 是否可领取金币：1否；2：是]"
                    + "[WelfareStatus 是否可领取福利：1否；0：是]}[loverName 初恋名字]")
    })
    @PostMapping(value = "roleLogin", produces = "application/json")
    public BaseReturn roleLogin(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "账号不能为空");
        if (userPO.getRoleId() == null || (userPO.getRoleId() <= 0 && userPO.getRoleId() > 8))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "角色ID不对！");
        if (StringUtils.isBlank(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "密码不能为空");

        Account accounts = accountService.getByAccount(userPO.getAccount());
        if (null != accounts && !accounts.getPassword().equals(userPO.getPassword()))
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "账号密码错误！");

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> userRoleMap = new HashMap<>();
        AccountUser account = accountService.getByAccountAndRoleId(userPO.getAccount(), userPO.getRoleId());
        if (account == null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "该账号不存在该角色！");
        accountService.updateLastLogin(userPO.getAccount(), userPO.getRoleId());
        User byUserId = userService.findByUserId(account.getUserId());
        //redisService.set(byUserId.getUserId()+"_login_",byUserId.getUserId(),60*60L);
        //同时查询用户当前背包物品信息
        TreeHouse treeHouse = treeHouseService.findByUserId(byUserId.getUserId());
        UserVO user = new UserVO();
        BeanUtils.copyProperties(byUserId, user);
        user.setTreeHouseLevel(treeHouse != null ? treeHouse.getLevel() : 1);
        //新用户进行新手引导
        NewPlayerGuide guide = newPlayerGuideService.getByUserId(byUserId.getUserId());
        if (guide == null) {
            //map.put("isNewPlayer", true);
            map.put("newPlayerTaskNum", 1);
        } else {
            if (guide.getTaskId() < NewPlayerGuideConstant.TASK_END) {
                //map.put("isNewPlayer", true);
                map.put("newPlayerTaskNum", guide.getTaskId());
                if (guide.getTaskId() != null && guide.getTaskId() <= NewPlayerGuideConstant.TASK_ID_5) {
                    map.put("loverName", redisService.get(account.getUserId() + "_loverName_"));
                }
            } else {
                //map.put("isNewPlayer", false);
                map.put("newPlayerTaskNum", NewPlayerGuideConstant.TASK_END);
            }
        }

        if (user.getRoleId() == null || user.getRoleId() == 0) {
            userRoleMap.put("roleId", 0);
        } else {
            UserRole userRole = userRoleService.getByUserId(byUserId.getUserId());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("roleId", user.getRoleId());
            //List<PropInfo> infoList = userGoodsService.getUserWarePropInfo(byUserId.getUserId());
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            PropInfo propInfo = new PropInfo();
            userRoleService.computePropInfo(propInfo, setting, userRole);
            UserAdornEquip adornEquip = userGoodsService.getUserFightingCapacity(byUserId.getUserId());
            userRoleMap.put("fightingCapacity", adornEquip.getAlwaysFighting().longValue());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("spouse", userRole.getSpouse() == null ? "" : userRole.getSpouse());
            userRoleMap.put("sociaty", userRole.getSociaty() == null ? "" : userRole.getSociaty());
            userRoleMap.put("experience", userRole.getExperience());
            userRoleMap.put("fullExperience", setting.getExp());
            userRoleMap.put("blood", userRole.getBlood());
            userRoleMap.put("fullBlood", new Double(propInfo.getVitality()).longValue());
        }
        ObjectUtil.setObjectFieldsEmpty(user, true, "userId", "name", "portraitUrl", "vip", "openId", "sex", "treeHouseLevel");
        map.put("user", user);
        map.put("userRole", userRoleMap);
        String token = AESOperator.getInstance().encrypt(userPO.getAccount() + "_" + user.getUserId() + "_" + System.currentTimeMillis());
        redisService.set(userPO.getAccount(), token);
        map.put("token", token);
        Pager<PropInfoVO> propInfoVOPager = userGoodsService.queryPropList(byUserId.getUserId());
        for (PropInfoVO propInfoVO : propInfoVOPager.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId() + "");
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "amount", "goodsId");
        }
        map.put("propList", propInfoVOPager.getContent());
        Pager<EquipmentInfoVO> equipmentInfoVOPager = userGoodsService.queryEquipmentInfoList(byUserId.getUserId());
        for (EquipmentInfoVO equipmentInfoVO : equipmentInfoVOPager.getContent()) {
            if (equipmentInfoVO.getSettingPosition() == 2) {
                equipmentInfoVO.setWear(true);
            } else {
                equipmentInfoVO.setWear(false);
            }
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");
        }
        map.put("equipList", equipmentInfoVOPager.getContent());
        List<ArsenalInfoVO> weaponList = userGoodsService.findUserAllWeaponList(byUserId.getUserId());
        for (ArsenalInfoVO arsenalInfoVO : weaponList) {
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            arsenalInfoVO.setId(arsenalInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");
        }
        map.put("weaponList", weaponList);
        String userId = byUserId.getUserId();
        List<BuildingGoldVo> list = buildingService.getGoldStatusByUserId(userId);
        log.info("建筑状态列表" + list);
        map.put("BuildingGoldStatus", list);//增加建筑是否可以领取金币
        welfareService.addDaysByUserId(userId);//增加登录记录
        int result = welfareService.getWelfareStatusByUserId(userId);//增加福利是否有奖励可领取的状态
        map.put("WelfareStatus", result);

        // 查询是否有全体邮件
        List<UserMailAll> userMailAllList = userMailAllService.getUserMailAllList();
        boolean bo = false;
        for (UserMailAll userMailAll : userMailAllList) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(byUserId.getUserId(), userMailAll.getId());
            if (userMailAllDetails == null) {
                userMailAllDetails = new UserMailAllDetails();
                userMailAllDetails.setMailallId(userMailAll.getId());
                userMailAllDetails.setReceiverId(byUserId.getUserId());
                userMailAllDetails.setRead(MailConstant.MAIL_READ_NO);
                userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_NO);
                userMailAllDetails.setStatus(MailConstant.MAIL_STATUS_NORMAL);
                userMailAllDetailsService.inesrt(userMailAllDetails);
                bo = true;
            }
        }
        if (bo) {
            Map<String, Object> object = ObjectUtil.getRedTipMap("mail");
            chatMessageService.sendMessageToSomeBody(byUserId.getUserId(), JSON.toJSONString(object));
        }

        return new BaseReturn("操作成功!", map);
    }


    @ApiOperation(value = "普通登陆", notes = "请求参数说明 [openId 用户ID] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [user:openId 微信openID][user:userId 用户ID] [user:name 用户名字]" +
                    "[user:portraitUrl 头像url][user:sex 用户性别][user:autograph 个性签名] [user:treeHouseLevel 树屋等级] [isNewPlayer 是否为新用户][newPlayerTaskNum 引导任务ID]" +
                    "[userRole:roleLevel 用户角色等级][userRole:roleTitle 称号][userRole:roleId 角色ID][userRole:fightingCapacity 战斗力]" +
                    "[userRole:roleLevel 等级][userRole:roleTitle 当前称号]" +
                    "[userRole:spouse 配偶][userRole:sociaty 公会][userRole:experience 经验][userRole:blood 生命][userRole:roleId 角色ID][token 认证key]" +
                    "{equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [enchantlvl 强化等级]  [gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]}" +
                    "{wapon 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [wear true 已装备 false 未装备] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]} " +
                    "{propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量][buildingType 建筑类型][goldStatus 是否可领取金币：1否；2：是]"
                    + "[WelfareStatus 是否可领取福利：1否；0：是]}")
    })
    @PostMapping(value = "testLogin-old", produces = "application/json")
    public BaseReturn testLoginOld(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getOpenId())) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "openId不能为空");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> userRoleMap = new HashMap<>();

        User byUserId = userService.findByOpenId(userPO.getOpenId());
        if (byUserId == null || (byUserId != null && (byUserId.getRoleId() == null || byUserId.getRoleId() == 0))) {//说明是个新用户，进行用户初始化
            if (byUserId == null)
                byUserId = userService.initWxUser(userPO.getOpenId(), 2, "" + System.currentTimeMillis(),
                        "湖南省", "长沙市", "ZH-CN", "", System.currentTimeMillis() + "");
            Map<String, Object> userMap = new HashMap<>();
            //byUserId = userService.initUser(userPO.getUserId());
            userRoleMap.put("roleId", 0);
            userMap.put("openId", userPO.getOpenId());
            map.put("userRole", userRoleMap);
            map.put("user", userMap);
            return new BaseReturn("操作成功!", map);
        }
        redisService.set(byUserId.getUserId() + "_login_", byUserId.getUserId(), 60 * 60L);
        //同时查询用户当前背包物品信息
        TreeHouse treeHouse = treeHouseService.findByUserId(byUserId.getUserId());
        UserVO user = new UserVO();
        BeanUtils.copyProperties(byUserId, user);
        user.setTreeHouseLevel(treeHouse != null ? treeHouse.getLevel() : 1);
        //新用户进行新手引导
        NewPlayerGuide guide = newPlayerGuideService.getByUserId(byUserId.getUserId());
        if (guide == null) {
            map.put("isNewPlayer", false);// true
            map.put("newPlayerTaskNum", 7);// 1
        } else {
            if (guide.getTaskId() < 7) {
                map.put("isNewPlayer", false);//TODO 暂时去掉新手引导 true
                map.put("newPlayerTaskNum", 7);//guide.getTaskId()
            } else {
                map.put("isNewPlayer", false);
            }
        }

        if (user.getRoleId() == null || user.getRoleId() == 0) {
            userRoleMap.put("roleId", 0);
        } else {
            UserRole userRole = userRoleService.getByUserId(byUserId.getUserId());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("roleId", user.getRoleId());
            List<PropInfo> infoList = userGoodsService.getUserWarePropInfo(byUserId.getUserId());
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            PropInfo propInfo = new PropInfo();
            userRoleService.computePropInfo(propInfo, setting, userRole);

            UserAdornEquip adornEquip = userGoodsService.getUserFightingCapacity(byUserId.getUserId());

            userRoleMap.put("fightingCapacity", adornEquip.getAlwaysFighting().longValue());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("spouse", userRole.getSpouse() == null ? "" : userRole.getSpouse());
            userRoleMap.put("sociaty", userRole.getSociaty() == null ? "" : userRole.getSociaty());
            userRoleMap.put("experience", userRole.getExperience());
            userRoleMap.put("fullExperience", setting.getExp());
            userRoleMap.put("blood", userRole.getBlood());
            userRoleMap.put("fullBlood", new Double(propInfo.getVitality()).longValue());
        }
        ObjectUtil.setObjectFieldsEmpty(user, "userId", "name", "portraitUrl", "vip", "openId", "sex", "treeHouseLevel");
        map.put("user", user);
        map.put("userRole", userRoleMap);
        map.put("token", AESOperator.getInstance().encrypt(user.getUserId()));
        Pager<PropInfoVO> propInfoVOPager = userGoodsService.queryPropList(byUserId.getUserId());
        for (PropInfoVO propInfoVO : propInfoVOPager.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId() + "");
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "amount", "goodsId");
        }
        map.put("propList", propInfoVOPager.getContent());
        Pager<EquipmentInfoVO> equipmentInfoVOPager = userGoodsService.queryEquipmentInfoList(byUserId.getUserId());
        for (EquipmentInfoVO equipmentInfoVO : equipmentInfoVOPager.getContent()) {
            if (equipmentInfoVO.getSettingPosition() == 2) {
                equipmentInfoVO.setWear(true);
            } else {
                equipmentInfoVO.setWear(false);
            }
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");
        }
        map.put("equipList", equipmentInfoVOPager.getContent());
        List<ArsenalInfoVO> weaponList = userGoodsService.findUserAllWeaponList(byUserId.getUserId());
        for (ArsenalInfoVO arsenalInfoVO : weaponList) {
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            arsenalInfoVO.setId(arsenalInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");

        }
        map.put("weaponList", weaponList);
        String userId = byUserId.getUserId();
        List<BuildingGoldVo> list = buildingService.getGoldStatusByUserId(userId);
        log.info("建筑状态列表" + list);
        map.put("BuildingGoldStatus", list);//增加建筑是否可以领取金币
        welfareService.addDaysByUserId(userId);//增加登录记录
        int result = welfareService.getWelfareStatusByUserId(userId);//增加福利是否有奖励可领取的状态
        map.put("WelfareStatus", result);

        // 查询是否有全体邮件
        List<UserMailAll> userMailAllList = userMailAllService.getUserMailAllList();
        boolean bo = false;
        for (UserMailAll userMailAll : userMailAllList) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(byUserId.getUserId(), userMailAll.getId());
            if (userMailAllDetails == null) {
                userMailAllDetails = new UserMailAllDetails();
                userMailAllDetails.setMailallId(userMailAll.getId());
                userMailAllDetails.setReceiverId(byUserId.getUserId());
                userMailAllDetails.setRead(MailConstant.MAIL_READ_NO);
                userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_NO);
                userMailAllDetails.setStatus(MailConstant.MAIL_STATUS_NORMAL);
                userMailAllDetailsService.inesrt(userMailAllDetails);
                bo = true;
            }
        }
        if (bo) {
            Map<String, Object> object = ObjectUtil.getRedTipMap("mail");
            chatMessageService.sendMessageToSomeBody(byUserId.getUserId(), JSON.toJSONString(object));
        }


        return new BaseReturn("操作成功!", map);
    }


    @ApiOperation(value = "微信网页授权登陆", notes = "请求参数说明 [code 微信授权code] ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [user:openId 微信openID][user:userId 用户ID] [user:name 用户名字]" +
                    "[user:portraitUrl 头像url][user:sex 用户性别][user:autograph 个性签名] [user:treeHouseLevel 树屋等级] [isNewPlayer 是否为新用户][newPlayerTaskNum 引导任务ID]" +
                    "[userRole:roleLevel 用户角色等级][userRole:roleTitle 称号][userRole:roleId 角色ID][userRole:fightingCapacity 战斗力]" +
                    "[userRole:roleLevel 等级][userRole:roleTitle 当前称号]" +
                    "[userRole:spouse 配偶][userRole:sociaty 公会][userRole:experience 经验][userRole:blood 生命][userRole:roleId 角色ID][token 认证key]" +
                    "{equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]}" +
                    "{wapon 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [wear true 已装备 false 未装备] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]} " +
                    "{propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量][buildingType 建筑类型][goldStatus 是否可领取金币：1否；2：是]"
                    + "[WelfareStatus 是否可领取福利：1否；0：是]}")
    })
    @GetMapping("/wxLogin")
    public BaseReturn wxLogin(@RequestParam("code") String code) {

        String accessTokenUrl = WXHttpClientUtil.getRequestAccessTokenUrl(code);
        String accessTokenInfo = WXHttpClientUtil.doGet(accessTokenUrl);
        JSONObject object = JSON.parseObject(accessTokenInfo);

        String access_token = object.getString("access_token");
        long expires_in = object.getLongValue("expires_in");
        String refresh_token = object.getString("refresh_token");
        String openid = object.getString("openid");
        String scope = object.getString("scope");

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> userRoleMap = new HashMap<>();
        User byUserId = userService.findByOpenId(openid);
        if (byUserId == null || (byUserId != null && (byUserId.getRoleId() == null || byUserId.getRoleId() == 0))) {//说明是个新用户，进行用户初始化
            if (byUserId == null) {
                String userInfoUrl = WXHttpClientUtil.getRequestUserInfo(access_token, openid);
                String resultUserInfo = WXHttpClientUtil.doGet(userInfoUrl);
                JSONObject userInfo = JSON.parseObject(resultUserInfo);
                String openid1 = userInfo.getString("openid");
                String nickname = userInfo.getString("nickname");
                String sex = userInfo.getString("sex");
                String province = userInfo.getString("province");
                String city = userInfo.getString("city");
                String country = userInfo.getString("country");
                String headimgurl = userInfo.getString("headimgurl");
                String unionid = userInfo.getString("unionid");
                byUserId = userService.initWxUser(openid1, StringUtils.isBlank(sex) ? 2 : Integer.valueOf(sex), nickname, province, city, country, headimgurl, unionid);
            }
            Map<String, Object> userMap = new HashMap<>();
            userRoleMap.put("roleId", 0);
            userMap.put("openId", openid);
            map.put("userRole", userRoleMap);
            map.put("user", userMap);
            return new BaseReturn("操作成功!", map);
        }
        redisService.set(byUserId.getUserId() + "_login_", byUserId.getUserId(), 60 * 60L);
        //同时查询用户当前背包物品信息
        TreeHouse treeHouse = treeHouseService.findByUserId(byUserId.getUserId());
        UserVO user = new UserVO();
        BeanUtils.copyProperties(byUserId, user);
        user.setTreeHouseLevel(treeHouse != null ? treeHouse.getLevel() : 1);
        //新用户进行新手引导
        NewPlayerGuide guide = newPlayerGuideService.getByUserId(byUserId.getUserId());
        if (guide == null) {
            map.put("isNewPlayer", false);// true
            map.put("newPlayerTaskNum", 7);// 1
        } else {
            if (guide.getTaskId() < 7) {
                map.put("isNewPlayer", false);//TODO 暂时去掉新手引导 true
                map.put("newPlayerTaskNum", 7);//guide.getTaskId()
            } else {
                map.put("isNewPlayer", false);
            }
        }

        if (user.getRoleId() == null || user.getRoleId() == 0) {
            userRoleMap.put("roleId", 0);
        } else {
            UserRole userRole = userRoleService.getByUserId(byUserId.getUserId());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("roleId", user.getRoleId());
            List<PropInfo> infoList = userGoodsService.getUserWarePropInfo(byUserId.getUserId());
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            PropInfo propInfo = new PropInfo();
            userRoleService.computePropInfo(propInfo, setting, userRole);
            UserAdornEquip adornEquip = userGoodsService.getUserFightingCapacity(byUserId.getUserId());

            userRoleMap.put("fightingCapacity", adornEquip.getAlwaysFighting().longValue());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("spouse", userRole.getSpouse() == null ? "" : userRole.getSpouse());
            userRoleMap.put("sociaty", userRole.getSociaty() == null ? "" : userRole.getSociaty());
            userRoleMap.put("experience", userRole.getExperience());
            userRoleMap.put("fullExperience", setting.getExp());
            userRoleMap.put("blood", userRole.getBlood());
            userRoleMap.put("fullBlood", new Double(propInfo.getVitality()).longValue());
        }
        ObjectUtil.setObjectFieldsEmpty(user, "userId", "name", "portraitUrl", "vip", "openId", "sex", "treeHouseLevel");
        map.put("user", user);
        map.put("userRole", userRoleMap);
        String token = AESOperator.getInstance().encrypt(byUserId.getUserId());
        redisService.set(byUserId.getUserId(), token);
        map.put("token", token);
        Pager<PropInfoVO> propInfoVOPager = userGoodsService.queryPropList(byUserId.getUserId());
        for (PropInfoVO propInfoVO : propInfoVOPager.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId() + "");
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "amount", "goodsId");
        }
        map.put("propList", propInfoVOPager.getContent());
        Pager<EquipmentInfoVO> equipmentInfoVOPager = userGoodsService.queryEquipmentInfoList(byUserId.getUserId());
        for (EquipmentInfoVO equipmentInfoVO : equipmentInfoVOPager.getContent()) {
            if (equipmentInfoVO.getSettingPosition() == 2) {
                equipmentInfoVO.setWear(true);
            } else {
                equipmentInfoVO.setWear(false);
            }
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");
        }
        map.put("equipList", equipmentInfoVOPager.getContent());
        List<ArsenalInfoVO> weaponList = userGoodsService.findUserAllWeaponList(byUserId.getUserId());
        for (ArsenalInfoVO arsenalInfoVO : weaponList) {
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            arsenalInfoVO.setId(arsenalInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");

        }
        map.put("weaponList", weaponList);
        String userId = byUserId.getUserId();
        List<BuildingGoldVo> list = buildingService.getGoldStatusByUserId(userId);
        log.info("建筑状态列表" + list);
        map.put("BuildingGoldStatus", list);//增加建筑是否可以领取金币
        welfareService.addDaysByUserId(userId);//增加登录记录
        int result = welfareService.getWelfareStatusByUserId(userId);//增加福利是否有奖励可领取的状态
        map.put("WelfareStatus", result);

        // 查询是否有全体邮件
        List<UserMailAll> userMailAllList = userMailAllService.getUserMailAllList();
        boolean bo = false;
        for (UserMailAll userMailAll : userMailAllList) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(byUserId.getUserId(), userMailAll.getId());
            if (userMailAllDetails == null) {
                userMailAllDetails = new UserMailAllDetails();
                userMailAllDetails.setMailallId(userMailAll.getId());
                userMailAllDetails.setReceiverId(byUserId.getUserId());
                userMailAllDetails.setRead(MailConstant.MAIL_READ_NO);
                userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_NO);
                userMailAllDetails.setStatus(MailConstant.MAIL_STATUS_NORMAL);
                userMailAllDetailsService.inesrt(userMailAllDetails);
                bo = true;
            }
        }
        if (bo) {
            Map<String, Object> ret = ObjectUtil.getRedTipMap("mail");
            chatMessageService.sendMessageToSomeBody(byUserId.getUserId(), JSON.toJSONString(ret));
        }
        return new BaseReturn("操作成功!", map);
    }


    @ApiOperation(value = "微信小游戏登陆", notes = "请求参数说明 [code 微信获取的code][encryptedData 微信返回包括敏感数据在内的完整用户信息的加密数据][iv 微信返回加密算法的初始向量]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [user:openId 微信openID][user:userId 用户ID] [user:name 用户名字]" +
                    "[user:portraitUrl 头像url][user:sex 用户性别][user:autograph 个性签名] [user:treeHouseLevel 树屋等级] [isNewPlayer 是否为新用户][newPlayerTaskNum 引导任务ID]" +
                    "[userRole:roleLevel 用户角色等级][userRole:roleTitle 称号][userRole:roleId 角色ID][userRole:fightingCapacity 战斗力]" +
                    "[userRole:roleLevel 等级][userRole:roleTitle 当前称号]" +
                    "[userRole:spouse 配偶][userRole:sociaty 公会][userRole:experience 经验][userRole:blood 生命][userRole:roleId 角色ID][token 认证key]" +
                    "{equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]}" +
                    "{wapon 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [wear true 已装备 false 未装备] [enchantlvl 强化等级][gem_rhombus 菱形宝石孔] [gem_roundness 圆形宝石孔][gem_hexagon 六边形宝石孔]} " +
                    "{propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量][buildingType 建筑类型][goldStatus 是否可领取金币：1否；2：是]"
                    + "[WelfareStatus 是否可领取福利：1否；0：是]}")
    })
    @PostMapping("/wxLoginGame")
    public BaseReturn wxLoginGame(@RequestBody UserPO userPO) throws Exception {
        if (StringUtils.isBlank(userPO.getCode()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "code为空！");
        String codeUrl = WXHttpClientUtil.getRequestAccessTokenUrl(userPO.getCode());
        String accessTokenResponse = WXHttpClientUtil.doGet(codeUrl);

        JSONObject json = JSON.parseObject(accessTokenResponse);
        String sessionkey = json.getString("session_key"); //会话秘钥
        String openid = json.getString("openid"); //用户唯一标识

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> userRoleMap = new HashMap<>();
        User byUserId = userService.findByOpenId(openid);
        if (byUserId == null || (byUserId.getRoleId() == null || byUserId.getRoleId() == 0)) {//说明是个新用户，进行用户初始化
            if (byUserId == null) {
                if (StringUtils.isBlank(userPO.getEncryptedData()) || StringUtils.isBlank(userPO.getIv()))
                    return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请传入用户信息！");

                //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
                String decrypts = AesCbcUtil.decrypt(userPO.getEncryptedData(), sessionkey, userPO.getIv(), "utf-8");//解密

                JSONObject jsons = JSON.parseObject(decrypts);
                String nickname = jsons.get("nickName").toString(); //用户昵称
                String headimgurl = jsons.get("avatarUrl").toString(); //头像
                String sex = jsons.get("gender").toString();//性别
                //    	            String unionId = jsons.get("unionid").toString(); //unionid
                String city = jsons.get("city").toString(); //城市
                String province = jsons.get("province").toString();//省份
                String country = jsons.get("country").toString(); //国家
                byUserId = userService.initWxUser(openid, StringUtils.isBlank(sex) ? 2 : Integer.valueOf(sex), nickname, province, city, country, headimgurl, "1");
            }
            Map<String, Object> userMap = new HashMap<>();
            userRoleMap.put("roleId", 0);
            userMap.put("openId", openid);
            map.put("userRole", userRoleMap);
            map.put("user", userMap);
            return new BaseReturn("操作成功!", map);
        }
        redisService.set(byUserId.getUserId() + "_login_", byUserId.getUserId(), 60 * 60l);
        //同时查询用户当前背包物品信息
        TreeHouse treeHouse = treeHouseService.findByUserId(byUserId.getUserId());
        UserVO user = new UserVO();
        BeanUtils.copyProperties(byUserId, user);
        user.setTreeHouseLevel(treeHouse != null ? treeHouse.getLevel() : 1);
        //新用户进行新手引导
        NewPlayerGuide guide = newPlayerGuideService.getByUserId(byUserId.getUserId());
        if (guide == null) {
            map.put("isNewPlayer", false);// true
            map.put("newPlayerTaskNum", 7);// 1
        } else {
            if (guide.getTaskId() < 7) {
                map.put("isNewPlayer", false);//TODO 暂时去掉新手引导 true
                map.put("newPlayerTaskNum", 7);//guide.getTaskId()
            } else {
                map.put("isNewPlayer", false);
            }
        }

        if (user.getRoleId() == null || user.getRoleId() == 0) {
            userRoleMap.put("roleId", 0);
        } else {
            UserRole userRole = userRoleService.getByUserId(byUserId.getUserId());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("roleId", user.getRoleId());
            List<PropInfo> infoList = userGoodsService.getUserWarePropInfo(byUserId.getUserId());
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            PropInfo propInfo = new PropInfo();
            userRoleService.computePropInfo(propInfo, setting, userRole);
            UserAdornEquip adornEquip = userGoodsService.getUserFightingCapacity(byUserId.getUserId());

            userRoleMap.put("fightingCapacity", adornEquip.getAlwaysFighting().longValue());
            userRoleMap.put("roleLevel", userRole.getRoleLevel());
            userRoleMap.put("roleTitle", userRole.getRoleTitle() == null ? "" : userRole.getRoleTitle());
            userRoleMap.put("spouse", userRole.getSpouse() == null ? "" : userRole.getSpouse());
            userRoleMap.put("sociaty", userRole.getSociaty() == null ? "" : userRole.getSociaty());
            userRoleMap.put("experience", userRole.getExperience());
            userRoleMap.put("fullExperience", setting.getExp());
            userRoleMap.put("blood", userRole.getBlood());
            userRoleMap.put("fullBlood", new Double(propInfo.getVitality()).longValue());
        }
        ObjectUtil.setObjectFieldsEmpty(user, "userId", "name", "portraitUrl", "vip", "openId", "sex", "treeHouseLevel");
        map.put("user", user);
        map.put("userRole", userRoleMap);
        map.put("token", AESOperator.getInstance().encrypt(user.getUserId()));
        Pager<PropInfoVO> propInfoVOPager = userGoodsService.queryPropList(byUserId.getUserId());
        for (PropInfoVO propInfoVO : propInfoVOPager.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId() + "");
            ObjectUtil.setObjectFieldsEmpty(propInfoVO, "id", "amount", "goodsId");
        }
        map.put("propList", propInfoVOPager.getContent());
        Pager<EquipmentInfoVO> equipmentInfoVOPager = userGoodsService.queryEquipmentInfoList(byUserId.getUserId());
        for (EquipmentInfoVO equipmentInfoVO : equipmentInfoVOPager.getContent()) {
            if (equipmentInfoVO.getSettingPosition() == 2) {
                equipmentInfoVO.setWear(true);
            } else {
                equipmentInfoVO.setWear(false);
            }
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");
        }
        map.put("equipList", equipmentInfoVOPager.getContent());
        List<ArsenalInfoVO> weaponList = userGoodsService.findUserAllWeaponList(byUserId.getUserId());
        for (ArsenalInfoVO arsenalInfoVO : weaponList) {
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            arsenalInfoVO.setId(arsenalInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO, "id", "equipmentId", "wear", "enchantlvl", "gemRhombus", "gemRoundness", "gemHexagon");

        }
        map.put("weaponList", weaponList);
        String userId = byUserId.getUserId();
        List<BuildingGoldVo> list = buildingService.getGoldStatusByUserId(userId);
        log.info("建筑状态列表{}", list);
        map.put("BuildingGoldStatus", list);//增加建筑是否可以领取金币
        welfareService.addDaysByUserId(userId);//增加登录记录
        int result = welfareService.getWelfareStatusByUserId(userId);//增加福利是否有奖励可领取的状态
        map.put("WelfareStatus", result);

        // 查询是否有全体邮件
        List<UserMailAll> userMailAllList = userMailAllService.getUserMailAllList();
        boolean bo = false;
        for (UserMailAll userMailAll : userMailAllList) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(byUserId.getUserId(), userMailAll.getId());
            if (userMailAllDetails == null) {
                userMailAllDetails = new UserMailAllDetails();
                userMailAllDetails.setMailallId(userMailAll.getId());
                userMailAllDetails.setReceiverId(byUserId.getUserId());
                userMailAllDetails.setRead(MailConstant.MAIL_READ_NO);
                userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_NO);
                userMailAllDetails.setStatus(MailConstant.MAIL_STATUS_NORMAL);
                userMailAllDetailsService.inesrt(userMailAllDetails);
                bo = true;
            }
        }
        if (bo) {
            Map<String, Object> object = ObjectUtil.getRedTipMap("mail");
            chatMessageService.sendMessageToSomeBody(byUserId.getUserId(), JSON.toJSONString(object));
        }

        return new BaseReturn("操作成功!", map);
    }

    @ApiOperation(value = "发送手机短信验证码", notes = "请求参数说明 [phoneNumber 手机号][type 1：注册,2：重置密码]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @PostMapping("/getCheckCode")
    public BaseReturn getCheckCode(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getPhoneNumber()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入手机号码！");
        if (!SmsSenderUtil.isMobileNO(userPO.getPhoneNumber()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请输入正确的手机号码！");
        if (redisService.get(userPO.getPhoneNumber() + "_" + userPO.getType()) != null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "验证码已发送！");
        int accountCount = accountService.getAccountCount();
        if (accountCount >= 200) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "当前封测用户人数已满！");
        }
        if (userPO.getType().equals(1)) {
            Account account = accountService.getByAccount(userPO.getPhoneNumber());
            if (account != null)
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该号码已注册！");
        }
        if (userPO.getType().equals(2)) {
            Account account = accountService.getByAccount(userPO.getPhoneNumber());
            if (account == null)
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "该号码尚未注册！");
        }
        String code = SmsSenderUtil.sendWithTemplate(userPO.getPhoneNumber());
        redisService.set(userPO.getPhoneNumber() + "_" + userPO.getType(), code, 60L);
        return new BaseReturn("发送成功！");
    }


}
