package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.GameNPC;

import java.util.List;

public interface GameNPCExtMapper {
    void batchInsert(List<GameNPC> list);
}
