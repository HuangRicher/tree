package com.tongzhu.treehouse.model;

import java.io.Serializable;

public class WorkType implements Serializable {
    /**
     * 工种ID
     */
    private Integer id;

    /**
     * 工种名称
     */
    private String workTypeName;

    /**
     * 序号
     */
    private Integer order;

    /**
     * 解锁等级
     */
    private Integer unlockLevel;

    /**
     * tz_work_type
     */
    private static final long serialVersionUID = 1L;

    /**
     * 工种ID
     * @return id 工种ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 工种ID
     * @param id 工种ID
     */
    public void setId(Integer id) {
        this.id = id;
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