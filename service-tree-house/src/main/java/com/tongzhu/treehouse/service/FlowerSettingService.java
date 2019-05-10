package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.FlowerSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FlowerSettingService {

    FlowerSetting getById(String id);

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    boolean batchImport(MultipartFile seedsDraw, MultipartFile flowerSeeds, MultipartFile flowerNumber) throws IOException;
}
