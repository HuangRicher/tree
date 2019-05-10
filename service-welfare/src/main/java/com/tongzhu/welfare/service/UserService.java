package com.tongzhu.welfare.service;

import com.tongzhu.welfare.domain.NewPlayerGuide;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.welfare.config.FeignConfig;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.domain.UserDetail;
import com.tongzhu.welfare.service.impl.UserServiceHystrix;

@FeignClient(value = "${feign-service.user}",configuration = FeignConfig.class,fallback = UserServiceHystrix.class)
public interface UserService {

    @RequestMapping(value = "/userResource/getUserDetail/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserDetail getUserDetail(@PathVariable("userId")String userId);



    @RequestMapping(value = "/userResource/findByUserId/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User findByUserId(@PathVariable("userId")String userId);


    /**
     * 更新月榜信息
     * @param userId
     * @param type   1 战斗力月榜 2 魅力月榜 3 幸福值月榜
     * @param recordValues 当前记录值 type 1 当前用户战斗力 2 当前用户魅力值 3 当前用户幸福值
     */
    @RequestMapping(value = "/userResource/updateCrunchies/{userId}/{type}/{recordValues}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateCrunchies(@PathVariable("userId") String userId,@PathVariable("type")Integer type,@PathVariable("recordValues")Integer recordValues);

    /**
     * 技能可升级红点提示
     * @param userId
     */
    @PostMapping(value = "/userSkillResource/sendSkillRedTip/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendSkillRedTip(@PathVariable("userId") String userId);

    @PostMapping("/userResource/getNewPlayerGuide/{userId}")
    NewPlayerGuide getNewPlayerGuide(@PathVariable("userId") String userId);
}
