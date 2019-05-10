package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.ArsenalInfo;
import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.service.ArsenalService;
import com.tongzhu.friend.service.PropService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ArsenalServiceHystrix implements ArsenalService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArsenalInfo getArsenalInfo(String goodsId) {
        throw new RuntimeException("error");
    }
}
