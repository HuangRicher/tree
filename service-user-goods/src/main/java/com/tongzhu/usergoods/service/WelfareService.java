package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.service.impl.WelfareServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "${feign-service.welfare}", configuration = FeignConfig.class, fallback = WelfareServiceHystrix.class)
public interface WelfareService {

    @RequestMapping(value = "/welfareResource/addMall/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addMall(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingCoffersSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingCoffersSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingWelfareSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingWelfareSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingChurchSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingChurchSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingSmithySetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingSmithySetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingWineshopSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingWineshopSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingStatueSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingStatueSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/welfareResource/addBuildingPetshopSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addBuildingPetshopSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

}
