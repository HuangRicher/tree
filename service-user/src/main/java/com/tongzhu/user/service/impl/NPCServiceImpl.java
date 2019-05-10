package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.HTTPUrlConstant;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.domain.PropInfo;
import com.tongzhu.user.mapper.UserRoleTitleMapper;
import com.tongzhu.user.model.*;
import com.tongzhu.user.service.*;
import com.tongzhu.util.UUIDUtil;
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
import java.util.Date;
import java.util.List;

@Service
public class NPCServiceImpl implements NPCService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserSkillService userSkillService;
    @Autowired
    private ArsenalService arsenalService;

    @Autowired
    private UserRoleTitleMapper userRoleTitleMapper;

    @Autowired
    private NPCAttributeService npcAttributeService;

    @Autowired
    private UserGoodsService userGoodsService;



    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<GameNPC> npcList = new ArrayList<>();
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
        List<String> ids=new ArrayList<>();
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            GameNPC npc = new GameNPC();
            Integer id=(int)(row.getCell(0).getNumericCellValue());
            String name=row.getCell(1).getStringCellValue();
            Integer head=(int)(row.getCell(2).getNumericCellValue());
            Integer level=(int)(row.getCell(3).getNumericCellValue());
            String weaponIds=row.getCell(4).getStringCellValue();
            String skillIds=row.getCell(5).getStringCellValue();

            npc.setId(id);
            npc.setLevelAdd(level);
            npc.setName(name);
            npc.setHead(head);
            npcList.add(npc);

            ids.add(id+"");
            accountService.deleteByAccount(id+"");
            accountService.add(id+"","666666");
            accountService.deleteAccountUserByAccount(id+"");
            accountService.addAccountUser(id+"",head,id+"");

            User user=new User();
            user.setPortraitUrl(String.format(HTTPUrlConstant.HEAD_URL,head));
            user.setGrade(level);
            user.setUserId(id+"");
            user.setStatus(1);
            user.setSex(head%2==1?1:2);
            user.setName(name);
            user.setRoleId(head);
            user.setCreateDate(new Date());
            user.setId(UUIDUtil.generateUUID());
            userService.deleteUserByUserId(id+"");
            userService.addUser(user);

            UserRole userRole=new UserRole();
            userRole.setUserId(id+"");
            userRole.setRoleLevel(level);
            userRole.setRoleTitle(3);
            userRole.setRoleId(head);
            userRole.setUserName(name);
            userRole.setExperience(0L);
            userRole.setSex(head%2==1?1:2);
            userRole.setCharmNum(0);
            userRoleService.deleteByUserId(id+"");
            userRoleService.add(userRole);

            UserRoleTitle userRoleTitle=new UserRoleTitle();
            userRoleTitle.setRoleTitle(3);
            userRoleTitle.setUserId(id+"");
            userRoleTitleMapper.insert(userRoleTitle);

            NPCAttribute attribute=npcAttributeService.findByLevelId(level);
            PropInfo propInfo = new PropInfo();

            propInfo.setVitality((double) attribute.getHp());//生命属性加成
            propInfo.setAttack((double) attribute.getPhAtk());//物理攻击属性加成
            propInfo.setSpellAttacks((double) attribute.getMfAtk());//法术攻击属性加成
            propInfo.setPdef((double) attribute.getPhDef());//物理防御属性加成
            propInfo.setMagdef((double) attribute.getMfDef());//法术防御属性加成
            propInfo.setCrit((double) attribute.getCritical());//暴击属性加成
            propInfo.setDodge((double) attribute.getMiss());//闪避属性加成
            propInfo.setHitRate((double) attribute.getAccuracy());//命中属性加成
            propInfo.setDefenseCrit((double) attribute.getDcritical());//抗暴击属性加成

            userGoodsService.addUserAdornEquipAttribute(id+"",propInfo.getVitality(),propInfo.getAttack(),
                    propInfo.getSpellAttacks(),propInfo.getPdef(),propInfo.getMagdef(),
                    propInfo.getCrit(),propInfo.getDodge(),propInfo.getHitRate(),propInfo.getDefenseCrit());

            userSkillService.deleteByUserId(id+"");
            for(Object object: JSONArray.parseArray(skillIds)){
                JSONObject obj=(JSONObject)object;
                UserSkill skill=new UserSkill();
                skill.setUserId(id+"");
                skill.setId(UUIDUtil.generateUUID());
                skill.setLevel(1);
                skill.setSkillId(obj.getIntValue("key"));
                userSkillService.add(skill);
            }

            for(Object object: JSONArray.parseArray(weaponIds)){
                JSONObject obj=(JSONObject)object;
                arsenalService.addGoodsWeapon(id+"",obj.getString("key"));
            }
        }

        return notNull;
    }

}
