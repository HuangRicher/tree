package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseWorkSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TreeHouseWorkSettingService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    TreeHouseWorkSetting getByRoleLevel(Integer roleLevel);

}
