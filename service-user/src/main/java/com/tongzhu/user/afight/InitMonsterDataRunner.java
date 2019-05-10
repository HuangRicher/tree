package com.tongzhu.user.afight;

import com.tongzhu.user.service.MonsterService;
import com.tongzhu.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitMonsterDataRunner implements CommandLineRunner {


    @Autowired
    private MonsterService monsterService;

    @Autowired
    private UserRoleService userRoleService;



    @Override
    public void run(String... args) throws Exception {
        monsterService.initCacheMonster();

        userRoleService.initFirstUser();

    }

}
