package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.mapper.TaskBranchRecordMapper;
import com.tongzhu.friend.mapper.ext.TaskBranchRecordExtMapper;
import com.tongzhu.friend.model.TaskBranch;
import com.tongzhu.friend.model.TaskBranchRecord;
import com.tongzhu.friend.model.TaskBranchRecordExample;
import com.tongzhu.friend.model.TaskBranchRecordKey;
import com.tongzhu.friend.service.TaskBranchRecordService;
import com.tongzhu.friend.service.TaskBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/1/19 0019.
 */

@Service
public class TaskBranchRecordServiceImpl implements TaskBranchRecordService {

    @Autowired
    private TaskBranchRecordMapper taskBranchRecordMapper;

    @Autowired
    private TaskBranchService taskBranchService;

    @Autowired
    private TaskBranchRecordExtMapper taskBranchRecordExtMapper;

    @Override
    public TaskBranchRecord getTaskBranchRecord(String userId, Integer taskId) {
        TaskBranchRecordKey taskBranchRecordKey = new TaskBranchRecordKey();
        taskBranchRecordKey.setTaskId(taskId);
        taskBranchRecordKey.setUserId(userId);
        TaskBranchRecord taskBranchRecord = taskBranchRecordMapper.selectByPrimaryKey(taskBranchRecordKey);
        return taskBranchRecord;
    }

    @Override
    public int insertSelective(TaskBranchRecord taskBranchRecord) {
        return taskBranchRecordMapper.insertSelective(taskBranchRecord);
    }

    @Override
    public List<TaskBranchRecord> getTaskBranchRecordUndoneList(String userId) {
        List<TaskBranchRecord> taskBranchRecords = taskBranchRecordExtMapper.getTaskBranchRecordUndoneList(userId, TaskConstant.TASK_RECEIVE_AWARD_NO);
        return taskBranchRecords;
    }

    @Override
    public int updateByPrimaryKeySelective(TaskBranchRecord taskBranchRecord) {
        return taskBranchRecordMapper.updateByPrimaryKeySelective(taskBranchRecord);
    }

    @Override
    public void taskBranchFinish(Integer taskId, String userId) {
        synchronized (userId.intern()){
        TaskBranchRecord taskBranchRecord = this.getTaskBranchRecord(userId, taskId);
        if (taskBranchRecord == null) {
            TaskBranch taskBranch = taskBranchService.getTaskBranch(taskId);
            if (taskBranch == null) {
                return;
            }
            taskBranchRecord = new TaskBranchRecord();
            taskBranchRecord.setUserId(userId);
            taskBranchRecord.setFinishDate(new Date());
            taskBranchRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
            taskBranchRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
            taskBranchRecord.setStatus(TaskConstant.TASK_STATUS_SUCCEED);
            taskBranchRecord.setGoal(taskBranch.getCondition());
            taskBranchRecord.setTaskId(taskBranch.getId());
            this.insertSelective(taskBranchRecord);
        } else {
            if (taskBranchRecord.getStatus() == TaskConstant.TASK_STATUS_SUCCEED) {
                return;
            }
            taskBranchRecord.setProgress(taskBranchRecord.getGoal());
            taskBranchRecord.setFinishDate(new Date());
            taskBranchRecord.setStatus(TaskConstant.TASK_STATUS_SUCCEED);
            taskBranchRecord.setProgress(taskBranchRecord.getGoal());
            this.updateByPrimaryKeySelective(taskBranchRecord);
        }
    }
}}
