package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.UserGoodsWarehouse;
import com.tongzhu.usergoods.model.UserGoodsWarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGoodsWarehouseMapper {
    int countByExample(UserGoodsWarehouseExample example);

    int deleteByExample(UserGoodsWarehouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserGoodsWarehouse record);

    int insertSelective(UserGoodsWarehouse record);

    List<UserGoodsWarehouse> selectByExample(UserGoodsWarehouseExample example);

    UserGoodsWarehouse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserGoodsWarehouse record, @Param("example") UserGoodsWarehouseExample example);

    int updateByExample(@Param("record") UserGoodsWarehouse record, @Param("example") UserGoodsWarehouseExample example);

    int updateByPrimaryKeySelective(UserGoodsWarehouse record);

    int updateByPrimaryKey(UserGoodsWarehouse record);
}