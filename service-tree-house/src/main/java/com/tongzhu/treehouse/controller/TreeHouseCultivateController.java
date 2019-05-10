package com.tongzhu.treehouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.constant.GoodsTypeConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.treehouse.constant.*;
import com.tongzhu.treehouse.domain.*;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.vo.TreeHouseVisitorDO;
import com.tongzhu.treehouse.model.*;
import com.tongzhu.treehouse.po.TreeHouseCultivatePO;
import com.tongzhu.treehouse.redis.RedisService;
import com.tongzhu.treehouse.service.*;
import com.tongzhu.treehouse.service.vo.PropInfoVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.ObjectUtil;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 树屋养成控制类
 */
@RestController
@RequestMapping("/treeHouseCultivate")
@Api(value="树屋养成controller",tags={"树屋养成相关API"})
public class TreeHouseCultivateController {

    @Autowired
    private TreeHouseVisitorService treeHouseVisitorService;

    @Autowired
    private TreeHouseFlowerpotService treeHouseFlowerpotService;

    @Autowired
    private TreeHouseFlowerpotLogService treeHouseFlowerpotLogService;

    @Autowired
    private TreeHouseFurnitureService treeHouseFurnitureService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private HoldFlowersService holdFlowersService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TreeHouseWorkSettingService treeHouseWorkSettingService;

    @Autowired
    private TreeHouseFlowerpotPlayerService treeHouseFlowerpotPlayerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private TreeHouseWorkLogService treeHouseWorkLogService;

    private String userIdIsNull="用户ID为空！";

    private String pleaseInputTreeHouseUserId="请传入树屋主人用户ID！";


