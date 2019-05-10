package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.NewPlayerGuideMapper;
import com.tongzhu.user.model.NewPlayerGuide;
import com.tongzhu.user.service.NewPlayerGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewPlayerGuideServiceImpl implements NewPlayerGuideService {

    @Autowired
    private NewPlayerGuideMapper newPlayerGuideMapper;


    @Override
    public NewPlayerGuide getByUserId(String userId) {
        return newPlayerGuideMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    @Override
    public void doTask(String userId, Integer taskNum) {
        NewPlayerGuide newPlayerGuide=new NewPlayerGuide();
        newPlayerGuide.setUserId(userId);
        NewPlayerGuide guide=this.getByUserId(userId);
        newPlayerGuide.setTaskId(taskNum);
        if(guide!=null)
            newPlayerGuideMapper.updateByPrimaryKeySelective(newPlayerGuide);
        else
            newPlayerGuideMapper.insertSelective(newPlayerGuide);
    }

}
