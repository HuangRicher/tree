package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.FlowerNumberSettingMapper;
import com.tongzhu.treehouse.mapper.FlowerSeedsDrawSettingMapper;
import com.tongzhu.treehouse.mapper.FlowerSettingMapper;
import com.tongzhu.treehouse.mapper.ext.FlowerSettingExtMapper;
import com.tongzhu.treehouse.model.*;
import com.tongzhu.treehouse.service.FlowerSettingService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowerSettingServiceImpl implements FlowerSettingService {

    @Autowired
    private FlowerSettingMapper flowerSettingMapper;

    @Autowired
    private FlowerNumberSettingMapper flowerNumberSettingMapper;
    @Autowired
    private FlowerSeedsDrawSettingMapper flowerSeedsDrawSettingMapper;


    @Autowired
    private FlowerSettingExtMapper flowerSettingExtMapper;




    @Override
    public FlowerSetting getById(String id) {
        return flowerSettingMapper.selectByPrimaryKey(id);
    }


    @Transactional
    @Override
    public boolean batchImport(MultipartFile seedsDraw, MultipartFile flowerSeeds, MultipartFile flowerNumber) throws IOException {
        boolean notNull = false;
        List<FlowerSetting> settingList = new ArrayList<>();
        List<FlowerSeedsDrawSetting> drawSettings=new ArrayList<>();
        List<FlowerNumberSetting> numberSettings=new ArrayList<>();

        Sheet sheetflowerSeeds = generate(flowerSeeds);
        Sheet sheetseedsDraw = generate(seedsDraw);
        Sheet sheetflowerNumber = generate(flowerNumber);

        for (int r = 3; r <= sheetflowerSeeds.getLastRowNum(); r++) {
            Row row = sheetflowerSeeds.getRow(r);
            if (row == null){
                continue;
            }
            FlowerSetting setting = new FlowerSetting();
            String id=((int)row.getCell(0).getNumericCellValue())+"";
            Integer outEnvironment=(int)(row.getCell(7).getNumericCellValue());
            Integer outMoney=(int) (row.getCell(8).getNumericCellValue());

            setting.setId(id);
            setting.setMoneyAward(outMoney);
            setting.setEnviromentAward(outEnvironment);
            settingList.add(setting);
        }

        for (int r = 3; r <= sheetseedsDraw.getLastRowNum(); r++) {
            Row row = sheetseedsDraw.getRow(r);
            if (row == null){
                continue;
            }
            FlowerSeedsDrawSetting setting = new FlowerSeedsDrawSetting();
            String id=((int)row.getCell(0).getNumericCellValue())+"";
            float  probability=(float)(row.getCell(1).getNumericCellValue());
            Integer limit=(int) (row.getCell(2).getNumericCellValue());

            setting.setGoodsId(id);
            setting.setProbability(probability);
            setting.setUpperLimit(limit);
            drawSettings.add(setting);
        }

        for (int r = 3; r <= sheetflowerNumber.getLastRowNum(); r++) {
            Row row = sheetflowerNumber.getRow(r);
            if (row == null){
                continue;
            }
            FlowerNumberSetting setting = new FlowerNumberSetting();
            Integer tims=(int)(row.getCell(0).getNumericCellValue());
            float rate=(float) (row.getCell(1).getNumericCellValue());
            setting.setDayPlantCount(tims);
            setting.setProbability(rate);
            numberSettings.add(setting);
        }

        flowerSettingMapper.deleteByExample(new FlowerSettingExample());
        flowerNumberSettingMapper.deleteByExample(new FlowerNumberSettingExample());
        flowerSeedsDrawSettingMapper.deleteByExample(new FlowerSeedsDrawSettingExample());
        flowerSettingExtMapper.batchInsertSeeds(settingList);
        flowerSettingExtMapper.batchInsertDraw(drawSettings);
        flowerSettingExtMapper.batchInsertNumber(numberSettings);
        notNull=true;
        return notNull;
    }


    private Sheet generate(MultipartFile file)throws IOException {
        String seedsDrawName=file.getOriginalFilename();
        if (!seedsDrawName.matches("^.+\\.(?i)(xls)$") && !seedsDrawName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (seedsDrawName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream isseedsDraw = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(isseedsDraw);
        } else {
            wb = new XSSFWorkbook(isseedsDraw);
        }
        Sheet sheet = wb.getSheetAt(0);
        return sheet;
    }



    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<FlowerSetting> settingList = new ArrayList<>();
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
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            FlowerSetting setting = new FlowerSetting();
            String id=((int)row.getCell(0).getNumericCellValue())+"";
            Integer output=(int)(row.getCell(7).getNumericCellValue());
            float rate=(float) (row.getCell(8).getNumericCellValue());
            JSONArray array=new JSONArray();
            Map<String,Object> map=new HashMap<>();
            map.put("rate",rate);
            map.put("goodsId",id);
            array.add(map);
            setting.setOtherAward(array.toJSONString());
            setting.setId(id);
            setting.setEnviromentAward(output);

            settingList.add(setting);
        }
        flowerSettingExtMapper.batchInsertSeeds(settingList);
        return notNull;
    }
}
