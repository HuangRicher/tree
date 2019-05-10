package com.tongzhu.user.service.impl;

import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.SkillMapper;
import com.tongzhu.user.mapper.ext.SkillExtMapper;
import com.tongzhu.user.model.Skill;
import com.tongzhu.user.model.SkillExample;
import com.tongzhu.user.service.SkillService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillMapper skillMapper;
    @Autowired
    private SkillExtMapper skillExtMapper;


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<Skill> userList = new ArrayList<>();
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
        Skill skill;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            skill = new Skill();

            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String skillName = row.getCell(1).getStringCellValue();
            String description = row.getCell(2).getStringCellValue();
            Integer coolingCircle= new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer roleLevel= new Double(row.getCell(4).getNumericCellValue()).intValue();
            Float apperceptionProbability= new Double(row.getCell(5).getNumericCellValue()).floatValue();
            Integer type= new Double(row.getCell(6).getNumericCellValue()).intValue();
            Integer priority= new Double(row.getCell(7).getNumericCellValue()).intValue();

            skill.setId(id);
            skill.setApperceptionProbability(apperceptionProbability);
            skill.setCoolingCircle(coolingCircle);
            skill.setDescription(description);
            skill.setPriority(priority);
            skill.setRoleLevel(roleLevel);
            skill.setSkillName(skillName);
            skill.setType(type);
            userList.add(skill);
        }
        /*for (Skill userResord : userList) {
            skillMapper.insert(userResord);
            System.out.println(" 插入 "+userResord);
        }*/
        SkillExample example=new SkillExample();
        skillMapper.deleteByExample(example);
        skillExtMapper.batchInsert(userList);
        return notNull;
    }

    @Override
    public List<Skill> findAllGroupByType() {
        SkillExample example=new SkillExample();
        return skillMapper.selectByExample(example);
    }

    @Override
    public List<Skill> findAllByBuffId(String buffId) {
        SkillExample example=new SkillExample();
        example.createCriteria().andBuffIdLike("%"+buffId+"%");
        return skillMapper.selectByExample(example);
    }

    @Override
    public Skill findBySkillId(Integer skillId) {
        return skillMapper.selectByPrimaryKey(skillId);
    }

}
