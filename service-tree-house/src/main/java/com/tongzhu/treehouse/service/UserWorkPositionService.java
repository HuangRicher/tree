package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.service.vo.WorkPositionModel;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.model.UserWorkPosition;
import com.tongzhu.treehouse.service.vo.WorkPositionVO;

import java.util.Date;
import java.util.List;

public interface UserWorkPositionService {


    /**
     * 手动解锁工位
     * @param userId
     * @param positionId
     * @param workTypeId
     * @param order
     */
    List<UserGoods> manualUnlock(String userId, String positionId, int workTypeId, int order) throws CheckException;

    /**
     * 工位列表
     * @param userId
     * @param workTypeId
     * @return
     */
    List<UserWorkPosition> findByUserIdAndWorkTypeId(String userId, int workTypeId);

    /**
     * 工位列表
     * @param userId
     * @param workTypeId
     * @return
     */
    List<WorkPositionModel> findModelByUserIdAndWorkTypeId(String userId, int workTypeId);

    /**
     * xx用户的xx工种的工位列表详情
     * @param userId  用户ID
     * @param workTypeId  工种ID
     * @return
     */
    List<WorkPositionVO> findListByUserIdAndWorkTypeId(String userId, int workTypeId);

    /**
     * 获取收益后重置打工开始时间
     * @param workPositionId
     */
    void resetForGetIncome(String workPositionId);

    /**
     * 打工中工位派工宅友更换
     * @param userId
     * @param workerId
     * @param workPositionId
     */
    void assignWorkChange(String userId, String workerId, String workPositionId);

    /**
     * 空闲工位派工
     * @param userId
     * @param workerId
     * @param workPositionId
     */
    void assignWork(String userId, String workerId, String workPositionId);

    /**
     * 选择宅友派工
     * @param workPositionId
     * @param workerId
     * @param workPosition
     */
    void updateByPositionIdForWorker(String workPositionId, String workerId, UserWorkPosition workPosition);

    /**
     * 更换派工宅友
     * @param workPositionId
     * @param workerId
     */
    void updateByPositionIdForChangeWorker(String workPositionId, String workerId);

    /**
     * 批量插入工位
     * @param positionList
     */
    void insertBatch(List<UserWorkPosition> positionList);

    UserWorkPosition findByWorkPositionId(String workPositionId);

    /**
     * 查找xx用户的工位上的xx宅友
     * @param userId
     * @param workerId
     * @return
     */
    UserWorkPosition findByUserIdAndWorkerId(String userId, String workerId);

    void initWorkPositionBatch(Integer workTypeId, String userId);

    void changeWorkerInWorking(String userId, String workerId, String workPositionId);

    /**
     * 宅友离开工位记录工时
     * @param workerId
     */
    void updateByWorkerIdForLeave(String workerId);

    void updateForReleaseWorker(String userId, String workerId, int lastTime, Date date);

    WorkPositionVO findUserWorkPositionByPositionId(String userId,String workPositionId);

    /**
     * 解除佣工
     * @param userId
     * @param workerId
     */
    void makeWorkPositionIdle(String userId, String workerId);
}
