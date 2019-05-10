package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.EquipmentInfo;
import com.tongzhu.friend.domain.PropInfo;
import com.tongzhu.friend.service.EquipmentService;
import com.tongzhu.friend.service.PropService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EquipmentServiceHystrix implements EquipmentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EquipmentInfo getEquipmentInfo(String goodsId) {
        throw new RuntimeException("error");
    }
}
