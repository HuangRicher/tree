package com.tongzhu.user.model;

public class Skill {
    private Integer id;

    private String skillName;

    private String description;

    private Integer coolingCircle;

    private Integer roleLevel;

    private Float apperceptionProbability;

    private Integer type;

    private Integer priority;

    private String buffId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName == null ? null : skillName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCoolingCircle() {
        return coolingCircle;
    }

    public void setCoolingCircle(Integer coolingCircle) {
        this.coolingCircle = coolingCircle;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Float getApperceptionProbability() {
        return apperceptionProbability;
    }

    public void setApperceptionProbability(Float apperceptionProbability) {
        this.apperceptionProbability = apperceptionProbability;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getBuffId() {
        return buffId;
    }

    public void setBuffId(String buffId) {
        this.buffId = buffId == null ? null : buffId.trim();
    }
}