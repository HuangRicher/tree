package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.constant.PacksackConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.PropInfoMapper;
import com.tongzhu.usergoods.mapper.ext.UserGoodsExtMapper;
import com.tongzhu.usergoods.mapper.ext.PropInfoExtMapper;
import com.tongzhu.usergoods.mapper.vo.PropInfoVO;
import com.tongzhu.usergoods.model.PropInfo;
import com.tongzhu.usergoods.model.PropInfoExample;
import com.tongzhu.usergoods.service.PropInfoService;
import com.tongzhu.util.DateUtil;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/10/9 0009.
 */
@Service
public class PropInfoServiceImpl implements PropInfoService {

    @Autowired
    private PropInfoMapper propInfoMapper;

    @Autowired
    private UserGoodsExtMapper userGoodsExtMapper;


    @Autowired
    private PropInfoExtMapper propInfoExtMapper;

    @Override
    public PropInfo getPropInfo(String goodsId) {
        return propInfoMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<PropInfo> getUserWarePropInfo(String userId) {
        List<PropInfo> propInfos = userGoodsExtMapper.selectUserWarePropInfo(userId, PacksackConstant.PROP_PLACE_ADORN);
        return propInfos;
    }

//

    @Override
    public Pager<PropInfoVO> queryPropList(String userId, int pageNum, int pageSize, Integer settingPosition) {
        Page<PropInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        propInfoExtMapper.queryPropList(userId, settingPosition);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }


    @Override
    public Pager<PropInfoVO> queryPropListByType(String userId, int pageNum, int pageSize, Integer settingPosition,Integer type) {
        Page<PropInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        propInfoExtMapper.queryPropListByType(userId, settingPosition,type);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public Pager<PropInfoVO> queryWarehousePropList(String userId, int pageNum, int pageSize) {
        Page<PropInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        propInfoExtMapper.queryWarehousePropList(userId);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public List<PropInfo> getPropInfoList() {
        PropInfoExample propInfoExample = new PropInfoExample();
        List<Integer> objects = new ArrayList<>();
        objects.add(0);
        propInfoExample.createCriteria().andTypeNotIn(objects);
        return propInfoMapper.selectByExample(propInfoExample);
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<PropInfo> propInfoList = new ArrayList<>();
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
        PropInfo propInfo;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            propInfo = new PropInfo();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String name = row.getCell(1).getStringCellValue();
            String description = row.getCell(2).getStringCellValue();
            String acquiringWay = row.getCell(3).getStringCellValue();
            Integer iconid = new Double(row.getCell(4).getNumericCellValue()).intValue();
//            Integer conversionPropAmount = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer conversionPropId = new Double(row.getCell(6).getNumericCellValue()).intValue();
            Integer conversionPropAmount = new Double(row.getCell(7).getNumericCellValue()).intValue();
            Integer profession = new Double(row.getCell(8).getNumericCellValue()).intValue();
            Integer quality = new Double(row.getCell(9).getNumericCellValue()).intValue();
            Integer type = new Double(row.getCell(10).getNumericCellValue()).intValue();
            Integer sex = new Double(row.getCell(11).getNumericCellValue()).intValue();
            Double attack = new Double(row.getCell(12).getNumericCellValue());
            Double spellAttacks = new Double(row.getCell(13).getNumericCellValue());
            Double pdef = new Double(row.getCell(14).getNumericCellValue());
            Double magdef = new Double(row.getCell(15).getNumericCellValue());
            Double vitality = new Double(row.getCell(16).getNumericCellValue());
            Double hitRate = new Double(row.getCell(17).getNumericCellValue());
            Double dodge = new Double(row.getCell(18).getNumericCellValue());
            Double crit = new Double(row.getCell(19).getNumericCellValue());
            Double defenseCrit = new Double(row.getCell(20).getNumericCellValue());
            Integer expirationTime = new Double(row.getCell(21).getNumericCellValue()).intValue();
            Integer sell = new Double(row.getCell(22).getNumericCellValue()).intValue();
            Integer use = new Double(row.getCell(23).getNumericCellValue()).intValue();

            propInfo.setId(id + "");
            propInfo.setName(name);
            propInfo.setDescription(description);
            propInfo.setAcquiringWay(acquiringWay);
            propInfo.setIconid(iconid + "");
            propInfo.setConversionPropId(conversionPropId+"");
            propInfo.setConversionAmount(conversionPropAmount);
            propInfo.setProfession(profession + "");
            propInfo.setQuality(quality);
            propInfo.setType(type);
            propInfo.setSex(sex);
            propInfo.setAttack(attack);
            propInfo.setSpellAttacks(spellAttacks);
            propInfo.setPdef(pdef);
            propInfo.setMagdef(magdef);
            propInfo.setVitality(vitality);
            propInfo.setHitRate(hitRate);
            propInfo.setDodge(dodge);
            propInfo.setCrit(crit);
            propInfo.setDefenseCrit(defenseCrit);
            if (expirationTime > 0) {
                propInfo.setExpirationTime(DateUtil.computingTime(expirationTime));
            }
            propInfo.setDestroy(sell);
            propInfo.setUse(use);
            propInfo.setCreateDate(new Date());
            propInfo.setUpdateDate(new Date());
            propInfo.setLogRecord(0);
            propInfo.setStorage(0);
            propInfo.setBinding(0);
            propInfo.setDeal(0);
            propInfo.setSell(sell);
            propInfo.setLevel(0);
            propInfo.setTime(0);
            propInfoList.add(propInfo);
        }
        for (PropInfo propInfoResord : propInfoList) {
            PropInfo p = propInfoMapper.selectByPrimaryKey(propInfoResord.getId());
            if (p == null) {
                propInfoMapper.insert(propInfoResord);
                System.out.println(" 插入 " + propInfoResord);
            } else {
                propInfoMapper.updateByPrimaryKey(propInfoResord);
                System.out.println(" 更新 " + propInfoResord.getName());

            }
        }
        return notNull;
    }
}
