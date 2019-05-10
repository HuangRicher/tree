package com.tongzhu.friend.microservice;

import com.tongzhu.friend.model.ChatHistoryMessage;
import com.tongzhu.friend.service.ChatHistoryMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/messageResource")
public class MessageResource {

    @Autowired
    private ChatHistoryMessageService chatHistoryMessageService;

    @PostMapping("/findTop50ByUserId/{userId}/{friendUserId}")
    public List<ChatHistoryMessage> findTop50ByUserId(@PathVariable("userId") String userId, @PathVariable("friendUserId") String friendUserId){
        return chatHistoryMessageService.findTop50ByUserId(userId,friendUserId);
    }

    @PostMapping("/addHistoryMessage/{id}/{userId}/{friendUserId}/{message}")
    public void addHistoryMessage(@PathVariable("id") String id,@PathVariable("userId") String userId,
                                                      @PathVariable("friendUserId") String friendUserId,
                                                      @PathVariable("message") String message) throws UnsupportedEncodingException {
        ChatHistoryMessage message1=new ChatHistoryMessage();
        message1.setContent(URLDecoder.decode(message,"UTF-8"));
        message1.setReceiver(friendUserId);
        message1.setSender(userId);
        message1.setId(id);
        chatHistoryMessageService.add(message1);
    }

    @PostMapping("/countUnReadMessage/{userId}")
    public int countUnReadMessage(@PathVariable("userId")String userId){
        return chatHistoryMessageService.countUnReadMessage(userId);
    }

    @PostMapping("/readMessage/{id}")
    public void readMessage(@PathVariable("id")String id){
         chatHistoryMessageService.readMessage(id);
    }
}
