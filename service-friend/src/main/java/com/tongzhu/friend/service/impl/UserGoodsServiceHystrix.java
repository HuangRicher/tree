package com.tongzhu.friend.service.impl;

import com.tongzhu.friend.domain.UserGoods;
import com.tongzhu.friend.service.UserGoodsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserGoodsServiceHystrix implements UserGoodsService {


	@Override
	public UserGoods addUserGoodsSingle(String userId, String goodsId, Integer amount) {
		throw new RuntimeException("error");
	}

    @Override
    public List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsList) {
        return null;
    }
}
