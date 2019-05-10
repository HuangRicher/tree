package com.tongzhu.friend.controller;

import com.tongzhu.friend.service.DailyTaskInfoService;
import com.tongzhu.friend.service.TaskBranchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private DailyTaskInfoService dailyTaskInfoService;

    @Autowired
    private TaskBranchService taskBranchService;

    @ApiOperation(value = "增加日常任务数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addDailyTaskInfo")
    public boolean addDailyTaskInfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = dailyTaskInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加支线任务数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addTaskBranch")
    public boolean addTaskBranch(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = taskBranchService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }



}

