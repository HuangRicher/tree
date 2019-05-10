package com.tongzhu.user.mapper.ext;


import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserExtMapper {

    /**
     * 随机查询推荐用户
     *
     * @param minSellingPrice
     * @param maxSellingPrice
     * @param userId
     * @param protectEndDate
     * @param exchangeDate
     * @param exchangeCount
     * @param count
     * @return
     */
    List<User> selectUserForBuyByRand(@Param("minSellingPrice") int minSellingPrice,
                                      @Param("maxSellingPrice") int maxSellingPrice,
                                      @Param("userId") String userId,
                                      @Param("protectEndDate") Date protectEndDate,
                                      @Param("exchangeDate") Date exchangeDate,
                                      @Param("exchangeCount") int exchangeCount,
                                      @Param("count") int count);


    List<User> selectFriendsForBuyByRand(@Param("userId") String userId,
                                         @Param("status") int status,
                                         @Param("protectEndDate") Date protectEndDate,
                                         @Param("exchangeDate") Date exchangeDate,
                                         @Param("exchangeCount") int exchangeCount,
                                         @Param("sellingPrice") int sellingPrice,
                                         @Param("count") int count);

    List<User> selectRecommendFriends(@Param("userId") String userId,
                                      @Param("roleLevel") Integer roleLevel,
                                      @Param("sex") Integer sex,
                                      @Param("count") Integer count);

    User recommendFriendByOppositeSex(@Param("userId")String userId, @Param("sex")Integer sex);

    List<User> selectRecommendFriendsOther(@Param("userId") String userId,
                                           @Param("roleLevelMin") Integer roleLevelMin,
                                           @Param("roleLevelMax") Integer roleLevelMax,
                                           @Param("sex") Integer sex,
                                           @Param("count") Integer count);

    /**
     * 查询身价排名
     *
     * @param count
     * @return
     */
    List<UserRankingVO> selectForSellingPriceRanklingList(@Param("count") int count);


    /**
     * 查询金币排名
     *
     * @param count
     * @return
     */
    List<UserRankingVO> selectForMoneyRanklingList(@Param("goodsId") String goodsId, @Param("count") int count);

    /**
     * 查询等级排名
     *
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutRoleLevel(@Param("count") int count);

    /**
     * 查询魅力值排名
     *
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutCharmNum(@Param("count") int count);

    /**
     * 查询战斗力排名
     *
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutAlwaysFighting(@Param("count") int count);

    /**
     * 根据Id或用户名
     * @param searchName
     * @return
     */
    List<User> selectByUserIdOrName(@Param("searchName")String searchName);

    /**
     * 查询拥有宠物数量排名
     * @param count
     * @return
     */
    List<UserRankingVO> findForRankingListAboutPet(@Param("count") int count);

    /**
     * 随机查找一个未参加荣誉挑战的玩家
     * @param userId
     * @return
     */
    User selectOneForFightRankingByRand(@Param("userId") String userId);

}
