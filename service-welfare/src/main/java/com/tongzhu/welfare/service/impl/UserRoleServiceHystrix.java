package com.tongzhu.welfare.service.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.RoleLevelSetting;
import com.tongzhu.welfare.domain.UserRole;
import com.tongzhu.welfare.service.UserRoleService;

@Component
public class UserRoleServiceHystrix implements UserRoleService {

	@Override
	public void addcharmNum(String userId, Integer num) {
		throw new RuntimeException("error");		
	}

	@Override
	public UserRole getUserRoleByUserId(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public void updateMarryStatus(String userId, String otherId) {
		throw new RuntimeException("error");
	}

	@Override
	public void deleteMarryStatus(String userId) {

	}

	@Override
	public Map<String, Object> updateRoleExp(String userId, Integer exp) {
		throw new RuntimeException("error");
	}

	@Override
	public void deleteMarrySpouse(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public RoleLevelSetting getUserRoleLevelSetting(Integer roleLevel) {
		throw new RuntimeException("error");
	}
}
