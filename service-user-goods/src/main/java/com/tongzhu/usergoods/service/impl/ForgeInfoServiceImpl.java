package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.mapper.ForgeInfoMapper;
import com.tongzhu.usergoods.model.ForgeInfo;
import com.tongzhu.usergoods.model.ForgeInfoExample;
import com.tongzhu.usergoods.service.ForgeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 */
@Service
public class ForgeInfoServiceImpl implements ForgeInfoService {

    @Autowired
    private ForgeInfoMapper forgeInfoMapper;

    @Override
    public ForgeInfo getForgeInfoByEnchantlvl(Integer enchantlvl) {
        ForgeInfoExample forgeInfoExample = new ForgeInfoExample();
        forgeInfoExample.createCriteria().andEnchantlvlEqualTo(enchantlvl);
        List<ForgeInfo> forgeInfos = forgeInfoMapper.selectByExample(forgeInfoExample);
        if (forgeInfos.size() > 0) {
            return forgeInfos.get(0);
        }
        return null;
    }
}
