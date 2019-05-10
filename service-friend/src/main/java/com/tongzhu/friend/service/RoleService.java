package com.tongzhu.friend.service;

import com.tongzhu.friend.config.FeignConfig;
import com.tongzhu.friend.domain.UserRole;
import com.tongzhu.friend.service.impl.RoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = RoleServiceHystrix.class)
public interface RoleService {



	@RequestMapping(value = "/roleResource/getUserRoleByUserId/{userId}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	UserRole getUserRoleByUserId(@PathVariable("userId") String userId);

}
