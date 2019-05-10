package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.UserPet;
import com.tongzhu.treehouse.service.PetService;
import org.springframework.stereotype.Component;

@Component
public class PetServiceHystrix implements PetService {
    @Override
    public UserPet getMyPetFollowed(String userId) {
        return null;
    }
}
