package com.tongzhu.usergoods.model;

import java.util.Date;

public class UserPet {
    private String id;

    private String userId;

    private String petId;

    private Date expirationTime;

    private Integer follow;

    private Integer exp;

    private Date createDate;

    private String petName;

    private Date updateDateName;

    private Integer spillExp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId == null ? null : petId.trim();
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    public Date getUpdateDateName() {
        return updateDateName;
    }

    public void setUpdateDateName(Date updateDateName) {
        this.updateDateName = updateDateName;
    }

    public Integer getSpillExp() {
        return spillExp;
    }

    public void setSpillExp(Integer spillExp) {
        this.spillExp = spillExp;
    }
}