package com.tongzhu.servicemessage.microservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.servicemessage.attribute.Attributes;
import com.tongzhu.servicemessage.constant.MessageConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.servicemessage.constant.ResultCodeConstant;
import com.tongzhu.servicemessage.message.ChatSendMessage;
import com.tongzhu.servicemessage.message.response.RedTipResponsePacket;
import com.tongzhu.servicemessage.redis.RedisService;
import com.tongzhu.servicemessage.session.Session;
import com.tongzhu.servicemessage.util.GlobalUserUtil;
import com.tongzhu.util.RandomUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messageResource")
public class MessageResource {

    @Autowired
    private RedisService redisService;

    @PostMapping("/sendBroadcast")
    public void sendBroadcast(@RequestParam("message") String message){
        ChatSendMessage sendMessage=new ChatSendMessage(MessageConstant.BROADCAST,"system",message);
        for (Channel channel : GlobalUserUtil.loginUsers) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendMessage)));
        }
    }


    @PostMapping("/sendMessageToSomeBody/{userId}")
    public void sendMessageToSomeBody(@PathVariable("userId") String userId, @RequestParam("message") String message){
        Channel channel=GlobalUserUtil.serverChannels.get(userId);
        if(channel!=null){
            channel.writeAndFlush(new TextWebSocketFrame(message));
        }
    }

    @PostMapping("/sendSkillRedTipToSomeBody/{userId}")
    public void sendSkillRedTipToSomeBody(@PathVariable("userId") String userId){
        List<String> list = new ArrayList<>();
        String model = MessageConstant.UPGRADE_SKILL + ":1";//是否显示红点 :1显示   :0不显示
        list.add(model);
        RedTipResponsePacket message = new RedTipResponsePacket();
        message.setType(MessageConstant.RED_TIP);
        message.setModels(list);
        message.setCode(ResultCodeConstant.SUCCESS);
        Channel channel=GlobalUserUtil.serverChannels.get(userId);
        if(channel!=null){
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }

    @PostMapping("/sendWeddingCruiseToSomeBody/{weddingType}/{boyName}/{girlName}")
    public void sendWeddingCruiseToSomeBody(@PathVariable("weddingType") Integer weddingType,
                                            @PathVariable("boyName") String boyName,
                                            @PathVariable("girlName") String girlName){
        for (Channel channel : GlobalUserUtil.loginUsers) {
	        	Map<String,Object> object=new HashMap<>();
	            object.put("type",MessageConstant.WEDDINGCRUISE);
	            object.put("code",0);
	            object.put("weddingType",weddingType);
	            object.put("boyName",boyName);
	            object.put("girlName",girlName);
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(object)));
        }
    }

    @PostMapping("/sendBroadcastToAllBody/{message}")
    public void sendMessageToSomeBody(@PathVariable("message") String message){
        for (Channel channel : GlobalUserUtil.loginUsers) {
            Map<String,Object> object=new HashMap<>();
            object.put("type",MessageConstant.BROADCAST);
            object.put("code",0);
            object.put("senderName","系统消息");
            object.put("content",message);
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(object)));
        }
    }
    @PostMapping("/sendMessageToAll")
    public void sendMessageToAll(@RequestParam("message") String message){
        for (Channel channel : GlobalUserUtil.loginUsers) {
            channel.writeAndFlush(new TextWebSocketFrame(message));
        }
    }



    /**
     * 树屋指派工作
     * @param userId
     * @param workerUserId
     * @param workType
     * @param grade
     */
    @PostMapping("/appointWork/{userId}/{message}/{workerUserId}/{workType}/{grade}/{sex}/{exp}/{money}")
    public void appointWork(@PathVariable("userId") String userId,@PathVariable("workerUserId") String workerUserId,
                            @PathVariable("workType") Integer workType,@PathVariable("grade") Integer grade,
                            @PathVariable("sex") Integer sex,@PathVariable("exp") Integer exp,
                            @PathVariable("message") String message,@PathVariable("money") Integer money) {
        Channel channel=GlobalUserUtil.serverChannels.get(workerUserId);
        if(channel!=null) {
            //{"type":"operate","chatWay":"treeHouseWork","content":"ff","receiver":"100132","treeHouseUserId":"111111","workType":1,"grade":1}
            Map<String,Object> object=new HashMap<>();
            object.put("type","operate");
            object.put("operateWay","treeHouseWork");
            object.put("content",message);
            object.put("sex",sex);
            object.put("exp",exp);
            object.put("money",money);
            object.put("receiver",workerUserId);
            object.put("treeHouseUserId",userId);
            object.put("workType",workType);
            object.put("grade",grade);
            object.put("code",0);
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(object)));
        }
    }



    /**
     * 参加婚礼
     * @param userId
     * @param marryId
     */
    @PostMapping("/joinMarry/{userId}/{marryId}")
    public void joinMarry(@PathVariable("userId") String userId,@PathVariable("marryId") String marryId){
        redisService.set(RedisKey.IN_MARRY_HOUSE+userId,marryId);
        if(redisService.get(RedisKey.IN_MARRY_HOUSE_ID+marryId)!=null){
            List<String> userIds=(List<String>)redisService.get(RedisKey.IN_MARRY_HOUSE_ID+marryId);
            userIds.add(userId);
            redisService.set(RedisKey.IN_MARRY_HOUSE_ID+marryId,userIds);
        }else{
            List<String> userIds=new ArrayList<>();
            userIds.add(userId);
            redisService.set(RedisKey.IN_MARRY_HOUSE_ID+marryId,userIds);
        }
    }

    @PostMapping("/sendMessageToAllInTreeHouse/{userId}/{message}")
    public void sendMessageToAllInTreeHouse(@PathVariable("userId") String userId,@PathVariable("message") String message){
        Map<String, Integer> users = (Map<String, Integer>) redisService.get(RedisKey.IN_TREE_HOUSE + userId);
        for (Map.Entry<String, Integer> entry : users.entrySet()) {
            if (!userId.equals(entry.getKey())) {
            Channel channel = GlobalUserUtil.serverChannels.get(entry.getKey());
            channel.writeAndFlush(new TextWebSocketFrame(message));
            }
        }
    }

    @PostMapping("/queryOnLineUser")
    public Map<String,Integer> queryOnLineUser(){
        if(GlobalUserUtil.serverChannels!=null){
            Map<String,Integer> users=new HashMap<>();
            for(Map.Entry<String,Channel> entry:GlobalUserUtil.serverChannels.entrySet()){
                Session session=entry.getValue().attr(Attributes.SESSION).get();
                //玩家在树屋挂机每15分钟获得60~80点人气值
                if(session!=null) users.put(session.getUserId(), RandomUtil.random(60,80));
            }
            return users;
        }
        return null;
    }
}
