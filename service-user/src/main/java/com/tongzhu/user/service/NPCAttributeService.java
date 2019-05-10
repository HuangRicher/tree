package com.tongzhu.user.service;

import com.tongzhu.user.model.NPCAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NPCAttributeService {

    boolean batchImport(String fileName, MultipartFile file)throws IOException;

    NPCAttribute findByLevelId(Integer levelId);
}
