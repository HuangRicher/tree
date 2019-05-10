package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.HoldFlowers;

import java.util.List;

public interface HoldFlowersService {

    List<HoldFlowers> findByUserId(String userId);

    void add(HoldFlowers flower);

    HoldFlowers findByGoodsId(String treeHouseUserId,String goodsId);
}
