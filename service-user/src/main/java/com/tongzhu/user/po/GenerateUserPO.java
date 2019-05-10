package com.tongzhu.user.po;

import com.tongzhu.user.domain.UserGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel
public class GenerateUserPO {

    @ApiModelProperty(value = "用户Token")
    private String token;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色等级")
    private Integer roleLevel;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "武器")
    private List<String> weaponList;

    @ApiModelProperty(value = "道具")
    private List<UserGoods> propList;

    @ApiModelProperty(value = "装备")
    private List<String> equipmentList;

    @ApiModelProperty(value = "技能")
    private List<Integer> skillIds;

    @ApiModelProperty(value = "树屋环境值")
    private Integer environmentCount;

    @ApiModelProperty(value = "树屋娱乐值")
    private Integer amusementCount;

    @ApiModelProperty(value = "树屋人气值")
    private Integer humanCount;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<String> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<String> weaponList) {
        this.weaponList = weaponList;
    }

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    public List<UserGoods> getPropList() {
        return propList;
    }

    public void setPropList(List<UserGoods> propList) {
        this.propList = propList;
    }

    public List<String> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<String> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Integer getEnvironmentCount() {
        return environmentCount;
    }

    public void setEnvironmentCount(Integer environmentCount) {
        this.environmentCount = environmentCount;
    }

    public Integer getAmusementCount() {
        return amusementCount;
    }

    public void setAmusementCount(Integer amusementCount) {
        this.amusementCount = amusementCount;
    }

    public Integer getHumanCount() {
        return humanCount;
    }

    public void setHumanCount(Integer humanCount) {
        this.humanCount = humanCount;
    }

}
