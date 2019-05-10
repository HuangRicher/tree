package com.tongzhu.treehouse.po;

import java.io.Serializable;

public class WorkerPO implements Serializable {

    private String userId;

    private String workerId;

    private int protectHours;//保护小时数

    private String id;

    private int pageNum;

    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public int getProtectHours() {
        return protectHours;
    }

    public void setProtectHours(int protectHours) {
        this.protectHours = protectHours;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
