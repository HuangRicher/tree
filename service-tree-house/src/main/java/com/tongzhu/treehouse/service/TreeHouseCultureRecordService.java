package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseCultureRecord;

public interface TreeHouseCultureRecordService {

    int add(TreeHouseCultureRecord record);

    int update(TreeHouseCultureRecord record);

    TreeHouseCultureRecord findById(String id);

    TreeHouseCultureRecord findByUserIdAndTypeAndLevel(String userId, int type, int level);

    void updateCount(String id, int cultureCount);
}
