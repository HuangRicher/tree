package com.tongzhu.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FightRankingExpSettingService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
