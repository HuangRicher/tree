package com.tongzhu.usergoods.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.PropGiftInfoMapper;
import com.tongzhu.usergoods.model.PropGiftInfo;
import com.tongzhu.usergoods.model.PropGiftInfoExample;
import com.tongzhu.usergoods.service.PropGiftInfoService;
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
 * Created by Administrator on 2018/10/12 0012.
 */
@Service
public class PropGiftInfoServiceImpl implements PropGiftInfoService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PropGiftInfoServiceImpl.class);

    @Autowired
    private PropGiftInfoMapper propGiftInfoMapper;

    @Override
    public PropGiftInfo getPropGiftInfo(String id) {
        PropGiftInfoExample propGiftInfoExample = new PropGiftInfoExample();
        propGiftInfoExample.createCriteria().andPropIdEqualTo(id);
        List<PropGiftInfo> propGiftInfoList = propGiftInfoMapper.selectByExample(propGiftInfoExample);
        if (propGiftInfoList.size() > 0) {
            return propGiftInfoList.get(0);
        }
        return null;
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<PropGiftInfo> propGiftInfoList = new ArrayList<>();
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
            PropGiftInfo propGiftInfo = new PropGiftInfo();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer type = new Double(row.getCell(1).getNumericCellValue()).intValue();
            String reward = row.getCell(2).getStringCellValue();
            propGiftInfo.setId(UUIDUtil.generateUUID());
            propGiftInfo.setPropId(id + "");
            propGiftInfo.setType(type);
            propGiftInfo.setGiftBagItems(reward);
            propGiftInfoList.add(propGiftInfo);

        }
        for (PropGiftInfo propGiftInfo : propGiftInfoList) {
            PropGiftInfo propGiftInfoByPropId = this.getPropGiftInfo(propGiftInfo.getPropId());
            if (propGiftInfoByPropId == null) {
                propGiftInfoMapper.insertSelective(propGiftInfo);
                log.info("增加成功"+propGiftInfo.getPropId());
            } else {
                propGiftInfo.setId(propGiftInfoByPropId.getId());
                propGiftInfoMapper.updateByPrimaryKeySelective(propGiftInfo);
                log.info("更新成功"+propGiftInfo.getPropId());
            }
        }
        return notNull;
    }
}
