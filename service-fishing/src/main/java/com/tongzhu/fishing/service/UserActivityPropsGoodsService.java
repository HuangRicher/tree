package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.UserActivityPropsGoods;

public interface UserActivityPropsGoodsService {

    int addUserActivityPropsGoods(UserActivityPropsGoods userActivityPropsGoods);

    UserActivityPropsGoods getUserActivityPropsGoods(Integer activityId, Integer propsId, String userId);

    int update(UserActivityPropsGoods userActivityPropsGoods);
}
