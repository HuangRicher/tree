package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.ExplorationSettingMapper;
import com.tongzhu.user.mapper.ext.ExplorationSettingExtMapper;
import com.tongzhu.user.model.ExplorationSetting;
import com.tongzhu.user.model.ExplorationSettingExample;
import com.tongzhu.user.service.ExplorationSettingService;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExplorationSettingServiceImpl implements ExplorationSettingService {


    @Autowired
    private ExplorationSettingExtMapper explorationSettingExtMapper;

    @Autowired
    private ExplorationSettingMapper explorationSettingMapper;



    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<ExplorationSetting> settingList = new ArrayList<>();
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
        Sheet sheet1 = wb.getSheetAt(0);

        if(sheet1!=null){
            notNull = true;
        }
        ExplorationSetting setting;
        for (int r = 1; r <= sheet1.getLastRowNum(); r++) {
            Row row = sheet1.getRow(r);
            if (row == null){
                continue;
            }
            setting = new ExplorationSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer firstExp=new Double(row.getCell(9).getNumericCellValue()).intValue();
            Integer nextExp=new Double(row.getCell(10).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setFirstExp(firstExp);
            setting.setNexExp(nextExp);
            setting.setExplorationId(((level-1)/40)+1);
            setting.setPassId((level%40)==0?40:(level%40));
            settingList.add(setting);
        }
        ExplorationSettingExample example=new ExplorationSettingExample();
        explorationSettingMapper.deleteByExample(example);
        explorationSettingExtMapper.batchInsert(settingList);
        return notNull;
    }

    @Override
    public ExplorationSetting getByExplorationIdAndPassId(Integer explorationId, Integer passId) {
        ExplorationSettingExample example=new ExplorationSettingExample();
        example.createCriteria().andExplorationIdEqualTo(explorationId).andPassIdEqualTo(passId);
        List<ExplorationSetting> settingList=explorationSettingMapper.selectByExample(example);
        if(settingList!=null && !settingList.isEmpty()){
            return settingList.get(0);
        }
        return null;
    }
}
