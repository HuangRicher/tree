package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.PetLevelInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2018/12/7 0007.
 */
public interface PetLevelInfoService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    PetLevelInfo getPetLevelInfoByLevel(Integer level);

    Integer getPetLevelPet(Integer minLevel, Integer maxLevel);
}
