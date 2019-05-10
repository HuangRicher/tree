package com.tongzhu.user.model;

public class FightCopy {
    private Integer id;

    private String copyName;

    private Integer commonCount;

    private Integer vipCount;

    private Integer minRoleLevel;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName == null ? null : copyName.trim();
    }

    public Integer getCommonCount() {
        return commonCount;
    }

    public void setCommonCount(Integer commonCount) {
        this.commonCount = commonCount;
    }

    public Integer getVipCount() {
        return vipCount;
    }

    public void setVipCount(Integer vipCount) {
        this.vipCount = vipCount;
    }

    public Integer getMinRoleLevel() {
        return minRoleLevel;
    }

    public void setMinRoleLevel(Integer minRoleLevel) {
        this.minRoleLevel = minRoleLevel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}