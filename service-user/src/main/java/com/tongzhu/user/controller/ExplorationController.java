package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.domain.UserGoods;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.CopyExtraAward;
import com.tongzhu.user.model.ExplorationSetting;
import com.tongzhu.user.model.ExplorationUser;
import com.tongzhu.user.po.ExplorationPO;
import com.tongzhu.user.po.FightCopyPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.CopyExtraAwardVO;
import com.tongzhu.user.service.vo.ExplorationUserVO;
import com.tongzhu.user.util.FightingUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/exploration")
@Api(value="主线探索controller",tags={"主线探索相关API"})
public class ExplorationController {

    @Autowired
    private FightCopyService fightCopyService;

    @Autowired
    private ExplorationService explorationService;

    @Autowired
    private ExplorationMonsterService explorationMonsterService;

    @Autowired
    private FightCommonService fightCommonService;

    @Autowired
    private ExplorationSettingService explorationSettingService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private RedisService redisService;

    @Autowired
    private CopyExtraAwardService copyExtraAwardService;


    private static final String FIGHT_EXPLORATION="_fight_exploration_";
    private static final String FIGHT_EXPLORATION_OPPONENT="_fight_exploration_opponent_";
    private static final String FIGHT_EXPLORATION_SELF="_fight_exploration_self_";

