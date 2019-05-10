package com.tongzhu.friend.service;

import com.tongzhu.friend.model.TaskBranch;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/1/18 0018.
 */
public interface TaskBranchService {
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    /**
     * 初始化支线任务列表
     * @param userId
     * @param level
     */
    void initializationTask(String userId,Integer level);

    TaskBranch getTaskBranch(Integer taskId);

    TaskBranch getTaskBranchByFront(Integer front);



}
