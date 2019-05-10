package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceHystrix implements RoleService {


    @Override
    public UserRole getUserRoleByUserId(String userId) {
        throw new RuntimeException("error");
    }
}
