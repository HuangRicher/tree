package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.UserGoodsWarehouse;

import java.util.List;

/**
 * Created by Administrator on 2019/3/4 0004.
 */
public interface UserGoodsWarehouseService {
    Integer getUserGoodsNumberByUserId(String userId);

    UserGoodsWarehouse getUserGoodsWarehouseById(String goodsId);

    int insertSelective(UserGoodsWarehouse userGoodsWarehouse);

    int update(UserGoodsWarehouse userGoodsWarehouse);

    UserGoodsWarehouse getUserGoodsWarehouseByUserIdAndGoodsId(String userId, String goodsId);

    List<UserGoodsWarehouse> getUserGoodsWarehouseList(String userId);
}
