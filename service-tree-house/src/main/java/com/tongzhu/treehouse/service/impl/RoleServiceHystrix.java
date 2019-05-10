package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.UserRole;
import com.tongzhu.treehouse.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoleServiceHystrix  implements RoleService {
    @Override
    public UserRole getUserRoleByUserId(String userId) {
        return null;
    }

    @Override
    public Map<String, Object> updateRoleExp(String userId, Integer exp) {
        return null;
    }
}
