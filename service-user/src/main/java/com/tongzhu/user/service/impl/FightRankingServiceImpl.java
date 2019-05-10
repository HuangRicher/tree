package com.tongzhu.user.service.impl;

import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.mapper.FightRankingMapper;
import com.tongzhu.user.mapper.ext.FightRankingExtMapper;
import com.tongzhu.user.mapper.vo.FightRankingDO;
import com.tongzhu.user.mapper.vo.FightRankingLogDO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.FightRankingDetailVO;
import com.tongzhu.util.DateComputeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightRankingServiceImpl implements FightRankingService {

    @Autowired
    private FightRankingMapper fightRankingMapper;

    @Autowired
    private FightRankingExtMapper fightRankingExtMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FightRankingLogService fightRankingLogService;

    @Autowired
    private FightRankingSettingService fightRankingSettingService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMailSingleService userMailSingleService;




    @Override
    public void sendAwardMail(int type) {
        //type=1 每天24：00服务器通过邮件发送排位前1000名玩家的奖励
        if(type==1){
            fightRankingExtMapper.sendDayAward();
        }
        //type=2 每个赛季结束的最后一天24:00，服务器通过邮件发送排位赛季奖励
        if(type==2){
            fightRankingExtMapper.sendSeasonAward();
        }
    }

    @Override
    public void updateRanking(String userId, int rankCount) {
        FightRanking ranking=fightRankingMapper.selectByPrimaryKey(userId);
        if(ranking!=null && ranking.getRanking()+rankCount>0){
            ranking.setScore(ranking.getScore()+rankCount);
            fightRankingMapper.updateByPrimaryKey(ranking);
        }
    }

    @Override
    public void add(String userId) {
        FightRankingSetting setting=fightRankingSettingService.getByScore(1500);
        if(setting!=null){
            LocalDate localDate=LocalDate.now();
            FightRanking ranking=new FightRanking();
            ranking.setRanking(setting.getRankId());
            ranking.setGrade(setting.getGrade());
            ranking.setHonor(0);
            ranking.setUserId(userId);
            ranking.setFightYear(localDate.getYear());
            ranking.setFightSeason(DateComputeUtil.getSeason(localDate));
            ranking.setScore(1500);
            fightRankingMapper.insertSelective(ranking);
        }


    }

    @Override
    public List<FightRankingDO> queryOrder50() {
        return fightRankingExtMapper.queryOrder50();
    }

    @Override
    public FightRanking getByUserId(String userId) {
        FightRankingExample example=new FightRankingExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<FightRanking> rankingList=fightRankingMapper.selectByExample(example);
        if(rankingList!=null && !rankingList.isEmpty()){
            return rankingList.get(0);
        }
        return null;
    }

    @Override
    public User matchFighter(String userId) {
        int count=0;
        List<FightRankingLogDO> fightRankingLogList=fightRankingLogService.findDOByUserId(userId);
        if(fightRankingLogList.size()>=3){
            for(int i=0;i<3;i++){
                if(fightRankingLogList.get(i).getFightResult()==0)
                    count++;
            }
        }
        if(count==3){
            //当一个玩家连续输掉3场战斗时，下一场必然匹配到电脑，电脑的数据取同等级的角色基础属性*1.5，
            // 职业随机，名字从名字库里找，微信头像用默认头像
            //TODO

        }else {
            FightRanking ranking=this.getByUserId(userId);
            /**
             * rank分跟自己相差＜100的
             * rank分跟自己相差101~1000的
             * rank分跟自己相差1001~2000的
             * rank分跟自己相差2001~3000的
             * rank分跟自己相差3001~4000的
             * rank分跟自己相差4001~5000的
             * 随机选择一名对手
             */
            if(ranking!=null){
                int max=0;
                int min=0;
                FightRanking fightRank=null;
                min=ranking.getScore()-100<0?0:ranking.getScore()-100;
                max=ranking.getScore()+100;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }
                min=ranking.getScore()-1000<0?0:ranking.getScore()-1000;
                max=ranking.getScore()+1000;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }
                min=ranking.getScore()-2000<0?0:ranking.getScore()-2000;
                max=ranking.getScore()+2000;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }

                min=ranking.getScore()-3000<0?0:ranking.getScore()-3000;
                max=ranking.getScore()+3000;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }

                min=ranking.getScore()-4000<0?0:ranking.getScore()-4000;
                max=ranking.getScore()+4000;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }
                min=ranking.getScore()-5000<0?0:ranking.getScore()-5000;
                max=ranking.getScore()+5000;
                fightRank=this.findOneByRand(min,max);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }

                fightRank=this.findOneByRand(0,0);
                if(fightRank!=null){
                    return userService.findByUserId(fightRank.getUserId());
                }
            }else{
                return userService.selectUserForFightRankingByRand(userId);
            }
        }
        return null;


    }

    @Override
    public FightRankingDetailVO fightRankingDetail(String userId) {
        List<FightRankingLogDO> fightRankingLogList=fightRankingLogService.findDOByUserId(userId);
        int winCount=0;
        for(FightRankingLog log:fightRankingLogList){
            if(log.getFightResult()==1)
                winCount++;
        }
        float rate= (float)winCount/fightRankingLogList.size();
        DecimalFormat df = new DecimalFormat("0.00");
        FightRanking fightRanking=this.getByUserId(userId);
        FightRankingSetting setting=fightRankingSettingService.getByRankIdAndGrade(fightRanking.getRanking(),fightRanking.getGrade());
        FightRankingDetailVO fightRankingDetailVO=new FightRankingDetailVO();
        fightRankingDetailVO.setCurrentGrade(setting.getName());
        fightRankingDetailVO.setCurrentHonor(fightRanking.getHonor());
        fightRankingDetailVO.setCurrentScore(fightRanking.getScore());
        fightRankingDetailVO.setFightRankingLogList(fightRankingLogList);
        fightRankingDetailVO.setCurrentWinRate(Float.valueOf(df.format(rate)));
        fightRankingDetailVO.setFullScore(setting.getMaxRank());
        fightRankingDetailVO.setFightCount((int)redisService.get(RedisKey.FIGHT_RANKING_DAY_COUNT+"_"+userId));
        Map<String,Object> award=new HashMap<>();
        award.put("money",setting.getAwardMoneySeason());
        award.put("honor",setting.getAwardHonorSeason());
        fightRankingDetailVO.setSeasonAward(award);
        LocalDate localDate=LocalDate.now();
        int year=localDate.getYear();
        int season=DateComputeUtil.getSeason(localDate);
        fightRankingDetailVO.setSeasonName(year+"_"+season);
        return fightRankingDetailVO;
    }

    private FightRanking findOneByRand(Integer min,Integer max){
        if(min>0 && max>0){
            FightRankingExample example=new FightRankingExample();
            example.createCriteria().andScoreBetween(min,max);
            example.setOrderByClause(" RAND() LIMIT 1 ");
            List<FightRanking> list=fightRankingMapper.selectByExample(example);
            if(list!=null && !list.isEmpty()){
                return list.get(0);
            }else {
                return null;
            }
        }else {
            FightRankingExample example=new FightRankingExample();
            example.setOrderByClause(" RAND() LIMIT 1 ");
            List<FightRanking> list=fightRankingMapper.selectByExample(example);
            if(list!=null && !list.isEmpty()){
                return list.get(0);
            }else {
                return null;
            }
        }
    }
}
