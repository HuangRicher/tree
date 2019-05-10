package com.tongzhu.welfare.service.impl;

import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.TreeHouse;
import com.tongzhu.welfare.service.TreeHouseService;

@Component
public class TreeHouseServiceHystrix implements TreeHouseService {


    @Override
    public TreeHouse findByUserId(String otherUserId) {
        throw new RuntimeException("error");
    }

	@Override
	public void updateTreeHouseLevel(String treeHouseId, int level) {
		throw new RuntimeException("error");
	}

	@Override
	public void unlockTreeHouseFlowerpot(String treeHouseUserId, Integer level) {
		throw new RuntimeException("error");
	}

	@Override
	public TreeHouse subByUserId(TreeHouse treeHouse) {
		throw new RuntimeException("error");
	}
}
