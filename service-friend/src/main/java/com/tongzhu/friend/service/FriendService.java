package com.tongzhu.friend.service;

import com.tongzhu.common.Pager;
import com.tongzhu.friend.domain.UserDetail;
import com.tongzhu.friend.mapper.vo.FriendDO;
import com.tongzhu.friend.model.Friend;
import com.tongzhu.friend.service.vo.FriendDetailVO;

import java.util.Date;
import java.util.List;

public interface FriendService {


    /**
     * 分页查找好友
     * @param userId
     */
    Pager<FriendDetailVO> findFriendByPage(String userId, int pageNumber, int pageSize);


    /**
     * 查找好友列表
     * @param userId
     * @return
     */
    List<FriendDO> findFriendList(String userId);


    /**
     * 根据userId 查询出要查询好友的信息
     * @param searchUser
     * @return 好友信息
     */
    List<UserDetail> searchUserByUserIdOrName(String searchUser);


    /**
     * 请求添加好友
     * @param userId
     * @return
     */
    int requestAddFriend(String userId, String friendId);


    /**
     * 查找待确认的好友请求数
     * @param friendId
     * @return
     */
    int countFriendToBeConfirmByFriendId(String friendId);


    /**
     * 确认加好友请求（同意、拒绝）
     * @param userId
     * @param friendId
     * @param type 同意：1  拒绝：2
     * @return
     */
    FriendDetailVO confirmAddFriend(String userId, String friendId, int type);


    /**
     * 查找好友申请列表
     * @param userId
     */
    List<FriendDO> findToBeConfirmFriend(String userId);


    /**
     * 分页查询好友申请列表
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Pager<FriendDetailVO> findToBeConfirmFriendByPage(String userId, int pageNumber, int pageSize);


    /**
     * 统计好友数
     * @param userId
     * @return
     */
    int countFriends(String userId);


    /**
     * 删除好友
     * @param userId 用户ID
     * @param friendId 好友用户ID
     */
    void deleteMyFriend(String userId, String friendId);

    /**
     * 查找xx是否为我的好友
     * @param userId 用户ID
     * @param receiverId  好友ID
     * @return
     */
    Friend checkIsMyFriend(String userId, String receiverId);

    /**
     * 查找xx是否为我正在申请的好友
     * @param userId 用户ID
     * @param friendId  好友ID
     * @return
     */
    Friend checkIsApplyMyFriend(String userId, String friendId);
    
    /**
     * 添加好友亲密度
     * @param userId   用户ID
     * @param friendId 好友用户ID
     * @param num 	        亲密度
     */
    void updateIntimacy(String userId, String friendId,int num);

    /**
     * 获取好友亲密度
     * @param userId   用户ID
     * @param friendId 好友用户ID
     * @return num 	        亲密度
     */
	Integer getIntimacy(String userId, String friendId);

    /**
     * 更新好友的战斗状态
     * @param userId
     * @param friendId
     * @param status
     */
    void updateFightStatus(String userId, String friendId, Integer status);

    void resetFightStatus();

    void addFriend(Friend friend);
}
