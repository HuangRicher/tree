package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.GemInfoMapper;
import com.tongzhu.usergoods.model.GemInfo;
import com.tongzhu.usergoods.service.GemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

@Service
public class GemInfoServiceImpl implements GemInfoService {

    @Autowired
    private GemInfoMapper gemInfoMapper;

    @Override
    public GemInfo getGemInfoById(String gemId) {
        return gemInfoMapper.selectByPrimaryKey(gemId);
    }
}
