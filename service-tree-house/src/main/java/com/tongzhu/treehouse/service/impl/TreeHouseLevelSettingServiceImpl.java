package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.TreeHouseLevelSettingMapper;
import com.tongzhu.treehouse.model.TreeHouseLevelSetting;
import com.tongzhu.treehouse.model.TreeHouseLevelSettingExample;
import com.tongzhu.treehouse.service.TreeHouseLevelSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeHouseLevelSettingServiceImpl implements TreeHouseLevelSettingService {

    @Autowired
    private TreeHouseLevelSettingMapper treeHouseLevelSettingMapper;

    @Override
    public TreeHouseLevelSetting findByTreeLevel(int level) {
        TreeHouseLevelSettingExample example=new TreeHouseLevelSettingExample();
        example.createCriteria().andLevelEqualTo(level);
        List<TreeHouseLevelSetting> settings=treeHouseLevelSettingMapper.selectByExample(example);
        if(settings!=null && settings.size()>0){
            return settings.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<TreeHouseLevelSetting> findAll() {
        TreeHouseLevelSettingExample example=new TreeHouseLevelSettingExample();
        return treeHouseLevelSettingMapper.selectByExample(example);
    }

    @Override
    public void update(TreeHouseLevelSetting setting) {
        treeHouseLevelSettingMapper.updateByPrimaryKeySelective(setting);
    }
}
