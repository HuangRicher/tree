package com.tongzhu.fishing.service;

import com.tongzhu.fishing.config.FeignConfig;
import com.tongzhu.fishing.domain.User;
import com.tongzhu.fishing.domain.UserMessage;
import com.tongzhu.fishing.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserServiceHystrix.class)
public interface UserService {

	@PostMapping(value = "/userResource/findByUserId/{userId}",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	User findByUserId(@PathVariable("userId") String userId);

	@PostMapping(value = "/userResource/addUserMessage",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	int addUserMessage(@RequestBody UserMessage userMessage);

}
