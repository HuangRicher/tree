package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.mapper.FishingSecretOperationMapper;
import com.tongzhu.fishing.model.FishingSecretOperation;
import com.tongzhu.fishing.model.FishingSecretOperationExample;
import com.tongzhu.fishing.service.FishingSecretOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 好友操作
 */
@Service
public class FishingSecretOperationServiceImpl implements FishingSecretOperationService {
    @Autowired
    private FishingSecretOperationMapper fishingSecretOperationMapper;

    @Override
    public List<FishingSecretOperation> getSecretPrize(Integer begin, Integer end) {
        FishingSecretOperationExample example=new FishingSecretOperationExample();
        example.createCriteria().andNumberFishingGreaterThan(begin).andNumberFishingLessThanOrEqualTo(end);
        List<FishingSecretOperation> fishingSecretOperations = fishingSecretOperationMapper.selectByExample(example);
        return fishingSecretOperations;
    }
}
