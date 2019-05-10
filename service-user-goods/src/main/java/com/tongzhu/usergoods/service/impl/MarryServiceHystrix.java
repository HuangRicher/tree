package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.domain.LoveTreeInfo;
import com.tongzhu.usergoods.service.MarryService;
import org.springframework.stereotype.Component;

@Component
public class MarryServiceHystrix implements MarryService {


    @Override
    public LoveTreeInfo getLoveTreeInfoObject(String loveTerrId,String userId) {
        return null;
    }

    @Override
    public int minusHappinessByUserId(String userId, Integer num,String ringId) {
        return 0;
    }
}
