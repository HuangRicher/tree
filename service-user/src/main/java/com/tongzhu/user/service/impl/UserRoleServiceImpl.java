package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.tongzhu.constant.RabbitMQConstant;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.constant.RanklingListConstant;
import com.tongzhu.user.constant.RoleTitleConstant;
import com.tongzhu.user.domain.ArsenalInfo;
import com.tongzhu.user.domain.PropInfo;
import com.tongzhu.user.mapper.RoleMapper;
import com.tongzhu.user.mapper.UserRoleMapper;
import com.tongzhu.user.mapper.UserRoleTitleMapper;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.*;
import com.tongzhu.user.util.UserRoleUpgradeUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserCrunchiesService userCrunchiesService;

    @Autowired
    private UserRoleTitleMapper userRoleTitleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private UserGoodsService userGoodsService;




    @Override
    public void add(UserRole userRole) {
        userRoleMapper.insertSelective(userRole);
    }

    @Override
    public UserRole getByUserId(String userId) {
        return userRoleMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Role getByRoleId(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }


    @Transactional
    @Override
    public synchronized Map<String, Object> updateUserRoleExp(String userId, long exp) throws CheckException {
        Map<String, Object> result = new HashMap<>();
        UserRole userRole = this.getByUserId(userId);
        if (userRole != null && exp < 0 && userRole.getExperience() + exp > 0) {
            UserRole role = new UserRole();
            role.setUserId(userId);
            role.setExperience(userRole.getExperience() + exp);
            userRoleMapper.updateByPrimaryKeySelective(role);
        } else if (userRole != null && exp >= 0) {
            long oldExp = userRole.getExperience();
            UserRole role = new UserRole();
            role.setUserId(userId);
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            if (setting != null) {
                if (oldExp + exp >= setting.getExp()) {
                    long currentExp=oldExp + exp;
                    while(currentExp >= setting.getExp()){
                        petService.udpatePetSpillExp(userId,setting.getNextLevel());
                        role.setRoleLevel(setting.getNextLevel()>0?setting.getNextLevel(): RoleConstant.MAX_ROLE_LEVEL);
                        role.setExperience(oldExp + exp - setting.getExp());
                        User user=new User();
                        user.setUserId(role.getUserId());
                        user.setGrade(role.getRoleLevel());
                        userService.updateUserByUserId(user);
                        //武器领悟等级:2,7,12,17,22,27,32,37,42,47
                        //技能领悟等级:3，8，13,18，23,28,33,38,43,48
                        List<Skill> skillList = userSkillService.findUserNoneSkill(role.getUserId());
                        Integer skillId= UserRoleUpgradeUtil.getSkill(role.getRoleLevel(),skillList);
                        if (null!=skillId) {
                            UserSkill userSkill = new UserSkill();
                            userSkill.setId(UUIDUtil.generateUUID());
                            userSkill.setUserId(role.getUserId());
                            userSkill.setActiveStatus(1);
                            userSkill.setLevel(1);
                            userSkill.setSkillId(skillId);
                            userSkillService.add(userSkill);
                            result.put("skillId", skillId);
                        }
                        int profession=1;
                        if(1==userRole.getRoleId() || 2==userRole.getRoleId()) profession=1;
                        if(3==userRole.getRoleId() || 4==userRole.getRoleId()) profession=2;
                        if(5==userRole.getRoleId() || 6==userRole.getRoleId()) profession=3;
                        if(7==userRole.getRoleId() || 8==userRole.getRoleId()) profession=4;
                        List<ArsenalInfo> weaponList= userGoodsService.findUserAllNotGetWeaponList(userId,profession);
                        String gId=UserRoleUpgradeUtil.getWeapon(userRole.getRoleId(),role.getRoleLevel(),weaponList);
                        if(StringUtils.isNotBlank(gId)){
                            Map<String,Integer> weap=new HashMap<>();
                            weap.put(gId,3);
                            List<Map> map=userGoodsService.addEquipAndWeaponList(userId,weap);
                            if(!CollectionUtils.isEmpty(map)){
                                result.put("weapon",map.get(0));
                            }
                        }
                        RoleLevelSetting nextSetting =null;
                        if(setting.getNextLevel()!=null && setting.getNextLevel()>0){
                            nextSetting = roleLevelSettingService.getByLevel(setting.getNextLevel());
                            result.put("currentExp", oldExp + exp - setting.getExp());
                            result.put("roleLevel", setting.getNextLevel());
                            result.put("fullExp", nextSetting.getExp());
                        }else{
                            nextSetting = setting;
                            result.put("currentExp", oldExp);
                            result.put("roleLevel", setting.getLevel());
                            result.put("fullExp", oldExp);
                        }

                        //增加角色等级大于25级开启教堂建筑以及允许结婚的规则
                        //if (userRole.getRoleLevel() == 25) {
                        //   buildingService.updateBuildingUser(userId);
                        //}

                        PropInfo propInfoOld = new PropInfo();
                        PropInfo propInfo = new PropInfo();
                        this.computePropInfo(propInfoOld,setting,userRole);
                        this.computePropInfo(propInfo,nextSetting,userRole);
                        userGoodsService.addUserAdornEquipAttribute(userId,propInfo.getVitality()-propInfoOld.getVitality(),
                                propInfo.getAttack()-propInfoOld.getAttack(),
                                propInfo.getSpellAttacks()-propInfoOld.getSpellAttacks(),
                                propInfo.getPdef()-propInfoOld.getPdef(),
                                propInfo.getMagdef()-propInfoOld.getMagdef(),
                                propInfo.getCrit()-propInfoOld.getCrit(),
                                propInfo.getDodge()-propInfoOld.getDodge(),
                                propInfo.getHitRate()-propInfoOld.getHitRate(),
                                propInfo.getDefenseCrit()-propInfoOld.getDefenseCrit());

                        if(role.getRoleLevel()<RoleConstant.MAX_ROLE_LEVEL){
                            currentExp=oldExp + exp - setting.getExp();
                            setting = roleLevelSettingService.getByLevel(setting.getNextLevel());
                        }
                    }
                } else {
                    role.setExperience(oldExp + exp);
                    result.put("currentExp", oldExp + exp);
                    result.put("roleLevel", userRole.getRoleLevel());
                    result.put("fullExp", setting.getExp());
                }
            }
            userRoleMapper.updateByPrimaryKeySelective(role);
            //发送技能升级提示红点
            Map<String,Object> map=new HashMap<>();
            map.put(RabbitMQConstant.USER_ID,userId);
            map.put(RabbitMQConstant.TYPE, RabbitMQConstant.TYPE_EXP);
            buildingService.sendMQMsg(JSON.toJSONString(map));
        } else {
            throw new CheckException("经验不足！");
        }
        System.out.println(result);
        return result;
    }

    @Override
    public Map<String, Object> updateUserRoleExpCheck(String userId, long exp) throws CheckException {
        Map<String, Object> result = new HashMap<>();
        UserRole userRole = this.getByUserId(userId);
        if (userRole != null && exp < 0 && userRole.getExperience() + exp > 0) {
            UserRole role = new UserRole();
            role.setUserId(userId);
            role.setExperience(userRole.getExperience() + exp);
        } else if (userRole != null && exp >= 0) {
            long oldExp = userRole.getExperience();
            UserRole role = new UserRole();
            role.setUserId(userId);
            RoleLevelSetting setting = roleLevelSettingService.getByLevel(userRole.getRoleLevel());
            if (setting != null) {
                if (oldExp + exp >= setting.getExp()) {
                    role.setRoleLevel(setting.getNextLevel());
                    role.setExperience(oldExp + exp - setting.getExp());
                    if(setting.getNextLevel()!=null && setting.getNextLevel()>0){
                        RoleLevelSetting nextSetting = roleLevelSettingService.getByLevel(setting.getNextLevel());
                        result.put("currentExp", oldExp + exp - setting.getExp());
                        result.put("roleLevel", setting.getNextLevel());
                        result.put("fullExp", nextSetting.getExp());
                    }else{
                        result.put("currentExp", oldExp);
                        result.put("roleLevel", setting.getLevel());
                        result.put("fullExp", oldExp);
                    }
                } else {
                    role.setExperience(oldExp + exp);
                    result.put("currentExp", oldExp + exp);
                    result.put("roleLevel", userRole.getRoleLevel());
                    result.put("fullExp", setting.getExp());
                }
            }
        } else {
            throw new CheckException("经验不足！");
        }
        return result;
    }

    @Override
    public void computePropInfo(PropInfo propInfo, RoleLevelSetting setting, UserRole userRole) {
        switch (userRole.getRoleId()) {
            case 1:
            case 2:
                propInfo.setVitality((double) setting.getWsHp());//生命属性加成
                propInfo.setAttack((double) setting.getWsPhAtk());//物理攻击属性加成
                propInfo.setSpellAttacks((double) setting.getWsMfAtk());//法术攻击属性加成
                propInfo.setPdef((double) setting.getWsPhDef());//物理防御属性加成
                propInfo.setMagdef((double) setting.getWsMfDef());//法术防御属性加成
                propInfo.setCrit((double) setting.getWsCritical());//暴击属性加成
                propInfo.setDodge((double) setting.getWsMiss());//闪避属性加成
                propInfo.setHitRate((double) setting.getWsAccuracy());//命中属性加成
                propInfo.setDefenseCrit((double) setting.getWsDcritical());//抗暴击属性加成
                break;
            case 3:
            case 4:
                propInfo.setVitality((double) setting.getCkHp());//生命属性加成
                propInfo.setAttack((double) setting.getCkPhAtk());//物理攻击属性加成
                propInfo.setSpellAttacks((double) setting.getCkMfAtk());//法术攻击属性加成
                propInfo.setPdef((double) setting.getCkPhDef());//物理防御属性加成
                propInfo.setMagdef((double) setting.getCkMfDef());//法术防御属性加成
                propInfo.setCrit((double) setting.getCkCritical());//暴击属性加成
                propInfo.setDodge((double) setting.getCkMiss());//闪避属性加成
                propInfo.setHitRate((double) setting.getCkAccuracy());//命中属性加成
                propInfo.setDefenseCrit((double) setting.getCkDcritical());//抗暴击属性加成
                break;
            case 5:
            case 6:
                propInfo.setVitality((double) setting.getGjHp());//生命属性加成
                propInfo.setAttack((double) setting.getGjPhAtk());//物理攻击属性加成
                propInfo.setSpellAttacks((double) setting.getGjMfAtk());//法术攻击属性加成
                propInfo.setPdef((double) setting.getGjPhDef());//物理防御属性加成
                propInfo.setMagdef((double) setting.getGjMfDef());//法术防御属性加成
                propInfo.setCrit((double) setting.getGjCritical());//暴击属性加成
                propInfo.setDodge((double) setting.getGjMiss());//闪避属性加成
                propInfo.setHitRate((double) setting.getGjAccuracy());//命中属性加成
                propInfo.setDefenseCrit((double) setting.getGjDcritical());//抗暴击属性加成
                break;
            case 7:
            case 8:
                propInfo.setVitality((double) setting.getFsHp());//生命属性加成
                propInfo.setAttack((double) setting.getFsPhAtk());//物理攻击属性加成
                propInfo.setSpellAttacks((double) setting.getFsMfAtk());//法术攻击属性加成
                propInfo.setPdef((double) setting.getFsPhDef());//物理防御属性加成
                propInfo.setMagdef((double) setting.getFsMfDef());//法术防御属性加成
                propInfo.setCrit((double) setting.getFsCritical());//暴击属性加成
                propInfo.setDodge((double) setting.getFsMiss());//闪避属性加成
                propInfo.setHitRate((double) setting.getFsAccuracy());//命中属性加成
                propInfo.setDefenseCrit((double) setting.getFsDcritical());//抗暴击属性加成
                break;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addcharmNum(String userId, int num) {
        UserRole userRole = this.getByUserId(userId);
        userCrunchiesService.updateCrunchies(userId, RanklingListConstant.MONTH_CHARM_NUM_TYPE, userRole.getCharmNum()+ num);
        userRole.setCharmNum(userRole.getCharmNum() + num);
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMarryStatus(String userId, String otherId) {
        UserRole userRole = this.getByUserId(userId);
        userRole.setSpouse(otherId);
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMarrySpouse(String userId) {
        UserRole userRole = this.getByUserId(userId);
        userRole.setSpouse(null);
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public void updateUserRoleTitle(String userId, Integer roleTitle) {
        UserRole userRole = this.getByUserId(userId);
        userRole.setRoleTitle(roleTitle);
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public List<UserRoleTitle> findTitleListByUserId(String userId) {
        UserRoleTitleExample example = new UserRoleTitleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleTitleMapper.selectByExample(example);
    }

    @Override
    public List<UserRole> findByUserName(String roleName) {
        UserRoleExample example=new UserRoleExample();
        example.createCriteria().andUserNameEqualTo(roleName);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public void addRoleTitle(Integer titleId, String userId) {
        if(RoleTitleConstant.TITLE_1.equals(titleId) ||
                RoleTitleConstant.TITLE_2.equals(titleId) ||
                RoleTitleConstant.TITLE_3.equals(titleId)){
            UserRoleTitle title=new UserRoleTitle();
            title.setUserId(userId);
            title.setRoleTitle(titleId);
            userRoleTitleMapper.insert(title);
        }
        if(RoleTitleConstant.TITLE_4.equals(titleId) ||RoleTitleConstant.TITLE_5.equals(titleId)){
            UserRoleTitleExample example=new UserRoleTitleExample();
            example.createCriteria().andRoleTitleEqualTo(titleId);
            userRoleTitleMapper.deleteByExample(example);

            UserRoleExample roleExample=new UserRoleExample();
            roleExample.createCriteria().andRoleTitleEqualTo(titleId);
            UserRole role=new UserRole();
            role.setRoleTitle(RoleTitleConstant.TITLE_3);
            userRoleMapper.updateByExampleSelective(role,roleExample);

            UserRole role1=new UserRole();
            role1.setUserId(userId);
            role1.setRoleTitle(titleId);
            userRoleMapper.updateByPrimaryKeySelective(role1);

            UserRoleTitle title=new UserRoleTitle();
            title.setUserId(userId);
            title.setRoleTitle(titleId);
            userRoleTitleMapper.insert(title);
        }
    }

    @Override
    public void deleteByUserId(String s) {
        UserRoleExample example=new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(s);
        userRoleMapper.deleteByExample(example);
    }

    @Override
    public void deleteMarryStatus(String userId) {
        UserRole userRole = this.getByUserId(userId);
        userRole.setSpouse("");
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public void initFirstUser() {
        UserRoleExample example=new UserRoleExample();
        example.createCriteria().andUserNameEqualTo("七七小七七");
        List<UserRole> list=userRoleMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            initUser();
        }
    }


    private void initUser(){
        User byUserId =userService.addUserId("swsd7777",2, 8);
        userService.addUserRole(byUserId.getUserId(),"七七小七七",8, 2,"swsd7777" );
    }
}
