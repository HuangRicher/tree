package com.tongzhu.fishing.service.impl;

import com.tongzhu.fishing.enums.AddressCodeEnum;
import com.tongzhu.fishing.enums.StatusEnum;
import com.tongzhu.fishing.mapper.UserActivityMapper;
import com.tongzhu.fishing.model.UserActivity;
import com.tongzhu.fishing.model.UserActivityExample;
import com.tongzhu.fishing.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Override
    public List<UserActivity> getActivity(Date date) {
        UserActivityExample example = new UserActivityExample();
        example.createCriteria().andStartTimeLessThanOrEqualTo(date).andEndTimeGreaterThanOrEqualTo(date).
                andStatusEqualTo(StatusEnum.NORMAL.getStatusCode()).andFallingPositionLike(AddressCodeEnum.FISHING.getCode()+"");
        List<UserActivity> userActivities = userActivityMapper.selectByExample(example);
        return userActivities;
    }
}
