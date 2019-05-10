package com.tongzhu.friend.microservice;

import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.mapper.vo.FriendDO;
import com.tongzhu.friend.model.Friend;
import com.tongzhu.friend.service.FriendService;
import com.tongzhu.friend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendResource")
public class FriendResource {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;


    @PostMapping("/checkIsMyFriend/{userId}/{otherUserId}")
    public Friend checkIsMyFriend(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId){
        return friendService.checkIsMyFriend(userId,otherUserId);
    }
    
    @PostMapping("/updateIntimacy/{userId}/{otherUserId}/{num}")
    public void updateIntimacy(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId,@PathVariable("num") int num){
         friendService.updateIntimacy(userId, otherUserId, num);
    }
    
    @PostMapping("/getIntimacy/{userId}/{otherUserId}")
    public Integer getIntimacy(@PathVariable("userId") String userId, @PathVariable("otherUserId") String otherUserId){
        return friendService.getIntimacy(userId, otherUserId);
    }

    @PostMapping("/updateFriendFightStatus/{userId}/{friendId}/{status}")
    public void updateFriendFightStatus(@PathVariable("userId") String userId,
                                           @PathVariable("friendId") String friendId,
                                           @PathVariable("status") Integer status){
         friendService.updateFightStatus(userId, friendId,status);
    }

    @PostMapping("/findFriendList/{userId}")
    public List<FriendDO> findFriendList(@PathVariable("userId") String userId){
        List<FriendDO> list=friendService.findFriendList(userId);
        UserRole role=userService.getUserRoleByUserId(userId);
        for(FriendDO friendDO:list){
            if(StringUtils.isNotBlank(friendDO.getSpouse()) && role!=null && friendDO.getSpouse().equals(role.getSpouse())){
                friendDO.setIsLovers(true);
                friendDO.setSpouse(null);
            }else{
                friendDO.setIsLovers(false);
                friendDO.setSpouse(null);
            }
        }
        return list;
    }


    @PostMapping("/countFriendToBeConfirmByFriendId/{userId}")
    public Integer countFriendToBeConfirmByFriendId(@PathVariable("userId") String userId){
        return friendService.countFriendToBeConfirmByFriendId(userId);
    }

    @PostMapping("/addFriend")
    public void addFriend(@RequestBody Friend friend){
        friendService.addFriend(friend);
    }
}