    @ApiOperation(value = "树屋养成详情",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋用户ID] " +
            "因工作进入树屋需传参数[workType 1浇水 2施肥 3打扫]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[treeHouseId 树屋ID][treeHouseUserName 树屋主人名字][canSpeak 是否禁言][ambienceCount 人气值][ambienceCountFull 升下一级所需人气值]" +
                    "[amusementCount 娱乐值][amusementCountFull 升下一级所需娱乐值][environmentCount 环境值][environmentCountFull 升下一级所需环境值][style 装修风格]</br>" +
                    "[flowerpots 花盆列表][id 花盆ID][waterCount 浇水和施肥次数][flowerStatus 状态 1：播种，2：成长，3：即将成熟，4：可收获][lockLevel 解锁等级]" +
                    "[lockStatus 解锁状态 0：锁定，1：解锁][goodsId 种子ID][waterCount 浇水+施肥次数][spreadManureCount 浇水+施肥累积奖励]</br>" +
                    "[furnitures 家具][goodsId 道具ID][location 家具摆放位置]</br>" +
                    "[canWork true:false是否可工作][visitors 游客][id 位置ID][roleId 角色ID][userName 用户名称][petId 宠物ID][sex 性别 1：男，2：女][status 位置状态 0：关闭  1：开启][workStatus 1:工作中]")
    })
    @PostMapping("/getTreeHouseCultivateDetail")
    public BaseReturn getTreeHouseCultivateDetail(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋用户ID为空！");
        if(redisService.get(RedisKey.TREE_HOUSE_LET_OUT+treeHouseCultivatePO.getTreeHouseUserId()+"_"+treeHouseCultivatePO.getUserId())!=null){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"你已被主人请出树屋，请稍后再进！");
        }
        try {
            treeHouseService.comeInTreeHouse(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getTreeHouseUserId());
        }catch (CheckException e){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
        }

        TreeHouse treeHouse=treeHouseService.findByUserId(treeHouseCultivatePO.getTreeHouseUserId());
        BuildingVo treeHouseSetting=buildingService.getTreeHouseByUserId(treeHouse.getLevel());
        List<TreeHouseVisitorDO> visitors=treeHouseVisitorService.findTreeHouseVisitor(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getTreeHouseUserId());
        List<TreeHouseFurniture> furnitureList=treeHouseFurnitureService.findCollocationByTreeHouseId(treeHouse.getUserId());
        List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotService.findByTreeHouseId(treeHouse.getUserId());
        List<TreeHouseFlowerpotDO> flowerpotDOS=new ArrayList<>();
        for(TreeHouseFlowerpot flowerpot:flowerpots){
            TreeHouseFlowerpotDO flowerpotDO=new TreeHouseFlowerpotDO();
            BeanUtils.copyProperties(flowerpot,flowerpotDO);
            if(flowerpot.getPlantId()!=null){
                TreeHouseFlowerpotPlayer player=treeHouseFlowerpotPlayerService.getByPlantIdAndUserId(flowerpot.getPlantId(),treeHouseCultivatePO.getUserId());
                if(player!=null)
                    flowerpotDO.setCanWork(false);
                else
                    flowerpotDO.setCanWork(true);
            }else{
                flowerpotDO.setCanWork(false);
            }
            flowerpotDOS.add(flowerpotDO);
        }
        User user=userService.findByUserId(treeHouseCultivatePO.getTreeHouseUserId());
        TreeHouseCultivateDetail detail=new TreeHouseCultivateDetail();
        detail.setTreeHouseUserName(user.getName());
        detail.setTreeHouseId(treeHouse.getId());
        detail.setAmbienceCount(treeHouse.getAmbienceCount());
        detail.setAmbienceCountFull(treeHouseSetting.getAmbienceCount());
        detail.setAmusementCount(treeHouse.getAmusementCount());
        detail.setAmusementCountFull(treeHouseSetting.getAmusementCount());
        detail.setEnvironmentCount(treeHouse.getEnvironmentCount());
        detail.setEnvironmentCountFull(treeHouseSetting.getEnvironmentCount());
        detail.setVisitors(visitors);
        detail.setFurnitures(furnitureList);
        detail.setFlowerpotDOS(flowerpotDOS);
        detail.setStyle(treeHouse.getStyle());
        detail.setCanSpeak(treeHouse.getCanSpeak());
        Map<String,Object> message=null;
        for (TreeHouseVisitorDO entry :visitors) {
            if(treeHouseCultivatePO.getUserId().equals(entry.getUserId())){
                message=new HashMap<>();
                message.put("type","enterTreeHouse");
                message.put("code",0);
                message.put("userId",entry.getUserId());
                message.put("userName",entry.getUserName());
                message.put("roleId",entry.getRoleId());
                message.put("sex",entry.getSex());
                message.put("petId",entry.getPetId());
                message.put("workStatus",entry.getWorkStatus());
                message.put("canSpeak",entry.getCanSpeak());
            }
        }
        for (TreeHouseVisitorDO entry :visitors) {
            if(message!=null && entry.getUserId()!=null && !entry.getUserId().equals(treeHouseCultivatePO.getUserId()) )
                chatMessageService.sendMessageToSomeBody(entry.getUserId(),JSON.toJSONString(message));
        }
        return new BaseReturn("查询成功！",detail);
    }



    /*@ApiOperation(value = "指派工作",notes = "请求参数说明 [userId 用户ID][workerUserId 被指派的用户ID][workType 1浇水  2施肥  3打扫]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明")
    })
    @PostMapping("/appointWork")
    public BaseReturn appointWork(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getWorkerUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"被指派的用户ID为空！");

        User user=userService.findByUserId(treeHouseCultivatePO.getUserId());
        UserRole userRole=roleService.getUserRoleByUserId(user.getUserId());
        TreeHouseWorkSetting setting=treeHouseWorkSettingService.getByRoleLevel(userRole.getRoleLevel());
        StringBuffer sb=new StringBuffer();
        int exp=0;
        int money=0;
        sb.append(user.getName()).append("指派了你进行");
        exp=setting.getExpCommon();
        sb.append("普通");
        if(treeHouseCultivatePO.getWorkType()==1)
            sb.append("浇水");
        if(treeHouseCultivatePO.getWorkType()==2)
            sb.append("施肥");
        if(treeHouseCultivatePO.getWorkType()==3)
            sb.append("打扫");
        sb.append(",持续15分钟。");

        chatMessageService.appointWork(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getWorkerUserId(),
                treeHouseCultivatePO.getWorkType(),1,user.getSex(),exp,sb.toString(),money);
        return new BaseReturn("指派成功！");
    }*/




    @ApiOperation(value = "盆栽日记",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [operator ][type 工作类型 1:浇水 2:施肥 3:收获]")
    })
    @PostMapping("/flowerpotLogList")
    public BaseReturn flowerpotLogList(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋用户ID为空！");

        List<TreeHouseFlowerpotLog> dataList=treeHouseFlowerpotLogService.findByTreeHouseUserId(treeHouseCultivatePO.getTreeHouseUserId());
        return new BaseReturn("查找成功！",dataList);
    }


    @ApiOperation(value = "清理已布置的家具",notes = "请求参数说明 [userId 用户ID][goodsId 家具ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [data 家具][goodsId 道具ID][location 家具摆放位置]")
    })
    @PostMapping("/cancelArrangedFurniture")
    public BaseReturn cancelArrangedFurniture(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getGoodsId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"已布置的家具ID为空！");

        treeHouseFurnitureService.cancelArrangedFurniture(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getGoodsId());
        List<TreeHouseFurniture> furnitureList=treeHouseFurnitureService.findCollocationByTreeHouseId(treeHouseCultivatePO.getUserId());
        return new BaseReturn("清理家具成功！",furnitureList);
    }


    @ApiOperation(value = "布置家具",notes = "请求参数说明 [userId 用户ID][goodsId 家具ID][location 摆放位置(大于0)]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/arrangeFurniture")
    public BaseReturn arrangeFurniture(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getGoodsId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"布置的家具ID为空！");
        if(treeHouseCultivatePO.getLocation()==null || treeHouseCultivatePO.getLocation()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择摆放位置！");

        treeHouseFurnitureService.arrange(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getGoodsId(),treeHouseCultivatePO.getLocation());
        return new BaseReturn("布置家具成功！");
    }


    @ApiOperation(value = "移动家具",notes = "请求参数说明 [userId 用户ID][furnitures：goodsId 已布置的家具id][furnitures location 摆放位置(大于0)]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/moveFurniture")
    public BaseReturn moveFurniture(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(CollectionUtils.isEmpty(treeHouseCultivatePO.getFurnitures()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请传入移动的家具！");

        treeHouseFurnitureService.move(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getFurnitures());
        return new BaseReturn("移动家具成功！");
    }



    @ApiOperation(value = "邀请参观树屋",notes = "请求参数说明 [userId 用户ID][inviteeUserId 被邀请人用户ID][treeHouseUserId 树屋主人用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/inviteToTreeHouse")
    public BaseReturn inviteToTreeHouse(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);
        if(StringUtils.isBlank(treeHouseCultivatePO.getInviteeUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"被邀请人用户ID为空！");

        UserMessage message=new UserMessage();
        message.setId(UUIDUtil.generateUUID());
        message.setMessageBody("邀请你去他的树屋做客！");
        message.setSenderId(treeHouseCultivatePO.getUserId());
        message.setReceiverId(treeHouseCultivatePO.getInviteeUserId());
        message.setType(MessageConstant.TYPE_TREE_HOUSE_INVITE);
        messageService.addUserMessage(message);
        return new BaseReturn("success");
    }



    @ApiOperation(value = "邀请所有朋友参观树屋",notes = "请求参数说明 [userId 用户ID][inviteeUserId 被邀请人用户ID][treeHouseUserId 树屋主人用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/inviteAllFriendToTreeHouse")
    public BaseReturn inviteAllFriendToTreeHouse(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);
        if(StringUtils.isBlank(treeHouseCultivatePO.getInviteeUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"被邀请人用户ID为空！");

        UserMessage message=new UserMessage();
        message.setId(UUIDUtil.generateUUID());
        message.setMessageBody("邀请你去他的树屋做客！");
        message.setSenderId(treeHouseCultivatePO.getUserId());
        message.setReceiverId(treeHouseCultivatePO.getInviteeUserId());
        message.setType(MessageConstant.TYPE_TREE_HOUSE_INVITE);

        List<FriendDO> friendDOList= friendService.findFriendList(treeHouseCultivatePO.getUserId());
        StringBuffer userIds=new StringBuffer();
        if(friendDOList!=null && !friendDOList.isEmpty()){
            for(int i=0;i<friendDOList.size();i++){
                userIds.append(friendDOList.get(i).getUserId());
                if(i<friendDOList.size()-1){
                  userIds.append(",");
                }
            }
            messageService.inviteFriendToTreeHouse(treeHouseCultivatePO.getUserId(),userIds.toString());
        }
        return new BaseReturn("success");
    }



    @ApiOperation(value = "解锁花盆",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋ID][flowerpotId 花盆ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/unLockFlowerpot")
    public BaseReturn unLockFlowerpot(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋ID为空！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getFlowerpotId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"花盆ID为空！");

        UserRole role=roleService.getUserRoleByUserId(treeHouseCultivatePO.getTreeHouseUserId());
        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotService.getById(treeHouseCultivatePO.getFlowerpotId());
        if(flowerpot.getLockLevel()>role.getRoleLevel()){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"角色等级未达到！");
        }
        treeHouseFlowerpotService.unlock(treeHouseCultivatePO.getFlowerpotId());
        return new BaseReturn("解锁花盆成功！");
    }



    @ApiOperation(value = "切换装修风格",notes = "请求参数说明 [userId 用户ID][treeHouseId 树屋ID][style 风格ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/changeTreeHouseStyle")
    public BaseReturn changeTreeHouseStyle(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"树屋ID为空！");
        if(treeHouseCultivatePO.getStyle()==null || treeHouseCultivatePO.getStyle()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择装修风格！");

        TreeHouse treeHouse=new TreeHouse();
        treeHouse.setStyle(treeHouseCultivatePO.getStyle());
        TreeHouseExample example=new TreeHouseExample();
        example.createCriteria().andIdEqualTo(treeHouseCultivatePO.getTreeHouseId());
        treeHouseService.updateTreeHouse(treeHouse,example);
        return new BaseReturn("装修风格切换成功！");
    }



    @ApiOperation(value = "关闭/开启游客位置",notes = "请求参数说明 [userId 用户ID][locationId 位置ID][type 类型  1：关闭  2：开启]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/manageVisitorLocation")
    public BaseReturn manageVisitorLocation(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){

        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getLocationId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"位置ID为空！");
        if(treeHouseCultivatePO.getType()==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择开启或关闭！");

        TreeHouseVisitor visitor=new TreeHouseVisitor();
        visitor.setId(treeHouseCultivatePO.getLocationId());
        if(treeHouseCultivatePO.getType()==1){
            visitor.setStatus(TreeHouseVisitorConstant.STATUS_DISABLE);
            treeHouseVisitorService.updateById(visitor);
        }else if(treeHouseCultivatePO.getType()==2){
            visitor.setStatus(TreeHouseVisitorConstant.STATUS_ABLE);
            treeHouseVisitorService.updateById(visitor);
        }else{
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择开启或关闭！");
        }
        return new BaseReturn("操作成功！");
    }



    @ApiOperation(value = "播种",notes = "请求参数说明 [userId 用户ID][flowerpotId 盆栽ID][goodsId 种子ID][treeHouseUserId 树屋主人用户ID]" +
            "[id 花盆ID][flowerStatus 花状态 1种子,2发芽，3生长，4开花][lockLevel 解锁等级][lockStatus 解锁状态 0：锁定，1：解锁][goodsId 种子ID][waterCount 浇水次数][spreadManureCount 施肥次数]</br>")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/sowSeeds")
    public BaseReturn sowSeeds(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getFlowerpotId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择要播种的盆栽！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getGoodsId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择要播撒的花种！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);
        TreeHouseFlowerpot treeHouseFlowerpot=treeHouseFlowerpotService.getById(treeHouseCultivatePO.getFlowerpotId());
        if(StringUtils.isNotBlank(treeHouseFlowerpot.getGoodsId()))
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该盆栽已播种！");
        treeHouseFlowerpotService.sowSeeds(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getFlowerpotId(),treeHouseCultivatePO.getGoodsId());
        List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotService.findByTreeHouseId(treeHouseCultivatePO.getTreeHouseUserId());
        return new BaseReturn("播种成功!",flowerpots);
    }



    @ApiOperation(value = "禁言",notes = "请求参数说明 [userId 用户ID][targetUserId 要禁言的用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/noSpeak")
    public BaseReturn noSpeak(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTargetUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"要禁言的用户ID为空！");

        Map<String,Integer> users=(Map<String,Integer>)redisService.get(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE +treeHouseCultivatePO.getUserId());
        if (users == null) {
            users=new HashMap<>();
            users.put(treeHouseCultivatePO.getTargetUserId(),1);
        }else{
            if (!users.containsKey(treeHouseCultivatePO.getTargetUserId())) {
                users.put(treeHouseCultivatePO.getTargetUserId(),1);
            }
        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("type","noSpeakingSingle");
        msg.put("code",0);
        chatMessageService.sendMessageToSomeBody(treeHouseCultivatePO.getTargetUserId(),JSON.toJSONString(msg));
        redisService.set(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE +treeHouseCultivatePO.getUserId(),users);
        return new BaseReturn("禁言成功!");
    }

    @ApiOperation(value = "全部禁言",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/allNoSpeak")
    public BaseReturn allNoSpeak(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        treeHouseService.allNoSpeak(treeHouseCultivatePO.getUserId());
        redisService.set(RedisKey.All_CAN_NOT_SPEAK_TREE_HOUSE+treeHouseCultivatePO.getUserId(),1);
        return new BaseReturn("全部禁言成功!");
    }


    @ApiOperation(value = "解除全部禁言",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/cancelAllNoSpeak")
    public BaseReturn cancelAllNoSpeak(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        treeHouseService.cancelAllNoSpeak(treeHouseCultivatePO.getUserId());
        redisService.remove(RedisKey.All_CAN_NOT_SPEAK_TREE_HOUSE+treeHouseCultivatePO.getUserId());
        return new BaseReturn("解除全部禁言成功!");
    }


    @ApiOperation(value = "解除禁言",notes = "请求参数说明 [userId 用户ID][targetUserId 要解除禁言的用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/cancelNoSpeak")
    public BaseReturn cancelNoSpeak(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTargetUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"解除禁言的用户ID为空！");

        Map<String,Integer> users=(Map<String,Integer>)redisService.get(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE +treeHouseCultivatePO.getUserId());
        if(users!=null){
            Map<String,Integer> newUsers=new HashMap<>();
            for(Map.Entry<String,Integer> entry:users.entrySet()){
                if(!entry.getKey().equals(treeHouseCultivatePO.getTargetUserId())){
                    newUsers.put(entry.getKey(),entry.getValue());
                }
            }
            Map<String,Object> msg=new HashMap<>();
            msg.put("type","canSpeakingSingle");
            msg.put("code",0);
            chatMessageService.sendMessageToSomeBody(treeHouseCultivatePO.getTargetUserId(),JSON.toJSONString(msg));
            if(!newUsers.isEmpty()) redisService.set(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE +treeHouseCultivatePO.getUserId(),newUsers);
        }
        return new BaseReturn("解除禁言成功!");
    }



    @ApiOperation(value = "浇水",notes = "请求参数说明 [userId 用户ID]" +
            "[flowerpotId 盆栽ID][treeHouseUserId 树屋主人用户ID]" +
            "[type 1：自己浇水  2：指派后同意浇水]" +
            "[grade 指派等级 1：初级  2：中级  3：高级]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[exp 获得经验] [flowerpots 盆栽][id 花盆ID][flowerStatus 花状态 1种子,2发芽，3生长，4开花][lockLevel 解锁等级][lockStatus 解锁状态 0：锁定，1：解锁][goodsId 种子ID][waterCount 浇水次数][spreadManureCount 施肥次数]")
    })
    @PostMapping("/watering")
    public BaseReturn watering(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(treeHouseCultivatePO.getType()==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择浇水类型！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getFlowerpotId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择要浇水的盆栽！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);

        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotService.getById(treeHouseCultivatePO.getFlowerpotId());
        if(flowerpot==null || !flowerpot.getTreeHouseId().equals(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"盆栽不存在！");
        TreeHouseFlowerpotPlayer player=treeHouseFlowerpotPlayerService.getByPlantIdAndUserId(flowerpot.getPlantId(),treeHouseCultivatePO.getUserId());
        if(player!=null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"已浇过水！");

        List<UserGoods> vigourList=new ArrayList<>();
        UserGoods vigour=new UserGoods();
        vigour.setGoodsId(GoodsConstant.GOODS_VIGOUR);
        vigour.setAmount(1);
        vigourList.add(vigour);
        Map<String, Object> result = new HashMap<>();
        List<UserGoods> userGoods=userGoodsService.subUserGoods(treeHouseCultivatePO.getUserId(),vigourList);
        if(CollectionUtils.isEmpty(userGoods)){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"体力不足");
        }else{
            UserGoods vigour1=userGoods.get(0);
            vigour1.setId(vigour1.getGoodsId()+"");
            vigour1.setType(GoodsTypeConstant.TYPE_PROP);
            result.put("vigour", vigour1);
        }

        Map<String, Object> data=treeHouseFlowerpotService.watering(flowerpot,treeHouseCultivatePO.getUserId(), treeHouseCultivatePO.getTreeHouseUserId());
        List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotService.findByTreeHouseId(treeHouseCultivatePO.getTreeHouseUserId());
        result.put("flowerpots",flowerpots);
        if(data!=null && data.entrySet().size()>0){
            for(Map.Entry<String,Object> entry:data.entrySet()){
                result.put(entry.getKey(),entry.getValue());
            }
        }
        return new BaseReturn("浇水成功!",result);
    }

    @ApiOperation(value = "施肥",notes = "请求参数说明 [userId 用户ID][flowerpotId 盆栽ID]" +
            "[treeHouseUserId 树屋主人用户ID]" +
            "[type 1：自己施肥  2：指派后同意施肥]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [id 花盆ID][flowerStatus 花状态 1种子,2发芽，3生长，4开花][lockLevel 解锁等级][lockStatus 解锁状态 0：锁定，1：解锁][goodsId 种子ID][waterCount 浇水次数][spreadManureCount 施肥次数]")
    })
    @PostMapping("/spreadManure")
    public BaseReturn spreadManure(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){

        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(treeHouseCultivatePO.getType()==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择施肥类型！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getFlowerpotId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择要施肥的盆栽！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);

        TreeHouseFlowerpot flowerpot=treeHouseFlowerpotService.getById(treeHouseCultivatePO.getFlowerpotId());
        if(flowerpot==null || !flowerpot.getTreeHouseId().equals(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"盆栽不存在！");
        TreeHouseFlowerpotPlayer player=treeHouseFlowerpotPlayerService.getByPlantIdAndUserId(flowerpot.getPlantId(),treeHouseCultivatePO.getUserId());
        if(player!=null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"已施过肥了！");

        List<UserGoods> vigourList=new ArrayList<>();
        UserGoods vigour=new UserGoods();
        vigour.setGoodsId(GoodsConstant.GOODS_VIGOUR);
        vigour.setAmount(1);
        vigourList.add(vigour);
        Map<String, Object> result = new HashMap<>();
        List<UserGoods> userGoods=userGoodsService.subUserGoods(treeHouseCultivatePO.getUserId(),vigourList);
        if(CollectionUtils.isEmpty(userGoods)){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"体力不足");
        }else{
            UserGoods vigour1=userGoods.get(0);
            vigour1.setId(vigour1.getGoodsId()+"");
            vigour1.setType(GoodsTypeConstant.TYPE_PROP);
            result.put("vigour", vigour1);
        }
        Map<String,Object> data=treeHouseFlowerpotService.spreadManure(flowerpot,treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getTreeHouseUserId() );
        //taskService.taskProgress(TreeHouseFlowerpotConstant.TASK_TYPE_SWGZ,1,treeHouseCultivatePO.getUserId());
        List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotService.findByTreeHouseId(treeHouseCultivatePO.getTreeHouseUserId());
        result.put("flowerpots",flowerpots);
        if(data!=null && data.entrySet().size()>0){
            for(Map.Entry<String,Object> entry:data.entrySet()){
                result.put(entry.getKey(),entry.getValue());
            }
        }
        return new BaseReturn("施肥成功!",result);
    }


    @ApiOperation(value = "打扫",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋主人用户ID]" +
            "[type 1：自己打扫  2：指派后同意打扫][grade 指派等级 1：初级  2：中级  3：高级]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/sweep")
    public BaseReturn sweep(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请传入树屋主人用户ID！");
        if(treeHouseCultivatePO.getType()==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择打扫类型！");

        Object obj=redisService.get(RedisKey.SWEEP_TREE_HOUSE+treeHouseCultivatePO.getTreeHouseUserId());
        if(obj!=null){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"已打扫!");
        }else{
            if(treeHouseCultivatePO.getType()==1){
                Map<String,Object> data=new HashMap<>();
                data.put("watering_self",treeHouseCultivatePO.getUserId());
                redisService.set(RedisKey.SWEEP_TREE_HOUSE+treeHouseCultivatePO.getTreeHouseUserId(),data,15*60L);//持续15分钟持续15分钟
            }else if(treeHouseCultivatePO.getType()==2){
                Map<String,String> data=new HashMap<>();
                data.put("sweep_worker",treeHouseCultivatePO.getUserId());
                redisService.set(RedisKey.SWEEP_TREE_HOUSE+treeHouseCultivatePO.getTreeHouseUserId(),data,15*60L);//持续15分钟持续15分钟
                taskService.taskProgress(TreeHouseFlowerpotConstant.TASK_TYPE_SWGZ,1,treeHouseCultivatePO.getUserId());
            }else {
                return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"施肥类型不对！");
            }
        }
        return new BaseReturn("操作成功！");
    }



    @ApiOperation(value = "发布工作任务",notes = "请求参数说明 [userId 用户ID][workType 工作类型 1浇水 2施肥 3打扫]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [id ][goodsId 道具ID][amount 数量]")
    })
    @PostMapping("/publishWork")
    public BaseReturn publishWork(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(treeHouseCultivatePO.getWorkType()==null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请传入工作类型！");

        User user=userService.findByUserId(treeHouseCultivatePO.getUserId());
        String content="";
        int money=TreeHouseConstant.map.get("allocating-1").get("money");
        switch (treeHouseCultivatePO.getWorkType()){
            case 1:
                content=user.getName()+"发布了浇水工作，我要工作";
                break;
            case 2:
                content=user.getName()+"发布了施肥工作，我要工作";
                break;
            case 3:
                content=user.getName()+"发布了打扫工作，我要工作";
                break;
        }
        UserGoods goods=null;
        try {
            goods=userGoodsService.subUserGoodsSingle(treeHouseCultivatePO.getUserId(),GoodsConstant.GOODS_MONEY,money);
        }catch (Exception e){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"金币余额不足！");
        }

        ObjectUtil.setObjectFieldsEmpty(goods, "id", "goodsId", "amount","type");
        goods.setId(goods.getGoodsId());
        JSONObject object=new JSONObject();
        object.put("type","chat");
        object.put("chatWay","worldchat");
        object.put("content",content);
        object.put("header",user.getPortraitUrl());
        object.put("senderName",user.getName());
        object.put("sex",user.getSex());
        object.put("code",0);
        object.put("treeHouseUserId",treeHouseCultivatePO.getUserId());
        object.put("workType",treeHouseCultivatePO.getWorkType());
        object.put("grade",1);
        chatMessageService.sendMessageToAll(JSON.toJSONString(object));
        treeHouseWorkLogService.add(JSON.toJSONString(object));
        return new BaseReturn("发布成功！",goods);
    }

    @ApiOperation(value = "查找工作消息",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/find2HoursWorkLog")
    public BaseReturn find2HoursWorkLog(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        List<TreeHouseWorkLog> logs=treeHouseWorkLogService.find2HourLog();
        JSONArray array=new JSONArray();
        for(TreeHouseWorkLog log:logs){
            array.add(JSON.parse(log.getMsgContent()));
        }
        return new BaseReturn("success",array);
    }

    @ApiOperation(value = "收获",notes = "请求参数说明 [userId 用户ID][flowerpotId 盆栽ID][treeHouseUserId 树屋主人用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [isFirstObtain 首次获得][goodsId 花种ID]" +
                    "[flowerpotsList 花盆列表][id 花盆ID][flowerStatus 花状态 1种子,2发芽，3生长，4开花][lockLevel 解锁等级][lockStatus 解锁状态 0：锁定，1：解锁][goodsId 种子ID][waterCount 浇水次数][spreadManureCount 施肥次数]</br>")
    })
    @PostMapping("/harvest")
    public BaseReturn harvest(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        //if(treeHouseCultivatePO.getType()==null)
         //   return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择收获类型！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getFlowerpotId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择要收获的盆栽！");
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);
        Map<String,Object> result=new HashMap<>();
        if(!treeHouseCultivatePO.getUserId().equals(treeHouseCultivatePO.getTreeHouseUserId())){
            try {
                Map<String,Object> data=treeHouseFlowerpotService.harvestOthers(treeHouseCultivatePO.getFlowerpotId(),treeHouseCultivatePO.getUserId());
                result.put("award",data);
                return new BaseReturn("收获成功！",result);
            }catch (CheckException e){
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
            }
        }else{
            try {
                TreeHouseFlowerpot flowerpot=treeHouseFlowerpotService.getById(treeHouseCultivatePO.getFlowerpotId());
                HoldFlowers flowers=holdFlowersService.findByGoodsId(treeHouseCultivatePO.getTreeHouseUserId(),flowerpot.getGoodsId());
                boolean isFirstObtain=false;
                if(flowers==null){
                    isFirstObtain=true;
                    result.put("goodsId",flowerpot.getGoodsId());
                }
                result.put("isFirstObtain",isFirstObtain);
                Map<String,Object> data=treeHouseFlowerpotService.harvest(treeHouseCultivatePO.getFlowerpotId());
                List<TreeHouseFlowerpot> flowerpots=treeHouseFlowerpotService.findByTreeHouseId(flowerpot.getTreeHouseId());
                result.put("flowerpotsList",flowerpots);
                result.put("award",data);
                return new BaseReturn("收获成功！",result);
            }catch (CheckException e){
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
            }
        }
    }



    @ApiOperation(value = "获得点亮的盆栽",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋主人用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [goodsId 花物品ID]")
    })
    @PostMapping("/ownFlowerList")
    public BaseReturn ownFlowerList(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);

          List<HoldFlowers> flowers=holdFlowersService.findByUserId(treeHouseCultivatePO.getTreeHouseUserId());
        return new BaseReturn("查询成功！",flowers);
    }


    @ApiOperation(value = "请出树屋",notes = "请求参数说明 [userId 用户ID][outUserId 请出的游客用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [id 位置ID][roleId 角色ID][userName 用户名称][petId 宠物ID]" +
                    "[sex 性别 1：男，2：女][status 位置状态 0：关闭  1：开启][workStatus 1:工作中]")
    })
    @PostMapping("/letOut")
    public BaseReturn letOut(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getOutUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请传入要请出的游客用户ID！");

        treeHouseVisitorService.letOutVisitor(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getOutUserId());
        List<TreeHouseVisitorDO> visitors=treeHouseVisitorService.findTreeHouseVisitor(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getUserId());
        return new BaseReturn("操作成功！",visitors);
    }


    @ApiOperation(value = "离开树屋",notes = "请求参数说明 [userId 用户ID][treeHouseUserId 树屋用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[waterExp 浇水获得的经验][spreadExp 施肥获得的经验][sweepExp 打扫获得的经验]")
    })
    @PostMapping("/leaveTreeHouse")
    public BaseReturn leaveTreeHouse(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(treeHouseCultivatePO.getTreeHouseUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,pleaseInputTreeHouseUserId);

        Object treeObj = redisService.get(RedisKey.CHAT_TREE_HOUSE_USER+treeHouseCultivatePO.getUserId());
        if (treeObj != null) {
            String treeHouseUserId = (String) treeObj;
            Map<String, Integer> users = (Map<String, Integer>) redisService.get(RedisKey.IN_TREE_HOUSE + treeHouseUserId);
            Map<String, Integer> result = new HashMap<>();
            for (Map.Entry<String, Integer> entry : users.entrySet()) {
                if (!entry.getKey().equals(treeHouseCultivatePO.getUserId())) {
                    result.put(entry.getKey(), entry.getValue());
                    Map<String,Object> message=new HashMap<>();
                    message.put("type","leaveTreeHouse");
                    message.put("code",0);
                    message.put("userId",treeHouseCultivatePO.getUserId());
                    chatMessageService.sendMessageToSomeBody(entry.getKey(),JSON.toJSONString(message));
                }
            }
            treeHouseVisitorService.updateByTreeHouseIdAndVisitorId(treeHouseCultivatePO.getTreeHouseUserId(),treeHouseCultivatePO.getUserId());
            redisService.set(RedisKey.IN_TREE_HOUSE + treeHouseUserId, result);
        }

        Object water=redisService.get(RedisKey.TREE_HOUSE_WATERING+treeHouseCultivatePO.getTreeHouseUserId());
        Object spread=redisService.get(RedisKey.TREE_HOUSE_SPREAD_MANURE+treeHouseCultivatePO.getTreeHouseUserId());
        Object sweep=redisService.get(RedisKey.SWEEP_TREE_HOUSE+treeHouseCultivatePO.getTreeHouseUserId());
        Map<String,Object> result=new HashMap<>();
        TreeHouseWorkSetting setting=null;
        if(water!=null || spread!=null || sweep!=null){
            UserRole role=roleService.getUserRoleByUserId(treeHouseCultivatePO.getUserId());
            setting=treeHouseWorkSettingService.getByRoleLevel(role.getRoleLevel());
        }
        if(setting!=null && water!=null){
            Map<String,String> data=(Map<String, String>)water;
            if(data.get("watering_worker").equals(treeHouseCultivatePO.getUserId())){
                int count=setting.getOutput();
                Map<String, Object> map=roleService.updateRoleExp(treeHouseCultivatePO.getUserId(),count);
                result.put("waterExp",count);
            }
        }

        if(setting!=null && spread!=null){
            Map<String,String> data=(Map<String, String>)spread;
            if(data.get("spreadManure_worker").equals(treeHouseCultivatePO.getUserId())){
                int count=setting.getOutput();
                roleService.updateRoleExp(treeHouseCultivatePO.getUserId(),count);
                result.put("spreadExp",count);
            }

        }

        if(setting!=null && sweep!=null){
            Map<String,String> data=(Map<String, String>)sweep;
            if(data.get("sweep_worker").equals(treeHouseCultivatePO.getUserId())){
                int count=setting.getOutput();
                roleService.updateRoleExp(treeHouseCultivatePO.getUserId(),count);
                result.put("sweepExp",count);
            }
        }

        return new BaseReturn("成功离开树屋！",result);
    }



    @ApiOperation(value = "获取拥有的家具列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [goodsId 家具物品ID][canForever 是否永久使用][status 状态 0：未布置 1：已布置][lastTime 可用时间(单位/小时)]")
    })
    @PostMapping("/ownFurnitureList")
    public BaseReturn ownFurnitureList(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<TreeHouseFurniture> furnitureList=treeHouseFurnitureService.findOwnFurnitureList(treeHouseCultivatePO.getUserId());
        for(TreeHouseFurniture furniture:furnitureList){
            if(furniture.getEndDate()!=null)
                furniture.setLastTime((int)((furniture.getEndDate().getTime()- System.currentTimeMillis())/1000));
        }
        return new BaseReturn("操作成功！",furnitureList);
    }


    @ApiOperation(value = "获取拥有的家具详情",notes = "请求参数说明 [userId 用户ID][id 家具ID]" +
            "[id 家具ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[data 可用时间(单位/秒)]")
    })
    @PostMapping("/getFurnitureDetail")
    public BaseReturn getFurnitureDetail(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        TreeHouseFurniture furniture=treeHouseFurnitureService.getById(treeHouseCultivatePO.getId());
        Integer lastSeconds=null;
        if(furniture!=null && !furniture.getCanForever() && furniture.getEndDate()!=null){
            lastSeconds=DateComputeUtil.countSecondBetweenTwoDate(new Date(),furniture.getEndDate());
        }
        return new BaseReturn("操作成功!",lastSeconds);
    }



    @ApiOperation(value = "获取拥有的种子列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[goodsId 种子ID][amount 数量]")
    })
    @PostMapping("/findMySeeds")
    public BaseReturn findMySeeds(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<PropInfoVO> seeds=userGoodsService.querySeedsPropList(treeHouseCultivatePO.getUserId());
        List<PropInfoVO> result=new ArrayList<>();
        if(seeds!=null && !seeds.isEmpty()){
            seeds.forEach(seed->{
                PropInfoVO vo=new PropInfoVO();
                ObjectUtil.setObjectFieldsEmpty(seed,"goodsId","amount");
                if(seed.getAmount()>0){
                    BeanUtils.copyProperties(seed,vo);
                    result.add(vo);
                }
            });
        }
        return new BaseReturn("获取成功！",result);
    }



    @ApiOperation(value = "购买种子",notes = "请求参数说明 [userId 用户ID][goodsId 种子ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[goodsId 种子ID][amount 数量]")
    })
    @PostMapping("/buyOneSeeds")
    public BaseReturn buyOneSeeds(@RequestBody TreeHouseCultivatePO treeHouseCultivatePO){
        if(StringUtils.isBlank(treeHouseCultivatePO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        UserGoods userGoods=userGoodsService.buyProp(treeHouseCultivatePO.getUserId(),treeHouseCultivatePO.getGoodsId());
        userGoods.setId(userGoods.getGoodsId());
        return new BaseReturn("购买成功！",userGoods);
    }

}
