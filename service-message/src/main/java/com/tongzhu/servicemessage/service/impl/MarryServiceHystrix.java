package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.domain.MarryLogVo;
import com.tongzhu.servicemessage.service.MarryService;
import org.springframework.stereotype.Component;

@Component
public class MarryServiceHystrix implements MarryService {
    @Override
    public void leaveWedding(String userId, String marryId) {

    }

    @Override
    public MarryLogVo getWeddingInfo(String marryId) {
        return null;
    }
}
