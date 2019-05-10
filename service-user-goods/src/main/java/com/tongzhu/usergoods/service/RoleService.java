package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.domain.Role;
import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.domain.UserRole;
import com.tongzhu.usergoods.service.impl.RoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = RoleServiceHystrix.class)
public interface RoleService {

	@RequestMapping(value = "/roleResource/findByRoleId/{roleId}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	Role findByRole(@PathVariable("roleId") int roleId);


	@RequestMapping(value = "/roleResource/getUserRoleByUserId/{userId}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	UserRole getUserRoleByUserId(@PathVariable("userId") String userId);

}
