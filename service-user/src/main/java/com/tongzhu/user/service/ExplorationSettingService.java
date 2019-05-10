package com.tongzhu.user.service;

import com.tongzhu.user.model.ExplorationSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExplorationSettingService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    ExplorationSetting getByExplorationIdAndPassId(Integer explorationId,Integer passId);

}
