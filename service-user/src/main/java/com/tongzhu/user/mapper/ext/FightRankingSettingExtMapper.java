package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.FightRankingSetting;

import java.util.List;

public interface FightRankingSettingExtMapper {
    void batchInsert(List<FightRankingSetting> list);
}