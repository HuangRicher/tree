package com.tongzhu.treehouse.controller;

import com.tongzhu.treehouse.service.FlowerSettingService;
import com.tongzhu.treehouse.service.FurnitureService;
import com.tongzhu.treehouse.service.TreeHouseWorkSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private TreeHouseWorkSettingService treeHouseWorkSettingService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private FlowerSettingService flowerSettingService;


    @PostMapping("/import/addTreeHouseWorkSetting")
    public boolean addTreeHouseWorkSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = treeHouseWorkSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


    @PostMapping("/import/addFurniture")
    public boolean addFurniture(@RequestParam("fileTree") MultipartFile fileTree,@RequestParam("fileExchange") MultipartFile fileExchange) {
        boolean a = false;
        //String fileName = file.getOriginalFilename();
        try {
            a = furnitureService.batchImport(fileTree, fileExchange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addFlowerSettings")
    public boolean addFlowerSettings(@RequestParam("flowerSeeds") MultipartFile flowerSeeds,
                                     @RequestParam("seedsDraw") MultipartFile seedsDraw,
                                     @RequestParam("flowerNumber") MultipartFile flowerNumber) {
        boolean a = false;
        //String fileName = file.getOriginalFilename();
        try {
            a = flowerSettingService.batchImport(seedsDraw,flowerSeeds,flowerNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
