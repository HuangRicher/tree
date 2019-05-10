package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.domain.Role;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceHystrix implements RoleService {

    @Override
    public Role findByRole(int roleId) {
        throw new RuntimeException("error");
    }

    @Override
    public UserRole getUserRoleByUserId(String userId) {
        throw new RuntimeException("error");
    }
}
