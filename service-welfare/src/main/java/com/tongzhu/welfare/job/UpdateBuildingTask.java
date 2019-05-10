package com.tongzhu.welfare.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.MarryService;

@Component
public class UpdateBuildingTask {

    Logger log= LoggerFactory.getLogger(UpdateBuildingTask.class);

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private MarryService marryService;
    
    /**
     * 每分钟查询建筑升级情况，缓存Redis;
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void work() throws NumberFormatException, Exception {
       try {
           int i = buildingService.upgradeBuildingTask();
       }catch (Exception e){
            e.printStackTrace();
           log.info(e.getMessage());
           log.info("有异常咯");
       }

    }
    
    /**
     * 每分钟查询预约婚礼是否开始，通知对应的客户端;
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void work1() throws NumberFormatException, Exception {
       int i = marryService.bookWedding();
    }
    
    /**
     * 每分钟查询婚礼结束后缓存是否已经被清除，并踢出多余的人
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void work3() throws NumberFormatException, Exception {
       marryService.kickLeaveWeddingRoom();
    }
    
    /**
     * 每晚凌晨0点重置洞房次数以及嬉闹次数
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void work2() throws NumberFormatException, Exception {
       int i = marryService.resetLoveTreeInfo();
    }
}
