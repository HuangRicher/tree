package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.domain.TreeHouse;
import com.tongzhu.fishing.service.TreeHouseService;
import org.springframework.stereotype.Component;

@Component
public class TreeHouseServiceHystrix implements TreeHouseService {
    @Override
    public TreeHouse findByUserId(String userId) {
        throw new RuntimeException("error");
    }
}
