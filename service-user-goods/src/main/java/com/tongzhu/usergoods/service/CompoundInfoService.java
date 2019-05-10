package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.model.CompoundInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2018/11/16 0016.
 */
public interface CompoundInfoService {
    CompoundInfo getCompoundInfo(String basicsId, String compositeId);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
