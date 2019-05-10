package com.tongzhu.fishing.service.impl;


import com.tongzhu.fishing.mapper.UserActivityPropsGoodsMapper;
import com.tongzhu.fishing.model.UserActivityPropsGoods;
import com.tongzhu.fishing.model.UserActivityPropsGoodsExample;
import com.tongzhu.fishing.service.UserActivityPropsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityPropsServiceGoodsImpl implements UserActivityPropsGoodsService {

    @Autowired
    private UserActivityPropsGoodsMapper userActivityPropsGoodsMapper;


    @Override
    public int addUserActivityPropsGoods(UserActivityPropsGoods userActivityPropsGoods) {
        return userActivityPropsGoodsMapper.insertSelective(userActivityPropsGoods);
    }

    @Override
    public UserActivityPropsGoods getUserActivityPropsGoods(Integer activityId, Integer propsId,String userId) {
        UserActivityPropsGoodsExample example = new UserActivityPropsGoodsExample();
        example.createCriteria().andUserIdEqualTo(userId).andActivityIdEqualTo(activityId).andPropsIdEqualTo(propsId);
        List<UserActivityPropsGoods> userActivityPropsGoodsList = userActivityPropsGoodsMapper.selectByExample(example);
        if(userActivityPropsGoodsList.size()>0) {
            return userActivityPropsGoodsList.get(0);
        }
        return null;
    }

    @Override
    public int update(UserActivityPropsGoods userActivityPropsGoods) {
        return userActivityPropsGoodsMapper.updateByPrimaryKeySelective(userActivityPropsGoods);
    }
}
