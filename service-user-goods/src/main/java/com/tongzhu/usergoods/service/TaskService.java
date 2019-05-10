package com.tongzhu.usergoods.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "${feign-service.friend}")
public interface TaskService {

    @RequestMapping(value = "/taskResource/taskProgress/{type}/{number}/{userId}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void taskProgress(@PathVariable("type") Integer type, @PathVariable("number") Integer number, @PathVariable("userId") String userId);

    @RequestMapping(value = "/taskResource/taskBranchFinish/{taskId}/{userId}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void taskBranchFinish(@PathVariable("taskId") Integer taskId, @PathVariable("userId") String userId);

    @RequestMapping(value = "/taskResource/addDailyTaskInfo/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addDailyTaskInfo(@PathVariable("fileName") String fileName,@RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/taskResource/addTaskBranch/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addTaskBranch(@PathVariable("fileName") String fileName,@RequestPart(value = "file") MultipartFile file);

}
