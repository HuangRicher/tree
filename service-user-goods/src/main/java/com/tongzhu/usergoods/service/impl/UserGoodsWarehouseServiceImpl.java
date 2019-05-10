package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.UserGoodsWarehouseMapper;
import com.tongzhu.usergoods.model.UserGoodsWarehouse;
import com.tongzhu.usergoods.model.UserGoodsWarehouseExample;
import com.tongzhu.usergoods.service.UserGoodsWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/3/4 0004.
 */

@Service
public class UserGoodsWarehouseServiceImpl implements UserGoodsWarehouseService {

    @Autowired
    private UserGoodsWarehouseMapper userGoodsWarehouseMapper;

    @Override
    public Integer getUserGoodsNumberByUserId(String userId) {
        UserGoodsWarehouseExample userGoodsWarehouseExample = new UserGoodsWarehouseExample();
        userGoodsWarehouseExample.createCriteria().andUserIdEqualTo(userId).andAmountGreaterThan(0);
        return userGoodsWarehouseMapper.countByExample(userGoodsWarehouseExample);
    }

    @Override
    public UserGoodsWarehouse getUserGoodsWarehouseById(String id) {
        return userGoodsWarehouseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserGoodsWarehouse userGoodsWarehouse) {
        userGoodsWarehouse.setUpdateDate(new Date());
        userGoodsWarehouse.setCreateDate(new Date());
        return userGoodsWarehouseMapper.insertSelective(userGoodsWarehouse);
    }

    @Override
    public int update(UserGoodsWarehouse userGoodsWarehouse) {
        userGoodsWarehouse.setUpdateDate(new Date());
        return userGoodsWarehouseMapper.updateByPrimaryKeySelective(userGoodsWarehouse);
    }

    @Override
    public UserGoodsWarehouse getUserGoodsWarehouseByUserIdAndGoodsId(String userId, String goodsId) {
        UserGoodsWarehouseExample userGoodsWarehouseExample = new UserGoodsWarehouseExample();
        userGoodsWarehouseExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        List<UserGoodsWarehouse> userGoodsWarehouses = userGoodsWarehouseMapper.selectByExample(userGoodsWarehouseExample);
        if (userGoodsWarehouses.size() > 0) {
            return userGoodsWarehouses.get(0);
        }
        return null;
    }

    @Override
    public List<UserGoodsWarehouse> getUserGoodsWarehouseList(String userId) {
        UserGoodsWarehouseExample userGoodsWarehouseExample = new UserGoodsWarehouseExample();
        userGoodsWarehouseExample.createCriteria().andUserIdEqualTo(userId).andAmountGreaterThan(0);
        List<UserGoodsWarehouse> userGoodsWarehouses = userGoodsWarehouseMapper.selectByExample(userGoodsWarehouseExample);
        return userGoodsWarehouses;
    }


}
