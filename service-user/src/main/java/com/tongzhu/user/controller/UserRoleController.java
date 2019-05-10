package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.user.constant.MailConstant;
import com.tongzhu.user.controller.vo.UserVO;
import com.tongzhu.user.domain.*;
import com.tongzhu.user.model.*;
import com.tongzhu.user.po.RolePO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import com.tongzhu.user.util.AESOperator;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.user.util.SensitiveWordFilterUtil;
import com.tongzhu.util.StringCheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/userRole")
@Api(value="用户角色信息controller",tags={"用户角色信息相关API"})
public class UserRoleController {

    private Logger logger= LoggerFactory.getLogger(UserRoleController.class);


    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private UserMailAllService userMailAllService;

    @Autowired
    private UserMailAllDetailsService userMailAllDetailsService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private WelfareService welfareService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NPCAttributeService npcAttributeService;
    @Autowired
    private ArsenalService arsenalService;





    @ApiOperation(value = "点击头像获取用户角色详情",notes = "请求参数：[userId 用户Id][otherUserId 他人用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[fightingCapacity 战斗力][roleLevel 等级][roleTitle 当前称号]" +
                    "[spouse 配偶][isGameFriend 是否为游戏好友][sociaty 公会][blood 生命][experience 经验][userName 角色名称][roleId 角色ID]" +
                    "[attack 物理攻击][spellAttacks 法术攻击][pdef 物理防御][magdef 法术防御][crit 暴击属性][dodge 闪避属性]" +
                    "[hitRate 命中属性][defenseCrit 抗暴击属性][fullBlood 生命上限][fullExperience 经验上限][sex 1为男性，2为女性][charmNum 魅力值]")
    })
    @PostMapping("/getUserRoleDetail")
    public BaseReturn getUserRoleDetail(@RequestBody RolePO rolePO){
        if(StringUtils.isBlank(rolePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        if(StringUtils.isBlank(rolePO.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"要查看的用户ID为空！");

        UserRole userRole=userRoleService.getByUserId(rolePO.getOtherUserId());
        UserRole currentUserRole=userRoleService.getByUserId(rolePO.getUserId());
        User spouseUser=null;
        //User user=userService.findByUserId(rolePO.getUserId());
        //List<PropInfo> infoList=userGoodsService.getUserWarePropInfo(rolePO.getOtherUserId());
        RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
        if(StringUtils.isNotBlank(userRole.getSpouse())){
            spouseUser=userService.findByUserId(userRole.getSpouse());
        }
        //PropInfo propInfo=new PropInfo();
        //userRoleService.computePropInfo(propInfo,setting,userRole);
        //UserAdornEquip adornEquip=userGoodsService.getUserFightingCapacity(rolePO.getOtherUserId());
        UserAdornEquip adornEquip=null;
        if(RoleConstant.NPC_IDS.contains(","+rolePO.getOtherUserId()+",")){
            NPCAttribute attribute=npcAttributeService.findByLevelId(userRole.getRoleLevel()+currentUserRole.getRoleLevel());
            adornEquip=userGoodsService.getUserAdornEquipAttribute(rolePO.getOtherUserId(),
                    (double)attribute.getHp(),
                    (double)attribute.getPhAtk(),(double)attribute.getMfAtk(),(double)attribute.getPhDef(),(double)attribute.getMfDef(),
                    (double)attribute.getCritical(),(double)attribute.getMiss(),(double)attribute.getAccuracy(),(double)attribute.getDcritical());
        }else{
            adornEquip=userGoodsService.getUserFightingCapacity(rolePO.getOtherUserId());
        }

        Map<String,Object> result=new HashMap<>();
        result.put("fightingCapacity",adornEquip.getAlwaysFighting().longValue());
        result.put("roleLevel",RoleConstant.NPC_IDS.contains(rolePO.getOtherUserId())?userRole.getRoleLevel()+currentUserRole.getRoleLevel():userRole.getRoleLevel());
        result.put("roleTitle",userRole.getRoleTitle()==null?3:userRole.getRoleTitle());
        result.put("spouse",spouseUser==null?"":spouseUser.getName());
        result.put("sociaty",userRole.getSociaty()==null?"":userRole.getSociaty());
        result.put("experience",userRole.getExperience());
        result.put("fullExperience",setting.getExp());
        result.put("blood",adornEquip.getVitality().longValue());
        result.put("fullBlood",adornEquip.getVitality().longValue());
        result.put("userName",userRole.getUserName());
        result.put("roleId",userRole.getRoleId());
        result.put("attack",adornEquip.getAttack().longValue());
        result.put("spellAttacks",adornEquip.getSpellAttacks().longValue());
        result.put("pdef",adornEquip.getPdef().longValue());
        result.put("magdef",adornEquip.getMagdef().longValue());
        result.put("crit",adornEquip.getCrit().longValue());
        result.put("dodge",adornEquip.getDodge().longValue());
        result.put("hitRate",adornEquip.getHitRate().longValue());
        result.put("defenseCrit",adornEquip.getDefenseCrit().longValue());
        result.put("sex",userRole.getSex());
        result.put("charmNum",userRole.getCharmNum());
        Friend friend=friendService.checkIsMyFriend(rolePO.getUserId(),rolePO.getOtherUserId());
        if(friend!=null){
            result.put("isGameFriend",true);
        }else {
            result.put("isGameFriend",false);
        }
        return new BaseReturn("操作成功",result);
    }


    @ApiOperation(value = "更新称号",notes = "请求参数：[userId 用户ID] [ roleTitle 称号ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/updateTitle")
    public BaseReturn updateTitle(@RequestBody RolePO rolePO){
        userRoleService.updateUserRoleTitle(rolePO.getUserId(),rolePO.getRoleTitle());
        return new BaseReturn("更新成功！");
    }



    @ApiOperation(value = "称号列表",notes = "请求参数：[userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[currentTitle 当前称号][titleList:roleTitle 已获得的称号Id]")
    })
    @PostMapping("/roleTitleList")
    public BaseReturn roleTitleList(@RequestBody RolePO rolePO){
        UserRole userRole=userRoleService.getByUserId(rolePO.getUserId());
        List<Integer> list=new ArrayList<>();
        List<UserRoleTitle> titleList=userRoleService.findTitleListByUserId(rolePO.getUserId());
        Map<String,Object> result=new HashMap<>();
        for(UserRoleTitle title:titleList){
            list.add(title.getRoleTitle());
        }
        result.put("currentTitle",userRole.getRoleTitle());
        result.put("titleList",list);
        return new BaseReturn("成功",result);
    }



    @ApiOperation(value = "创建用户角色",notes = "创建用户角色请求参数：[account 账号] [roleId 角色ID] [roleName 角色名称]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [user:userId 用户ID] [user:name 用户名字]" +
                    "[user:portraitUrl 头像url][user:sex 用户性别][user:autograph 个性签名] [user:treeHouseLevel 树屋等级][newPlayerTaskNum 引导任务ID]" +
                    "[userRole:roleLevel 用户角色等级][userRole:roleTitle 称号][userRole:roleId 角色ID][userRole:fightingCapacity 战斗力]" +
                    "[userRole:roleLevel 等级][userRole:roleTitle 当前称号]" +
                    "[userRole:spouse 配偶][userRole:sociaty 公会][userRole:experience 经验][userRole:blood 生命][userRole:roleId 角色ID][token 认证key]" +
                    "{equipmentInfoList 装备数据:[id 背包物品id唯一键] [equipmentId 装备初始化id] [goodsId 装备唯一键id]} " +
                    "{propInfoList 道具数据：[id 背包物品id唯一键][goodsId 道具唯一键id][amount 数量]}")
    })
    @PostMapping("/addUserRole")
    public BaseReturn addUserRole(@RequestBody RolePO rolePO){
        if(StringUtils.isBlank(rolePO.getAccount()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"账号为空！");
        if(StringUtils.isBlank(rolePO.getRoleName()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"角色名称为空！");
        if(rolePO.getRoleId()==null || rolePO.getRoleId()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择角色！");
        if(rolePO.getRoleName().length()>8)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"角色名称超过字符长度限制！");
        SensitiveWordFilterUtil filter = new SensitiveWordFilterUtil();
        Set<String> set = filter.getSensitiveWord(rolePO.getRoleName(), 1);
        if(set!=null && !set.isEmpty())
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"角色名称包含敏感字！");
        if(!StringCheckUtil.checkHaveEspecialCode(rolePO.getRoleName()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"角色名称只能是中文，英文，数字！");
        List<UserRole> userRoleList=userRoleService.findByUserName(rolePO.getRoleName());
        if(!CollectionUtils.isEmpty(userRoleList))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"角色名称已存在！");

        Map<String, Object> map = new HashMap<>();
        Map<String,Object> userRoleMap=new HashMap<>();

        AccountUser account=accountService.getByAccountAndRoleId(rolePO.getAccount(),rolePO.getRoleId());
        if(account!=null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"该用户已创建角色！");

        /*User usu=userService.findByOpenId(rolePO.getOpenId());
        if(usu==null){
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"非法用户！");
        }
        if(usu.getRoleId()!=null && usu.getRoleId()>0){
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"该用户已创建角色！");
        }*/

        Role role =userRoleService.getByRoleId(rolePO.getRoleId());

        User byUserId = userService.addUserId(rolePO.getAccount(),role.getSex(), rolePO.getRoleId());
        userService.addUserRole(byUserId.getUserId(),rolePO.getRoleName(),rolePO.getRoleId(), role.getSex(),rolePO.getAccount() );
        //redisService.set(byUserId.getUserId()+"_login_",byUserId.getUserId(),30*60l);
        byUserId.setName(rolePO.getRoleName());
        TreeHouse treeHouse=treeHouseService.findByUserId(byUserId.getUserId());
        UserVO user=new UserVO();
        BeanUtils.copyProperties(byUserId,user);
        if(redisService.get(rolePO.getAccount()+"header")!=null){
            user.setPortraitUrl((String)redisService.get(rolePO.getAccount()+"header"));
        }else{
            user.setPortraitUrl(rolePO.getRoleId()+"");
        }
        user.setTreeHouseLevel(treeHouse!=null?treeHouse.getLevel():1);

        UserRole userRole=userRoleService.getByUserId(byUserId.getUserId());
        userRoleMap.put("roleLevel",userRole.getRoleLevel());
        userRoleMap.put("roleTitle",userRole.getRoleTitle()==null?3:userRole.getRoleTitle());
        userRoleMap.put("roleId",rolePO.getRoleId());
        //List<PropInfo> infoList=userGoodsService.getUserWarePropInfo(byUserId.getUserId());
        RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
        //PropInfo propInfo=new PropInfo();
        //userRoleService.computePropInfo(propInfo,setting,userRole);

        UserAdornEquip adornEquip=userGoodsService.getUserFightingCapacity(user.getUserId());

        ObjectUtil.setObjectFieldsEmpty(user,"userId","name","portraitUrl","vip","openId","sex","treeHouseLevel");
        map.put("user", user);
        String token=AESOperator.getInstance().encrypt(rolePO.getAccount()+"_"+byUserId.getUserId()+"_"+System.currentTimeMillis());
        redisService.set(rolePO.getAccount(),token);
        map.put("token", token);
        userRoleMap.put("fightingCapacity",adornEquip.getAlwaysFighting().longValue());
        userRoleMap.put("roleLevel",userRole.getRoleLevel());
        userRoleMap.put("roleTitle",userRole.getRoleTitle()==null?"":userRole.getRoleTitle());
        userRoleMap.put("spouse",userRole.getSpouse());
        userRoleMap.put("sociaty",userRole.getSociaty());
        userRoleMap.put("experience",userRole.getExperience());
        userRoleMap.put("fullExperience",setting.getExp());
        userRoleMap.put("blood",userRole.getBlood());
        userRoleMap.put("fullBlood",adornEquip.getVitality());
        map.put("userRole",userRoleMap);
        Pager<PropInfoVO> propInfoVOPager = userGoodsService.queryPropList(byUserId.getUserId());
        for (PropInfoVO propInfoVO: propInfoVOPager.getContent()) {
            propInfoVO.setId(propInfoVO.getGoodsId()+"");
            ObjectUtil.setObjectFieldsEmpty(propInfoVO,"id","amount","goodsId");
        }
        map.put("propList", propInfoVOPager.getContent());
        Pager<EquipmentInfoVO> equipmentInfoVOPager = userGoodsService.queryEquipmentInfoList(byUserId.getUserId());
        for (EquipmentInfoVO equipmentInfoVO:equipmentInfoVOPager.getContent()) {
            if (equipmentInfoVO.getSettingPosition() == 2) {
                equipmentInfoVO.setWear(true);
            } else {
                equipmentInfoVO.setWear(false);
            }
            equipmentInfoVO.setId(equipmentInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO,"id","equipmentId","wear","enchantlvl","gemRhombus","gemRoundness","gemHexagon");
        }
        map.put("equipList", equipmentInfoVOPager.getContent());
        List<ArsenalInfoVO> weaponList = userGoodsService.findUserAllWeaponList(byUserId.getUserId());
        for (ArsenalInfoVO arsenalInfoVO:weaponList) {
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            arsenalInfoVO.setId(arsenalInfoVO.getGoodsId());
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO,"id","equipmentId","wear","enchantlvl","gemRhombus","gemRoundness","gemHexagon");
        }
        //List<ArsenalInfoVO> weaponList =new ArrayList<>();
        //新手武器（一次性)
        List<String> weaponIdList_ws=Arrays.asList("110016","110016","110018","110019","110020","110021","110022","110023","110024","110025");
        List<String> weaponIdList_ck=Arrays.asList("120016","120016","120018","120019","120020","120021","120022","120023","120024","120025");
        List<String> weaponIdList_gj=Arrays.asList("130016","130016","130018","130019","130020","130021","130022","130023","130024","130025");
        List<String> weaponIdList_fs=Arrays.asList("140016","140016","140018","140019","140020","140021","140022","140023","140024","140025");
        //卫士		刺客		工匠		法师
        //110016   120016  130016  140016
        //110017   120017  130017  140017
        //110018   120018  130018  140018
        //110019   120019  130019  140019
        //110020   120020  130020  140020
        //110021   120021  130021  140021
        //110022   120022  130022  140022
        //110023   120023  130023  140023
        //110024   120024  130024  140024
        //110025   120025  130025  140025
        List<String> weaponListId=null;
        if(1==userRole.getRoleId() || 2==userRole.getRoleId()){
            weaponListId=weaponIdList_ws;

        }
        if(3==userRole.getRoleId() || 4==userRole.getRoleId()){
            weaponListId=weaponIdList_ck;
        }
        if(5==userRole.getRoleId() || 6==userRole.getRoleId()){
            weaponListId=weaponIdList_gj;
        }
        if(7==userRole.getRoleId() || 8==userRole.getRoleId()){
            weaponListId=weaponIdList_fs;
        }
        List<String> weaponIdList=new ArrayList<>();
        for(String weaponId:weaponListId){
            ArsenalInfo info=arsenalService.getArsenalInfoByIdAndOriginal(weaponId);
            ArsenalInfoVO arsenalInfo=new ArsenalInfoVO();
            BeanUtils.copyProperties(info,arsenalInfo);
            arsenalInfo.setId(info.getIntensifyId());
            arsenalInfo.setEquipmentId(info.getId());
            arsenalInfo.setGoodsId(info.getId()+"");
            arsenalInfo.setWear(false);
            weaponList.add(arsenalInfo);
            weaponIdList.add(info.getIntensifyId());
        }
        redisService.set(RedisKey.NEW_PLAYER_WEAPON_LIST+byUserId.getUserId(),weaponIdList);
        map.put("weaponList", weaponList);
        String userId = byUserId.getUserId();
        List<BuildingGoldVo> list = buildingService.getGoldStatusByUserId(userId);
        logger.info("建筑状态列表 {}",list);
        map.put("BuildingGoldStatus", list);//增加建筑是否可以领取金币
        welfareService.addDaysByUserId(userId);//增加登录记录
        int result = welfareService.getWelfareStatusByUserId(userId);//增加福利是否有奖励可领取的状态
        map.put("WelfareStatus", result);
        
        // 查询是否有全体邮件
        List<UserMailAll> userMailAllList = userMailAllService.getUserMailAllList();

        for (UserMailAll userMailAll:userMailAllList) {
            UserMailAllDetails userMailAllDetails = userMailAllDetailsService.getUserMailAllDetails(byUserId.getUserId(),userMailAll.getId());
            if (userMailAllDetails == null) {
                userMailAllDetails = new UserMailAllDetails();
                userMailAllDetails.setMailallId(userMailAll.getId());
                userMailAllDetails.setReceiverId(byUserId.getUserId());
                userMailAllDetails.setRead(MailConstant.MAIL_READ_NO);
                userMailAllDetails.setReceive(MailConstant.MAIL_RECEIVE_NO);
                userMailAllDetails.setStatus(MailConstant.MAIL_STATUS_NORMAL);
                userMailAllDetailsService.inesrt(userMailAllDetails);
            }
        }
        //新用户进行新手引导
        map.put("newPlayerTaskNum", 1);
        return new BaseReturn("操作成功!",map);
    }

    @ApiOperation(value = "获取玩家穿戴的装备和拥有的武器",notes = "请求参数：[userId 玩家用户ID][otherUserId 要查看的玩家用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [equipmentList 装备列表][equipmentId 装备ID][goodsId 物品ID] </br>" +
                    "[weaponList 武器列表][equipmentId 武器ID][goodsId 物品ID][wear 是否穿戴]")
    })
    @PostMapping("/getDressedEquipmentAndWeaponList")
    public BaseReturn getDressedEquipmentAndWeaponList(@RequestBody RolePO rolePO){
        if(StringUtils.isBlank(rolePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(rolePO.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"要查看的用户ID为空！");

        User user=userService.findByUserId(rolePO.getOtherUserId());
        if(user==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"要查看的用户不存在！");
        
        Map<String,Object> result=new HashMap<>();
        //武器
        List<ArsenalInfoVO> weaponList=userGoodsService.findUserAllWeaponList(rolePO.getOtherUserId());
        //装备
        List<EquipmentInfoVO> equipmentList=userGoodsService.queryDressedEquipmentList(rolePO.getOtherUserId());

        for(ArsenalInfoVO arsenalInfoVO:weaponList){
            if (arsenalInfoVO.getSettingPosition() == 2) {
                arsenalInfoVO.setWear(true);
            } else {
                arsenalInfoVO.setWear(false);
            }
            ObjectUtil.setObjectFieldsEmpty(arsenalInfoVO,"equipmentId","wear","goodsId","enchantlvl");
        }
        for(EquipmentInfoVO equipmentInfoVO:equipmentList){
            ObjectUtil.setObjectFieldsEmpty(equipmentInfoVO,"wear","equipmentId","goodsId","enchantlvl");
        }
        result.put("equipmentList",equipmentList);
        result.put("weaponList",weaponList);

        return new BaseReturn("获取成功！",result);
    }

}
