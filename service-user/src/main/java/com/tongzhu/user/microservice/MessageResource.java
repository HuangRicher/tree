package com.tongzhu.user.microservice;

import com.tongzhu.constant.MessageConstant;
import com.tongzhu.user.model.UserMessage;
import com.tongzhu.user.service.UserMessageService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/messageResource")
public class MessageResource {

    @Autowired
    private UserMessageService userMessageService;


    @PostMapping("/addUserMessage")
    public int addUserMessage(@RequestBody UserMessage userMessage){
        return userMessageService.add(userMessage);
    }

    @PostMapping("/inviteFriendToTreeHouse/{userId}/{receiverIds}")
    public void inviteFriendToTreeHouse(@PathVariable("userId") String userId, @PathVariable("receiverIds") String receiverIds){
        String[] ids=receiverIds.split(",");
        for(int i=0;i<ids.length;i++){
            UserMessage message=new UserMessage();
            message.setId(UUIDUtil.generateUUID());
            message.setMessageBody("邀请你去他的树屋做客！");
            message.setSenderId(userId);
            message.setReceiverId(ids[i]);
            message.setType(MessageConstant.TYPE_TREE_HOUSE_INVITE);
            message.setSurplusDate(DateUtil.getBeforeDate(new Date()));
            userMessageService.add(message);
        }
    }

    @PostMapping("/userMessageRow/{userId}")
    public int userMessageRow(@PathVariable("userId")String userId){
        return userMessageService.userMessageRow(userId);
    }

    @PostMapping("/userMessageRowAndType/{userId}/{type}")
    public int userMessageRowAndType(@PathVariable("userId")String userId,@PathVariable("type") int type){
        return userMessageService.userMessageRowAndType(userId,type);
    }

}
