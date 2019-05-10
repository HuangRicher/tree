package com.tongzhu.welfare.service.impl;

import java.util.List;
import java.util.Map;

import com.tongzhu.welfare.domain.PropInfo;
import org.springframework.stereotype.Component;

import com.tongzhu.welfare.domain.GeneralityGoods;
import com.tongzhu.welfare.domain.UserAdornEquip;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.service.UserGoodsService;

@Component
public class UserGoodsServiceHystrix implements UserGoodsService {

	@Override
	public List<UserGoods> subUserGoods(String userId, List<UserGoods> goodsList) {
		throw new RuntimeException("error");
	}

	@Override
	public List<UserGoods> addUserGoods(String userId, List<UserGoods> goodsList) {
		throw new RuntimeException("error");
	}

	@Override
	public UserGoods getGoodsCount(String userId, String goodsId) {
		throw new RuntimeException("error");
	}

	@Override
	public void deleteByUserIdAndGoodsId(String userId, Integer goodsId) {
		throw new RuntimeException("error");
	}

	@Override
	public PropInfo getPropInfo(Integer goodsId) {
		return null;
	}

	@Override
	public UserGoods deleteWeddingRingByUserIdAndGoodsId(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public List<GeneralityGoods> queryGoodsWeddingRing(String userId) {
		throw new RuntimeException("error");
	}

	@Override
	public UserGoods addEquipAndWeapon(String userId, Integer type, Integer id) {
		throw new RuntimeException("error");
	}

	@Override
	public Map<String, Object> wearEquipment(String userId, String goodsId) {
		throw new RuntimeException("error");
	}

	@Override
	public UserAdornEquip getUserFightingCapacity(String userId) {
		return null;
	}

	@Override
	public UserAdornEquip addUserAdornEquipAttribute(String userId, Double vitality, Double attack, Double spellAttacks,
			Double pdef, Double magdef, Double crit, Double dodge, Double hitRate, Double defenseCrit) {
		throw new RuntimeException("error");
	}

	@Override
	public int queryBackpackSpace(String userId, List<String> goodsIdList) {
		return -1;
	}

	@Override
	public Integer getUserWeddingRingId(String userId) {
		return null;
	}

	@Override
	public Map getUserWeddingRingHappinessNum(String userId) {
		return null;
	}

}
