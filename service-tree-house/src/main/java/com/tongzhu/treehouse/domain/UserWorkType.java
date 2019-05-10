package com.tongzhu.treehouse.domain;

import java.io.Serializable;

public class UserWorkType implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 工种ID
     */
    private Integer workTypeId;

    /**
     * 工种名称
     */
    private String workTypeName;

    /**
     * 状态 0：锁住 1：解锁
     */
    private Integer status;

    /**
     * 序号
     */
    private Integer order;

    /**
     * 打工场景等级
     */
    private Integer level;

    /**
     * 解锁等级
     */
    private Integer unlockLevel;

    /**
     * tz_user_work_type
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 用户ID
     * @return user_id 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 工种ID
     * @return work_type_id 工种ID
     */
    public Integer getWorkTypeId() {
        return workTypeId;
    }

    /**
     * 工种ID
     * @param workTypeId 工种ID
     */
    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    /**
     * 工种名称
     * @return work_type_name 工种名称
     */
    public String getWorkTypeName() {
        return workTypeName;
    }

    /**
     * 工种名称
     * @param workTypeName 工种名称
     */
    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName == null ? null : workTypeName.trim();
    }

    /**
     * 状态 0：锁住 1：解锁
     * @return status_ 状态 0：锁住 1：解锁
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0：锁住 1：解锁
     * @param status 状态 0：锁住 1：解锁
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 序号
     * @return order_ 序号
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 序号
     * @param order 序号
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 打工场景等级
     * @return level_ 打工场景等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 打工场景等级
     * @param level 打工场景等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 解锁等级
     * @return unlock_level 解锁等级
     */
    public Integer getUnlockLevel() {
        return unlockLevel;
    }

    /**
     * 解锁等级
     * @param unlockLevel 解锁等级
     */
    public void setUnlockLevel(Integer unlockLevel) {
        this.unlockLevel = unlockLevel;
    }
}