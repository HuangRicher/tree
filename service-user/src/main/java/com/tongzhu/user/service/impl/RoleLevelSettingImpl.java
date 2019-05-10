package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.RoleLevelSettingMapper;
import com.tongzhu.user.mapper.ext.RoleLevelSettingExtMapper;
import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.RoleLevelSettingExample;
import com.tongzhu.user.service.RoleLevelSettingService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleLevelSettingImpl implements RoleLevelSettingService {

    @Autowired
    private RoleLevelSettingMapper roleLevelSettingMapper;

    @Autowired
    private RoleLevelSettingExtMapper roleLevelSettingExtMapper;



    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {

        boolean notNull = false;
        List<RoleLevelSetting> userList = new ArrayList<>();
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
        RoleLevelSetting skill;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {

            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            if(r>153)
                break;

            skill = new RoleLevelSetting();
            Integer level= new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer nextLevel= new Double(row.getCell(1).getNumericCellValue()).intValue();
            Long exp= new Double(row.getCell(2).getNumericCellValue()).longValue();
            //Integer blood= new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer wsHp= new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer wsPhAtk= new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer wsMfAtk= new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer wsPhDef= new Double(row.getCell(6).getNumericCellValue()).intValue();
            Integer wsMfDef= new Double(row.getCell(7).getNumericCellValue()).intValue();
            Integer wsAccuracy= new Double(row.getCell(8).getNumericCellValue()).intValue();
            Integer wsMiss= new Double(row.getCell(9).getNumericCellValue()).intValue();
            Integer wsCritical= new Double(row.getCell(10).getNumericCellValue()).intValue();
            Integer wsDcritical= new Double(row.getCell(11).getNumericCellValue()).intValue();
            Integer ckHp= new Double(row.getCell(12).getNumericCellValue()).intValue();
            Integer ckPhAtk= new Double(row.getCell(13).getNumericCellValue()).intValue();
            Integer ckMfAtk= new Double(row.getCell(14).getNumericCellValue()).intValue();
            Integer ckPhDef= new Double(row.getCell(15).getNumericCellValue()).intValue();
            Integer ckMfDef= new Double(row.getCell(16).getNumericCellValue()).intValue();
            Integer ckAccuracy= new Double(row.getCell(17).getNumericCellValue()).intValue();
            Integer ckMiss= new Double(row.getCell(18).getNumericCellValue()).intValue();
            Integer ckCritical= new Double(row.getCell(19).getNumericCellValue()).intValue();
            Integer ckDcritical= new Double(row.getCell(20).getNumericCellValue()).intValue();
            Integer gjHp= new Double(row.getCell(21).getNumericCellValue()).intValue();
            Integer gjPhAtk= new Double(row.getCell(22).getNumericCellValue()).intValue();
            Integer gjMfAtk= new Double(row.getCell(23).getNumericCellValue()).intValue();
            Integer gjPhDef= new Double(row.getCell(24).getNumericCellValue()).intValue();
            Integer gjMfDef= new Double(row.getCell(25).getNumericCellValue()).intValue();
            Integer gjAccuracy= new Double(row.getCell(26).getNumericCellValue()).intValue();
            Integer gjMiss= new Double(row.getCell(27).getNumericCellValue()).intValue();
            Integer gjCritical= new Double(row.getCell(28).getNumericCellValue()).intValue();
            Integer gjDcritical= new Double(row.getCell(29).getNumericCellValue()).intValue();
            Integer fsHp= new Double(row.getCell(30).getNumericCellValue()).intValue();
            Integer fsPhAtk= new Double(row.getCell(31).getNumericCellValue()).intValue();
            Integer fsMfAtk= new Double(row.getCell(32).getNumericCellValue()).intValue();
            Integer fsPhDef= new Double(row.getCell(33).getNumericCellValue()).intValue();
            Integer fsMfDef= new Double(row.getCell(34).getNumericCellValue()).intValue();
            Integer fsAccuracy= new Double(row.getCell(35).getNumericCellValue()).intValue();
            Integer fsMiss= new Double(row.getCell(36).getNumericCellValue()).intValue();
            Integer fsCritical= new Double(row.getCell(37).getNumericCellValue()).intValue();
            Integer fsDcritical= new Double(row.getCell(38).getNumericCellValue()).intValue();

            skill.setLevel(level);
            skill.setNextLevel(nextLevel);
            skill.setExp(exp);
            skill.setWsHp(wsHp);
            skill.setWsPhAtk(wsPhAtk);
            skill.setWsMfAtk(wsMfAtk);
            skill.setWsPhDef(wsPhDef);
            skill.setWsMfDef(wsMfDef);
            skill.setWsAccuracy(wsAccuracy);
            skill.setWsMiss(wsMiss);
            skill.setWsCritical(wsCritical);
            skill.setWsDcritical(wsDcritical);
            skill.setCkHp(ckHp);
            skill.setCkPhAtk(ckPhAtk);
            skill.setCkMfAtk(ckMfAtk);
            skill.setCkPhDef(ckPhDef);
            skill.setCkMfDef(ckMfDef);
            skill.setCkAccuracy(ckAccuracy);
            skill.setCkMiss(ckMiss);
            skill.setCkCritical(ckCritical);
            skill.setCkDcritical(ckDcritical);
            skill.setGjHp(gjHp);
            skill.setGjPhAtk(gjPhAtk);
            skill.setGjMfAtk(gjMfAtk);
            skill.setGjPhDef(gjPhDef);
            skill.setGjMfDef(gjMfDef);
            skill.setGjAccuracy(gjAccuracy);
            skill.setGjMiss(gjMiss);
            skill.setGjCritical(gjCritical);
            skill.setGjDcritical(gjDcritical);
            skill.setFsHp(fsHp);
            skill.setFsPhAtk(fsPhAtk);
            skill.setFsMfAtk(fsMfAtk);
            skill.setFsPhDef(fsPhDef);
            skill.setFsMfDef(fsMfDef);
            skill.setFsAccuracy(fsAccuracy);
            skill.setFsMiss(fsMiss);
            skill.setFsCritical(fsCritical);
            skill.setFsDcritical(fsDcritical);
            userList.add(skill);
        }

        /*for (RoleLevelSetting userResord : userList) {
            roleLevelSettingMapper.insert(userResord);
            System.out.println(" 插入 "+userResord);
        }*/
        RoleLevelSettingExample example=new RoleLevelSettingExample();
        roleLevelSettingMapper.deleteByExample(example);
        roleLevelSettingExtMapper.batchInsert(userList);
        return notNull;
    }

    @Override
    public RoleLevelSetting getByLevel(Integer level) {
        return roleLevelSettingMapper.selectByPrimaryKey(level);
    }

}
