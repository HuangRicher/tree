package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseWorkLog;

import java.util.List;

public interface TreeHouseWorkLogService {

    void add(String content);

    List<TreeHouseWorkLog> find2HourLog();

    void deleteBefore2Hour();
}
