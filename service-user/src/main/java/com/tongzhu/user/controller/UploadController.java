package com.tongzhu.user.controller;

import com.tongzhu.user.service.*;
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
    private SkillService skillService;

    @Autowired
    private SkillSettingService skillSettingService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private FightRankingSettingService fightRankingSettingService;

    @Autowired
    private CopyExpSettingService copyExpSettingService;

    @Autowired
    private ExplorationSettingService explorationSettingService;

    @Autowired
    private FightRankingExpSettingService fightRankingExpSettingService;

    @Autowired
    private ExplorationMonsterService explorationMonsterService;

    @Autowired
    private CopyExtraAwardService copyExtraAwardService;

    @Autowired
    private NPCService npcService;

    @Autowired
    private NPCAttributeService npcAttributeService;


    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/import/addSkill")
    public boolean addSkill(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = skillService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addSkillSetting")
    public boolean addSkillSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = skillSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addRoleLevelSetting")
    public boolean addRoleLevelSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = roleLevelSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /*@PostMapping("/import/addRoleLevelSetting")
    public boolean addRoleLevelSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = roleLevelSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }*/

    @PostMapping("/import/addMonster")
    public boolean addMonster(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = monsterService.batchImport(fileName, file);
            monsterService.initCacheMonster();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addFightRankingSetting")
    public boolean addFightRankingSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = fightRankingSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addFightRankingExpSetting")
    public boolean addFightRankingExpSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = fightRankingExpSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addCopyExpSetting")
    public boolean addCopyExpSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = copyExpSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addExplorationSetting")
    public boolean addExplorationSetting(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = explorationSettingService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addExplorationMonster")
    public boolean addExplorationMonster(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = explorationMonsterService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addCopyExtraAward")
    public boolean addCopyExtraAward(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = copyExtraAwardService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addNPC")
    public boolean addNPC(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = npcService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @PostMapping("/import/addNPCAttribute")
    public boolean addNPCAttribute(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = npcAttributeService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
