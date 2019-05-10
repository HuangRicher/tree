package com.tongzhu.user.service;

import com.tongzhu.user.mapper.vo.MonsterDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExplorationMonsterService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    List<MonsterDO> findByExplorationIdAndPassId(Integer explorationId, Integer passId);

}
