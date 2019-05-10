package com.tongzhu.usergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.ArsenalInfoMapper;
import com.tongzhu.usergoods.mapper.ext.ArsenalInfoExtMapper;
import com.tongzhu.usergoods.mapper.vo.ArsenalInfoVO;
import com.tongzhu.usergoods.model.ArsenalInfo;
import com.tongzhu.usergoods.model.ArsenalInfoExample;
import com.tongzhu.usergoods.service.ArsenalInfoService;
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
public class ArsenalInfoServiceImpl implements ArsenalInfoService {

    @Autowired
    private ArsenalInfoMapper arsenalInfoMapper;

    @Autowired
    private ArsenalInfoExtMapper arsenalInfoExtMapper;

    @Override
    public ArsenalInfo getArsenalInfo(String goodsId) {
        return arsenalInfoMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public Pager<ArsenalInfoVO> queryWeaponList(String userId, int pageNum, int pageSize, Integer settingPosition) {
        Page<ArsenalInfoVO> page = PageHelper.startPage(pageNum, pageSize);
        arsenalInfoExtMapper.queryWeaponList(userId, settingPosition);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);

    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<ArsenalInfo> arsenalInfoList = new ArrayList<>();
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
        ArsenalInfo arsenalInfo;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            arsenalInfo = new ArsenalInfo();
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
            Integer level = new Double(row.getCell(12).getNumericCellValue()).intValue();
            Integer type = new Double(row.getCell(13).getNumericCellValue()).intValue();
            Integer upgrade = new Double(row.getCell(14).getNumericCellValue()).intValue();
            Integer profession = new Double(row.getCell(15).getNumericCellValue()).intValue();
            Integer quality = new Double(row.getCell(16).getNumericCellValue()).intValue();
            Integer conversionPropId = new Double(row.getCell(17).getNumericCellValue()).intValue();
            Integer conversionPropAmount = new Double(row.getCell(18).getNumericCellValue()).intValue();
            Double attack = new Double(row.getCell(19).getNumericCellValue());
            Double spellAttacks = new Double(row.getCell(20).getNumericCellValue());
            Double pdef = new Double(row.getCell(21).getNumericCellValue());
            Double magdef = new Double(row.getCell(22).getNumericCellValue());
            Double vitality = new Double(row.getCell(23).getNumericCellValue());
            Double hitRate = new Double(row.getCell(24).getNumericCellValue());
            Double dodge = new Double(row.getCell(25).getNumericCellValue());
            Double crit = new Double(row.getCell(26).getNumericCellValue());
            Double defenseCrit = new Double(row.getCell(27).getNumericCellValue());
            Integer expirationTime = new Double(row.getCell(28).getNumericCellValue()).intValue();


            arsenalInfo.setId(id);
            arsenalInfo.setName(name);
            arsenalInfo.setDescription(description);
            arsenalInfo.setGuardiansMale(guardiansMale + "");
            arsenalInfo.setGuardiansFemale(guardiansFemale + "");
            arsenalInfo.setAssassinMale(assassinMale + "");
            arsenalInfo.setAssassinFemale(assassinFemale + "");
            arsenalInfo.setCraftsMale(craftsMale + "");
            arsenalInfo.setCraftsFemale(craftsFemale + "");
            arsenalInfo.setMasterMale(masterMale + "");
            arsenalInfo.setMasterFemale(masterFemale + "");
            arsenalInfo.setIconid(iconid + "");
            arsenalInfo.setLevel(level);
            arsenalInfo.setType(type);
            arsenalInfo.setUpgrade(upgrade);
            arsenalInfo.setProfession(profession + "");
            arsenalInfo.setQuality(quality);
            arsenalInfo.setConversionPropId(conversionPropId);
            arsenalInfo.setConversionAmount(conversionPropAmount);
            arsenalInfo.setTime(0);
            arsenalInfo.setAttack(attack);
            arsenalInfo.setSpellAttacks(spellAttacks);
            arsenalInfo.setPdef(pdef);
            arsenalInfo.setMagdef(magdef);
            arsenalInfo.setVitality(vitality);
            arsenalInfo.setHitRate(hitRate);
            arsenalInfo.setDodge(dodge);
            arsenalInfo.setCrit(crit);
            arsenalInfo.setDefenseCrit(defenseCrit);
            arsenalInfo.setEnchantlvl(0);
            if (expirationTime > 0) {
                arsenalInfo.setExpirationTime(DateUtil.computingTime(expirationTime));
            }

            arsenalInfo.setIntensifyId(UUIDUtil.generateUUID());
            arsenalInfo.setFightingCapacity((double) Math.round(arsenalInfo.getVitality() * 0.2 + arsenalInfo.getAttack() + arsenalInfo.getSpellAttacks() +
                    arsenalInfo.getPdef() + arsenalInfo.getMagdef() + arsenalInfo.getCrit() * 1.5
                    + arsenalInfo.getDodge() * 1.5 + arsenalInfo.getHitRate() * 1.5 + arsenalInfo.getDefenseCrit() * 1.5));
            arsenalInfo.setSex(0);
            arsenalInfo.setCreateDate(new Date());
            arsenalInfo.setUpdateDate(new Date());
            arsenalInfo.setLogRecord(0);
            arsenalInfo.setStorage(0);
            arsenalInfo.setBinding(0);
            arsenalInfo.setDeal(0);
            arsenalInfo.setSell(0);
            arsenalInfo.setDestroy(0);
            arsenalInfo.setInlay(0);
            arsenalInfo.setOriginal(0);
            arsenalInfoList.add(arsenalInfo);
        }
        for (ArsenalInfo arsenalInfoResord : arsenalInfoList) {
            ArsenalInfo arsenalInfoByIdAndOriginal = getArsenalInfoByIdAndOriginal(arsenalInfoResord.getId() + "", PacksackConstant.GOODS_ORIGINAL_YES);
            if (arsenalInfoByIdAndOriginal == null) {
                arsenalInfoMapper.insertSelective(arsenalInfoResord);
                System.out.println(" 插入 " + arsenalInfoResord);
            } else {
                arsenalInfoResord.setId(arsenalInfoByIdAndOriginal.getId());
                arsenalInfoResord.setIntensifyId(arsenalInfoByIdAndOriginal.getIntensifyId());
                arsenalInfoMapper.updateByPrimaryKeySelective(arsenalInfoResord);
                System.out.println("更新" + arsenalInfoResord.getName());
            }
        }
        return notNull;
    }

    @Override
    public ArsenalInfo getArsenalInfoByIdAndOriginal(String id, Integer original) {
        ArsenalInfoExample arsenalInfoExample = new ArsenalInfoExample();
        arsenalInfoExample.createCriteria().andIdEqualTo(Integer.parseInt(id)).andOriginalEqualTo(original);
        List<ArsenalInfo> arsenalInfos = arsenalInfoMapper.selectByExample(arsenalInfoExample);
        if (arsenalInfos.size() <= 0) {
            return null;
        }
        return arsenalInfos.get(0);
    }

    @Override
    public int update(ArsenalInfo arsenalInfo) {
        return arsenalInfoMapper.updateByPrimaryKeySelective(arsenalInfo);
    }

    @Override
    public int insert(ArsenalInfo arsenalInfo) {
        return arsenalInfoMapper.insertSelective(arsenalInfo);
    }

    @Override
    public List<ArsenalInfo> findUserAllNotGetWeaponList(String userId, String profession) {
        List<ArsenalInfo> arsenalInfoList = arsenalInfoExtMapper.findUserAllNotGetWeaponList(userId, profession);
        return arsenalInfoList;
    }
}
