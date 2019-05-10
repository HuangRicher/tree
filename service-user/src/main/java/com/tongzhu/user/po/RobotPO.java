package com.tongzhu.user.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RobotPO implements Serializable {

    private String userName;
    private Integer roleId;
    private Integer roleLevel;
    private String roleName;
    private String openId;
    private List<SkillPO> skillList;
    private List<String> weaponIds;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SkillPO> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillPO> skillList) {
        this.skillList = skillList;
    }

    public List<String> getWeaponIds() {
        return weaponIds;
    }

    public void setWeaponIds(List<String> weaponIds) {
        this.weaponIds = weaponIds;
    }
}
