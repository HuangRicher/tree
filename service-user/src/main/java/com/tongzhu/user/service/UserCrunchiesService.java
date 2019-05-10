package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.UserRankingVO;

import java.util.List;

/**
 * Created by Administrator on 2018/11/24 0024.
 */
public interface UserCrunchiesService {

    /**
     * 更新月榜信息
     * @param userId
     * @param type   1 战斗力月榜 2 魅力月榜 3 幸福值月榜
     * @param recordValues 当前记录值 type 1 当前用户战斗力 2 当前用户魅力值 3 当前用户幸福值
     */
    void updateCrunchies(String userId,int type,int recordValues);

    /**
     * 获取月榜信息
     * @param count
     * @param type 1 战斗力月榜 2 魅力月榜 3 幸福值月榜
     * @return
     */
    List<UserRankingVO> fightingMonthRankingList(int count, int type);
}
