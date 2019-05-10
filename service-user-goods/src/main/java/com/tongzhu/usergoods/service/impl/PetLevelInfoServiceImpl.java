package com.tongzhu.usergoods.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.PetLevelInfoMapper;
import com.tongzhu.usergoods.mapper.ext.UserPetExtMapper;
import com.tongzhu.usergoods.model.PetLevelInfo;
import com.tongzhu.usergoods.model.UserPetExample;
import com.tongzhu.usergoods.service.PetLevelInfoService;
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

/**
 * Created by Administrator on 2018/12/7 0007.
 */
@Service
public class PetLevelInfoServiceImpl implements PetLevelInfoService{

    @Autowired
    private PetLevelInfoMapper petLevelInfoMapper;

    @Autowired
    private UserPetExtMapper userPetExtMapper;


    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<PetLevelInfo> petLevelInfoList = new ArrayList<>();
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

        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            PetLevelInfo petLevelInfo = new PetLevelInfo();
            Integer level= new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer nextLevel= new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer exp= new Double(row.getCell(2).getNumericCellValue()).intValue();
            petLevelInfo.setLevel(level);
            petLevelInfo.setNextLevel(nextLevel);
            petLevelInfo.setExp(exp);
            petLevelInfoList.add(petLevelInfo);
        }
        for (PetLevelInfo petLevelInfo : petLevelInfoList) {
            PetLevelInfo pet = petLevelInfoMapper.selectByPrimaryKey(petLevelInfo.getLevel());

            if (pet == null) {
                petLevelInfoMapper.insert(petLevelInfo);
                System.out.println("插入成功：" + petLevelInfo.getLevel());
            } else {
                petLevelInfoMapper.updateByPrimaryKeySelective(petLevelInfo);
                System.out.println(" 更新成功:" + petLevelInfo.getLevel());
            }
        }
        return notNull;

    }

    @Override
    public PetLevelInfo getPetLevelInfoByLevel(Integer level) {
        PetLevelInfo petLevelInfo = petLevelInfoMapper.selectByPrimaryKey(level);
        return petLevelInfo;
    }

    @Override
    public Integer getPetLevelPet(Integer minLevel, Integer maxLevel) {
        return userPetExtMapper.getPetLevelPet(minLevel,maxLevel);
    }
}
