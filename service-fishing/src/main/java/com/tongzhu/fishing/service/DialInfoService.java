package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.DialInfo;

import java.util.List;

public interface DialInfoService {

	List<DialInfo> getDialInfoList();

    Integer getForHeavySum(String userId);

    List<DialInfo> getDialInfoList(String userId);

    DialInfo getDialInfoById(Integer dialId);
}
