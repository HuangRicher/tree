package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.UserPet;
import com.tongzhu.treehouse.service.impl.PetServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.user-goods}",configuration = FeignConfig.class,fallback = PetServiceHystrix.class)
public interface PetService {

    @PostMapping(value = "/petResource/getMyPetFollowed/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserPet getMyPetFollowed(@PathVariable("userId") String userId);
}
