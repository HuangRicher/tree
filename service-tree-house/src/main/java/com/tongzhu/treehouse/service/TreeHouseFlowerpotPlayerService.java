package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseFlowerpotPlayer;

public interface TreeHouseFlowerpotPlayerService {

    void add(String plantId,String userId);

    TreeHouseFlowerpotPlayer getByPlantIdAndUserId(String plantId,String userId);

    void deleteByPlantId(String plantId);
}
