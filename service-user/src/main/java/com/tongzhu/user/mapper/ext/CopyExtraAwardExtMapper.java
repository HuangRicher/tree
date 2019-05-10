package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.CopyExtraAward;

import java.util.List;

public interface CopyExtraAwardExtMapper {
    void batchInsert(List<CopyExtraAward> list);
}
