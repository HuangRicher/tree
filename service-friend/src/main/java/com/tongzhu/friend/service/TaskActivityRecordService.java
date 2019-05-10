package com.tongzhu.friend.service;

import com.tongzhu.friend.model.TaskActivityRecord;
import com.tongzhu.friend.service.vo.TaskActivityVO;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
public interface TaskActivityRecordService {

    /**
     * 根据活跃度获取用户可以打开的宝箱
     * @param userId
     * @param liveness
     * @return
     */
    List<TaskActivityVO> getTaskActivityList(String userId,Integer liveness);

    int insert(TaskActivityRecord taskActivityRecord);

    /**
     * 获取用户所有活跃度宝箱的详情
     * @param userId
     * @return
     */
    List<TaskActivityRecord> getTaskActivityRecordList(String userId);

    int update(TaskActivityRecord taskActivityRecord);
    /**
     * 获取用户所有活跃度宝箱的详情
     * @param userId
     * @return
     */
    List<TaskActivityRecord> getTaskActivityRecordListByLiveness(String userId, Integer liveness);

    /**
     * 获取宝箱详情
     * @param id
     * @return
     */
    TaskActivityRecord getTaskActivityRecord(String id);

    /**
     * 根据用户id和宝箱id 获取任务详情
     * @param id
     * @param userId
     * @return
     */
    TaskActivityRecord getTaskActivityRecordByActivityRewardsId(Integer id,String userId);

    /**
     * 更新任务活跃度宝箱详情
     * @param userId
     */
    void detectionTaskActivity(String userId);
}
