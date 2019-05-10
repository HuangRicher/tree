package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.mapper.WorkPositionSettingMapper;
import com.tongzhu.treehouse.model.WorkPositionSetting;
import com.tongzhu.treehouse.model.WorkPositionSettingExample;
import com.tongzhu.treehouse.service.WorkPositionSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPositionSettingServiceImpl implements WorkPositionSettingService {

    @Autowired
    private WorkPositionSettingMapper workPositionSettingMapper;


    @Override
    public WorkPositionSetting findByWorkTypeIdAndOrder(int workTypeId, int order) {
        WorkPositionSettingExample example=new WorkPositionSettingExample();
        example.createCriteria().andWorkTypeIdEqualTo(workTypeId).andPositionOrderEqualTo(order);
        List<WorkPositionSetting> settings=workPositionSettingMapper.selectByExample(example);
        if(settings!=null && settings.size()>0){
            return settings.get(0);
        }
        return null;
    }
}
