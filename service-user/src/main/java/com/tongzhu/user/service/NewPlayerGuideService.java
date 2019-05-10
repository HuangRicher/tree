package com.tongzhu.user.service;

import com.tongzhu.user.model.NewPlayerGuide;

public interface NewPlayerGuideService {

    /**
     * 查看用户新手引导情况
     * @param userId
     * @return
     */
    NewPlayerGuide getByUserId(String userId);

    /**
     * 提交新手引导完成的任务
     * @param userId
     * @param taskNum
     */
    void doTask(String userId, Integer taskNum);

}
