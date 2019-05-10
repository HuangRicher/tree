package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.mapper.DialShoppingInfoMapper;
import com.tongzhu.fishing.model.DialShoppingInfo;
import com.tongzhu.fishing.service.DialShoppingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16 0016.
 */
@Service
public class DialShoppingInfoServiceImpl implements DialShoppingInfoService {

    @Autowired
    private DialShoppingInfoMapper dialShoppingInfoMapper;

    @Override
    public List<DialShoppingInfo> getDialShoppingInfoList() {
        List<DialShoppingInfo> dialShoppingInfoList = dialShoppingInfoMapper.selectByExample(null);
        return dialShoppingInfoList;
    }
}
