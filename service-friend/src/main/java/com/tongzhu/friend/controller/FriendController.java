package com.tongzhu.friend.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.friend.constant.FriendConstant;
import com.tongzhu.friend.domain.GameNPC;
import com.tongzhu.friend.domain.User;
import com.tongzhu.friend.domain.UserDetail;
import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.mapper.vo.FriendDO;
import com.tongzhu.friend.model.ChatHistoryMessage;
import com.tongzhu.friend.model.Friend;
import com.tongzhu.friend.po.FriendPO;
import com.tongzhu.friend.redis.RedisService;
import com.tongzhu.friend.service.*;
import com.tongzhu.friend.service.vo.FriendDetailVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 好友相关
 */
@Api(value="好友controller",tags={"好友相关API"})
@RestController
@RequestMapping("/friendList")
public class  FriendController  {

    private Logger log= LoggerFactory.getLogger(FriendController.class);

    @Autowired
    private FriendService friendService;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ChatHistoryMessageService chatHistoryMessageService;

    @Autowired
    private ChatMessageService chatMessageService;




    private String userIdIsNull="用户ID为空！";



    /**
     * 根据userId查找好友
     * @param friendPO
     *          --userId 要查找的好友用户ID
     * @return
     */
    @ApiOperation(value = "根据userId查找好友",notes = "请求参数说明 [userId 用户ID] [searchUser 要查找的好友Id或名称]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[userId 用户ID] [userName 用户名称] [portraitUrl 头像][roleLevel 角色等级] " +
                    "[roleId 角色ID] [sex 性别 1:男 2：女][isGameFriend 是否为好友]")
    })
    @PostMapping("/searchUserUserId")
    public BaseReturn searchUserUserId(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(friendPO.getSearchUser()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请输入好友搜索条件！");

        List<UserDetail> userDetails=friendService.searchUserByUserIdOrName(friendPO.getSearchUser());

        if(!CollectionUtils.isEmpty(userDetails)){
            UserDetail userDetail=userDetails.get(0);
            Friend friend=friendService.checkIsMyFriend(friendPO.getUserId(),friendPO.getSearchUser());
            if(friend!=null){
                userDetail.setIsGameFriend(true);
            }else {
                userDetail.setIsGameFriend(false);
            }
            return new BaseReturn("查找玩家成功！",userDetail);
        }else{
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"未查询到该名玩家，请重新输入搜索条件！");
        }
    }


    /**
     * 请求添加好友
     * @param friendPO
     *          --userId 用户ID
     *          --friendId 要添加的好友用户ID
     * @return
     */
    @ApiOperation(value = "申请添加好友",notes = "请求参数说明 [userId 用户ID][friendId 要添加的好友用户ID]")
    @PostMapping("/requestAddFriend")
    public BaseReturn requestAddFriend(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(friendPO.getFriendId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"好友用户ID为空！");

        User user=userService.findByUserId(friendPO.getFriendId());
        if(user==null) return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"好友用户ID不存在！");

        int myCount=friendService.countFriends(friendPO.getUserId());
        if(myCount> FriendConstant.MAX_FRIEND_NUM)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"你的好友列表已满，无法添加");
        int friendCount=friendService.countFriends(friendPO.getFriendId());
        if(friendCount>FriendConstant.MAX_FRIEND_NUM)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"对方的好友列表已满，无法添加");

        int count=friendService.countFriendToBeConfirmByFriendId(friendPO.getFriendId());
        if(count>FriendConstant.MAX_APPLY_FOR_FRIEND_COUNT)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"该玩家好友申请数量已达上限，请稍后再试。");
        if(redisService.get("_refuse_friend_"+friendPO.getFriendId()+"_"+friendPO.getUserId())!=null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"对方已拒绝过你的好友请求，暂不能发送请求");
        Friend friend=friendService.checkIsMyFriend(friendPO.getUserId(),friendPO.getFriendId());
        if(friend!=null) return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"你们已经是好友了，请不要重复申请!");

        Friend friendApply=friendService.checkIsApplyMyFriend(friendPO.getUserId(),friendPO.getFriendId());
        if(friendApply!=null) return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"您已发送过申请，不要重复发送!");

        int result=friendService.requestAddFriend(friendPO.getUserId(),friendPO.getFriendId());
        if(result>0){
            chatMessageService.sendMessageToSomeBody(friendPO.getFriendId(),"{\"models\":[\"addFriend:1\"],\"code\":\"0\",\"type\":\"redTip\"}");
            return new BaseReturn("申请成功，等待确认！");
        }
        return new  BaseReturn("申请失败！");
    }



    /**
     * 确认加好友请求（同意、拒绝）
     * @param friendPO
     *          --userId 用户ID
     *          --friendId 好友用户ID
     *          --type 同意：1  拒绝：2
     * @return
     */
    @ApiOperation(value = "请求添加好友",notes = "请求参数说明 [userId 用户ID] [friendId 好友用户ID] [type 同意：1  拒绝：2]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [name 用户名字] [portraitUrl 头像url][sex 性别]" +
                    "[status  在线状态  1 在线   0 离线] [intimacy 亲密度] [roleId 角色ID] [roleLevel 角色等级][isLovers 是否为情侣 true是  false不是][maxFriendNumInfo 在线]")
    })
    @PostMapping("/confirmAddFriend")
    public BaseReturn confirmAddFriend(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(friendPO.getFriendId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"好友ID为空！");
        if(friendPO.getType()==0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择同意或拒绝！");

        int myCount=friendService.countFriends(friendPO.getUserId());
        if(myCount>FriendConstant.MAX_FRIEND_NUM)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"您的好友数量已达上限!");
        int friendCount=friendService.countFriends(friendPO.getFriendId());
        if(friendCount>FriendConstant.MAX_FRIEND_NUM)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"对方的好友数量已达上限");

        if(friendPO.getType()==2){
            redisService.set("_refuse_friend_"+friendPO.getUserId()+"_"+friendPO.getFriendId(),1,2*60*60l);
        }
        FriendDetailVO userFriendDetailVO=friendService.confirmAddFriend(friendPO.getUserId(),friendPO.getFriendId(),friendPO.getType());
        if(userFriendDetailVO!=null){
            List<FriendDO> list=friendService.findFriendList(friendPO.getUserId());
            UserRole role=userService.getUserRoleByUserId(friendPO.getUserId());
            int onlineCount=0;
            for(FriendDO friendDO:list){
                if(friendDO.getStatus()==FriendConstant.STATUS_ONLINE)
                    onlineCount++;
            }
            if(StringUtils.isNotBlank(role.getSpouse()) && role.getSpouse()==userFriendDetailVO.getUserId())
                userFriendDetailVO.setLovers(true);
            else userFriendDetailVO.setLovers(false);
            userFriendDetailVO.setMaxFriendNumInfo( onlineCount+"/"+list.size());
        }

        return new BaseReturn("操作成功！",userFriendDetailVO);
    }

    /**
     * 删除好友
     * @param friendPO
     *          --userId 用户ID
     *          --friendId 好友用户ID
     * @return
     */
    @ApiOperation(value = "删除好友",notes = "请求参数说明 [userId 用户ID] [friendId 好友用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [maxFriendNumInfo 在线数]")
    })
    @PostMapping("/deleteMyFriend")
    public BaseReturn deleteMyFriend(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(friendPO.getFriendId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"好友ID为空！");

        friendService.deleteMyFriend(friendPO.getUserId(),friendPO.getFriendId());

        List<FriendDO> list=friendService.findFriendList(friendPO.getUserId());
        //UserRole role=userService.getUserRoleByUserId(friendPO.getUserId());
        int onlineCount=0;
        for(FriendDO friendDO:list){
            if(friendDO.getStatus()==FriendConstant.STATUS_ONLINE)
                onlineCount++;
        }
        Map<String,Object> result=new HashMap<>();
        String maxFriendNumInfo=onlineCount+"/"+list.size();
        result.put("maxFriendNumInfo",maxFriendNumInfo);
        return new BaseReturn("操作成功！",result);
    }

    /**
     * 好友列表
     * @param friendPO
     *          --userId 用户ID
     *          --pageNumber 当前页码
     *          --pageSize  每页条数
     * @return
     */
    @ApiOperation(value = "好友列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [name 用户名字] [portraitUrl 头像url][sex 性别][npc 是否为NPC true:是,false:不是" +
                    "[status  在线状态  1 在线   0 离线] [intimacy 亲密度] [roleId 角色ID] [roleLevel 角色等级][isLovers 是否为情侣 true是  false不是][maxFriendNumInfo 在线]")
    })
    @PostMapping("/queryFriendByPage")
    public BaseReturn queryFriendByPage(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<FriendDO> list=friendService.findFriendList(friendPO.getUserId());
        UserRole role=userService.getUserRoleByUserId(friendPO.getUserId());
        int onlineCount=0;
        for(FriendDO friendDO:list){
            if(friendDO.getStatus()==FriendConstant.STATUS_ONLINE)
                onlineCount++;
            if(StringUtils.isNotBlank(friendDO.getSpouse()) && role!=null && friendDO.getUserId().equals(role.getSpouse())){
                friendDO.setIsLovers(true);
                friendDO.setSpouse(null);
            }else{
                friendDO.setIsLovers(false);
                friendDO.setSpouse(null);
            }
            if(RoleConstant.NPC_IDS.contains(","+friendDO.getUserId()+",")){
                friendDO.setRoleLevel(friendDO.getRoleLevel()+role.getRoleLevel());
                friendDO.setNpc(true);
            }else{
                friendDO.setNpc(false);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("friendList",list);

        map.put("maxFriendNumInfo", onlineCount+"/"+list.size());
        return new BaseReturn("查询成功！",map);
    }


    /**
     * 聊天好友列表
     * @param friendPO
     *          --userId 用户ID
     *          --pageNumber 当前页码
     *          --pageSize  每页条数
     * @return
     */
    @ApiOperation(value = "聊天好友列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[friendList 好友列表] [userId 用户ID] [name 用户名字] [portraitUrl 头像url] [friendOnLineCount 在线用户数][friendTotalCount 好友总数]" +
                    "[status  在线状态  1在线 0离线][showRedTip 是否显示红点 true:显示 false:不显示] [firstFriendMsg 第一个人的消息]")
    })
    @PostMapping("/queryMyFriendList")
    public BaseReturn queryMyFriendList(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<FriendDO> list=friendService.findFriendList(friendPO.getUserId());
        List<FriendDO> friendList=new ArrayList<>();
        int onlineCount=0;
        for(FriendDO friendDO:list){
            if(!RoleConstant.NPC_IDS.contains(","+friendDO.getUserId()+",")){
                if(friendDO.getStatus()==FriendConstant.STATUS_ONLINE)
                    onlineCount++;
                int count=chatHistoryMessageService.countUnReadMessage(friendPO.getUserId(),friendDO.getUserId());
                ObjectUtil.setObjectFieldsEmpty(friendDO,"name","portraitUrl","status","userId");
                if(count>0){
                    friendDO.setShowRedTip(true);
                }else {
                    friendDO.setShowRedTip(false);
                }
                friendList.add(friendDO);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("friendList",friendList);
        if(list!=null && !list.isEmpty()){
            List<ChatHistoryMessage> messageList=chatHistoryMessageService.findTop50ByUserId(friendPO.getUserId(),list.get(0).getUserId());
            List<Object> voList=new ArrayList<>();
            for(ChatHistoryMessage message:messageList){
                JSONObject object=JSON.parseObject(message.getContent());
                if(message.getSender().equals(friendPO.getUserId())){
                    object.put("receiveUserId",message.getSender());
                }
                voList.add(object);
            }
            map.put("firstFriendMsg",voList);
        }else{
            map.put("firstFriendMsg",new ArrayList<>());
        }
        map.put("friendOnLineCount", onlineCount);
        map.put("friendTotalCount",friendList.size());
        return new BaseReturn("查询成功！",map);
    }

    /**
     * 好友历史消息列表
     * @param friendPO
     * @return
     */
    @ApiOperation(value = "好友历史消息列表",notes = "请求参数说明 [userId 用户ID][friendId 好友用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 ")
    })
    @PostMapping("/myFriendHistoryMessageList")
    public BaseReturn myFriendHistoryMessageList(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isBlank(friendPO.getFriendId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"好友用户ID为空！");

        List<Object> voList=new ArrayList<>();
        List<ChatHistoryMessage> messageList=chatHistoryMessageService.findTop50ByUserId(friendPO.getUserId(),friendPO.getFriendId());
        for(ChatHistoryMessage message:messageList){
            JSONObject object=JSON.parseObject(message.getContent());
            if(message.getSender().equals(friendPO.getUserId())){
                object.put("receiveUserId",message.getSender());
            }
            voList.add(object);
        }
        return new BaseReturn("查询成功！",voList);
    }



    /**
     * 好友推荐列表
     * @param friendPO
     * @return
     */
    @ApiOperation(value = "好友推荐列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [name 用户名字] [portraitUrl 头像url]" +
                    "[roleLevel 角色等级] [roleId 角色ID] [sex 性别 1:男 2：女]")
    })
    @PostMapping("/recommendFriendList")
    public BaseReturn recommendFriendList(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<User> userList=userService.selectRecommendFriends(friendPO.getUserId());
        return new BaseReturn("查询成功！",userList);
    }

    /**
     * 好友推荐
     * @param friendPO
     * @return
     */
    @ApiOperation(value = "好友推荐",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [name 用户名字] [portraitUrl 头像url]" +
                    "[roleLevel 角色等级] [roleId 角色ID] [sex 性别 1:男 2：女]")
    })
    @PostMapping("/recommendFriendByOppositeSex")
    public BaseReturn recommendFriendByOppositeSex(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        Object data=redisService.get("recommendFriendByOppositeSex_"+friendPO.getUserId());
        if(data==null){
            User user=userService.recommendFriendByOppositeSex(friendPO.getUserId());
            if(user!=null){
                Friend friend=new Friend();
                friend.setUserId(friendPO.getUserId());
                friend.setFriendsId(user.getUserId());
                friend.setStatus(1);
                friend.setCreateDate(new Date());
                friend.setUpdateDate(new Date());
                friend.setIntimacy(0);
                friend.setType(2);
                friendService.addFriend(friend);
            }
            List<FriendDO> list=friendService.findFriendList(friendPO.getUserId());
            if(list.size()>=20) user=null;
            redisService.set("recommendFriendByOppositeSex_"+friendPO.getUserId(),1, DateComputeUtil.getSecondsNextEarlyMorning());
            return new BaseReturn("查询成功！",user);
        }
        return new BaseReturn("查询成功！",null);
    }


    /**
     * 好友申请列表
     * @param friendPO
     *          --userId 用户ID
     *          --pageNumber 当前页码
     *          --pageSize  每页条数
     * @return
     */
    @ApiOperation(value = "好友申请列表",notes = "请求参数说明 [userId 用户ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [name 用户名字] [portraitUrl 头像url] " +
                    "[roleLevel 角色等级] [roleId 角色ID] [sex 性别 1:男 2：女]")
    })
    @PostMapping("/queryApplyFriendList")
    public BaseReturn queryApplyFriendList(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);

        List<FriendDO> list= friendService.findToBeConfirmFriend(friendPO.getUserId());
        return new BaseReturn("查询成功!",list);
    }


    /**
     * 宅友列表
     * @param friendPO
     *          --userId 用户ID
     *          --pageNumber 当前页码
     *          --pageSize  每页条数
     * @return
     */
    @ApiOperation(value = "宅友列表",notes = "请求参数说明 [userId 用户ID][otherUserId 他人用户ID(当查看他的宅友时传入)]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [userId 用户ID] [userName 用户名字] [portraitUrl 头像url]" +
                    " [status 0：空闲 1:工作中] [onlineState  上线状态] [sellingPrice 身价] [workerCount 宅友数量] " +
                    "[isGameFriend 是否为游戏好友  true：是  false：不是][protectTimeDetail 保护时间  无返回：显示保护宅友按钮  有返回:显示]" +
                    "[canReduceSellingPrice 能否操作降身价 true:能  false：不能] ")
    })

    @PostMapping("/workerList")
    public BaseReturn workerList(@RequestBody FriendPO friendPO){
        if(StringUtils.isBlank(friendPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,userIdIsNull);
        if(StringUtils.isNotBlank(friendPO.getOtherUserId())){
            List<FriendDetailVO> list=treeHouseRoomService.findFriendWorkersListWithSelf(friendPO.getUserId(),friendPO.getOtherUserId());
            Map<String, Object> map = new HashMap<>();
            map.put("pageList",list);
            return new BaseReturn("查询成功！",map);
        }
        List<FriendDetailVO> list=treeHouseRoomService.findWorkersList(friendPO.getUserId());
        int count=treeHouseRoomService.countRoomsByUserId(friendPO.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("pageList",list);
        //宅友上限
        map.put("workerCount", list.size()+"/"+count);
        return new BaseReturn("查询成功！",map);
    }



}
