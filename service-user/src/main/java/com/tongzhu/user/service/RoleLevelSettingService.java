package com.tongzhu.user.service;

import com.tongzhu.user.model.RoleLevelSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RoleLevelSettingService {

    /**
     * 批量导入角色等级配置数据Excel
     * @param fileName
     * @param file
     * @return
     * @throws IOException
     */
    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    /**
     * 获取某一等级的角色配置
     * @param level
     * @return
     */
    RoleLevelSetting getByLevel(Integer level);


}
