package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.UserWorkType;

import java.util.List;

public interface UserWorkTypeExtMapper {
    void insertBatch(List<UserWorkType> list);
}
