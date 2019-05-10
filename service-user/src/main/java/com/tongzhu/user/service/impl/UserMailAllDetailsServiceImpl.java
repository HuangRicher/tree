package com.tongzhu.user.service.impl;


import com.tongzhu.user.mapper.UserMailAllDetailsMapper;
import com.tongzhu.user.model.UserMailAllDetails;
import com.tongzhu.user.model.UserMailAllDetailsKey;
import com.tongzhu.user.service.UserMailAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMailAllDetailsServiceImpl implements UserMailAllDetailsService {

    @Autowired
    private UserMailAllDetailsMapper userMailAllDetailsMapper;

    @Override
    public UserMailAllDetails getUserMailAllDetails(String userId, String id) {
        UserMailAllDetailsKey userMailAllDetailsKey = new UserMailAllDetailsKey();
        userMailAllDetailsKey.setReceiverId(userId);
        userMailAllDetailsKey.setMailallId(id);
        return userMailAllDetailsMapper.selectByPrimaryKey(userMailAllDetailsKey);
    }

    @Override
    public int inesrt(UserMailAllDetails userMailAllDetails) {
        return userMailAllDetailsMapper.insertSelective(userMailAllDetails);
    }

    @Override
    public int update(UserMailAllDetails userMailAllDetails) {
        return userMailAllDetailsMapper.updateByPrimaryKey(userMailAllDetails);
    }

    @Override
    public int delete(String id, String userId) {
        UserMailAllDetailsKey userMailAllDetailsKey = new UserMailAllDetailsKey();
        userMailAllDetailsKey.setMailallId(id);
        userMailAllDetailsKey.setReceiverId(userId);
        return userMailAllDetailsMapper.deleteByPrimaryKey(userMailAllDetailsKey);
    }
}
