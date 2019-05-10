package com.tongzhu.friend.service;

import com.tongzhu.friend.model.DailyTaskInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
public interface DailyTaskInfoService {
    /**
     * 获取所有的日常任务
     * @return
     */
    List<DailyTaskInfo> getDailyTaskInfoList();

    /**
     * 获取当前用户任务活跃度
     * @param userId
     * @return
     */
    Integer getUserLiveness(String userId);

    /**
     * 通过id查询任务信息
     * @param id
     * @return
     */
    DailyTaskInfo getDailyTaskInfoById(Integer id);

    /**
     * 通过任务类型查询任务信息
     * @param type
     * @return
     */
    List<DailyTaskInfo> getDailyTaskInfoByType(Integer type);

    boolean batchImport(String fileName, MultipartFile file) throws Exception;
}
