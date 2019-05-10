package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseFlowerpot;

import java.util.List;
import java.util.Map;

public interface TreeHouseFlowerpotService {
    void add(TreeHouseFlowerpot treeHouseFlowerpot);

    void addBatch(List<TreeHouseFlowerpot> list);

    List<TreeHouseFlowerpot> findByTreeHouseId(String treeHouseId);

    /**
     * 解锁
     * @param id
     */
    void unlock(String id);

    /**
     * 播种
     * @param id
     * @param goodsId
     */
    void  sowSeeds(String userId,String id,String goodsId);

    /**
     * 浇水
     * @param flowerpot
     * @param treeHouseUserId
     */
    Map<String, Object> watering(TreeHouseFlowerpot flowerpot, String userId, String treeHouseUserId);

    /**
     * 施肥
     * @param flowerpot
     * @param treeHouseUserId
     */
    Map<String, Object> spreadManure(TreeHouseFlowerpot flowerpot, String userId, String treeHouseUserId);

    /**
     * 铲掉
     * @param id
     */
    void delete(String id);

    /**
     * 收获
     * @param id
     */
    Map<String,Object> harvest(String id);

    /**
     * 全部播种
     * @param userId
     */
    void sowSeedsAll(String userId);

    /**
     * 指派后同意全部浇水
     * @param userId
     * @param treeHouseUserId
     * @param grade
     */
    void wateringAll(String userId, String treeHouseUserId, Integer grade);

    /**
     * 指派后同意全部施肥
     * @param userId
     * @param treeHouseUserId
     * @param grade
     */
    void spreadManureAll(String userId, String treeHouseUserId, Integer grade);

    /**
     * 全部收获
     * @param userId
     */
    void harvestAll(String userId);

    /**
     *全部铲掉
     * @param userId
     */
    void deleteAll(String userId);

    TreeHouseFlowerpot getById(String flowerpotId);

    void unlockByTreeHouseLevel(String treeHouseUserId, Integer level);

    Map<String, Object> harvestOthers(String flowerpotId, String userId);
}
