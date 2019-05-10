package com.tongzhu.user.controller;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.constant.GoodsTypeConstant;
import com.tongzhu.constant.RedisKey;
import com.tongzhu.constant.RoleConstant;
import com.tongzhu.user.afight.FightCopyUtil;
import com.tongzhu.user.constant.FightingConstant;
import com.tongzhu.user.constant.NewPlayerGuideConstant;
import com.tongzhu.user.constant.SkillConstant;
import com.tongzhu.user.controller.vo.Combatant;
import com.tongzhu.user.controller.vo.FightFriendVO;
import com.tongzhu.user.controller.vo.FightStepDetailVO;
import com.tongzhu.user.domain.Friend;
import com.tongzhu.user.domain.FriendDO;
import com.tongzhu.user.domain.PropInfo;
import com.tongzhu.user.domain.UserGoods;
import com.tongzhu.user.mapper.vo.MonsterDO;
import com.tongzhu.user.model.*;
import com.tongzhu.user.po.FightCopyPO;
import com.tongzhu.user.po.NewCopyFightPO;
import com.tongzhu.user.redis.RedisService;
import com.tongzhu.user.service.*;
import com.tongzhu.user.service.vo.UserAdornEquip;
import com.tongzhu.user.util.FightingUtil;
import com.tongzhu.util.CollectionsUtil;
import com.tongzhu.util.DateComputeUtil;
import io.netty.handler.codec.http.CombinedHttpHeaders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/fightCopy")
@Api(value="新版战斗副本controller",tags={"新版战斗副本相关API"})
public class NewCopyFightController {

    @Autowired
    private NewCopyFightService newCopyFightService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private FightCopyService fightCopyService;

    @Autowired
    private CopyExpSettingService copyExpSettingService;

    @Autowired
    private RoleLevelSettingService roleLevelSettingService;
    @Autowired
    private NewPlayerGuideService newPlayerGuideService;


    private static final String USER_ID_ISNULL ="用户ID为空！";

