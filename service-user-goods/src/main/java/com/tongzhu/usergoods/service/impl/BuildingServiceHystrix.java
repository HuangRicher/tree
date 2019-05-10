package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.service.BuildingService;
import org.springframework.stereotype.Component;

@Component
public class BuildingServiceHystrix implements BuildingService {
    @Override
    public void sendMQMsg(String content) {

    }
}
