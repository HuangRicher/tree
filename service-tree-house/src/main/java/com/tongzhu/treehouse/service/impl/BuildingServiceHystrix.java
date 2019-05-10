package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.BuildingVo;
import com.tongzhu.treehouse.service.BuildingService;
import org.springframework.stereotype.Component;

@Component
public class BuildingServiceHystrix implements BuildingService {

    @Override
    public BuildingVo getTreeHouseByUserId(int treeGrade) {
        return null;
    }

    @Override
    public void sendMQMsg(String content) {

    }
}
