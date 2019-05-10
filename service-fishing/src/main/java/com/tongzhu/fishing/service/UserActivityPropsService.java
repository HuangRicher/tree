package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.UserActivityProps;

import java.util.List;

public interface UserActivityPropsService {

    List<UserActivityProps> getActivityPropsList(Integer id);
}
