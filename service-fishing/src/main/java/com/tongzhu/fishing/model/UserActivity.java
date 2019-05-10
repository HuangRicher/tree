package com.tongzhu.fishing.model;

import java.io.Serializable;
import java.util.Date;

public class UserActivity  implements Serializable {
    private Integer id;

    private String activitiesName;

    private Date startTime;

    private Date endTime;

    private String activitiesPicture;

    private String activitiesIntroduction;

    private String fallingPosition;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivitiesName() {
        return activitiesName;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName == null ? null : activitiesName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivitiesPicture() {
        return activitiesPicture;
    }

    public void setActivitiesPicture(String activitiesPicture) {
        this.activitiesPicture = activitiesPicture == null ? null : activitiesPicture.trim();
    }

    public String getActivitiesIntroduction() {
        return activitiesIntroduction;
    }

    public void setActivitiesIntroduction(String activitiesIntroduction) {
        this.activitiesIntroduction = activitiesIntroduction == null ? null : activitiesIntroduction.trim();
    }

    public String getFallingPosition() {
        return fallingPosition;
    }

    public void setFallingPosition(String fallingPosition) {
        this.fallingPosition = fallingPosition == null ? null : fallingPosition.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}