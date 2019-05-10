package com.tongzhu.usergoods.controller;

import com.tongzhu.usergoods.constant.FileConstant;
import com.tongzhu.usergoods.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ArsenalInfoService arsenalInfoService;

    @Autowired
    private PropInfoService propInfoService;

    @Autowired
    private PetLevelInfoService petLevelInfoService;

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private PropGiftInfoService propGiftInfoService;

    @Autowired
    private CompoundInfoService compoundInfoService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TreeHouseFurnitureService treeHouseFurnitureService;
    @Autowired
    private WelfareService welfareService;

    @ApiOperation(value = "增加装备数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addEquipment")
    public boolean addEquipment(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = equipmentInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加武器数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addArsenalInfo")
    public boolean addArsenalInfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = arsenalInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加道具数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addProplInfo")
    public boolean addProplInfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = propInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加宠物等级数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addPetLevelInfo")
    public boolean addPetLevelInfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = petLevelInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加宠物数据", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addPetInfo")
    public boolean addPetInfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = petInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加礼包配置", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addExchange")
    public boolean addExchange(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = propGiftInfoService.batchImport(fileName, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @ApiOperation(value = "增加合成物品配置", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")})
    @PostMapping("/import/addCompound")
    public boolean addCompound(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = compoundInfoService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


    @ApiOperation(value = "增加数据或者更新数据", notes = "通用更新数据接口,根据文件名进行更新  " +
            "\tTask.xlsx\n" +
            "\tTaskbranch.xlsx\n" +
            "\tWeapon.xlsx\n" +
            "\tcompound.xlsx\n" +
            "\tcopy_extra_award.xlsx\n" +
            "\tExchange.xlsx\n" +
            "\texp_setting.xlsx\n" +
            "\texploration.xlsx\n" +
            "\texplorationmonster.xlsx\n" +
            "\tfight_rank_grade.xlsx\n" +
            "\tMonster.xlsx\n" +
            "\trole_level_prop.xlsx\n" +
            "\trank.xlsx\n" +
            "\tskillset.xlsx\n" +
            "\twelfare_month.xlsx\n" +
            "\t")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true 成功 false 失败")})
    @PostMapping("/import/updateDate")
    public boolean updateDate(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        switch (fileName) {
            case FileConstant.equipment:
                try {
                    a = equipmentInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.items:
                try {
                    a = propInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.pet:
                try {
                    a = petInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.task:
                try {
                    a = taskService.addDailyTaskInfo(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.taskbranch:
                try {
                    a = taskService.addTaskBranch(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.weapon:
                try {
                    a = arsenalInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.compound:
                try {
                    a = compoundInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.copyExtraAward:
                try {
                    a = userService.addCopyExtraAward(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.exchange:
                try {
                    a = propGiftInfoService.batchImport(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.expSetting:
                try {
                    a = userService.addCopyExpSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.exploration:
                try {
                    a = userService.addExplorationSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.explorationmonster:
                try {
                    a = userService.addExplorationMonster(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.fightRankGrade:
                try {
                    a = userService.addFightRankingSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.monster:
                try {
                    a = userService.addMonster(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.roleLevelProp:
                try {
                    a = userService.addRoleLevelSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.rank:
                try {
                    a = userService.addFightRankingExpSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.skillset:
                try {
                    a = userService.addSkillSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.welfareMonth:
                try {
                    a = treeHouseFurnitureService.addTreeHouseWorkSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.npc:
                try {
                    a = userService.addNPC(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.monsterAttribute:
                try {
                    a = userService.addNPCAttribute(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.shop:
                try {
                    a = welfareService.addMall(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardJk:
                try {
                    a = welfareService.addBuildingCoffersSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardCwd:
                try {
                    a = welfareService.addBuildingPetshopSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardFls:
                try {
                    a = welfareService.addBuildingWelfareSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardJg:
                try {
                    a = welfareService.addBuildingWineshopSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardJt:
                try {
                    a = welfareService.addBuildingChurchSetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case FileConstant.buildingAwardTjp:
                try {
                    a = welfareService.addBuildingSmithySetting(fileName, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        return a;
    }


}

