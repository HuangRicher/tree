package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.TreeHouseRoom;
import com.tongzhu.friend.service.impl.TreeHouseRoomServiceHystrix;
import com.tongzhu.friend.service.vo.FriendDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseRoomServiceHystrix.class )
public interface TreeHouseRoomService {


    @RequestMapping(value = "/treeHouseRoomResource/countWorkersByUserId/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countWorkersByUserId(@PathVariable("userId")String userId);



    @RequestMapping(value = "/treeHouseRoomResource/countRoomsByUserId/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int countRoomsByUserId(@PathVariable("userId")String userId);



    @RequestMapping(value = "/treeHouseRoomResource/checkIsWorkerByWorkerId/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    TreeHouseRoom checkIsWorkerByWorkerId(@PathVariable("userId")String userId);



    @RequestMapping(value = "/treeHouseRoomResource/updateIsGameFriend/{userId}/{friendId}/{isGameFriend}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateIsGameFriend(@PathVariable("userId")String userId,
                            @PathVariable("friendId")String friendId,
                            @PathVariable("isGameFriend")Boolean isGameFriend);



    @RequestMapping(value = "/treeHouseRoomResource/findWorkersList/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FriendDetailVO> findWorkersList(@PathVariable("userId") String userId);


    @RequestMapping(value = "/treeHouseRoomResource/findFriendWorkersListWithSelf/{userId}/{friendId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FriendDetailVO> findFriendWorkersListWithSelf(@PathVariable("userId") String userId,@PathVariable("friendId") String friendId);
}
