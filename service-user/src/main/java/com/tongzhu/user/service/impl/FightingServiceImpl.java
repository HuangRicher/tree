package com.tongzhu.user.service.impl;

import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.domain.FightParameter;
import com.tongzhu.user.service.FightCommonService;
import com.tongzhu.user.service.FightingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightingServiceImpl implements FightingService {

    private Logger logger= LoggerFactory.getLogger(FightingServiceImpl.class);

    @Autowired
    private FightCommonService fightCommonService;




    @Override
    public boolean pvpFightProcess(Combatant peerA, Combatant peerB, List<FightStepDetailVO> fightingStepList) {
        boolean isWin=false;//peerA的战斗结果
        boolean isContinue=true;
        Date startdate1=new Date();
        int fightCase=fight(peerA,peerB,fightingStepList);
        logger.info("{}--先攻->{} 耗时{}",peerA.getUserName(),peerB.getUserName(),new Date().getTime()-startdate1.getTime());
        if(fightCase==1){
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
            Map<String,Object> deadPlayer=new HashMap<>();
            deadPlayer.put("group",peerB.getGroup());
            deadPlayer.put("index",peerB.getGroupIndex());
            stepDetail.setDeadPlayer(deadPlayer);
            fightingStepList.add(stepDetail);
            isContinue=false;
            isWin=true;
        }else if(fightCase==2){
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
            Map<String,Object> deadPlayer=new HashMap<>();
            deadPlayer.put("group",peerA.getGroup());
            deadPlayer.put("index",peerA.getGroupIndex());
            stepDetail.setDeadPlayer(deadPlayer);
            fightingStepList.add(stepDetail);
            isWin=false;
            isContinue=false;
        }else {
            isContinue=true;
        }
        //最多25个回合
        int count=0;
        while (isContinue && count<25){
            count++;
            //peerA 攻击 peerB
            startdate1=new Date();
            if(count>1){
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_98);
                stepDetail.setFightingCount(count);
                fightingStepList.add(stepDetail);
            }
            fightCase=fight(peerA,peerB,fightingStepList);
            logger.info("{}--->{} 耗时{}",peerA.getUserName(),peerB.getUserName(),new Date().getTime()-startdate1.getTime());

            if(fightCase==1){
                FightStepDetailVO stepDetail22=new FightStepDetailVO();
                stepDetail22.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerB.getGroup());
                deadPlayer.put("index",peerB.getGroupIndex());
                stepDetail22.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail22);
                isWin=true;
                break;
            }
            if(fightCase==2){
                FightStepDetailVO stepDetail22=new FightStepDetailVO();
                stepDetail22.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerA.getGroup());
                deadPlayer.put("index",peerA.getGroupIndex());
                stepDetail22.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail22);
                isWin=false;
                break;
            }
            //peerB 攻击 peerA
            startdate1=new Date();
            fightCase=fight(peerB,peerA,fightingStepList);
            logger.info("{}--->{} 耗时{}",peerB.getUserName(),peerA.getUserName(),new Date().getTime()-startdate1.getTime());
            if(fightCase==1){
                FightStepDetailVO stepDetail22=new FightStepDetailVO();
                stepDetail22.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerA.getGroup());
                deadPlayer.put("index",peerA.getGroupIndex());
                stepDetail22.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail22);
                isWin=false;
                break;
            }
            if(fightCase==2){
                FightStepDetailVO stepDetail22=new FightStepDetailVO();
                stepDetail22.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerB.getGroup());
                deadPlayer.put("index",peerB.getGroupIndex());
                stepDetail22.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail22);
                isWin=true;
                break;
            }
        }
        return isWin;
    }

    /**
     * 每一回合战斗过程
     * @param peerA
     * @param peerB
     * @param resultData
     * @return   1:peerA胜利   2：peerB胜利  0：战斗继续
     */
    public int fight(Combatant peerA,Combatant peerB,List<FightStepDetailVO> resultData){

        Integer hurtCount=0;
        Integer toxicosisCount=0; //中毒伤害数
        Integer bloodCount=0;     //生命回复值
        Integer hurtBackCount=0;  //反噬伤害数
        FightParameter fightParameter=new FightParameter(hurtCount,toxicosisCount,bloodCount,hurtBackCount);
        //检测debuff
        //检测是否拥有技能 1038,每回合有xx%概率移除自身所有debuff  skillId:32 心如止水
        //               1040,自身有debuff时，xx%概率触发，净化所有debuff，接下来3回合不受debuff影响  skillId: 34 菩提之光
        boolean isCleanDeBuff=fightCommonService.checkIsCleanDebuff(peerA,peerB,resultData);


        fightParameter.setSkip1(false);
        int resolveDebuffResult=fightCommonService.solveDebuffResult(peerA,peerB,resultData,isCleanDeBuff,fightParameter);
        if(resolveDebuffResult<3){
            return resolveDebuffResult;
        }
        fightParameter.setSkip2(false);
        if((!fightParameter.getSkip1())) {//使用大招，技能
            int useBigAndSkill= fightCommonService.useBigAndSkill(peerA,peerB,resultData,fightParameter);
            if(useBigAndSkill<3){
                return useBigAndSkill;
            }
        }

        fightParameter.setType(0);//1:徒手 2：武器
        fightParameter.setSkip3(false);
        if((!fightParameter.getSkip2())){//使用武器攻击
            int useWeapon= fightCommonService.useWeapon(peerA,peerB,resultData,fightParameter);
            if(useWeapon<3){
                return useWeapon;
            }
        }

        if((!fightParameter.getSkip3())){ //得出最终伤害,判断扣除血量后是否致死
            int computeResult=fightCommonService.computeResult(peerA,peerB,resultData,fightParameter);
            if(computeResult<3){
                return computeResult;
            }
        }
        fightCommonService.reduceFreezeCount(peerA.getSkillList());
        //减一次所有的buff debuff
        if(peerA.getBuff()!=null){
            fightCommonService.subCountOne(peerA.getBuff());
        }
        if(peerA.getDebuff()!=null){
            fightCommonService.subCountOne(peerA.getDebuff());
        }

        if(toxicosisCount>0){
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            Map<String,Object> toxicosisMap=new HashMap<>();
            toxicosisMap.put("group",peerA.getGroup());
            toxicosisMap.put("index",peerA.getGroupIndex());
            toxicosisMap.put("value",toxicosisCount);
            stepDetail.setToxicosisCount(toxicosisMap);
            toxicosisCount=0;
            resultData.add(stepDetail);
        }
        return 0;
    }

}
