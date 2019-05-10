package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.domain.FightParameter;
import com.tongzhu.user.mapper.FightCopyMapper;
import com.tongzhu.user.model.FightCopy;
import com.tongzhu.user.model.FightCopyExample;
import com.tongzhu.user.service.FightCopyCommonService;
import com.tongzhu.user.service.FightCopyService;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Service
public class FightCopyServiceImpl implements FightCopyService {

    private Logger logger= LoggerFactory.getLogger(FightingServiceImpl.class);

    @Autowired
    private FightCopyMapper fightCopyMapper;

    @Autowired
    private FightCopyCommonService fightCopyCommonService;


    @Override
    public List<FightCopy> findAll() {
        FightCopyExample example=new FightCopyExample();
        return fightCopyMapper.selectByExample(example);
    }

    @Override
    public FightCopy getById(Integer id) {
        return fightCopyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FightCopy> findByType(Integer type) {
        FightCopyExample example=new FightCopyExample();
        example.createCriteria().andTypeEqualTo(type);
        example.setOrderByClause(" id ASC ");
        return fightCopyMapper.selectByExample(example);
    }

    @Override
    public void pveFightProcess(List<Map<String,Object>> attackGroup,List<Map<String,Object>> defendGroup,Map<String,Integer> awardMap,
                                List<Combatant> groupA, List<Combatant> groupB, List<FightStepDetailVO> fightingStepList) {
        boolean isContinue=true;
        int fightCase=6; // fightCase  1:peerA胜利   2：peerB胜利  0：战斗继续
        if(groupA.get(0).getGroup()==1){
            logger.info("{}--先攻->{}",groupA.get(0).getUserName(),groupB.get(0).getCombatantList().get(0).getUserName());
            int listSize=groupB.get(0).getCombatantList().size();
            int index=RandomUtil.random(0,listSize-1);
            Combatant peerA=groupA.get(0);
            Combatant peerB=groupB.get(0).getCombatantList().get(index);

            fightCopyCommonService.getCombatant(attackGroup,peerA);
            fightCopyCommonService.getCombatant(defendGroup,groupB.get(0));

            fightCase=fight(peerA,peerB,fightingStepList,groupB,awardMap,defendGroup);
            if(fightCase==2){//怪赢了,玩家被kill
                logger.info("玩家{}被kill",peerA.getUserName());
                FightStepDetailVO stepDetail=new FightStepDetailVO();
                stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerA.getGroup());
                deadPlayer.put("index",peerA.getGroupIndex());
                stepDetail.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail);
                removeCombatant(groupA,0);
            }
            if(fightCase==1){//玩家赢了，怪被kill
                logger.info("怪物{}[{}]被kill掉了一只",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                FightStepDetailVO stepDetail1=new FightStepDetailVO();
                stepDetail1.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",groupB.get(0).getGroup());
                deadPlayer.put("index",groupB.get(0).getGroupIndex());
                stepDetail1.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail1);

                if(StringUtils.isNotBlank(peerB.getMonster().getDrop())){
                    addAward(awardMap,peerB);
                }

                removeCombatant(groupB.get(0).getCombatantList(),index);
                if(groupB.get(0).getCombatantList().isEmpty()){
                    logger.info("怪物{}[{}]被全部kill",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                    removeCombatant(groupB,0);
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(100);
                    stepDetail.setSelfIndex(1);
                    stepDetail.setOpponentIndex(2);
                    fightingStepList.add(stepDetail);
                }
            }
            fightMainLoad(attackGroup,defendGroup,awardMap,groupA,groupB,isContinue,fightingStepList,fightCase);
        }else{
            logger.info("{}--先攻->{}",groupA.get(0).getCombatantList().get(0).getUserName(),groupB.get(0).getUserName());
            int listSize=groupA.get(0).getCombatantList().size();
            int index=RandomUtil.random(0,listSize-1);
            Combatant peerA=groupA.get(0).getCombatantList().get(index);
            Combatant peerB=groupB.get(0);

            fightCopyCommonService.getCombatant(attackGroup,peerB);
            fightCopyCommonService.getCombatant(defendGroup,groupA.get(0));

            fightCase=fight(peerA,peerB,fightingStepList,groupA,awardMap,defendGroup);
            if(fightCase==1){//怪赢了,玩家被kill
                logger.info("玩家{}被kill",peerB.getUserName());
                FightStepDetailVO stepDetail1=new FightStepDetailVO();
                stepDetail1.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",peerB.getGroup());
                deadPlayer.put("index",peerB.getGroupIndex());
                stepDetail1.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail1);
                removeCombatant(groupB,0);
            }
            if(fightCase==2){//玩家赢了，怪被kill
                logger.info("怪物{}[{}]被kill了一只",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                FightStepDetailVO stepDetail1=new FightStepDetailVO();
                stepDetail1.setType(FightingConstant.FIGHT_STEP_TYPE_99);
                Map<String,Object> deadPlayer=new HashMap<>();
                deadPlayer.put("group",groupA.get(0).getGroup());
                deadPlayer.put("index",groupA.get(0).getGroupIndex());
                stepDetail1.setDeadPlayer(deadPlayer);
                fightingStepList.add(stepDetail1);

                if(StringUtils.isNotBlank(peerA.getMonster().getDrop())){
                    addAward(awardMap,peerA);
                }
                removeCombatant(groupA.get(0).getCombatantList(),index);
                if(groupA.get(0).getCombatantList().isEmpty()){
                    logger.info("怪物{}[{}]被全部kill",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                    removeCombatant(groupA,0);
                }
            }
            fightMainLoad(attackGroup,defendGroup,awardMap,groupA,groupB,isContinue,fightingStepList,fightCase);
        }
    }


    private void fightMainLoad(List<Map<String,Object>> attackGroup,List<Map<String,Object>> defendGroup,
                               Map<String,Integer> awardMap,List<Combatant> groupA, List<Combatant> groupB,
                               boolean isContinue, List<FightStepDetailVO> fightingStepList,Integer fightCase){
        //单轮战斗最多25个回合
        int count=0;
        int temp=0;
        if(groupA.get(0).getGroup()==1){//玩家先攻击怪物
            boolean notFirst=false;
            while (!groupA.isEmpty() && !groupB.isEmpty() && count<25){
                count++;
                temp=count;
                //groupA 攻击 groupB
                if(count>1 || notFirst){
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_98);
                    stepDetail.setFightingCount(count);
                    fightingStepList.add(stepDetail);
                }
                int listSize1=groupB.get(0).getCombatantList().size();
                int index1=RandomUtil.random(0,listSize1-1);
                Combatant peerA1=groupA.get(0);
                Combatant peerB1=groupB.get(0).getCombatantList().get(index1);
                int preCount=groupB.size();
                fightCase=fight(peerA1,peerB1,fightingStepList,groupB,awardMap,defendGroup);
                int afterCount=groupB.size();
                if(afterCount< preCount){
                    count=0;
                    notFirst=true;
                }
                logger.info("回合[{}]:{}-->{}",temp,peerA1.getUserName(),peerB1.getUserName());
                if(fightCase==2){//怪赢了,玩家被kill
                    logger.info("玩家{}被kill",peerA1.getUserName());
                    removeCombatant(groupA,0);
                    count=0;
                    if(!CollectionUtils.isEmpty(groupA)){
                        fightCopyCommonService.getCombatant(attackGroup,groupA.get(0));
                    }
                    if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        stepDetail.setType(100);
                        stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                        stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                        fightingStepList.add(stepDetail);
                    }
                }
                if(fightCase==1){//玩家赢了，怪被kill
                    logger.info("怪物{}[{}]被kill掉了一只",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                    if(StringUtils.isNotBlank(peerB1.getMonster().getDrop())){
                        addAward(awardMap,peerB1);
                    }
                    removeCombatant(groupB.get(0).getCombatantList(),index1);
                    if(groupB.get(0).getCombatantList().isEmpty()){
                        logger.info("怪物{}[{}]被全部kill",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                        removeCombatant(groupB,0);
                        count=0;
                        if(!CollectionUtils.isEmpty(groupB)){
                            fightCopyCommonService.getCombatant(defendGroup,groupB.get(0));
                        }
                        if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                            FightStepDetailVO stepDetail=new FightStepDetailVO();
                            stepDetail.setType(100);
                            stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                            stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                            fightingStepList.add(stepDetail);
                        }
                    }
                }
                if(groupA.isEmpty()) break;
                if(groupB.isEmpty()) break;
                //groupB 攻击 groupA
                int listSize2=groupB.get(0).getCombatantList().size();
                int index2=RandomUtil.random(0,listSize2-1);
                Combatant peerA2=groupA.get(0);
                Combatant peerB2=groupB.get(0).getCombatantList().get(index2);
                logger.info("回合[{}]:{}--->{}",temp,peerB2.getUserName(),peerA2.getUserName());
                fightCase=fight(peerB2,peerA2,fightingStepList,groupB,awardMap,defendGroup);
                if(fightCase==1){//怪赢了,玩家被kill
                    logger.info("玩家{}被kill",peerA2.getUserName());
                    removeCombatant(groupA,0);
                    count=0;
                    if(!CollectionUtils.isEmpty(groupA)){
                        fightCopyCommonService.getCombatant(attackGroup,groupA.get(0));
                    }
                    if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        stepDetail.setType(100);
                        stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                        stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                        fightingStepList.add(stepDetail);
                    }
                }
                if(fightCase==2){//玩家赢了，怪被kill
                    logger.info("怪物{}[{}]被kill掉了一只",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                    if(StringUtils.isNotBlank(peerB1.getMonster().getDrop())){
                        addAward(awardMap,peerB1);
                    }
                    removeCombatant(groupB.get(0).getCombatantList(),index2);
                    if(groupB.get(0).getCombatantList().isEmpty()){
                        logger.info("怪物{}[{}]被全部kill",groupB.get(0).getUserName(),groupB.get(0).getGroupIndex());
                        removeCombatant(groupB,0);
                        count=0;
                        if(!CollectionUtils.isEmpty(groupB)){
                            fightCopyCommonService.getCombatant(defendGroup,groupB.get(0));
                        }
                        if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                            FightStepDetailVO stepDetail=new FightStepDetailVO();
                            stepDetail.setType(100);
                            stepDetail.setSelfIndex(groupA.get(0).getGroupIndex());
                            stepDetail.setOpponentIndex(groupB.get(0).getGroupIndex());
                            fightingStepList.add(stepDetail);
                        }
                    }
                }
            }
        }else {//怪物先攻击玩家
            boolean notFirst=false;
            while (!groupA.isEmpty() && !groupB.isEmpty() && count<25){
                count++;
                temp=count;
                //groupA 攻击 groupB
                if(count>1 || notFirst){
                    FightStepDetailVO stepDetail=new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_98);
                    stepDetail.setFightingCount(count);
                    fightingStepList.add(stepDetail);
                }
                int listSize1=groupA.get(0).getCombatantList().size();
                int index1=RandomUtil.random(0,listSize1-1);
                Combatant peerA1=groupA.get(0).getCombatantList().get(index1);
                Combatant peerB1=groupB.get(0);
                logger.info("回合[{}]:{}--->{}",temp,peerA1.getUserName(),peerB1.getUserName());
                fightCase=fight(peerA1,peerB1,fightingStepList,groupA,awardMap,defendGroup);
                if(fightCase==1){//怪赢了,玩家被kill
                    logger.info("玩家{}被kill",peerB1.getUserName());
                    removeCombatant(groupB,0);
                    count=0;
                    if(!CollectionUtils.isEmpty(groupB)){
                        fightCopyCommonService.getCombatant(attackGroup,groupB.get(0));
                    }
                    if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        stepDetail.setType(100);
                        stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                        stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                        fightingStepList.add(stepDetail);
                    }
                }
                if(fightCase==2){//玩家赢了，怪被kill
                    logger.info("怪物{}[{}]被kill了一只",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                    if(StringUtils.isNotBlank(peerA1.getMonster().getDrop())){
                        addAward(awardMap,peerA1);
                    }
                    removeCombatant(groupA.get(0).getCombatantList(),index1);
                    if(groupA.get(0).getCombatantList().isEmpty()){
                        logger.info("怪物{}[{}]被全部kill",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                        removeCombatant(groupA,0);
                        count=0;
                        if(!CollectionUtils.isEmpty(groupA)){
                            fightCopyCommonService.getCombatant(defendGroup,groupA.get(0));
                        }
                        if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                            FightStepDetailVO stepDetail=new FightStepDetailVO();
                            stepDetail.setType(100);
                            stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                            stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                            fightingStepList.add(stepDetail);
                        }
                    }
                }
                if(groupA.isEmpty()) break;
                if(groupB.isEmpty()) break;
                //groupB 攻击 groupA
                int listSize2=groupA.get(0).getCombatantList().size();
                int index2=RandomUtil.random(0,listSize2-1);
                Combatant peerA2=groupA.get(0).getCombatantList().get(index2);
                Combatant peerB2=groupB.get(0);
                logger.info("回合[{}]:{}--->{}",temp,peerB2.getUserName(),peerA2.getUserName());
                int preCount=groupA.size();
                fightCase=fight(peerB2,peerA2,fightingStepList,groupA,awardMap,defendGroup);
                int afterCount=groupA.size();
                if(afterCount<preCount){
                    count=0;
                    notFirst=true;
                }
                if(fightCase==2){//怪赢了,玩家被kill
                    logger.info("玩家{}被kill",peerB2.getUserName());
                    removeCombatant(groupB,0);
                    count=0;
                    if(!CollectionUtils.isEmpty(groupB)){
                        fightCopyCommonService.getCombatant(attackGroup,groupB.get(0));
                    }
                    if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                        FightStepDetailVO stepDetail=new FightStepDetailVO();
                        stepDetail.setType(100);
                        stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                        stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                        fightingStepList.add(stepDetail);
                    }
                }
                if(fightCase==1){//玩家赢了，怪被kill
                    logger.info("怪物{}[{}]被kill了一只",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                    if(StringUtils.isNotBlank(peerA2.getMonster().getDrop())){
                        addAward(awardMap,peerA2);
                    }
                    removeCombatant(groupA.get(0).getCombatantList(),index2);
                    if(groupA.get(0).getCombatantList().isEmpty()){
                        logger.info("怪物{}[{}]被全部kill",groupA.get(0).getUserName(),groupA.get(0).getGroupIndex());
                        removeCombatant(groupA,0);
                        count=0;
                        if(!CollectionUtils.isEmpty(groupA)){
                            fightCopyCommonService.getCombatant(defendGroup,groupA.get(0));
                        }
                        if(!CollectionUtils.isEmpty(groupA) && !CollectionUtils.isEmpty(groupB)){
                            FightStepDetailVO stepDetail=new FightStepDetailVO();
                            stepDetail.setType(100);
                            stepDetail.setSelfIndex(groupB.get(0).getGroupIndex());
                            stepDetail.setOpponentIndex(groupA.get(0).getGroupIndex());
                            fightingStepList.add(stepDetail);
                        }
                    }
                }
            }
        }
    }

    private void removeCombatant(List<Combatant>  combatantList,int index){
        ListIterator<Combatant> it=combatantList.listIterator();
        while (it.hasNext()){
            if(index==it.nextIndex()){
                it.next();
                it.remove();
                break;
            }else{
                it.next();
            }
        }
    }




    /**
     * 每一回合战斗过程
     * @param peerA
     * @param peerB
     * @param resultData
     * @return   1:peerA胜利   2：peerB胜利  0：战斗继续
     */
    public int fight(Combatant peerA,Combatant peerB,List<FightStepDetailVO> resultData,List<Combatant> groupB,Map<String,Integer> awardMap,List<Map<String,Object>> defendGroup){
        Integer hurtCount=0;
        Integer toxicosisCount=0; //中毒伤害数
        Integer bloodCount=0;     //生命回复值
        Integer hurtBackCount=0;  //反噬伤害数
        FightParameter fightParameter=new FightParameter(hurtCount,toxicosisCount,bloodCount,hurtBackCount);
        //检测debuff
        //检测是否拥有技能 1038,每回合有xx%概率移除自身所有debuff  skillId:32 心如止水
        //               1040,自身有debuff时，xx%概率触发，净化所有debuff，接下来3回合不受debuff影响  skillId: 34 菩提之光
        boolean isCleanDeBuff=fightCopyCommonService.checkIsCleanDebuff(peerA,peerB,resultData);


        fightParameter.setSkip1(false);
        int resolveDebuffResult=fightCopyCommonService.solveDebuffResult(peerA,peerB,resultData,isCleanDeBuff,fightParameter);
        if(resolveDebuffResult<3){
            return resolveDebuffResult;
        }

        fightParameter.setSkip2(false);
        if((!fightParameter.getSkip1())) {//使用大招，技能
            int useBigAndSkill= fightCopyCommonService.useBigAndSkill(peerA,peerB,resultData,fightParameter,groupB,awardMap,defendGroup);
            if(useBigAndSkill<3){
                return useBigAndSkill;
            }
        }

        fightParameter.setSkip3(true);
        fightParameter.setType(0);//1:徒手 2：武器
        if(peerA.getMonster()==null){
            if(!fightParameter.getSkip2()){//使用武器攻击
                int useWeapon= fightCopyCommonService.useWeapon(peerA,peerB,resultData,fightParameter,groupB);
                if(useWeapon<3){
                    return useWeapon;
                }
                if((!fightParameter.getSkip3())){ //得出最终伤害,判断扣除血量后是否致死
                    int computeResult=fightCopyCommonService.computeResult(peerA,peerB,resultData,fightParameter);
                    if(computeResult<3){
                        return computeResult;
                    }
                }
            }
        }else{
            //fightParameter.setSkip3(false);
            int attackCount=fightCopyCommonService.getUserAggressivity(peerA,peerA.getWornWeapon());//攻击力
            int defenceCount=fightCopyCommonService.getUserDefence(peerA,peerB,peerA.getWornWeapon());//防御力
            int hurtCountEnd= fightCopyCommonService.computeHurtCount(peerA,attackCount,defenceCount);
            fightParameter.setHurtCount(hurtCountEnd);
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightingUtil.generateFightingStep(peerA,peerB,1,SkillConstant.SKILL_994  ,false,1,hurtCountEnd,stepDetail);
            if(peerA.getFuryValue()!=null){
                int furyA = peerA.getFuryValue();
                furyA = furyA + RandomUtil.random(8, 12);
                peerA.setFuryValue(furyA > FightingConstant.MAX_FURY_VALUE ? FightingConstant.MAX_FURY_VALUE : furyA);
            }
            if(peerB.getFuryValue()!=null){
                int furyB = peerB.getFuryValue();
                furyB=furyB + RandomUtil.random(12, 16);
                peerB.setFuryValue(furyB> FightingConstant.MAX_FURY_VALUE?FightingConstant.MAX_FURY_VALUE:furyB);
                stepDetail.setSelfFuryValue(peerA.getFuryValue());
            }
            if(peerA.getGroup()==1){
                stepDetail.setSelfFuryValue(peerA.getFuryValue());
                stepDetail.setOpponentFuryValue(peerB.getFuryValue());
                stepDetail.setSelfBlood(peerA.getBlood());
                stepDetail.setOpponentBlood((peerB.getBlood()-hurtCountEnd)>0?(peerB.getBlood()-hurtCountEnd):0);
            }else{
                stepDetail.setSelfFuryValue(peerB.getFuryValue());
                stepDetail.setOpponentFuryValue(peerA.getFuryValue());
                stepDetail.setSelfBlood(peerB.getBlood());
                stepDetail.setOpponentBlood((peerA.getBlood()-hurtCountEnd)>0?(peerA.getBlood()-hurtCountEnd):0);
            }
            resultData.add(stepDetail);

            int computeResult=fightCopyCommonService.computeResult(peerA,peerB,resultData,fightParameter);
            if(computeResult<3){
                return computeResult;
            }
        }

        fightCopyCommonService.reduceFreezeCount(peerA.getSkillList());
        //减一次所有的buff debuff
        if(peerA.getBuff()!=null){
            fightCopyCommonService.subCountOne(peerA.getBuff());
        }
        if(peerA.getDebuff()!=null){
            fightCopyCommonService.subCountOne(peerA.getDebuff());
        }

        if(toxicosisCount>0){
            FightStepDetailVO stepDetail=new FightStepDetailVO();
            FightingUtil.dealToxicosisCount(peerA,toxicosisCount,stepDetail);
            resultData.add(stepDetail);
        }
        return 0;
    }


    private void addAward(Map<String,Integer> awardMap,Combatant peer){
        if(StringUtils.isNotBlank(peer.getMonster().getDrop())){
            JSONArray array=JSONArray.parseArray(peer.getMonster().getDrop());
            if(peer.getExpAward()!=null){
                if(awardMap.get(GoodsConstant.GOODS_EXP)!=null){
                    int exp=awardMap.get(GoodsConstant.GOODS_EXP);
                    awardMap.put(GoodsConstant.GOODS_EXP,peer.getExpAward()+exp);
                }else{
                    awardMap.put(GoodsConstant.GOODS_EXP,peer.getExpAward());
                }
            }
            for(int i=0;i<array.size();i++){
                Map<String, Object> map=array.getJSONObject(i);
                if(map.get("rate")!=null && RandomUtil.probabilityEvent((int)map.get("rate"))){
                    for(Map.Entry<String,Object> entry:map.entrySet()){
                        String key=entry.getKey();
                        if(!key.equals("rate")){
                            if(awardMap.get(key)!=null && awardMap.get(key)>0){
                                awardMap.put(key,awardMap.get(key)+(int)entry.getValue());
                            }else{
                                awardMap.put(key,(int)entry.getValue());
                            }
                        }
                    }
                }else{
                    if(map.get("rate")==null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String key = entry.getKey();
                            if (awardMap.get(key) != null && awardMap.get(key) > 0) {
                                awardMap.put(key, awardMap.get(key) + (int) entry.getValue());
                            } else {
                                awardMap.put(key, (int) entry.getValue());
                            }
                        }
                    }
                }
            }
        }
    }
}
