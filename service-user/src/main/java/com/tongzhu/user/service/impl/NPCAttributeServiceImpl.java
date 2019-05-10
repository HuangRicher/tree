package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.NPCAttributeMapper;
import com.tongzhu.user.mapper.ext.NPCAttributeExtMapper;
import com.tongzhu.user.model.NPCAttribute;
import com.tongzhu.user.model.NPCAttributeExample;
import com.tongzhu.user.service.NPCAttributeService;
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
public class NPCAttributeServiceImpl implements NPCAttributeService {

    @Autowired
    private NPCAttributeExtMapper npcAttributeExtMapper;

    @Autowired
    private NPCAttributeMapper npcAttributeMapper;



    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<NPCAttribute> npcAttributeList = new ArrayList<>();
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
            NPCAttribute npcAttribute = new NPCAttribute();
            Integer level=(int)(row.getCell(0).getNumericCellValue());
            Integer phAtk=(int)(row.getCell(1).getNumericCellValue());
            Integer mfAtk=(int)(row.getCell(2).getNumericCellValue());
            Integer phDef=(int)(row.getCell(3).getNumericCellValue());
            Integer mfDef=(int)(row.getCell(4).getNumericCellValue());
            Integer hp=(int)(row.getCell(5).getNumericCellValue());
            Integer accuracy=(int)(row.getCell(7).getNumericCellValue());
            Integer miss=(int)(row.getCell(8).getNumericCellValue());
            Integer critical=(int)(row.getCell(9).getNumericCellValue());
            Integer decritical=(int)(row.getCell(10).getNumericCellValue());

            npcAttribute.setLevelId(level);
            npcAttribute.setPhAtk(phAtk);
            npcAttribute.setMfAtk(mfAtk);
            npcAttribute.setPhDef(phDef);
            npcAttribute.setMfDef(mfDef);
            npcAttribute.setHp(hp);
            npcAttribute.setAccuracy(accuracy);
            npcAttribute.setMiss(miss);
            npcAttribute.setCritical(critical);
            npcAttribute.setDcritical(decritical);

            npcAttributeList.add(npcAttribute);
        }
        NPCAttributeExample example=new NPCAttributeExample();
        npcAttributeMapper.deleteByExample(example);
        npcAttributeExtMapper.batchInsert(npcAttributeList);
        return notNull;
    }

    @Override
    public NPCAttribute findByLevelId(Integer levelId) {
        return npcAttributeMapper.selectByPrimaryKey(levelId);
    }
}
