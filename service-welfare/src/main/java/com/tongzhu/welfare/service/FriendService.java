package com.tongzhu.welfare.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.Friend;
import com.tongzhu.welfare.model.vo.FriendDO;
import com.tongzhu.welfare.service.impl.FriendServiceHystrix;

@FeignClient(value = "${feign-service.friend}",configuration = FeignConfig.class,fallback = FriendServiceHystrix.class)
public interface FriendService {

    @RequestMapping(value = "/friendResource/checkIsMyFriend/{userId}/{otherUserId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Friend checkIsMyFriend(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId);
    
    @RequestMapping(value = "/friendResource/updateIntimacy/{userId}/{friendId}/{num}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateIntimacy(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId,@PathVariable("num") int num);
    
    @RequestMapping(value = "/friendResource/getIntimacy/{userId}/{friendId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Integer getIntimacy(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId);
    
    @RequestMapping(value = "/taskResource/taskBranchFinish/{taskId}/{userId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void taskBranchFinish(@PathVariable("taskId") Integer taskId, @PathVariable("userId") String userId);
   
    
    @RequestMapping(value = "/friendResource/findFriendList/{userId}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FriendDO> findFriendList(@PathVariable("userId") String userId);
}
