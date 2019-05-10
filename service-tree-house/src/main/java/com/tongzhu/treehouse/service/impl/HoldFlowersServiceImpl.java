package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.HoldFlowersMapper;
import com.tongzhu.treehouse.model.HoldFlowers;
import com.tongzhu.treehouse.model.HoldFlowersExample;
import com.tongzhu.treehouse.service.HoldFlowersService;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldFlowersServiceImpl implements HoldFlowersService {

    @Autowired
    private HoldFlowersMapper holdFlowersMapper;


    @Override
    public List<HoldFlowers> findByUserId(String userId) {
        HoldFlowersExample example=new HoldFlowersExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return holdFlowersMapper.selectByExample(example);
    }

    @Override
    public void add(HoldFlowers flower) {
        HoldFlowersExample example=new HoldFlowersExample();
        example.createCriteria().andUserIdEqualTo(flower.getUserId()).andGoodsIdEqualTo(flower.getGoodsId());
        List<HoldFlowers> holdFlowersList=holdFlowersMapper.selectByExample(example);
        if (holdFlowersList.isEmpty()){
            flower.setId(UUIDUtil.generateUUID());
            holdFlowersMapper.insert(flower);
        }
    }

    @Override
    public HoldFlowers findByGoodsId(String treeHouseUserId,String goodsId) {
        HoldFlowersExample example=new HoldFlowersExample();
        example.createCriteria().andUserIdEqualTo(treeHouseUserId).andGoodsIdEqualTo(goodsId);
        List<HoldFlowers> holdFlowersList=holdFlowersMapper.selectByExample(example);
        if (holdFlowersList.isEmpty()){
            return null;
        }else {
            return holdFlowersList.get(0);
        }
    }
}
