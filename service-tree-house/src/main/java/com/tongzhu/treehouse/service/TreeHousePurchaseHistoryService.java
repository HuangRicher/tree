package com.tongzhu.treehouse.service;


import com.tongzhu.common.Pager;
import com.tongzhu.treehouse.model.TreeHousePurchaseHistory;
import com.tongzhu.treehouse.service.vo.TreeHousePurchaseHistoryVO;

public interface TreeHousePurchaseHistoryService {

    int add(TreeHousePurchaseHistory treeHousePurchaseHistory);



    /**
     * 获取玩家的宅友消费记录
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pager<TreeHousePurchaseHistoryVO> findByPage(String userId, Integer pageNum, Integer pageSize);


    /**
     * 删除玩家的宅友消费记录
     * @param id
     * @return
     */
    int delPurchaseHistory(String id);

}
