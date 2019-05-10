package com.tongzhu.servicemessage.service;

import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.domain.User;
import com.tongzhu.servicemessage.service.impl.UserGoodsServiceHystrix;
import com.tongzhu.servicemessage.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserServiceHystrix.class)
public interface UserService {

    @PostMapping(value = "/userResource/updateForUserOnlineState/{userId}/{status}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateForUserOnlineState(@PathVariable("userId") String userId, @PathVariable("status") Integer status);


    @PostMapping(value = "/userResource/findByUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User findByUserId(@PathVariable("userId") String userId);


    @PostMapping(value = "/userMailSingleResource/userMailMessage/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int userMailMessage(@PathVariable("userId") String userId);

    @PostMapping(value = "/messageResource/userMessageRow/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int userMessageRow(@PathVariable("userId") String userId);

    @PostMapping(value = "/messageResource/userMessageRowAndType/{userId}/{type}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int userMessageRowAndType(@PathVariable("userId")String userId,@PathVariable("type") int type);


    /**
     * 技能是否可升级
     * @param userId
     * @return true 可升，false 不可升
     */
    @PostMapping(value = "/userSkillResource/checkSkillCanUpgrade/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Boolean checkSkillCanUpgrade(@PathVariable("userId") String userId);

}
