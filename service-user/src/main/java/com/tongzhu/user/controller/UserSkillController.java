package com.tongzhu.user.controller;

import com.google.gson.Gson;
import com.tongzhu.constant.GoodsConstant;
import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.user.domain.UserGoods;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.model.*;
import com.tongzhu.user.po.UserSkillPO;
import com.tongzhu.user.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/skill")
@Api(value="用户技能controller",tags={"用户技能相关API"})
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SkillSettingService skillSettingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private SkillService skillService;



    @ApiOperation(value = "获取用户拥有的xx类型的技能",notes = "请求参数：[userId 用户Id][skillType 技能类型ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[skillList 技能列表][id 技能主键ID][skillId 技能ID][level 等级][canUpgrade 技能是否可升级 true:可升，false:不可升]" +
                    "[redTips 红点数组；1：有，0：无]")
    })
    @PostMapping("/getUserSkillList")
    public BaseReturn getUserSkillList(@RequestBody UserSkillPO userSkillPO){
        if(StringUtils.isBlank(userSkillPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(userSkillPO.getSkillType()==null || userSkillPO.getSkillType()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"技能类型ID为空！");

        List<UserSkill> userSkills= userSkillService.findByUserIdAndType(userSkillPO.getUserId(),userSkillPO.getSkillType());
        UserGoods userGoods=userGoodsService.getByUserIdAndGoodsId(userSkillPO.getUserId(),GoodsConstant.GOODS_MONEY);
        UserRole role=userRoleService.getByUserId(userSkillPO.getUserId());
        Map<String,Object> result=new HashMap<>();
        List<Map<String,Object>> skillList=new ArrayList<>();
        boolean isCanUpgrade=false;
        if(!CollectionUtils.isEmpty(userSkills) && role!=null){
            for(UserSkill skill:userSkills){
                Map<String,Object> map=new HashMap<>();
                map.put("id",skill.getId());
                map.put("skillId",skill.getSkillId());
                map.put("level",skill.getLevel());
                map.put("canUpgrade",false);
                SkillSetting setting=skillSettingService.getBySkillIdAndRoleLevel(skill.getSkillId(),role.getRoleLevel());
                SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(skill.getSkillId(),skill.getLevel());
                //UserRole userRoleN=userRoleService.getByUserId(userSkillPO.getUserId());
                if(setting!=null && setting.getSkillLevel()!=null && skillSetting!=null && skillSetting.getSkillLevel()!=null && userGoods!=null){
                    if(skillSetting.getSkillLevel()<setting.getSkillLevel() &&
                            skillSetting.getConsumeExperience()<=role.getExperience() &&
                            skillSetting.getConsumeMoney()<=userGoods.getAmount()){
                        map.put("canUpgrade",true);
                        isCanUpgrade=true;
                    }
                }
                skillList.add(map);
            }
        }
        int [] redTips=new int[4];
        for(int i=0;i<4;i++){
            if(userSkillPO.getSkillType().equals(i+1)){
                if(isCanUpgrade)
                    redTips[userSkillPO.getSkillType()-1]=1;
                else
                    redTips[userSkillPO.getSkillType()-1]=0;
            }else{
                boolean re=userSkillService.checkSkillCanUpgrade(userSkillPO.getUserId(),i+1);
                if(re)
                    redTips[i]=1;
                else
                    redTips[i]=0;
            }
        }
        result.put("skillList",skillList);
        result.put("redTips",redTips);
        return new BaseReturn("操作成功！",result);
    }




    @ApiOperation(value = "升级技能显示详情",notes = "请求参数：[userId 用户Id][id 技能主键ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[consumeMoney 消耗金币][skillLevel 技能等级]" +
                    "[consumeExperience 消耗经验][roleLevel 角色要求等级][canUpgrade 是否可以升级][currentDesc 技能效果][nextDesc 下级技能效果]")
    })
    @PostMapping("/upgradeSkillDetail")
    public BaseReturn upgradeSkillDetail(@RequestBody UserSkillPO userSkillPO){
        if(StringUtils.isBlank(userSkillPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(userSkillPO.getId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"技能主键ID为空！");

        UserSkill userSkill=userSkillService.getById(userSkillPO.getId());
        UserRole userRole=userRoleService.getByUserId(userSkill.getUserId());
        UserGoods userGoods=userGoodsService.getByUserIdAndGoodsId(userSkillPO.getUserId(),GoodsConstant.GOODS_MONEY);
        SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(userSkill.getSkillId(),userSkill.getLevel());
        SkillSetting skillSettingNext=skillSettingService.getBySkillIdAndSkillLevel(userSkill.getSkillId(),userSkill.getLevel()+1);
        boolean canUpgrade = false;
        if(userGoods!=null && skillSettingNext!=null){
            if(skillSettingNext.getRoleLevel()<=userRole.getRoleLevel() &&
                    userGoods.getAmount()>=skillSetting.getConsumeMoney() &&
                    skillSetting.getConsumeExperience()<=userRole.getExperience()){
                canUpgrade=true;
            }
        }
        Map<String,Object> result=new HashMap<>();
        result.put("skillLevel",userSkill.getLevel());
        result.put("consumeMoney",skillSetting.getConsumeMoney());
        result.put("consumeExperience",skillSetting.getConsumeExperience());
        result.put("roleLevel",skillSettingNext==null?"":skillSettingNext.getRoleLevel());
        result.put("canUpgrade",canUpgrade);
        List<String> currentDesc=new ArrayList<>();
        List<String> nextDesc=new ArrayList<>();
        if(skillSetting!=null && StringUtils.isNotBlank(skillSetting.getPro())){
            //JSONObject obj1=JSONObject.parseObject(skillSetting.getPro());
            //JsonObject returnData = new JsonParser().parse(skillSetting.getPro()).getAsJsonObject();
            Gson gson = new Gson();
            Map<String,Object> map1=gson.fromJson(skillSetting.getPro(),Map.class);
            for(Map.Entry<String,Object> entry:map1.entrySet()){
                currentDesc.add(entry.getValue().toString());
            }
            result.put("currentDesc",currentDesc.toArray());
        }
        if(skillSettingNext!=null && StringUtils.isNotBlank(skillSettingNext.getPro())){
            //JSONObject obj2=JSONObject.parseObject(skillSettingNext.getPro());
            Gson gson = new Gson();
            Map<String,Object> map2=gson.fromJson(skillSettingNext.getPro(),Map.class);
            for(Map.Entry<String,Object> entry:map2.entrySet()){
                nextDesc.add(entry.getValue().toString());
            }
            result.put("nextDesc", nextDesc.toArray());
        }
        return new BaseReturn("操作成功！",result);
    }





    @ApiOperation(value = "升级技能",notes = "请求参数：[userId 用户Id][id 技能主键ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[skill:skillLevel 当前等级][skill:consumeMoney 消耗金币]" +
                    "[skill:consumeExperience 消耗经验][skill:roleLevel 角色等级][skill:canUpgrade 是否可以升级][skill:currentDesc 技能效果]" +
                    "[skill:nextDesc 下级技能效果][goodsList:goodsId 物品ID][goodsList:amount 物品数量][currentExp 当前经验][redTips 红点数组；1：有，0：无]")
    })
    @PostMapping("/upgradeSkillLevel")
    public BaseReturn upgradeSkillLevel(@RequestBody UserSkillPO userSkillPO){
        if(StringUtils.isBlank(userSkillPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(userSkillPO.getId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"技能主键ID为空！");
        Map<String,Object> data=new HashMap<>();
        try {
            Map<String,Object> consume=userSkillService.upgradeSkillLevel(userSkillPO.getId());
            UserRole userRole=userRoleService.getByUserId(userSkillPO.getUserId());
            List<UserGoods> goodsList=new ArrayList<>();

            UserGoods money=new UserGoods();
            money.setGoodsId(GoodsConstant.GOODS_MONEY);
            money.setAmount((Integer)consume.get("money"));
            money.setType(1);
            money.setId(GoodsConstant.GOODS_MONEY);
            goodsList.add(money);
            data.put("currentExp",userRole.getExperience().intValue());
            data.put("goodsList",goodsList);

            UserSkill userSkill=userSkillService.getById(userSkillPO.getId());
            SkillSetting skillSetting=skillSettingService.getBySkillIdAndSkillLevel(userSkill.getSkillId(),userSkill.getLevel());
            SkillSetting skillSettingNext=skillSettingService.getBySkillIdAndSkillLevel(userSkill.getSkillId(),userSkill.getLevel()+1);
            SkillSetting skillSettingMax=skillSettingService.getBySkillIdAndRoleLevel(userSkill.getSkillId(),userRole.getRoleLevel());
            boolean canUpgrade=false;
            //canUpgrade=skillSetting.getRoleLevel()<skillSettingMax.getRoleLevel();

            SkillSetting setting=skillSettingService.getBySkillIdAndRoleLevel(userSkill.getSkillId(),userRole.getRoleLevel());
            if(setting!=null && setting.getSkillLevel()!=null){
                if(skillSetting.getRoleLevel()<skillSettingMax.getRoleLevel() &&
                        skillSetting.getConsumeExperience()<=userRole.getExperience() &&
                        skillSetting.getConsumeMoney()<=(int)consume.get("money")){
                    canUpgrade=true;
                }
            }


            Map<String,Object> result=new HashMap<>();
            result.put("skillLevel",userSkill.getLevel());
            result.put("consumeMoney",skillSetting.getConsumeMoney());
            result.put("consumeExperience",skillSetting.getConsumeExperience());
            result.put("roleLevel",skillSettingNext.getRoleLevel());
            result.put("canUpgrade",canUpgrade);
            List<String> currentDesc=new ArrayList<>();
            List<String> nextDesc=new ArrayList<>();
            if(skillSetting!=null && StringUtils.isNotBlank(skillSetting.getPro())){
                //JSONObject obj1=JSONObject.parseObject(skillSetting.getPro());
                Gson gson = new Gson();
                Map<String,Object> map1=gson.fromJson(skillSetting.getPro(),Map.class);
                for(Map.Entry<String,Object> entry:map1.entrySet()){
                    currentDesc.add(entry.getValue().toString());
                }
                result.put("currentDesc",currentDesc.toArray());
            }
            if(skillSettingNext!=null && StringUtils.isNotBlank(skillSettingNext.getPro())){
                //JSONObject obj2=JSONObject.parseObject(skillSettingNext.getPro());
                Gson gson = new Gson();
                Map<String,Object> map2=gson.fromJson(skillSettingNext.getPro(),Map.class);
                for(Map.Entry<String,Object> entry:map2.entrySet()){
                    nextDesc.add(entry.getValue().toString());
                }
                result.put("nextDesc", nextDesc.toArray());
            }
            data.put("skill",result);
            Skill skill=skillService.findBySkillId(userSkill.getSkillId());
            int [] redTips=new int[4];
            for(int i=0;i<4;i++){
                boolean re=userSkillService.checkSkillCanUpgrade(userSkillPO.getUserId(),i+1);
                if(re)
                    redTips[i]=1;
                else
                    redTips[i]=0;
            }
            data.put("redTips",redTips);
            return new BaseReturn("操作成功！",data);

        }catch (CheckException e){
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,e.getMessage());
        }


    }





    @ApiOperation(value = "一键升级技能",notes = "请求参数：[userId 用户Id][skillType 技能类型ID]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明[skillList:skillId 技能ID][skillList:level 等级]" +
                    "[skillList:canUpgrade 技能是否可升级 true:可升，false:不可升]" +
                    "[goodsList:goodsId 物品ID][goodsList:amount 物品数量][currentExp 当前经验][redTips 红点数组；1：有，0：无]")
    })
    @PostMapping("/upgradeAllSkill")
    public BaseReturn upgradeAllSkill(@RequestBody UserSkillPO userSkillPO){
        if(StringUtils.isBlank(userSkillPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(userSkillPO.getSkillType()==null || userSkillPO.getSkillType()<=0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"技能类型ID为空！");

        Map<String,Object> result=new HashMap<>();

        Map<String,Integer> consume=userSkillService.upgradeAllSkillByType(userSkillPO.getUserId(),userSkillPO.getSkillType());
        UserRole userRole=userRoleService.getByUserId(userSkillPO.getUserId());
        List<UserGoods> goodsList=new ArrayList<>();
        UserGoods money=new UserGoods();
        money.setGoodsId(GoodsConstant.GOODS_MONEY);
        money.setAmount(consume.get("money"));
        money.setType(1);
        money.setId(GoodsConstant.GOODS_MONEY);
        goodsList.add(money);
        result.put("currentExp",userRole.getExperience().intValue());
        result.put("goodsList",goodsList);

        List<UserSkill> userSkills= userSkillService.findByUserIdAndType(userSkillPO.getUserId(),userSkillPO.getSkillType());
        UserRole role=userRoleService.getByUserId(userSkillPO.getUserId());
        List<Map<String,Object>> skills=new ArrayList<>();
        if(userSkills!=null && userSkills.size()>0 && role!=null){
            for(UserSkill skill:userSkills){
                Map<String,Object> map=new HashMap<>();
                map.put("skillId",skill.getSkillId());
                map.put("id",skill.getId());
                map.put("level",skill.getLevel());
                map.put("canUpgrade",false);
                SkillSetting setting=skillSettingService.getBySkillIdAndRoleLevel(skill.getSkillId(),role.getRoleLevel());
                UserRole userRoleN=userRoleService.getByUserId(userSkillPO.getUserId());
                if(setting!=null && setting.getSkillLevel()!=null){
                    if(setting.getSkillLevel()>skill.getLevel() &&
                            setting.getConsumeExperience()<=userRoleN.getExperience() &&
                            setting.getConsumeMoney()<=consume.get("money")){
                        map.put("canUpgrade",true);
                    }
                }
                skills.add(map);
            }
        }
        result.put("skillList",skills);

        int [] redTips=new int[4];
        for(int i=0;i<4;i++){
            boolean re=userSkillService.checkSkillCanUpgrade(userSkillPO.getUserId(),i+1);
            if(re)
                redTips[i]=1;
            else
                redTips[i]=0;
        }
        result.put("redTips",redTips);
        return new BaseReturn("操作成功！",result);
    }

    @ApiOperation(value = "玩家获得的所有技能列表",notes = "请求参数：[userId 玩家用户ID][otherUserId 要查看的用户ID为空]")
    @ApiResponses({
            @ApiResponse(code=200,message="返回参数说明 [id 技能主键ID][skillId 技能ID]")
    })
    @PostMapping("/getOwnAllSkillList")
    public BaseReturn getOwnAllSkillList(@RequestBody UserSkillPO userSkillPO){
        if(StringUtils.isBlank(userSkillPO.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"用户ID为空！");
        if(StringUtils.isBlank(userSkillPO.getOtherUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR,"要查看的用户ID为空！");
        User user=userService.findByUserId(userSkillPO.getOtherUserId());
        if(user==null)
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR,"要查看的用户不存在！");

        List<UserSkill> skillList=userSkillService.findByUserId(userSkillPO.getOtherUserId());

        for(UserSkill skill:skillList){
            skill.setLevel(null);
            skill.setUserId(null);
            skill.setActiveStatus(null);
        }
        return new BaseReturn("获取成功！",skillList);
    }

}
