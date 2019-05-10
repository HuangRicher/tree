package com.tongzhu.user.job;

import com.tongzhu.user.constant.RanklingListConstant;
import com.tongzhu.user.constant.RoleTitleConstant;
import com.tongzhu.user.mapper.vo.UserRankingVO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.UserCrunchiesService;
import com.tongzhu.user.service.UserGoodsService;
import com.tongzhu.user.service.UserRoleService;
import com.tongzhu.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecoveryUserVigourTask {

    private static Logger log = LoggerFactory.getLogger(RecoveryUserVigourTask.class);

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCrunchiesService userCrunchiesService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 每15分钟给用户恢复1点精力(精力上限为25)
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void work() {
        log.info("------每15分钟给用户恢复1点精力(精力上限为25)------");
        userGoodsService.addVigourForSchedule();
    }

    /**
     * 每十分钟查询一次排行榜，缓存Redis
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateLevelRankingList() {
        log.info("------更新等级排行总榜------");
        List<UserRankingVO> userRankingVOAboutRoleLevel = userService.findForRankingListAboutRoleLevel(RanklingListConstant.ROLE_LEVEL_RANKING_SHOW_COUNT);
        redisService.removePattern(RanklingListConstant.ROLE_LEVEL_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOAboutRoleLevel) {
            redisService.lPush(RanklingListConstant.ROLE_LEVEL_REDIS, userRankingVO);
        }
        log.info("------更新魅力值排行总榜------");
        List<UserRankingVO> userRankingVOAboutCharmNum = userService.findForRankingListAboutCharmNum(RanklingListConstant.CHARM_NUM_RANKING_SHOW_COUNT);
        redisService.removePattern(RanklingListConstant.CHARM_NUM_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOAboutCharmNum) {
            if (userRankingVO.getSerialNo() == 1) {
                userRoleService.addRoleTitle(RoleTitleConstant.TITLE_5,userRankingVO.getUserId());
            }
            redisService.lPush(RanklingListConstant.CHARM_NUM_REDIS, userRankingVO);
        }
        log.info("------更新战斗力排行总榜------");
        List<UserRankingVO> userRankingVOAlwaysFighting = userService.findForRankingListAboutAlwaysFighting(RanklingListConstant.FIGHTING_RANKING_SHOW_COUNT);
        redisService.removePattern(RanklingListConstant.FIGHTING_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOAlwaysFighting) {
            if (userRankingVO.getSerialNo() == 1) {
                userRoleService.addRoleTitle(RoleTitleConstant.TITLE_4,userRankingVO.getUserId());
            }
            redisService.lPush(RanklingListConstant.FIGHTING_REDIS, userRankingVO);
        }

//        log.info("------更新战斗力排行月榜------");
//        List<UserRankingVO> userRankingVOMonthFighting = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_FIGHTING_RANKING_SHOW_COUNT, RanklingListConstant.MONTH_FIGHTING_TYPE);
//        redisService.removePattern(RanklingListConstant.MONTH_FIGHTING_REDIS);
//        for (UserRankingVO userRankingVO:userRankingVOMonthFighting) {
//            redisService.lPush(RanklingListConstant.MONTH_FIGHTING_REDIS,userRankingVO);
//        }

        log.info("------更新魅力值排行月榜------");
        List<UserRankingVO> userRankingVOMonthCharmNum = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_CHARM_NUM_RANKING_SHOW_COUNT, RanklingListConstant.MONTH_CHARM_NUM_TYPE);
        redisService.removePattern(RanklingListConstant.MONTH_CHARM_NUM_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOMonthCharmNum) {
            redisService.lPush(RanklingListConstant.MONTH_CHARM_NUM_REDIS, userRankingVO);
        }

        log.info("------更新钻石排行月榜------");
        List<UserRankingVO> userRankingVOMonthZSNum = userCrunchiesService.fightingMonthRankingList(RanklingListConstant.MONTH_ZS_RANKING_SHOW_COUNT, RanklingListConstant.MONTH_ZS_NUM_TYPE);
        redisService.removePattern(RanklingListConstant.MONTH_ZS_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOMonthZSNum) {
            redisService.lPush(RanklingListConstant.MONTH_ZS_REDIS, userRankingVO);
        }
        log.info("------更新拥有宠物排行总榜------");
        List<UserRankingVO> userRankingVOSPet = userService.findForRankingListAboutPet(RanklingListConstant.USER_PET_RANKING_SHOW_COUNT);
        redisService.removePattern(RanklingListConstant.USER_PET_REDIS);
        for (UserRankingVO userRankingVO : userRankingVOSPet) {
            redisService.lPush(RanklingListConstant.USER_PET_REDIS, userRankingVO);
        }
    }
}
