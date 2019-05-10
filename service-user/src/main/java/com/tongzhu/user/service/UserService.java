package com.tongzhu.user.service;


import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.model.User;
import com.tongzhu.user.po.GenerateUserPO;
import com.tongzhu.user.po.SkillPO;
import com.tongzhu.user.service.vo.UserDetailVO;

import java.util.Date;
import java.util.List;


public interface UserService {

    User findByUserId(String userId);

    void updateUser(User user);

    User initUser(String byUserId);

    /**
     * 降低宅友身价
     *
     * @param userId
     * @param workerId
     */
    User reduceSellingPrice(String userId, String workerId);

    /**
     * 随机查询可购买的陌生玩家
     *
     * @param minSellingPrice 身价范围--小
     * @param maxSellingPrice 身价范围--大
     * @param userId          用户ID
     * @param currentDateTime 当前时间
     * @param currentDate     当前日期
     * @param exchangeCount   用户被转让的次数
     * @param count           数量
     * @return
     */
    List<User> selectUserForBuyByRand(int minSellingPrice, int maxSellingPrice, String userId,
                                      Date currentDateTime, Date currentDate, int exchangeCount, int count);


    /**
     * 随机查询可购买的好友
     *
     * @param userId          用户ID
     * @param status          好友状态
     * @param currentDateTime 当前时间
     * @param currentDate     当前日期
     * @param exchangeCount   转让次数
     * @param sellingPrice    用户拥有金币的数量
     * @param count           查询记录数量
     * @return
     */
    List<User> selectFriendsForBuyByRand(String userId, int status, Date currentDateTime, Date currentDate,
                                         int exchangeCount, int sellingPrice, int count);

    /**
     * 推荐好友列表
     * @param userId
     * @return
     */
    List<User> selectRecommendFriends(String userId);


    /**
     * 随机匹配一个玩家
     * @param userId
     * @return
     */
    User selectUserForFightRankingByRand(String userId);

    /**
     * 身价排名榜
     *
     * @param count 查询的记录数量
     * @return
     */
    List<UserRankingVO> findForRankingListAboutSellingPrice(int count);


    /**
     * 富豪榜（金币数量）
     *
     * @param goodsId 物品Id
     * @param count   查询的记录数量
     * @return
     */
    List<UserRankingVO> findForRankingListAboutMoney(String goodsId, int count);

    /**
     * 获取玩家信息
     *
     * @param userId
     * @return
     */
    UserDetailVO getUserDetail(String userId);

    /**
     * 微信用户首次登陆
     *
     * @param openid
     * @param sex
     * @param nickName
     * @param province
     * @param city
     * @param country
     * @param headImgUrl
     * @param unionId
     * @return
     */
    User initWxUser(String openid, int sex, String nickName, String province, String city, String country, String headImgUrl, String unionId);

    /**
     * 根据微信openID查找用户
     *
     * @param openid
     * @return
     */
    User findByOpenId(String openid);

    /**
     * 修改宅友身价
     *
     * @param userId
     * @param sellingPrice
     */
    void updateUserSellingPrice(String userId, Integer sellingPrice);

    /**
     * 统计所有用户数
     *
     * @return
     */
    int countAllUser();

    /**
     * 给用户添加角色
     * @param userId
     * @param roleName
     * @param roleId
     * @param roleSex
     * @param account
     */
    void addUserRole(String userId, String roleName, Integer roleId, Integer roleSex, String account);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleName
     * @param roleId
     */
    void addUserRole(String userId, String roleName, Integer roleId, Integer roleLevel, List<SkillPO> skillList, List<String> weaponIds);

    /**
     * 给微信注册用户添加userId,并初始化数据。
     *
     * @param account
     * @param roleSex
     * @param roleId
     * @return
     */
    User addUserId(String account, Integer roleSex, Integer roleId);

    void updateUserByUserId(User user);

    /**
     * 等级排行总榜
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutRoleLevel(int count);

    /**
     * 魅力值排行总榜
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutCharmNum(int count);

    /**
     * 战斗力排行总榜
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutAlwaysFighting(int count);

    /**
     * 根据用户名或userId获取玩家信息
     * @param searchUser
     * @return
     */
    List<UserDetailVO> getUserByUserIdOrName(String searchUser);

    List<UserRankingVO> findForRankingListAboutPet(int count);

    void generateNewUser(GenerateUserPO generateUserPO);

    User recommendFriendByOppositeSex(String userId);

    void addUser(User user);

    List<User> findNPCUser(List<String> userIds);

    void deleteUserByUserId(String s);
}
