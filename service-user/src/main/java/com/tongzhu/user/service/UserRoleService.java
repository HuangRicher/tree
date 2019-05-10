package com.tongzhu.user.service;

import com.tongzhu.user.domain.PropInfo;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.model.Role;
import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.UserRole;
import com.tongzhu.user.model.UserRoleTitle;

import java.util.List;
import java.util.Map;

public interface UserRoleService {

    void add(UserRole userRole);
    
    /**
     * 更新用户魅力值
     * @param userId
     * @param num 魅力值
     */
    void addcharmNum(String userId, int num);

    UserRole getByUserId(String userId);

    Role getByRoleId(int roleId);

    /**
     * 更新角色经验，可增加，可减少经验
     * @param userId
     * @param exp
     * @throws CheckException
     */
    Map<String,Object> updateUserRoleExp(String userId, long exp) throws CheckException;

    /**
     * 检查是否可更新角色经验，可增加，可减少经验
     * @param userId
     * @param exp
     * @throws CheckException
     */
    Map<String,Object> updateUserRoleExpCheck(String userId, long exp) throws CheckException;

    void computePropInfo(PropInfo propInfo, RoleLevelSetting setting,UserRole userRole);

    /**
     * 更新角色婚姻状态
     * @param userId
     * @param otherId	配偶Id
     */
	void updateMarryStatus(String userId, String otherId);

	/**
	 * 删除配偶信息
	 * @param userId
	 */
	void deleteMarrySpouse(String userId);

    void updateUserRoleTitle(String userId, Integer roleTitle);

    /**
     * 获取用户的称号列表
     * @param userId
     * @return
     */
    List<UserRoleTitle> findTitleListByUserId(String userId);

    void addRoleTitle(Integer titleId, String userId);

    List<UserRole> findByUserName(String roleName);

    void deleteByUserId(String s);

    void deleteMarryStatus(String userId);

    void initFirstUser();
}
