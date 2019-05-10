package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.service.TreeHouseFurnitureService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TreeHouseFurnitureServiceHystrix implements TreeHouseFurnitureService {
    @Override
    public void addFurniture(String userId, String goodsId) {

    }

    @Override
    public boolean addTreeHouseWorkSetting(String fileName, MultipartFile file) {
        return false;
    }
}
