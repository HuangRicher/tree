package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.constant.MessageConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.constant.NewPlayerGuideConstant;
import com.tongzhu.user.constant.StatusConstant;
import com.tongzhu.user.controller.vo.UserVO;
import com.tongzhu.user.domain.Friend;
import com.tongzhu.user.domain.TreeHouse;
import com.tongzhu.user.domain.TreeHouseRoom;
import com.tongzhu.user.model.AccountUser;
import com.tongzhu.user.model.NewPlayerGuide;
import com.tongzhu.user.model.User;
import com.tongzhu.user.model.UserMessage;
import com.tongzhu.user.po.GenerateUserPO;
import com.tongzhu.user.po.UserPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserDetailVO;
import com.tongzhu.user.service.vo.UserMessageVO;
import com.tongzhu.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.stream.Stream;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户信息controller", tags = {"用户信息相关API"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private NewPlayerGuideService newPlayerGuideService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserMainPersonService userMainPersonService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserGoodsService userGoodsService;


    /**
     * 玩家信息
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "玩家信息", notes = "玩家信息请求参数：[userId 用户Id] [otherUserId 玩家用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户ID] [userName 用户名称] [portraitUrl 头像] " +
                    "[status 0：自由身 1：工作中 ] [sellingPrice 身价][workStatus 0：空闲中，1：工作中] " +
                    "[workerCount 宅友数量] [masterName 主人名称] [isGameFriend 是否为游戏好友]")
    })
    @PostMapping("/getPersonalInformation")
    public BaseReturn getPersonalInformation(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        UserDetailVO userDetail = userService.getUserDetail(userPO.getOtherUserId());
        if (userDetail != null) {
            int workerCount = treeHouseRoomService.countWorkersByUserId(userPO.getOtherUserId());
            int rooms = treeHouseRoomService.countRoomsByUserId(userPO.getOtherUserId());
            userDetail.setWorkerCount(workerCount + "/" + rooms);
            TreeHouseRoom room = treeHouseRoomService.checkIsWorkerByWorkerId(userPO.getOtherUserId());
            if (room == null) {
                userDetail.setStatus(StatusConstant.USER_FREE);
            } else {
                User user = userService.findByUserId(room.getUserId());
                userDetail.setWorkStatus(room.getStatus());
                userDetail.setMasterName(user.getName());
            }
            Friend friend = friendService.checkIsMyFriend(userPO.getUserId(), userPO.getOtherUserId());
            if (friend != null) {
                userDetail.setIsGameFriend(true);
            } else {
                userDetail.setIsGameFriend(false);
            }
            if (userPO.getUserId().equals(userPO.getOtherUserId())) userDetail.setIsGameFriend(true);

            userDetail.setAutograph(null);
            return new BaseReturn("查询成功！", userDetail);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在!");
        }
    }

    /**
     * 个人主页
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "个人主页", notes = "个人主页请求参数：[userId 用户Id][pageNum 页号] [pageSize 每页大小][otherUserId 要查看的用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户ID] [userName 用户名称] [portraitUrl 头像] " +
                    "[autograph 个性签名][canSendMessage 是否能留言(好友才可留言)] [currentPage 当前页][pageSize 每页大小]" +
                    "[pageTotal 总页数][recordTotal 总条数][id 留言ID][messageBody 留言][senderName 留言人名称][senderHeader 留言人头像url] [sellingPrice 身价]")
    })
    @PostMapping("/getPersonalMessage")
    public BaseReturn getPersonalHome(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户Id为空!");
        if (StringUtils.isBlank(userPO.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "要查看的用户ID为空!");
        if (userPO.getPageNum() <= 0 || userPO.getPageSize() <= 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "分页参数要大于0!");

        UserDetailVO userDetail = userService.getUserDetail(userPO.getOtherUserId());
        Pager<UserMessage> messagePage = userMessageService.findReceivedMessageByPage(userPO.getOtherUserId(), userPO.getPageNum(), userPO.getPageSize());
        List<UserMessageVO> list = new ArrayList<>();
        for (UserMessage msg : messagePage.getContent()) {
            UserMessageVO messageVO = new UserMessageVO();
            messageVO.setId(msg.getId());
            messageVO.setMessageBody(msg.getMessageBody());
            User user = userService.findByUserId(msg.getSenderId());
            if (user != null) {
                messageVO.setSenderName(user.getName());
                messageVO.setSenderHeader(user.getPortraitUrl());
            }
            list.add(messageVO);
        }

        Pager<UserMessageVO> msgPage = new Pager(messagePage.getCurrentPage(), messagePage.getPageSize(),
                messagePage.getPageTotal(), messagePage.getRecordTotal(), list);
        Friend friend = friendService.checkIsMyFriend(userPO.getUserId(), userPO.getOtherUserId());
        if (userDetail == null) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "用户不存在!");
        }
        if (friend != null || userPO.getUserId().equals(userPO.getReceiverId())) {
            userDetail.setCanSendMessage(true);
        } else {
            userDetail.setCanSendMessage(false);
        }
        userDetail.setData(msgPage);
        return new BaseReturn("查询成功！", userDetail);
    }


    /**
     * 发送留言
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "发送留言", notes = "发送留言请求参数：[userId 发送者用户Id][receiverId 接收留言用户ID][message 留言]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/sendMessage")
    public BaseReturn sendMessage(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "发送者用户ID为空!");
        if (StringUtils.isBlank(userPO.getReceiverId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "接收人用户ID为空!");
        if (StringUtils.isBlank(userPO.getMessage()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "留言不能为空！");
        Friend friend = friendService.checkIsMyFriend(userPO.getUserId(), userPO.getReceiverId());
        if (friend == null && !userPO.getUserId().equals(userPO.getReceiverId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "非好友不能留言!");
        if (userPO.getMessage().length() > 20) return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "留言字符不能超过20个!");

        UserMessage message = new UserMessage();
        message.setCreateDate(new Date());
        message.setId(UUIDUtil.generateUUID());
        message.setMessageBody(userPO.getMessage());
        message.setReceiverId(userPO.getReceiverId());
        message.setSenderId(userPO.getUserId());
        message.setStatus(1);
        userMessageService.add(message);
        return new BaseReturn("留言发送成功！");
    }

    /**
     * 删除留言
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "删除留言", notes = "删除留言请求参数：[userId 用户Id][messageId 留言ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/deleteMessage")
    public BaseReturn deleteMessage(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (StringUtils.isBlank(userPO.getMessageId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "留言ID为空!");
        userMessageService.delete(userPO.getMessageId());
        return new BaseReturn("留言删除成功！");
    }

    /**
     * 前往家园
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "前往家园", notes = "前往家园请求参数：[userId 用户Id][otherUserId 家园用户ID]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明 [treeHouseLevel 树屋等级]")
    })
    @PostMapping("/toOtherUserHome")
    public BaseReturn toOtherUserHome(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (StringUtils.isBlank(userPO.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "家园用户ID为空!");

        TreeHouse treeHouse = treeHouseService.findByUserId(userPO.getOtherUserId());
        UserVO user = new UserVO();
        user.setTreeHouseLevel(treeHouse != null ? treeHouse.getLevel() : 1);
        return new BaseReturn("查找成功！", user);
    }


    /**
     * 记录新手引导完成的任务
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "记录新手引导完成的任务", notes = "记录新手引导完成的任务请求参数：[userId 用户Id][taskNum 任务编号(从1开始)]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[loverName 初恋名字]")
    })
    @PostMapping("/doNewPlayerGuideTask")
    public BaseReturn doNewPlayerGuideTask(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (userPO.getTaskNum() < 1 || userPO.getTaskNum() > NewPlayerGuideConstant.TASK_END)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务编号不正确!");

        NewPlayerGuide guide = newPlayerGuideService.getByUserId(userPO.getUserId());
        if (guide != null && guide.getTaskId() >= NewPlayerGuideConstant.TASK_END)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "异常操作！");

        newPlayerGuideService.doTask(userPO.getUserId(), userPO.getTaskNum());
        Map<String, Object> map = new HashMap<>();
        if (NewPlayerGuideConstant.TASK_ID_5.equals(userPO.getTaskNum())) {
            map.put("loverName", redisService.get(userPO.getUserId() + "_loverName_"));
        }
        if (NewPlayerGuideConstant.TASK_ADD_YY_LOVER_NAME.equals(userPO.getTaskNum())) {
            userGoodsService.addUserGoodsSingle(userPO.getUserId(), "28207", 1);
        }
        if(NewPlayerGuideConstant.TASK_ID_5.equals(userPO.getTaskNum())){
            List<String> weaponList=(List<String>)redisService.get(RedisKey.NEW_PLAYER_WEAPON_LIST+userPO.getUserId());
            map.put("weaponList",weaponList);
            redisService.remove(RedisKey.NEW_PLAYER_WEAPON_LIST+userPO.getUserId());
        }
        return new BaseReturn("引导成功！", map);
    }

    /**
     * 新手引导输入其他玩家名字
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "新手引导输入其他玩家名字", notes = "参数：[userId 用户Id][taskNum 任务编号(从1开始)][loverName 初恋名字]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明")
    })
    @PostMapping("/doNewPlayerAddPersonName")
    public BaseReturn doNewPlayerAddPersonName(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (userPO.getTaskNum() < 1 || userPO.getTaskNum() > NewPlayerGuideConstant.TASK_END)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "任务编号不正确!");

        NewPlayerGuide guide = newPlayerGuideService.getByUserId(userPO.getUserId());
        if (guide != null && guide.getTaskId() >= NewPlayerGuideConstant.TASK_END)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "异常操作！");

        if (NewPlayerGuideConstant.TASK_ADD_YY_LOVER_NAME.equals(userPO.getTaskNum()) && userPO.getLoverName() != null) {
            userMainPersonService.add(userPO.getUserId(), userPO.getLoverName());
        }
        if (NewPlayerGuideConstant.TASK_LOVER_NAME.equals(userPO.getTaskNum()) && userPO.getLoverName() != null) {
            redisService.set(userPO.getUserId() + "_loverName_", userPO.getLoverName());
        }
        return new BaseReturn("success！");
    }


    /**
     * 查询用户留言
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "查询用户留言", notes = "删除留言请求参数：[userId 用户Id][pageNum 页号] [pageSize 每页大小]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 主键id] [receiverId 接收者用户ID] [senderId 发送者id] [messageBody 消息内容]" +
                    "[createDate 发送时间] [title 标题] [type 消息类型 0-宅友被买走 1-自由身被别人买走 2-有主人被买走 11-树屋邀请游客] [senderName 发送者名字]  ")
    })
    @PostMapping("/allMessage")
    public BaseReturn allMessage(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (userPO.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (userPO.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");

        Pager<UserMessage> receivedMessageByPage = userMessageService.findReceivedMessageByPage(userPO.getUserId(), userPO.getPageNum(), userPO.getPageSize());
        userMessageService.updateMessageRead(userPO.getUserId(), null);
        return new BaseReturn("查询成功！", receivedMessageByPage);
    }

    /**
     * 查询用户留言
     *
     * @param userPO
     * @return
     */
    @ApiOperation(value = "查询用户留言", notes = "请求参数：[userId 用户Id]" +
            "[type 消息类型 0-宅友被买走 1-自由身被别人买走 2-有主人被买走 3：砸金库 9:取消婚礼 8:邀请参加婚礼 11-树屋邀请游客")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[id 主键id] [receiverId 接收者用户ID] [senderId 发送者id] [messageBody 消息内容]" +
                    "[createDate 发送时间] [title 标题] [type 消息类型 0-宅友被买走 1-自由身被别人买走 2-有主人被买走 11-树屋邀请游客] [senderName 发送者名字]  ")
    })
    @PostMapping("/queryMessageByType")
    public BaseReturn queryMessageByType(@RequestBody UserPO userPO) {
        if (StringUtils.isBlank(userPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空!");
        if (userPO.getType() == null || userPO.getType() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "消息类型为空！");
        List<com.tongzhu.user.controller.vo.UserMessageVO> receivedMessageByPage = userMessageService.findMessageByType(userPO.getUserId(), userPO.getType());
        userMessageService.updateMessageRead(userPO.getUserId(), userPO.getType());
        return new BaseReturn("查询成功！", receivedMessageByPage);
    }

    @PostMapping("/generateUserData")
    public BaseReturn generateUserData(@RequestBody GenerateUserPO generateUserPO) {
        AccountUser account = accountService.getByAccountAndRoleId(generateUserPO.getAccount(), generateUserPO.getRoleId());
        if (account != null)
            return new BaseReturn("该角色已创建！");
        userService.generateNewUser(generateUserPO);
        return new BaseReturn("查询成功！", generateUserPO);
    }

    @PostMapping("/testThreadTotal0")
    public BaseReturn testThreadTotal0(@RequestBody GenerateUserPO generateUserPO) {
        return new BaseReturn("查询成功！");
    }

    @PostMapping("/testThreadTotal100")
    public BaseReturn testThreadTotal100(@RequestBody GenerateUserPO generateUserPO) throws InterruptedException {
        Thread.sleep(100L);
        return new BaseReturn("查询成功！", generateUserPO);
    }

    @PostMapping("/testThreadTotal200")
    public BaseReturn testThreadTotal200(@RequestBody GenerateUserPO generateUserPO) throws InterruptedException {
        Thread.sleep(200L);
        return new BaseReturn("查询成功！", generateUserPO);
    }

    @PostMapping("/testThreadTotal300")
    public BaseReturn testThreadTotal300(@RequestBody GenerateUserPO generateUserPO) throws InterruptedException {
        Thread.sleep(300L);
        return new BaseReturn("查询成功！", generateUserPO);
    }

    @PostMapping("/testThreadTotal500")
    public BaseReturn testThreadTotal500(@RequestBody GenerateUserPO generateUserPO) throws InterruptedException {
        Thread.sleep(500L);
        return new BaseReturn("查询成功！");
    }
}
