package com.tongzhu.user.job;

import com.tongzhu.user.service.FightRankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendFightRankingAwardTask {

    private static Logger log= LoggerFactory.getLogger(SendFightRankingAwardTask.class);


    @Autowired
    private FightRankingService fightRankingService;


    /**
     * 每天24：00服务器通过邮件发送排位前1000名玩家的奖励
     */
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void workEveryDay() {
        log.info("------每天24：00服务器通过邮件发送排位前1000名玩家的奖励------");
        fightRankingService.sendAwardMail(1);
    }


    /**
     * 每个赛季结束的最后一天24:00，服务器通过邮件发送排位赛季奖励
     */
    @Scheduled(cron = "0 0 0 1 1,4,7,10 ?")
    public void workEverySeason() {
        log.info("------每个赛季结束的最后一天24:00，服务器通过邮件发送排位赛季奖励------");
        fightRankingService.sendAwardMail(2);
    }
}
