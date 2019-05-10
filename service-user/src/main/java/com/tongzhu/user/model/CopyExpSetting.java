package com.tongzhu.user.model;

import java.io.Serializable;

public class CopyExpSetting implements Serializable {
    private String id;

    private Integer roleLevel;

    private Integer copyExp;

    private Integer copyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getCopyExp() {
        return copyExp;
    }

    public void setCopyExp(Integer copyExp) {
        this.copyExp = copyExp;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }
}