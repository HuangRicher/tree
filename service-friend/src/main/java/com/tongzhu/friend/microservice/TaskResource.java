package com.tongzhu.friend.microservice;

import com.alibaba.fastjson.JSON;
import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.model.DailyTaskInfo;
import com.tongzhu.friend.model.DailyTaskRecord;
import com.tongzhu.friend.model.TaskBranchRecord;
import com.tongzhu.friend.service.*;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/taskResource")
public class TaskResource {

    @Autowired
    private DailyTaskInfoService dailyTaskInfoService;

    @Autowired
    private DailyTaskRecordService dailyTaskRecordService;

    @Autowired
    private TaskBranchService taskBranchService;

    @Autowired
    private TaskBranchRecordService taskBranchRecordService;

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/taskProgress/{type}/{number}/{userId}")
    public void taskProgress(@PathVariable("type") Integer type, @PathVariable("number") Integer number, @PathVariable("userId") String userId) {
        List<DailyTaskInfo> dailyTaskInfoList = dailyTaskInfoService.getDailyTaskInfoByType(type);
        boolean bo = false;
        if (dailyTaskInfoList.size() > 0) {
            for (DailyTaskInfo dailyTaskInfo : dailyTaskInfoList) {
                DailyTaskRecord dailyTaskRecord = dailyTaskRecordService.getDailyTaskRecordByDailyTaskId(dailyTaskInfo.getId(), userId);
                if (dailyTaskRecord == null) {
                    dailyTaskRecord = new DailyTaskRecord();
                    dailyTaskRecord.setId(UUIDUtil.generateUUID());
                    dailyTaskRecord.setUserId(userId);
                    dailyTaskRecord.setDailyTaskId(dailyTaskInfo.getId());
                    dailyTaskRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                    dailyTaskRecord.setGoal(dailyTaskInfo.getCondition());
                    dailyTaskRecord.setUpdateDate(new Date());
                    dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                    dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                    if (dailyTaskRecord.getGoal() > dailyTaskRecord.getProgress() + number) {
                        dailyTaskRecord.setProgress(dailyTaskRecord.getProgress() + number);
                        dailyTaskRecord.setUpdateDate(new Date());
                    } else {
                        dailyTaskRecord.setProgress(dailyTaskRecord.getGoal());
                        dailyTaskRecord.setUpdateDate(new Date());
                        dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_SUCCEED);
                        bo = true;
                    }
                    dailyTaskRecordService.insert(dailyTaskRecord);
                    continue;
                } else {
                    if (!DateUtil.isSameDate(new Date(), dailyTaskRecord.getUpdateDate())) {
                        dailyTaskRecord.setDailyTaskId(dailyTaskInfo.getId());
                        dailyTaskRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                        dailyTaskRecord.setGoal(dailyTaskInfo.getCondition());
                        dailyTaskRecord.setUpdateDate(new Date());
                        dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                        dailyTaskRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                    }
                    if (dailyTaskRecord.getStatus() == TaskConstant.TASK_STATUS_SUCCEED) {
                        continue;
                    }
                }
                if (dailyTaskRecord.getGoal() > dailyTaskRecord.getProgress() + number) {
                    dailyTaskRecord.setProgress(dailyTaskRecord.getProgress() + number);
                    dailyTaskRecord.setUpdateDate(new Date());
                } else {
                    dailyTaskRecord.setProgress(dailyTaskRecord.getGoal());
                    dailyTaskRecord.setUpdateDate(new Date());
                    dailyTaskRecord.setStatus(TaskConstant.TASK_STATUS_SUCCEED);
                    bo = true;
                }
                dailyTaskRecordService.update(dailyTaskRecord);
            }
        }
        if (bo) {
            Map<String, Object> object = new HashMap<>();
            List list = new ArrayList();
            list.add("task:1");
            object.put("models", list);
            object.put("type", "redTip");
            object.put("code", "0");
            chatMessageService.sendMessageToSomeBody(userId, JSON.toJSONString(object));
        }
    }

    /**
     * 完成支线任务
     *
     * @param taskId
     * @param userId
     */
    @PostMapping("/taskBranchFinish/{taskId}/{userId}")
    public void taskBranchFinish(@PathVariable("taskId") Integer taskId, @PathVariable("userId") String userId) {
//      {"requestType":"chat","type":"redTip"}
        taskBranchRecordService.taskBranchFinish(taskId, userId);
//        {"models":["chat:1","mail:0"],"type":"redTip"}
        Map<String, Object> object = new HashMap<>();
        List list = new ArrayList();
        list.add("taskBranch:1");
        object.put("models", list);
        object.put("type", "redTip");
        object.put("code", "0");
        chatMessageService.sendMessageToSomeBody(userId, JSON.toJSONString(object));
    }


    @PostMapping("/accomplishTaskCount/{userId}")
    public int accomplishTaskCount(@PathVariable("userId") String userId) {
        List<DailyTaskRecord> dailyTaskRecordList = dailyTaskRecordService.getDailyTaskRecordList(userId, TaskConstant.TASK_STATUS_SUCCEED, TaskConstant.TASK_RECEIVE_AWARD_NO);
        return dailyTaskRecordList.size();
    }

    @PostMapping("/accomplishTaskBranchCount/{userId}")
    public int accomplishTaskBranchCount(@PathVariable("userId") String userId) {
        List<TaskBranchRecord> taskBranchRecords = taskBranchRecordService.getTaskBranchRecordUndoneList(userId);
        for (int i = 0; i < taskBranchRecords.size(); i++) {
            TaskBranchRecord taskBranchRecord = taskBranchRecords.get(i);
            if (taskBranchRecord == null) {
                continue;
            }
            if (taskBranchRecord.getStatus() == TaskConstant.TASK_STATUS_SUCCEED) {
                return 1;
            }
        }
        return 0;
    }

    @PostMapping("/addDailyTaskInfo/{fileName}")
    public boolean addDailyTaskInfo(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean b = false;
        try {
            b = dailyTaskInfoService.batchImport(fileName, file);
        } catch (Exception e) {

        }
        return b;
    }

    @PostMapping("/addTaskBranch/{fileName}")
    public boolean addTaskBranch(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean b = false;
        try {
            b = taskBranchService.batchImport(fileName, file);
        } catch (Exception e) {

        }
        return b;
    }


}
