package com.tongzhu.user.service;

import com.tongzhu.user.domain.PetInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/12/24 0024.
 */
@FeignClient(value = "${feign-service.user-goods}")
public interface PetService {

    @RequestMapping(value = "/petResource/getPetInfoList/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<PetInfo> getPetInfoList(@PathVariable("userId") String userId);

    /**
     * 更新宠物溢出经验
     * @param userId
     * @return
     */
    @RequestMapping(value = "/petResource/udpatePetSpillExp/{userId}/{level}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void udpatePetSpillExp(@PathVariable("userId") String userId,@PathVariable("level")Integer level);

}
