package com.tongzhu.user.service;

import com.tongzhu.user.model.Skill;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SkillService {
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    /**
     * 更具类型分组查找所有技能列表
     * @return
     */
    List<Skill> findAllGroupByType();

    /**
     * 查找触发xxBuff的技能
     * @param buffId
     * @return
     */
    List<Skill> findAllByBuffId(String buffId);

    Skill findBySkillId(Integer skillId);

}
