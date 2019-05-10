package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.domain.UserGoods;
import com.tongzhu.treehouse.service.UserGoodsService;
import com.tongzhu.treehouse.service.vo.PropInfoVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGoodsServiceHystrix implements UserGoodsService {

    @Override
    public List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsList) {
        return null;
    }

    @Override
    public UserGoods subUserGoodsSingle(String userId, String goodsId, int multiple) {
        return null;
    }

    @Override
    public List<UserGoods> addUserGoods(String userId, List<UserGoods> goodsList) {
        return null;
    }

    @Override
    public UserGoods getGoodsCount(String userId, String goodsId) {
        return null;
    }

    @Override
    public List<PropInfoVO> querySeedsPropList(String userId) {
        return null;
    }

    @Override
    public UserGoods buyProp(String userId, String goodsId) {
        return null;
    }
}
