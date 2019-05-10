package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.ExplorationMonsterMapper;
import com.tongzhu.user.mapper.ext.ExplorationMonsterExtMapper;
import com.tongzhu.user.mapper.ext.MonsterExtMapper;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.ExplorationMonster;
import com.tongzhu.user.model.Monster;
import com.tongzhu.user.service.ExplorationMonsterService;
import com.tongzhu.user.service.MonsterService;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorationMonsterServiceImpl implements ExplorationMonsterService {

    @Autowired
    private ExplorationMonsterExtMapper explorationMonsterExtMapper;

    @Autowired
    private ExplorationMonsterMapper explorationMonsterMapper;

    @Autowired
    private MonsterExtMapper monsterExtMapper;

    @Autowired
    private MonsterService monsterService;




    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<Monster> userList = new ArrayList<>();
        List<ExplorationMonster> explorationMonsterList=new ArrayList<>();
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
            String talk=row.getCell(24).getStringCellValue();
            Integer limit=(int)(row.getCell(25).getNumericCellValue());

            Integer explorationId=(int)(row.getCell(26).getNumericCellValue());
            Integer passId=(int)(row.getCell(27).getNumericCellValue());
            Integer groupId=(int)(row.getCell(28).getNumericCellValue());

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
            monster.setTalk(talk);
            monster.setLimit(limit);
            userList.add(monster);

            for (int i=0;i<limit;i++){
                ExplorationMonster explorationMonster=new ExplorationMonster();
                explorationMonster.setId(UUIDUtil.generateUUID());
                explorationMonster.setGroupId(groupId);
                explorationMonster.setExplorationId(explorationId);
                explorationMonster.setPassId(passId);
                explorationMonster.setMonsterId(monsterId);
                explorationMonsterList.add(explorationMonster);
            }
        }


        monsterExtMapper.batchInsert(userList);
        explorationMonsterExtMapper.batchInsert(explorationMonsterList);
        return notNull;
    }

    @Override
    public List<MonsterDO> findByExplorationIdAndPassId(Integer explorationId, Integer passId) {
        return monsterService.findByExplorationIdAndPassId(explorationId,passId);
    }
}
