package com.tongzhu.welfare.service;

import com.tongzhu.welfare.domain.PetInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tongzhu.welfare.domain.UserPet;

/**
 * Created by Administrator on 2018/12/24 0024.
 */
@FeignClient(value = "${feign-service.user-goods}")
public interface PetService {

    /**
     * 查询跟随的宠物
     * @param userId
     * @return
     */
    @RequestMapping(value = "/petResource/getMyPetFollowed/{userId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserPet getMyPetFollowed(@PathVariable("userId") String userId);

    /**
     * 查询宠物信息
     * @return
     */
    @RequestMapping(value = "/petResource/getPetInfoByPetId/{petId}",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PetInfo getPetInfoByPetId(@PathVariable("petId") String petId);


}