    @ApiOperation(value = "用户探索列表",notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[explorationId 大关ID][status 状态 0锁定 1解锁][items:passId 小关ID][items:starCount 星星数]")
    })
    @PostMapping("/queryPassList")
    public BaseReturn queryPassList(@RequestBody ExplorationPO explorationPO){
        if(StringUtils.isBlank(explorationPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        List<ExplorationUserVO> list=explorationService.findByUserId(explorationPO.getUserId());

        return new BaseReturn("查找成功!",list);
    }


    @ApiOperation(value = "开始探索",notes = "请求参数：[userId 用户Id][explorationId 大关ID][passId 小关ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[explorationId 大关ID][status 状态 0锁定 1解锁][][]")
    })
    @PostMapping("/advanceGame")
    public BaseReturn advanceGame(@RequestBody ExplorationPO explorationPO){
        if(StringUtils.isBlank(explorationPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(explorationPO.getExplorationId()==null || explorationPO.getExplorationId()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择大关ID");
        if(explorationPO.getPassId()==null || explorationPO.getPassId()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择小关ID");


        List<MonsterDO> monsterDOList=explorationMonsterService.findByExplorationIdAndPassId(explorationPO.getExplorationId(),explorationPO.getPassId());
        List<String> ids= Arrays.asList(explorationPO.getUserId());
        Map<String,Object> result=new HashMap<>();
        List<Map<String,Object>> selfMapList=fightCommonService.getUserDetail(1,explorationPO.getUserId(),ids,null,null);
        ExplorationSetting setting=explorationSettingService.getByExplorationIdAndPassId(explorationPO.getExplorationId(),explorationPO.getPassId());
        int exp=0;
        if(setting!=null){
            ExplorationUser user=explorationService.findBuyExplorationIdAndPassId(explorationPO.getUserId(),explorationPO.getExplorationId(),explorationPO.getPassId());
            if(user==null){
                exp=setting.getFirstExp();
            }else{
                exp=setting.getNexExp();
            }
        }
        List<Map<String,Object>> opponentMapList=fightCommonService.getUserDetail(2,explorationPO.getUserId(),null,monsterDOList,exp);

        result.put("selfGroup",selfMapList);
        result.put("opponentGroup",opponentMapList);
        redisService.set(FIGHT_EXPLORATION+explorationPO.getUserId(),explorationPO,60*60l);

        return new BaseReturn("查找成功!");
    }

    @ApiOperation(value = "主线探索战斗过程",notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[selfGroup 己方][opponentGroup 对方]</br>" +
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
                    "[weaponIdList 丢掉或打掉的武器（String数组）]" )
    })
    @PostMapping("/fighting")
    public BaseReturn fighting(@RequestBody ExplorationPO explorationPO){
        if(StringUtils.isBlank(explorationPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        Map<String,Integer> awardMap=new HashMap<>();
        explorationPO=(ExplorationPO)redisService.get(FIGHT_EXPLORATION+explorationPO.getUserId());
        List<Combatant> selfGroup=(List<Combatant>)redisService.get(FIGHT_EXPLORATION_SELF+explorationPO.getUserId());
        List<Combatant> opponentGroup=(List<Combatant>)redisService.get(FIGHT_EXPLORATION_OPPONENT+explorationPO.getUserId());
        //List<Combatant> attackGroup=fightCommonService.getCombatant(selfGroup);
        //List<Combatant> defendGroup=fightCommonService.getCombatant(opponentGroup);

        List<Map<String,Object>> attackGroup=new ArrayList<>();
        List<Map<String,Object>> defendGroup=new ArrayList<>();

        List<FightStepDetailVO> fightingStepList=new ArrayList<>(); //战斗过程列表
        Map<String,Object> resultData=new HashMap<>();
        Combatant self=selfGroup.get(0);
        boolean isSelfFirst=fightCommonService.checkIsFirstAttack(self.getSkillList(),self.getSkillSettingList());
        int count=1,i=0,j=0;

        FightStepDetailVO stepDetail=new FightStepDetailVO();
        stepDetail.setType(100);
        stepDetail.setSelfIndex(selfGroup.get(i).getGroupIndex());
        stepDetail.setOpponentIndex(opponentGroup.get(j).getGroupIndex());
        fightingStepList.add(stepDetail);

        if(isSelfFirst){// 自己先攻击
            if(count==1){
                FightStepDetailVO stepDetail1=new FightStepDetailVO();
                FightingUtil.generateFightingStep(selfGroup.get(i),opponentGroup.get(j),2, SkillConstant.SKILL_33,false,1,0,stepDetail1);
                fightingStepList.add(stepDetail1);
            }
            fightCopyService.pveFightProcess(attackGroup,defendGroup,awardMap,selfGroup,opponentGroup,fightingStepList);
        }else{// 对方先攻击
            if(count==1){
                FightStepDetailVO stepDetail1=new FightStepDetailVO();
                FightingUtil.generateFightingStep(opponentGroup.get(j),selfGroup.get(i),2,SkillConstant.SKILL_33,false,1,0,stepDetail1);
                fightingStepList.add(stepDetail1);
            }
            fightCopyService.pveFightProcess(attackGroup,defendGroup,awardMap,opponentGroup,selfGroup,fightingStepList);
        }
        List<UserGoods> goodsList=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:awardMap.entrySet()){
            if (!entry.getKey().equals(GoodsConstant.GOODS_EXP)) {
                UserGoods goods=new UserGoods();
                goods.setGoodsId(entry.getKey());
                goods.setAmount(entry.getValue());
                goodsList.add(goods);
            }
        }
        //for(Combatant combatant:selfGroup){
            if(!goodsList.isEmpty())
                userGoodsService.addUserGoods(explorationPO.getUserId(),goodsList);
            if(awardMap.get(GoodsConstant.GOODS_EXP)!=null)
                userRoleService.updateUserRoleExp(explorationPO.getUserId(),awardMap.get(GoodsConstant.GOODS_EXP));
        //}

        Object object=redisService.get(RedisKey.EXPLORATION_FIRST_WIN+explorationPO.getUserId());
        if(object==null && !selfGroup.isEmpty() && opponentGroup.isEmpty()){
            List<CopyExtraAwardVO> list=copyExtraAwardService.getExtraAwardList();
            redisService.set(RedisKey.EXPLORATION_FIRST_WIN+explorationPO.getUserId(),1);
            resultData.put("awardBoxList",list);
        }
        resultData.put("award",awardMap);
        resultData.put("selfGroup",attackGroup);
        resultData.put("opponentGroup",defendGroup);
        resultData.put("fightingStepList",fightingStepList);
        return new BaseReturn("操作成功！",resultData);
    }


    @ApiOperation(value = "开启宝箱",notes = "请求参数说明 [userId 用户ID][id 宝箱ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[friendList 好友列表] [userId 用户ID] [name 用户名字][sex 性别] [portraitUrl 头像url]" +
                    "[roleId 角色ID] [roleLevel 角色等级]" +
                    "[isLovers 是否为情侣 true是  false不是][fightingCapacity 战斗力][fightStatus 组队状态 0空闲 1已组队]")
    })
    @PostMapping("/openExtraBox")
    public BaseReturn openExtraBox(@RequestBody FightCopyPO fightCopyPO){
        if(StringUtils.isBlank(fightCopyPO.getId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"宝箱ID为空!");
        CopyExtraAward award=copyExtraAwardService.getById(fightCopyPO.getId());
        if(award!=null){
            if(StringUtils.isNotBlank(award.getConsumeGoods())){
                List<UserGoods> goodsList=userGoodsService.openBox(award.getGoodsId(),award.getAmount(),award.getConsumeGoods());
            }else{
                List<UserGoods> goodsList=userGoodsService.openBox(award.getGoodsId(),award.getAmount());
            }
        }

        return new BaseReturn("开启成功！","");
    }

}
