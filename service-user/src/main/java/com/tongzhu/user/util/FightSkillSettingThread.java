package com.tongzhu.user.util;

import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.SkillService;
import com.tongzhu.user.service.SkillSettingService;

import java.util.List;

public class FightSkillSettingThread implements Runnable{
    private String userId;
    private List<Combatant> combatantList;
    private RedisService redisService;
    private SkillSettingService skillSettingService;
    private SkillService skillService;

    public FightSkillSettingThread() {
    }

    public FightSkillSettingThread(String userId, List<Combatant> combatantList, RedisService redisService,
                                   SkillSettingService skillSettingService, SkillService skillService) {
        this.userId = userId;
        this.combatantList = combatantList;
        this.redisService = redisService;
        this.skillSettingService = skillSettingService;
        this.skillService = skillService;
    }

    @Override
    public void run() {
        for(Combatant combatant:combatantList){

        }
        redisService.set("_fight_pve_self_"+userId,combatantList);
    }
}
