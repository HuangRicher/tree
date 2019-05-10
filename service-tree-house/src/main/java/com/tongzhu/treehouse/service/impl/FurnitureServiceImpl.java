package com.tongzhu.treehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.except.CheckException;
import com.tongzhu.treehouse.mapper.FurnitureMapper;
import com.tongzhu.treehouse.mapper.ext.FurnitureExtMapper;
import com.tongzhu.treehouse.model.Furniture;
import com.tongzhu.treehouse.model.FurnitureExample;
import com.tongzhu.treehouse.service.FurnitureService;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    @Autowired
    private FurnitureMapper furnitureMapper;

    @Autowired
    private FurnitureExtMapper furnitureExtMapper;




    @Override
    public Furniture getById(String goodsId) {
        return furnitureMapper.selectByPrimaryKey(goodsId);
    }

    @Transactional
    @Override
    public boolean batchImport(MultipartFile fileTree,MultipartFile fileExchange) throws IOException {
        List<Furniture> furnitureList = new ArrayList<>();
        Sheet sheet1=getNet(fileExchange);
        Sheet sheet2=getNet(fileTree);
        for (int r = 3; r <= sheet1.getLastRowNum(); r++) {
            Row row = sheet1.getRow(r);
            if (row == null){
                continue;
            }
            String id=((int)row.getCell(0).getNumericCellValue())+"";
            Integer type=(int)(row.getCell(1).getNumericCellValue());
            if(type==6){
                Furniture furniture = new Furniture();
                String reward=row.getCell(2).getStringCellValue();
                JSONObject rewardObj= JSON.parseObject(reward);
                String categoryId=rewardObj.getInteger("furniture")+"";
                Integer time=rewardObj.getInteger("time");
                for (int r2 = 3; r2 <= sheet2.getLastRowNum(); r2++) {
                    Row row2 = sheet2.getRow(r2);
                    if (row2 == null){
                        continue;
                    }
                    String catId=(int)(row2.getCell(0).getNumericCellValue())+"";
                    if(categoryId.equals(catId)){
                        Integer location=(int)(row2.getCell(2).getNumericCellValue());
                        String name=row2.getCell(3).getStringCellValue();
                        Integer outputRate=(int)(row2.getCell(6).getNumericCellValue());
                        Integer output=(int)(row2.getCell(7).getNumericCellValue());
                        furniture.setId(id);
                        furniture.setName(name);
                        if(time==0){
                            furniture.setType(1);
                        }else{
                            furniture.setType(0);
                        }
                        furniture.setCategoryId(categoryId);
                        furniture.setLocation(location);
                        furniture.setOutputRate(outputRate);
                        furniture.setOutputValue(output);
                        furniture.setUseableTime(time);

                    }
                }
                furnitureList.add(furniture);
            }
        }
        FurnitureExample example=new FurnitureExample();
        furnitureMapper.deleteByExample(example);
        furnitureExtMapper.batchInsert(furnitureList);
        return true;
    }

    private Sheet getNet(MultipartFile file)throws IOException{
        String fileName=file.getOriginalFilename();
        List<Furniture> furnitureList = new ArrayList<>();
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
        return wb.getSheetAt(0);
    }

    @Override
    public Furniture getByCategoryId(String goodsId) {
        FurnitureExample example=new FurnitureExample();
        example.createCriteria().andCategoryIdEqualTo(goodsId);
        List<Furniture> list=furnitureMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list)?null:list.get(0);
    }
}
