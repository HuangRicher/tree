package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.model.UserWorkType;

import java.util.List;

public interface UserWorkTypeService {
    void batchAdd(List<UserWorkType> userWorkTypes);

    int add(UserWorkType userWorkType);

    List<UserWorkType> findByUserId(String userId);

    UserWorkType findByUserIdAndWorkTypeId(String userId, int workTypeId);

    /**
     * @Author hwb
     * @Description 打工场景解锁
     * @Param [status]
     * @return int
     **/
    int updateStatusByUserIdAndWorkTypeId(String userId, int workTypeId, int status);


    /**
     * @Author hwb
     * @Description 根据树屋等级解锁打工场景
     * @Param [treeHouseGrade, userId]
     * @return void
     **/
    void upgradeWorkType(int treeHouseGrade, String userId);


    /**
     * @Author hwb
     * @Description 一次获取xx工种下的所有收益
     * @Param [userId, workTypeId]
     * @return void
     **/
    List<UserGoods> getWorkIncomeAll(String userId, int workTypeId);

    /**
     * 升级工种
     * @param userId
     * @param workTypeId
     */
    void upgradeLevel(String userId, Integer workTypeId, Integer level);
}
