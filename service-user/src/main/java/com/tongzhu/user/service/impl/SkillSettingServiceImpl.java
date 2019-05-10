package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.SkillSettingMapper;
import com.tongzhu.user.mapper.ext.SkillSettingExtMapper;
import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.user.model.SkillSettingExample;
import com.tongzhu.user.model.UserRole;
import com.tongzhu.user.model.UserSkill;
import com.tongzhu.user.service.SkillSettingService;
import com.tongzhu.user.util.BigDecimalUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
//@CacheConfig(cacheNames="skill_setting")
public class SkillSettingServiceImpl implements SkillSettingService {

    @Autowired
    private SkillSettingMapper skillSettingMapper;

    @Autowired
    private SkillSettingExtMapper skillSettingExtMapper;



    @Override
    public void add(SkillSetting skillSetting) {
        skillSettingMapper.insert(skillSetting);
    }

    //@Cacheable(key = "#p0+'_id_lv_'+#p1")
    @Override
    public SkillSetting getBySkillIdAndRoleLevel(Integer skillId, Integer roleLevel) {
        SkillSettingExample example=new SkillSettingExample();
        example.createCriteria().andSkillIdEqualTo(skillId).andRoleLevelEqualTo(roleLevel);
        List<SkillSetting> settings=skillSettingMapper.selectByExample(example);
        if(settings!=null && !settings.isEmpty()){
            return settings.get(0);
        }
        return null;
    }

    @Override
    public List<SkillSetting> selectUserSkillSettings(String userId) {
        return skillSettingExtMapper.selectUserSkillSettings(userId);
    }

    @Override
    public List<SkillSetting> selectSkillSettingBySkillIdAndLevel(List<SkillSetting> skillSettingList) {
        return skillSettingExtMapper.selectSkillSettingBySkillIdAndLevel(skillSettingList);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {

        boolean notNull = false;
        List<SkillSetting> userList = new ArrayList<>();
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
        SkillSetting skill;
        for (int skillLevel=1;skillLevel<100;skillLevel++){
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null){
                    continue;
                }

                skill = new SkillSetting();

                Integer skillId=new Double(row.getCell(0).getNumericCellValue()).intValue();
                Integer roleLevel= skillLevel;

                //消耗金币=ROUNDUP(当前技能等级*500*当前技能等级^0.5,-1)
                BigDecimal cc=new BigDecimal(skillLevel*500*Math.pow(skillId,0.5));
                Integer consumeMoney= BigDecimalUtils.setScaleRoundUp(-1,cc).intValue();

                //消耗经验=ROUNDDOWN(当前技能等级*300*0.7^0.5,-1)
                BigDecimal mm=new BigDecimal(skillLevel*300*Math.pow(0.7,0.5));
                Integer consumeExperience= BigDecimalUtils.setScaleRoundDown(-1,mm).intValue();
                Map<String,Float> map=new LinkedHashMap();
                for(int i=0;i<3;i++){
                    if(row.getCell(10+i*3)!=null){
                        if(new Double(row.getCell(10+i*3).getNumericCellValue()).intValue()>0){
                            String key=new Double(row.getCell(10+i*3).getNumericCellValue()).intValue()+"";
                            float addRate=new Double(row.getCell(12+(i*3)).getNumericCellValue()).floatValue();
                            float value=new Double(row.getCell(11+(i*3)).getNumericCellValue()).floatValue()+((skillLevel-1)*addRate);
                            map.put(key,value);
                        }
                    }
                }
                String pro= JSON.toJSONString(map);

                skill.setSkillId(skillId);
                skill.setRoleLevel(roleLevel);
                skill.setConsumeMoney(consumeMoney);
                skill.setConsumeExperience(consumeExperience);
                skill.setSkillLevel(skillLevel);
                skill.setPro(pro);


                userList.add(skill);
            }
        }

        /*for (SkillSetting setting : userList) {
            skillSettingMapper.insert(setting);
            System.out.println(" 插入 "+setting);
        }*/
        SkillSettingExample example=new SkillSettingExample();
        skillSettingMapper.deleteByExample(example);
        skillSettingExtMapper.batchInsert(userList);
        return notNull;
    }

    //@Cacheable(key = "#p0+'_sk_'+#p1")
    @Override
    public SkillSetting getBySkillIdAndSkillLevel(Integer skillId, Integer level) {
        SkillSettingExample example=new SkillSettingExample();
        example.createCriteria().andSkillIdEqualTo(skillId).andSkillLevelEqualTo(level);
        List<SkillSetting> settings=skillSettingMapper.selectByExample(example);
        if(settings!=null && settings.size()>0){
            return settings.get(0);
        }
        return null;
    }

    //@Cacheable(key = "#p0+'_'")
    @Override
    public List<SkillSetting> findByRoleLevel(Integer roleLevel) {
        SkillSettingExample example=new SkillSettingExample();
        example.createCriteria().andRoleLevelEqualTo(roleLevel);
        return skillSettingMapper.selectByExample(example);
    }

}
