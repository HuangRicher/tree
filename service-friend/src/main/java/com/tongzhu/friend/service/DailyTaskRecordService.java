package com.tongzhu.friend.service;

import com.tongzhu.friend.model.DailyTaskRecord;
import com.tongzhu.friend.service.vo.DailyTaskVO;

import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
public interface DailyTaskRecordService {


    /**
     * 获取任务列表详情
     * @param userId
     * @return
     */
    List<DailyTaskVO> getDailyTaskVOList(String userId);

    int insert(DailyTaskRecord dailyTaskRecord);

    /**
     * 查找日常任务详情
     * @param id
     * @return
     */
    DailyTaskRecord getDailyTaskRecordById(String id);

    int update(DailyTaskRecord dailyTaskRecord);

    /**
     * 查询日常任务列表
     * @param userId
     * @return
     */
    List<DailyTaskRecord> getDailyTaskRecordList(String userId);

    /**
     * 查询任务详情
     * @param dailyTaskId  任务信息表id
     * @param userId 用户id
     * @return
     */
    DailyTaskRecord getDailyTaskRecordByDailyTaskId(Integer dailyTaskId,String userId);

    /**
     * 更新任务进度和信息
     * @param userId
     */
    void detectionTask(String userId);

    DailyTaskRecord getDailyTaskRecordByUserIdAndDailyTaskId(String userId, Integer dailyTaskId);

    List<DailyTaskRecord> getDailyTaskRecordList(String userId, int status , int taskReceiveAwardNo);
}
