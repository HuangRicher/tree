package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.mapper.DailyTaskRecordMapper;
import com.tongzhu.friend.mapper.ext.DailyTaskRecordExtMapper;
import com.tongzhu.friend.model.DailyTaskInfo;
import com.tongzhu.friend.model.DailyTaskRecord;
import com.tongzhu.friend.model.DailyTaskRecordExample;
import com.tongzhu.friend.service.DailyTaskInfoService;
import com.tongzhu.friend.service.DailyTaskRecordService;
import com.tongzhu.friend.service.vo.DailyTaskVO;
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
public class DailyTaskRecordImpl implements DailyTaskRecordService {

    @Autowired
    private DailyTaskRecordExtMapper dailyTaskRecordExtMapper;

    @Autowired
    private DailyTaskRecordMapper dailyTaskRecordMapper;

    @Autowired
    private DailyTaskInfoService dailyTaskInfoService;

    @Override
    public List<DailyTaskVO> getDailyTaskVOList(String userId) {
        return dailyTaskRecordExtMapper.getDailyTaskVOList(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DailyTaskRecord dailyTaskRecord) {
        return dailyTaskRecordMapper.insert(dailyTaskRecord);
    }

    @Override
    public DailyTaskRecord getDailyTaskRecordById(String id) {
        return dailyTaskRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(DailyTaskRecord dailyTaskRecord) {
        return dailyTaskRecordMapper.updateByPrimaryKeySelective(dailyTaskRecord);
    }

    @Override
    public List<DailyTaskRecord> getDailyTaskRecordList(String userId) {
        DailyTaskRecordExample dailyTaskRecordExample = new DailyTaskRecordExample();
        dailyTaskRecordExample.createCriteria().andUserIdEqualTo(userId);
        dailyTaskRecordExample.setOrderByClause("daily_task_id ASC ");
        return dailyTaskRecordMapper.selectByExample(dailyTaskRecordExample);
    }

    @Override
    public DailyTaskRecord getDailyTaskRecordByDailyTaskId(Integer dailyTaskId, String userId) {
        DailyTaskRecordExample dailyTaskRecordExample = new DailyTaskRecordExample();
        dailyTaskRecordExample.createCriteria().andDailyTaskIdEqualTo(dailyTaskId).andUserIdEqualTo(userId);
        List<DailyTaskRecord> dailyTaskRecordList = dailyTaskRecordMapper.selectByExample(dailyTaskRecordExample);
        if (dailyTaskRecordList.size() <= 0) {
            return null;
        }
        return dailyTaskRecordList.get(0);
    }

    @Override
    public void detectionTask(String userId) {
        List<DailyTaskRecord> dailyTaskRecordList = getDailyTaskRecordList(userId);
        List<DailyTaskInfo> dailyTaskInfoList = dailyTaskInfoService.getDailyTaskInfoList();
        Date date = new Date();
        if (dailyTaskRecordList.size() <= 0) {
            DailyTaskRecord dailyTaskRecord;
            for (DailyTaskInfo dailyTaskInfo : dailyTaskInfoList) {
                dailyTaskRecord = new DailyTaskRecord();
                dailyTaskRecord.setId(UUIDUtil.generateUUID());
                dailyTaskRecord.setUserId(userId);
                dailyTaskRecord.setDailyTaskId(dailyTaskInfo.getId());
                dailyTaskRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                dailyTaskRecord.setGoal(dailyTaskInfo.getCondition());
                dailyTaskRecord.setUpdateDate(date);
                dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                insert(dailyTaskRecord);
            }
        } else {
            for (DailyTaskRecord dailyTaskRecord : dailyTaskRecordList) {
                if (!DateUtil.isSameDate(new Date(), dailyTaskRecord.getUpdateDate())) {
                    DailyTaskInfo dailyTaskInfo = dailyTaskInfoService.getDailyTaskInfoById(dailyTaskRecord.getDailyTaskId());
                    if (dailyTaskInfo == null) {
                        continue;
                    }
                    dailyTaskRecord.setDailyTaskId(dailyTaskInfo.getId());
                    dailyTaskRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                    dailyTaskRecord.setGoal(dailyTaskInfo.getCondition());
                    dailyTaskRecord.setUpdateDate(date);
                    dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                    dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                    update(dailyTaskRecord);
                }
            }
        }
        if (dailyTaskRecordList.size() != dailyTaskInfoList.size()) {
            for (DailyTaskInfo dailyTaskInfo : dailyTaskInfoList) {
                DailyTaskRecord dailyTaskRecord = getDailyTaskRecordByDailyTaskId(dailyTaskInfo.getId(), userId);
                if (dailyTaskRecord == null) {
                    if (!DateUtil.isSameDate(new Date(), dailyTaskInfo.getCreateDate())) {
                        dailyTaskRecord = new DailyTaskRecord();
                        dailyTaskRecord.setId(UUIDUtil.generateUUID());
                        dailyTaskRecord.setUserId(userId);
                        dailyTaskRecord.setDailyTaskId(dailyTaskInfo.getId());
                        dailyTaskRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                        dailyTaskRecord.setGoal(dailyTaskInfo.getCondition());
                        dailyTaskRecord.setUpdateDate(date);
                        dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                        dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                        insert(dailyTaskRecord);
                    }
                }
            }
        }
    }

    @Override
    public DailyTaskRecord getDailyTaskRecordByUserIdAndDailyTaskId(String userId, Integer dailyTaskId) {
        DailyTaskRecordExample dailyTaskRecordExample = new DailyTaskRecordExample();
        dailyTaskRecordExample.createCriteria().andUserIdEqualTo(userId).andDailyTaskIdEqualTo(dailyTaskId);
        List<DailyTaskRecord> dailyTaskRecordList = dailyTaskRecordMapper.selectByExample(dailyTaskRecordExample);
        if (dailyTaskRecordList.size() > 0) {
            return dailyTaskRecordList.get(0);
        }
        return null;
    }

    @Override
    public List<DailyTaskRecord> getDailyTaskRecordList(String userId, int status, int receiveAward) {
        DailyTaskRecordExample dailyTaskRecordExample = new DailyTaskRecordExample();
        Date date = DateUtil.initDateByDay();
        dailyTaskRecordExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status).andReceiveAwardEqualTo(receiveAward).andUpdateDateGreaterThanOrEqualTo(date);
        return dailyTaskRecordMapper.selectByExample(dailyTaskRecordExample);
    }
}
