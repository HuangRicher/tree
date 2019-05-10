package com.tongzhu.user.microservice;

import com.tongzhu.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/4/17 0017.
 */
@RestController
@RequestMapping("/uploadResource")
public class UploadResource {

    @Autowired
    private CopyExtraAwardService copyExtraAwardService;
    @Autowired
    private CopyExpSettingService copyExpSettingService;
    @Autowired
    private ExplorationSettingService explorationSettingService;
    @Autowired
    private ExplorationMonsterService explorationMonsterService;
    @Autowired
    private FightRankingSettingService fightRankingSettingService;
    @Autowired
    private MonsterService monsterService;
    @Autowired
    private RoleLevelSettingService roleLevelSettingService;
    @Autowired
    private FightRankingExpSettingService fightRankingExpSettingService;
    @Autowired
    private SkillSettingService skillSettingService;
    @Autowired
    private NPCService npcService;
    @Autowired
    private NPCAttributeService npcAttributeService;


    @PostMapping("/addCopyExtraAward/{fileName}")
    public boolean addCopyExtraAward(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = copyExtraAwardService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addCopyExpSetting/{fileName}")
    public boolean addCopyExpSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = copyExpSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addExplorationSetting/{fileName}")
    public boolean addExplorationSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = explorationSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addExplorationMonster/{fileName}")
    public boolean addExplorationMonster(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = explorationMonsterService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addFightRankingSetting/{fileName}")
    public boolean addFightRankingSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = fightRankingSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addMonster/{fileName}")
    public boolean addMonster(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = monsterService.batchImport(fileName, file);
            monsterService.initCacheMonster();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addRoleLevelSetting/{fileName}")
    public boolean addRoleLevelSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = roleLevelSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addFightRankingExpSetting/{fileName}")
    public boolean addFightRankingExpSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = fightRankingExpSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addSkillSetting/{fileName}")
    public boolean addSkillSetting(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = skillSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addNPC/{fileName}")
    public boolean addNPC(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = npcService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/addNPCAttribute/{fileName}")
    public boolean addNPCAttribute(@PathVariable("fileName") String fileName, @RequestPart("file") MultipartFile file) throws Exception {
        boolean a = false;
        try {
            a = npcAttributeService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


}
