package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.UserRole;
import com.tongzhu.treehouse.service.impl.RoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = RoleServiceHystrix.class)
public interface RoleService {

    /**
     * 获取角色信息
     * @param userId
     * @return
     */
    @PostMapping(value = "/roleResource/getUserRoleByUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserRole getUserRoleByUserId(@PathVariable("userId") String userId);


    /**
     * 更新用户经验
     *
     * @param userId
     * @param exp    大于0，增加经验；小于0，减少经验
     */
    @PostMapping(value = "/roleResource/updateRoleExp/{userId}/{exp}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, Object> updateRoleExp(@PathVariable("userId") String userId, @PathVariable("exp") Integer exp);
}
