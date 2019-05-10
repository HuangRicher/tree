package com.tongzhu.treehouse.microservice;

import com.tongzhu.treehouse.domain.FriendDetail;
import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.service.TreeHouseRoomService;
import com.tongzhu.treehouse.service.vo.UserFriendDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("treeHouseRoomResource")
public class TreeHouseRoomResource {

    @Autowired
    private TreeHouseRoomService treeHouseRoomService;

    @PostMapping("/countWorkersByUserId/{userId}")
    public int countWorkersByUserId(@PathVariable("userId") String userId){
        return treeHouseRoomService.countWorkersByUserId(userId);
    }

    @PostMapping("/countRoomsByUserId/{userId}")
    public int countRoomsByUserId(@PathVariable("userId")String userId){
        return treeHouseRoomService.countRoomsByUserId(userId);
    }

    @PostMapping("/checkIsWorkerByWorkerId/{userId}")
    public TreeHouseRoom checkIsWorkerByWorkerId(@PathVariable("userId")String userId){
        return treeHouseRoomService.checkIsWorkerByWorkerId(userId);
    }

    @PostMapping("/updateIsGameFriend/{userId}/{friendId}/{isGameFriend}")
    public void updateIsGameFriend(@PathVariable("userId")String userId,
                                   @PathVariable("friendId")String friendId,
                                   @PathVariable("isGameFriend")Boolean isGameFriend){
        treeHouseRoomService.updateIsGameFriend(userId,friendId,isGameFriend);
    }

    @PostMapping("/findWorkersByUserIdAndWorkerId/{userId}/{workerId}")
    public TreeHouseRoom findWorkersByUserIdAndWorkerId(@PathVariable("userId") String userId, @PathVariable("workerId") String workerId){
        return treeHouseRoomService.findWorkersByUserIdAndWorkerId(userId,workerId);
    }

    @PostMapping("/updateReducePriceDate/{userId}/{workerId}")
    public void updateReducePriceDate(@RequestParam("userId") String userId, @RequestParam("workerId") String workerId){
        treeHouseRoomService.updateReducePriceDate(userId,workerId);
    }

    @PostMapping("/findWorkersList/{userId}")
    public List<FriendDetail> findWorkersList(@PathVariable("userId") String userId){
        return treeHouseRoomService.findWorkersList(userId);
    }

    @PostMapping("/findFriendWorkersListWithSelf/{userId}/{friendUserId}")
    List<FriendDetail> findFriendWorkersListWithSelf(@PathVariable("userId") String userId,
                                                     @PathVariable("friendUserId") String friendUserId){
        return treeHouseRoomService.findFriendWorkersListWithSelf(userId,friendUserId);
    }

}
