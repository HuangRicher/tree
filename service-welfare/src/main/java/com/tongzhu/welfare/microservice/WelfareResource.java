package com.tongzhu.welfare.microservice;

import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tongzhu.welfare.service.WelfareService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/welfareResource")
public class WelfareResource {

    @Autowired
    WelfareService welfareService;

    @Autowired
    private MallService mallService;

    @Autowired
    private BuildingService buildingService;

    /**
     * 增加登录记录
     *
     * @param userId
     */
    @PostMapping("/addDaysByUserId/{userId}")
    public void addDaysByUserId(@PathVariable("userId") String userId) {
        welfareService.addDaysByUserId(userId);
    }

    /**
     * 判断当前用户是否有可领取的福利
     *
     * @param userId
     */
    @PostMapping("/getWelfareStatusByUserId/{userId}")
    public int getWelfareStatusByUserId(@PathVariable("userId") String userId) {
        return welfareService.getWelfareStatusByUserId(userId);
    }


    @PostMapping("/addMall/{fileName}")
    public boolean addMall(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = mallService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 金库配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingCoffersSetting/{fileName}")
    public boolean addBuildingCoffersSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = buildingService.addBuildingCoffersSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 福利社配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingWelfareSetting/{fileName}")
    public boolean addBuildingWelfareSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingWelfareSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 教堂配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingChurchSetting/{fileName}")
    public boolean addBuildingChurchSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingChurchSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 铁匠铺配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingSmithySetting/{fileName}")
    public boolean addBuildingSmithySetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingSmithySetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 酒馆配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingWineshopSetting/{fileName}")
    public boolean addBuildingWineshopSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingWineshopSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    /**
     * 宠物馆配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingPetshopSetting/{fileName}")
    public boolean addBuildingPetshopSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingPetshopSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    /**
     * 雕像配置
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuildingStatueSetting/{fileName}")
    public boolean addBuildingStatueSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) {
        boolean a = false;
        try {
            a = buildingService.addBuildingStatueSetting(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


}
