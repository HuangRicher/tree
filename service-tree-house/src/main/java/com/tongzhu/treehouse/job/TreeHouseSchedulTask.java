package com.tongzhu.treehouse.job;

import com.tongzhu.treehouse.model.Furniture;
import com.tongzhu.treehouse.model.TreeHouseFurniture;
import com.tongzhu.treehouse.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TreeHouseSchedulTask {

    Logger log= LoggerFactory.getLogger(TreeHouseSchedulTask.class);

    @Autowired
    private TreeHouseService treeHouseService;

    @Autowired
    private TreeHouseFurnitureService treeHouseFurnitureService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private TreeHouseWorkLogService treeHouseWorkLogService;



    /**
     * 根据树屋的家具布置持续的时间，定时增加娱乐值;
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    //@Scheduled(cron = "0 */1 * * * ?")
    public void work() {
        log.info("------每1小时根据家具给树屋增加娱乐值------");
        List<TreeHouseFurniture> furnitureList=treeHouseFurnitureService.findCollocations();
        for(TreeHouseFurniture tFurniture:furnitureList){
            if(StringUtils.isNotBlank(tFurniture.getGoodsId()))
            {
                Furniture furniture=furnitureService.getByCategoryId(tFurniture.getGoodsId());
                if(furniture!=null){
                    int amuseCount=furniture.getOutputValue();
                    treeHouseService.updateTreeHouse(tFurniture.getTreeHouseId(),amuseCount);
                }
            }

        }
    }

    /**
     * 玩家在树屋挂机每15分钟获得60~80点人气值
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void addAmbience() {
        Map<String,Integer> users=chatMessageService.queryOnLineUser();
        users.forEach((k,v)->{
            treeHouseService.addAmbienceCount(k,v);
        });
    }

    /**
     * 删除过期的家具
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void deleteOverdueFurniture() {
        treeHouseFurnitureService.deleteOverdueFurniture();
    }

    /**
     * 删除2小时前的工作消息
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void deleteBefore2HoursTreeHouseWorkLog() {
        treeHouseWorkLogService.deleteBefore2Hour();
    }

}
