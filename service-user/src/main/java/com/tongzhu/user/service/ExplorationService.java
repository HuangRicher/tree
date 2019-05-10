package com.tongzhu.user.service;

import com.tongzhu.user.model.Exploration;
import com.tongzhu.user.model.ExplorationUser;
import com.tongzhu.user.service.vo.ExplorationUserVO;

import java.util.List;

public interface ExplorationService{

    void updateScoreForExplorationUser(String userId,Integer explorationId,Integer passId,Integer starCount);

    List<Exploration> findAll();

    List<ExplorationUserVO> findByUserId(String userId);

    ExplorationUser findBuyExplorationIdAndPassId(String userId,Integer explorationId,Integer passId);

    List<ExplorationUser> findByUserIdAndExplorationId(String userId, int explorationId);

    /**
     * 初始化用户探索关卡
     * @param userId
     */
    void initUserExploration(String userId);
}
