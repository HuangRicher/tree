package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.Furniture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FurnitureService {

    Furniture getById(String goodsId);

    boolean batchImport(MultipartFile fileTree,MultipartFile fileExchange)throws IOException;

    Furniture getByCategoryId(String goodsId);
}
