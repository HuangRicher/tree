package com.tongzhu.fishing.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.fishing.constant.ConstantValue;
import com.tongzhu.fishing.domain.PropInfo;
import com.tongzhu.fishing.domain.UserGoods;
import com.tongzhu.fishing.mapper.FishingInfoMapper;
import com.tongzhu.fishing.mapper.ext.FishingInfoExtMapper;
import com.tongzhu.fishing.model.FishEntrepot;
import com.tongzhu.fishing.model.FishInfo;
import com.tongzhu.fishing.model.FishingInfo;
import com.tongzhu.fishing.redis.RedisService;
import com.tongzhu.fishing.service.*;
import com.tongzhu.fishing.util.FisheryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class FishingInfoServiceImpl implements FishingInfoService {

    @Autowired
    private FishingInfoMapper fishingInfoMapper;

    @Autowired
    private FishingInfoExtMapper fishingInfoExtMapper;


    @Autowired
    private RedisService redisService;

    @Autowired
    private FishInfoService fishInfoService;

    @Autowired
    private PropService propService;

    @Autowired
    private FishEntrepotService fishEntrepotService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private TaskService taskService;

    @Override
    public int addFishingInfo(FishingInfo fishingInfo) {
        return fishingInfoMapper.insertSelective(fishingInfo);
    }

    @Override
    public FishingInfo getFishingInfo(String userId) {
        return fishingInfoExtMapper.selectByUserId(userId);
    }

    @Override
    public int renewalFishingInfo(FishingInfo fishingInfo) {
        return fishingInfoMapper.updateByPrimaryKey(fishingInfo);
    }

    @Override
    @Transactional
    public List<FishInfo> lottery(String userId, Integer multiple, String consumables, Integer amount) throws Exception {
        // 减去消耗品
        UserGoods userGoods = userGoodsService.subUserGoodsSingle(userId, consumables, amount);
        if (userGoods == null) {
            throw new Exception("资源不足");
        }
        Map<List<Integer>, FishInfo> fishingMap = redisService.getFishing(ConstantValue.LOTTERY_FISH, new FishInfo());
        Integer sumWeight = (Integer) redisService.get(ConstantValue.PROBABILITY_LOTTERY_FISH);
        if (fishingMap == null) {
            List<FishInfo> fishInfoList = fishInfoService.getFishInfoList();
            fishingMap = FisheryUtil.getFisheryMap(fishInfoList, "commonProbability");
            redisService.setFishing(ConstantValue.LOTTERY_FISH, fishingMap);
            fishingMap = redisService.getFishing(ConstantValue.LOTTERY_FISH, new FishInfo());
        }
        if (sumWeight == null) {
            sumWeight = fishInfoService.getSumCommonProbability();
            redisService.set(ConstantValue.PROBABILITY_LOTTERY_FISH, sumWeight);
        }
        List<FishInfo> list = new ArrayList();
        for (int i = 0; i < multiple; i++) {
            FishInfo fishInfo = FisheryUtil.getActivityProperty(fishingMap, sumWeight);
            String reward = fishInfo.getReward();
            JSONObject jsonObject = JSON.parseObject(reward);
            for (Object goodsId : jsonObject.keySet()) {
                Object count = jsonObject.get(goodsId);
                PropInfo propInfo = propService.getPropInfo(goodsId + "");
                if (propInfo == null) {
                    continue;
                }
                FishEntrepot fishEntrepot = new FishEntrepot();
                fishEntrepot.setAmount((Integer) count);
                fishEntrepot.setCreateDate(new Date());
                fishEntrepot.setGoodsId(goodsId + "");
                fishEntrepot.setUserId(userId);
                fishEntrepot.setReceive(0);
                fishEntrepot.setBoutique(fishInfo.getBoutique());
                fishEntrepotService.insertSelective(fishEntrepot);
            }
            list.add(fishInfo);
        }
        // 日常任务
        taskService.taskProgress(ConstantValue.TASK_TYPE_CJDY, multiple, userId);
        return list;
    }
}
