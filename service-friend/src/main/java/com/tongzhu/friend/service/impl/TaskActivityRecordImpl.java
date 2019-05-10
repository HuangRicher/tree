package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.mapper.TaskActivityRecordMapper;
import com.tongzhu.friend.mapper.ext.TaskActivityRecordExtMapper;
import com.tongzhu.friend.model.TaskActivityRecord;
import com.tongzhu.friend.model.TaskActivityRecordExample;
import com.tongzhu.friend.model.TaskActivityRewards;
import com.tongzhu.friend.service.TaskActivityRecordService;
import com.tongzhu.friend.service.TaskActivityRewardsService;
import com.tongzhu.friend.service.vo.TaskActivityVO;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
@Service
public class TaskActivityRecordImpl implements TaskActivityRecordService {

    @Autowired
    private TaskActivityRecordExtMapper taskActivityRecordExtMapper;

    @Autowired
    private TaskActivityRecordMapper taskActivityRecordMapper;

    @Autowired
    private TaskActivityRewardsService taskActivityRewardsService;

    @Override
    public List<TaskActivityVO> getTaskActivityList(String userId, Integer liveness) {
        return taskActivityRecordExtMapper.getTaskActivityRecordList(userId, liveness);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TaskActivityRecord taskActivityRecord) {
        return taskActivityRecordMapper.insertSelective(taskActivityRecord);
    }

    @Override
    public List<TaskActivityRecord> getTaskActivityRecordList(String userId) {
        TaskActivityRecordExample taskActivityRecordExample = new TaskActivityRecordExample();
        taskActivityRecordExample.createCriteria().andUserIdEqualTo(userId);
        return taskActivityRecordMapper.selectByExample(taskActivityRecordExample);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(TaskActivityRecord taskActivityRecord) {
        return taskActivityRecordMapper.updateByPrimaryKey(taskActivityRecord);
    }

    @Override
    public List<TaskActivityRecord> getTaskActivityRecordListByLiveness(String userId, Integer liveness) {
        return taskActivityRecordExtMapper.getTaskActivityRecordListByLiveness(userId, liveness);
    }

    @Override
    public TaskActivityRecord getTaskActivityRecord(String id) {
        return taskActivityRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaskActivityRecord getTaskActivityRecordByActivityRewardsId(Integer id,String userId) {
        TaskActivityRecordExample taskActivityRecordExample = new TaskActivityRecordExample();
        taskActivityRecordExample.createCriteria().andActivityRewardsIdEqualTo(id).andUserIdEqualTo(userId);
        List<TaskActivityRecord> taskActivityRecordList = taskActivityRecordMapper.selectByExample(taskActivityRecordExample);
        if (taskActivityRecordList.size() <= 0) {
            return null;
        }
        return taskActivityRecordList.get(0);
    }

    @Override
    public void detectionTaskActivity(String userId) {
        List<TaskActivityRecord> taskActivityRecordList = getTaskActivityRecordList(userId);
        List<TaskActivityRewards> taskActivityRewardsList = taskActivityRewardsService.getTaskActivityRewardsList(userId);
        Date date = new Date();
        if (taskActivityRecordList.size() <= 0) {
            TaskActivityRecord taskActivityRecord;
            for (TaskActivityRewards taskActivityRewards : taskActivityRewardsList) {
                taskActivityRecord = new TaskActivityRecord();
                taskActivityRecord.setId(UUIDUtil.generateUUID());
                taskActivityRecord.setUserId(userId);
                taskActivityRecord.setActivityRewardsId(taskActivityRewards.getId());
                taskActivityRecord.setPerformance(TaskConstant.TASK_ACTIVITY_PERFORMANCE_NO);
                taskActivityRecord.setStatus(TaskConstant.TASK_ACTIVITY_STATUS_NO);
                taskActivityRecord.setUpdateDate(date);
                insert(taskActivityRecord);
            }
        } else {
            for (TaskActivityRecord taskActivityRecord : taskActivityRecordList) {
                if (!DateUtil.isSameDate(new Date(), taskActivityRecord.getUpdateDate())) {
                    taskActivityRecord.setPerformance(TaskConstant.TASK_ACTIVITY_PERFORMANCE_NO);
                    taskActivityRecord.setStatus(TaskConstant.TASK_ACTIVITY_STATUS_NO);
                    taskActivityRecord.setUpdateDate(date);
                    update(taskActivityRecord);
                }
            }
        }
        if (taskActivityRecordList.size() != taskActivityRewardsList.size()) {
            for (TaskActivityRewards taskActivityRewards : taskActivityRewardsList) {
                TaskActivityRecord taskActivityRecord = getTaskActivityRecordByActivityRewardsId(taskActivityRewards.getId(),userId);
                if (taskActivityRecord == null) {
                    if (!DateUtil.isSameDate(new Date(), taskActivityRewards.getCreateDate())) {
                        taskActivityRecord = new TaskActivityRecord();
                        taskActivityRecord.setId(UUIDUtil.generateUUID());
                        taskActivityRecord.setUserId(userId);
                        taskActivityRecord.setActivityRewardsId(taskActivityRewards.getId());
                        taskActivityRecord.setPerformance(TaskConstant.TASK_ACTIVITY_PERFORMANCE_NO);
                        taskActivityRecord.setUpdateDate(date);
                        taskActivityRecord.setStatus(TaskConstant.TASK_ACTIVITY_STATUS_NO);
                        insert(taskActivityRecord);
                    }
                }
            }
        }
    }
}
