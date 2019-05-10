package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.CopyExpSettingMapper;
import com.tongzhu.user.mapper.ext.CopyExpSettingExtMapper;
import com.tongzhu.user.model.CopyExpSetting;
import com.tongzhu.user.model.CopyExpSettingExample;
import com.tongzhu.user.model.FightFriendExpSetting;
import com.tongzhu.user.service.CopyExpSettingService;
import com.tongzhu.user.service.FightFriendExpSettingService;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
@CacheConfig(cacheNames="copy_exp_setting")
public class CopyExpSettingServiceImpl implements CopyExpSettingService {


    @Autowired
    private CopyExpSettingExtMapper copyExpSettingExtMapper;

    @Autowired
    private CopyExpSettingMapper copyExpSettingMapper;

    @Autowired
    private FightFriendExpSettingService fightFriendExpSettingService;


    //@Cacheable(key = "#p0+'_rolelevel_copyid_'+#p1")
    @Override
    public CopyExpSetting getByRoleLevelAndCopyId(Integer roleLevel, Integer copyId) {
        CopyExpSettingExample example=new CopyExpSettingExample();
        example.createCriteria().andRoleLevelEqualTo(roleLevel).andCopyIdEqualTo(copyId);
        List<CopyExpSetting> settingList=copyExpSettingMapper.selectByExample(example);
        if(settingList!=null && !settingList.isEmpty()){
            return settingList.get(0);
        }
        return null;
    }

    //@CacheEvict(value="copy_exp_setting")
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<CopyExpSetting> settingList = new ArrayList<>();
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
        Sheet sheet2 = wb.getSheetAt(1);
        Sheet sheet3 = wb.getSheetAt(2);
        Sheet sheet4 = wb.getSheetAt(3);
        Sheet sheet5 = wb.getSheetAt(4);
        Sheet sheet6 = wb.getSheetAt(5);
        Sheet sheet7 = wb.getSheetAt(6);

        if(sheet1!=null){
            notNull = true;
        }
        CopyExpSetting setting;
        for (int r = 1; r <= sheet1.getLastRowNum(); r++) {
            Row row = sheet1.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(1);
            settingList.add(setting);
        }

        for (int r = 1; r <= sheet2.getLastRowNum(); r++) {
            Row row = sheet2.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(2);
            settingList.add(setting);
        }

        for (int r = 1; r <= sheet3.getLastRowNum(); r++) {
            Row row = sheet3.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(3);
            settingList.add(setting);
        }

        for (int r = 1; r <= sheet4.getLastRowNum(); r++) {
            Row row = sheet4.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(4);
            settingList.add(setting);
        }

        for (int r = 1; r <= sheet5.getLastRowNum(); r++) {
            Row row = sheet5.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(5);
            settingList.add(setting);
        }

        for (int r = 1; r <= sheet6.getLastRowNum(); r++) {
            Row row = sheet6.getRow(r);
            if (row == null){
                continue;
            }
            setting = new CopyExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer exp=new Double(row.getCell(4).getNumericCellValue()).intValue();
            setting.setId(UUIDUtil.generateUUID());
            setting.setRoleLevel(level);
            setting.setCopyExp(exp);
            setting.setCopyId(6);
            settingList.add(setting);
        }

        List<FightFriendExpSetting> fightFriendExpSettings=new ArrayList<>();
        for (int r = 1; r <= sheet7.getLastRowNum(); r++) {
            Row row = sheet7.getRow(r);
            if (row == null){
                continue;
            }
            FightFriendExpSetting setting1 = new FightFriendExpSetting();
            Integer roleLevel=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer dayMax=new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer successExp=new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer failExp=new Double(row.getCell(3).getNumericCellValue()).intValue();

            setting1.setRoleLevel(roleLevel);
            setting1.setDayMax(dayMax);
            setting1.setSuccessExp(successExp);
            setting1.setFailExp(failExp);
            fightFriendExpSettings.add(setting1);
        }
        fightFriendExpSettingService.batchInsert(fightFriendExpSettings);
        CopyExpSettingExample example=new CopyExpSettingExample();
        copyExpSettingMapper.deleteByExample(example);
        copyExpSettingExtMapper.batchInsert(settingList);

        return notNull;
    }
}
