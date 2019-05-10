package com.tongzhu.usergoods.service;


import com.tongzhu.usergoods.model.PropGiftInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PropGiftInfoService {
    /**
     * 通过道具礼包id获取礼包对象
     * @param id
     * @return
     */
    PropGiftInfo getPropGiftInfo(String id);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
