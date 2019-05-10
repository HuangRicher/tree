package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseFurniture;

import java.util.List;

public interface TreeHouseFurnitureService {

    void add(String treeHouseUserId,String goodsId);

    TreeHouseFurniture getByTreeHouseIdAndGoodsId(String treeHouseUserId,String goodsId);

    void delete(String id);

    List<TreeHouseFurniture> findCollocationByTreeHouseId(String treeHouseId);


    /**
     * 布置家具
     * @param userId
     * @param goodsId
     */
    void arrange(String userId, String goodsId,Integer location);

    /**
     * 布置家具
     * @param userId
     * @param furnitures
     */
    void move(String userId, List<TreeHouseFurniture> furnitures);

    List<TreeHouseFurniture> findOwnFurnitureList(String userId);

    TreeHouseFurniture getById(String id);

    List<TreeHouseFurniture> findCollocations();

    void cancelArrangedFurniture(String userId, String goodsId);


    void deleteOverdueFurniture();
}
