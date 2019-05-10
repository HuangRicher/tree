package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.MonsterDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MonsterService {

    List<MonsterDO>  findByCopyId(Integer copyId);

    List<MonsterDO>  findByExplorationIdAndPassId(Integer explorationId ,Integer passId);

    boolean batchImport(String fileName, MultipartFile file)throws IOException;

    void cacheMonster(List<MonsterDO> monsterList ,Integer copyId);

    void initCacheMonster();
}
