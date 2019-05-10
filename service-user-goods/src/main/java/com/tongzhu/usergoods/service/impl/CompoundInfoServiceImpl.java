package com.tongzhu.usergoods.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.CompoundInfoMapper;
import com.tongzhu.usergoods.model.CompoundInfo;
import com.tongzhu.usergoods.model.CompoundInfoExample;
import com.tongzhu.usergoods.model.PropGiftInfo;
import com.tongzhu.usergoods.service.CompoundInfoService;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/16 0016.
 */
@Service
public class CompoundInfoServiceImpl implements CompoundInfoService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CompoundInfoServiceImpl.class);


    @Autowired
    private CompoundInfoMapper compoundInfoMapper;

    @Override
    public CompoundInfo getCompoundInfo(String basicsId, String compositeId) {
        CompoundInfoExample compoundInfoExample = new CompoundInfoExample();
        compoundInfoExample.createCriteria().andBasicItemsEqualTo(basicsId).andCompositeEqualTo(compositeId);
        List<CompoundInfo> compoundInfos = compoundInfoMapper.selectByExample(compoundInfoExample);
        if (compoundInfos.size() <= 0) {
            return null;
        }
        return compoundInfos.get(0);
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<CompoundInfo> compoundInfoList = new ArrayList<>();
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
        if (sheet != null) {
            notNull = true;
        }

        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            CompoundInfo compoundInfo = new CompoundInfo();
            Integer basicItems = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer basicItemsAmount = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer composite = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer compositeType = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer compositeAmount = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer cost = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer costAmount = new Double(row.getCell(6).getNumericCellValue()).intValue();
            compoundInfo.setBasicItems(basicItems+ "");
            compoundInfo.setBasicItemsAmount(basicItemsAmount);
            compoundInfo.setComposite(composite+"");
            compoundInfo.setCompositeType(compositeType);
            compoundInfo.setCompositeAmount(compositeAmount);
            compoundInfo.setCost(cost+"");
            compoundInfo.setCostAmount(costAmount);
            compoundInfoList.add(compoundInfo);
        }
        for (CompoundInfo compoundInfo : compoundInfoList) {
            CompoundInfo compound = this.getCompoundInfo(compoundInfo.getBasicItems(), compoundInfo.getComposite());
            if (compound == null) {
                compoundInfoMapper.insertSelective(compoundInfo);
                log.info("增加成功"+ compoundInfo.getBasicItems() + "------->"+compoundInfo.getComposite());
            } else {
                compoundInfoMapper.updateByPrimaryKeySelective(compoundInfo);
                log.info("更新成功"+ compoundInfo.getBasicItems() + "------->"+compoundInfo.getComposite() +"------>"+compoundInfo.getBasicItemsAmount());
            }
        }
        return notNull;
    }
}
