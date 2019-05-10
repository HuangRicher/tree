package com.tongzhu.friend.job;

import com.tongzhu.friend.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetFriendFightStatusTask {


    private static Logger log= LoggerFactory.getLogger(ResetFriendFightStatusTask.class);

    @Autowired
    private FriendService friendService;



    /**
     * 每天24：00执行一次
     */
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void resetFriendFightStatus(){
        log.info("------每天24：00执行一次------");
        friendService.resetFightStatus();
    }
}
