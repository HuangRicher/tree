package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.service.PropService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PropServiceHystrix implements PropService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PropInfo getPropInfo(String goodsId) {
        throw new RuntimeException("error");
    }
}
