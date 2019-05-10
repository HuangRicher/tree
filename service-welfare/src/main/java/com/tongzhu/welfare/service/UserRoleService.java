package com.tongzhu.welfare.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.RoleLevelSetting;
import com.tongzhu.welfare.domain.UserRole;
import com.tongzhu.welfare.service.impl.UserRoleServiceHystrix;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserRoleServiceHystrix.class)
public interface UserRoleService {

    @RequestMapping(value = "/roleResource/addcharmNum/{userId}/{num}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void addcharmNum(@PathVariable("userId") String userId, @PathVariable("num") Integer num);

    @RequestMapping(value = "/roleResource/updateMarryStatus/{userId}/{otherId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    void updateMarryStatus(@PathVariable("userId") String userId, @PathVariable("otherId") String otherId);
       @RequestMapping(value = "/roleResource/deleteMarryStatus/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteMarryStatus(@PathVariable("userId") String userId);


    @RequestMapping(value = "/roleResource/deleteMarrySpouse/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteMarrySpouse(@PathVariable("userId") String userId);
    
    @RequestMapping(value = "/roleResource/getUserRoleByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserRole getUserRoleByUserId(@PathVariable("userId") String userId);

    @RequestMapping(value = "/roleResource//updateRoleExp/{userId}/{exp}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, Object> updateRoleExp(@PathVariable("userId") String userId, @PathVariable("exp") Integer exp);
    
    @RequestMapping(value = "/roleResource/getUserRoleLevelSetting/{roleLevel}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    RoleLevelSetting getUserRoleLevelSetting(@PathVariable("roleLevel") Integer roleLevel);
}
