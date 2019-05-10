package com.tongzhu.treehouse.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.TreeHouseWorkSettingMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHouseWorkSettingExtMapper;
import com.tongzhu.treehouse.model.TreeHouseWorkSetting;
import com.tongzhu.treehouse.model.TreeHouseWorkSettingExample;
import com.tongzhu.treehouse.service.TreeHouseWorkSettingService;
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
public class TreeHouseWorkSettingServiceImpl implements TreeHouseWorkSettingService {

    @Autowired
    private TreeHouseWorkSettingMapper treeHouseWorkSettingMapper;

    @Autowired
    private TreeHouseWorkSettingExtMapper treeHouseWorkSettingExtMapper;


    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<TreeHouseWorkSetting> settingList = new ArrayList<>();
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
        TreeHouseWorkSetting setting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            setting = new TreeHouseWorkSetting();
            Integer roleLevel=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer output=new Double(row.getCell(1).getNumericCellValue()).intValue();

            setting.setOutput(output);
            setting.setRoleLevel(roleLevel);
            settingList.add(setting);
        }
        TreeHouseWorkSettingExample example=new TreeHouseWorkSettingExample();
        treeHouseWorkSettingMapper.deleteByExample(example);
        treeHouseWorkSettingExtMapper.batchInsert(settingList);
        return notNull;
    }

    @Override
    public TreeHouseWorkSetting getByRoleLevel(Integer roleLevel) {

        return treeHouseWorkSettingMapper.selectByPrimaryKey(roleLevel);
    }
}
