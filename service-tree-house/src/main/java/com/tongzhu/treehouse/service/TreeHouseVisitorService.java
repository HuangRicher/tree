package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.mapper.vo.TreeHouseVisitorDO;
import com.tongzhu.treehouse.model.TreeHouseVisitor;

import java.util.List;

public interface TreeHouseVisitorService {

    void add(TreeHouseVisitor treeHouseVisitor);

    void addBatch(List<TreeHouseVisitor> list);

    /**
     * 树屋游客列表
     * @param userId
     * @param treeHouseUserId
     * @return
     */
    List<TreeHouseVisitorDO> findTreeHouseVisitor(String userId,String treeHouseUserId);

    void deleteVisitor(String treeHouseId,String userId);

    void disableVisitor(String id);

    void updateById(TreeHouseVisitor visitor);

    /**
     * 统计树屋可用的空闲游客位置数量
     * @param treeHouseId
     * @return
     */
    int getCanUseVisitorLocation(String treeHouseId);

    /**
     * 查找树屋的游客列表
     * @param treeHouseId
     * @return
     */
    List<TreeHouseVisitor> findByTreeHouseId(String treeHouseId);

    List<TreeHouseVisitor> findByVisitorId(String userId);

    /**
     * 无值置空更新
     * @param visitor
     */
    void updateByIdWithNull(TreeHouseVisitor visitor);

    /**
     * 把游客请出树屋
     * @param userId
     * @param outUserId
     */
    void letOutVisitor(String userId, String outUserId);

    void updateByTreeHouseIdAndVisitorId(String treeHouseUserId, String userId);
}
