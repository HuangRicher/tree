package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.CopyMonster;

import java.util.List;

public interface CopyMonsterExtMapper {

    void batchInsert(List<CopyMonster> list);
}
