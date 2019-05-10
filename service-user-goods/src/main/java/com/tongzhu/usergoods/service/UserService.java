package com.tongzhu.usergoods.service;

import com.tongzhu.usergoods.config.FeignConfig;
import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.service.impl.UserServiceHystrix;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "${feign-service.user}", configuration = FeignConfig.class, fallback = UserServiceHystrix.class)
public interface UserService {

    @RequestMapping(value = "/userResource/findByUserId/{userId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    User findByUserId(@PathVariable("userId") String userId);

    @RequestMapping(value = "/userResource/updateCrunchies/{userId}/{type}/{recordValues}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateCrunchies(@PathVariable("userId") String userId, @PathVariable("type") Integer type, @PathVariable("recordValues") Integer recordValues);


    @RequestMapping(value = "/uploadResource/addCopyExtraAward/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addCopyExtraAward(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);


    @RequestMapping(value = "/uploadResource/addCopyExpSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addCopyExpSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addExplorationSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addExplorationSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addExplorationMonster/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addExplorationMonster(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addFightRankingSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addFightRankingSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addMonster/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addMonster(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addRoleLevelSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addRoleLevelSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addFightRankingExpSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addFightRankingExpSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addSkillSetting/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addSkillSetting(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addNPC/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addNPC(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/uploadResource/addNPCAttribute/{fileName}",
            method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    boolean addNPCAttribute(@PathVariable("fileName") String fileName, @RequestPart(value = "file") MultipartFile file);


}
