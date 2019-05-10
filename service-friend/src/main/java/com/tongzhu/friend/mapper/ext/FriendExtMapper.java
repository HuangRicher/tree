package com.tongzhu.friend.mapper.ext;

import com.tongzhu.friend.mapper.vo.FriendDO;
import com.tongzhu.friend.mapper.vo.FriendVO;
import com.tongzhu.friend.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FriendExtMapper {

    List<FriendVO> selectFriends(@Param("userId") String userId, @Param("status") int status);

    List<FriendVO> selectFriendsOrderByDate(@Param("userId") String userId, @Param("status") int status);

    List<Friend> selectFriendsForBuyByRand(@Param("userId") String userId,
                                           @Param("status") int status,
                                           @Param("protectEndDate") Date protectEndDate,
                                           @Param("exchangeDate") Date exchangeDate,
                                           @Param("exchangeCount") int exchangeCount,
                                           @Param("sellingPrice") int sellingPrice,
                                           @Param("count") int count);

    Friend checkIsMyFriend(@Param("userId") String userId,@Param("searchUser") String searchUser);

    List<FriendDO> selectMyFriendList(@Param("userId") String userId);

    List<FriendDO> selectFriendListToConfirm(@Param("userId") String userId);
}
