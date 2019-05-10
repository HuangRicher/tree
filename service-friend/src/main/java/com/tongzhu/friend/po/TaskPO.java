package com.tongzhu.friend.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
public class TaskPO implements Serializable {
    private String id;

    private String userId;

    private Integer dailyTaskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDailyTaskId() {
        return dailyTaskId;
    }

    public void setDailyTaskId(Integer dailyTaskId) {
        this.dailyTaskId = dailyTaskId;
    }
}
