package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.WorkIncomeSetting;

import java.util.List;

public interface WorkIncomeSettingService {

    int add(WorkIncomeSetting setting);

    int updateById(WorkIncomeSetting setting);

    int deleteById(String Id);

    /**
     * 查找xx工种下xx工位的收益
     * @param workTypeId 工种Id
     * @param positionId 工位序号
     * @param level 工种等级
     * @return
     */
    List<WorkIncomeSetting> findByWorkTypeIdAndPositionIdAndLevel(int workTypeId, int positionId, int level);


    List<WorkIncomeSetting> findByWorkTypeIdAndLevel(Integer id, Integer level);
}
