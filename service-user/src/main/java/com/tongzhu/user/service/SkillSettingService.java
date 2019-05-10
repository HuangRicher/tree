package com.tongzhu.user.service;

import com.tongzhu.user.model.SkillSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SkillSettingService {

    void add(SkillSetting skillSetting);

    /**
     * 批量导入技能升级配置
     * @param fileName
     * @param file
     * @return
     * @throws IOException
     */
    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    SkillSetting getBySkillIdAndSkillLevel(Integer skillId, Integer level);

    List<SkillSetting> findByRoleLevel(Integer roleLevel);

    /**
     * 根据技能ID和角色等级查询
     * @param skillId
     * @param roleLevel
     * @return
     */
    SkillSetting getBySkillIdAndRoleLevel(Integer skillId, Integer roleLevel);

    /**
     * 查询用户拥有的技能配置列表
     * @param userId
     * @return
     */
    List<SkillSetting> selectUserSkillSettings(String userId);

    /**
     * 根据技能ID和等级查找列表
     * @param skillSettingList
     * @return
     */
    List<SkillSetting> selectSkillSettingBySkillIdAndLevel(List<SkillSetting> skillSettingList);


}
