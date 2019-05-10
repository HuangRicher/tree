package com.tongzhu.friend.service;


import com.tongzhu.friend.model.TaskBranchRecord;

import java.util.List;

/**
 * Created by Administrator on 2019/1/18 0018.
 */
public interface TaskBranchRecordService {
    TaskBranchRecord getTaskBranchRecord(String userId,Integer taskId);

    int insertSelective(TaskBranchRecord taskBranchRecord);

    List<TaskBranchRecord> getTaskBranchRecordUndoneList(String userId);

    int updateByPrimaryKeySelective(TaskBranchRecord taskBranchRecord);

    void taskBranchFinish(Integer taskId, String userId);
}
