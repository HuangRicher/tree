package com.tongzhu.treehouse.microservice;

import com.tongzhu.treehouse.model.TreeHouse;
import com.tongzhu.treehouse.service.TreeHouseFlowerpotService;
import com.tongzhu.treehouse.service.TreeHouseService;
import com.tongzhu.treehouse.service.TreeHouseWorkSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/treeHouseResource")
public class TreeHouseResource {

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private TreeHouseWorkSettingService treeHouseWorkSettingService;


    @PostMapping("/add")
    public void add(@RequestBody TreeHouse treeHouse) {
        treeHouseService.add(treeHouse);
    }


    @PostMapping(value = "/findByUserId/{userId}")
    public TreeHouse findByUserId(@PathVariable("userId") String userId) {
        return treeHouseService.findByUserId(userId);
    }


    @PostMapping(value = "/subByUserId")
    public TreeHouse subByUserId(@RequestBody TreeHouse treeHouse) {
        return treeHouseService.subByUserId(treeHouse);
    }

    @PostMapping("/comeInTreeHouse/{userId}/{treeHouseUserId}")
    public void comeInTreeHouse(@PathVariable("userId") String userId, @PathVariable("treeHouseUserId") String treeHouseUserId) {
        treeHouseService.comeInTreeHouse(userId, treeHouseUserId);
    }

    @PostMapping("/outTreeHouse/{userId}/{treeHouseUserId}")
    public void outTreeHouse(@PathVariable("userId") String userId, @PathVariable("treeHouseUserId") String treeHouseUserId) {
        treeHouseService.outTreeHouse(userId, treeHouseUserId);
    }

    @PostMapping("/updateTreeHouseLevel/{treeHouseUserId}/{level}")
    public void unlockTreeHouseFlowerpot(@PathVariable("treeHouseUserId") String treeHouseUserId, @PathVariable("level") Integer level) {
        treeHouseService.updateTreeHouseLevel(treeHouseUserId, level);
    }

    @PostMapping("/addTreeHouseWorkSetting/{fileName}")
    public boolean addTreeHouseWorkSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = treeHouseWorkSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


}
