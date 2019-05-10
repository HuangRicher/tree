package com.tongzhu.user.service;

import com.tongzhu.user.model.CopyExpSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CopyExpSettingService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    CopyExpSetting getByRoleLevelAndCopyId(Integer roleLevel,Integer copyId);
}
