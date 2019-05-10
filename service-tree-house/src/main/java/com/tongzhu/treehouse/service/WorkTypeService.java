package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.WorkType;
import com.tongzhu.treehouse.service.vo.UserWorkTypeVO;

import java.util.List;

public interface WorkTypeService {
    List<WorkType> findAll();

    List<UserWorkTypeVO> listUserWorkType(String userId);

    WorkType findByWorkTypeId(int workTypeId);

    UserWorkTypeVO findUserWorkTypeByWorkTypeId(String userId, Integer workTypeId);
}
