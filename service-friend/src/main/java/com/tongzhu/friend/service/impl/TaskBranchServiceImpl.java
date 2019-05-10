package com.tongzhu.friend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.mapper.TaskBranchMapper;
import com.tongzhu.friend.model.TaskBranch;
import com.tongzhu.friend.model.TaskBranchExample;
import com.tongzhu.friend.model.TaskBranchRecord;
import com.tongzhu.friend.service.RoleService;
import com.tongzhu.friend.service.TaskBranchRecordService;
import com.tongzhu.friend.service.TaskBranchService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/18 0018.
 */
@Service
public class TaskBranchServiceImpl implements TaskBranchService {

    @Autowired
    private TaskBranchMapper taskBranchMapper;

    @Autowired
    private TaskBranchRecordService taskBranchRecordService;

    @Autowired
    private RoleService roleService;


    @Override
    public void initializationTask(String userId, Integer level) {
        TaskBranchExample taskBranchExample = new TaskBranchExample();
        taskBranchExample.createCriteria().andFrontEqualTo(0).andLevelLessThanOrEqualTo(level);
        List<TaskBranch> taskBranchList = taskBranchMapper.selectByExample(taskBranchExample);
        for (TaskBranch taskBranch : taskBranchList) {
            TaskBranchRecord taskBranchRecord = taskBranchRecordService.getTaskBranchRecord(userId, taskBranch.getId());
            if (taskBranchRecord == null) {
                taskBranchRecord = new TaskBranchRecord();
                taskBranchRecord.setUserId(userId);
                taskBranchRecord.setProgress(TaskConstant.TASK_PROGRESS_INITIALIZE);
                taskBranchRecord.setReceiveAward(TaskConstant.TASK_RECEIVE_AWARD_NO);
                taskBranchRecord.setStatus(TaskConstant.TASK_STATUS_UNFINISHED);
                taskBranchRecord.setGoal(taskBranch.getCondition());
                taskBranchRecord.setTaskId(taskBranch.getId());
                taskBranchRecordService.insertSelective(taskBranchRecord);
            }
        }

    }

    @Override
    public TaskBranch getTaskBranch(Integer taskId) {
        return taskBranchMapper.selectByPrimaryKey(taskId);
    }

    @Override
    public TaskBranch getTaskBranchByFront(Integer front) {
        TaskBranchExample taskBranchExample = new TaskBranchExample();
        taskBranchExample.createCriteria().andFrontEqualTo(front);
        List<TaskBranch> taskBranchList = taskBranchMapper.selectByExample(taskBranchExample);
        if (taskBranchList.size() > 0) {
            return taskBranchList.get(0);
        }
        return null;
    }


    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<TaskBranch> taskBranchList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("格式错误");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }

        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            TaskBranch taskBranch = new TaskBranch();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String name = row.getCell(1).getStringCellValue();
            String description = row.getCell(2).getStringCellValue();
            Integer condition = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer number = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer level = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer front = new Double(row.getCell(6).getNumericCellValue()).intValue();
            String item = row.getCell(7).getStringCellValue();


            taskBranch.setId(id);
            taskBranch.setName(name);
            taskBranch.setDescription(description);
            taskBranch.setContent(condition);
            taskBranch.setCondition(number);
            taskBranch.setLevel(level);
            taskBranch.setFront(front);
            item = item.replace("[", "").replace("]", "");
            JSONObject jsonObject = JSON.parseObject(item);
            Integer jsonId = (Integer) jsonObject.get("id");
            Integer num = (Integer) jsonObject.get("num");
            Map<String, Integer> map = new HashMap<>();
            map.put(jsonId + "", num);
            Map award = new HashMap();
            award.put("prop", map);
            taskBranch.setAward(JSONObject.toJSONString(award));
            taskBranchList.add(taskBranch);
        }
        for (TaskBranch taskBranch : taskBranchList) {
            TaskBranch taskBranchById = taskBranchMapper.selectByPrimaryKey(taskBranch.getId());
            if (taskBranchById == null) {
                taskBranchMapper.insertSelective(taskBranch);
                System.out.println("增加成功" + taskBranch.getId());
            } else {
                taskBranchMapper.updateByPrimaryKeySelective(taskBranch);
                System.out.println("更新成功" + taskBranch.getId());
            }
        }
        return notNull;
    }


}
