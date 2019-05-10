package com.tongzhu.servicemessage.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.*;
import com.tongzhu.servicemessage.action.ActionMap;
import com.tongzhu.servicemessage.action.NettyController;
import com.tongzhu.servicemessage.attribute.Attributes;
import com.tongzhu.servicemessage.constant.*;
import com.tongzhu.servicemessage.constant.MessageConstant;
import com.tongzhu.servicemessage.domain.MarryLogVo;
import com.tongzhu.servicemessage.domain.User;
import com.tongzhu.servicemessage.domain.UserGoods;
import com.tongzhu.servicemessage.message.*;
import com.tongzhu.servicemessage.message.response.RedTipResponsePacket;
import com.tongzhu.servicemessage.redis.RedisService;
import com.tongzhu.servicemessage.service.*;
import com.tongzhu.servicemessage.session.Session;
import com.tongzhu.servicemessage.util.GlobalUserUtil;
import com.tongzhu.servicemessage.util.ItemGoodsUtil;
import com.tongzhu.util.UUIDUtil;
import feign.FeignException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;
import java.util.*;

@NettyController()
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private ChatHistoryMessageService chatHistoryMessageService;

    @Autowired
    private MarryService marryService;

    @Autowired
    private TaskMessageService taskMessageService;

    @Autowired
    private FriendService friendService;


    /**
     * 登陆
     *
     * @param ctx
     * @param userId
     */
    @ActionMap(key = 1)
    public void login(ChannelHandlerContext ctx, String userId) {
        userService.updateForUserOnlineState(userId, 1);
        int count = chatHistoryMessageService.countUnReadMessage(userId);
        int taskCount = taskMessageService.accomplishTaskCount(userId);
        int mail = userService.userMailMessage(userId);
        int messageRow = userService.userMessageRow(userId);
        int card = userService.userMessageRowAndType(userId, com.tongzhu.constant.MessageConstant.TYPE_MARRY_JOIN);
        int invite = userService.userMessageRowAndType(userId, com.tongzhu.constant.MessageConstant.TYPE_TREE_HOUSE_INVITE);
        User user=userService.findByUserId(userId);

        Session session=ctx.channel().attr(Attributes.SESSION).get();
        session.setUserName(user.getName());
        session.setRoleLevel(user.getGrade());
        ctx.channel().attr(Attributes.SESSION).set(session);

        Map<String,Object> data=new HashMap<>();
        data.put("type",MessageConstant.LOGIN);
        data.put("code",0);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(data)));
        //        int taskBranchCount = taskMessageService.accomplishTaskBranchCount(userId);
        List<String> list = new ArrayList<>();
        if (count > 0) {
            String model = MessageConstant.CHAT + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
        if (taskCount > 0) {
            String model = MessageConstant.TASK + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }

        if (messageRow > 0 || mail > 0) {
            String model = MessageConstant.MAIL + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
        if (invite > 0) {
            String model = MessageConstant.INVITE + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
        if (card > 0) {
            String model = MessageConstant.CARD + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
//        if (taskBranchCount > 0) {
//            String model = MessageConstant.TASK_BRANCH + ":1";//是否显示红点 :1显示   :0不显示

//            list.add(model);
//        }
        int friendCount = friendService.countFriendToBeConfirmByFriendId(userId);
        if (friendCount > 0) {
            String model = MessageConstant.ADDFRIEND + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
        boolean isSkillCanUpgrade = userService.checkSkillCanUpgrade(userId);
        if (isSkillCanUpgrade) {
            String model = MessageConstant.UPGRADE_SKILL + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
        }
        if (list.size() > 0) {
            RedTipResponsePacket message = new RedTipResponsePacket();
            message.setType(MessageConstant.RED_TIP);
            message.setModels(list);
            message.setCode(ResultCodeConstant.SUCCESS);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }


    }

    /**
     * 退出
     *
     * @param ctx
     */
    @ActionMap(key = 2)
    public void logout(ChannelHandlerContext ctx) {
        String userId=ctx.channel().attr(Attributes.SESSION).get().getUserId();
        ctx.channel().attr(Attributes.SESSION).set(null);
        Object object = redisService.get(RedisKey.IN_MARRY_HOUSE + userId);
        if (object != null) {
            String marryId = (String) object;
            marryService.leaveWedding(userId, marryId);
            redisService.remove(RedisKey.IN_MARRY_HOUSE + userId);
        }
        Set<Object> members = redisService.setMembers(RedisKey.WEDDING_ENGAGEMENT);
        if (members != null && !members.isEmpty()) {
            members.forEach(o -> {
                String otherUserId = (String) o;
                if (userId.equals(otherUserId)) {
                    marryService.leaveWedding(userId, "666666");
                }
            });
        }
        //婚房掉线检测
        if (redisService.get(RedisKey.IN_WEDDING_ROOM_ID + userId) != null) {
            String loveTreeId = (String) redisService.get(RedisKey.IN_WEDDING_ROOM_ID + userId);
            Set<String> friendkeys = redisService.likePattern(RedisKey.IN_WEDDING_ROOM + loveTreeId + "*");
            for (String uid : friendkeys) {
                if (!uid.equals(userId)) {
                    JSONObject json = new JSONObject();
                    json.put("type", "leaveWeddingRoom");
                    json.put("code", 0);
                    json.put("userId", userId);
                    Channel channel = GlobalUserUtil.serverChannels.get(userId);
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(json.toString()));
                    }
                }
            }
        }
        userService.updateForUserOnlineState(userId, 0);
        if (StringUtils.isNotBlank(userId)) {
            String treeHouseUserId = (String) redisService.get(userId + RedisKey.IN_TREE_HOUSE);
            if (StringUtils.isNotBlank(treeHouseUserId))
                this.outTreeHouse(ctx.channel(), userId, treeHouseUserId);
        }

        Object treeObj = redisService.get(RedisKey.CHAT_TREE_HOUSE_USER + userId);
        if (treeObj != null) {
            String treeHouseUserId = (String) treeObj;
            Map<String, Integer> users = (Map<String, Integer>) redisService.get(RedisKey.IN_TREE_HOUSE + treeHouseUserId);
            Map<String, Integer> result = new HashMap<>();
            for (Map.Entry<String, Integer> entry : users.entrySet()) {
                if (!entry.getKey().equals(userId)) {
                    result.put(entry.getKey(), entry.getValue());

                    Map<String, Object> message = new HashMap<>();
                    message.put("type", "leaveTreeHouse");
                    message.put("code", 0);
                    message.put("userId", userId);

                    Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }
            }
            redisService.set(RedisKey.IN_TREE_HOUSE + treeHouseUserId, result);
        }

        GlobalUserUtil.channels.remove(ctx.channel());
    }

    /**
     * 退出
     *
     * @param ch
     */
    @ActionMap(key = 22)
    public void kickOutClearData(Channel ch) {
        Session session=ch.attr(Attributes.SESSION).get();
        String userId=session.getUserId();
        ch.attr(Attributes.SESSION).set(null);
        Object object = redisService.get(RedisKey.IN_MARRY_HOUSE + userId);
        if (object != null) {
            String marryId = (String) object;
            marryService.leaveWedding(userId, marryId);
            redisService.remove(RedisKey.IN_MARRY_HOUSE + userId);
        }
        Set<Object> members = redisService.setMembers(RedisKey.WEDDING_ENGAGEMENT);
        if (members != null && !members.isEmpty()) {
            members.forEach(o -> {
                String otherUserId = (String) o;
                if (userId.equals(otherUserId)) {
                    marryService.leaveWedding(userId, "666666");
                }
            });
        }
        //婚房掉线检测
        if (redisService.get(RedisKey.IN_WEDDING_ROOM_ID + userId) != null) {
            String loveTreeId = (String) redisService.get(RedisKey.IN_WEDDING_ROOM_ID + userId);
            Set<String> friendkeys = redisService.likePattern(RedisKey.IN_WEDDING_ROOM + loveTreeId + "*");
            for (String uid : friendkeys) {
                if (!uid.equals(userId)) {
                    JSONObject json = new JSONObject();
                    json.put("type", "leaveWeddingRoom");
                    json.put("code", 0);
                    json.put("userId", userId);
                    Channel channel = GlobalUserUtil.serverChannels.get(userId);
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(json.toString()));
                    }
                }
            }
        }
        userService.updateForUserOnlineState(userId, 0);
        if (StringUtils.isNotBlank(userId)) {
            String treeHouseUserId = (String) redisService.get(userId + RedisKey.IN_TREE_HOUSE);
            if (StringUtils.isNotBlank(treeHouseUserId))
                this.outTreeHouse(ch, userId, treeHouseUserId);
        }
        Object treeObj = redisService.get(RedisKey.CHAT_TREE_HOUSE_USER + userId);
        if (treeObj != null) {
            String treeHouseUserId = (String) treeObj;
            Map<String, Integer> users = (Map<String, Integer>) redisService.get(RedisKey.IN_TREE_HOUSE + treeHouseUserId);
            Map<String, Integer> result = new HashMap<>();
            for (Map.Entry<String, Integer> entry : users.entrySet()) {
                if (!entry.getKey().equals(userId)) {
                    result.put(entry.getKey(), entry.getValue());

                    Map<String, Object> message = new HashMap<>();
                    message.put("type", "leaveTreeHouse");
                    message.put("code", 0);
                    message.put("userId", userId);

                    Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }
            }
            redisService.set(RedisKey.IN_TREE_HOUSE + treeHouseUserId, result);
        }
    }

    /**
     * 进入树屋
     *
     * @param ctx
     * @param userId
     * @param treeHouseUserId
     */
    @ActionMap(key = 3)
    public void comInTreeHouse(ChannelHandlerContext ctx, String userId, String treeHouseUserId) {
        try {
            if (redisService.get(TreeHouseVisitorConstant.REDIS_TREE_HOUSE_LET_OUT + treeHouseUserId + "_" + userId) != null) {
                OperateMessage sendMessage = new OperateMessage(MessageConstant.OPERATION, "你已被主人请出树屋，请稍后再进！", 10000);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
                return;
            }
            if (redisService.get(userId + RedisKey.IN_TREE_HOUSE) == null) {
                redisService.set(userId + RedisKey.IN_TREE_HOUSE, treeHouseUserId);
                treeHouseService.comeInTreeHouse(userId, treeHouseUserId);
                OperateMessage sendMessage = new OperateMessage(MessageConstant.OPERATION, "进入树屋成功", 0);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
            } else {
                OperateMessage sendMessage = new OperateMessage(MessageConstant.OPERATION, "不可同时进入多个树屋！", 10000);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
            }
        } catch (FeignException e) {
            JSONObject object = JSON.parseObject(e.getMessage().substring(e.getMessage().indexOf("content") + 9));
            OperateMessage sendMessage = new OperateMessage(MessageConstant.OPERATION, object.getString("message"), 10000);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
        }
    }


    /**
     * 退出树屋
     *
     * @param channel
     * @param userId
     * @param treeHouseUserId
     */
    @ActionMap(key = 4)
    public void outTreeHouse(Channel channel, String userId, String treeHouseUserId) {
        treeHouseService.outTreeHouse(userId, treeHouseUserId);
        redisService.remove(userId + RedisKey.IN_TREE_HOUSE);
        OperateMessage sendMessage = new OperateMessage(MessageConstant.OPERATION, "离开树屋成功", 0);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
    }


    /**
     * 在树屋中发送聊天消息
     *
     * @param ctx
     * @param userId
     * @param message
     */
    @ActionMap(key = 5)
    public void sendMessageToTreeHouseVisitors(ChannelHandlerContext ctx, String userId, String message) {

        String treeHouseUserId = (String) redisService.get(RedisKey.CHAT_TREE_HOUSE_USER + userId);
        Map<String, Integer> users = (Map<String, Integer>) redisService.get(RedisKey.IN_TREE_HOUSE + treeHouseUserId);
        if(redisService.get(RedisKey.All_CAN_NOT_SPEAK_TREE_HOUSE+userId)==null){
            Map<String, Integer> canNotSpeakUsers = (Map<String, Integer>) redisService.get(RedisKey.CAN_NOT_SPEAK_TREE_HOUSE + treeHouseUserId);
            Channel channels = GlobalUserUtil.serverChannels.get(userId);
            String userName=channels.attr(Attributes.SESSION).get().getUserName();
            if(canNotSpeakUsers==null){
                for (Map.Entry<String, Integer> entry : users.entrySet()) {
                    Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
                    ChatSendMessage sendMessage = new ChatSendMessage(MessageConstant.CHAT, MessageConstant.CHAT_IN_TREE_HOUSE,userName , message, 0);
                    sendMessage.setCode(ResultCodeConstant.SUCCESS);
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
                }
            }else{
                if(!canNotSpeakUsers.containsKey(userId)){
                    for (Map.Entry<String, Integer> entry : users.entrySet()) {
                        Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
                        ChatSendMessage sendMessage = new ChatSendMessage(MessageConstant.CHAT, MessageConstant.CHAT_IN_TREE_HOUSE,userName , message, 0);
                        sendMessage.setCode(ResultCodeConstant.SUCCESS);
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
                    }
                }
            }
        }else{
           if(userId.equals(treeHouseUserId)){
               Channel channels = GlobalUserUtil.serverChannels.get(userId);
               String userName=channels.attr(Attributes.SESSION).get().getUserName();
               for (Map.Entry<String, Integer> entry : users.entrySet()) {
                   Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
                   ChatSendMessage sendMessage = new ChatSendMessage(MessageConstant.CHAT, MessageConstant.CHAT_IN_TREE_HOUSE,userName , message, 0);
                   sendMessage.setCode(ResultCodeConstant.SUCCESS);
                   channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
               }
           }
        }
    }


    /**
     * 发送喇叭消息
     *
     * @param ctx
     * @param userId
     * @param msg
     */
    @ActionMap(key = 6)
    public void sendTrumpetMessage(ChannelHandlerContext ctx, String userId, String msg) {
        List<UserGoods> goodsParameters = new ArrayList<>();
        UserGoods goods = new UserGoods();
        goods.setAmount(1);
        goods.setGoodsId(GoodsConstant.TRUMPET_ID);
        goodsParameters.add(goods);
        List<UserGoods> goodsList = userGoodsService.subUserGoods(userId, goodsParameters);
        if (goodsList == null) {
            WorldChatMessage message = new WorldChatMessage();
            message.setCode(ResultCodeConstant.FAIL);
            message.setContent(ItemGoodsUtil.getNameByGoodsId(GoodsConstant.TRUMPET_ID) + "数量不足！");
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        } else {
            User user = userService.findByUserId(userId);
            WorldChatMessage message = new WorldChatMessage();
            message.setHeader(user.getPortraitUrl());
            message.setSenderName(user.getName());
            message.setSex(user.getSex());
            message.setContent(msg);
            message.setType(MessageConstant.CHAT);
            message.setChatWay(MessageConstant.TRUMPET);
            message.setVip(user.getVip());
            message.setCode(ResultCodeConstant.SUCCESS);
            message.setSenderUserId(userId);
            for (Channel channel : GlobalUserUtil.loginUsers) {
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
            }
        }
    }


    /**
     * 发送世界聊天消息
     *
     * @param ctx
     * @param userId
     * @param msg
     */
    @ActionMap(key = 7)
    public void sendWorldChatMessage(ChannelHandlerContext ctx, String userId, String msg) {
        User user = userService.findByUserId(userId);
        WorldChatMessage message = new WorldChatMessage();
        message.setHeader(user.getPortraitUrl());
        message.setSenderName(user.getName());
        message.setSex(user.getSex());
        message.setContent(msg);
        message.setType(MessageConstant.CHAT);
        message.setChatWay(MessageConstant.WORLDCHAT);
        message.setVip(user.getVip());
        message.setCode(ResultCodeConstant.SUCCESS);
        message.setSenderUserId(userId);
        for (Channel channel : GlobalUserUtil.loginUsers) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }

    /**
     * 发送私聊消息
     *
     * @param ctx
     * @param userId
     * @param msg
     * @param receiverId
     */
    @ActionMap(key = 8)
    public void sendSingleChatMessage(Channel ch, String userId, String msg, String receiverId) {
        try {
            String id = UUIDUtil.generateUUID();
            User sender = userService.findByUserId(userId);
            SingleChatSendMessage message = new SingleChatSendMessage();
            message.setType(MessageConstant.CHAT);
            message.setChatWay(MessageConstant.SINGLECHAT);
            message.setContent(msg);
            message.setVip(sender.getVip());
            message.setHeader(sender.getPortraitUrl());
            message.setSex(sender.getSex());
            message.setSenderName(sender.getName());
            message.setCode(ResultCodeConstant.SUCCESS);
            message.setId(id);
            message.setSenderUserId(userId);
            Channel channel = GlobalUserUtil.serverChannels.get(receiverId);
            if (channel != null) {
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));

                List<String> list = new ArrayList<>();
                String model = MessageConstant.CHAT + ":1";//是否显示红点 :1显示   :0不显示
                list.add(model);
                RedTipResponsePacket redTip = new RedTipResponsePacket();
                redTip.setType(MessageConstant.RED_TIP);
                redTip.setModels(list);
                redTip.setCode(ResultCodeConstant.SUCCESS);
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(redTip)));
            }
            chatHistoryMessageService.addHistoryMessage(id, userId, receiverId, URLEncoder.encode(JSON.toJSONString(message), "UTF-8"));
            message.setReceiveUserId(receiverId);
            ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ActionMap(key = 9)
    public void chatRead(ChannelHandlerContext ctx, String msg) {
        chatHistoryMessageService.readMessage(msg);
    }

    @ActionMap(key = 10)
    public void sendChatRedTip(ChannelHandlerContext ctx, String userId) {
        int count = chatHistoryMessageService.countUnReadMessage(userId);
        List<String> list = new ArrayList<>();
        RedTipResponsePacket message = new RedTipResponsePacket();
        if (count > 0) {
            String model = MessageConstant.CHAT + ":1";//是否显示红点 :1显示   :0不显示
            list.add(model);
            message.setModels(list);
        } else {
            String model = MessageConstant.CHAT + ":0";//是否显示红点 :1显示   :0不显示
            list.add(model);
            message.setModels(list);
        }
        message.setType(MessageConstant.RED_TIP);
        message.setCode(ResultCodeConstant.SUCCESS);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
    }

    @ActionMap(key = 11)
    public void sendWeddingMsg(ChannelHandlerContext ctx, String userId, String msg) {
        if (redisService.get(RedisKey.IN_MARRY_HOUSE + userId) != null) {
            String marryId = (String) redisService.get(RedisKey.IN_MARRY_HOUSE + userId);
            if (StringUtils.isNotBlank(marryId) && redisService.get(RedisKey.IN_MARRY_HOUSE_ID + marryId) != null) {
                Set<String> friendkeys = redisService.likePattern("MARRYFRIEND" + marryId + "*");
                Set<String> keys = redisService.likePattern("MARRY" + marryId + "*");
                SingleChatSendMessage message = new SingleChatSendMessage();
                message.setType(MessageConstant.CHAT);
                message.setChatWay(MessageConstant.WEDDINGCHAT);
                message.setContent(msg);
                message.setCode(ResultCodeConstant.SUCCESS);
                message.setSenderUserId(userId);
                for (String uid : friendkeys) {
                    Channel channel = GlobalUserUtil.serverChannels.get(uid.replace("MARRYFRIEND" + marryId, ""));
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }
                for (String uid : keys) {
                    Channel channel = GlobalUserUtil.serverChannels.get(uid.replace("MARRY" + marryId, ""));
                    if (channel != null) {
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }
                MarryLogVo marryLogVo = marryService.getWeddingInfo(marryId);
                if (marryLogVo != null) {
                    Channel channel1 = GlobalUserUtil.serverChannels.get(marryLogVo.getUserId());
                    if (channel1 != null) {
                        channel1.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                    Channel channel2 = GlobalUserUtil.serverChannels.get(marryLogVo.getOtherId());
                    if (channel2 != null) {
                        channel2.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    }
                }

            }
        }
    }

    @ActionMap(key = 12)
    public void sendWeddingRoomMsg(ChannelHandlerContext ctx, String userId, String msg, String receiver) {
        SingleChatSendMessage message = new SingleChatSendMessage();
        message.setType(MessageConstant.CHAT);
        message.setChatWay(MessageConstant.WEDDINGROOMCHAT);
        message.setContent(msg);
        message.setCode(ResultCodeConstant.SUCCESS);
        message.setSenderUserId(userId);
        Channel channel = GlobalUserUtil.serverChannels.get(receiver);
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }

    @ActionMap(key = 13)
    public void noSpeakInTreeHouse(ChannelHandlerContext ctx, String treeHouseUserId, String userId) {
        Map<String, Long> users = (Map<String, Long>) redisService.get("_come_in_tree_house_" + treeHouseUserId);
        for (Map.Entry<String, Long> entry : users.entrySet()) {
            if (entry.getKey().equals(userId)) {
                entry.setValue(0l);
            }
        }
        redisService.set(RedisKey.IN_TREE_HOUSE + treeHouseUserId, users);
    }

}
