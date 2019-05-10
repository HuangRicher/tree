package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.WorkIncomeSettingMapper;
import com.tongzhu.treehouse.model.WorkIncomeSetting;
import com.tongzhu.treehouse.model.WorkIncomeSettingExample;
import com.tongzhu.treehouse.service.WorkIncomeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkIncomeSettingServiceImpl implements WorkIncomeSettingService {

    @Autowired
    private WorkIncomeSettingMapper workIncomeSettingMapper;

    @Override
    public int add(WorkIncomeSetting setting) {
        return workIncomeSettingMapper.insertSelective(setting);
    }

    @Override
    public List<WorkIncomeSetting> findByWorkTypeIdAndPositionIdAndLevel(int workTypeId, int positionId, int level) {
        WorkIncomeSettingExample example=new WorkIncomeSettingExample();
        example.createCriteria().andWorkTypeIdEqualTo(workTypeId).andPositionIdEqualTo(positionId)
        .andWorkTypeLevelEqualTo(level);
        return workIncomeSettingMapper.selectByExample(example);
    }

    @Override
    public List<WorkIncomeSetting> findByWorkTypeIdAndLevel(Integer workTypeId, Integer level) {
        WorkIncomeSettingExample example=new WorkIncomeSettingExample();
        example.createCriteria().andWorkTypeIdEqualTo(workTypeId).andWorkTypeLevelEqualTo(level);
        return workIncomeSettingMapper.selectByExample(example);
    }

    @Override
    public int updateById(WorkIncomeSetting setting) {
        return workIncomeSettingMapper.updateByPrimaryKeySelective(setting);
    }

    @Override
    public int deleteById(String id) {
        return workIncomeSettingMapper.deleteByPrimaryKey(id);
    }
}
