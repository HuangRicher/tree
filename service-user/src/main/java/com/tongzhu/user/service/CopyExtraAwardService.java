package com.tongzhu.user.service;

import com.tongzhu.user.model.CopyExtraAward;
import com.tongzhu.user.service.vo.CopyExtraAwardVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CopyExtraAwardService {

    boolean batchImport(String fileName, MultipartFile file)throws IOException;

    List<CopyExtraAwardVO> getExtraAwardList();

    CopyExtraAward getById(String id);

}
