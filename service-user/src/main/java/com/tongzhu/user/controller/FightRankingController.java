package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.model.User;
import com.tongzhu.user.po.FightRankingPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.FightRankingDetailVO;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/fightRanking")
@Api(value = "荣誉挑战controller", tags = {"荣誉挑战相关API"})
public class FightRankingController {


    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private FightRankingService fightRankingService;

    @Autowired
    private FightCommonService fightCommonService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FightingService fightingService;

    @Autowired
    private TaskService taskService;


    @ApiOperation(value = "对手匹配", notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户Id][portraitUrl 头像][name 名称]")
    })
    @PostMapping("/matchFighter")
    public BaseReturn matchFighter(@RequestBody FightRankingPO fightRankingPO) {
        if (StringUtils.isBlank(fightRankingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        Object object = redisService.get(RedisKey.FIGHT_COUNT + fightRankingPO.getUserId());
        if (object != null && ((int) object) > 15) {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "已超过当日挑战次数！");
        }
        User user = fightRankingService.matchFighter(fightRankingPO.getUserId());

        List<String> selfIds = Arrays.asList(fightRankingPO.getUserId());
        List<String> opponentIds = Arrays.asList(user.getUserId());

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> selfMapList = fightCommonService.getUserDetail(1, fightRankingPO.getUserId(), selfIds);
        List<Map<String, Object>> opponentMapList = fightCommonService.getUserDetail(2, fightRankingPO.getUserId(), opponentIds);

        result.put("selfGroup", selfMapList);
        result.put("opponentGroup", opponentMapList);
        result.put("user", user);

        redisService.set(RedisKey.FIGHT_RANKING + fightRankingPO.getUserId(), fightRankingPO);
        if (object != null) {
            int count = (int) object;
            redisService.set(RedisKey.FIGHT_COUNT + fightRankingPO.getUserId(), count + 1, DateComputeUtil.getSecondsNextEarlyMorning());
        } else {
            redisService.set(RedisKey.FIGHT_COUNT + fightRankingPO.getUserId(), 1, DateComputeUtil.getSecondsNextEarlyMorning());
        }
        // 日常任务
        taskService.taskProgress(FightingConstant.TASK_TYPE_RYTZ, 1, fightRankingPO.getUserId());

        return new BaseReturn("匹配成功！", result);
    }

    @ApiOperation(value = "取消对手匹配", notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[userId 用户Id][portraitUrl 头像][name 名称]")
    })
    @PostMapping("/cancelMatchFighter")
    public BaseReturn cancelMatchFighter(@RequestBody FightRankingPO fightRankingPO) {
        if (StringUtils.isBlank(fightRankingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        redisService.remove(RedisKey.FIGHT_RANKING + fightRankingPO.getUserId());

        return new BaseReturn("匹配成功！");
    }


    @ApiOperation(value = "进入对手匹配显示详情", notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[seasonName 赛季][currentGrade 当前等级][currentScore 当前积分][fullScore 满级积分]<br/>" +
                    "[currentHonor 当前荣誉][currentWinRate 当前胜率][fightCount 剩余战斗次数][seasonAward 赛季奖励]<br/>" +
                    "[seasonAward：money 金币][seasonAward：honor 荣誉]" +
                    "[fightRankingLogList 匹配记录][fightRankingLogList：participantName 被匹配的人名][fightRankingLogList：fightDate 匹配时间]" +
                    "[fightRankingLogList：fightResult 匹配挑战结果][fightRankingLogList：changeScore 挑战得分增减数]")
    })
    @PostMapping("/fightRankingDetail")
    public BaseReturn fightRankingDetail(@RequestBody FightRankingPO fightRankingPO) {
        if (StringUtils.isBlank(fightRankingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        FightRankingDetailVO fightRankingDetailVO = fightRankingService.fightRankingDetail(fightRankingPO.getUserId());

        return new BaseReturn("查询成功！", fightRankingDetailVO);
    }

    @ApiOperation(value = "荣誉挑战战斗过程", notes = "请求参数：[userId 用户Id]")
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
                    "[bloodCount 回复了的生命值 group组号  index组编号 value 生命值 ][onceBloodCount 立即回复的血量][toxicosisCount 中毒伤害数 group组号  index组编号 value 伤害数][hurtBackCount 反噬伤害][deadPlayer 死亡者 ：group组号  index组编号]" +
                    "[weaponIdList 丢掉或打掉的武器（String数组）]")
    })
    @PostMapping("/challengeFight")
    public BaseReturn challengeFight(@RequestBody FightRankingPO fightRankingPO) {
        if (StringUtils.isBlank(fightRankingPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");

        fightRankingPO = (FightRankingPO) redisService.get(RedisKey.FIGHT_RANKING + fightRankingPO.getUserId());
        List<Combatant> selfGroup = (List<Combatant>) redisService.get("_fight_self_" + fightRankingPO.getUserId());
        List<Combatant> opponentGroup = (List<Combatant>) redisService.get("_fight_opponent_" + fightRankingPO.getUserId());
        List<Combatant> attackGroup = fightCommonService.getCombatant(selfGroup);
        List<Combatant> defendGroup = fightCommonService.getCombatant(opponentGroup);

        List<FightStepDetailVO> fightingStepList = new ArrayList<>(); //战斗过程列表
        boolean winResult = false;
        Map<String, Object> resultData = new HashMap<>();
        boolean selfWin = false;
        boolean opponentWin = false;

        Combatant self = selfGroup.get(0);
        Combatant opponent = opponentGroup.get(0);

        boolean isSelfFirst = fightCommonService.checkIsFirstAttack(self.getSkillList(), self.getSkillSettingList());

        if (isSelfFirst) {// 自己先攻击
            FightStepDetailVO stepDetail = new FightStepDetailVO();
            stepDetail.setType(100);
            stepDetail.setSelfIndex(self.getGroupIndex());
            stepDetail.setOpponentIndex(opponent.getGroupIndex());
            fightingStepList.add(stepDetail);
            FightStepDetailVO stepDetail1 = new FightStepDetailVO();
            FightingUtil.generateFightingStep(self, opponent, 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
            fightingStepList.add(stepDetail1);
            selfWin = fightingService.pvpFightProcess(self, opponent, fightingStepList);
            if (selfWin) {
                winResult = true;
            } else {
                winResult = false;
            }
        } else {// 对方先攻击
            FightStepDetailVO stepDetail = new FightStepDetailVO();
            stepDetail.setType(100);
            stepDetail.setSelfIndex(self.getGroupIndex());
            stepDetail.setOpponentIndex(opponent.getGroupIndex());
            fightingStepList.add(stepDetail);
            FightStepDetailVO stepDetail1 = new FightStepDetailVO();
            FightingUtil.generateFightingStep(opponent, self, 2, SkillConstant.SKILL_33, false, 1, 0, stepDetail1);
            fightingStepList.add(stepDetail1);
            opponentWin = fightingService.pvpFightProcess(opponent, self, fightingStepList);
            if (opponentWin) {
                winResult = false;
            } else {
                winResult = true;
            }
        }

        if (winResult) {
            List<Map<String, Object>> awardMapList = new ArrayList<>();
            int rankCount = RandomUtil.random(15, 20);
            fightRankingService.updateRanking(self.getUserId(), rankCount);
            FightStepDetailVO fightStepDetail = new FightStepDetailVO();
            fightStepDetail.setType(97);
            fightingStepList.add(fightStepDetail);
        } else {
            int rankCount = RandomUtil.random(13, 18);
            fightRankingService.updateRanking(self.getUserId(), rankCount);
            FightStepDetailVO fightStepDetail = new FightStepDetailVO();
            fightStepDetail.setType(97);
            fightingStepList.add(fightStepDetail);
        }

        resultData.put("selfGroup", attackGroup);
        resultData.put("opponentGroup", defendGroup);
        resultData.put("fightingStepList", fightingStepList);
        return new BaseReturn("挑战成功！", resultData);
    }
}
