package com.tongzhu.user.service.impl;

import com.tongzhu.user.mapper.UserMainPersonMapper;
import com.tongzhu.user.model.UserMainPerson;
import com.tongzhu.user.service.UserMainPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMainPersonServiceImpl implements UserMainPersonService {

    @Autowired
    private UserMainPersonMapper userMainPersonMapper;



    @Override
    public void add(String userId, String personName) {
        UserMainPerson person=userMainPersonMapper.selectByPrimaryKey(userId);
        if(person!=null){
            person.setMainPersonName(personName);
            userMainPersonMapper.updateByPrimaryKey(person);
        }else{
            person=new UserMainPerson();
            person.setMainPersonName(personName);
            person.setUserId(userId);
            userMainPersonMapper.insert(person);
        }

    }
}
