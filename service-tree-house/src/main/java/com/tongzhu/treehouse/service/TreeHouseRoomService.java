package com.tongzhu.treehouse.service;

import com.github.pagehelper.Page;
import com.tongzhu.common.Pager;
import com.tongzhu.treehouse.domain.FriendDetail;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomCountVO;
import com.tongzhu.treehouse.mapper.vo.TreeHouseRoomDO;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.service.vo.UserFriendDetailVO;
import com.tongzhu.treehouse.service.vo.UserVO;
import com.tongzhu.treehouse.service.vo.UserWorkerVO;

import java.util.List;

public interface TreeHouseRoomService {

    int add(TreeHouseRoom userWorker);

    /**
     * 查找当前用户的宅友列表
     * @param userId
     * @return
     */
    List<UserWorkerVO> findWorkersByUserId(String userId);

    /**
     * 查找xx用户宅友用户ID为xx的宅友
     * @param userId
     * @param workerId
     * @return
     */
    TreeHouseRoom findWorkersByUserIdAndWorkerId(String userId, String workerId);

    /**
     * 判断宅友是否正在工作中
     * @param userId
     * @param workerId
     * @return
     */
    TreeHouseRoom checkWorkerIsWorking(String userId, String workerId);

    /**
     * 给宅友派工
     * @param userId
     * @param workerId
     */
    void updateByWorkIdForStatus(String userId, int workTypeId, String workerId);

    /**
     * 派工更换宅友
     * @param userId
     * @param workerId
     */
    void updateByWorkIdForStatusChange(String userId, int workTypeId, String workerId, String oldWorkerId);

    /**
     * 查找每个用户的树屋房间数
     * @return
     */
    List<TreeHouseRoomCountVO> countByUserId();

    /**
     * 从最大的房间号开始减房间
     * @param userId
     */
    void deleteFinalRoom(String userId);

    /**
     * 查找树屋所拥有的房间
     * @param id
     * @return
     */
    List<TreeHouseRoom> findRoomsByTreeHouseId(String id);


    /**
     * 查找当前用户的宅友上限（树屋房间总数）
     * @param userId
     * @return
     */
    int countRoomsByUserId(String userId);

    /**
     * 判断xx用户是否为他人的宅友
     * @param userId
     * @return
     */
    TreeHouseRoom checkIsWorkerByWorkerId(String userId);

    /**
     * 查找当前用户的宅友列表
     * @param userId
     * @return
     */
    List<FriendDetail> findWorkersList(String userId);

    /**
     * 查找好友的宅友列表（包括好友自己)
     * @param friendUserId
     * @return
     */
    List<FriendDetail> findFriendWorkersListWithSelf(String userId,String friendUserId);

    /**
     * 查找当前用户拥有的宅友
     * @param userId
     * @return
     */
    List<TreeHouseRoomDO> findWorkersListByUserId(String userId);

    /**
     * 查找当前用户拥有的宅友数量
     * @param userId
     * @return
     */
    int countWorkersByUserId(String userId);

    /**
     * 分页查找当前用户的宅友列表
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<TreeHouseRoom> findWorkersListByUserIdForPage(String userId, int pageNumber, int pageSize);


    /**
     * 分页查找当前用户的宅友列表
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Pager<UserFriendDetailVO> findWorkersByPage(String userId, int pageNumber, int pageSize);

    /**
     * 保护宅友（消耗钻石）
     * @param userId
     * @param workerId
     * @param hours
     * @return
     */
    UserGoods protectWorker(String userId, String workerId, int hours);

    /**
     * 获得宅友
     * @param userId
     * @param treeHouseRoom
     */
    UserGoods updateForBuyWorker(String userId, TreeHouseRoom treeHouseRoom) throws CheckException;

    /**
     * 更新降低宅友身价操作的时间
     * @param userId
     * @param workerId
     */
    void updateReducePriceDate(String userId, String workerId);

    /**
     * 释放宅友
     * @param userId
     * @param workerId
     */
    void releaseWorker(String userId, String workerId);

    /**
     *购买推荐宅友列表
     * @param userId
     * @return
     */
    List<UserVO> recommendToBuyList(String userId);


    void updateIsGameFriend(String userId, String friendId, Boolean value);

    UserWorkerVO findWorkerByUserIdAndWorkerId(String userId,String workerId);

    /**
     * 查找空闲的宅友
     * @param userId
     * @return
     */
    List<UserWorkerVO> findIdleWorkersByUserId(String userId);

    TreeHouseRoom checkWorkIsIdle(String userId, String workerId);


    void makeWorkerIdle(String userId, String workerId);


}
