package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.domain.UserGoods;
import com.tongzhu.servicemessage.service.UserGoodsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserGoodsServiceHystrix implements UserGoodsService {


	@Override
	public UserGoods addUserGoodsSingle(String userId, String goodsId, Integer amount) {
		return null;
	}

    @Override
    public List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsList) {
        return null;
    }
}