    @ApiOperation(value = "副本列表",notes = "请求参数：[userId 用户Id][type 副本类型 1：单人 2：多人 3：boss]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[doCount 当天已挑战次数][copyId 副本ID]")
    })
    @PostMapping("/fightCopyList")
    public BaseReturn fightCopyList(@RequestBody FightCopyPO fightCopyPO){
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, USER_ID_ISNULL);
        if(fightCopyPO.getType()==null || fightCopyPO.getType()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择副本类型！");

        List<FightCopy> fightCopyList=fightCopyService.findByType(fightCopyPO.getType());
        if(fightCopyList.isEmpty()){
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"该类型副本不存在！");
        }else{
            List<Map<String,Object>> dataList=new ArrayList<>();
            for(FightCopy copy:fightCopyList){
                Map<String,Object> data=new HashMap<>();
                data.put("copyId",copy.getId());
                if(redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+copy.getId())==null){
                    data.put("doCount",0);
                }else {
                    int count = (int) redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+copy.getId());
                    data.put("doCount",count);
                }
                dataList.add(data);
            }
            return new BaseReturn("查询成功！",dataList);
        }
    }

    @ApiOperation(value = "购买或使用药水",notes = "请求参数：[userId 用户Id][type  1：使用  2：购买并使用][goodsId 药水ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[id][goodsId 道具ID][amount 数量][type 类型]")
    })
    @PostMapping("/useOrBuyLiquid")
    public BaseReturn useOrBuyLiquid(@RequestBody FightCopyPO fightCopyPO){
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, USER_ID_ISNULL);
        if(fightCopyPO.getType()==null || fightCopyPO.getType()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择购买还是使用！");
        if(StringUtils.isBlank(fightCopyPO.getGoodsId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择药水！");

        if(fightCopyPO.getType()==1){
            try {
                UserGoods goods=userGoodsService.onlyUseProp(fightCopyPO.getUserId(),fightCopyPO.getGoodsId(),1);
                if(redisService.get(RedisKey.USE_LIQUID+fightCopyPO.getUserId())!=null){
                    Map<String,Integer> data=(Map<String,Integer>)redisService.get(RedisKey.USE_LIQUID+fightCopyPO.getUserId());
                    data.put(fightCopyPO.getGoodsId(),1);
                    redisService.set(RedisKey.USE_LIQUID+fightCopyPO.getUserId(),data);
                }else{
                    Map<String,Integer> data=new HashMap<>();
                    data.put(fightCopyPO.getGoodsId(),1);
                    redisService.set(RedisKey.USE_LIQUID+fightCopyPO.getUserId(),data);
                }
                goods.setId(goods.getGoodsId()+"");
                return new BaseReturn("success",goods);
            }catch (Exception e){
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
            }
        }
        if(fightCopyPO.getType()==2){
            UserGoods goods=userGoodsService.buyAndUseProp(fightCopyPO.getUserId(),fightCopyPO.getGoodsId());
            if(redisService.get(RedisKey.USE_LIQUID+fightCopyPO.getUserId())!=null){
                Map<String,Integer> data=(Map<String,Integer>)redisService.get(RedisKey.USE_LIQUID+fightCopyPO.getUserId());
                data.put(fightCopyPO.getGoodsId(),1);
                redisService.set(RedisKey.USE_LIQUID+fightCopyPO.getUserId(),data);
            }else{
                Map<String,Integer> data=new HashMap<>();
                data.put(fightCopyPO.getGoodsId(),1);
                redisService.set(RedisKey.USE_LIQUID+fightCopyPO.getUserId(),data);
            }
            goods.setId(goods.getGoodsId()+"");
            return new BaseReturn("success",goods);
        }
        return new BaseReturn("success");
    }

    @ApiOperation(value = "战斗好友列表",notes = "请求参数说明 [userId 用户ID][")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[friendList 好友列表] [userId 用户ID] [name 用户名字][sex 性别] [portraitUrl 头像url]" +
                    "[roleId 角色ID] [roleLevel 角色等级]" +
                    "[isLovers 是否为情侣 true是  false不是][fightingCapacity 战斗力][fightStatus 组队状态 0空闲 1已组队]")
    })
    @PostMapping("/getFriendList")
    public BaseReturn getFriendList(@RequestBody FightCopyPO fightCopyPO){
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空!");

        List<FightFriendVO> fightFriendVOList=new ArrayList<>();
        List<FriendDO> friendDOList=friendService.findFriendList(fightCopyPO.getUserId());
        if(friendDOList!=null && !friendDOList.isEmpty()){
            friendDOList.forEach(friendDO -> {
                if(!RoleConstant.NPC_IDS.contains(","+friendDO.getUserId()+",")) {
                    FightFriendVO fightFriendVO = new FightFriendVO();
                    BeanUtils.copyProperties(friendDO, fightFriendVO);
                    if (redisService.get(fightCopyPO.getUserId() + RedisKey.FIGHT_PVE_TOGETHER + friendDO.getUserId()) != null) {
                        fightFriendVO.setFightStatus(1);
                    } else {
                        fightFriendVO.setFightStatus(0);
                    }
                    //UserRole userRole=userRoleService.getByUserId(friendDO.getUserId());
                    //List<PropInfo> infoList=userGoodsService.getUserWarePropInfo(friendDO.getUserId());
                    //RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
                    //PropInfo propInfo=new PropInfo();
                    //userRoleService.computePropInfo(propInfo,setting,userRole);

                    UserAdornEquip adornEquip = userGoodsService.getUserFightingCapacity(friendDO.getUserId());

                    fightFriendVO.setFightingCapacity(adornEquip.getAlwaysFighting().longValue());
                    fightFriendVO.setStatus(null);
                    fightFriendVO.setIntimacy(null);
                    fightFriendVOList.add(fightFriendVO);
                }
            });
        }
        return new BaseReturn("查询成功！",fightFriendVOList);
    }


    @ApiOperation(value = "副本战斗准备",notes = "请求参数：[userId 用户Id][copyId 副本Id][playerIds 玩家用户ID(数组)]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[selfGroup 己方][opponentGroup 对方][furyValue 怒气][portraitUrl 头像]</br>" +
                    "[roleId 角色ID][sex 性别][name 名称][roleLevel 角色等级][blood 血量][skillList 拥有技能][skillList:id 主键ID][skillList:skillId 技能Id] </br>" +
                    "[weaponList 武器列表][userId 玩家/怪物ID][count 该轮怪物数量][totalMonster 副本总怪物波数]")
    })
    @PostMapping("/readyForFightingCopy")
    public BaseReturn readyForFightingCopy(@RequestBody FightCopyPO fightCopyPO){
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, USER_ID_ISNULL);
        if(fightCopyPO.getCopyId()==null || fightCopyPO.getCopyId()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请选择副本！");
        if(fightCopyPO.getPlayerIds().isEmpty())
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"请传入玩家ID");

        Map<String,Object> result=new HashMap<>();

        if(999==fightCopyPO.getCopyId()){
            NewPlayerGuide guide = newPlayerGuideService.getByUserId(fightCopyPO.getUserId());
            if(null==guide || guide.getTaskId()== (NewPlayerGuideConstant.TASK_FIGHT_COPY_NEWER)){
                List<Map<String,Object>> selfMapList=newCopyFightService.getUserDetail(1,fightCopyPO.getUserId(), Arrays.asList(fightCopyPO.getUserId()),fightCopyPO.getCopyId(),null);
                List<Map<String,Object>> opponentMapList=newCopyFightService.getUserDetail(2,fightCopyPO.getUserId(),null,fightCopyPO.getCopyId(),200);
                for(Map<String,Object> data:opponentMapList){
                    if(data.get("furyValue")==null){
                        data.put("furyValue",0);
                    }
                }
                result.put("selfGroup",selfMapList);
                result.put("opponentGroup",opponentMapList);
                List<Combatant> combatantList=(List<Combatant>)redisService.get(RedisKey.FIGHT_PVE_OPPONENT+fightCopyPO.getUserId());
                result.put("totalMonster",combatantList.size());
                redisService.set(RedisKey.FIGHT_PVE+fightCopyPO.getUserId(),fightCopyPO,60*60l);
            }else{
                return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"玩家异常！");
            }
        }else{
            FightCopy fightCopy=fightCopyService.getById(fightCopyPO.getCopyId());
            if(fightCopy==null)
                return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"副本不匹配！");

            UserRole role=userRoleService.getByUserId(fightCopyPO.getUserId());
            if(role.getRoleLevel()<fightCopy.getMinRoleLevel())
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"等级不足！");

            Object object=redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+fightCopyPO.getCopyId());
            if(object!=null){
                int count=(int)redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+fightCopyPO.getCopyId());
                if(count>=fightCopy.getCommonCount()){
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"超过每日次数！");
                }
            }
            for(String id:fightCopyPO.getPlayerIds()){
                if(!fightCopyPO.getUserId().equals(id) && redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_PVE_TOGETHER+id)!=null)
                    return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"今日已组过队了！");
                if(!fightCopyPO.getUserId().equals(id)){
                    Friend friend=friendService.checkIsMyFriend(fightCopyPO.getUserId(),id);
                    if(friend==null)
                        return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"不能和非好友组队！");
                    redisService.set(fightCopyPO.getUserId()+RedisKey.FIGHT_PVE_TOGETHER+id,1,DateComputeUtil.getSecondsNextEarlyMorning());
                }
            }
            List<Map<String,Object>> selfMapList=newCopyFightService.getUserDetail(1,fightCopyPO.getUserId(),fightCopyPO.getPlayerIds(),fightCopyPO.getCopyId(),null);
            CopyExpSetting expSetting=copyExpSettingService.getByRoleLevelAndCopyId(role.getRoleLevel(),fightCopyPO.getCopyId());
            List<Map<String,Object>> opponentMapList=newCopyFightService.getUserDetail(2,fightCopyPO.getUserId(),null,fightCopyPO.getCopyId(),expSetting!=null?expSetting.getCopyExp():0);
            for(Map<String,Object> data:opponentMapList){
                if(data.get("furyValue")==null){
                    data.put("furyValue",0);
                }
            }
            result.put("selfGroup",selfMapList);
            result.put("opponentGroup",opponentMapList);
            List<Combatant> combatantList=(List<Combatant>)redisService.get(RedisKey.FIGHT_PVE_OPPONENT+fightCopyPO.getUserId());
            result.put("totalMonster",combatantList.size());
            redisService.set(RedisKey.FIGHT_PVE+fightCopyPO.getUserId(),fightCopyPO,60*60l);
        }
        return new BaseReturn("success",result);
    }



    @ApiOperation(value = "经验副本战斗过程",notes = "请求参数：[userId 用户Id]")
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
                    "97:战斗结束  98：回合开始  99: 死亡者  100:战斗中玩家换人 96:聊天 ] [content 聊天内容]</br>" +
                    "[currentExpLevel 战斗后当前经验等级][currentExp 当前经验][fullExp 当前等级经验上限][roleLevel 角色等级][isUpgrade 是否升级]</br>" +
                    "[bloodCount 回复了的生命值 group组号  index组编号 value 生命值 ][addBlood 立即回血 group组号  index组编号 value 生命值 ]</br>" +
                    "[toxicosisCount 中毒伤害数 group组号  index组编号 value 伤害数][hurtBackCount 反噬伤害][deadPlayer 死亡者 ：group组号  index组编号]" +
                    "[lostWeapon 丢掉或打掉的武器 group组号  index组编号 weaponIdList武器Id(String数组)]")
    })
    @PostMapping("/fighting")
    public BaseReturn fight(@RequestBody FightCopyPO fightCopyPO) throws Exception {
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        Map<String,Integer> awardMap=new HashMap<>();
        fightCopyPO=(FightCopyPO)redisService.get(RedisKey.FIGHT_PVE+fightCopyPO.getUserId());
        List<Combatant> selfGroup=(List<Combatant>)redisService.get(RedisKey.FIGHT_PVE_SELF+fightCopyPO.getUserId());
        List<Combatant> opponentGroup=(List<Combatant>)redisService.get(RedisKey.FIGHT_PVE_OPPONENT+fightCopyPO.getUserId());

        List<Map<String,Object>> attackGroup=new ArrayList<>();
        List<Map<String,Object>> defendGroup=new ArrayList<>();


        List<FightStepDetailVO> fightingStepList=new ArrayList<>(); //战斗过程列表
        Map<String,Object> resultData=new HashMap<>();
        Combatant self=selfGroup.get(0);
        boolean isSelfFirst=newCopyFightService.checkIsFirstAttack(self.getSkillList(),self.getSkillSettingList());

        FightStepDetailVO stepDetail=new FightStepDetailVO();
        stepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_100);
        stepDetail.setSelfIndex(selfGroup.get(0).getGroupIndex());
        stepDetail.setOpponentIndex(opponentGroup.get(0).getGroupIndex());
        fightingStepList.add(stepDetail);

        if(StringUtils.isNotBlank(selfGroup.get(0).getTalk())){
            FightStepDetailVO stepDetail2=new FightStepDetailVO();
            stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_96);
            stepDetail2.setGroup(1);
            stepDetail2.setIndex(1);
            stepDetail2.setContent(selfGroup.get(0).getTalk());
            fightingStepList.add(stepDetail2);
        }
        if(StringUtils.isNotBlank(opponentGroup.get(0).getTalk())){
            FightStepDetailVO stepDetail2=new FightStepDetailVO();
            stepDetail2.setType(FightingConstant.FIGHT_STEP_TYPE_96);
            stepDetail2.setIndex(opponentGroup.get(0).getCombatantList().get(0).getGroupIndex());
            stepDetail2.setGroup(opponentGroup.get(0).getGroup());
            stepDetail2.setContent(opponentGroup.get(0).getTalk());
            fightingStepList.add(stepDetail2);
        }

        FightCopyUtil.getCombatant(attackGroup,selfGroup.get(0));
        FightCopyUtil.getCombatant(defendGroup,opponentGroup.get(0));
        if(isSelfFirst){// 自己先攻击
            FightStepDetailVO stepDetail1=new FightStepDetailVO();
            FightingUtil.generateFightingStep(selfGroup.get(0),opponentGroup.get(0),2, SkillConstant.SKILL_33,false,1,0,stepDetail1);
            fightingStepList.add(stepDetail1);

            newCopyFightService.pveFightProcess(attackGroup,defendGroup,awardMap,selfGroup,opponentGroup,fightingStepList);
        }else{// 对方先攻击
            FightStepDetailVO stepDetail1=new FightStepDetailVO();
            FightingUtil.generateFightingStep(opponentGroup.get(0),selfGroup.get(0),2,SkillConstant.SKILL_33,false,1,0,stepDetail1);
            fightingStepList.add(stepDetail1);
            newCopyFightService.pveFightProcess(attackGroup,defendGroup,awardMap,opponentGroup,selfGroup,fightingStepList);
        }
        List<UserGoods> goodsList=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:awardMap.entrySet()){
            if (!entry.getKey().equals(GoodsConstant.GOODS_EXP)) {
                UserGoods goods=new UserGoods();
                goods.setGoodsId(entry.getKey());
                goods.setAmount(entry.getValue());
                goods.setId(entry.getKey());
                goods.setType(1);
                goodsList.add(goods);
            }
        }
        /*if(!goodsList.isEmpty()){
            userGoodsService.addUserGoods(fightCopyPO.getUserId(),goodsList);
        }*/
        /*for(Combatant combatant: selfGroup){
            if(combatant.getUserId().equals(fightCopyPO.getUserId())){
                self=combatant;
            }
        }*/
        //UserRole userRole=userRoleService.getByUserId(fightCopyPO.getUserId());
        //RoleLevelSetting setting=roleLevelSettingService.getByLevel(userRole.getRoleLevel());
        if(awardMap.get(GoodsConstant.GOODS_EXP)!=null){
            UserGoods goods=new UserGoods();
            goods.setGoodsId(GoodsConstant.GOODS_EXP);
            goods.setAmount(awardMap.get(GoodsConstant.GOODS_EXP));
            goods.setId(GoodsConstant.GOODS_EXP);
            goods.setType(GoodsTypeConstant.TYPE_PROP);
            goodsList.add(goods);
            Map<String, Object> resu=userRoleService.updateUserRoleExpCheck(fightCopyPO.getUserId(),awardMap.get(GoodsConstant.GOODS_EXP));
            if (self.getRoleLevel() < (int) resu.get("roleLevel"))
                resu.put("isUpgrade", true);
            else
                resu.put("isUpgrade", false);
            resultData.put("currentExpLevel", resu);
        }else{
            Map<String, Object> resu=userRoleService.updateUserRoleExpCheck(fightCopyPO.getUserId(),0);
            if (self.getRoleLevel() < (int) resu.get("roleLevel"))
                resu.put("isUpgrade", true);
            else
                resu.put("isUpgrade", false);
            resultData.put("currentExpLevel", resu);
        }
        redisService.set(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY_AWARD,awardMap);
        for(String id:fightCopyPO.getPlayerIds()){
            if(!fightCopyPO.getUserId().equals(id)){
                friendService.updateFriendFightStatus(fightCopyPO.getUserId(),id,1);
            }
        }
        for(int m=0;m<3;m++){
            CollectionsUtil.removeCombatant(defendGroup,0);
        }

        FightStepDetailVO fightStepDetail=new FightStepDetailVO();
        fightStepDetail.setType(FightingConstant.FIGHT_STEP_TYPE_97);
        fightingStepList.add(fightStepDetail);

        for(Map<String,Object> data:defendGroup){
            if(data.get("furyValue")==null){
                data.put("furyValue",0);
            }
        }
        resultData.put("award",goodsList);
        resultData.put("selfGroup",attackGroup);
        resultData.put("opponentGroup",defendGroup);
        resultData.put("fightingStepList",fightingStepList);

        Object object=redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+fightCopyPO.getCopyId());
        int fightCount=object==null?0:(int)object;
        fightCount=fightCount+1;
        redisService.set(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY+fightCopyPO.getCopyId(),fightCount, DateComputeUtil.getSecondsNextEarlyMorning());

        return new BaseReturn("操作成功！",resultData);
    }

    @ApiOperation(value = "领取副本战斗奖励",notes = "请求参数：[userId 用户Id]")
    @ApiResponses({
            @ApiResponse(code=200,message="")
    })
    @PostMapping("/receiveAward")
    public BaseReturn receiveAward(@RequestBody FightCopyPO fightCopyPO){
        Map<String,Object> result=new HashMap<>();
        if(StringUtils.isBlank(fightCopyPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");

        if(redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY_AWARD)!=null){
            Map<String,Integer> awardMap=(Map<String,Integer>)redisService.get(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY_AWARD);
            List<UserGoods> goodsList=new ArrayList<>();
            List<String> goodsIdList=new ArrayList<>();
            List<UserGoods> awardList=new ArrayList<>();
            if(awardMap.get(GoodsConstant.GOODS_MONEY)!=null){
                UserGoods goodsMoney=new UserGoods();
                goodsMoney.setGoodsId(GoodsConstant.GOODS_MONEY);
                goodsMoney.setAmount(awardMap.get(GoodsConstant.GOODS_MONEY));
                goodsMoney.setId(GoodsConstant.GOODS_MONEY);
                goodsMoney.setType(GoodsTypeConstant.TYPE_PROP);
                //awardList.add(goodsMoney);
                goodsList.add(goodsMoney);
            }
            for(Map.Entry<String,Integer> entry:awardMap.entrySet()){
                if (!entry.getKey().equals(GoodsConstant.GOODS_EXP) &&!entry.getKey().equals(GoodsConstant.GOODS_MONEY)) {
                    goodsIdList.add(entry.getKey());
                    UserGoods goods=new UserGoods();
                    goods.setGoodsId(entry.getKey());
                    goods.setAmount(entry.getValue());
                    goods.setId(entry.getKey());
                    goods.setType(GoodsTypeConstant.TYPE_PROP);
                    goodsList.add(goods);
                }
            }
            if(!CollectionUtils.isEmpty(goodsIdList)){
                int count=userGoodsService.queryBackpackSpace(fightCopyPO.getUserId(),goodsIdList);
                if(count<0){
                    userGoodsService.addMailByUserGoodsList(fightCopyPO.getUserId(),goodsList);
                }else{
                    if(!goodsList.isEmpty()){
                        userGoodsService.addUserGoods(fightCopyPO.getUserId(),goodsList);
                        awardList.addAll(goodsList);
                    }
                }
            }else{
                if(!goodsList.isEmpty()){
                    userGoodsService.addUserGoods(fightCopyPO.getUserId(),goodsList);
                    awardList.addAll(goodsList);
                }
            }
            Map<String,Object> data=new HashMap<>();
            if(awardMap.get(GoodsConstant.GOODS_EXP)!=null){
                data=userRoleService.updateUserRoleExp(fightCopyPO.getUserId(),awardMap.get(GoodsConstant.GOODS_EXP));
            }else{
                data=userRoleService.updateUserRoleExp(fightCopyPO.getUserId(),0);
            }
            redisService.remove(fightCopyPO.getUserId()+RedisKey.FIGHT_COPY_AWARD);
            if(awardMap.get(GoodsConstant.GOODS_EXP)!=null){
                UserGoods exp=new UserGoods();
                exp.setGoodsId(GoodsConstant.GOODS_EXP);
                exp.setAmount(awardMap.get(GoodsConstant.GOODS_EXP));
                exp.setId(GoodsConstant.GOODS_EXP);
                exp.setType(GoodsTypeConstant.TYPE_PROP);
                awardList.add(exp);
            }
            result.put("getAwardList",awardList);
            if(data!=null && data.entrySet().size()>0){
                for(Map.Entry<String,Object> entry:data.entrySet()){
                    result.put(entry.getKey(),entry.getValue());
                }
            }
        }else{
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"不存在可领取的奖励！");
        }
        return new BaseReturn("领取成功！",result);
    }
}
