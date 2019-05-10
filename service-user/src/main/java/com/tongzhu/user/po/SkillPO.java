package com.tongzhu.user.po;

import java.io.Serializable;

public class SkillPO implements Serializable {
    private Integer skillId;
    private Integer level;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
