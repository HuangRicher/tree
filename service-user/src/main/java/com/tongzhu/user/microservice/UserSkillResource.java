package com.tongzhu.user.microservice;

import com.tongzhu.user.service.ChatMessageService;
import com.tongzhu.user.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PipedReader;

@RestController
@RequestMapping("/userSkillResource")
public class UserSkillResource {

    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private UserSkillService userSkillService;

    /**
     * 技能可升级红点提示
     * @param userId
     */
    @PostMapping("/sendSkillRedTip/{userId}")
    public void sendSkillRedTip(@PathVariable("userId") String userId){
        boolean check=userSkillService.checkSkillCanUpgrade(userId);
        if(check) chatMessageService.sendSkillRedTipToSomeBody(userId);
    }


    /**
     * 技能是否可升级
     * @param userId
     * @return true 可升，false 不可升
     */
    @PostMapping("/checkSkillCanUpgrade/{userId}")
    public Boolean checkSkillCanUpgrade(@PathVariable("userId") String userId){
        return userSkillService.checkSkillCanUpgrade(userId);
    }

}
