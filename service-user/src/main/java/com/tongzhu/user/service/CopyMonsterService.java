package com.tongzhu.user.service;

import com.tongzhu.user.model.CopyMonster;

import java.util.List;

public interface CopyMonsterService {

    void batchInsert(List<CopyMonster> list);

    void deleteAll();

    List<CopyMonster> findByCopyId(Integer copyId);
}
