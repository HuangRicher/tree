package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.RankingExpSettingMapper;
import com.tongzhu.user.mapper.ext.RankingExpSettingExtMapper;
import com.tongzhu.user.model.RankingExpSetting;
import com.tongzhu.user.model.RankingExpSettingExample;
import com.tongzhu.user.service.FightRankingExpSettingService;
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
public class FightRankingExpSettingServiceImpl implements FightRankingExpSettingService{

    @Autowired
    private RankingExpSettingMapper rankingExpSettingMapper;
    @Autowired
    private RankingExpSettingExtMapper rankingExpSettingExtMapper;


    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<RankingExpSetting> settingList = new ArrayList<>();
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
        RankingExpSetting setting;
        for (int r = 1; r <= sheet1.getLastRowNum(); r++) {
            Row row = sheet1.getRow(r);
            if (row == null){
                continue;
            }
            setting = new RankingExpSetting();
            Integer level=new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer expsu=new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer expf=new Double(row.getCell(2).getNumericCellValue()).intValue();
            setting.setRoleLevel(level);
            setting.setFailExp(expf);
            setting.setWinExp(expsu);
            settingList.add(setting);
        }

        RankingExpSettingExample example=new RankingExpSettingExample();
        rankingExpSettingMapper.deleteByExample(example);
        rankingExpSettingExtMapper.batchInsert(settingList);

        return notNull;
    }
}
