package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.afight.MonsterCache;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.mapper.MonsterMapper;
import com.tongzhu.user.mapper.ext.MonsterExtMapper;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.*;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterExtMapper monsterExtMapper;

    @Autowired
    private MonsterMapper monsterMapper;

    @Autowired
    private CopyMonsterService copyMonsterService;

    @Autowired
    private UserCopySettingService userCopySettingService;

    @Autowired
    private SkillService skillService;
    @Autowired
    private SkillSettingService skillSettingService;
    @Autowired
    private FightCopyService fightCopyService;




    @Override
    public List<MonsterDO> findByCopyId(Integer copyId) {
        List<MonsterDO> result=new ArrayList<>();
        List<MonsterDO> list= monsterExtMapper.selectByCopyIdOrderByGroup(copyId);
        List<MonsterDO> multRandom=new ArrayList<>();

        for(MonsterDO monsterDO:list){
            if(monsterDO.getRandomStatus()==1){
                multRandom.add(monsterDO);
                continue;
            }
            if(!CollectionUtils.isEmpty(multRandom)){
                int index= RandomUtil.random(0,multRandom.size()-1);
                monsterDO=multRandom.get(index);
            }
            for(int i=0;i<monsterDO.getLimit();i++){
                MonsterDO monster=new MonsterDO();
                BeanUtils.copyProperties(monsterDO,monster);
                result.add(monster);
            }
            multRandom=new ArrayList<>();
        }
        return result;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {

        boolean notNull = false;
        List<Monster> userList = new ArrayList<>();
        List<CopyMonster> copyMonsterList=new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        userCopySettingService.deleteAll();
        Monster monster;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {

            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            monster = new Monster();
            String monsterId=(int)(row.getCell(0).getNumericCellValue())+"";
            String name=row.getCell(1).getStringCellValue();
            String head=(int)(row.getCell(2).getNumericCellValue())+"";
            String mode=(int)(row.getCell(3).getNumericCellValue())+"";
            Integer type=(int)(row.getCell(4).getNumericCellValue());
            Integer level=(int)(row.getCell(6).getNumericCellValue());
            Float phAtk=(float) (row.getCell(7).getNumericCellValue());
            Float mfAtk=(float)(row.getCell(8).getNumericCellValue());
            Float phDef=(float)(row.getCell(9).getNumericCellValue());
            Float mfDef=(float)(row.getCell(10).getNumericCellValue());
            Float hp=(float)(row.getCell(11).getNumericCellValue());
            Float accuracy=(float)(row.getCell(12).getNumericCellValue());
            Float miss=(float)(row.getCell(13).getNumericCellValue());
            Float critical=(float)(row.getCell(14).getNumericCellValue());
            Float dcritical=(float)(row.getCell(15).getNumericCellValue());
            Integer skill1Id=(int)(row.getCell(16).getNumericCellValue());
            Integer skill1Lv=(int)(row.getCell(17).getNumericCellValue());
            Integer skill2Id=(int)(row.getCell(18).getNumericCellValue());
            Integer skill2Lv=(int)(row.getCell(19).getNumericCellValue());
            Integer skill3Id=(int)(row.getCell(20).getNumericCellValue());
            Integer skill3Lv=(int)(row.getCell(21).getNumericCellValue());
            Float realHurt=(float)(row.getCell(22).getNumericCellValue());
            String drop=row.getCell(23).getStringCellValue();
            String userTalk=row.getCell(24).getStringCellValue();
            String talk=row.getCell(25).getStringCellValue();
            Integer limit=(int)(row.getCell(26).getNumericCellValue());

            Integer copyId=(int)(row.getCell(27).getNumericCellValue());
            Integer groupId=(int)(row.getCell(29).getNumericCellValue());
            Integer isRandom=(int)(row.getCell(30).getNumericCellValue());

            monster.setMonsterId(monsterId);
            monster.setName(name);
            monster.setHead(head);
            monster.setMode(mode);
            monster.setType(type);
            monster.setLevel(level);
            monster.setPhAtk(phAtk);
            monster.setMfAtk(mfAtk);
            monster.setPhDef(phDef);
            monster.setMfDef(mfDef);
            monster.setHp(hp);
            monster.setAccuracy(accuracy);
            monster.setMiss(miss);
            monster.setCritical(critical);
            monster.setDcritical(dcritical);
            monster.setSkill1Id(skill1Id);
            monster.setSkill1Lv(skill1Lv);
            monster.setSkill2Id(skill2Id);
            monster.setSkill2Lv(skill2Lv);
            monster.setSkill3Id(skill3Id);
            monster.setSkill3Lv(skill3Lv);
            monster.setRealHurt(realHurt);
            monster.setDrop(drop);
            if(StringUtils.isNotBlank(talk.trim())){
                monster.setTalk(talk);
            }
            monster.setLimit(limit);
            userList.add(monster);

            CopyMonster copyMonster=new CopyMonster();
            copyMonster.setId(UUIDUtil.generateUUID());
            copyMonster.setGroupId(groupId);
            copyMonster.setCopyId(copyId);
            copyMonster.setMonsterId(monsterId);
            copyMonster.setRandomStatus(isRandom);
            copyMonster.setLimit(limit);
            copyMonsterList.add(copyMonster);

            if(StringUtils.isNotBlank(userTalk)){
                UserCopySetting setting=new UserCopySetting();
                setting.setGroupId(groupId);
                setting.setContent(userTalk);
                setting.setCopyId(copyId);
                userCopySettingService.add(setting);
            }
        }

        MonsterExample example=new MonsterExample();
        monsterMapper.deleteByExample(example);
        copyMonsterService.deleteAll();
        monsterExtMapper.batchInsert(userList);
        copyMonsterService.batchInsert(copyMonsterList);
        return notNull;
    }

    @Override
    public List<MonsterDO> findByExplorationIdAndPassId(Integer explorationId, Integer passId) {
        return monsterExtMapper.selectByExplorationIdAndPassId(explorationId,passId);
    }

    @Override
    public void cacheMonster(List<MonsterDO> monsterList, Integer copyId) {
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Combatant> combatantList=new ArrayList<>();
        int groupIndex=0;
        int tempGroup=0;
        int childIndex=0;
        int i=0;
        for(MonsterDO monster:monsterList){
            if(tempGroup<monster.getGroupId()){
                i++;
                if(i<=3){
                    Map<String,Object> monsterMap=new HashMap<>();
                    monsterMap.put("blood",monster.getHp().intValue());
                    monsterMap.put("userId",monster.getMonsterId());
                    monsterMap.put("furyValue",0);
                    monsterMap.put("roleLevel",monster.getLevel());
                    monsterMap.put("headUrl",monster.getHead());
                    monsterMap.put("model",monster.getMode());
                    monsterMap.put("name",monster.getName());
                    monsterMap.put("skillList",new ArrayList<>());
                    monsterMap.put("weaponList",new ArrayList<>());
                    monsterMap.put("count",1);
                    mapList.add(monsterMap);
                }

                tempGroup=monster.getGroupId();
                groupIndex++;
                childIndex=1;

                Combatant combatant=new Combatant();
                List<Combatant> children=new ArrayList<>();
                Combatant child=new Combatant();
                combatant.setExpAward(0);
                child.setExpAward(0);
                combatant.setGroup(2);
                child.setGroup(2);
                combatant.setGroupIndex(groupIndex);
                child.setGroupIndex(childIndex);
                combatant.setTalk(monster.getTalk());
                child.setTalk(monster.getTalk());
                combatant.setBlood(monster.getHp().intValue());
                child.setBlood(monster.getHp().intValue());
                combatant.setFullBlood(monster.getHp().intValue());
                child.setFullBlood(monster.getHp().intValue());
                //combatant.setFuryValue(0);
                //child.setFuryValue(0);
                combatant.setRoleLevel(monster.getLevel());
                child.setRoleLevel(monster.getLevel());
                combatant.setSex(1);
                child.setSex(1);
                combatant.setUserId(monster.getMonsterId());
                child.setUserId(monster.getMonsterId());
                combatant.setUserName(monster.getName());
                child.setUserName(monster.getName());
                child.setMonster(monster);
                List<UserSkillVO> skillVOList=new ArrayList<>();
                List<SkillSetting> settingParams=new ArrayList<>();
                if(monster.getSkill1Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill1Id());
                    skillVO.setSkillLevel(monster.getSkill1Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill1Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill1Id());
                    setting.setSkillLevel(monster.getSkill1Lv());
                    settingParams.add(setting);
                }
                if(monster.getSkill2Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill2Id());
                    skillVO.setSkillLevel(monster.getSkill2Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill2Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill2Id());
                    setting.setSkillLevel(monster.getSkill2Lv());
                    settingParams.add(setting);
                }
                if(monster.getSkill3Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill3Id());
                    skillVO.setSkillLevel(monster.getSkill3Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill3Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill3Id());
                    setting.setSkillLevel(monster.getSkill3Lv());
                    settingParams.add(setting);
                }
                child.setSkillList(skillVOList);
                if(!settingParams.isEmpty()){
                    List<SkillSetting> skillSettings=skillSettingService.selectSkillSettingBySkillIdAndLevel(settingParams);
                    child.setSkillSettingList(skillSettings);
                }
                //武器
                child.setWeaponList(new ArrayList<>());
                //装备
                child.setEquipmentList(new ArrayList<>());
                children.add(child);
                combatant.setCombatantList(children);
                combatant.setSkillList(skillVOList);
                combatantList.add(combatant);
            }else{
                if(tempGroup<=3){
                    Integer blood=(int)((int)mapList.get(mapList.size()-1).get("blood")+monster.getHp());
                    mapList.get(mapList.size()-1).put("blood",blood);
                }
                if(i<=3){
                    int count=(int)(mapList.get(mapList.size()-1).get("count"))+1;
                    mapList.get(mapList.size()-1).put("count",count);
                }
                childIndex++;
                Combatant combatant=combatantList.get(combatantList.size()-1);
                List<Combatant> children=combatant.getCombatantList();
                Combatant child=new Combatant();
                child.setGroup(2);
                child.setTalk(monster.getTalk());
                child.setGroupIndex(childIndex);
                int bloodCount=combatant.getBlood()+monster.getHp().intValue();
                combatant.setBlood(bloodCount);
                child.setBlood(monster.getHp().intValue());
                combatant.setFullBlood(bloodCount);
                child.setFullBlood(monster.getHp().intValue());
                //child.setFuryValue(0);
                child.setRoleLevel(monster.getLevel());
                child.setSex(1);
                child.setUserId(monster.getMonsterId());
                child.setUserName(monster.getName());
                child.setMonster(monster);
                List<UserSkillVO> skillVOList=new ArrayList<>();
                List<SkillSetting> settingParams=new ArrayList<>();
                if(monster.getSkill1Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill1Id());
                    skillVO.setSkillLevel(monster.getSkill1Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill1Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill1Id());
                    setting.setSkillLevel(monster.getSkill1Lv());
                    settingParams.add(setting);
                }
                if(monster.getSkill2Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill2Id());
                    skillVO.setSkillLevel(monster.getSkill2Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill2Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill2Id());
                    setting.setSkillLevel(monster.getSkill2Lv());
                    settingParams.add(setting);
                }
                if(monster.getSkill3Id()>0){
                    UserSkillVO skillVO=new UserSkillVO();
                    skillVO.setSkillId(monster.getSkill3Id());
                    skillVO.setSkillLevel(monster.getSkill3Lv());
                    Skill ski=skillService.findBySkillId(monster.getSkill3Id());
                    skillVO.setFreezeCount(0);
                    skillVO.setType(ski.getType());
                    skillVO.setCoolingSetting(ski.getCoolingCircle());
                    skillVOList.add(skillVO);

                    SkillSetting setting=new SkillSetting();
                    setting.setSkillId(monster.getSkill3Id());
                    setting.setSkillLevel(monster.getSkill3Lv());
                    settingParams.add(setting);
                }
                child.setSkillList(skillVOList);
                if(settingParams!=null && !settingParams.isEmpty()){
                    List<SkillSetting> skillSettings=skillSettingService.selectSkillSettingBySkillIdAndLevel(settingParams);
                    child.setSkillSettingList(skillSettings);
                }

                //武器
                child.setWeaponList(new ArrayList<>());
                //装备
                child.setEquipmentList(new ArrayList<>());
                children.add(child);
            }
        }
        MonsterCache.monsters.put(copyId,combatantList);
        MonsterCache.mapList.put(copyId,mapList);
    }

    @Override
    public void initCacheMonster() {
        List<FightCopy> fightCopyList=fightCopyService.findAll();
        if(!CollectionUtils.isEmpty(fightCopyList)){
            for(FightCopy copy:fightCopyList){
                List<MonsterDO> monsterDOList=this.findByCopyId(copy.getId());
                this.cacheMonster(monsterDOList,copy.getId());
            }
            List<MonsterDO> monsterDOList=this.findByCopyId(999);
            this.cacheMonster(monsterDOList,999);
        }
    }
}
