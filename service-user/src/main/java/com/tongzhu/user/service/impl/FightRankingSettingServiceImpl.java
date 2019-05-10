package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.FightRankingSettingMapper;
import com.tongzhu.user.mapper.ext.FightRankingSettingExtMapper;
import com.tongzhu.user.model.FightRankingSetting;
import com.tongzhu.user.model.FightRankingSettingExample;
import com.tongzhu.user.service.FightRankingSettingService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames="fight_ranking_setting")
public class FightRankingSettingServiceImpl implements FightRankingSettingService {

    @Autowired
    private FightRankingSettingMapper fightRankingSettingMapper;

    @Autowired
    private FightRankingSettingExtMapper fightRankingSettingExtMapper;



    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<FightRankingSetting> settingList = new ArrayList<>();
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
        FightRankingSetting setting;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            setting = new FightRankingSetting();
            String name=row.getCell(0).getStringCellValue();
            Integer min=new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer max=new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer news=new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer daymoney=new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer seasonmoney=new Double(row.getCell(5).getNumericCellValue()).intValue();

            setting.setName(name);
            setting.setMaxRank(max);
            setting.setMinRank(min);
            setting.setAwardMoneyDay(daymoney);
            setting.setAwardMoneySeason(seasonmoney);
            setting.setFirstRank(news);

            settingList.add(setting);
        }

        /*for (FightRankingSetting settings : settingList) {
            fightRankingSettingMapper.insert(settings);
            System.out.println(" 插入 "+settings);
        }*/
        FightRankingSettingExample example=new FightRankingSettingExample();
        fightRankingSettingMapper.deleteByExample(example);
        fightRankingSettingExtMapper.batchInsert(settingList);
        return notNull;
    }

    @Cacheable(key = "#p0+'_ranking_grade_'+#p1")
    @Override
    public FightRankingSetting getByRankIdAndGrade(Integer ranking, Integer grade) {
        FightRankingSettingExample example=new FightRankingSettingExample();
        example.createCriteria().andRankIdEqualTo(ranking).andGradeEqualTo(grade);
        List<FightRankingSetting> settings=fightRankingSettingMapper.selectByExample(example);
        if(settings!=null && !settings.isEmpty()){
            return settings.get(0);
        }
        return null;
    }

    @Override
    public FightRankingSetting getByScore(Integer score) {
        FightRankingSettingExample example=new FightRankingSettingExample();
        example.createCriteria().andMinRankLessThanOrEqualTo(score).andMaxRankGreaterThanOrEqualTo(score);
        List<FightRankingSetting> settings=fightRankingSettingMapper.selectByExample(example);
        if(settings!=null && !settings.isEmpty()){
            return settings.get(0);
        }
        return null;
    }
}
