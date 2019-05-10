package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.Skill;

import java.util.List;

public interface SkillExtMapper {
    void batchInsert(List<Skill> list);
}
