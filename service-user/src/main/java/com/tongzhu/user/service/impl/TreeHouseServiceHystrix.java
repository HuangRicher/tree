package com.tongzhu.user.service.impl;

import com.tongzhu.user.domain.TreeHouse;
import com.tongzhu.user.service.TreeHouseService;
import org.springframework.stereotype.Component;

@Component
public class TreeHouseServiceHystrix implements TreeHouseService {
    @Override
    public void add(TreeHouse treeHouse) {
        throw new RuntimeException("error");
    }

    @Override
    public TreeHouse findByUserId(String otherUserId) {
        throw new RuntimeException("error");
    }
}
