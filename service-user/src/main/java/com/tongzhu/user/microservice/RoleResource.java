package com.tongzhu.user.microservice;

import com.tongzhu.user.constant.RoleTitleConstant;
import com.tongzhu.user.model.Role;
import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.UserRole;
import com.tongzhu.user.service.RoleLevelSettingService;
import com.tongzhu.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/roleResource")
public class RoleResource {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;




    @PostMapping("/findByRoleId/{roleId}")
    public Role findByRole(@PathVariable("roleId") int roleId) {
        return userRoleService.getByRoleId(roleId);
    }

    /**
     * 更新用户经验
     * @param userId
     * @param exp    大于0，增加经验；小于0，减少经验
     */
    @PostMapping("/updateRoleExp/{userId}/{exp}")
    public Map<String, Object> updateRoleExp(@PathVariable("userId") String userId, @PathVariable("exp") Integer exp) {
        return userRoleService.updateUserRoleExp(userId, exp);
    }

    /**
     * 更新用户魅力值
     * @param userId
     * @param num    魅力值
     */
    @PostMapping("/addcharmNum/{userId}/{num}")
    public void addcharmNum(@PathVariable("userId") String userId, @PathVariable("num") Integer num) {
        userRoleService.addcharmNum(userId, num);
    }
    
    /**
     * 更新用户配偶信息
     * @param userId
     * @param otherId    配偶ID
     */
    @PostMapping("/updateMarryStatus/{userId}/{otherId}")
    public void updateMarryStatus(@PathVariable("userId") String userId, @PathVariable("otherId") String otherId) {
        userRoleService.updateMarryStatus(userId, otherId);
    }
      /**
     * 删除用户配偶信息
     * @param userId
     */
    @PostMapping("/deleteMarryStatus/{userId}")
    public void deleteMarryStatus(@PathVariable("userId") String userId) {
        userRoleService.deleteMarryStatus(userId);
    }

    /**
     * 删除用户配偶信息
     * @param userId
     */
    @PostMapping("/deleteMarrySpouse/{userId}")
    public void deleteMarrySpouse(@PathVariable("userId") String userId) {
        userRoleService.deleteMarrySpouse(userId);
    }

    /**
     * 获取角色信息
     * @param userId
     * @return
     */
    @PostMapping("/getUserRoleByUserId/{userId}")
    public UserRole getUserRoleByUserId(@PathVariable("userId") String userId) {
        UserRole byUserId = userRoleService.getByUserId(userId);
        return byUserId;
    }

    /**
     * 获得【全服战斗力排行第1】称号
     * @param userId
     * @return
     */
    @PostMapping("/getTopPower/{userId}")
    public void getTopPower(@PathVariable("userId") String userId) {
        userRoleService.addRoleTitle(RoleTitleConstant.TITLE_4,userId);
    }

    /**
     * 获得【全服魅力值排行第1】称号
     * @param userId
     * @return
     */
    @PostMapping("/getFascinationTop/{userId}")
    public void getFascinationTop(@PathVariable("userId") String userId) {
        userRoleService.addRoleTitle(RoleTitleConstant.TITLE_5,userId);
    }

    /**
     * 获取角色等级配置
     * @param roleLevel
     * @return
     */
    @PostMapping("/getUserRoleLevelSetting/{roleLevel}")
    public RoleLevelSetting getUserRoleLevelSetting(@PathVariable("roleLevel") Integer roleLevel) {
        return roleLevelSettingService.getByLevel(roleLevel);
    }
}
