package com.tongzhu.user.service.impl;

import com.tongzhu.user.constant.MailConstant;
import com.tongzhu.user.mapper.UserMailAllMapper;
import com.tongzhu.user.model.UserMailAll;
import com.tongzhu.user.model.UserMailAllExample;
import com.tongzhu.user.service.UserMailAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserMailAllServiceImpl implements UserMailAllService {

    @Autowired
    private UserMailAllMapper userMailAllMapper;

    @Override
    public int insert(UserMailAll userMailAll) {
        return userMailAllMapper.insertSelective(userMailAll);
    }

    @Override
    public UserMailAll getUserMailAllById(String id) {
        return userMailAllMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(String id) {
        return userMailAllMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserMailAll> getUserMailAllList() {
        UserMailAllExample userMailAllExample = new UserMailAllExample();
        userMailAllExample.createCriteria().andExpireTimeGreaterThanOrEqualTo(new Date()).andStatusEqualTo(MailConstant.MAIL_STATUS_NORMAL);
        return userMailAllMapper.selectByExample(userMailAllExample);
    }


}
