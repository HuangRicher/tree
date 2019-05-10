package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.enums.StatusEnum;
import com.tongzhu.fishing.mapper.UserActivityPropsMapper;
import com.tongzhu.fishing.model.UserActivityProps;
import com.tongzhu.fishing.model.UserActivityPropsExample;
import com.tongzhu.fishing.service.UserActivityPropsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityPropsServiceImpl implements UserActivityPropsService {

    @Autowired
    private UserActivityPropsMapper userActivityPropsMapper;
    @Override
    public List<UserActivityProps> getActivityPropsList(Integer id) {
        UserActivityPropsExample example = new UserActivityPropsExample();
        example.createCriteria().andActivityIdEqualTo(id).andStatusEqualTo(StatusEnum.NORMAL.getStatusCode());
        List<UserActivityProps> userActivityPropsList = userActivityPropsMapper.selectByExample(example);
        return userActivityPropsList;
    }
}
