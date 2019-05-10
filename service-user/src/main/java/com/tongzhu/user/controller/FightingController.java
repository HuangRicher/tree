package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.constant.GoodsTypeConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.controller.vo.UserSkillVO;
import com.tongzhu.user.domain.Friend;
import com.tongzhu.user.domain.UserGoods;
import com.tongzhu.user.model.FightFriendExpSetting;
import com.tongzhu.user.model.UserRole;
import com.tongzhu.user.model.UserSkill;
import com.tongzhu.user.po.FightCopyPO;
import com.tongzhu.user.po.FightingPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.DateComputeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fighting")
@Api(value = "战斗controller", tags = {"战斗相关API"})
public class FightingController {

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FightingService fightingService;

    @Autowired
    private FightCommonService fightCommonService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private FriendService friendService;

    @Autowired
    private FightFriendExpSettingService fightFriendExpSettingService;



    @ApiOperation(value = "战斗准备", notes = "请求参数：[userId 用户Id][selfIds 挑战者ID列表][opponentIds 挑战对手ID列表][type 战斗类型  1:1Vn 2:nVn]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[selfGroup 己方][opponentGroup 对方][portraitUrl 头像url][name 己方名称][roleId 角色ID]" +
                    "[sex 性别][roleLevel 角色等级][blood 生命][furyValue 怒气值][userId 玩家ID][skillList 技能列表][skillList:id 技能主键ID][skillList:skillId 技能ID]</br>" +
                    "[vigour 精力][vigour:id][vigour:goodsId 道具ID][vigour:amount 数量][vigour:type 类型]")
    })
    @PostMapping("/readyForSingleFighting")
    public BaseReturn readyForSingleFighting(@RequestBody FightingPO fightingPO) {
        if (StringUtils.isBlank(fightingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (fightingPO.getSelfIds().isEmpty())
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "挑战者ID为空！");
        if (fightingPO.getOpponentIds().isEmpty())
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "挑战对手ID为空！");
        if (fightingPO.getType() == null || !(fightingPO.getType() == 1 || fightingPO.getType() == 2))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "请选择战斗类型！");
        if (fightingPO.getType() == 1 && fightingPO.getSelfIds().size() != 1 && fightingPO.getSelfIds().size() < 1) {
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "挑战类型与双方人数不匹配！");
        }
        if (fightingPO.getType() == 2 && fightingPO.getSelfIds().size() != fightingPO.getOpponentIds().size())
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "挑战类型与双方人数不匹配！");

        for(String id:fightingPO.getOpponentIds()){
            Friend friend=friendService.checkIsMyFriend(fightingPO.getUserId(),id);
            if (friend == null) {
                return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "挑战对手非好友！");
            }
        }

        /*if(redisService.get(fightingPO.getSelfIds().get(0)+RedisKey.FIGHT_PVP_WITH+fightingPO.getOpponentIds().get(0))!=null)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "一天只能挑战好友一次！");*/

        List<UserGoods> vigourList=new ArrayList<>();
        UserGoods vigour=new UserGoods();
        vigour.setGoodsId(GoodsConstant.GOODS_VIGOUR);
        vigour.setAmount(2);
        vigourList.add(vigour);
        Map<String, Object> result = new HashMap<>();
        List<UserGoods> userGoods=userGoodsService.subUserGoods(fightingPO.getUserId(),vigourList);
        if(CollectionUtils.isEmpty(userGoods)){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"精力不足");
        }else{
            UserGoods vigour1=userGoods.get(0);
            vigour1.setId(vigour1.getGoodsId()+"");
            vigour1.setType(GoodsTypeConstant.TYPE_PROP);
            result.put("vigour", vigour1);
        }
        List<Map<String, Object>> selfMapList = fightCommonService.getUserDetail(1, fightingPO.getUserId(), fightingPO.getSelfIds());
        List<Map<String, Object>> opponentMapList = fightCommonService.getUserDetail(2, fightingPO.getUserId(), fightingPO.getOpponentIds());

        result.put("selfGroup", selfMapList);
        result.put("opponentGroup", opponentMapList);

        redisService.set("_fight_" + fightingPO.getUserId(), fightingPO);
        //redisService.set(fightingPO.getSelfIds().get(0)+RedisKey.FIGHT_PVP_WITH+fightingPO.getOpponentIds().get(0),1, DateComputeUtil.getSecondsNextEarlyMorning());
        taskService.taskProgress(FightingConstant.TASK_TYPE_TZHY, 1, fightingPO.getUserId());
        return new BaseReturn("操作成功！", result);
    }


    @ApiOperation(value = "pVp战斗过程", notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[selfGroup 己方][opponentGroup 对方]</br>" +
                    "[fullBlood 生命上限][blood 当前生命值][furyValue 当前怒气值]</br>" +
                    "[weaponList 武器列表][weaponList:id 物品ID][equipmentId 装备ID] </br>" +
                    "[skillList:id 技能主键ID][skillList:skillId 技能ID][skillList:freezeCount 冻结回合]</br>" +
                    "[isWin  是否胜利 true:胜利 false:失败][award:goodsId 奖品ID][award:amount 奖品数量] </br>" +
                    "[fightingStepList 战斗过程列表]</br>" +
                    "[category 类别  1：闪避   2：格挡]" +
                    "[skillId 本回合所使用的技能ID 991：普通攻击 992：投掷武器 993：徒手攻击 1--56:系统技能ID] </br>" +
                    "[buffId 1: 被击晕了 2: 被定身了 3: 中毒了 4: 被沉默了 5: 处于虚弱状态 6: 失明了 </br>" +
                    "7: 命中提升了 8: 9: 攻击力提升了 10: 普攻抗性提升了 11: 回复了生命值 12: 对自己使用了净化 13: 自身受到了xxx点反噬伤害 </br>" +
                    "14: 触发了先发制人 15: 抵抗了眩晕 16: 使中毒效果暂缓了 17: 抵抗了定身 18: 格挡了一次攻击]</br>" +
                    "[winnerUserId 赢者][attacker 挑战者 attacker:group 组号（1是己方， 2是敌方） attacker:index 组编号][defender 被挑战者 defender:group 组号 defender:index 组编号 ]" +
                    "[bj 是否暴击 true:暴击 false：无暴击][hurtCount 本回合被攻击者掉的血量]</br>" +
                    "[buffUserId:group 组ID index:所在组位置Id ][buff：id 触发的buffId  amount 影响回合数 ][selfFuryValue： 己方怒气]" +
                    "[opponentFuryValue 对方怒气值]" +
                    "[type 100.战斗回合开始 </br>" +
                    "1.[玩家a]使用了技能xxx，对[玩家b]造成了xxx点伤害（暴击伤害），动画效果id </br>" +
                    "2.[玩家a]使用xx对[玩家b]进行攻击，造成了xxx点伤害（暴击伤害） </br>" +
                    "3.[玩家a]向[玩家b]投掷了一件xxx，造成了xxx点伤害（暴击伤害） </br>" +
                    "4.[玩家a]使用xxx对[玩家b]进行攻击，被[玩家B]躲开了（格挡了） </br>" +
                    "5.[玩家a]瞬间向[玩家b]投掷了3件武器，造成了xxx点伤害（暴击伤害） </br>" +
                    "6.[玩家a]向[玩家b]投掷了一件xxx，被[玩家B]躲开了（格挡了） </br>" +
                    "7.[玩家a]使用了技能xxx，动画效果id </br>" +
                    "97:战斗结束  98：回合开始  99: 死亡者  100:战斗中玩家换人 ] </br>" +
                    "[currentExpLevel 战斗后当前经验等级][currentExp 当前经验][fullExp 当前等级经验上限][roleLevel 角色等级][isUpgrade 是否升级]</br>" +
                    "[bloodCount 生命值回复 group组号  index组编号 value 生命值 ][addBlood 立即回复生命值 group组号  index组编号 value 生命值][onceBloodCount 立即回复的血量][toxicosisCount 中毒伤害数 group组号  index组编号 value 伤害数][hurtBackCount 反噬伤害][deadPlayer 死亡者 ：group组号  index组编号]" +
                    "[lostWeapon 丢掉或打掉的武器 group组号  index组编号 weaponIdList武器Id(String数组)]")
    })
    @PostMapping("/fighting")
    public BaseReturn fighting(@RequestBody FightingPO fightingPO) {
        if (StringUtils.isBlank(fightingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        fightingPO = (FightingPO) redisService.get("_fight_" + fightingPO.getUserId());
        List<Combatant> selfGroup = (List<Combatant>) redisService.get("_fight_self_" + fightingPO.getUserId());
        List<Combatant> opponentGroup = (List<Combatant>) redisService.get("_fight_opponent_" + fightingPO.getUserId());
        List<Combatant> attackGroup = fightCommonService.getCombatant(selfGroup);
        List<Combatant> defendGroup = fightCommonService.getCombatant(opponentGroup);
        int type = fightingPO.getType();
        List<FightStepDetailVO> fightingStepList = new ArrayList<>(); //战斗过程列表
        boolean winResult = false;
        Map<String, Object> resultData = new HashMap<>();
        boolean selfWin = false;
        boolean opponentWin = false;

        Combatant self = selfGroup.get(0);

        boolean isSelfFirst = fightCommonService.checkIsFirstAttack(self.getSkillList(), self.getSkillSettingList());

        if (type == 1) {//1Vn
            int i = 0;
            for (Combatant combatant : opponentGroup) {
                i++;
                if (isSelfFirst) {// 自己先攻击
                    FightStepDetailVO stepDetail = new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(self.getGroupIndex());
                    stepDetail.setOpponentIndex(combatant.getGroupIndex());
                    fightingStepList.add(stepDetail);
                    if (i == 1) {
                        FightStepDetailVO stepDetail1 = new FightStepDetailVO();
                        FightingUtil.generateFightingStep(self, combatant, 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
                        fightingStepList.add(stepDetail1);
                    }
                    selfWin = fightingService.pvpFightProcess(self, combatant, fightingStepList);
                    if (selfWin) {
                        winResult = true;
                        continue;
                    } else {
                        winResult = false;
                        break;
                    }
                } else {// 对方先攻击
                    FightStepDetailVO stepDetail = new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(self.getGroupIndex());
                    stepDetail.setOpponentIndex(combatant.getGroupIndex());
                    fightingStepList.add(stepDetail);
                    if (i == 1) {
                        FightStepDetailVO stepDetail1 = new FightStepDetailVO();
                        FightingUtil.generateFightingStep(combatant, self, 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
                        fightingStepList.add(stepDetail1);
                    }
                    opponentWin = fightingService.pvpFightProcess(combatant, self, fightingStepList);
                    if (opponentWin) {
                        winResult = false;
                        break;
                    } else {
                        winResult = true;
                        continue;
                    }
                }
            }
        }
        if (type == 2) {//nVn
            int count = 0, i = 0, j = 0;
            while (true) {
                count++;
                if (isSelfFirst) {// 自己先攻击
                    FightStepDetailVO stepDetail = new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(selfGroup.get(i).getGroupIndex());
                    stepDetail.setOpponentIndex(opponentGroup.get(j).getGroupIndex());
                    fightingStepList.add(stepDetail);
                    if (count == 1) {
                        FightStepDetailVO stepDetail1 = new FightStepDetailVO();
                        FightingUtil.generateFightingStep(selfGroup.get(i), opponentGroup.get(j), 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
                        fightingStepList.add(stepDetail1);
                    }
                    selfWin = fightingService.pvpFightProcess(selfGroup.get(i), opponentGroup.get(j), fightingStepList);
                    if (selfWin) {
                        if (j < opponentGroup.size() - 1) {
                            j++;
                        } else {
                            winResult = true;
                            break;
                        }
                    } else {
                        if (i < selfGroup.size() - 1) {
                            i++;
                        } else {
                            winResult = false;
                            break;
                        }
                    }
                } else {// 对方先攻击
                    FightStepDetailVO stepDetail = new FightStepDetailVO();
                    stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
                    stepDetail.setSelfIndex(selfGroup.get(i).getGroupIndex());
                    stepDetail.setOpponentIndex(opponentGroup.get(j).getGroupIndex());
                    fightingStepList.add(stepDetail);
                    if (count == 1) {
                        FightStepDetailVO stepDetail1 = new FightStepDetailVO();
                        FightingUtil.generateFightingStep(opponentGroup.get(j), selfGroup.get(i), 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
                        fightingStepList.add(stepDetail1);
                    }
                    opponentWin = fightingService.pvpFightProcess(opponentGroup.get(j), selfGroup.get(i), fightingStepList);
                    if (opponentWin) {
                        if (i < selfGroup.size() - 1) {
                            i++;
                        } else {
                            winResult = false;
                            break;
                        }
                    } else {
                        if (j < selfGroup.size() - 1) {
                            j++;
                        } else {
                            winResult = true;
                            break;
                        }
                    }
                }
            }
        }
        //pvp 胜利获得经验=自身等级*750    失败获得经验=自身等级*150
        //    胜利获得金币  5000          失败获得金币 1000
        UserRole userRole = userRoleService.getByUserId(self.getUserId());
        UserRole userOpent = userRoleService.getByUserId(opponentGroup.get(0).getUserId());
        FightFriendExpSetting fightFriendExpSetting=fightFriendExpSettingService.getByRoleLevel(userRole.getRoleLevel());
        if(fightFriendExpSetting!=null){
            if (winResult) {
                //挑战好友实际获得经验=ROUNDUP(胜利/失败获得经验*((100+对手等级-自身等级)/100),0)
                int selfExp = fightFriendExpSetting.getSuccessExp();
                selfExp=(int)(selfExp*(100+userOpent.getRoleLevel()-userRole.getRoleLevel())/100f);
                List<UserGoods> selfGoodsList = new ArrayList<>();
                UserGoods exp=new UserGoods();
                exp.setGoodsId(GoodsConstant.GOODS_EXP);
                exp.setAmount(selfExp);
                selfGoodsList.add(exp);
                Map<String, Object> resu = userRoleService.updateUserRoleExpCheck(self.getUserId(), selfExp);
                if (self.getUserId().equals(self.getUserId())) {
                    if (self.getRoleLevel() < (int) resu.get("roleLevel"))
                        resu.put("isUpgrade", true);
                    else
                        resu.put("isUpgrade", false);
                    resultData.put("currentExpLevel", resu);
                }
                redisService.set(fightingPO.getUserId()+RedisKey.FIGHT_PVP_AWARD,selfGoodsList);

                List<Map<String, Object>> awardMapList = new ArrayList<>();
                Map<String, Object> map1 = new HashMap<>();
                map1.put("goodsId", GoodsConstant.GOODS_EXP);
                map1.put("amount", selfExp);
                map1.put("type", 1);
                map1.put("id", GoodsConstant.GOODS_EXP);
                awardMapList.add(map1);
                resultData.put("award", awardMapList);
                resultData.put("isWin", true);

                FightStepDetailVO fightStepDetail = new FightStepDetailVO();
                fightStepDetail.setType(97);
                fightingStepList.add(fightStepDetail);
            } else {
                //挑战好友实际获得经验=ROUNDUP(胜利/失败获得经验*((100+对手等级-自身等级)/100),0)
                int selfExp = fightFriendExpSetting.getFailExp();
                selfExp=(int)(selfExp*(100+userOpent.getRoleLevel()-userRole.getRoleLevel())/100f);
                List<UserGoods> selfGoodsList = new ArrayList<>();
                UserGoods exp = new UserGoods();
                exp.setGoodsId(GoodsConstant.GOODS_EXP);
                exp.setAmount(selfExp);
                selfGoodsList.add(exp);
                redisService.set(fightingPO.getUserId()+RedisKey.FIGHT_PVP_AWARD,selfGoodsList);

                Map<String, Object> resu = userRoleService.updateUserRoleExpCheck(self.getUserId(), selfExp);
                if (self.getUserId().equals(self.getUserId())) {
                    if (self.getRoleLevel() < (int) resu.get("roleLevel"))
                        resu.put("isUpgrade", true);
                    else
                        resu.put("isUpgrade", false);
                    resultData.put("currentExpLevel", resu);
                }


                List<Map<String, Object>> awardMapList = new ArrayList<>();
                Map<String, Object> map1 = new HashMap<>();
                map1.put("goodsId", GoodsConstant.GOODS_EXP);
                map1.put("amount", selfExp);
                map1.put("type", 1);
                map1.put("id", GoodsConstant.GOODS_EXP);
                awardMapList.add(map1);
                resultData.put("award", awardMapList);
                resultData.put("isWin", false);

                FightStepDetailVO fightStepDetail = new FightStepDetailVO();
                fightStepDetail.setType(97);
                fightingStepList.add(fightStepDetail);
            }
        }

        resultData.put("selfGroup", attackGroup);
        resultData.put("opponentGroup", defendGroup);
        resultData.put("fightingStepList", fightingStepList);
        return new BaseReturn("操作成功！", resultData);
    }


    @ApiOperation(value = "领取PVP战斗奖励",notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code=200,message="")
    })
    @PostMapping("/receiveAward")
    public BaseReturn receiveAward(@RequestBody FightingPO fightingPO){
        if(StringUtils.isBlank(fightingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        if(redisService.get(fightingPO.getUserId()+RedisKey.FIGHT_PVP_AWARD)!=null){
            Map<String,Object> result=new HashMap<>();
            List<UserGoods> awardList=new ArrayList<>();
            List<UserGoods> selfGoodsList=(List<UserGoods>)redisService.get(fightingPO.getUserId()+RedisKey.FIGHT_PVP_AWARD);
            List<UserGoods> goodsList=new ArrayList<>();
            List<String> goodsIdList=new ArrayList<>();
            UserGoods exp=null;
            Map<String,Object> data=new HashMap<>();
            for(UserGoods goods:selfGoodsList){
                if (GoodsConstant.GOODS_EXP.equals(goods.getGoodsId()+"")) {
                    exp=new UserGoods();
                    exp.setGoodsId(GoodsConstant.GOODS_EXP);
                    exp.setAmount(goods.getAmount());
                    exp.setId(GoodsConstant.GOODS_EXP);
                    exp.setType(GoodsTypeConstant.TYPE_PROP);
                    data=userRoleService.updateUserRoleExp(fightingPO.getUserId(), goods.getAmount());
                }else if(GoodsConstant.GOODS_MONEY.equals(goods.getGoodsId()+"")){
                    UserGoods goodsMoney=new UserGoods();
                    goodsMoney.setGoodsId(GoodsConstant.GOODS_MONEY);
                    goodsMoney.setAmount(goods.getAmount());
                    goodsMoney.setId(GoodsConstant.GOODS_MONEY);
                    goodsMoney.setType(GoodsTypeConstant.TYPE_PROP);
                    //awardList.add(goodsMoney);
                    goodsList.add(goodsMoney);
                }else{
                    goodsIdList.add(goods.getGoodsId()+"");
                    goodsList.add(goods);
                }
            }
            if(!CollectionUtils.isEmpty(goodsIdList)){
                int count=userGoodsService.queryBackpackSpace(fightingPO.getUserId(),goodsIdList);
                if(count<0){
                    userGoodsService.addMailByUserGoodsList(fightingPO.getUserId(),goodsList);
                }else{
                    if(!goodsList.isEmpty()){
                        userGoodsService.addUserGoods(fightingPO.getUserId(),goodsList);
                        awardList.addAll(goodsList);
                    }
                }
            }else{
                if(!goodsList.isEmpty()){
                    userGoodsService.addUserGoods(fightingPO.getUserId(),goodsList);
                    awardList.addAll(goodsList);
                }
            }
            if(exp!=null)
                awardList.add(exp);
            redisService.remove(fightingPO.getUserId()+RedisKey.FIGHT_PVP_AWARD);
            result.put("getAwardList",awardList);
            if(data!=null && data.entrySet().size()>0){
                for(Map.Entry<String,Object> entry:data.entrySet()){
                    result.put(entry.getKey(),entry.getValue());
                }
            }
            return new BaseReturn("领取成功！",result);
        }else{
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"不存在可领取的奖励！");
        }

    }


    private List<UserSkillVO> getUserSkillList(String userId) {
        List<UserSkill> skillData = userSkillService.findByUserId(userId);
        List<UserSkillVO> skillList = new ArrayList<>();
        for (UserSkill skill : skillData) {
            UserSkillVO skillVO = new UserSkillVO();
            skillVO.setId(skill.getId());
            skillVO.setSkillId(skill.getSkillId());
            skillList.add(skillVO);
        }
        return skillList;
    }


}
