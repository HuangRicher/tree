package com.tongzhu.usergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.EquipmentInfoMapper;
import com.tongzhu.usergoods.mapper.ext.EquipmentInfoExtMapper;
import com.tongzhu.usergoods.mapper.vo.EquipmentInfoVO;
import com.tongzhu.usergoods.model.EquipmentInfo;
import com.tongzhu.usergoods.model.EquipmentInfoExample;
import com.tongzhu.usergoods.service.EquipmentInfoService;
import com.tongzhu.util.DateUtil;
import com.tongzhu.util.UUIDUtil;
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
 * Created by Administrator on 2018/10/24 0024.
 */
@Service
public class EquipmentInfoServiceImpl implements EquipmentInfoService {

    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;

    @Autowired
    private EquipmentInfoExtMapper equipmentInfoExtMapper;

    @Override
    public EquipmentInfo getEquipmentInfo(String goodsId) {
        return equipmentInfoMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public Pager<EquipmentInfoVO> queryEquipmentInfoList(String userId, int pageNum, int pageSize, Integer settingPosition) {
        Page<EquipmentInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        equipmentInfoExtMapper.queryEquipmentInfoList(userId, settingPosition);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<EquipmentInfo> equipmentInfoList = new ArrayList<>();
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
        EquipmentInfo equipmentInfo;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            equipmentInfo = new EquipmentInfo();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String name = row.getCell(1).getStringCellValue();
            String description = row.getCell(2).getStringCellValue();
            Integer guardiansMale = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer guardiansFemale = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer assassinMale = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Integer assassinFemale = new Double(row.getCell(6).getNumericCellValue()).intValue();
            Integer craftsMale = new Double(row.getCell(7).getNumericCellValue()).intValue();
            Integer craftsFemale = new Double(row.getCell(8).getNumericCellValue()).intValue();
            Integer masterMale = new Double(row.getCell(9).getNumericCellValue()).intValue();
            Integer masterFemale = new Double(row.getCell(10).getNumericCellValue()).intValue();
            Integer iconid = new Double(row.getCell(11).getNumericCellValue()).intValue();
            Integer wearPosition = new Double(row.getCell(12).getNumericCellValue()).intValue();
            Integer level = new Double(row.getCell(13).getNumericCellValue()).intValue();
            Integer upgrade = new Double(row.getCell(14).getNumericCellValue()).intValue();
            Integer profession = new Double(row.getCell(15).getNumericCellValue()).intValue();
            Integer quality = new Double(row.getCell(16).getNumericCellValue()).intValue();
            Integer conversionPropId = new Double(row.getCell(17).getNumericCellValue()).intValue();
            Integer conversionPropAmount = new Double(row.getCell(18).getNumericCellValue()).intValue();
            Integer suitId = new Double(row.getCell(19).getNumericCellValue()).intValue();
            Double attack = new Double(row.getCell(20).getNumericCellValue());
            Double spellAttacks = new Double(row.getCell(21).getNumericCellValue());
            Double pdef = new Double(row.getCell(22).getNumericCellValue());
            Double magdef = new Double(row.getCell(23).getNumericCellValue());
            Double vitality = new Double(row.getCell(24).getNumericCellValue());
            Double hitRate = new Double(row.getCell(25).getNumericCellValue());
            Double dodge = new Double(row.getCell(26).getNumericCellValue());
            Double crit = new Double(row.getCell(27).getNumericCellValue());
            Double defenseCrit = new Double(row.getCell(28).getNumericCellValue());
            Integer expirationTime = new Double(row.getCell(29).getNumericCellValue()).intValue();


            equipmentInfo.setId(id);

            equipmentInfo.setName(name);
            equipmentInfo.setDescription(description);
            equipmentInfo.setGuardiansMale(guardiansMale + "");
            equipmentInfo.setGuardiansFemale(guardiansFemale + "");
            equipmentInfo.setAssassinMale(assassinMale + "");
            equipmentInfo.setAssassinFemale(assassinFemale + "");
            equipmentInfo.setCraftsMale(craftsMale + "");
            equipmentInfo.setCraftsFemale(craftsFemale + "");
            equipmentInfo.setMasterMale(masterMale + "");
            equipmentInfo.setMasterFemale(masterFemale + "");
            equipmentInfo.setIconid(iconid + "");
            equipmentInfo.setWearPosition(wearPosition);
            equipmentInfo.setLevel(level);
            equipmentInfo.setUpgrade(upgrade);
            equipmentInfo.setProfession(profession + "");
            equipmentInfo.setQuality(quality);
            equipmentInfo.setConversionPropId(conversionPropId + "");
            equipmentInfo.setConversionAmount(conversionPropAmount);
            equipmentInfo.setTime(0);
            equipmentInfo.setSuitId(suitId);
            equipmentInfo.setAttack(attack);
            equipmentInfo.setSpellAttacks(spellAttacks);
            equipmentInfo.setPdef(pdef);
            equipmentInfo.setMagdef(magdef);
            equipmentInfo.setVitality(vitality);
            equipmentInfo.setHitRate(hitRate);
            equipmentInfo.setDodge(dodge);
            equipmentInfo.setCrit(crit);
            equipmentInfo.setDefenseCrit(defenseCrit);

            if (expirationTime > 0) {
                equipmentInfo.setExpirationTime(DateUtil.computingTime(expirationTime));
            }
            equipmentInfo.setEnchantlvl(0);
            equipmentInfo.setIntensifyId(UUIDUtil.generateUUID());
            equipmentInfo.setFightingCapacity((double) Math.round(equipmentInfo.getVitality() * 0.2 + equipmentInfo.getAttack() + equipmentInfo.getSpellAttacks() +
                    equipmentInfo.getPdef() + equipmentInfo.getMagdef() + equipmentInfo.getCrit() * 1.5
                    + equipmentInfo.getDodge() * 1.5 + equipmentInfo.getHitRate() * 1.5 + equipmentInfo.getDefenseCrit() * 1.5));
            equipmentInfo.setSex(0);
            equipmentInfo.setCreateDate(new Date());
            equipmentInfo.setUpdateDate(new Date());
            equipmentInfo.setLogRecord(0);
            equipmentInfo.setStorage(0);
            equipmentInfo.setBinding(0);
            equipmentInfo.setDeal(0);
            equipmentInfo.setSell(0);
            equipmentInfo.setDestroy(0);
            equipmentInfo.setInlay(0);
            equipmentInfo.setOriginal(0);
            equipmentInfoList.add(equipmentInfo);
        }
        for (EquipmentInfo equipmentInfoResord : equipmentInfoList) {
            EquipmentInfo equipmentInfoByIdAndOriginal = getEquipmentInfoByIdAndOriginal(equipmentInfoResord.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (equipmentInfoByIdAndOriginal == null) {
                equipmentInfoMapper.insertSelective(equipmentInfoResord);
                System.out.println(" 插入 " + equipmentInfoResord);
            } else {
                equipmentInfoResord.setIntensifyId(equipmentInfoByIdAndOriginal.getIntensifyId());
                equipmentInfoMapper.updateByPrimaryKeySelective(equipmentInfoResord);
                System.out.println(" 更新 " + equipmentInfoResord.getName());
            }


        }
        return notNull;
    }

    @Override
    public List<EquipmentInfo> findDressedEquipmentList(String goodsId) {
        return null;
    }

    @Override
    public EquipmentInfo getEquipmentInfoByIdAndOriginal(String id, Integer original) {
        EquipmentInfoExample equipmentInfoExample = new EquipmentInfoExample();
        equipmentInfoExample.createCriteria().andIdEqualTo(Integer.parseInt(id)).andOriginalEqualTo(original);
        List<EquipmentInfo> equipmentInfos = equipmentInfoMapper.selectByExample(equipmentInfoExample);
        if (equipmentInfos.size() <= 0) {
            return null;
        }
        return equipmentInfos.get(0);
    }

    @Override
    public int update(EquipmentInfo equipmentInfo) {
        return equipmentInfoMapper.updateByPrimaryKeySelective(equipmentInfo);
    }

    @Override
    public int insert(EquipmentInfo equipmentInfo) {
        return equipmentInfoMapper.insertSelective(equipmentInfo);
    }

    @Override
    public EquipmentInfo getEquipmentInfoByWearPositionAndQuality(Integer wearPosition, int quality) {
        EquipmentInfoExample equipmentInfoExample = new EquipmentInfoExample();
        equipmentInfoExample.createCriteria().andWearPositionEqualTo(wearPosition).andQualityEqualTo(quality);
        List<EquipmentInfo> equipmentInfos = equipmentInfoMapper.selectByExample(equipmentInfoExample);
        if (equipmentInfos.size() > 0) {
            return equipmentInfos.get(0);
        }
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String intensifyId) {
        return equipmentInfoMapper.deleteByPrimaryKey(intensifyId);
    }

    @Override
    public Pager<EquipmentInfoVO> queryWarehouseEquipmentInfoList(String userId,Integer pageNum, Integer pageSize) {
        Page<EquipmentInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        equipmentInfoExtMapper.queryWarehouseEquipmentInfoList(userId);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }


}
