package com.tongzhu.fishing.service;



import com.tongzhu.fishing.model.UserActivity;

import java.util.Date;
import java.util.List;

public interface UserActivityService {

    List<UserActivity> getActivity(Date date);

}
