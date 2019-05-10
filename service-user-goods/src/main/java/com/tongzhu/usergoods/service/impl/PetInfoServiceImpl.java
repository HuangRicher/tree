package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.constant.PacksackConstant;
import com.tongzhu.usergoods.constant.PetConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.usergoods.mapper.PetInfoMapper;
import com.tongzhu.usergoods.model.PetInfo;
import com.tongzhu.usergoods.model.PetInfoExample;
import com.tongzhu.usergoods.model.UserPet;
import com.tongzhu.usergoods.service.PetInfoService;
import com.tongzhu.usergoods.service.UserPetService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/7 0007.
 */

@Service
public class PetInfoServiceImpl implements PetInfoService {


    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CompoundInfoServiceImpl.class);


    @Autowired
    private UserPetService userPetService;

    @Autowired
    private PetInfoMapper petInfoMapper;

    @Override
    public List<PetInfo> getPetInfoList(String userId, Integer overdue) {
        List<UserPet> userPetList = userPetService.getUserPetList(userId, overdue);
        List<String> list = new ArrayList<>();
        for (UserPet userPet : userPetList) {
            list.add(userPet.getPetId());
        }
        if (userPetList.size() > 0) {
            PetInfoExample petInfoExample = new PetInfoExample();
            petInfoExample.createCriteria().andPetIdIn(list);
            petInfoExample.setOrderByClause("quality DESC ,fighting_capacity DESC");
            List<PetInfo> petInfoList = petInfoMapper.selectByExample(petInfoExample);
            return petInfoList;
        }
        return new ArrayList<>();
    }


    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<PetInfo> petInfoList = new ArrayList<>();
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

        Map<Integer, Map<String, Double>> map = new HashMap<>();
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            PetInfo petInfo = new PetInfo();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String name = row.getCell(1).getStringCellValue();
            Integer icon = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer quality = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer overdue = new Double(row.getCell(5).getNumericCellValue()).intValue();
            Map<String, Double> growMap = new HashMap<>();
            Double attack = new Double(row.getCell(6).getNumericCellValue());
            Double attack_grow = new Double(row.getCell(7).getNumericCellValue());
            Double attack_addition = new Double(row.getCell(8).getNumericCellValue());
            growMap.put("attack_grow", attack_grow);

            Double spellAttacks = new Double(row.getCell(9).getNumericCellValue());
            Double spellAttacks_grow = new Double(row.getCell(10).getNumericCellValue());
            Double spellAttacks_addition = new Double(row.getCell(11).getNumericCellValue());
            growMap.put("spellAttacks_grow", spellAttacks_grow);

            Double pdef = new Double(row.getCell(12).getNumericCellValue());
            Double pdef_grow = new Double(row.getCell(13).getNumericCellValue());
            Double pdef_addition = new Double(row.getCell(14).getNumericCellValue());
            growMap.put("pdef_grow", pdef_grow);

            Double magdef = new Double(row.getCell(15).getNumericCellValue());
            Double magdef_grow = new Double(row.getCell(16).getNumericCellValue());
            Double magdef_addition = new Double(row.getCell(17).getNumericCellValue());
            growMap.put("magdef_grow", magdef_grow);

            Double vitality = new Double(row.getCell(18).getNumericCellValue());
            Double vitality_grow = new Double(row.getCell(19).getNumericCellValue());
            Double vitality_addition = new Double(row.getCell(20).getNumericCellValue());
            growMap.put("vitality_grow", vitality_grow);

            Double hitRate = new Double(row.getCell(21).getNumericCellValue());
            Double hitRate_grow = new Double(row.getCell(22).getNumericCellValue());
            Double hitRate_addition = new Double(row.getCell(23).getNumericCellValue());
            growMap.put("hitRate_grow", hitRate_grow);

            Double dodge = new Double(row.getCell(24).getNumericCellValue());
            Double dodge_grow = new Double(row.getCell(25).getNumericCellValue());
            Double dodge_addition = new Double(row.getCell(26).getNumericCellValue());
            growMap.put("dodge_grow", dodge_grow);

            Double crit = new Double(row.getCell(27).getNumericCellValue());
            Double crit_grow = new Double(row.getCell(28).getNumericCellValue());
            Double crit_addition = new Double(row.getCell(29).getNumericCellValue());
            growMap.put("crit_grow", crit_grow);

            Double defenseCrit = new Double(row.getCell(30).getNumericCellValue());
            Double defenseCrit_grow = new Double(row.getCell(31).getNumericCellValue());
            Double defenseCrit_addition = new Double(row.getCell(32).getNumericCellValue());
            growMap.put("defenseCrit_grow", defenseCrit_grow);

            petInfo.setId(id);
            petInfo.setPetId(UUIDUtil.generateUUID());
            petInfo.setName(name);
            petInfo.setIcon(icon + "");
            petInfo.setQuality(quality);
            petInfo.setOverdue(overdue);
            petInfo.setTime(0);
            petInfo.setAttack(attack);
            petInfo.setSpellAttacks(spellAttacks);
            petInfo.setPdef(pdef);
            petInfo.setMagdef(magdef);
            petInfo.setVitality(vitality);
            petInfo.setHitRate(hitRate);
            petInfo.setDodge(dodge);
            petInfo.setCrit(crit);
            petInfo.setDefenseCrit(defenseCrit);

            petInfo.setAttackAddition(attack_addition);
            petInfo.setSpellAttacksAddition(spellAttacks_addition);
            petInfo.setPdefAddition(pdef_addition);
            petInfo.setMagdefAddition(magdef_addition);
            petInfo.setVitalityAddition(vitality_addition);
            petInfo.setHitRateAddition(hitRate_addition);
            petInfo.setDodgeAddition(dodge_addition);
            petInfo.setCritAddition(crit_addition);
            petInfo.setDefenseCritAddition(defenseCrit_addition);

            petInfoList.add(petInfo);
            map.put(id, growMap);
        }
        for (PetInfo petInfo : petInfoList) {
            if (petInfo.getOverdue() == PetConstant.OVERDUE_PERPETUAL) {
                for (int i = 0; i <= PetConstant.LEVEL_MAX; i++) {
                    Map<String, Double> stringDoubleMap = map.get(petInfo.getId());
                    PetInfo petInfoNew = new PetInfo();
                    petInfoNew.setId(petInfo.getId());
                    petInfoNew.setPetId(UUIDUtil.generateUUID());
                    petInfoNew.setName(petInfo.getName());
                    petInfoNew.setIcon(petInfo.getIcon());
                    petInfoNew.setQuality(petInfo.getQuality());
                    petInfoNew.setOverdue(petInfo.getOverdue());
                    petInfoNew.setTime(petInfo.getTime());
                    petInfoNew.setAttack(petInfo.getAttack() + stringDoubleMap.get("attack_grow") * i);
                    petInfoNew.setSpellAttacks(petInfo.getSpellAttacks() +stringDoubleMap.get("spellAttacks_grow")* i);
                    petInfoNew.setPdef(petInfo.getPdef() + stringDoubleMap.get("pdef_grow")*i);
                    petInfoNew.setMagdef(petInfo.getMagdef() +stringDoubleMap.get("magdef_grow")* i);
                    petInfoNew.setVitality(petInfo.getVitality() + stringDoubleMap.get("vitality_grow")*i);
                    petInfoNew.setHitRate(petInfo.getHitRate() +stringDoubleMap.get("hitRate_grow")* i);
                    petInfoNew.setDodge(petInfo.getDodge() + stringDoubleMap.get("dodge_grow")*i);
                    petInfoNew.setCrit(petInfo.getCrit() +stringDoubleMap.get("crit_grow")* i);
                    petInfoNew.setDefenseCrit(petInfo.getDefenseCrit() +stringDoubleMap.get("defenseCrit_grow")* i);
                    petInfoNew.setAttackAddition(petInfo.getAttackAddition());
                    petInfoNew.setSpellAttacksAddition(petInfo.getSpellAttacksAddition());
                    petInfoNew.setPdefAddition(petInfo.getPdefAddition());
                    petInfoNew.setMagdefAddition(petInfo.getMagdefAddition());
                    petInfoNew.setVitalityAddition(petInfo.getVitalityAddition());
                    petInfoNew.setHitRateAddition(petInfo.getHitRateAddition());
                    petInfoNew.setDodgeAddition(petInfo.getDodgeAddition());
                    petInfoNew.setCritAddition(petInfo.getCritAddition());
                    petInfoNew.setDefenseCritAddition(petInfo.getDefenseCritAddition());

                    petInfoNew.setLevel(i);
                    petInfoNew.setFightingCapacity( Math.ceil(petInfoNew.getVitality() * 0.2 + petInfoNew.getAttack() + petInfoNew.getSpellAttacks() +
                            petInfoNew.getPdef() + petInfoNew.getMagdef() + petInfoNew.getCrit() * 1.5
                            + petInfoNew.getDodge() * 1.5 + petInfoNew.getHitRate() * 1.5 + petInfoNew.getDefenseCrit() * 1.5));
                    PetInfo pet = this.getPetInfoByIdAndLevel(petInfo.getId(), i);
                    if (pet == null) {
                        petInfoMapper.insertSelective(petInfoNew);
                        log.info("增加" + petInfoNew.getName() + petInfoNew.getLevel() + "级");
                    } else {
                        petInfoNew.setPetId(pet.getPetId());
                        petInfoMapper.updateByPrimaryKeySelective(petInfoNew);
                        log.info("更新" + petInfoNew.getName() + petInfoNew.getLevel() + "级");
                    }
                }
            } else {
                petInfo.setFightingCapacity( Math.ceil(petInfo.getVitality() * 0.2 + petInfo.getAttack() + petInfo.getSpellAttacks() +
                        petInfo.getPdef() + petInfo.getMagdef() + petInfo.getCrit() * 1.5
                        + petInfo.getDodge() * 1.5 + petInfo.getHitRate() * 1.5 + petInfo.getDefenseCrit() * 1.5));
                petInfo.setLevel(0);
                PetInfo pet = this.getPetInfoByIdAndLevel(petInfo.getId(), PetConstant.LEVEL_MIN);
                if (pet == null) {
                    petInfoMapper.insertSelective(petInfo);
                    log.info("增加限时宠物" + petInfo.getName() + petInfo.getLevel() + "级");
                } else {
                    petInfo.setPetId(pet.getPetId());
                    petInfoMapper.updateByPrimaryKeySelective(petInfo);
                    log.info("更新限时宠物" + petInfo.getName() + petInfo.getLevel() + "级");
                }
            }
        }
        return notNull;

    }

    @Override
    public PetInfo getPetInfoByIdAndLevel(Integer id, Integer level) {
        PetInfoExample petInfoExample = new PetInfoExample();
        petInfoExample.createCriteria().andIdEqualTo(id).andLevelEqualTo(level);
        List<PetInfo> petInfoList = petInfoMapper.selectByExample(petInfoExample);
        if (petInfoList.size() > 0) {
            return petInfoList.get(0);
        }
        return null;
    }

    @Override
    public PetInfo getPetInfoByPetId(String petId) {
        return petInfoMapper.selectByPrimaryKey(petId);
    }

}
